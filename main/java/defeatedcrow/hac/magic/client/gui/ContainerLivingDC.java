package defeatedcrow.hac.magic.client.gui;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLivingDC extends Container {

	public final InventoryLiving inv_liv;
	public final InventoryPlayer player;

	public ContainerLivingDC(EntityLiving livingIn, EntityPlayer playerIn) {
		this.inv_liv = new InventoryLiving(livingIn);
		this.player = playerIn.inventory;

		this.addSlotToContainer(new Slot(inv_liv, 0, 44, 9));
		this.addSlotToContainer(new Slot(inv_liv, 1, 44, 63));

		for (int j = 0; j < 4; ++j) {
			this.addSlotToContainer(new Slot(inv_liv, 5 - j, 116, 9 + j * 18));
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(player, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(player, l, 8 + l * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return inv_liv.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 8) {
				if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, 4, false)) {
				return ItemStack.EMPTY;
			}

			if (!DCUtil.isEmpty(itemstack1)) {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}

}
