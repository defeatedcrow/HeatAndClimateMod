package defeatedcrow.hac.magic.client.gui;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class InventoryLiving implements IInventory {

	private final EntityLiving owner;
	private boolean hasCustomName;

	public InventoryLiving(@Nonnull EntityLiving ownerIn) {
		this.owner = ownerIn;
	}

	@Override
	public String getName() {
		return "dcs.inv.living";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return owner.getDisplayName();
	}

	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	public boolean isEmpty() {
		for (int i1 = 0; i1 < 6; i1++) {
			if (!owner.getItemStackFromSlot(getSlot(i1)).isEmpty()) {
				return false;
			}
		}
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return owner.getItemStackFromSlot(getSlot(index));
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (!getStackInSlot(index).isEmpty() && count > 0) {
			return getStackInSlot(index).splitStack(count);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (!getStackInSlot(index).isEmpty()) {
			ItemStack ret = getStackInSlot(index).copy();
			owner.setItemStackToSlot(getSlot(index), ItemStack.EMPTY);
			return ret;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		owner.setItemStackToSlot(getSlot(index), stack.copy());
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < 6; i++) {
			removeStackFromSlot(i);
		}
	}

	private EntityEquipmentSlot getSlot(int index) {
		switch (index) {
		case 0:
			return EntityEquipmentSlot.MAINHAND;
		case 1:
			return EntityEquipmentSlot.OFFHAND;
		case 2:
			return EntityEquipmentSlot.FEET;
		case 3:
			return EntityEquipmentSlot.LEGS;
		case 4:
			return EntityEquipmentSlot.CHEST;
		case 5:
			return EntityEquipmentSlot.HEAD;
		default:
			return EntityEquipmentSlot.MAINHAND;
		}
	}

}
