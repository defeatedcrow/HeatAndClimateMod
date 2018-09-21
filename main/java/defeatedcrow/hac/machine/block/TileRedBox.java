package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.energy.ICrankReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.util.EnumFacing;

public class TileRedBox extends TileTorqueBase implements ICrankReceiver {

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return !isInputSide(side);
	}

	@Override
	public boolean isPressed() {
		return BlockRedBox.isLit(world, pos);
	}

	@Override
	public boolean isMaxPressed() {
		return false;
	}

	@Override
	public void setPressed(boolean flag) {
		BlockRedBox.changeLitState(world, pos, flag);
	}

	@Override
	public void setMaxPressed(boolean flag) {}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

}
