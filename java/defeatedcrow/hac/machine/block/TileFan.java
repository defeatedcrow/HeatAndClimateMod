package defeatedcrow.hac.machine.block;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.client.ModelFan;

public class TileFan extends TileTorqueBase implements ITorqueReceiver {

	@SideOnly(Side.CLIENT)
	private final ModelFan model = new ModelFan();

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
	@SideOnly(Side.CLIENT)
	public DCTileModelBase getModel() {
		return model;
	}

}
