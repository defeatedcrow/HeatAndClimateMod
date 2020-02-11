package defeatedcrow.hac.main.block.fluid;

import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCLimitedTank extends DCTank {

	private final String[] limits;

	public DCLimitedTank(int cap, String... strings) {
		super(cap);
		limits = strings;
	}

	@Override
	public boolean canFillTarget(FluidStack get) {
		if (get != null && get.getFluid() != null && limits != null && limits.length > 0) {
			for (String check : limits) {
				if (check == null)
					continue;
				Fluid f = FluidRegistry.getFluid(check);
				if (f != null && f == get.getFluid()) {
					return true;
				}
				if (FluidDictionaryDC.matchFluidName(get.getFluid(), check)) {
					return true;
				}
			}
			return false;
		}
		return super.canFillTarget(get);
	}

}
