package defeatedcrow.hac.main.block.fluid;

import defeatedcrow.hac.core.fluid.DCTank;
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
			for (String name : limits) {
				if (name != null && get.getFluid().getName().contains(name))
					return true;
			}
			return false;
		}
		return super.canFillTarget(get);
	}

}
