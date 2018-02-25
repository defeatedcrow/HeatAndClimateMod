package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.plugin.cofh.RFDeviceHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Optional.Method;

public class TileMonitorRF extends TileMonitorBase {

	@Override
	public String unit() {
		return "RF";
	}

	@Override
	@Method(modid = "redstoneflux")
	protected boolean updateAmount() {
		TileEntity target = world.getTileEntity(getPairPos());
		if (target != null && RFDeviceHelper.isRFHandler(target)) {
			amount = RFDeviceHelper.getHandlerAmount(target, getSide().getFacing());
			amountMax = RFDeviceHelper.getHandlerMax(target, getSide().getFacing());
			return true;
		} else if (target != null && RFDeviceHelper.isRFStorage(target)) {
			amount = RFDeviceHelper.getStorageAmount(target);
			amountMax = RFDeviceHelper.getStorageMax(target);
			return true;
		}
		return false;
	}

}
