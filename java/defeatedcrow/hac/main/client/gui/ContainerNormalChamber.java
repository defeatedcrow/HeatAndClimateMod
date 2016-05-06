package defeatedcrow.hac.main.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.main.block.device.TileNormalChamber;

public class ContainerNormalChamber extends Container {

	public final TileNormalChamber chamber;
	public final InventoryPlayer player;

	private int currentBurn;
	private int maxBurn;
	private int heatTier;

	public ContainerNormalChamber(TileNormalChamber cham, InventoryPlayer playerInv) {
		this.chamber = cham;
		this.player = playerInv;

		this.addSlotToContainer(new Slot(cham, 0, 80, 55));

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
	public void onCraftGuiOpened(ICrafting listener) {
		super.onCraftGuiOpened(listener);
		listener.sendAllWindowProperties(this, this.chamber);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = this.crafters.get(i);

			if (this.heatTier != this.chamber.getField(2)) {
				icrafting.sendProgressBarUpdate(this, 2, this.chamber.getField(2));
			}

			if (this.currentBurn != this.chamber.getField(0)) {
				icrafting.sendProgressBarUpdate(this, 0, this.chamber.getField(0));
			}

			if (this.maxBurn != this.chamber.getField(1)) {
				icrafting.sendProgressBarUpdate(this, 1, this.chamber.getField(1));
			}
		}

		this.heatTier = this.chamber.getField(2);
		this.currentBurn = this.chamber.getField(0);
		this.maxBurn = this.chamber.getField(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.chamber.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.chamber.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0) {
				if (!this.mergeItemStack(itemstack1, 1, 36, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
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
