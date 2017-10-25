package defeatedcrow.hac.plugin.cofh;

import cofh.api.util.ThermalExpansionHelper;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCRecipeCoFH {

	static void loadFuels() {
		ThermalExpansionHelper.addCompressionFuel(MachineInit.fuelOil.getName(), 800000);
		ThermalExpansionHelper.addCompressionFuel(MachineInit.fuelGas.getName(), 1000000);
		ThermalExpansionHelper.addCompressionFuel(FoodInit.blackLiquor.getName(), 100000);
		ThermalExpansionHelper.addCompressionFuel(FoodInit.oil.getName(), 200000);

		// CoolantManager.addCoolant(MachineInit.nitrogen, 1500000, 3);
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
			ThermalExpansionHelper.addRefineryRecipe(5000, new FluidStack(MachineInit.fuelOil, 100),
					new FluidStack(DCPluginCoFH.naphtha, 100), DCPluginCoFH.tar);

			ThermalExpansionHelper.addRefineryRecipe(5000, new FluidStack(FoodInit.blackLiquor, 100),
					new FluidStack(DCPluginCoFH.tree, 100), DCPluginCoFH.tar);

			ThermalExpansionHelper.addRefineryRecipe(5000, new FluidStack(FoodInit.oil, 100),
					new FluidStack(DCPluginCoFH.naphtha, 50), new ItemStack(MachineInit.reagent, 1, 1));
		}
	}
}
