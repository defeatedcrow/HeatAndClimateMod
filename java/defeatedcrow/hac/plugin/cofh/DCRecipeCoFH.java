package defeatedcrow.hac.plugin.cofh;

import cofh.api.util.ThermalExpansionHelper;
import cofh.thermalexpansion.util.managers.CoolantManager;
import cofh.thermalexpansion.util.managers.dynamo.CompressionManager;
import cofh.thermalexpansion.util.managers.machine.RefineryManager;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCRecipeCoFH {

	static void loadFuels() {
		CompressionManager.addFuel(MachineInit.fuelOil, 800000);
		CompressionManager.addFuel(MachineInit.fuelGas, 1000000);
		CompressionManager.addFuel(FoodInit.blackLiquor, 100000);
		CompressionManager.addFuel(FoodInit.oil, 200000);

		CoolantManager.addCoolant(MachineInit.nitrogen, 1500000, 3);
	}

	static void loadRecipes() {
		// crusible
		Fluid creosote = FluidRegistry.getFluid("creosote");
		if (creosote != null) {
			ThermalExpansionHelper.addCrucibleRecipe(2000, new ItemStack(MachineInit.reagent, 1, 0),
					new FluidStack(creosote, 100));
		}

		// refinary
		if (DCPluginCoFH.naphtha != null && DCPluginCoFH.tree != null && DCPluginCoFH.tar != null
				&& DCPluginCoFH.rogin != null) {
			RefineryManager.addRecipe(5000, new FluidStack(MachineInit.fuelOil, 100),
					new FluidStack(DCPluginCoFH.naphtha, 100), DCPluginCoFH.tar);

			RefineryManager.addRecipe(5000, new FluidStack(FoodInit.blackLiquor, 100),
					new FluidStack(DCPluginCoFH.tree, 100), DCPluginCoFH.tar);

			RefineryManager.addRecipe(5000, new FluidStack(FoodInit.oil, 100),
					new FluidStack(DCPluginCoFH.naphtha, 100), new ItemStack(MachineInit.reagent, 1, 1));
		}
	}
}
