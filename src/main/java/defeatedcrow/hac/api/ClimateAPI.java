package defeatedcrow.hac.api;

import defeatedcrow.hac.api.climate.IBiomeClimateRegister;
import defeatedcrow.hac.api.climate.IClimateCalculator;
import defeatedcrow.hac.api.climate.IClimateHelper;
import defeatedcrow.hac.api.climate.IHeatBlockRegister;
import defeatedcrow.hac.api.damage.IArmorItemRegister;
import defeatedcrow.hac.api.damage.IArmorMaterialRegister;
import defeatedcrow.hac.api.damage.IMobHeatResistance;

public class ClimateAPI {

	private ClimateAPI() {}

	public static IBiomeClimateRegister registerBiome;
	public static IHeatBlockRegister registerBlock;
	public static IClimateCalculator calculator;
	public static IClimateHelper helper;

	public static IMobHeatResistance registerMob;
	public static IArmorItemRegister registerArmor;
	public static IArmorMaterialRegister registerMaterial;

}
