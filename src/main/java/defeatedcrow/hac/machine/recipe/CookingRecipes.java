package defeatedcrow.hac.machine.recipe;

import java.util.List;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.recipe.device.DeviceRecipeList;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

public class CookingRecipes {
	public static CookingRecipes INSTANCE = new CookingRecipes() {};

	public static void init() {
		List<DCHeatTier> boil = ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN);
		List<DCHeatTier> heat = ImmutableList.of(DCHeatTier.OVEN, DCHeatTier.KILN, DCHeatTier.SMELTING);
		List<DCHumidity> e_hum = ImmutableList.of();
		List<DCAirflow> e_air = ImmutableList.of();
		List<String> tags = ImmutableList.of();

		// ingredients
		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SAP_SWEET)));

		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.PALM_FLOWER)));

		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_JAM.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FRUITS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_MARMALADE.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CITRUS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_ANKO.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ADZUKI), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_CUSTARD.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.SUGARS), Ingredient.of(Tags.Items.EGGS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_CUSTARD.get(), 4), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.VANILLA_CURED), Ingredient.of(TagDC.ItemTag.SUGARS), Ingredient.of(Tags.Items.EGGS)));

		// misc
		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(FoodInit.TREEWAX.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LACQUER)));

		DeviceRecipeList.addCookingRecipe(0, RecipeTypeDC.COOKING, new ItemStack(CoreInit.DUST_SALT.get(), 2), new ItemStack(CoreInit.DUST_TRONA.get()), 100, FluidStack.EMPTY, boil,
			ImmutableList.of(TagDC.FluidTag.BRINE.location().toString()),
			ImmutableList.of());

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(CoreInit.DUST_SALT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of());

		// fermentation
		DeviceRecipeList.addFermentationRecipe(0, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.CURED_VANILLA.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_VANILLA)));

		DeviceRecipeList.addFermentationRecipe(0, RecipeTypeDC.FERMENTATION, ItemStack.EMPTY, ItemStack.EMPTY, 0, new FluidStack(CoreInit.SPARKLING.getStillFluid().get(), 1000),
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SUGARS)));

	}

}
