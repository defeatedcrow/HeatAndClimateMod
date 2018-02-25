package defeatedcrow.hac.magic.block;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockMace extends DCTileBlock {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

	public BlockMace(String s) {
		super(Material.IRON, s, 1);
		this.setSoundType(SoundType.METAL);
		this.setTickRandomly(true);
	}

	@Override
	public int tickRate(World world) {
		return 120;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.worldObj.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			world.setBlockToAir(pos);
		}
		return true;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// block側の気候処理は無し
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
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
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		ItemStack stack = new ItemStack(this, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("dcs.mace.energy", 640);
		stack.setTagCompound(tag);
		list.add(stack);
		return list;
	}

	// particleのON/OFF

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = this.getMetaFromState(state) & 3;
		return meta == 1 ? 4 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (world.isRemote && state != null && (state.getBlock().getMetaFromState(state) & 3) != 0) {
			double x = pos.getX() + rand.nextDouble();
			double y = pos.getY() + 1.0D + rand.nextDouble();
			double z = pos.getZ() + rand.nextDouble();

			Particle p = new ParticleBlink.Factory().createParticle(0, world, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);

		}
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != null && state.getBlock() instanceof BlockMace) {
			int m = DCState.getInt(state, DCState.TYPE4);
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 3);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != null && state.getBlock() instanceof BlockMace) {
			int meta = state.getBlock().getMetaFromState(state) & 3;
			return meta == 1;
		} else
			return false;
	}

	/* NBT */

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof TileMaceBase) {
			if (stack != null && stack.hasTagCompound()) {
				if (stack.getTagCompound().hasKey("dcs.mace.energy")) {
					int d = stack.getTagCompound().getInteger("dcs.mace.energy");
					((TileMaceBase) tile).setEnergy(d);
				}
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		int i = this.damageDropped(state);
		ItemStack drop = new ItemStack(this, 1, i);

		if (tile != null && tile instanceof TileMaceBase) {
			NBTTagCompound tag = new NBTTagCompound();
			int d = ((TileMaceBase) tile).getEnergy();
			tag.setInteger("dcs.mace.energy", d);
			drop.setTagCompound(tag);
		}

		if (!world.isRemote) {
			EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
					drop);
			float f3 = 0.05F;
			entityitem.motionX = (float) this.rand.nextGaussian() * f3;
			entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.25F;
			entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
			world.spawnEntityInWorld(entityitem);
		}
		world.updateComparatorOutputLevel(pos, state.getBlock());
		world.removeTileEntity(pos);

	}
}
