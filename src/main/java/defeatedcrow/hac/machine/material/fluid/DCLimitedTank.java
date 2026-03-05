package defeatedcrow.hac.machine.material.fluid;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * Limited Fluid Only
 */
public class DCLimitedTank extends DCTank {

	private final Fluid limit;

	public DCLimitedTank(Fluid fluid, int cap) {
		super(cap);
		limit = fluid;
	}

	@Override
	public boolean isFluidValid(FluidStack stack) {
		return fluid.isEmpty() || (!stack.isEmpty() && stack.getFluid() == limit);
	}

}
