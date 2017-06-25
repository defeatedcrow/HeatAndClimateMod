package defeatedcrow.hac.plugin.cofh;

import cofh.api.util.ThermalExpansionHelper;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.core.climate.recipe.ReactorRecipe;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DCPluginFluid;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCPluginCoFH {

	public static final DCPluginCoFH INSTANCE = new DCPluginCoFH();

	public static Fluid crude = null;
	public static Fluid coal = null;
	public static Fluid resin = null;
	public static Fluid tree = null;
	public static Fluid naphtha = null;
	public static Fluid refined = null;

	public static ItemStack tar = null;
	public static ItemStack rogin = null;

	public static void load() {
		loadFluid();
		loadFuels();
		loadRecipes();
		if (ModuleConfig.machine && ModuleConfig.machine_advanced) {
			loadDCRecipes();
		}
	}

	static void loadFluid() {
		crude = FluidRegistry.getFluid("crude_oil");
		if (crude != null) {
			DCPluginFluid.registerPotion(crude, MainInit.gravity);
			FluidDictionaryDC.registerFluidDic(crude, "crude");
		}
		coal = FluidRegistry.getFluid("coal");
		if (coal != null) {
			DCPluginFluid.registerPotion(coal, MainInit.gravity);
			FluidDictionaryDC.registerFluidDic(coal, "coal");
		}
		resin = FluidRegistry.getFluid("tree_oil");
		if (resin != null) {
			DCPluginFluid.registerPotion(resin, MobEffects.WEAKNESS);
			FluidDictionaryDC.registerFluidDic(resin, "resin");
		}
		tree = FluidRegistry.getFluid("tree_oil");
		if (tree != null) {
			DCPluginFluid.registerPotion(tree, MobEffects.RESISTANCE);
			FluidDictionaryDC.registerFluidDic(tree, "tree_oil");
		}
		naphtha = FluidRegistry.getFluid("fuel");
		if (naphtha != null) {
			DCPluginFluid.registerPotion(naphtha, MobEffects.HASTE);
			FluidDictionaryDC.registerFluidDic(naphtha, "naphtha");
		}
		refined = FluidRegistry.getFluid("refined_oil");
		if (refined != null) {
			DCPluginFluid.registerPotion(refined, MobEffects.RESISTANCE);
			FluidDictionaryDC.registerFluidDic(refined, "refined");
		}

		// other fluid
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("redstone"), MobEffects.JUMP_BOOST);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("glowstone"), MobEffects.GLOWING);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("ender"), MobEffects.NAUSEA);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("pyrotheum"), MobEffects.FIRE_RESISTANCE);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("cryotheum"), DCInit.prevFreeze);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("aerotheum"), MainInit.bird);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("petrotheum"), MainInit.heavyboots);
		DCPluginFluid.registerPotion(FluidRegistry.getFluid("mana"), MainInit.ocean);

		Item material = Item.REGISTRY.getObject(new ResourceLocation("thermalfoundation", "material"));
		if (material != null) {
			rogin = new ItemStack(material, 1, 832);
			tar = new ItemStack(material, 1, 833);
		}
	}

	static void loadDCRecipes() {
		if (tar != null && coal != null && crude != null) {
			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 0), null,
					new FluidStack(MachineInit.fuelOil, 200), null, DCHeatTier.KILN, 0,
					new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(coal, 200), null, new Object[] {}),
					DCHeatTier.KILN);

			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 7), null,
					new FluidStack(MachineInit.fuelOil, 200), null, DCHeatTier.KILN, 0,
					new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 200), null,
					new Object[] {
							tar
					}), DCHeatTier.KILN);

			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 0), null,
					new FluidStack(MachineInit.fuelOil, 400), null, DCHeatTier.KILN, 0,
					new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(crude, 200), null, new Object[] {}),
					DCHeatTier.KILN);
		}

		if (tree != null && resin != null && rogin != null) {
			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(rogin, null,
					new FluidStack(MachineInit.fuelOil, 400), null, DCHeatTier.KILN, 0,
					new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(resin, 200), null, new Object[] {}),
					DCHeatTier.KILN);

			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MachineInit.reagent, 1, 1), null,
					new FluidStack(MachineInit.fuelOil, 200), null, DCHeatTier.KILN, 0,
					new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 200), null,
					new Object[] {
							rogin
					}), DCHeatTier.KILN);
		}

		if (naphtha != null && refined != null) {
			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MainInit.miscDust, 1, 7), null,
					new FluidStack(MachineInit.fuelGas, 400), null, DCHeatTier.SMELTING, 0,
					new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(naphtha, 200),
					new FluidStack(FluidRegistry.WATER, 200), new Object[] {}), DCHeatTier.SMELTING);

			RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MainInit.miscDust, 1, 7), null,
					new FluidStack(MachineInit.fuelGas, 800), null, DCHeatTier.SMELTING, 0,
					new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(naphtha, 200),
					new FluidStack(MachineInit.hydrogen, 200), new Object[] {}), DCHeatTier.SMELTING);

			RecipeAPI.registerReactorRecipes.addRecipe(
					new ReactorRecipe(null, null, new FluidStack(MachineInit.fuelGas, 400), null, DCHeatTier.SMELTING,
							0, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(refined, 200),
							new FluidStack(FluidRegistry.WATER, 200), new Object[] {}),
					DCHeatTier.SMELTING);

			RecipeAPI.registerReactorRecipes.addRecipe(
					new ReactorRecipe(null, null, new FluidStack(MachineInit.fuelGas, 800), null, DCHeatTier.SMELTING,
							0, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(refined, 200),
							new FluidStack(MachineInit.hydrogen, 200), new Object[] {}),
					DCHeatTier.SMELTING);
		}

		RecipeAPI.registerReactorRecipes.addRecipe(new ReactorRecipe(new ItemStack(MachineInit.reagent, 2, 9), null,
				null, null, DCHeatTier.UHT, 0, null, null, null, new Object[] {
						"fuelCoke",
						"dustLime"
				}), DCHeatTier.UHT);
	}

	static void loadFuels() {
		ThermalExpansionHelper.addCompressionFuel("dcs.fuel_oil", 800);
		ThermalExpansionHelper.addCompressionFuel("dcs.fuel_gas", 1000);
		ThermalExpansionHelper.addCompressionFuel("dcs.seed_oil", 100);
		ThermalExpansionHelper.addCompressionFuel("dcs.black_liquor", 100);

		ThermalExpansionHelper.addCoolant("dcs.nitrogen", 1500);
	}

	static void loadRecipes() {
		// crusible
		Fluid creosote = FluidRegistry.getFluid("creosote");
		if (creosote != null) {
			ThermalExpansionHelper.addCrucibleRecipe(2000, new ItemStack(MachineInit.reagent, 1, 0),
					new FluidStack(creosote, 100));
		}

		// refinary
		// if (naphtha != null && tree != null && tar != null && rogin != null) {
		// ThermalExpansionHelper.addRefineryRecipe(5000, new FluidStack(MachineInit.fuelOil, 100),
		// new FluidStack(naphtha, 100), new ItemStack(MachineInit.reagent, 1, 0));
		//
		// ThermalExpansionHelper.addRefineryRecipe(5000, new FluidStack(FoodInit.blackLiquor, 100),
		// new FluidStack(tree, 100), new ItemStack(MachineInit.reagent, 1, 0));
		//
		// ThermalExpansionHelper.addRefineryRecipe(5000, new FluidStack(FoodInit.oil, 100),
		// new FluidStack(naphtha, 100), new ItemStack(MachineInit.reagent, 1, 1));
		// }
	}

}
