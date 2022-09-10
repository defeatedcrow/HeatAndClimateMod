package defeatedcrow.hac.api;

import defeatedcrow.hac.api.climate.IBiomeClimateRegister;
import defeatedcrow.hac.api.climate.IClimateCalculator;
import defeatedcrow.hac.api.climate.IClimateHelper;
import defeatedcrow.hac.api.climate.IHeatBlockRegister;

public class ClimateAPI {

	private ClimateAPI() {}

	public static IBiomeClimateRegister registerBiome;
	public static IHeatBlockRegister registerBlock;
	public static IClimateCalculator calculator;
	public static IClimateHelper helper;
}
