package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCPluginBuildcraft {

	public static final DCPluginBuildcraft INSTANCE = new DCPluginBuildcraft();

	private DCPluginBuildcraft() {}

	public static void loadPost() {

		Fluid black = FluidRegistry.getFluid("oil");
		Fluid gas = FluidRegistry.getFluid("fuel_gaseous");
		Fluid fuel = FluidRegistry.getFluid("fuel_light");
		Fluid dense = FluidRegistry.getFluid("fuel_dense");
		Fluid mixed = FluidRegistry.getFluid("fuel_mixed_heavy");
		Fluid heavy = FluidRegistry.getFluid("oil_heavy");
		Fluid denseoil = FluidRegistry.getFluid("oil_dense");
		Fluid dist = FluidRegistry.getFluid("oil_distilled");
		Fluid residue = FluidRegistry.getFluid("oil_residue");

		Item wax = Item.REGISTRY.getObject(new ResourceLocation("buildcrafttransport:waterproof"));
		if (wax != null) {
			DCRecipe.addShapelessNBTRecipe(RecipeResourcesMain.MAIN.getRecipeName(), new ItemStack(wax, 1,
					0), new Object[] { new ItemStack(MachineInit.reagent, 1, 0) });
		}

		if (black == null || gas == null || fuel == null || dense == null || mixed == null || heavy == null || denseoil == null || dist == null || residue == null)
			return;

		if (ModuleConfig.machine && ModuleConfig.machine_advanced) {

			ItemStack tar = new ItemStack(MachineInit.reagent, 1, 0);
			ItemStack coke = new ItemStack(MachineInit.reagent, 1, 13);

			// coke
			RecipeAPI.registerReactorRecipes
					.addRecipe(coke, tar, 1.0F, null, null, DCHeatTier.KILN, (ItemStack) null, new FluidStack(residue,
							1000), null, new Object[] {});

			// Ni 水蒸気改質
			RecipeAPI.registerReactorRecipes.addRecipe(tar, null, 0F, new FluidStack(dist,
					500), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(residue,
							500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});

			// Pt 炭化水素ガス
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), null, 0F, new FluidStack(
					MainInit.fuelGas, 1000), new FluidStack(dense, 100), DCHeatTier.SMELTING, new ItemStack(
							MachineInit.catalyst, 1, 3), new FluidStack(dist, 500), new FluidStack(FluidRegistry.WATER,
									500), new Object[] {});

			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), null, 0F, new FluidStack(
					MainInit.fuelGas, 1000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1,
							3), new FluidStack(fuel, 500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});

			// Pt 水素化
			RecipeAPI.registerReactorRecipes.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), null, 0F, new FluidStack(
					MainInit.fuelGas, 2000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1,
							3), new FluidStack(fuel, 500), new FluidStack(MainInit.hydrogen, 500), new Object[] {});

			// Ni 水素
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0F, new FluidStack(MainInit.hydrogen,
					1000), null, DCHeatTier.SMELTING, new ItemStack(MachineInit.catalyst, 1, 0), new FluidStack(gas,
							500), new FluidStack(FluidRegistry.WATER, 500), new Object[] {});
		}

	}

	public static void loadInit() {
		// FacadeAPI.disableBlock(FoodInit.blackLiquorBlock);
		// FacadeAPI.disableBlock(FoodInit.blackTeaBlock);
		// FacadeAPI.disableBlock(FoodInit.coffeeBlock);
		// FacadeAPI.disableBlock(FoodInit.creamBlock);
		// FacadeAPI.disableBlock(FoodInit.greenTeaBlock);
		// FacadeAPI.disableBlock(FoodInit.hotSpringBlock);
		// FacadeAPI.disableBlock(FoodInit.lemonBlock);
		// FacadeAPI.disableBlock(FoodInit.oilBlock);
		// FacadeAPI.disableBlock(FoodInit.stockBlock);
		// FacadeAPI.disableBlock(FoodInit.tomatoBlock);
		// FacadeAPI.disableBlock(FoodInit.mazaiBlock);
		// FacadeAPI.disableBlock(MachineInit.ethanolBlock);
		// FacadeAPI.disableBlock(MachineInit.ammoniaBlock);
		// FacadeAPI.disableBlock(MachineInit.fuelGasBlock);
		// FacadeAPI.disableBlock(MachineInit.fuelOilBlock);
		// FacadeAPI.disableBlock(MachineInit.hydrogenBlock);
		// FacadeAPI.disableBlock(MachineInit.nitricAcidBlock);
		// FacadeAPI.disableBlock(MachineInit.nitrogenBlock);
		// FacadeAPI.disableBlock(MachineInit.sulfuricAcidBlock);
	}
}
