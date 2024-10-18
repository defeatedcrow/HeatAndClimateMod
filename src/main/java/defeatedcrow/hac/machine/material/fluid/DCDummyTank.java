package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class DCDummyTank extends DCTank {

	public DCDummyTank() {
		super(0);
	}

	@Override
	public void readFromNBT(CompoundTag tag) {}

	@Override
	public CompoundTag writeToNBT(CompoundTag tag) {
		getFluid().writeToNBT(tag);
		return tag;
	}

	@Override
	public @NotNull FluidStack getFluid() {
		return FluidStack.EMPTY;
	}

	@Override
	public void setFluid(FluidStack f) {}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean isFull() {
		return true;
	}

	@Override
	public Fluid getFluidType() {
		return fluid.getFluid();
	}

	@Override
	public String getFluidName() {
		return "EMPTY";
	}

	@Override
	public int getFluidAmount() {
		return 0;
	}

	@Override
	public int getCapacity() {
		return 0;
	}

	@Override
	public int getSpace() {
		return 0;
	}

	@Override
	public boolean isFluidValid(FluidStack stack) {
		return false;
	}

	@Override
	public int fill(FluidStack get, FluidAction action) {
		return 0;
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		return FluidStack.EMPTY;

	}

	@Override
	public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
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
		return isFluidValid(stack) && stack.getAmount() > 0 && tank <= getTanks();
	}

}
