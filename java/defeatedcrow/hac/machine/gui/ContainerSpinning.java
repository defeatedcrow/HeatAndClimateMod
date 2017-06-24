package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.machine.block.TileSpinningMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSpinning extends Container {

	public final TileSpinningMachine mill;
	public final InventoryPlayer player;

	private int currentBurn;
	private int maxBurn;
	private int climate;

	public ContainerSpinning(TileSpinningMachine tile, InventoryPlayer playerInv) {
		this.mill = tile;
		this.player = playerInv;

		this.addSlotToContainer(new Slot(tile, 0, 54, 32));

		this.addSlotToContainer(new SlotOutput(tile, 1, 106, 32));

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
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.mill);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = this.listeners.get(i);

			if (this.climate != this.mill.getField(2)) {
				icrafting.sendProgressBarUpdate(this, 2, this.mill.getField(2));
			}

			if (this.currentBurn != this.mill.getField(0)) {
				icrafting.sendProgressBarUpdate(this, 0, this.mill.getField(0));
			}

			if (this.maxBurn != this.mill.getField(1)) {
				icrafting.sendProgressBarUpdate(this, 1, this.mill.getField(1));
			}
		}

		this.climate = this.mill.getField(2);
		this.currentBurn = this.mill.getField(0);
		this.maxBurn = this.mill.getField(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.mill.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.mill.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 2) {
				if (!this.mergeItemStack(itemstack1, 2, 37, true))
					return null;
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, 1, false))
				return null;

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
