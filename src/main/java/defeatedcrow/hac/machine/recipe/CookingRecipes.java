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
import net.minecraft.world.item.Items;
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

		// foods
		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_OAT), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_BREAD_GRAINS), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_MILLETS), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(4, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_PSEUDOCEREALS), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_OAT), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_BREAD_GRAINS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_MILLETS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(4, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_PSEUDOCEREALS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BOILED_RICE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_RICES)));

		// ingredients
		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SAP_SWEET)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.PALM_FLOWER)));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_RICES), Ingredient.of(FoodInit.FOOD_MALT.get())));

		DeviceRecipeList.addCookingRecipe(4, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_MILLETS), Ingredient.of(FoodInit.FOOD_MALT.get())));

		DeviceRecipeList.addCookingRecipe(5, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(Tags.Items.CROPS_POTATO), Ingredient.of(FoodInit.FOOD_MALT.get())));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_RICES), Ingredient.of(FoodInit.FOOD_MALT.get())));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_JAM.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FRUITS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_MARMALADE.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CITRUS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_ANKO.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ADZUKI), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_CUSTARD.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.SUGARS), Ingredient.of(Tags.Items.EGGS)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_CUSTARD.get(), 4), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.VANILLA_CURED), Ingredient.of(TagDC.ItemTag.SUGARS), Ingredient.of(Tags.Items.EGGS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_CHEESE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.COW_MILK), Ingredient.of(TagDC.ItemTag.RENNET)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_CHEESE.get(), 4), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.RENNET)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_TOFU.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SOY_MILK), Ingredient.of(TagDC.ItemTag.DUST_TRONA)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP_VEGI.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.CROP_ONION), Ingredient.of(TagDC.ItemTag.CROP_CELERY)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP_VEGI.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(Items.DRIED_KELP), Ingredient.of(Tags.Items.MUSHROOMS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP_BEEF.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.CROP_SPICY_VEGI), Ingredient.of(TagDC.ItemTag.BONE_COW)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP_PORK.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.CROP_SPICY_VEGI), Ingredient.of(TagDC.ItemTag.BONE_PIG)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP_CHICKEN.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.CROP_SPICY_VEGI), Ingredient.of(TagDC.ItemTag.BONE_CHICKEN)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.CROP_SPICY_VEGI), Ingredient.of(TagDC.ItemTag.RAW_MEAT),
				Ingredient.of(TagDC.ItemTag.CROP_CELERY), Ingredient.of(Tags.Items.CROPS_CARROT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.CROP_SPICY_VEGI), Ingredient.of(TagDC.ItemTag.RAW_EDIBLE_FISH),
				Ingredient.of(TagDC.ItemTag.CROP_CELERY), Ingredient.of(Tags.Items.CROPS_CARROT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_KETCHUP.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_FLAVORED), Ingredient.of(TagDC.ItemTag.SUGARS),
				Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(TagDC.ItemTag.VINEGAR)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_HOT_SAUSE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CHILI), Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(TagDC.ItemTag.VINEGAR)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_HUMMUS.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GARBANZO), Ingredient.of(TagDC.ItemTag.CROP_SESAMI), Ingredient.of(TagDC.ItemTag.CROP_GARLIC),
				Ingredient.of(TagDC.ItemTag.CROP_LEMON), Ingredient.of(TagDC.ItemTag.PLANT_OIL)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SALSA.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CHILI), Ingredient.of(TagDC.ItemTag.CROP_ONION),
				Ingredient.of(TagDC.ItemTag.CROP_BELL), Ingredient.of(TagDC.ItemTag.CROP_HERBS), Ingredient.of(TagDC.ItemTag.CROP_LEMON)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SALSA.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CHILI), Ingredient.of(TagDC.ItemTag.CROP_ONION),
				Ingredient.of(TagDC.ItemTag.CROP_PAPRIKA), Ingredient.of(TagDC.ItemTag.CROP_HERBS), Ingredient.of(TagDC.ItemTag.CROP_LEMON)));

		// misc
		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.TREEWAX.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LACQUER)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.CAMPHOR.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(FoodInit.LOG_CN_CAMPHOR.get())));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(CoreInit.DUST_SALT.get(), 2), new ItemStack(CoreInit.DUST_TRONA.get()), 100, FluidStack.EMPTY, boil,
			ImmutableList.of(TagDC.FluidTag.BRINE.location().toString()),
			ImmutableList.of());

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(CoreInit.DUST_SALT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of());

		// fermentation
		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.SPROUT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SEED_SPROUT)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.CURED_VANILLA.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_VANILLA)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_MALT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BARLEY)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.BOTTLE_BEER.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(FoodInit.FOOD_MALT.get()), Ingredient.of(FoodInit.FOOD_MALT.get())));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_KOJI.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(FoodInit.BOILED_RICE.get())));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.BOTTLE_SAKE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_RICES), Ingredient.of(FoodInit.FOOD_KOJI.get())));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.BOTTLE_WINE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RED_GRAPES)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.BOTTLE_WINE_WHITE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WHITE_GRAPE)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_SAUERKRAUT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CABAGGE), Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(TagDC.ItemTag.SEED_APIUM)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_UMEBOSHI.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_PLUM), Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(TagDC.ItemTag.CROP_PERILLA)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_TSUKEMONO.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TSUKEMONO), Ingredient.of(FoodInit.FOOD_KOJI.get())));

		DeviceRecipeList.addFermentationRecipe(2, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_TSUKEMONO.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_NAPA), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addFermentationRecipe(3, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_TSUKEMONO.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TSUKEMONO), Ingredient.of(TagDC.ItemTag.BRAN), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_KIMCHI.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TSUKEMONO), Ingredient.of(TagDC.ItemTag.CROP_CHILI), Ingredient.of(FoodInit.FOOD_SHRIMP_PASTE.get())));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_FISH_SAUSE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.FISH_BLUE), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_ANCHOVY.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SARDINE), Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(TagDC.ItemTag.PLANT_OIL)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_SHRIMP_PASTE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.KRILL), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_MISO.get(), 1), new ItemStack(FoodInit.FOOD_SOYSAUCE.get(), 1), 100, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SOY), Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(FoodInit.FOOD_KOJI.get())));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_SOYSAUCE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DEFATTED_SOY), Ingredient.of(TagDC.ItemTag.DUST_SALT), Ingredient.of(FoodInit.FOOD_KOJI.get())));

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_VINEGER.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(FoodInit.BOTTLE_SAKE.get()), Ingredient.of(TagDC.ItemTag.DUST_RICES)));

		DeviceRecipeList.addFermentationRecipe(2, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_VINEGER.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(FoodInit.BOTTLE_WINE.get())));

		DeviceRecipeList.addFermentationRecipe(3, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_VINEGER.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(FoodInit.BOTTLE_WINE_WHITE.get())));

	}

}
