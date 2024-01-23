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
		List<DCHeatTier> cool = ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL);
		List<DCHeatTier> boil = ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN);
		List<DCHeatTier> heat = ImmutableList.of(DCHeatTier.OVEN, DCHeatTier.KILN, DCHeatTier.SMELTING);
		List<DCHumidity> e_hum = ImmutableList.of();
		List<DCAirflow> e_air = ImmutableList.of();
		List<String> tags = ImmutableList.of();

		// foods
		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_OAT), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_BREAD_GRAINS), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_MILLETS), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(4, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_PSEUDOCEREALS), Ingredient.of(TagDC.ItemTag.DUST_SALT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_OAT), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_BREAD_GRAINS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_MILLETS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(4, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_MILK.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_PSEUDOCEREALS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_SAFFRON.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.DUST_BARLEY), Ingredient.of(TagDC.ItemTag.BONE_COW),
				Ingredient.of(TagDC.ItemTag.CROP_ONION), Ingredient.of(TagDC.ItemTag.CROP_SAFFRON), Ingredient.of(TagDC.ItemTag.CHEESE)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_SAFFRON.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.DUST_RICES), Ingredient.of(TagDC.ItemTag.BONE_COW),
				Ingredient.of(TagDC.ItemTag.CROP_ONION), Ingredient.of(TagDC.ItemTag.CROP_SAFFRON), Ingredient.of(TagDC.ItemTag.CHEESE)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_SQUID.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.DUST_BARLEY), Ingredient.of(TagDC.ItemTag.SQUID),
				Ingredient.of(TagDC.ItemTag.CROP_ONION), Ingredient.of(TagDC.ItemTag.CROP_HERBS), Ingredient.of(TagDC.ItemTag.PLANT_OIL)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.PORRIDGE_SQUID.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.DUST_RICES), Ingredient.of(TagDC.ItemTag.SQUID),
				Ingredient.of(TagDC.ItemTag.CROP_ONION), Ingredient.of(TagDC.ItemTag.CROP_HERBS), Ingredient.of(TagDC.ItemTag.PLANT_OIL)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BOILED_RICE.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.DUST_RICES)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_POTATO.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(Tags.Items.CROPS_POTATO), Ingredient.of(TagDC.ItemTag.CREAMS),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS)));

		DeviceRecipeList.addCookingRecipe(3, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_POTATO.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_SWEET_POTATO), Ingredient.of(TagDC.ItemTag.CREAMS),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_PUMPKIN.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_PUMPKIN), Ingredient.of(TagDC.ItemTag.CREAMS),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_CORN.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_CORN), Ingredient.of(TagDC.ItemTag.CREAMS),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_SPINACH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_SPINACH), Ingredient.of(Tags.Items.CROPS_POTATO),
				Ingredient.of(TagDC.ItemTag.CREAMS), Ingredient.of(TagDC.ItemTag.CROP_ONIONS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_PARSNIP.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_PARSNIP), Ingredient.of(TagDC.ItemTag.CREAMS),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_SHRIMP.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.PRAWN), Ingredient.of(TagDC.ItemTag.CROP_TOMATO),
				Ingredient.of(TagDC.ItemTag.CROP_GARLIC), Ingredient.of(TagDC.ItemTag.FOOD_FAT), Ingredient.of(FoodInit.BOTTLE_WINE_WHITE.get())));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CREAM_SHRIMP.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CRAB), Ingredient.of(TagDC.ItemTag.CROP_TOMATO),
				Ingredient.of(TagDC.ItemTag.CROP_GARLIC), Ingredient.of(TagDC.ItemTag.FOOD_FAT), Ingredient.of(FoodInit.BOTTLE_WINE_WHITE.get())));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CHINESE_EGG.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(FoodInit.BASESOUP_CHICKEN.get()), Ingredient.of(Tags.Items.EGGS), Ingredient.of(TagDC.ItemTag.CROP_CHIVES)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CHINESE_CRAB.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(FoodInit.BASESOUP_CHICKEN.get()), Ingredient.of(Tags.Items.EGGS), Ingredient.of(TagDC.ItemTag.CRAB),
				Ingredient.of(TagDC.ItemTag.CROP_CORN), Ingredient.of(TagDC.ItemTag.CROP_CHIVES)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_JUTE.get(), 2), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_JUTE), Ingredient.of(TagDC.ItemTag.CROP_GARLIC),
				Ingredient.of(TagDC.ItemTag.FOOD_FAT), Ingredient.of(TagDC.ItemTag.SEED_CORIANDER)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_MINESTRONE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_ONION),
				Ingredient.of(Tags.Items.CROPS_CARROT), Ingredient.of(TagDC.ItemTag.CROP_CABAGGE), Ingredient.of(TagDC.ItemTag.CROP_BEANS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_CHILIBEANS.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.RAW_MEAT), Ingredient.of(TagDC.ItemTag.CROP_ONION),
				Ingredient.of(TagDC.ItemTag.CROP_BEANS), Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CHILI)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.SOUP_SORREL.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.RAW_MEAT), Ingredient.of(TagDC.ItemTag.CROP_FLAVORED),
				Ingredient.of(TagDC.ItemTag.CROP_SORREL), Ingredient.of(TagDC.ItemTag.CREAMS), Ingredient.of(TagDC.ItemTag.CROP_HERBS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_BORSCH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.RAW_MEAT), Ingredient.of(TagDC.ItemTag.CROP_FLAVORED),
				Ingredient.of(Tags.Items.CROPS_BEETROOT), Ingredient.of(TagDC.ItemTag.CREAMS), Ingredient.of(TagDC.ItemTag.CROP_HERBS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_CONSOMME_VEGETABLE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.CROP_ONION), Ingredient.of(Tags.Items.CROPS_CARROT),
				Ingredient.of(Tags.Items.CROPS_POTATO), Ingredient.of(TagDC.ItemTag.CROP_CELERY), Ingredient.of(TagDC.ItemTag.CROP_HERBS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_IRISH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.RAW_MUTTON), Ingredient.of(Tags.Items.CROPS_POTATO),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS), Ingredient.of(Tags.Items.CROPS_CARROT), Ingredient.of(TagDC.ItemTag.CROP_HERBS)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_IRISH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.RAW_BEEF), Ingredient.of(Tags.Items.CROPS_POTATO),
				Ingredient.of(TagDC.ItemTag.CROP_ONIONS), Ingredient.of(Tags.Items.CROPS_CARROT), Ingredient.of(TagDC.ItemTag.CROP_HERBS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_CREAM_MUSHROOM.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_CEREALS),
				Ingredient.of(Tags.Items.CROPS_CARROT), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(TagDC.ItemTag.CROP_FLAVORED)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_CREAM_SALMON.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_CEREALS),
				Ingredient.of(TagDC.ItemTag.RAW_SALMON), Ingredient.of(TagDC.ItemTag.CROP_SPINACH), Ingredient.of(TagDC.ItemTag.CROP_FLAVORED)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_CREAM_SHRIMP.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.DUST_CEREALS),
				Ingredient.of(TagDC.ItemTag.PRAWN), Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS), Ingredient.of(TagDC.ItemTag.CROP_FLAVORED)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_CULLEN_SKINK.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MILKS), Ingredient.of(TagDC.ItemTag.RAW_COD),
				Ingredient.of(Tags.Items.CROPS_POTATO), Ingredient.of(TagDC.ItemTag.CROP_ONIONS), Ingredient.of(TagDC.ItemTag.FOOD_FAT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_KHARCHO.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.RAW_BEEF), Ingredient.of(TagDC.ItemTag.CROP_TOMATO),
				Ingredient.of(TagDC.ItemTag.CROP_PLUM), Ingredient.of(TagDC.ItemTag.CROP_WALNUT), Ingredient.of(TagDC.ItemTag.SEED_APIUM)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_TOMYUMGOONG.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(FoodInit.FOOD_TOMYUM_PASTE.get()), Ingredient.of(TagDC.ItemTag.PRAWN), Ingredient.of(Tags.Items.MUSHROOMS),
				Ingredient.of(TagDC.ItemTag.COCONUT_MILK), Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CORIANDER)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_TOMYUMPLA.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(FoodInit.FOOD_TOMYUM_PASTE.get()), Ingredient.of(TagDC.ItemTag.FISH_WHITE), Ingredient.of(Tags.Items.MUSHROOMS),
				Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CORIANDER)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_TOMYUMGAI.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(FoodInit.FOOD_TOMYUM_PASTE.get()), Ingredient.of(TagDC.ItemTag.RAW_CHICKEN), Ingredient.of(Tags.Items.MUSHROOMS),
				Ingredient.of(TagDC.ItemTag.COCONUT_MILK), Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CORIANDER)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_BAKKUTTEH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_PORK), Ingredient.of(TagDC.ItemTag.CROP_RADISH), Ingredient.of(TagDC.ItemTag.CROP_ONIONS),
				Ingredient.of(TagDC.ItemTag.CROP_GINGER), Ingredient.of(TagDC.ItemTag.SOYSAUCE), Ingredient.of(TagDC.ItemTag.CROP_CORIANDER)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_BAKKUTTEH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_PORK), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(TagDC.ItemTag.CROP_ONIONS),
				Ingredient.of(TagDC.ItemTag.CROP_GINGER), Ingredient.of(TagDC.ItemTag.CROP_SICHUAN_PEPPER), Ingredient.of(TagDC.ItemTag.CROP_CORIANDER)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_MISO_TOFU.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MISO), Ingredient.of(TagDC.ItemTag.TOFU),
				Ingredient.of(Items.DRIED_KELP), Ingredient.of(TagDC.ItemTag.CROP_CHIVES)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_MISO_TOFU.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MISO), Ingredient.of(TagDC.ItemTag.TOFU),
				Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS), Ingredient.of(TagDC.ItemTag.CROP_CHIVES)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_MISO_EGGPLANT.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MISO), Ingredient.of(TagDC.ItemTag.CROP_EGGPLANT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_MISO_MUSHROOM.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MISO), Ingredient.of(TagDC.ItemTag.TOFU),
				Ingredient.of(Tags.Items.MUSHROOMS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.COOKING, new ItemStack(FoodInit.STEW_MISO_PORK.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, heat, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.BASESOUP), Ingredient.of(TagDC.ItemTag.MISO), Ingredient.of(TagDC.ItemTag.RAW_PORK),
				Ingredient.of(Tags.Items.CROPS_CARROT), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(TagDC.ItemTag.CROP_RADISH)));

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

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.BASESOUP.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
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

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SALSA.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TOMATO), Ingredient.of(TagDC.ItemTag.CROP_CHILI), Ingredient.of(TagDC.ItemTag.CROP_ONION),
				Ingredient.of(TagDC.ItemTag.CROP_PAPRIKA), Ingredient.of(TagDC.ItemTag.CROP_HERBS), Ingredient.of(TagDC.ItemTag.CROP_LEMON)));

		// tea
		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_APPLE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_APPLE)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_MELON.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CANTALOUP)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_BERRY.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BERRY), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_GRAPE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RED_GRAPE)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_GRAPE_WHITE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WHITE_GRAPE)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_PLUM.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_PLUM), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_PEACH.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_PEACH)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_POMELO.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_POMELO), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_MANDARIN.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MANDARIN), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_LEMON.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LEMON), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_MANGO.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MANGO), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_GUAVA.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GUAVA), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_VEGETABLE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS), Ingredient.of(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE), Ingredient.of(TagDC.ItemTag.CROP_CITRUS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_MILK_SHAKE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(TagDC.ItemTag.SUGARS), Ingredient.of(TagDC.ItemTag.VANILLA_CURED)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.DRINK_TONIC.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, cool,
			ImmutableList.of(TagDC.FluidTag.SPARKLING.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_HERBS), Ingredient.of(TagDC.ItemTag.CROP_CITRUS), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_GREEN.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_GREEN)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_BLUE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_OOLONG)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_BLACK.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_BLACK)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_APPLE.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_BLACK), Ingredient.of(TagDC.ItemTag.CROP_APPLE)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_BERRY.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_BLACK), Ingredient.of(TagDC.ItemTag.CROP_BERRY)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_CHAI.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_BLACK), Ingredient.of(TagDC.ItemTag.CROP_CINNAMON), Ingredient.of(TagDC.ItemTag.CROP_CLOVE)));

		DeviceRecipeList.addCookingRecipe(2, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_CHAI.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.TEA_LEAVES_BLACK), Ingredient.of(TagDC.ItemTag.CROP_CARDAMOM), Ingredient.of(TagDC.ItemTag.CROP_CLOVE)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_COCOA.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_COCOA), Ingredient.of(TagDC.ItemTag.SUGARS)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_MINT.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MINT)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_MALLOW.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BLUE_MALLOW)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_SAFFRON.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SAFFRON)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_ROSEHIP.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RUGOSA), Ingredient.of(TagDC.ItemTag.CROP_DAMASCHENA)));

		DeviceRecipeList.addCookingRecipe(1, RecipeTypeDC.TEA, new ItemStack(FoodInit.TEA_HIBISCUS.get(), 3), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(FluidTags.WATER.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RUGOSA), Ingredient.of(TagDC.ItemTag.MALLOW_CALYCES)));

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

		DeviceRecipeList.addFermentationRecipe(1, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_YOGULT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.COW_MILK)));

		DeviceRecipeList.addFermentationRecipe(2, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.FOOD_YOGULT.get(), 6), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			ImmutableList.of(Tags.Fluids.MILK.location().toString()),
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.YOGULT)));

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

		DeviceRecipeList.addFermentationRecipe(3, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.OOLONG_TEA_LEAVES.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(FoodInit.GREEN_TEA_LEAVES.get())));

		DeviceRecipeList.addFermentationRecipe(3, RecipeTypeDC.FERMENTATION, new ItemStack(FoodInit.BLACK_TEA_LEAVES.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY,
			tags,
			ImmutableList.of(Ingredient.of(FoodInit.OOLONG_TEA_LEAVES.get())));

	}

}
