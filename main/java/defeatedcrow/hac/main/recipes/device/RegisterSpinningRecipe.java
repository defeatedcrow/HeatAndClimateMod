package defeatedcrow.hac.main.recipes.device;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.SpinningRecipe;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RegisterSpinningRecipe {

	public static void load() {

		RecipeAPI.registerSpinningRecipes.addRecipe(new SpinningRecipe(new ItemStack(MainInit.clothes, 1, 0), 4,
				"blockTallgrass"));

		RecipeAPI.registerSpinningRecipes.addRecipe(new SpinningRecipe(new ItemStack(MainInit.clothes, 1, 0), 4,
				"feedStraw"));

		RecipeAPI.registerSpinningRecipes.addRecipe(new SpinningRecipe(new ItemStack(MainInit.clothes, 1, 0), 4,
				"dustPlant"));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 1), 4, new ItemStack(
				Items.STRING));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 1), 4, "cropCotton");

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 1), 1, "blockWool");

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 2, 0), 1, new ItemStack(
				Blocks.HAY_BLOCK, 1, 0));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 2, 1), 1, new ItemStack(
				MainInit.cropBasket, 1, 5));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 4), 4, "dustAsbest");

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 6), 2, new ItemStack(
				MainInit.silkworm, 1, 2));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 4, 6), 1, new ItemStack(
				MainInit.cropBasket, 1, 11));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 10), 4, "bunchVine");

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.clothes, 1, 10), 1, new ItemStack(
				MainInit.builds, 1, 11));

	}

}
