package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.GasStack;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class DCPluginMekanism {

	public static final DCPluginMekanism INSTANCE = new DCPluginMekanism();

	private DCPluginMekanism() {}

	public static void load() {
		Fluid e = FluidRegistry.getFluid("liquidethene");
		if (e != null) {
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0F, new FluidStack(e, 1000), null, DCHeatTier.KILN,
					new ItemStack(MachineInit.catalyst, 1, 1), new FluidStack(MachineInit.ethanol, 1000),
					new FluidStack(MachineInit.sulfuricAcid, 500), new Object[] {});
		}
	}

	public static void sendIMC() {
		Item bio = Item.REGISTRY.getObject(new ResourceLocation("mekanism", "biofuel"));
		if (bio != null && ModuleConfig.food) {
			NBTTagCompound crop0 = new NBTTagCompound();
			crop0.setTag("input", new ItemStack(FoodInit.crops, 1, 0).writeToNBT(new NBTTagCompound()));
			crop0.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop0);

			NBTTagCompound crop1 = new NBTTagCompound();
			crop1.setTag("input", new ItemStack(FoodInit.crops, 1, 1).writeToNBT(new NBTTagCompound()));
			crop1.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop1);

			NBTTagCompound crop2 = new NBTTagCompound();
			crop2.setTag("input", new ItemStack(FoodInit.crops, 1, 2).writeToNBT(new NBTTagCompound()));
			crop2.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop2);

			NBTTagCompound crop3 = new NBTTagCompound();
			crop3.setTag("input", new ItemStack(FoodInit.crops, 1, 3).writeToNBT(new NBTTagCompound()));
			crop3.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop3);

			NBTTagCompound crop4 = new NBTTagCompound();
			crop4.setTag("input", new ItemStack(FoodInit.crops, 1, 4).writeToNBT(new NBTTagCompound()));
			crop4.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop4);

			NBTTagCompound crop5 = new NBTTagCompound();
			crop5.setTag("input", new ItemStack(FoodInit.crops, 1, 5).writeToNBT(new NBTTagCompound()));
			crop5.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop5);

			NBTTagCompound crop6 = new NBTTagCompound();
			crop6.setTag("input", new ItemStack(FoodInit.crops, 1, 6).writeToNBT(new NBTTagCompound()));
			crop6.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop6);

			NBTTagCompound crop7 = new NBTTagCompound();
			crop7.setTag("input", new ItemStack(FoodInit.crops, 1, 7).writeToNBT(new NBTTagCompound()));
			crop7.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop7);

			NBTTagCompound crop8 = new NBTTagCompound();
			crop8.setTag("input", new ItemStack(FoodInit.crops, 1, 8).writeToNBT(new NBTTagCompound()));
			crop8.setTag("output", new ItemStack(bio, 2, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop8);

			NBTTagCompound crop9 = new NBTTagCompound();
			crop9.setTag("input", new ItemStack(FoodInit.crops, 1, 9).writeToNBT(new NBTTagCompound()));
			crop9.setTag("output", new ItemStack(bio, 2, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop9);
		}

		// enrichment

		NBTTagCompound dust_zinc = new NBTTagCompound();
		dust_zinc.setTag("input", new ItemStack(MainInit.ores, 1, 8).writeToNBT(new NBTTagCompound()));
		dust_zinc.setTag("output", new ItemStack(MainInit.oreDust, 2, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", dust_zinc);

		NBTTagCompound dust_nickel = new NBTTagCompound();
		dust_nickel.setTag("input", new ItemStack(MainInit.ores, 1, 7).writeToNBT(new NBTTagCompound()));
		dust_nickel.setTag("output", new ItemStack(MainInit.oreDust, 2, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", dust_nickel);

		NBTTagCompound dust_nickel2 = new NBTTagCompound();
		dust_nickel2.setTag("input", new ItemStack(MainInit.ores_2, 1, 8).writeToNBT(new NBTTagCompound()));
		dust_nickel2.setTag("output", new ItemStack(MainInit.oreDust, 2, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", dust_nickel2);

		NBTTagCompound dirty_zinc = new NBTTagCompound();
		dirty_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 0).writeToNBT(new NBTTagCompound()));
		dirty_zinc.setTag("output", new ItemStack(MainInit.oreDust, 1, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", dirty_zinc);

		NBTTagCompound dirty_nickel = new NBTTagCompound();
		dirty_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 4).writeToNBT(new NBTTagCompound()));
		dirty_nickel.setTag("output", new ItemStack(MainInit.oreDust, 1, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", dirty_nickel);

		// crusher

		NBTTagCompound cotton = new NBTTagCompound();
		cotton.setTag("input", new ItemStack(MainInit.materials, 1, 3).writeToNBT(new NBTTagCompound()));
		cotton.setTag("output", new ItemStack(Items.STRING, 4, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", cotton);

		NBTTagCompound clump_zinc = new NBTTagCompound();
		clump_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 2).writeToNBT(new NBTTagCompound()));
		clump_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 0).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", clump_zinc);

		NBTTagCompound clump_nickel = new NBTTagCompound();
		clump_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 6).writeToNBT(new NBTTagCompound()));
		clump_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 4).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", clump_nickel);

		NBTTagCompound crush_zinc = new NBTTagCompound();
		crush_zinc.setTag("input", new ItemStack(MainInit.oreIngot, 1, 1).writeToNBT(new NBTTagCompound()));
		crush_zinc.setTag("output", new ItemStack(MainInit.oreDust, 1, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crush_zinc);

		NBTTagCompound crush_nickel = new NBTTagCompound();
		crush_nickel.setTag("input", new ItemStack(MainInit.oreIngot, 1, 2).writeToNBT(new NBTTagCompound()));
		crush_nickel.setTag("output", new ItemStack(MainInit.oreDust, 1, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crush_nickel);

		// purification

		NBTTagCompound ore3_zinc = new NBTTagCompound();
		ore3_zinc.setTag("input", new ItemStack(MainInit.ores, 1, 8).writeToNBT(new NBTTagCompound()));
		ore3_zinc.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		ore3_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 3, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "PurificationChamberRecipe", ore3_zinc);

		NBTTagCompound ore3_nickel = new NBTTagCompound();
		ore3_nickel.setTag("input", new ItemStack(MainInit.ores, 1, 7).writeToNBT(new NBTTagCompound()));
		ore3_nickel.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		ore3_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 3, 6).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "PurificationChamberRecipe", ore3_nickel);

		NBTTagCompound ore3_nickel2 = new NBTTagCompound();
		ore3_nickel2.setTag("input", new ItemStack(MainInit.ores_2, 1, 8).writeToNBT(new NBTTagCompound()));
		ore3_nickel2.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		ore3_nickel2.setTag("output", new ItemStack(MainInit.metalMaterials, 3, 6).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "PurificationChamberRecipe", ore3_nickel2);

		NBTTagCompound shard_zinc = new NBTTagCompound();
		shard_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 1).writeToNBT(new NBTTagCompound()));
		shard_zinc.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		shard_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "PurificationChamberRecipe", shard_zinc);

		NBTTagCompound shard_nickel = new NBTTagCompound();
		shard_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 5).writeToNBT(new NBTTagCompound()));
		shard_nickel.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		shard_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 6).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "PurificationChamberRecipe", shard_nickel);

		// c_injection

		NBTTagCompound ore4_zinc = new NBTTagCompound();
		ore4_zinc.setTag("input", new ItemStack(MainInit.ores, 1, 8).writeToNBT(new NBTTagCompound()));
		ore4_zinc.setTag("gasType",
				new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000).write(new NBTTagCompound()));
		ore4_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 4, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "ChemicalInjectionChamberRecipe", ore4_zinc);

		NBTTagCompound ore4_nickel = new NBTTagCompound();
		ore4_nickel.setTag("input", new ItemStack(MainInit.ores, 1, 7).writeToNBT(new NBTTagCompound()));
		ore4_nickel.setTag("gasType",
				new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000).write(new NBTTagCompound()));
		ore4_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 4, 5).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "ChemicalInjectionChamberRecipe", ore4_nickel);

		NBTTagCompound ore4_nickel2 = new NBTTagCompound();
		ore4_nickel2.setTag("input", new ItemStack(MainInit.ores_2, 1, 8).writeToNBT(new NBTTagCompound()));
		ore4_nickel2.setTag("gasType",
				new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000).write(new NBTTagCompound()));
		ore4_nickel2.setTag("output", new ItemStack(MainInit.metalMaterials, 4, 5).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "ChemicalInjectionChamberRecipe", ore4_nickel2);

		NBTTagCompound crystal_zinc = new NBTTagCompound();
		crystal_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 3).writeToNBT(new NBTTagCompound()));
		crystal_zinc.setTag("gasType",
				new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000).write(new NBTTagCompound()));
		crystal_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "ChemicalInjectionChamberRecipe", crystal_zinc);

		NBTTagCompound crystal_nickel = new NBTTagCompound();
		crystal_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 7).writeToNBT(new NBTTagCompound()));
		crystal_nickel.setTag("gasType",
				new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000).write(new NBTTagCompound()));
		crystal_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 5).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "ChemicalInjectionChamberRecipe", crystal_nickel);

	}

}
