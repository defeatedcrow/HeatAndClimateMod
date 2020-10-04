package defeatedcrow.hac.main.api;

import defeatedcrow.hac.main.api.brewing.IBrewingRecipeRegister;
import defeatedcrow.hac.main.api.brewing.IMicrobeRegister;
import defeatedcrow.hac.main.api.orevein.IVeinTableRegister;

public class MainAPIManager {

	private MainAPIManager() {}

	public static IFluidFuelRegister fuelRegister;
	public static IVeinTableRegister veinRegister;
	public static IHeatTreatmentRegister heatTreatmentRegister;
	public static IDCInfoDataRegister infoRegister;
	public static IMicrobeRegister microbeRegister;
	public static IBrewingRecipeRegister brewingRegister;

	public static boolean isLoaded = false;
}
