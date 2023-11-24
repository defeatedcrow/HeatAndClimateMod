package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

// stackごと処理できるやつ
public class FluidItemWrapperStackable implements IFluidHandlerItem, ICapabilityProvider {

	@NotNull
	protected ItemStack container;
	protected int capacity = 1000;
	protected boolean canHotFluid = true;

	public FluidItemWrapperStackable(@NotNull ItemStack cont) {
		this.container = cont;
	}

	public FluidItemWrapperStackable(@NotNull ItemStack cont, int cap) {
		this.container = cont;
		this.capacity = cap;
	}

	public FluidItemWrapperStackable(@NotNull ItemStack cont, boolean b) {
		this(cont);
		this.canHotFluid = b;
	}

	@Override
	public @NotNull ItemStack getContainer() {
		return container;
	}

	protected void setFluid(FluidStack fluid) {
		CompoundTag tag = container.getOrCreateTag();
		CompoundTag tankTag = new CompoundTag();
		if (fluid.isEmpty()) {
			container.getTag().remove(TagKeyDC.getTankKey(1));
		} else {
			fluid.writeToNBT(tankTag);
			tankTag.putInt(TagKeyDC.CAPACITY, capacity);
			container.getTag().put(TagKeyDC.getTankKey(1), tankTag);
		}
	}

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public @NotNull FluidStack getFluidInTank(int i) {
		FluidStack fluid = FluidStack.EMPTY;
		if (!container.hasTag())
			return fluid;

		CompoundTag tag = container.getTag();
		if (tag.contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag = tag.getCompound(TagKeyDC.getTankKey(1));
			fluid = FluidStack.loadFluidStackFromNBT(tankTag);
		}
		return fluid;
	}

	@Override
	public int getTankCapacity(int tank) {
		return capacity;
	}

	@Override
	public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
		if (!canHotFluid && stack.getFluid().getFluidType().getTemperature(stack) > 573) {
			return false;
		}
		return true;
	}

	@Override
	public int fill(FluidStack fill, FluidAction action) {
		if (fill.isEmpty() || !isFluidValid(1, fill)) {
			return 0;
		}

		FluidStack target = getFluidInTank(1);

		if (target.isEmpty()) {
			int count = container.getCount() * capacity;
			if (count > fill.getAmount()) {
				return 0;
			}
			if (action.execute()) {
				FluidStack filled = fill.copy();
				filled.setAmount(count);
				setFluid(filled);
			}
			return count;
		} else {
			if (container.getCount() == 1 && DCFluidUtil.isSameFluid(target, fill)) {
				int count = container.getCount() * (capacity - target.getAmount());
				if (count > fill.getAmount()) {
					return 0;
				}
				if (action.execute()) {
					target.grow(count);
					setFluid(target);
				}
				return count;
			}
			return 0;
		}
	}

	@Override
	public @NotNull FluidStack drain(FluidStack drain, FluidAction action) {
		FluidStack target = getFluidInTank(1);
		if (drain.isEmpty() || !DCFluidUtil.isSameFluid(target, drain)) {
			return FluidStack.EMPTY;
		}
		return drain(drain.getAmount(), action);
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		FluidStack target = getFluidInTank(1);
		int count = container.getCount() * target.getAmount();
		if (maxDrain <= 0 || target.isEmpty() || count < maxDrain) {
			return FluidStack.EMPTY;
		}

		FluidStack ret = target.copy();
		ret.setAmount(count);

		if (action.execute()) {
			target.shrink(count);
			if (target.isEmpty()) {
				setContainerToEmpty();
			} else {
				setFluid(target);
			}
		}

		return ret;
	}

	protected void setContainerToEmpty() {
		setFluid(FluidStack.EMPTY);
	}

	private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);

	@Override
	@NotNull
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
		return ForgeCapabilities.FLUID_HANDLER_ITEM.orEmpty(capability, holder);
	}

}
