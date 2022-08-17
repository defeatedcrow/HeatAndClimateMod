package defeatedcrow.hac.main.block.device;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFacelessTileBlock;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSwedishTorch extends DCFacelessTileBlock implements IHeatTile {
	public BlockSwedishTorch(String s) {
		super(Material.WOOD, s, 16, false);
		this.setTickRandomly(true);
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
	public int tickRate(World worldIn) {
		return 20;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileSwedishTorch();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	// 設置・破壊処理
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {}

	@Override
	public int damageDropped(IBlockState state) {
		int i = DCState.getInt(state, DCState.TYPE16);
		int i2 = i & 7;
		if (i2 == 7)
			i2 = 1;
		return i2;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int i = DCState.getInt(state, DCState.TYPE16);
		if (i == 7 || i == 15)
			return Items.COAL;
		else
			return Item.getItemFromBlock(this);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN || side == EnumFacing.UP;
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int i = DCState.getInt(state, DCState.TYPE16);
		return i > 7 && i < 15;
	}

	public static boolean isEnd(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int i = DCState.getInt(state, DCState.TYPE16);
		return i == 7 || i == 15;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int i = DCState.getInt(state, DCState.TYPE16);
		return i > 7 && i < 15 ? 14 : 0;
	}

	// lit control
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (isLit(world, pos) && !world.isRemote) {
			boolean ext = false;
			if (world.isRaining() && world.canSeeSky(pos)) {
				ext = true;
			} else {
				ClimateSupplier sup = new ClimateSupplier(world, pos);
				if (sup.get().getHumidity() == DCHumidity.UNDERWATER) {
					ext = true;
				}
			}
			if (ext) {
				world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos
						.getZ() + 0.5F, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 1.0F, false);
				changeLitState(world, pos, false);
			} else if (rand.nextInt(4) == 0) {
				int i = DCState.getInt(state, DCState.TYPE16);
				int i2 = i + 1;
				if (i2 >= 15) {
					i2 = 7;
				}
				world.setBlockState(pos, state.withProperty(DCState.TYPE16, i2), 3);
				world.notifyNeighborsOfStateChange(pos, this, true);
			}
		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (isLit(world, pos)) {
			if (player != null && hand == EnumHand.MAIN_HAND) {
				if (!world.isRemote)
					changeLitState(world, pos, false);
				player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.8F, 1.0F);
				return true;
			}
		}
		return false;
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MainInit.swedishTorch) {
			int i = DCState.getInt(state, DCState.TYPE16);
			if (f) {
				int i2 = (i & 7) + 8;
				world.setBlockState(pos, state.withProperty(DCState.TYPE16, i2), 3);
			} else {
				int i2 = (i & 7);
				world.setBlockState(pos, state.withProperty(DCState.TYPE16, i2), 3);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && BlockSwedishTorch.isLit(world, pos)) {
			int c = ClimateMain.proxy.getParticleCount();
			if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
				double x = pos.getX() + 0.5D + rand.nextDouble() * 0.15D;
				double y = pos.getY() + 0.75D + rand.nextDouble() * 0.15D;
				double z = pos.getZ() + 0.5D + rand.nextDouble() * 0.15D;
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(I18n.format("dcs.tip.firestand1"));
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.RED.toString() + "OVEN");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.firestand2"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 1");
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos targrt, BlockPos source) {
		IBlockState state = world.getBlockState(source);
		int i = DCState.getInt(state, DCState.TYPE16);
		if (i > 7 && i < 15) { return DCHeatTier.OVEN; }
		return DCHeatTier.NORMAL;
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}
}
