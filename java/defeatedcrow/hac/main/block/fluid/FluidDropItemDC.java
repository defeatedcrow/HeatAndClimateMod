package defeatedcrow.hac.main.block.fluid;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

/**
 * DropはFluidと紐付けられたアイテムだが、取り出せる液体は決まっている。
 */
public class FluidDropItemDC implements IFluidHandler, ICapabilityProvider {

	protected final ItemStack container;
	private final String contain;

	public FluidDropItemDC(ItemStack container, String name) {
		this.container = container;
		this.contain = name;
	}

	/* Fluid */

	@Nullable
	public FluidStack getFluid() {
		Fluid fluid = FluidRegistry.getFluid(contain);
		if (fluid != null) {
			return new FluidStack(fluid, 200);
		}
		return null;
	}

	protected void setFluid(FluidStack fluid) {
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new FluidTankProperties[] { new FluidTankProperties(getFluid(), 200) };
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if (resource == null || resource.amount < 200 || !resource.isFluidEqual(getFluid())) {
			return null;
		}
		return drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (maxDrain < 200) {
			return null;
		}

		FluidStack contained = getFluid();
		if (contained == null || !canDrainFluidType(contained)) {
			return null;
		}

		final int drainAmount = 200;

		FluidStack drained = contained.copy();
		drained.amount = drainAmount;

		if (doDrain) {
			setContainerToEmpty();
		}

		return drained;
	}

	public boolean canFillFluidType(FluidStack fluid) {
		return false;
	}

	public boolean canDrainFluidType(FluidStack fluid) {
		return true;
	}

	protected void setContainerToEmpty() {
		container.stackSize--;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) this : null;
	}

}
