package defeatedcrow.hac.food.gui;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import defeatedcrow.hac.main.client.gui.SlotInvalid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerBrewingTank extends Container {

	public final TileFluidProcessorBase processor;
	public final InventoryPlayer player;

	private int[] current = new int[10];

	public ContainerBrewingTank(TileFluidProcessorBase tile, InventoryPlayer playerInv) {
		this.processor = tile;
		this.player = playerInv;

		// 液体系
		this.addSlotToContainer(new Slot(tile, 0, 19, 18));
		this.addSlotToContainer(new SlotInvalid(tile, 1, 19, 54));
		this.addSlotToContainer(new Slot(tile, 2, 140, 18));
		this.addSlotToContainer(new SlotInvalid(tile, 3, 140, 54));

		for (int i = 0; i < 3; i++) {
			this.addSlotToContainer(new Slot(tile, 4 + i, 57, 18 + 18 * i));
			this.addSlotToContainer(new SlotInvalid(tile, 7 + i, 102, 18 + 18 * i));
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

			for (int j = 0; j < this.processor.getFieldCount(); j++) {
				if (this.current[j] != this.processor.getField(j)) {
					icrafting.sendWindowProperty(this, j, this.processor.getField(j));
				}
			}
		}

		for (int k = 0; k < this.processor.getFieldCount(); k++) {
			this.current[k] = this.processor.getField(k);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.processor.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.processor.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		int lim = 7;

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < lim) {
				if (!this.mergeItemStack(itemstack1, lim, this.inventorySlots.size(), true))
					return ItemStack.EMPTY;
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, lim, false))
				return ItemStack.EMPTY;

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
