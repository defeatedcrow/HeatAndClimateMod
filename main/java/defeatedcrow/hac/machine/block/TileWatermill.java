package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileWatermill extends TileTorqueBase implements ITorqueProvider {

	@Override
	public void updateTile() {
		if (!world.isRemote) {
			// 水流をチェック
			float flow = 0F;
			IBlockState state = world.getBlockState(getWaterCheckPos());
			if (state.getBlock() instanceof BlockLiquid) {
				BlockLiquid liq = (BlockLiquid) state.getBlock();
				IBlockState under = world.getBlockState(getWaterCheckPos().down());
				IBlockState upper = world.getBlockState(getWaterCheckPos().up());
				if ((under.getMaterial().blocksMovement() || under.getMaterial().isLiquid()) && (upper.getMaterial()
						.blocksMovement() || upper.getMaterial().isLiquid())) {
					flow = getGearTier();
				}
			} else if (state.getBlock() instanceof BlockFluidClassic) {
				BlockFluidClassic flu = (BlockFluidClassic) state.getBlock();
				if (flu.getDensity(world, getWaterCheckPos()) > 0 && flu
						.isFlowingVertically(world, getWaterCheckPos())) {
					flow = getGearTier();
				}
			}

			if (flow == 0F) {
				IBlockState sec = world.getBlockState(pos.down());
				IBlockState sec2 = world.getBlockState(getWaterCheckPos().down());
				if (sec.getBlock() instanceof BlockLiquid && sec2.getBlock() instanceof BlockLiquid) {
					if (sec.getValue(BlockLiquid.LEVEL) > sec2.getValue(BlockLiquid.LEVEL)) {
						float f = (15.0F - sec.getValue(BlockLiquid.LEVEL)) / 15.0F;
						flow = getGearTier() * f;
					}
				} else if (sec.getBlock() instanceof BlockFluidClassic && sec2
						.getBlock() instanceof BlockFluidClassic) {
					BlockFluidClassic flu = (BlockFluidClassic) sec.getBlock();
					if (flu.getDensity(world, pos.down()) > 0) {
						if (sec.getValue(BlockFluidBase.LEVEL) > sec2.getValue(BlockFluidBase.LEVEL)) {
							float f = (15.0F - sec.getValue(BlockLiquid.LEVEL)) / 15.0F;
							flow = getGearTier() * f;
						}
					}
				}
			}

			this.currentTorque = flow;

			// provider
			for (EnumFacing side : getOutputSide()) {
				this.provideTorque(world, getPos().offset(side), side, false);
			}
		}
		super.updateTile();
	}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	@Override
	public float maxSpeed() {
		return 360.0F;
	}

	@Override
	public List<EnumFacing> getOutputSide() {
		List<EnumFacing> ret = Lists.newArrayList();
		ret.add(getBaseSide());
		return ret;
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F)
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		return false;
	}

	@Override
	public float provideTorque(World world, BlockPos outputPos, EnumFacing output, boolean sim) {
		float amo = this.getCurrentTorque();
		if (canProvideTorque(world, outputPos, output)) {
			ITorqueReceiver target = (ITorqueReceiver) world.getTileEntity(outputPos);
			float ret = target.receiveTorque(amo, output, sim);
			return ret;
		}
		return 0;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide();
	}

	public EnumFacing getWaterCheckSide() {
		EnumFacing side = getBaseSide().rotateAround(EnumFacing.Axis.Y);
		return side;
	}

	public BlockPos getWaterCheckPos() {
		EnumFacing side = getBaseSide().rotateAround(EnumFacing.Axis.Y);
		return pos.offset(side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(-1, -1, -1), getPos().add(1, 1, 1));
		return bb;
	}

}
