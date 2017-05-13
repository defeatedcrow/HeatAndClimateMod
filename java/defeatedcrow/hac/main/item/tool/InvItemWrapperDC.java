package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * 決め打ちでItemEggだけ入れられるようにしたポンコツ実装例でござる
 */
public class InvItemWrapperDC implements IInventory, IItemHandler, ICapabilityProvider {

	protected final ItemStack container;

	public InvItemWrapperDC(ItemStack container) {
		this.container = container;
	}

	@Override
	public String getName() {
		return "dcs.item.inventory";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	@Override
	public boolean hasCapability(Capability<?> cap, EnumFacing face) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> cap, EnumFacing face) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this;
		return null;
	}

	@Override
	public int getSlots() {
		return this.getSizeInventory();
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (DCUtil.isEmpty(container))
			return null;
		if (!DCUtil.isEmpty(stack) && this.isItemValidForSlot(slot, stack)) {
			ItemStack current = this.getStackInSlot(0);
			int currentsize = 0;
			if (!DCUtil.isEmpty(current)) {
				currentsize = current.stackSize;
			}

			int limit = this.getInventoryStackLimit() - currentsize;
			int ret = this.getInventoryStackLimit() - stack.stackSize;
			if (ret > stack.getMaxStackSize()) {
				ret = stack.getMaxStackSize();
			}
			if (ret > limit) {
				ret = limit;
			}

			if (!simulate) {
				this.setInventorySlotContents(0, new ItemStack(stack.getItem(), ret + currentsize));
			}

			return new ItemStack(stack.getItem(), ret);
		}
		return null;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (DCUtil.isEmpty(container))
			return null;
		if (amount > 0) {
			ItemStack current = this.getStackInSlot(0);
			int currentsize = 0;
			if (DCUtil.isEmpty(current))
				return null;
			currentsize = current.stackSize;
			int ret = amount;
			if (ret > currentsize) {
				ret = currentsize;
			}
			if (ret > current.getMaxStackSize()) {
				ret = current.getMaxStackSize();
			}

			if (!simulate) {
				current.stackSize -= ret;
				if (current.stackSize <= 0) {
					current = null;
				}
				this.setInventorySlotContents(0, current);
			}

			return new ItemStack(current.getItem(), ret);
		}
		return null;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (!DCUtil.isEmpty(container)) {
			NBTTagCompound tag = container.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			int count = tag.getInteger("ItemCount");
			if (count > 0) {
				new ItemStack(getValidItem(), count);
			}
		}
		return null;
	}

	// sim不可能な以外はextractといっしょ
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (DCUtil.isEmpty(container))
			return null;
		if (count > 0) {
			ItemStack current = this.getStackInSlot(0);
			int currentsize = 0;
			if (DCUtil.isEmpty(current))
				return null;
			currentsize = current.stackSize;
			int ret = count;
			if (ret > currentsize) {
				ret = currentsize;
			}
			if (ret > current.getMaxStackSize()) {
				ret = current.getMaxStackSize();
			}

			current.stackSize -= ret;
			if (current.stackSize <= 0) {
				current = null;
			}
			this.setInventorySlotContents(0, current);

			return new ItemStack(current.getItem(), ret);
		}
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		this.setInventorySlotContents(index, null);
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (!DCUtil.isEmpty(container)) {
			NBTTagCompound tag = container.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			int count = tag.getInteger("ItemCount");
			if (DCUtil.isEmpty(stack) || stack.stackSize <= 0) {
				tag.setInteger("ItemCount", 0);
			} else {
				tag.setInteger("ItemCount", stack.stackSize);
			}
			container.setTagCompound(tag);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 256;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return !DCUtil.isEmpty(stack) && stack.getItem() == getValidItem();
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.setInventorySlotContents(0, null);
	}

	/**
	 * 入れられるものを制限してる
	 */
	private Item getValidItem() {
		return Items.EGG;
	}

}
