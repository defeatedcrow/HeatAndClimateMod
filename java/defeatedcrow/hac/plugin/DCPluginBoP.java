package defeatedcrow.hac.plugin;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.SpinningRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class DCPluginBoP {

	public static final DCPluginBoP INSTANCE = new DCPluginBoP();

	private DCPluginBoP() {}

	public static void load() {
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
		OreDictionary.registerOre("dropHoney", new ItemStack(BOPItems.jar_filled));
		OreDictionary.registerOre("dropHoney", new ItemStack(BOPItems.filled_honeycomb));

		OreDictionary.registerOre("cropSeaweed", new ItemStack(BOPBlocks.coral, 1, 4));
		OreDictionary.registerOre("cropSeaweed", new ItemStack(BOPBlocks.seaweed, 1, 0));
		OreDictionary.registerOre("cropKelp", new ItemStack(BOPBlocks.seaweed, 1, 0));

		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 0));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 1));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 2));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 3));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 4));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 5));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 7));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 8));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 9));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 13));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 14));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_0, 1, 15));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(BOPBlocks.plant_1, 1, 0));

		// machine
		if (ModuleConfig.r_mill) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2),
					new ItemStack(MainInit.foodMaterials, 1, 2), 0.5F, "plantWildRice");
		}

		if (ModuleConfig.r_spinning) {
			RecipeAPI.registerSpinningRecipes
					.addRecipe(new SpinningRecipe(new ItemStack(MainInit.materials, 1, 0), 2, "plantFlax"));
		}

	}

}
