package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.tileentity.TileEntity;

public class TileMonitorTorque extends TileMonitorBase {

	@Override
	public String unit() {
		return "Tq";
	}

	@Override
	protected boolean updateAmount() {
		TileEntity target = worldObj.getTileEntity(getPairPos());
		if (target != null && target instanceof TileTorqueBase) {
			TileTorqueBase tq = (TileTorqueBase) target;
			amount = tq.getCurrentTorque();
			amountMax = tq.maxTorque();
			return true;
		}
		return false;
	}

}
