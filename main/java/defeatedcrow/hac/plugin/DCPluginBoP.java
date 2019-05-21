package defeatedcrow.hac.plugin;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.SpinningRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.recipes.FoodFluidRecipe;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class DCPluginBoP {

	public static final DCPluginBoP INSTANCE = new DCPluginBoP();

	private DCPluginBoP() {}

	public static void loadInit() {

		// climate registering
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.flower_1, 4, DCHeatTier.FROSTBITE);
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.hard_ice, 0, DCHeatTier.COLD);
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.hot_spring_water, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHeatBlock(BOPBlocks.flower_0, 15, DCHeatTier.OVEN);

		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.dried_sand, 0, DCHumidity.DRY);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.ash_block, 0, DCHumidity.DRY);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.flesh, 0, DCHumidity.WET);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.mud, 0, DCHumidity.WET);
		ClimateAPI.registerBlock.registerHumBlock(BOPBlocks.coral, 0, DCHumidity.UNDERWATER);

		// add ore
		addOre("dropHoney", BOPItems.jar_filled, 0);
		addOre("dropHoney", BOPItems.filled_honeycomb, 0);

		addOre("cropSeaweed", BOPBlocks.seaweed, 0);
		addOre("cropKelp", BOPBlocks.seaweed, 0);
		addOre("listAllberry", BOPItems.berries, 0);

		addOre("listAllmushroom", BOPBlocks.mushroom, 1);
		addOre("listAllmushroom", BOPBlocks.mushroom, 2);
		addOre("listAllmushroom", BOPBlocks.mushroom, 4);

		addOre("vineLeaves", BOPBlocks.ivy, 0);
		addOre("vineLeaves", BOPBlocks.willow_vine, 0);

		addOre("blockTallgrass", BOPBlocks.plant_0, 0);
		addOre("blockTallgrass", BOPBlocks.plant_0, 1);
		addOre("blockTallgrass", BOPBlocks.plant_0, 2);
		addOre("blockTallgrass", BOPBlocks.plant_0, 3);
		addOre("blockTallgrass", BOPBlocks.plant_0, 4);
		addOre("blockTallgrass", BOPBlocks.plant_0, 5);
		addOre("blockTallgrass", BOPBlocks.plant_0, 7);
		addOre("blockTallgrass", BOPBlocks.plant_0, 8);
		addOre("blockTallgrass", BOPBlocks.plant_0, 9);
		addOre("blockTallgrass", BOPBlocks.plant_0, 13);
		addOre("blockTallgrass", BOPBlocks.plant_0, 14);
		addOre("blockTallgrass", BOPBlocks.plant_0, 15);
		addOre("blockTallgrass", BOPBlocks.plant_1, 0);

		addOre("cropWildice", BOPBlocks.plant_1, 3);
		addOre("listAllgrain", BOPBlocks.plant_1, 3);
		addOre("cropBarlay", BOPBlocks.plant_1, 11);
		addOre("listAllgrain", BOPBlocks.plant_1, 11);

		// machine
		if (ModuleConfig.machine && ModuleConfig.r_mill) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), null, 0.0F, "plantWildrice");

			Block stalagmite = Block.REGISTRY.getObject(new ResourceLocation("biomesoplenty", "stone_formations"));
			if (stalagmite != null && stalagmite != Blocks.AIR) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 2), null, 0.0F, new ItemStack(
						stalagmite, 1, 0));

				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 2), null, 0.0F, new ItemStack(
						MachineInit.rotaryBlade, 1, 1), new ItemStack(stalagmite, 1, 0));
			}

			Item wax = Item.REGISTRY.getObject(new ResourceLocation("forestry", "beeswax"));
			if (DCIntegrationCore.loadedForestry && wax != null) {
				RecipeAPI.registerMills.addRecipe(new ItemStack(wax, 1, 0), null, 0.0F, new ItemStack(
						BOPItems.honeycomb, 1, 0));

				RecipeAPI.registerCrushers.addRecipe(new ItemStack(wax, 1, 0), null, 0.0F, new ItemStack(
						MachineInit.rotaryBlade, 1, 0), new ItemStack(BOPItems.honeycomb, 1, 0));
			}

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), new ItemStack(
					MainInit.foodDust, 1, 0), 0.25F, new ItemStack(MainInit.foodDust, 1, 1), 0.25F, null, new ItemStack(
							MachineInit.rotaryBlade, 1, 0), "cropWildrice");

		}

		if (ModuleConfig.machine && ModuleConfig.r_spinning) {
			RecipeAPI.registerSpinningRecipes.addRecipe(new SpinningRecipe(new ItemStack(MainInit.clothes, 1, 0), 2,
					"plantFlax"));
		}

		if (ModuleConfig.food && ModuleConfig.r_fluid) {
			FoodFluidRecipe.regNonFoodrecipe(new ItemStack(MainInit.foodDust, 1,
					3), null, 0F, null, DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(
							FluidRegistry.WATER, 200), new Object[] { "cropBarlay" });

			FoodFluidRecipe.regNonFoodrecipe(new ItemStack(FoodInit.crops, 1,
					18), null, 0F, null, DCHeatTier.BOIL, null, null, false, new FluidStack(FluidRegistry.WATER,
							200), new Object[] { "plantReed", "dustAsh" });
		}

	}

	private static void addOre(String string, Block block, int meta) {
		try {
			OreDictionary.registerOre(string, new ItemStack(block, 1, meta));
		} catch (Exception e) {

		}
	}

	private static void addOre(String string, Item item, int meta) {
		try {
			OreDictionary.registerOre(string, new ItemStack(item, 1, meta));
		} catch (Exception e) {

		}
	}

}
