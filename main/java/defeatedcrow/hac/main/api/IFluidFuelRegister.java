package defeatedcrow.hac.main.api;

import java.util.List;

import net.minecraftforge.fluids.Fluid;

public interface IFluidFuelRegister {

	List<IFluidFuel> getFuelList();

	/**
	 * @param fluidName
	 *        registered name of fluid at FluidRegistry
	 * @param burnTime
	 *        burning time per mB
	 */
	void registerFuel(String fluidName, Integer burnTime);

	void registerFuel(Fluid fluid, Integer burnTime);

	boolean isRegistered(Fluid fluid);

	int getBurningTime(Fluid fluid);

	void removeFuel(Fluid fluid);

}
