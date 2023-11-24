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

public class FluidItemWrapperSided implements IFluidHandlerItem, ICapabilityProvider {

	@NotNull
	protected ItemStack container;
	protected int capacity = 1000;
	protected boolean isSided = false;
	protected boolean canHotFluid = true;

	public FluidItemWrapperSided(@NotNull ItemStack cont) {
		this.container = cont;
		if (cont.hasTag() && cont.getTag().contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag = cont.getTag().getCompound(TagKeyDC.getTankKey(1));
			if (tankTag.contains(TagKeyDC.CAPACITY)) {
				int c = tankTag.getInt(TagKeyDC.CAPACITY);
				capacity = c;
			}
		}
	}

	public FluidItemWrapperSided(@NotNull ItemStack cont, int cap) {
		this.container = cont;
		this.capacity = cap;
	}

	public FluidItemWrapperSided(@NotNull ItemStack cont, int cap, boolean b) {
		this(cont, cap);
		this.canHotFluid = b;
	}

	public FluidItemWrapperSided sidedTank() {
		this.isSided = true;
		return this;
	}

	@Override
	public @NotNull ItemStack getContainer() {
		return container;
	}

	protected void setFluid(int i, FluidStack fluid) {
		CompoundTag tag = container.getOrCreateTag();

		CompoundTag tankTag = new CompoundTag();
		fluid.writeToNBT(tankTag);
		tankTag.putInt(TagKeyDC.CAPACITY, capacity);
		container.getTag().put(TagKeyDC.getTankKey(i), tankTag);
	}

	@Override
	public int getTanks() {
		return isSided ? 2 : 1;
	}

	@Override
	public @NotNull FluidStack getFluidInTank(int i) {
		if (i > getTanks() || !isSided || i < 1)
			i = 1;
		FluidStack fluid = FluidStack.EMPTY;
		if (!container.hasTag())
			return fluid;

		CompoundTag tag = container.getTag();
		if (tag.contains(TagKeyDC.getTankKey(i), 10)) {
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
		int id = 1;
		if (container.getCount() != 1 || fill.isEmpty() || !isFluidValid(id, fill)) {
			return 0;
		}

		FluidStack target = getFluidInTank(id);
		if (target.isEmpty()) {
			int fillAmount = Math.min(capacity, fill.getAmount());
			if (action.execute()) {
				FluidStack filled = fill.copy();
				filled.setAmount(fillAmount);
				setFluid(id, filled);
			}
			return fillAmount;
		} else {
			if (DCFluidUtil.isSameFluid(target, fill)) {
				int fillAmount = Math.min(capacity - target.getAmount(), fill.getAmount());
				if (action.execute() && fillAmount > 0) {
					target.grow(fillAmount);
					setFluid(id, target);
				}
				return fillAmount;
			}
			return 0;
		}
	}

	@Override
	public @NotNull FluidStack drain(FluidStack drain, FluidAction action) {
		int id = isSided ? 2 : 1;
		FluidStack target = getFluidInTank(id);
		if (container.getCount() != 1 || drain.isEmpty() || !DCFluidUtil.isSameFluid(target, drain)) {
			return FluidStack.EMPTY;
		}

		return drain(drain.getAmount(), action);
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		int id = isSided ? 2 : 1;
		FluidStack target = getFluidInTank(id);
		if (container.getCount() != 1 || maxDrain <= 0 || target.isEmpty()) {
			return FluidStack.EMPTY;
		}

		final int drainAmount = Math.min(target.getAmount(), maxDrain);

		FluidStack ret = target.copy();
		ret.setAmount(drainAmount);

		if (action.execute()) {
			target.shrink(drainAmount);
			if (target.isEmpty()) {
				setContainerToEmpty(id);
			} else {
				setFluid(id, target);
			}
		}

		return ret;
	}

	protected void setContainerToEmpty(int id) {
		setFluid(id, FluidStack.EMPTY);
	}

	private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);

	@Override
	@NotNull
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
		return ForgeCapabilities.FLUID_HANDLER_ITEM.orEmpty(capability, holder);
	}

}
