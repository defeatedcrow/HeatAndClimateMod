package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.energy.ICrankDC;
import defeatedcrow.hac.api.energy.ICrankReceiver;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileCrank_S extends TileTorqueBase implements ITorqueReceiver, ICrankDC {

	@SideOnly(Side.CLIENT)
	private defeatedcrow.hac.machine.client.ModelCrank_S model;

	@Override
	@SideOnly(Side.CLIENT)
	protected void createModel() {
		if (model == null)
			model = new defeatedcrow.hac.machine.client.ModelCrank_S();
	}

	@Override
	public void updateTile() {
		super.updateTile();
	}

	@Override
	protected void onServerUpdate() {
		boolean power = this.outputPower() > 0F;
		boolean max = this.outputPower() >= 1.0F;
		TileEntity target = worldObj.getTileEntity(pos.offset(getBaseSide().getOpposite()));
		if (target != null && target instanceof ICrankReceiver) {
			ICrankReceiver crank = (ICrankReceiver) target;
			boolean b1 = crank.isPressed();
			boolean b2 = crank.isMaxPressed();
			if (b1 != power) {
				crank.setPressed(power);
				// DCLogger.debugLog("push! " + power);
			}
			if (b2 != max) {
				crank.setMaxPressed(max);
			}
		}
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
		return 8.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public defeatedcrow.hac.core.client.base.DCTileModelBase getModel() {
		return model;
	}

	@Override
	public float outputPower() {
		double rot = (float) (this.currentRotation * Math.PI / 360F);
		double cos = Math.cos(rot);
		return (float) cos;
	}

}
