package defeatedcrow.hac.main.api;

import defeatedcrow.hac.main.api.orevein.IVeinTableRegister;

public class MainAPIManager {

	private MainAPIManager() {}

	public static IFluidFuelRegister fuelRegister;
	public static IVeinTableRegister veinRegister;

	public static boolean isLoaded = false;
}
