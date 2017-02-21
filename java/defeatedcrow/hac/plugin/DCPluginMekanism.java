package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.FoodInit;
import mekanism.api.recipe.RecipeHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DCPluginMekanism {

	public static final DCPluginMekanism INSTANCE = new DCPluginMekanism();

	private DCPluginMekanism() {}

	public static void load() {
		Item bio = Item.REGISTRY.getObject(new ResourceLocation("Mekanism", "BioFuel"));
		if (bio != null) {
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 0), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 1), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 2), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 3), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 4), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 5), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 6), new ItemStack(bio, 4, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 7), new ItemStack(bio, 2, 0));
			RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 8), new ItemStack(bio, 2, 0));
		}

	}

}
