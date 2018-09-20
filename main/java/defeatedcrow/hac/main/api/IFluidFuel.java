package defeatedcrow.hac.main.api;

import net.minecraftforge.fluids.Fluid;

public interface IFluidFuel {

	Fluid getFluid();

	int getBurnTime();

	boolean content(Fluid f);

}
