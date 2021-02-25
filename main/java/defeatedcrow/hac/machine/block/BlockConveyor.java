package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConveyor extends BlockTorqueBase {

	protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.125D, 0.25D, 0.0D, 0.875D, 0.375D, 1.0D);
	protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.0D, 0.25D, 0.125D, 1.0D, 0.375D, 0.875D);

	public BlockConveyor(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
		this.setHorizontal();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileConveyor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getSide(state, DCState.SIDE).face;
		switch (face) {
		case EAST:
			return AABB_EW;
		case NORTH:
			return AABB_NS;
		case SOUTH:
			return AABB_NS;
		case WEST:
			return AABB_EW;
		default:
			return AABB_NS;

		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity != null) {
			if (!(entity instanceof EntityItem) && entity.posY > pos.getY() + 0.20D) {
				EnumFacing face = DCState.getSide(state, DCState.SIDE).face;
				double mX = entity.motionX + face.getFrontOffsetX() * 0.1D;
				if (mX > 0.035D) {
					mX = 0.035D;
				}
				if (mX < -0.035D) {
					mX = -0.035D;
				}
				double mZ = entity.motionZ + face.getFrontOffsetZ() * 0.1D;
				if (mZ > 0.035D) {
					mZ = 0.035D;
				}
				if (mZ < -0.035D) {
					mZ = -0.035D;
				}

				double dX = 0D;
				if (face.rotateY().getFrontOffsetX() != 0 && entity.posX < pos.getX() + 0.4D) {
					dX = 0.035D;
				}
				if (face.rotateY().getFrontOffsetX() != 0 && entity.posX > pos.getX() + 0.6D) {
					dX = -0.035D;
				}
				double dZ = 0D;
				if (face.rotateY().getFrontOffsetZ() != 0 && entity.posZ < pos.getZ() + 0.4D) {
					dZ = 0.035D;
				}
				if (face.rotateY().getFrontOffsetZ() != 0 && entity.posZ > pos.getZ() + 0.6D) {
					dZ = -0.035D;
				}

				entity.motionX = mX + dX;
				entity.motionZ = mZ + dZ;
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileConveyor) {
				TileConveyor conv = (TileConveyor) tile;

				ItemStack drop1 = conv.getStackInSlot(0);
				if (!DCUtil.isEmpty(drop1)) {
					EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos
							.getZ() + 0.5D, drop1);
					float f3 = 0.05F;
					entityitem.motionX = (float) world.rand.nextGaussian() * f3;
					entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.25F;
					entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
					world.spawnEntity(entityitem);
				}

				ItemStack drop2 = conv.getStackInSlot(1);
				if (!DCUtil.isEmpty(drop2)) {
					EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos
							.getZ() + 0.5D, drop2);
					float f3 = 0.05F;
					entityitem.motionX = (float) world.rand.nextGaussian() * f3;
					entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.25F;
					entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
					world.spawnEntity(entityitem);
				}
			}

		}
		world.updateComparatorOutputLevel(pos, state.getBlock());
		super.breakBlock(world, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.NON_POWERED.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.ITEM.getLocalizedName() + DCName.TRANSPORT.getLocalizedName() + ": 1 item/16t");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.conveyor3"));
			tooltip.add(I18n.format("dcs.tip.conveyor"));
			tooltip.add(TextFormatting.RED.toString() + "SMELTING+" + TextFormatting.GRAY
					.toString() + " and " + TextFormatting.DARK_BLUE.toString() + "TIGHT" + TextFormatting.GRAY
							.toString() + ": " + I18n.format("dcs.tip.conveyor2"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

}
