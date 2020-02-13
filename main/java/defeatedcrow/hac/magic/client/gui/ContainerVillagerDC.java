package defeatedcrow.hac.magic.client.gui;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerVillagerDC extends Container {

	public final InventoryBasic inv_vil;
	public final InventoryLiving inv_liv;
	public final InventoryPlayer player;

	public ContainerVillagerDC(EntityVillager villagerIn, EntityPlayer playerIn) {
		this.inv_vil = villagerIn.getVillagerInventory();
		this.inv_liv = new InventoryLiving(villagerIn);
		this.player = playerIn.inventory;

		for (int i = 0; i < 2; ++i) {
			for (int i2 = 0; i2 < 4; ++i2) {
				this.addSlotToContainer(new Slot(inv_vil, i2 + i * 4, 99 + i2 * 18, 27 + i * 18));
			}
		}

		this.addSlotToContainer(new Slot(inv_liv, 0, 7, 9));
		this.addSlotToContainer(new Slot(inv_liv, 1, 7, 63));

		for (int j = 0; j < 4; ++j) {
			this.addSlotToContainer(new Slot(inv_liv, 5 - j, 79, 9 + j * 18));
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
		return inv_vil.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 8) {
				if (!this.mergeItemStack(itemstack1, 12, 48, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, 12, false)) {
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
