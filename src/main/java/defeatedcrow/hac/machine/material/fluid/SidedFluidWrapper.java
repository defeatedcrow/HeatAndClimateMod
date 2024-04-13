package defeatedcrow.hac.machine.material.fluid;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class SidedFluidWrapper implements IFluidHandler, ICapabilityProvider {

	private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> this);

	final DCTank inputTank;
	final DCTank outputTank;

	public SidedFluidWrapper(DCTank inTank, DCTank outTank) {
		inputTank = inTank;
		outputTank = outTank;
	}

	@Override
	public int fill(FluidStack get, FluidAction action) {
		return inputTank.fill(get, action);
	}

	@Override
	public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
		return outputTank.drain(maxDrain, action);
	}

	@Override
	public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
		return outputTank.drain(resource, action);
	}

	/* head */

	@Override
	public int getTanks() {
		return 2;
	}

	@NotNull
	@Override
	public FluidStack getFluidInTank(int tank) {
		return tank == 0 ? inputTank.getFluidInTank(0) : outputTank.getFluidInTank(0);
	}

	@Override
	public int getTankCapacity(int tank) {
		return tank == 0 ? inputTank.getCapacity() : outputTank.getCapacity();
	}

	@Override
	public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
		return tank == 0 ? inputTank.isFluidValid(stack) : outputTank.isFluidValid(stack);
	}

	@Override
	@NotNull
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
		return ForgeCapabilities.FLUID_HANDLER.orEmpty(capability, holder);
	}

}
