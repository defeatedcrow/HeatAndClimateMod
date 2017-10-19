package defeatedcrow.hac.main.client.gui;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInvalid extends Slot {

	public SlotInvalid(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	// 投入禁止
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return false;
	}

}
