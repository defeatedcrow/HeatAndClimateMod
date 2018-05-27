package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageBiomeGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBiomeGlass extends BlockDC {

	// Type上限
	public final int maxMeta;

	public BlockBiomeGlass(String s) {
		super(Material.GLASS, s);
		this.setHardness(0.2F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.TYPE4, 0).withProperty(DCState.POWERED,
				false));
		this.maxMeta = 3;
		this.fullBlock = false;
		this.lightOpacity = 0;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		for (int i = 0; i < maxMeta + 1; i++) {
			list.add(new ItemStack(this, 1, i));
		}
		return list;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(DCState.POWERED) ? 15 : 0;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}
		return i;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		if (i > maxMeta) {
			i = maxMeta;
		}
		boolean f = (meta & 8) > 0;
		IBlockState state = this.getDefaultState().withProperty(DCState.TYPE4, i);
		state = state.withProperty(DCState.POWERED, f);
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		int f = 0;

		i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}

		f = state.getValue(DCState.POWERED) ? 8 : 0;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.POWERED,
				DCState.TYPE4
		});
	}

	// biome shape
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			this.changePowerState(world, pos);
			world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.8F, 2.0F);
		}
		return true;
	}

	public static void changePowerState(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MagicInit.biomeOrb) {
			boolean m = DCState.getBool(state, DCState.POWERED);
			if (m) {
				if (!world.isRemote) {
					world.setBlockState(pos, state.withProperty(DCState.POWERED, false), 3);
				}
				rebuildBiome(world, pos);
			} else {
				if (!world.isRemote) {
					world.setBlockState(pos, state.withProperty(DCState.POWERED, true), 3);
				}
				shapeBiome(world, pos, DCState.getInt(state, DCState.TYPE4));
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		rebuildBiome(world, pos);
		super.breakBlock(world, pos, state);
	}

	public static void shapeBiome(World world, BlockPos pos, int meta) {
		BlockPos st = pos.add(-7, 0, -7);
		BlockPos en = pos.add(7, 0, 7);
		Iterable<BlockPos> poses = BlockPos.getAllInBox(st, en);
		for (BlockPos p : poses) {
			Chunk c = world.getChunkFromBlockCoords(p);
			int i = p.getX() & 15;
			int j = p.getZ() & 15;
			int k = c.getBiomeArray()[j << 4 | i] & 255;

			Biome biome = Biomes.PLAINS;
			switch (meta) {
			case 1:
				biome = Biomes.JUNGLE;
				break;
			case 2:
				biome = Biomes.DESERT;
				break;
			case 3:
				biome = Biomes.ICE_PLAINS;
				break;
			default:
			}
			k = Biome.getIdForBiome(biome);
			c.getBiomeArray()[j << 4 | i] = (byte) (k & 255);
		}
	}

	public static void rebuildBiome(World world, BlockPos pos) {
		BlockPos st = pos.add(-7, 0, -7);
		BlockPos en = pos.add(7, 0, 7);
		Iterable<BlockPos> poses = BlockPos.getAllInBox(st, en);
		for (BlockPos p : poses) {
			Chunk c = world.getChunkFromBlockCoords(p);
			int i = p.getX() & 15;
			int j = p.getZ() & 15;
			int k = c.getBiomeArray()[j << 4 | i] & 255;

			Biome biome = world.getBiomeProvider().getBiome(p, Biomes.PLAINS);
			k = Biome.getIdForBiome(biome);
			c.getBiomeArray()[j << 4 | i] = (byte) (k & 255);
		}

		if (!world.isRemote) {
			Biome biome2 = world.getBiomeProvider().getBiome(pos, Biomes.PLAINS);
			int k2 = Biome.getIdForBiome(biome2) & 255;
			DCMainPacket.INSTANCE.sendToAll(new MessageBiomeGlass(pos, (short) k2));
		}
	}

	public static void rebuildBiomeFromID(World world, BlockPos pos, int biomeId) {
		BlockPos st = pos.add(-7, 0, -7);
		BlockPos en = pos.add(7, 0, 7);
		Iterable<BlockPos> poses = BlockPos.getAllInBox(st, en);
		for (BlockPos p : poses) {
			Chunk c = world.getChunkFromBlockCoords(p);
			int i = p.getX() & 15;
			int j = p.getZ() & 15;
			int k = c.getBiomeArray()[j << 4 | i] & 255;
			c.getBiomeArray()[j << 4 | i] = (byte) (biomeId & 255);
		}
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add("This device change the surrounding biome.");
			tooltip.add("Right-click: Turn on/off this device.");
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
