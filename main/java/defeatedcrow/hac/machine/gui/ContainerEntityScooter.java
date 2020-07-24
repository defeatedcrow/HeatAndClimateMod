package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.entity.EntityScooter;
import defeatedcrow.hac.main.client.gui.SlotInvalid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerEntityScooter extends Container {

	public final EntityScooter entity;
	public final InventoryPlayer player;

	private int currentBurn;
	private int[] current = new int[5];

	public ContainerEntityScooter(EntityScooter entityIn, EntityPlayer playerIn) {
		entity = entityIn;
		this.player = playerIn.inventory;

		this.addSlotToContainer(new Slot(entity, 0, 19, 16));
		this.addSlotToContainer(new SlotInvalid(entity, 1, 19, 52));
		// this.addSlotToContainer(new SlotDisplay(entity, 2, 19, 70));

		for (int c = 0; c < 6; ++c) {
			this.addSlotToContainer(new Slot(entity, c + 3, 61 + c * 18, 16));
			this.addSlotToContainer(new Slot(entity, c + 9, 61 + c * 18, 34));
			this.addSlotToContainer(new Slot(entity, c + 15, 61 + c * 18, 52));
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerIn.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerIn.inventory, l, 8 + l * 18, 142));
		}
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, entity);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = this.listeners.get(i);

			for (int j = 0; j < entity.getFieldCount(); j++) {
				if (this.current[j] != entity.getField(j)) {
					icrafting.sendWindowProperty(this, j, entity.getField(j));
				}
			}
		}

		for (int k = 0; k < entity.getFieldCount(); k++) {
			this.current[k] = entity.getField(k);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		entity.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return entity.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 16) {
				if (!this.mergeItemStack(itemstack1, 17, this.inventorySlots.size(), true))
					return ItemStack.EMPTY;
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!this.mergeItemStack(itemstack1, 13, 16, false))
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
