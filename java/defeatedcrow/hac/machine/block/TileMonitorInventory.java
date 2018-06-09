package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileMonitorInventory extends TileMonitorBase {

	@Override
	public String unit() {
		return getSide().getFacing().name();
	}

	@Override
	public String amountString() {
		return amount == 0 ? "-----" : String.format("%1$.0f", amount);
	}

	@Override
	protected boolean updateAmount() {
		TileEntity target = world.getTileEntity(getPairPos());
		if (target != null && target.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
				getSide().getFacing())) {
			IItemHandler inv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
					getSide().getFacing());
			if (inv != null) {
				amountMax = 0;
				amount = 0;
				for (int i = 0; i < inv.getSlots(); i++) {
					if (!DCUtil.isEmpty(inv.getStackInSlot(i))) {
						int l = Math.min(inv.getSlotLimit(i), inv.getStackInSlot(i).getMaxStackSize());
						amountMax += l;
						amount += inv.getStackInSlot(i).getCount();
					} else {
						amountMax += inv.getSlotLimit(i);
					}
				}
				return true;
			}
		}
		return false;
	}

}
