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
		if (e != null && ModuleConfig.r_reactor) {
			RecipeAPI.registerReactorRecipes.addRecipe(null, null, 0F, new FluidStack(e,
					1000), null, DCHeatTier.KILN, new ItemStack(MachineInit.catalyst, 1, 1), new FluidStack(
							MainInit.ethanol, 1000), new FluidStack(MainInit.sulfuricAcid, 500), new Object[] {});
		}
	}

	public static void sendIMC() {
		Item bio = Item.REGISTRY.getObject(new ResourceLocation("mekanism", "biofuel"));
		if (bio != null && ModuleConfig.food) {
			for (int i = 0; i < 20; i++) {
				NBTTagCompound crop = new NBTTagCompound();
				crop.setTag("input", new ItemStack(FoodInit.crops, 1, 0).writeToNBT(new NBTTagCompound()));
				crop.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
				FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", crop);
			}
		}

		// enrichment

		NBTTagCompound dust_zinc = new NBTTagCompound();
		dust_zinc.setTag("input", new ItemStack(MainInit.oreItem, 1, 2).writeToNBT(new NBTTagCompound()));
		dust_zinc.setTag("output", new ItemStack(MainInit.oreDust, 2, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "EnrichmentChamberRecipe", dust_zinc);

		NBTTagCompound dust_nickel = new NBTTagCompound();
		dust_nickel.setTag("input", new ItemStack(MainInit.oreItem, 1, 6).writeToNBT(new NBTTagCompound()));
		dust_nickel.setTag("output", new ItemStack(MainInit.oreDust, 2, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "EnrichmentChamberRecipe", dust_nickel);

		NBTTagCompound dirty_zinc = new NBTTagCompound();
		dirty_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 0).writeToNBT(new NBTTagCompound()));
		dirty_zinc.setTag("output", new ItemStack(MainInit.oreDust, 1, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "EnrichmentChamberRecipe", dirty_zinc);

		NBTTagCompound dirty_nickel = new NBTTagCompound();
		dirty_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 4).writeToNBT(new NBTTagCompound()));
		dirty_nickel.setTag("output", new ItemStack(MainInit.oreDust, 1, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "EnrichmentChamberRecipe", dirty_nickel);

		// crusher

		NBTTagCompound cotton = new NBTTagCompound();
		cotton.setTag("input", new ItemStack(MainInit.clothes, 1, 3).writeToNBT(new NBTTagCompound()));
		cotton.setTag("output", new ItemStack(Items.STRING, 4, 0).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", cotton);

		NBTTagCompound clump_zinc = new NBTTagCompound();
		clump_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 2).writeToNBT(new NBTTagCompound()));
		clump_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 0).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", clump_zinc);

		NBTTagCompound clump_nickel = new NBTTagCompound();
		clump_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 6).writeToNBT(new NBTTagCompound()));
		clump_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 4).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", clump_nickel);

		NBTTagCompound crush_zinc = new NBTTagCompound();
		crush_zinc.setTag("input", new ItemStack(MainInit.oreIngot, 1, 1).writeToNBT(new NBTTagCompound()));
		crush_zinc.setTag("output", new ItemStack(MainInit.oreDust, 1, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", crush_zinc);

		NBTTagCompound crush_nickel = new NBTTagCompound();
		crush_nickel.setTag("input", new ItemStack(MainInit.oreIngot, 1, 2).writeToNBT(new NBTTagCompound()));
		crush_nickel.setTag("output", new ItemStack(MainInit.oreDust, 1, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", crush_nickel);

		// purification

		NBTTagCompound ore3_zinc = new NBTTagCompound();
		ore3_zinc.setTag("input", new ItemStack(MainInit.oreItem, 1, 2).writeToNBT(new NBTTagCompound()));
		ore3_zinc.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		ore3_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 3, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "PurificationChamberRecipe", ore3_zinc);

		NBTTagCompound ore3_nickel = new NBTTagCompound();
		ore3_nickel.setTag("input", new ItemStack(MainInit.oreItem, 1, 6).writeToNBT(new NBTTagCompound()));
		ore3_nickel.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		ore3_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 3, 6).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "PurificationChamberRecipe", ore3_nickel);

		NBTTagCompound shard_zinc = new NBTTagCompound();
		shard_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 1).writeToNBT(new NBTTagCompound()));
		shard_zinc.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		shard_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "PurificationChamberRecipe", shard_zinc);

		NBTTagCompound shard_nickel = new NBTTagCompound();
		shard_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 5).writeToNBT(new NBTTagCompound()));
		shard_nickel.setTag("gasType", new GasStack(GasRegistry.getGas("oxygen"), 1000).write(new NBTTagCompound()));
		shard_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 6).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "PurificationChamberRecipe", shard_nickel);

		// c_injection

		NBTTagCompound ore4_zinc = new NBTTagCompound();
		ore4_zinc.setTag("input", new ItemStack(MainInit.oreItem, 1, 2).writeToNBT(new NBTTagCompound()));
		ore4_zinc.setTag("gasType", new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000)
				.write(new NBTTagCompound()));
		ore4_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 4, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "ChemicalInjectionChamberRecipe", ore4_zinc);

		NBTTagCompound ore4_nickel = new NBTTagCompound();
		ore4_nickel.setTag("input", new ItemStack(MainInit.oreItem, 1, 6).writeToNBT(new NBTTagCompound()));
		ore4_nickel.setTag("gasType", new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000)
				.write(new NBTTagCompound()));
		ore4_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 4, 5).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "ChemicalInjectionChamberRecipe", ore4_nickel);

		NBTTagCompound crystal_zinc = new NBTTagCompound();
		crystal_zinc.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 3).writeToNBT(new NBTTagCompound()));
		crystal_zinc.setTag("gasType", new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000)
				.write(new NBTTagCompound()));
		crystal_zinc.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 1).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "ChemicalInjectionChamberRecipe", crystal_zinc);

		NBTTagCompound crystal_nickel = new NBTTagCompound();
		crystal_nickel.setTag("input", new ItemStack(MainInit.metalMaterials, 1, 7).writeToNBT(new NBTTagCompound()));
		crystal_nickel.setTag("gasType", new GasStack(GasRegistry.getGas("hydrogenchloride"), 1000)
				.write(new NBTTagCompound()));
		crystal_nickel.setTag("output", new ItemStack(MainInit.metalMaterials, 1, 5).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("mekanism", "ChemicalInjectionChamberRecipe", crystal_nickel);

	}

}
