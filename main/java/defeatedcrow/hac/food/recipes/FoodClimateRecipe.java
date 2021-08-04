package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodClimateRecipe {

	public static void load() {
		loadClimateRecipes();
		loadSmeltingRecipe();
		loadClimateData();
	}

	static void loadClimateRecipes() {

		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 1), new ItemStack(FoodInit.bread, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 3), new ItemStack(FoodInit.bread, 1, 2));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 5), new ItemStack(FoodInit.bread, 1, 4));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 7), new ItemStack(FoodInit.bread, 1, 6));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 11), new ItemStack(FoodInit.bread, 1, 10));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 13), new ItemStack(FoodInit.bread, 1, 12));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 15), new ItemStack(FoodInit.bread, 1, 14));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 17), new ItemStack(FoodInit.bread, 1, 16));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 19), new ItemStack(FoodInit.bread, 1, 18));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 21), new ItemStack(FoodInit.bread, 1, 20));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 23), new ItemStack(FoodInit.bread, 1, 22));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 25), new ItemStack(FoodInit.bread, 1, 24));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 27), new ItemStack(FoodInit.bread, 1, 26));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 29), new ItemStack(FoodInit.bread, 1, 28));
		addFoodRecipe(new ItemStack(FoodInit.bread, 1, 31), new ItemStack(FoodInit.bread, 1, 30));

		addFoodRecipe(new ItemStack(FoodInit.pizza, 1, 1), new ItemStack(FoodInit.pizza, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.pizza, 1, 3), new ItemStack(FoodInit.pizza, 1, 2));

		addFoodRecipe(new ItemStack(FoodInit.cookie, 1, 1), new ItemStack(FoodInit.cookie, 1, 0));

		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 1), new ItemStack(FoodInit.sticks, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 3), new ItemStack(FoodInit.sticks, 1, 2));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 5), new ItemStack(FoodInit.sticks, 1, 4));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 7), new ItemStack(FoodInit.sticks, 1, 6));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 9), new ItemStack(FoodInit.sticks, 1, 8));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 11), new ItemStack(FoodInit.sticks, 1, 10));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 13), new ItemStack(FoodInit.sticks, 1, 12));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 15), new ItemStack(FoodInit.sticks, 1, 14));
		addFoodRecipe(new ItemStack(FoodInit.sticks, 1, 17), new ItemStack(FoodInit.sticks, 1, 16));

		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 1), new ItemStack(FoodInit.pastryRound, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 3), new ItemStack(FoodInit.pastryRound, 1, 2));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 5), new ItemStack(FoodInit.pastryRound, 1, 4));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 7), new ItemStack(FoodInit.pastryRound, 1, 6));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 9), new ItemStack(FoodInit.pastryRound, 1, 8));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 11), new ItemStack(FoodInit.pastryRound, 1, 10));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 13), new ItemStack(FoodInit.pastryRound, 1, 12));
		addFoodRecipe(new ItemStack(FoodInit.pastryRound, 1, 15), new ItemStack(FoodInit.pastryRound, 1, 14));

		addFoodRecipe(new ItemStack(FoodInit.pastrySquare, 1, 1), new ItemStack(FoodInit.pastrySquare, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.pastrySquare, 1, 3), new ItemStack(FoodInit.pastrySquare, 1, 2));
		addFoodRecipe(new ItemStack(FoodInit.pastrySquare, 1, 5), new ItemStack(FoodInit.pastrySquare, 1, 4));
		addFoodRecipe(new ItemStack(FoodInit.pastrySquare, 1, 7), new ItemStack(FoodInit.pastrySquare, 1, 6));
		addFoodRecipe(new ItemStack(FoodInit.pastrySquare, 1, 9), new ItemStack(FoodInit.pastrySquare, 1, 8));
		addFoodRecipe(new ItemStack(FoodInit.pastrySquare, 1, 11), new ItemStack(FoodInit.pastrySquare, 1, 10));

		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 1), new ItemStack(FoodInit.plateMeal, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 3), new ItemStack(FoodInit.plateMeal, 1, 2));
		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 5), new ItemStack(FoodInit.plateMeal, 1, 4));
		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 7), new ItemStack(FoodInit.plateMeal, 1, 6));
		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 9), new ItemStack(FoodInit.plateMeal, 1, 8));
		addFoodRecipe(new ItemStack(FoodInit.plateMeal, 1, 11), new ItemStack(FoodInit.plateMeal, 1, 10));

		addFoodRecipe(new ItemStack(FoodInit.plateSoup, 1, 1), new ItemStack(FoodInit.plateSoup, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.plateSoup, 1, 3), new ItemStack(FoodInit.plateSoup, 1, 2));
		addFoodRecipe(new ItemStack(FoodInit.plateSoup, 1, 5), new ItemStack(FoodInit.plateSoup, 1, 4));
		addFoodRecipe(new ItemStack(FoodInit.plateSoup, 1, 7), new ItemStack(FoodInit.plateSoup, 1, 6));
		addFoodRecipe(new ItemStack(FoodInit.plateSoup, 1, 9), new ItemStack(FoodInit.plateSoup, 1, 8));
		addFoodRecipe(new ItemStack(FoodInit.plateSoup, 1, 11), new ItemStack(FoodInit.plateSoup, 1, 10));

		addFoodRecipe(new ItemStack(FoodInit.cake, 1, 1), new ItemStack(FoodInit.cake, 1, 0));
		addFoodRecipe(new ItemStack(FoodInit.cake, 1, 9), new ItemStack(FoodInit.cake, 1, 8));
		addFoodRecipe(new ItemStack(FoodInit.cake, 1, 14), new ItemStack(FoodInit.cake, 1, 13));

		addFoodRecipe(new ItemStack(FoodInit.mochi, 1, 1), new ItemStack(FoodInit.mochi, 1, 0));

	}

	private static void addFoodRecipe(ItemStack out, ItemStack in) {
		ClimateSmelting r = new ClimateSmelting(out, null, DCHeatTier.OVEN, DCHumidity.DRY, null, 0F, false, in);
		r.requiredHeat().add(DCHeatTier.SMELTING);
		r.requiredHum().add(DCHumidity.NORMAL);
		r.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerSmelting.addRecipe(r);

		GameRegistry.addSmelting(in, out, 0);
	}

	private static void loadSmeltingRecipe() {
		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				5), DCHeatTier.BOIL, DCHumidity.UNDERWATER, null, false, new ItemStack(FoodInit.crops, 1, 10));

		RecipeAPI.registerSmelting.addRecipe(new ItemStack(MainInit.bakedApple, 1,
				6), DCHeatTier.BOIL, DCHumidity.UNDERWATER, null, false, new ItemStack(FoodInit.crops, 1, 12));
	}

	private static void loadClimateData() {
		ClimateAPI.registerBlock.registerHumBlock(FoodInit.baseFertilizer, 0, DCHumidity.WET);
	}

}
