package defeatedcrow.hac.main.api;

import java.util.Map;

public interface IFluidFuelRegister {

	Map<String, Integer> getFuelMap();

	/**
	 * @param fluidName
	 *            registered name of fluid at FluidRegistry
	 * @param burnTime
	 *            burning time per mB
	 */
	void registerFuel(String fluidName, Integer burnTime);

	boolean isRegistered(String fluidName);

	int getBurningTime(String fluidName);

}
