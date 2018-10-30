package defeatedcrow.hac.plugin;

import crazypants.enderio.base.fluid.FluidFuelRegister;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class DCPluginEIO {
	public static final DCPluginEIO INSTANCE = new DCPluginEIO();

	private DCPluginEIO() {}

	public static void init() {
		// fluid
		Fluid hootch = FluidRegistry.getFluid("hootch");
		if (hootch != null) {
			DCPluginFluid.registerPotion(hootch, MobEffects.HASTE);
			MainAPIManager.fuelRegister.registerFuel("hootch", 40);
		}

		Fluid fire = FluidRegistry.getFluid("fire_water");
		if (fire != null) {
			DCPluginFluid.registerPotion(fire, MobEffects.STRENGTH);
			MainAPIManager.fuelRegister.registerFuel("fire_water", 100);
		}

		Fluid rocket = FluidRegistry.getFluid("rocket_fuel");
		if (rocket != null) {
			DCPluginFluid.registerPotion(rocket, MobEffects.STRENGTH);
			MainAPIManager.fuelRegister.registerFuel("rocket_fuel", 150);
		}

		DCPluginFluid.registerPotion(FluidRegistry.getFluid("nutrient_distillation"), MobEffects.HEALTH_BOOST);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("ender_distillation"), MobEffects.INVISIBILITY);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("vapor_of_levity"), MobEffects.JUMP_BOOST);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("xpjuice"), MobEffects.LUCK);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("liquid_sunshine"), MobEffects.FIRE_RESISTANCE);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("cloud_seed"), MobEffects.WATER_BREATHING);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("cloud_seed_concentrated"), MainInit.ocean);

		// fuel
		FluidFuelRegister.instance.addFuel(MainInit.blackLiquor, 10, 20000);
		FluidFuelRegister.instance.addFuel(MainInit.oil, 40, 10000);
		FluidFuelRegister.instance.addFuel(MainInit.hydrogen, 80, 4000);
		FluidFuelRegister.instance.addFuel(MainInit.fuelOil, 100, 12000);
		FluidFuelRegister.instance.addFuel(MainInit.fuelGas, 150, 8000);
		FluidFuelRegister.instance.addFuel(MainInit.ethanol, 60, 8000);
		FluidFuelRegister.instance.addCoolant(MainInit.ammonia, 0.006F);
		FluidFuelRegister.instance.addCoolant(MainInit.nitrogen, 0.015F);

	}

}
