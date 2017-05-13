package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileWindmill extends TileTorqueBase implements ITorqueProvider {

	@Override
	public void updateTile() {
		if (!worldObj.isRemote) {
			// Airflowチェック
			DCAirflow air = ClimateAPI.calculator.getAirflow(worldObj, pos);
			float wind = getGearTier() * 0.25F;
			switch (air) {
			case NORMAL:
				wind *= 0.5F;
				break;
			case FLOW:
				wind *= 1.0F;
				break;
			case WIND:
				wind *= 2.0F;
				break;
			default:
			}
			if (this.getBaseSide() == DCUtil.getWorldWind(worldObj).getOpposite()) {
				wind *= 2.0F;
			}
			this.currentTorque = wind;

			// provider
			for (EnumFacing side : getOutputSide()) {
				this.provideTorque(worldObj, getPos().offset(side), side, false);
			}
		}
		super.updateTile();
	}

	@Override
	public float getGearTier() {
		return 4.0F;
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
		return getOutputSide().contains(side);
	}

}
