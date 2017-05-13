package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.machine.block.TileReactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerReactor extends Container {

	public final TileReactor machine;
	public final InventoryPlayer player;

	private int currentBurn;
	private int[] current = new int[15];

	public ContainerReactor(TileReactor tile, InventoryPlayer playerInv) {
		this.machine = tile;
		this.player = playerInv;

		this.addSlotToContainer(new Slot(tile, 0, 26, 18));
		this.addSlotToContainer(new SlotOutput(tile, 1, 26, 44));
		this.addSlotToContainer(new Slot(tile, 2, 66, 18));
		this.addSlotToContainer(new SlotOutput(tile, 3, 66, 44));
		this.addSlotToContainer(new Slot(tile, 4, 108, 48));
		this.addSlotToContainer(new SlotOutput(tile, 5, 108, 74));
		this.addSlotToContainer(new Slot(tile, 6, 148, 48));
		this.addSlotToContainer(new SlotOutput(tile, 7, 148, 74));

		this.addSlotToContainer(new Slot(tile, 8, 98, 13));

		for (int c = 0; c < 4; ++c) {
			this.addSlotToContainer(new Slot(tile, c + 9, 12 + c * 18, 67));
			this.addSlotToContainer(new SlotOutput(tile, c + 13, 94 + c * 18, 97));
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 119 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 177));
		}
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.machine);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = this.listeners.get(i);

			for (int j = 0; j < this.machine.getFieldCount(); j++) {
				if (this.current[j] != this.machine.getField(j)) {
					icrafting.sendProgressBarUpdate(this, j, this.machine.getField(j));
				}
			}
		}

		for (int k = 0; k < this.machine.getFieldCount(); k++) {
			this.current[k] = this.machine.getField(k);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.machine.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.machine.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 16) {
				if (!this.mergeItemStack(itemstack1, 17, 52, true))
					return null;
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!this.mergeItemStack(itemstack1, 13, 16, false))
					return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(playerIn, itemstack1);
		}

		return itemstack;
	}
}
