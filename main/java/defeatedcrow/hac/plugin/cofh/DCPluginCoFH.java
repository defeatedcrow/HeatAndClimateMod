package defeatedcrow.hac.plugin.cofh;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DCPluginFluid;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;

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

		if (ModuleConfig.machine && ModuleConfig.machine_advanced && ModuleConfig.r_reactor) {
			loadDCRecipes();
		}

		if (Loader.isModLoaded("thermalexpansion")) {
			DCRecipeCoFH.loadFuels();
			DCRecipeCoFH.loadRecipes();
		}
	}

	static void loadFluid() {
		crude = FluidRegistry.getFluid("crude_oil");
		if (crude != null) {
			DCPluginFluid.registerPotion(crude, MainInit.gravity);
		}
		coal = FluidRegistry.getFluid("coal");
		if (coal != null) {
			DCPluginFluid.registerPotion(coal, MainInit.gravity);
			MainAPIManager.fuelRegister.registerFuel("coal", 20);
		}
		resin = FluidRegistry.getFluid("resin");
		if (resin != null) {
			DCPluginFluid.registerPotion(resin, MobEffects.WEAKNESS);
		}
		tree = FluidRegistry.getFluid("tree_oil");
		if (tree != null) {
			DCPluginFluid.registerPotion(tree, MobEffects.RESISTANCE);
			MainAPIManager.fuelRegister.registerFuel("tree_oil", 30);
		}
		naphtha = FluidRegistry.getFluid("refined_oil");
		if (naphtha != null) {
			DCPluginFluid.registerPotion(naphtha, MobEffects.HASTE);
			MainAPIManager.fuelRegister.registerFuel("refined_oil", 100);
		}
		refined = FluidRegistry.getFluid("refined_fuel");
		if (refined != null) {
			DCPluginFluid.registerPotion(refined, MobEffects.HASTE);
			MainAPIManager.fuelRegister.registerFuel("refined_fuel", 150);
		}
		Fluid refined_bio = FluidRegistry.getFluid("refined_biofuel");
		if (refined_bio != null) {
			DCPluginFluid.registerPotion(refined_bio, MobEffects.SPEED);
			MainAPIManager.fuelRegister.registerFuel("refined_biofuel", 120);
		}
		Fluid biocrude = FluidRegistry.getFluid("biocrude");
		if (biocrude != null) {
			DCPluginFluid.registerPotion(biocrude, MobEffects.SLOWNESS);
			MainAPIManager.fuelRegister.registerFuel("biocrude", 60);
		}

		Fluid rs = FluidRegistry.getFluid("redstone");
		if (rs != null) {
			DCPluginFluid.registerPotion(rs, MobEffects.JUMP_BOOST);
		}

		Fluid gs = FluidRegistry.getFluid("glowstone");
		if (gs != null) {
			DCPluginFluid.registerPotion(gs, MobEffects.GLOWING);
		}

		// other fluid
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
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 0), new ItemStack(
					MachineInit.reagent, 1, 14), 0.5F, new FluidStack(MainInit.fuelOil,
							500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(coal,
									500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					7), null, 0F, new FluidStack(MainInit.fuelOil, 200), null, DCHeatTier.KILN, new ItemStack(
							MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 500), null, new Object[] {
									tar
			});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1, 0), new ItemStack(
					MachineInit.reagent, 1, 14), 1F, new FluidStack(MainInit.fuelOil,
							500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(
									crude, 1000), null, new Object[] {});
		}

		if (tree != null && resin != null && rogin != null) {
			RecipeAPI.registerReactorRecipes.addRecipe(rogin, null, 0F, new FluidStack(MainInit.fuelOil,
					500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(resin,
							1000), null, new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MachineInit.reagent, 1,
					1), null, 0, new FluidStack(MainInit.fuelOil, 100), null, DCHeatTier.KILN, new ItemStack(
							MachineInit.catalyst, 1, 0), new FluidStack(FluidRegistry.WATER, 200), null, new Object[] {
									rogin
			});
		}

		if (naphtha != null && refined != null) {
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), null, 0, new FluidStack(
					MainInit.fuelGas, 1000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1,
							3), new FluidStack(naphtha, 500), new FluidStack(FluidRegistry.WATER,
									500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), null, 0, new FluidStack(
					MainInit.fuelGas, 2000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1,
							3), new FluidStack(naphtha, 500), new FluidStack(MainInit.hydrogen, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.fuelGas,
					1000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(refined,
							500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0, new FluidStack(MainInit.fuelGas,
					2000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1, 3), new FluidStack(refined,
							500), new FluidStack(MainInit.hydrogen, 500), new Object[] {});
		}

	}

}
