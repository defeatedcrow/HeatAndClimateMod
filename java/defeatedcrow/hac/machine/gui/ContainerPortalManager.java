package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.block.TilePortalManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPortalManager extends Container {

	public final TilePortalManager tile;
	public final InventoryPlayer playerInv;

	public ContainerPortalManager(TilePortalManager chest, EntityPlayer player) {
		this.tile = chest;
		this.playerInv = player.inventory;
		chest.openInventory(player);

		for (int l = 0; l < 6; ++l) {
			this.addSlotToContainer(new Slot(chest, l, 44 + l * 18, 21));
			this.addSlotToContainer(new Slot(chest, 6 + l, 44 + l * 18, 51));
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tile.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);
		int lim = tile.getSizeInventory();

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < lim) {
				if (!this.mergeItemStack(itemstack1, lim, this.inventorySlots.size(), true))
					return null;
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, lim, false))
				return null;

			if (!DCUtil.isEmpty(itemstack1)) {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(playerIn, itemstack1);
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.tile.closeInventory(player);
	}

}
