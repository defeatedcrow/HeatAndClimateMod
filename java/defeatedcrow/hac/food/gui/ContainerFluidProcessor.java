package defeatedcrow.hac.food.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;

public class ContainerFluidProcessor extends Container {

	public final TileFluidProcessorBase processor;
	public final InventoryPlayer player;

	private int[] current = new int[10];

	public ContainerFluidProcessor(TileFluidProcessorBase tile, InventoryPlayer playerInv) {
		this.processor = tile;
		this.player = playerInv;

		// 液体系
		this.addSlotToContainer(new Slot(tile, 0, 19, 18));
		this.addSlotToContainer(new Slot(tile, 1, 19, 53));
		this.addSlotToContainer(new Slot(tile, 2, 140, 18));
		this.addSlotToContainer(new Slot(tile, 3, 140, 53));

		for (int i = 0; i < 3; i++) {
			this.addSlotToContainer(new Slot(tile, 4 + i, 57, 18 + 18 * i));
			this.addSlotToContainer(new Slot(tile, 7 + i, 102, 18 + 18 * i));
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
		listener.sendAllWindowProperties(this, this.processor);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = this.listeners.get(i);

			if (this.current[i] != this.processor.getField(i)) {
				icrafting.sendProgressBarUpdate(this, i, this.processor.getField(i));
			}

			this.current[i] = this.processor.getField(i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.processor.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.processor.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);
		int lim = 9;

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < lim) {
				if (!this.mergeItemStack(itemstack1, lim + 1, 36 + lim, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, lim, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(playerIn, itemstack1);
		}

		return itemstack;
	}
}
