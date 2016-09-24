package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateTileEntity;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.ClimateReceiveTile;
import defeatedcrow.hac.core.base.ClimateReceiverLockable;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileFan extends TileTorqueBase implements ITorqueReceiver, IClimateTileEntity {

	@SideOnly(Side.CLIENT)
	private defeatedcrow.hac.machine.client.ModelFan model;

	@Override
	@SideOnly(Side.CLIENT)
	protected void createModel() {
		if (model == null)
			model = new defeatedcrow.hac.machine.client.ModelFan();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public defeatedcrow.hac.core.client.base.DCTileModelBase getModel() {
		return model;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side != getBaseSide() && side != getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque()) {
			return false;
		}
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public float getGearTier() {
		return 1.0F;
	}

	@Override
	public float maxSpeed() {
		return 360.0F;
	}

	@Override
	public void updateTile() {
		super.updateTile();

		// 前方に向かって風を送る処理
		if (!worldObj.isRemote) {
			BlockFan fan = (BlockFan) MachineInit.fan;
			EnumFacing face = this.getBaseSide();
			for (int i = 2; i < 5; i++) {
				BlockPos target = this.getPos().offset(face, i);
				TileEntity targetTE = worldObj.getTileEntity(target);
				if (targetTE != null && targetTE instanceof ClimateReceiveTile) {
					if (!((ClimateReceiveTile) targetTE).getHeatTilePos().contains(getPos())) {
						((ClimateReceiveTile) targetTE).addHeatTilePos(getPos());
					}
					break;
				} else if (targetTE != null && targetTE instanceof ClimateReceiverLockable) {
					if (!((ClimateReceiverLockable) targetTE).getHeatTilePos().contains(getPos())) {
						DCLogger.debugLog("find target");
						((ClimateReceiverLockable) targetTE).addHeatTilePos(getPos());
					}
					break;
				} else if (worldObj.getBlockState(target).isOpaqueCube()) {
					break;
				}
			}
		}
	}

	@Override
	public DCHeatTier getHeatTier(BlockPos target) {
		return ClimateAPI.calculator.getAverageTemp(worldObj, getPos());
	}

	@Override
	public DCHumidity getHumidity(BlockPos target) {
		return ClimateAPI.calculator.getHumidity(worldObj, getPos());
	}

	@Override
	public DCAirflow getAirflow(BlockPos target) {
		if (this.currentTorque > 6.0F) {
			return DCAirflow.WIND;
		} else if (this.currentTorque > 1.0F) {
			return DCAirflow.FLOW;
		} else {
			return DCAirflow.TIGHT;
		}
	}

	@Override
	public boolean isActive() {
		return this.prevTorque > 0.0F;
	}

}
