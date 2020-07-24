package defeatedcrow.hac.food.gui;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.block.TileSilkwormBox;
import defeatedcrow.hac.main.client.gui.SlotDCTile;
import defeatedcrow.hac.main.client.gui.SlotInvalid;
import defeatedcrow.hac.main.client.gui.SlotSingle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSilkwormBox extends Container {

	public final TileSilkwormBox box;
	public final InventoryPlayer player;

	private int[] days = new int[5];

	public ContainerSilkwormBox(TileSilkwormBox tile, InventoryPlayer playerInv) {
		this.box = tile;
		this.player = playerInv;

		this.addSlotToContainer(new SlotDCTile(tile, 0, 26, 33));

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new SlotSingle(tile, i + 1, 8 + i * 18, 75));
		}

		for (int k = 0; k < 3; ++k) {
			for (int i2 = 0; i2 < 3; ++i2) {
				this.addSlotToContainer(new SlotInvalid(tile, i2 + k * 3 + 10, 62 + i2 * 18, 15 + k * 18));
			}

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
		listener.sendAllWindowProperties(this, this.box);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = this.listeners.get(i);

			if (this.days[i] != this.box.getField(i)) {
				icrafting.sendWindowProperty(this, i, this.box.getField(i));
			}

			this.days[i] = this.box.getField(i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.box.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.box.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		int lim = 19;

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < lim) {
				if (!this.mergeItemStack(itemstack1, lim, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
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
