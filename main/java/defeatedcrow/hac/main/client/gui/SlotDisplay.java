package defeatedcrow.hac.main.client.gui;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDisplay extends Slot {

	public SlotDisplay(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return false;
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return false;
	}

}
