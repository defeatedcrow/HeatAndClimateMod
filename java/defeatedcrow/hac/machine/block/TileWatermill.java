package defeatedcrow.hac.machine.block;

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

public class TileWatermill extends TileTorqueBase implements ITorqueProvider {

	@Override
	public void updateTile() {
		if (!worldObj.isRemote) {
			// 水流をチェック
			float flow = 0F;
			IBlockState state = worldObj.getBlockState(getWaterCheckPos());
			if (state.getBlock() instanceof BlockLiquid) {
				BlockLiquid liq = (BlockLiquid) state.getBlock();
				IBlockState under = worldObj.getBlockState(getWaterCheckPos().down());
				IBlockState upper = worldObj.getBlockState(getWaterCheckPos().up());
				if ((under.getMaterial().blocksMovement() || under.getMaterial().isLiquid())
						&& (upper.getMaterial().blocksMovement() || upper.getMaterial().isLiquid())) {
					flow = getGearTier() * 0.5F;
				}
			} else if (state.getBlock() instanceof BlockFluidClassic) {
				BlockFluidClassic flu = (BlockFluidClassic) state.getBlock();
				if (flu.getDensity(worldObj, getWaterCheckPos()) > 0
						&& flu.isFlowingVertically(worldObj, getWaterCheckPos())) {
					flow = getGearTier() * 0.5F;
				}
			}

			if (flow == 0F) {
				IBlockState sec = worldObj.getBlockState(pos.down());
				IBlockState sec2 = worldObj.getBlockState(getWaterCheckPos().down());
				if (sec.getBlock() instanceof BlockLiquid && sec2.getBlock() instanceof BlockLiquid) {
					if (sec.getValue(BlockLiquid.LEVEL) > sec2.getValue(BlockLiquid.LEVEL)) {
						float f = (15.0F - sec.getValue(BlockLiquid.LEVEL)) / 15.0F;
						flow = getGearTier() * 0.5F * f;
					}
				} else if (sec.getBlock() instanceof BlockFluidClassic
						&& sec2.getBlock() instanceof BlockFluidClassic) {
					BlockFluidClassic flu = (BlockFluidClassic) sec.getBlock();
					if (flu.getDensity(worldObj, pos.down()) > 0) {
						if (sec.getValue(BlockFluidBase.LEVEL) > sec2.getValue(BlockFluidBase.LEVEL)) {
							float f = (15.0F - sec.getValue(BlockLiquid.LEVEL)) / 15.0F;
							flow = getGearTier() * 0.5F * f;
						}
					}
				}
			}

			this.currentTorque = flow;

			// provider
			this.provideTorque(worldObj, getPos().offset(getOutputSide()), getOutputSide(), false);
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
	public EnumFacing getOutputSide() {
		return this.getBaseSide();
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

}
