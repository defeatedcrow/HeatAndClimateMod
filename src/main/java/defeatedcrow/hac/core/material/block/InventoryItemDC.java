package defeatedcrow.hac.core.material.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

public class InventoryItemDC implements Container, IItemHandlerModifiable, ICapabilityProvider {

	final ItemStack container;
	final Player player;

	public InventoryItemDC(Player playerIn) {
		player = playerIn;
		container = playerIn.getMainHandItem();
	}

	public InventoryItemDC(ItemStack item) {
		player = null;
		container = item;
	}

	@Override
	public void clearContent() {
		NonNullList<ItemStack> inv = getItemData();
		inv.clear();
		setItemData(inv);
	}

	@Override
	public int getContainerSize() {
		NonNullList<ItemStack> inv = getItemData();
		return inv.size();
	}

	@Override
	public boolean isEmpty() {
		NonNullList<ItemStack> inv = getItemData();
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
		validateSlotIndex(s);
		NonNullList<ItemStack> inv = getItemData();
		return inv.get(s);
	}

	@Override
	public ItemStack removeItem(int s, int count) {
		validateSlotIndex(s);
		NonNullList<ItemStack> inv = getItemData();
		ItemStack split = inv.get(s).split(count);
		setItemData(inv);
		return split;
	}

	@Override
	public ItemStack removeItemNoUpdate(int s) {
		validateSlotIndex(s);
		NonNullList<ItemStack> inv = getItemData();
		ItemStack split = inv.set(s, ItemStack.EMPTY);
		setItemData(inv);
		return split;
	}

	@Override
	public void setItem(int s, ItemStack item) {
		validateSlotIndex(s);
		if (s < 0 || s >= getContainerSize()) {
			return;
		} else {
			NonNullList<ItemStack> inv = getItemData();
			if (DCUtil.isEmpty(item)) {
				item = ItemStack.EMPTY;
			} else if (item.getCount() > getSlotLimit(s)) {
				item.setCount(getSlotLimit(s));
			}
			inv.set(s, item);
			setItemData(inv);
			this.setChanged();
		}
	}

	@Override
	public void setChanged() {
		if (player != null)
			player.getInventory().setChanged();
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	protected int validateSlotIndex(int slot) {
		if (slot < 0 || slot >= 5) {
			return 0;
		}
		return slot;
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return DCUtil.isEmpty(stack) || stack.is(TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public int getSlotLimit(int s) {
		ItemStack check = getItem(s);
		if (DCUtil.isEmpty(check)) {
			return this.getMaxStackSize();
		} else {
			return Math.min(getMaxStackSize(), check.getMaxStackSize());
		}
	}

	public NonNullList<ItemStack> getItemData() {
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
		if (!DCUtil.isEmpty(container)) {
			CompoundTag tag = container.getOrCreateTag();
			ContainerHelper.loadAllItems(tag, inv);
		}
		return inv;
	}

	public void setItemData(NonNullList<ItemStack> inv) {
		if (!DCUtil.isEmpty(container)) {
			CompoundTag tag = container.getOrCreateTag();
			ContainerHelper.saveAllItems(tag, inv);
			container.setTag(tag);
		}
	}

	@Override
	public int getSlots() {
		return getContainerSize();
	}

	@Override
	public @NotNull ItemStack getStackInSlot(int slot) {
		return getItem(slot);
	}

	@Override
	public void setStackInSlot(int slot, @NotNull ItemStack stack) {
		setItem(slot, stack);
	}

	@Override
	public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
		if (stack.isEmpty())
			return ItemStack.EMPTY;
		if (!isItemValid(slot, stack))
			return stack;
		validateSlotIndex(slot);
		NonNullList<ItemStack> inv = getItemData();
		ItemStack target = inv.get(slot);

		int lim = getSlotLimit(slot);
		if (!target.isEmpty()) {
			if (!ItemHandlerHelper.canItemStacksStack(stack, target))
				return stack;
			lim -= target.getCount();
		}
		if (lim <= 0)
			return stack;

		int count = Math.min(lim, stack.getCount());
		if (!simulate) {
			if (!target.isEmpty()) {
				inv.set(slot, ItemHandlerHelper.copyStackWithSize(stack, count));
			} else {
				target.grow(count);
			}
			setItemData(inv);
		}

		return stack.getCount() > count ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - count) : ItemStack.EMPTY;
	}

	@Override
	public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (amount == 0)
			return ItemStack.EMPTY;
		validateSlotIndex(slot);
		NonNullList<ItemStack> inv = getItemData();
		ItemStack target = inv.get(slot);
		if (target.isEmpty())
			return ItemStack.EMPTY;
		int count = Math.min(amount, target.getMaxStackSize());

		if (target.getCount() <= count) {
			if (!simulate) {
				inv.set(slot, ItemStack.EMPTY);
				setItemData(inv);
				return target;
			} else {
				return target.copy();
			}
		} else {
			if (!simulate) {
				inv.set(slot, ItemHandlerHelper.copyStackWithSize(target, target.getCount() - count));
				setItemData(inv);
			}

			return ItemHandlerHelper.copyStackWithSize(target, count);
		}
	}

	@Override
	public boolean isItemValid(int slot, @NotNull ItemStack stack) {
		return canPlaceItem(slot, stack);
	}

	private final LazyOptional<IItemHandler> holder = LazyOptional.of(() -> this);

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, holder);
	}

}
