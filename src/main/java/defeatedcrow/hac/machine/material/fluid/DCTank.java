package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class DCTank implements IFluidHandler, IFluidTank {

	protected FluidStack fluid = FluidStack.EMPTY;
	protected final int capacity;

	public DCTank(int cap) {
		capacity = cap;
	}

	public DCTank readFromNBT(CompoundTag tag) {
		DCTank ret = this;
		if (tag.contains(TagKeyDC.CAPACITY)) {
			int c = tag.getInt(TagKeyDC.CAPACITY);
			if (c != capacity) {
				ret = new DCTank(c);
			}
		}
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag);
		ret.setFluid(fluid);
		return ret;
	}

	public CompoundTag writeToNBT(CompoundTag tag) {
		fluid.writeToNBT(tag);
		tag.putInt(TagKeyDC.CAPACITY, capacity);
		return tag;
	}

	@Override
	public @NotNull FluidStack getFluid() {
		return fluid;
	}

	public void setFluid(FluidStack f) {
		fluid = f;
	}

	public boolean isFull() {
		return (fluid != null) && (fluid.getAmount() == capacity);
	}

	public Fluid getFluidType() {
		return fluid.getFluid();
	}

	public String getFluidName() {
		return fluid.isEmpty() ? "EMPTY" : fluid.getTranslationKey();
	}

	@Override
	public int getFluidAmount() {
		return fluid.getAmount();
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	public int getSpace() {
		return Math.max(0, capacity - fluid.getAmount());
	}

	@Override
	public boolean isFluidValid(FluidStack stack) {
		return fluid.isEmpty() || DCFluidUtil.isSameFluid(stack, fluid);
	}

	@Override
	public int fill(FluidStack get, FluidAction action) {
		if (!get.isEmpty() && isFluidValid(get)) {
			int vac = capacity - this.getFluidAmount();
			int ret = Math.min(vac, get.getAmount());
			if (!action.simulate()) {
				if (fluid.isEmpty()) {
					setFluid(get);
				} else {
					CompoundTag tag = DCFluidUtil.combineTag(fluid, get);
					if (tag != null) {
						fluid.setTag(tag);
					}
					fluid.grow(ret);
				}
				DCFluidUtil.removeHead(fluid);
			}
			return ret;
		}
		return 0;
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		if (!fluid.isEmpty()) {
			int ret = Math.min(maxDrain, fluid.getAmount());
			if (ret > 0) {
				FluidStack f = new FluidStack(fluid.getFluid(), ret);
				if (!action.simulate()) {
					fluid.shrink(ret);
					if (fluid.getAmount() <= 0) {
						setFluid(FluidStack.EMPTY);
					}
				}
				return DCFluidUtil.removeHead(fluid);
			}
		}
		return FluidStack.EMPTY;

	}

	@Override
	public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
		if (DCFluidUtil.isSameFluid(fluid, resource)) {
			return drain(resource.getAmount(), action);
		}
		return FluidStack.EMPTY;
	}

	/* head */

	@Override
	public int getTanks() {
		return 1;
	}

	@NotNull
	@Override
	public FluidStack getFluidInTank(int tank) {
		return getFluid();
	}

	@Override
	public int getTankCapacity(int tank) {
		return getCapacity();
	}

	@Override
	public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
		return isFluidValid(stack) && stack.getAmount() > 0 && tank >= stack.getAmount();
	}

}
