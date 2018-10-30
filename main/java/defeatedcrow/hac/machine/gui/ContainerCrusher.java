package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.block.TileRollerCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCrusher extends Container {

	public final TileRollerCrusher machine;
	public final InventoryPlayer player;

	private int currentBurn;
	private int[] current = new int[4];

	public ContainerCrusher(TileRollerCrusher tile, InventoryPlayer playerInv) {
		this.machine = tile;
		this.player = playerInv;

		this.addSlotToContainer(new Slot(tile, 0, 30, 25));
		this.addSlotToContainer(new Slot(tile, 1, 136, 15));
		this.addSlotToContainer(new SlotOutput(tile, 2, 136, 52));

		this.addSlotToContainer(new Slot(tile, 6, 61, 25));

		for (int c = 0; c < 3; ++c) {
			this.addSlotToContainer(new SlotOutput(tile, c + 3, 98, 16 + c * 18));
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
					icrafting.sendWindowProperty(this, j, this.machine.getField(j));
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
		return this.machine.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 8) {
				if (!this.mergeItemStack(itemstack1, 8, 42, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!this.mergeItemStack(itemstack1, 0, 1, false))
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
