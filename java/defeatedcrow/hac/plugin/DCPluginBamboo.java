package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ruby.bamboo.api.BambooItems;
import ruby.bamboo.api.crafting.grind.GrindRegistory;

public class DCPluginBamboo {

	public static final DCPluginBamboo INSTANCE = new DCPluginBamboo();

	private DCPluginBamboo() {}

	public static void load() {
		if (ModuleConfig.r_mill) {
			loadHaCMillRecipe();
		}
		loadBambooMillRecipe();
	}

	static void loadHaCMillRecipe() {
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2),
				new ItemStack(BambooItems.RICE_SEED));

		RecipeAPI.registerSpinningRecipes.addRecipe(new ItemStack(MainInit.materials, 1, 0), 2,
				new ItemStack(BambooItems.STRAW));
	}

	static void loadBambooMillRecipe() {
		GrindRegistory.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 0), new ItemStack(MainInit.gems, 1, 8));

		GrindRegistory.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 1), new ItemStack(Items.WHEAT, 1, 0));

		GrindRegistory.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(FoodInit.crops, 1, 0));
	}

}