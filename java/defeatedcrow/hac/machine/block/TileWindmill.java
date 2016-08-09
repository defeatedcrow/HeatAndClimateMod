package defeatedcrow.hac.machine.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;

public class TileWindmill extends TileTorqueBase implements ITorqueProvider {

	@SideOnly(Side.CLIENT)
	private defeatedcrow.hac.machine.client.ModelWindmill model;

	@Override
	@SideOnly(Side.CLIENT)
	protected void createModel() {
		if (model == null)
			model = new defeatedcrow.hac.machine.client.ModelWindmill();
	}

	@Override
	public void updateTile() {
		super.updateTile();
		if (!worldObj.isRemote) {
			// Airflowチェック
			DCAirflow air = ClimateAPI.calculator.getAirflow(worldObj, pos, 1, false);
			float wind = 0.0F;
			switch (air) {
			case NORMAL:
				wind = getGearTier() * 0.25F;
				break;
			case FLOW:
				wind = getGearTier() * 0.5F;
				break;
			case WIND:
				wind = getGearTier() * 1.0F;
				break;
			default:
			}
			if (this.getBaseSide() == DCUtil.getWorldWind(worldObj).getOpposite()) {
				wind *= 2.0F;
			}
			this.currentTorque += wind;

			// provider
			this.provideTorque(worldObj, getPos().offset(getOutputSide()), getOutputSide(), false);
		}
	}

	@Override
	public float getGearTier() {
		return 4.0F;
	}

	@Override
	public EnumFacing getOutputSide() {
		return this.getBaseSide();
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque() * this.getFrictionalForce();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F) {
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		}
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

	@Override
	@SideOnly(Side.CLIENT)
	public DCTileModelBase getModel() {
		return model;
	}

}
