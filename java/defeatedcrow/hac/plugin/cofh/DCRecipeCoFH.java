package defeatedcrow.hac.plugin.cofh;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class DCRecipeCoFH {

	static void loadFuels() {
		addCompressionFuel(MachineInit.fuelOil.getName(), 800000);
		addCompressionFuel(MachineInit.fuelGas.getName(), 1000000);
		addCompressionFuel(FoodInit.blackLiquor.getName(), 100000);
		addCompressionFuel(FoodInit.oil.getName(), 200000);
	}

	static void loadRecipes() {
		// crusible
		Fluid creosote = FluidRegistry.getFluid("creosote");
		if (creosote != null) {
			addCrucibleRecipe(2000, new ItemStack(MachineInit.reagent, 1, 0), new FluidStack(creosote, 100));
		}

		// refinary
		if (DCPluginCoFH.naphtha != null && DCPluginCoFH.tree != null && DCPluginCoFH.tar != null
				&& DCPluginCoFH.rogin != null) {
			addRefineryRecipe(5000, new FluidStack(MachineInit.fuelOil, 100), new FluidStack(DCPluginCoFH.naphtha, 100),
					DCPluginCoFH.tar);

			addRefineryRecipe(5000, new FluidStack(FoodInit.blackLiquor, 100), new FluidStack(DCPluginCoFH.tree, 100),
					DCPluginCoFH.tar);

			addRefineryRecipe(5000, new FluidStack(FoodInit.oil, 100), new FluidStack(DCPluginCoFH.naphtha, 50),
					new ItemStack(MachineInit.reagent, 1, 1));
		}

		// pulvarizer
		addPulverizerRecipe(5000, new ItemStack(MainInit.ores, 1, 0), new ItemStack(MainInit.gems, 2, 3),
				new ItemStack(MainInit.gems, 1, 6), 10);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores, 1, 2), new ItemStack(MainInit.gems, 2, 0),
				new ItemStack(MainInit.gems, 1, 4), 10);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores, 1, 5), new ItemStack(MainInit.oreDust, 2, 7),
				new ItemStack(MainInit.oreDust, 1, 9), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores, 1, 8), new ItemStack(MainInit.oreDust, 2, 1),
				new ItemStack(MainInit.oreDust, 1, 12), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores, 1, 15), new ItemStack(MainInit.oreDust, 2, 7),
				new ItemStack(MainInit.oreDust, 1, 9), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores_2, 1, 6), new ItemStack(MainInit.gems, 2, 12),
				new ItemStack(MainInit.gems, 1, 13), 10);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores_2, 1, 7), new ItemStack(MainInit.gems, 2, 14),
				new ItemStack(MainInit.gems, 1, 14), 10);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores_2, 1, 9), new ItemStack(MainInit.oreDust, 2, 12),
				new ItemStack(MainInit.oreDust, 1, 1), 25);

		addPulverizerRecipe(5000, new ItemStack(MainInit.ores_2, 1, 11), new ItemStack(MainInit.oreDust, 2, 10),
				new ItemStack(MainInit.gems, 1, 15), 10);
	}

	private static void addCompressionFuel(String name, int amount) {
		NBTTagCompound toSend = new NBTTagCompound();

		toSend.setString("fluidName", name);
		toSend.setInteger("energy", amount);

		FMLInterModComms.sendMessage("thermalexpansion", "addcompressionfuel", toSend);
	}

	private static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {
		if (input != null && output != null) {

			NBTTagCompound toSend = new NBTTagCompound();

			toSend.setInteger("energy", energy);
			toSend.setTag("input", new NBTTagCompound());
			toSend.setTag("output", new NBTTagCompound());

			input.writeToNBT(toSend.getCompoundTag("input"));
			output.writeToNBT(toSend.getCompoundTag("output"));

			FMLInterModComms.sendMessage("thermalexpansion", "addcruciblerecipe", toSend);
		}
	}

	private static void addRefineryRecipe(int energy, FluidStack input, FluidStack output, ItemStack outputItem) {
		if (input != null && output != null) {

			NBTTagCompound toSend = new NBTTagCompound();

			toSend.setInteger("energy", energy);
			toSend.setTag("input", new NBTTagCompound());
			toSend.setTag("output", new NBTTagCompound());

			if (outputItem != null) {
				toSend.setTag("secondaryOutput", new NBTTagCompound());
				outputItem.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
			}

			input.writeToNBT(toSend.getCompoundTag("input"));
			output.writeToNBT(toSend.getCompoundTag("output"));

			FMLInterModComms.sendMessage("thermalexpansion", "addrefineryrecipe", toSend);
		}
	}

	private static void addPulverizerRecipe(int energy, ItemStack input, ItemStack out, ItemStack sec, int chance) {
		NBTTagCompound toSend = new NBTTagCompound();

		toSend.setInteger("energy", energy);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("primaryOutput", new NBTTagCompound());

		input.writeToNBT(toSend.getCompoundTag("input"));
		out.writeToNBT(toSend.getCompoundTag("primaryOutput"));

		if (sec != null) {
			toSend.setTag("secondaryOutput", new NBTTagCompound());
			sec.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
			toSend.setInteger("secondaryChance", chance);
		}
		FMLInterModComms.sendMessage("thermalexpansion", "addpulverizerrecipe", toSend);
	}
}
