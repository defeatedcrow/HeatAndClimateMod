package defeatedcrow.hac.plugin;

import com.enderio.core.common.util.NNList;
import com.enderio.core.common.util.stackable.Things;

import crazypants.enderio.base.fluid.FluidFuelRegister;
import crazypants.enderio.base.paint.PaintSourceValidator;
import crazypants.enderio.base.recipe.IRecipeInput;
import crazypants.enderio.base.recipe.ThingsRecipeInput;
import crazypants.enderio.base.recipe.alloysmelter.AlloyRecipeManager;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
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
			FluidDictionaryDC.registerFluidDic(hootch, "ethanol");
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

		// blacklist
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.oreNew, 1, 32767));
		PaintSourceValidator.instance.addToBlacklist(new ItemStack(MainInit.layerNew, 1, 32767));
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

		// recipe
		// ores
		NNList<IRecipeInput> list1 = new NNList();
		list1.add(new ThingsRecipeInput(new Things("ingotCopper")).setCount(3));
		list1.add(new ThingsRecipeInput(new Things("ingotZinc")));
		AlloyRecipeManager.getInstance().addRecipe(list1, new ItemStack(MainInit.oreIngot, 4, 4), 5000, 0.1F);

		NNList<IRecipeInput> list7 = new NNList();
		list7.add(new ThingsRecipeInput(new Things("dustCopper")).setCount(3));
		list7.add(new ThingsRecipeInput(new Things("dustZinc")));
		AlloyRecipeManager.getInstance().addRecipe(list7, new ItemStack(MainInit.oreIngot, 4, 4), 4000, 0.1F);

		NNList<IRecipeInput> list2 = new NNList();
		list2.add(new ThingsRecipeInput(new Things("ingotCopper")).setCount(3));
		list2.add(new ThingsRecipeInput(new Things("ingotNickel")));
		list2.add(new ThingsRecipeInput(new Things("ingotZinc")));
		AlloyRecipeManager.getInstance().addRecipe(list2, new ItemStack(MainInit.oreIngot, 3, 6), 22000, 0.1F);

		NNList<IRecipeInput> list8 = new NNList();
		list8.add(new ThingsRecipeInput(new Things("dustCopper")).setCount(3));
		list8.add(new ThingsRecipeInput(new Things("dustNickel")));
		list8.add(new ThingsRecipeInput(new Things("dustZinc")));
		AlloyRecipeManager.getInstance().addRecipe(list8, new ItemStack(MainInit.oreIngot, 3, 6), 20000, 0.1F);

		NNList<IRecipeInput> list3 = new NNList();
		list3.add(new ThingsRecipeInput(new Things("ingotIron")).setCount(2));
		list3.add(new ThingsRecipeInput(new Things("dustChromium")));
		list3.add(new ThingsRecipeInput(new Things("ingotNickel")));
		AlloyRecipeManager.getInstance().addRecipe(list3, new ItemStack(MainInit.oreIngot, 2, 10), 45000, 0.1F);

		NNList<IRecipeInput> list9 = new NNList();
		list9.add(new ThingsRecipeInput(new Things("dustIron")).setCount(2));
		list9.add(new ThingsRecipeInput(new Things("dustChromium")));
		list9.add(new ThingsRecipeInput(new Things("dustNickel")));
		AlloyRecipeManager.getInstance().addRecipe(list9, new ItemStack(MainInit.oreIngot, 2, 10), 40000, 0.1F);

		// gems
		NNList<IRecipeInput> list4 = new NNList();
		list4.add(new ThingsRecipeInput(new Things("dustAluminum")).setCount(2));
		list4.add(new ThingsRecipeInput(new Things("dustChromium")));
		list4.add(new ThingsRecipeInput(new Things("dustIron")));
		AlloyRecipeManager.getInstance().addRecipe(list4, new ItemStack(MainInit.gems, 1, 4), 20000, 0.1F);

		if (ModuleConfig.machine) {
			// reagent
			NNList<IRecipeInput> list5 = new NNList();
			list5.add(new ThingsRecipeInput(new Things("dustApatite")).setCount(2));
			list5.add(new ThingsRecipeInput(new Things("sand")));
			list5.add(new ThingsRecipeInput(new Things("gemCoal")));
			AlloyRecipeManager.getInstance().addRecipe(list5, new ItemStack(MachineInit.reagent, 1, 10), 8000, 0.1F);
		}
	}

}
