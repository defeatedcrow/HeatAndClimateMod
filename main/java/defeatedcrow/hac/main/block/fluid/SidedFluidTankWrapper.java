package defeatedcrow.hac.main.block.fluid;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.fluid.DCTank;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class SidedFluidTankWrapper implements IFluidHandler, ICapabilityProvider {

	protected final DCTank tank;
	private final boolean drainOnly;
	private final boolean fillOnly;

	protected EnumSide side;

	public SidedFluidTankWrapper(DCTank t1, boolean drain, boolean fill) {
		tank = t1;
		drainOnly = drain;
		fillOnly = fill;
	}

	public SidedFluidTankWrapper setSide(EnumSide s) {
		side = s;
		return this;
	}

	public DCTank getTank() {
		if (tank != null)
			return tank;
		return null;
	}

	/* Fluid */

	@Nullable
	public FluidStack getFluid() {
		if (getTank() != null && getTank().getFluid() != null)
			return getTank().getFluid();
		return null;
	}

	protected void setFluid(FluidStack fluid) {}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		if (getTank() == null)
			return new FluidTankProperties[] {};
		return new FluidTankProperties[] { new FluidTankProperties(getTank().getFluid(), getTank().getCapacity()) };
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (drainOnly || resource == null)
			return 0;
		return getTank().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if (getTank() == null || fillOnly || resource == null)
			return null;
		return drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (getTank() == null || fillOnly || maxDrain <= 0)
			return null;
		return getTank().drain(maxDrain, doDrain);
	}

	public boolean canFillFluidType(FluidStack fluid) {
		return true;
	}

	public boolean canDrainFluidType(FluidStack fluid) {
		return true;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) this : null;
	}
}
