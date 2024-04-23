package defeatedcrow.hac.core.material.block;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class InventoryFilterWrapperDC extends SidedInvWrapper {

	final int[] filterdSlot;

	public static LazyOptional<IItemHandlerModifiable>[] create(WorldlyContainer inv, int[] slots, Direction... sides) {
		LazyOptional<IItemHandlerModifiable>[] ret = new LazyOptional[sides.length];
		for (int x = 0; x < sides.length; x++) {
			final Direction side = sides[x];
			ret[x] = LazyOptional.of(() -> new InventoryFilterWrapperDC(inv, slots, side));
		}
		return ret;
	}

	public InventoryFilterWrapperDC(WorldlyContainer inv, int[] slots, Direction side) {
		super(inv, side);
		filterdSlot = slots;
	}

	public boolean isIncludedFilterSlot(int slot) {
		if (filterdSlot != null || filterdSlot.length > 0) {
			for (int i : filterdSlot) {
				if (i == slot)
					return true;
			}
		}
		return false;
	}

	@Override
	@NotNull
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (isIncludedFilterSlot(slot)) {
			ItemStack stack = this.inv.getItem(slot);
			if (amount >= stack.getCount()) {
				amount = stack.getCount() - 1;
			}
		}
		if (amount <= 0)
			return ItemStack.EMPTY;

		return super.extractItem(slot, amount, simulate);
	}

}
