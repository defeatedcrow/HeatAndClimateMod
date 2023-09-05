package defeatedcrow.hac.core.material.block;

import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class InventoryDC implements Container {

	private final int size;
	public final NonNullList<ItemStack> inv;
	final BlockEntity tile;

	public InventoryDC(int i, BlockEntity e) {
		size = i;
		inv = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
		tile = e;
	}

	@Override
	public void clearContent() {
		inv.clear();
	}

	@Override
	public int getContainerSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		boolean flag = true;
		for (ItemStack item : inv) {
			if (!DCUtil.isEmpty(item)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	public ItemStack getItem(int s) {
		if (s >= 0 && s < getContainerSize()) {
			return inv.get(s);
		} else
			return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItem(int s, int count) {
		if (s >= 0 && s < getContainerSize() && count > 0) {
			return inv.get(s).split(count);
		} else
			return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItemNoUpdate(int s) {
		if (s >= 0 && s < getContainerSize()) {
			return inv.set(s, ItemStack.EMPTY);
		} else
			return ItemStack.EMPTY;
	}

	@Override
	public void setItem(int s, ItemStack item) {
		if (s < 0 || s >= getContainerSize()) {
			return;
		} else {
			if (DCUtil.isEmpty(item)) {
				item = ItemStack.EMPTY;
			} else if (item.getCount() > getSlotLimit(s)) {
				item.setCount(getSlotLimit(s));
			}

			inv.set(s, item);

			this.setChanged();
		}
	}

	@Override
	public void setChanged() {
		tile.setChanged();
	}

	@Override
	public boolean stillValid(Player p_18946_) {
		return true;
	}

	/* * * * */

	public int getSlotLimit(int s) {
		return LARGE_MAX_STACK_SIZE;
	}

	public NonNullList<ItemStack> getSizedList(int from, int to) {
		NonNullList<ItemStack> ret = NonNullList.create();
		for (int i = from; i <= to; i++) {
			if (DCUtil.isEmpty(getItem(i))) {
				ret.add(ItemStack.EMPTY);
			} else {
				ret.add(getItem(i));
			}
		}
		return ret;
	}

	public int canIncrSlot(int s, ItemStack ins) {
		if (DCUtil.isEmpty(ins)) {
			return 0;
		}
		if (DCUtil.isEmpty(getItem(s))) {
			return getSlotLimit(s);
		}
		ItemStack slot = getItem(s);
		if (DCItemUtil.isSameItem(ins, slot, true)) {
			int i1 = slot.getCount() + ins.getCount();
			int limit = Math.min(getSlotLimit(s), slot.getMaxStackSize());
			if (i1 >= limit) {
				return limit - slot.getCount();
			} else {
				return ins.getCount();
			}
		}
		return 0;
	}

	public int incrStackInSlot(int s, ItemStack ins) {
		int add = canIncrSlot(s, ins);
		if (add >= 0) {
			if (!DCUtil.isEmpty(getItem(s))) {
				getItem(s).grow(add);
			} else {
				this.setItem(s, ins);
			}
			return add;
		}
		return 0;
	}

	public int canInsertResult(ItemStack item, int s1, int s2) {
		if (DCUtil.isEmpty(item))
			return -1;
		ItemStack ret = item.copy();
		int count = 0;
		for (int i = s1; i < s2; i++) {
			int l = canIncrSlot(i, item);
			ret.split(l);
			count += l;
			if (ret.isEmpty())
				break;
		}
		return count;
	}

	public int insertResult(ItemStack item, int s1, int s2) {
		if (DCUtil.isEmpty(item))
			return 0;
		ItemStack ret = item.copy();
		int count = 0;
		for (int i = s1; i < s2; i++) {
			int l = incrStackInSlot(i, ret);
			ret.split(l);
			count += l;
			if (ret.isEmpty())
				break;
		}
		return count;
	}

	/* NBT */

	public void readFromNBT(CompoundTag tag) {
		ContainerHelper.loadAllItems(tag, this.inv);
	}

	public CompoundTag writeToNBT(CompoundTag tag) {
		ContainerHelper.saveAllItems(tag, this.inv);
		return tag;
	}

}
