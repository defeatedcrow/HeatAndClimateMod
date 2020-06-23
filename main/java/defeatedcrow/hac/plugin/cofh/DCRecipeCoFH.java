package defeatedcrow.hac.plugin.cofh;

import cofh.api.util.ThermalExpansionHelper;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DCRecipeCoFH {

	static void loadFuels() {
		addCompressionFuel(MainInit.fuelOil.getName(), 800000);
		addCompressionFuel(MainInit.fuelGas.getName(), 1000000);
		addCompressionFuel(MainInit.ethanol.getName(), 400000);
		addCompressionFuel(MainInit.blackLiquor.getName(), 100000);
		addCompressionFuel(MainInit.oil.getName(), 200000);
		addCompressionFuel(MainInit.hydrogen.getName(), 400000);
		addCoolant(MainInit.ammonia.getName(), 1000000, 20);
		addCoolant(MainInit.nitrogen.getName(), 1500000, 60);
	}

	static void loadRecipes() {
		// crusible
		Fluid creosote = FluidRegistry.getFluid("creosote");
		if (creosote != null) {
			addCrucibleRecipe(2000, new ItemStack(MachineInit.reagent, 1, 0), new FluidStack(creosote, 100));
		}

		// refinary
		if (DCPluginCoFH.naphtha != null && DCPluginCoFH.tree != null && DCPluginCoFH.tar != null && DCPluginCoFH.rogin != null) {
			addRefineryRecipe(5000, new FluidStack(MainInit.fuelOil, 200), new FluidStack(DCPluginCoFH.naphtha,
					100), DCPluginCoFH.tar, 50);

			addRefineryRecipe(5000, new FluidStack(MainInit.blackLiquor, 200), new FluidStack(DCPluginCoFH.tree,
					100), DCPluginCoFH.tar, 50);

			addRefineryRecipe(5000, new FluidStack(MainInit.oil, 200), new FluidStack(DCPluginCoFH.naphtha,
					50), new ItemStack(MachineInit.reagent, 1, 1), 50);
		}

		// pulvarizer
		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 0), new ItemStack(MainInit.oreItem, 2,
				0), new ItemStack(MainInit.gems, 1, 1), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 1), new ItemStack(MainInit.oreItem, 2,
				1), new ItemStack(MainInit.gems, 1, 5), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 2), new ItemStack(MainInit.oreItem, 2,
				2), new ItemStack(MainInit.gems, 1, 0), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 3), new ItemStack(MainInit.oreItem, 2,
				3), new ItemStack(MainInit.gems, 1, 2), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 4), new ItemStack(MainInit.oreItem, 2,
				4), new ItemStack(MainInit.gems, 1, 23), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 5), new ItemStack(MainInit.oreItem, 2,
				5), new ItemStack(MainInit.oreItem, 1, 10), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 6), new ItemStack(MainInit.oreItem, 2,
				6), new ItemStack(MainInit.oreItem, 1, 11), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 7), new ItemStack(MainInit.oreItem, 2,
				7), new ItemStack(MainInit.oreItem, 1, 12), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 8), new ItemStack(MainInit.oreItem, 2,
				8), new ItemStack(MainInit.oreItem, 1, 13), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 9), new ItemStack(MainInit.oreItem, 2,
				9), new ItemStack(MainInit.oreItem, 1, 14), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 10), new ItemStack(MainInit.oreItem, 2,
				0), new ItemStack(MainInit.oreItem, 1, 1), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 11), new ItemStack(MainInit.oreItem, 2,
				5), new ItemStack(MainInit.oreItem, 1, 10), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 12), new ItemStack(MainInit.oreItem, 2,
				4), new ItemStack(MainInit.oreItem, 1, 23), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreNew, 1, 13), new ItemStack(MainInit.oreItem, 2,
				9), new ItemStack(MainInit.oreItem, 1, 14), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.layerNew, 1, 0), new ItemStack(MainInit.gems, 2,
				3), new ItemStack(MainInit.gems, 1, 6), 10);

		addPulverizerRecipe(5000, new ItemStack(MainInit.layerNew, 1, 6), new ItemStack(MainInit.gems, 2,
				12), new ItemStack(MainInit.gems, 1, 13), 10);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreItem, 1, 4), new ItemStack(MainInit.oreDust, 2,
				7), new ItemStack(MainInit.oreDust, 1, 9), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreItem, 1, 2), new ItemStack(MainInit.oreDust, 2,
				1), new ItemStack(MainInit.oreDust, 1, 12), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreItem, 1, 12), new ItemStack(MainInit.oreDust, 2,
				12), new ItemStack(MainInit.oreDust, 1, 1), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.oreItem, 1, 5), new ItemStack(MainInit.oreDust, 2,
				10), new ItemStack(MainInit.gems, 1, 15), 10);
	}

	private static void addCompressionFuel(String name, int amount) {
		ThermalExpansionHelper.addCompressionFuel(name, amount);
		// NBTTagCompound toSend = new NBTTagCompound();
		//
		// toSend.setString("fluid", name);
		// toSend.setInteger("energy", amount);
		//
		// FMLInterModComms.sendMessage("thermalexpansion", ThermalExpansionHelper.ADD_COMPRESSION_FUEL, toSend);
	}

	private static void addCoolant(String name, int amount, int factor) {
		ThermalExpansionHelper.addCoolant(name, amount, factor);
		// NBTTagCompound toSend = new NBTTagCompound();
		//
		// toSend.setString("fluid", name);
		// toSend.setInteger("energy", amount);
		// toSend.setInteger("factor", factor);
		//
		// FMLInterModComms.sendMessage("thermalexpansion", ThermalExpansionHelper.ADD_COOLANT, toSend);
	}

	private static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {
		if (!input.isEmpty() && output != null) {
			ThermalExpansionHelper.addCrucibleRecipe(energy, input, output);

			// NBTTagCompound toSend = new NBTTagCompound();
			//
			// toSend.setInteger("energy", energy);
			// toSend.setTag("input", new NBTTagCompound());
			// toSend.setTag("output", new NBTTagCompound());
			//
			// input.writeToNBT(toSend.getCompoundTag("input"));
			// output.writeToNBT(toSend.getCompoundTag("output"));
			//
			// FMLInterModComms.sendMessage("thermalexpansion", ThermalExpansionHelper.ADD_CRUCIBLE_RECIPE, toSend);
		}
	}

	private static void addRefineryRecipe(int energy, FluidStack input, FluidStack output, ItemStack outputItem,
			int chance) {
		if (input != null && output != null) {
			ThermalExpansionHelper.addRefineryRecipe(energy, input, output, outputItem);
			//
			// NBTTagCompound toSend = new NBTTagCompound();
			//
			// toSend.setInteger("energy", energy);
			// toSend.setTag("input", new NBTTagCompound());
			// toSend.setTag("output", new NBTTagCompound());
			//
			// if (!outputItem.isEmpty()) {
			// toSend.setTag("secondaryOutput", new NBTTagCompound());
			// outputItem.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
			// toSend.setInteger("secondaryChance", chance);
			// }
			//
			// input.writeToNBT(toSend.getCompoundTag("input"));
			// output.writeToNBT(toSend.getCompoundTag("output"));
			//
			// FMLInterModComms.sendMessage("thermalexpansion", ThermalExpansionHelper.ADD_REFINERY_RECIPE, toSend);
		}
	}

	private static void addPulverizerRecipe(int energy, ItemStack input, ItemStack out, ItemStack sec, int chance) {
		ThermalExpansionHelper.addPulverizerRecipe(energy, input, out, sec, chance);
		//
		// NBTTagCompound toSend = new NBTTagCompound();
		//
		// toSend.setInteger("energy", energy);
		// toSend.setTag("input", new NBTTagCompound());
		// toSend.setTag("primaryOutput", new NBTTagCompound());
		//
		// input.writeToNBT(toSend.getCompoundTag("input"));
		// out.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		//
		// if (!sec.isEmpty()) {
		// toSend.setTag("secondaryOutput", new NBTTagCompound());
		// sec.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
		// toSend.setInteger("secondaryChance", chance);
		// }
		// FMLInterModComms.sendMessage("thermalexpansion", ThermalExpansionHelper.ADD_PULVERIZER_RECIPE, toSend);
	}
}
