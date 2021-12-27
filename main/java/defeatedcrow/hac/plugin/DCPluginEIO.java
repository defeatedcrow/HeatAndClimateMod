package defeatedcrow.hac.plugin;

import crazypants.enderio.base.fluid.FluidFuelRegister;
import crazypants.enderio.base.paint.PaintSourceValidator;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.recipes.device.RegisterBrewingDC;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCPluginEIO {
	public static final DCPluginEIO INSTANCE = new DCPluginEIO();

	private DCPluginEIO() {}

	public static void init() {
		// fluid

		DCPluginFluid.registerPotion("nutrient_distillation", MobEffects.HEALTH_BOOST);
		DCPluginFluid.registerPotion("ender_distillation", MobEffects.INVISIBILITY);
		DCPluginFluid.registerPotion("vapor_of_levity", MobEffects.JUMP_BOOST);
		DCPluginFluid.registerPotion("xpjuice", MobEffects.LUCK);
		DCPluginFluid.registerPotion("liquid_sunshine", MobEffects.FIRE_RESISTANCE);
		DCPluginFluid.registerPotion("cloud_seed", MobEffects.WATER_BREATHING);
		DCPluginFluid.registerPotion("cloud_seed_concentrated", MainInit.ocean);

		// fuel
		FluidFuelRegister.instance.addFuel(MainInit.blackLiquor, 10, 20000);
		FluidFuelRegister.instance.addFuel(MainInit.oil, 40, 10000);
		FluidFuelRegister.instance.addFuel(MainInit.hydrogen, 80, 4000);
		FluidFuelRegister.instance.addFuel(MainInit.fuelOil, 100, 12000);
		FluidFuelRegister.instance.addFuel(MainInit.fuelGas, 150, 8000);
		FluidFuelRegister.instance.addFuel(MainInit.ethanol, 60, 8000);
		FluidFuelRegister.instance.addCoolant(MainInit.ammonia, 0.006F);
		FluidFuelRegister.instance.addCoolant(MainInit.nitrogen, 0.015F);

		if (ModuleConfig.food && ModuleConfig.food_advanced) {
			Fluid f = FluidRegistry.getFluid("hootch");
			if (f != null)
				RegisterBrewingDC.aging(new FluidStack(FoodInit.whisky, 900), new FluidStack(f, 1000), 30);
		}

		// blacklist
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.thermometer, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.windvane, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.stevenson_screen, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.bellow, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.chamber, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.shitirin, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.lampCarbide, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.lampGas, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.lightOrb, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.markingPanel, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.realtimeClock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.realtimeClock_L, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.sinkChest, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.sinkMetal, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.stevenson_screen, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.ammoniaBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.blackLiquorBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.blackTeaBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.blackLiquorBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.blackTeaBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.coffeeBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.creamBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.ethanolBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.fuelGasBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.fuelOilBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.greenTeaBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.hotSpringBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.hydrogenBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.lemonBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.mazaiBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.milkBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.nitricAcidBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.nitrogenBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.oilBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.soyMilkBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.sulfuricAcidBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.tomatoBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.stockBlock, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 0));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 1));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 2));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 3));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 4));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 5));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 6));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 7));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 8));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 9));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 10));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 11));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 12));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 13));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 14));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 15));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 16));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 17));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 18));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 19));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 20));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(FoodInit.paperPack, 1, 21));
	}

}
