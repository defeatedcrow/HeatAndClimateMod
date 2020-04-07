package defeatedcrow.hac.plugin.cofh;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
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

		if (ModuleConfig.r_reactor) {
			loadDCRecipes();
		}

		if (Loader.isModLoaded("thermalexpansion")) {
			DCRecipeCoFH.loadFuels();
			DCRecipeCoFH.loadRecipes();
		}
	}

	static void loadFluid() {

		crude = FluidRegistry.getFluid("crude_oil");
		coal = FluidRegistry.getFluid("coal");
		resin = FluidRegistry.getFluid("resin");
		tree = FluidRegistry.getFluid("tree_oil");
		naphtha = FluidRegistry.getFluid("refined_oil");
		refined = FluidRegistry.getFluid("refined_fuel");

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
								tar });

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
								rogin });
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
