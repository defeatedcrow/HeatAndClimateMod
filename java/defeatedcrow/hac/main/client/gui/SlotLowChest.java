package defeatedcrow.hac.main.client.gui;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class SlotLowChest extends Slot {

	public SlotLowChest(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	// inventory持ちのItemを入れることはできない
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		if (stack != null) {
			if (stack.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
				return false;
			} else if (stack.hasTagCompound() && stack.getTagCompound().hasKey("InvItems")) {
				return false;
			}
		}
		return true;
	}

}
