package defeatedcrow.hac.machine.gui;

import javax.annotation.Nullable;

import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMold extends Slot {

	public SlotMold(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return stack != null && stack.getItem() != null && stack.getItem() instanceof IPressMold;
	}

}
