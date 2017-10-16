package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.block.TilePressMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerPressMachine extends Container {

	public final TilePressMachine machine;
	public final InventoryPlayer player;

	private int currentBurn;

	public ContainerPressMachine(TilePressMachine tile, InventoryPlayer playerInv) {
		this.machine = tile;
		this.player = playerInv;

		this.addSlotToContainer(new SlotMold(tile, 0, 24, 15));

		this.addSlotToContainer(new SlotOutput(tile, 1, 136, 24));

		for (int c = 0; c < 9; ++c) {
			this.addSlotToContainer(new Slot(tile, c + 2, 8 + c * 18, 75));
		}

		for (int a = 0; a < 3; ++a) {
			for (int b = 0; b < 3; ++b) {
				this.addSlotToContainer(new SlotDisplay(tile, b + a * 3 + 11, 44 + b * 18, 15 + a * 18));
			}
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 104 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 162));
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

			if (this.currentBurn != this.machine.getField(0)) {
				icrafting.sendWindowProperty(this, 0, this.machine.getField(0));
			}
		}

		this.currentBurn = this.machine.getField(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.machine.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.machine.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 12) {
				if (!this.mergeItemStack(itemstack1, 20, 46, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index > 19) {
				if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
					if (!this.mergeItemStack(itemstack1, 2, 11, false))
						return ItemStack.EMPTY;
				}
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
