package defeatedcrow.hac.food.recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class FoodRecipeProvider extends RecipeProvider {

	public FoodRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> cons) {
		foodRecipes(cons);
		materialRecipes(cons);

		for (PlantRecipes.Wood wood : PlantRecipes.INSTANCE.woods) {
			woodRecipes(cons, wood);
		}

		for (PlantRecipes.Seeding seeding : PlantRecipes.INSTANCE.seedings) {
			seedingRecipes(cons, seeding);
		}

		mortarRecipes(cons);
		smeltingRecipes(cons);

		copperRecipes(cons);

		for (ContainerRecipes.Cont pair : ContainerRecipes.INSTANCE.pairs) {
			contRecipes(cons, pair);
		}

		SpecialRecipeBuilder.special((SimpleRecipeSerializer<?>) CoreInit.FLAVOR_SEREALIZER.get()).save(cons, "food_customize");

	}

	static void foodRecipes(Consumer<FinishedRecipe> cons) {

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_ROUND_RAW_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_BREAD_GRAINS))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.requires(Ingredient.of(TagDC.ItemTag.WATER))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.unlockedBy("has_bread_grains", has(TagDC.ItemTag.DUST_BREAD_GRAINS))
				.save(cons, "dcs_climate:food/bread_round");

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_SQUARE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DOUGH))
				.requires(Ingredient.of(Items.PAPER))
				.unlockedBy("has_dough", has(TagDC.ItemTag.DOUGH))
				.save(cons, "dcs_climate:food/bread_square");

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_NUTS_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DOUGH))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_FRUITS))
				.unlockedBy("has_dough", has(TagDC.ItemTag.DOUGH))
				.save(cons, "dcs_climate:food/bread_nuts");

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_CINNAMON_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DOUGH))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CINNAMON))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.unlockedBy("has_dough", has(TagDC.ItemTag.DOUGH))
				.save(cons, "dcs_climate:food/bread_cinnamon");

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_ANKO_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DOUGH))
				.requires(Ingredient.of(TagDC.ItemTag.ANKO))
				.unlockedBy("has_dough", has(TagDC.ItemTag.DOUGH))
				.save(cons, "dcs_climate:food/bread_anko");

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_CREAM_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DOUGH))
				.requires(Ingredient.of(TagDC.ItemTag.CUSTARD))
				.unlockedBy("has_dough", has(TagDC.ItemTag.DOUGH))
				.save(cons, "dcs_climate:food/bread_cream");

		ShapelessRecipeBuilder.shapeless(FoodInit.BREAD_SAUSAGE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DOUGH))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_SAUSAGE))
				.unlockedBy("has_dough", has(TagDC.ItemTag.DOUGH))
				.save(cons, "dcs_climate:food/bread_sausage");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_FRUIT_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.JAM))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_fruit_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_MARMALADE_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.MARMALADE))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_marmalade_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_EGG_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_egg_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALAD_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_salad_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALAD_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PLANT_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_salad_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALMON_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_SALMON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_salmon_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALMON_ITEM.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_SALMON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_AVOCADO))
				.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
				.save(cons, "dcs_climate:food/sandwich_salmon_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CAKE_BUTTER.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_BREAD_GRAINS))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.BUTTER))
				.unlockedBy("has_bread_grains", has(TagDC.ItemTag.DUST_BREAD_GRAINS))
				.save(cons, "dcs_climate:food/cake_butter_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.CAKE_BERRY.get(), 1)
				.requires(Ingredient.of(FoodInit.CAKE_BUTTER.get()))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BERRY))
				.requires(Ingredient.of(TagDC.ItemTag.CREAM))
				.unlockedBy("has_cake", has(FoodInit.CAKE_BUTTER.get()))
				.save(cons, "dcs_climate:food/cake_berry_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.CAKE_CHOCOLATE.get(), 1)
				.requires(Ingredient.of(FoodInit.CAKE_BUTTER.get()))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_COCOA))
				.requires(Ingredient.of(TagDC.ItemTag.CREAM))
				.unlockedBy("has_cake", has(FoodInit.CAKE_BUTTER.get()))
				.save(cons, "dcs_climate:food/cake_chocolate_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.CAKE_GREENTEA.get(), 1)
				.requires(Ingredient.of(FoodInit.CAKE_BUTTER.get()))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TEA))
				.requires(Ingredient.of(TagDC.ItemTag.CREAM))
				.unlockedBy("has_cake", has(FoodInit.CAKE_BUTTER.get()))
				.save(cons, "dcs_climate:food/cake_greemtea_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_BEEF_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_BEEF))
				.unlockedBy("has_beef", has(TagDC.ItemTag.RAW_BEEF))
				.save(cons, "dcs_climate:food/stick_beef");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_PORK_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PORK))
				.unlockedBy("has_pork", has(TagDC.ItemTag.RAW_PORK))
				.save(cons, "dcs_climate:food/stick_pork");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_MUTTON_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_MUTTON))
				.unlockedBy("has_mutton", has(TagDC.ItemTag.RAW_MUTTON))
				.save(cons, "dcs_climate:food/stick_mutton");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_CHICKEN_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_CHICKEN))
				.unlockedBy("has_chicken", has(TagDC.ItemTag.RAW_CHICKEN))
				.save(cons, "dcs_climate:food/stick_chicken");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_OFFAL_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.OFFAL))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_SPICES))
				.unlockedBy("has_offal", has(TagDC.ItemTag.OFFAL))
				.save(cons, "dcs_climate:food/stick_offal");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_FISH_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_EDIBLE_FISH))
				.unlockedBy("has_edible_fish", has(TagDC.ItemTag.RAW_EDIBLE_FISH))
				.save(cons, "dcs_climate:food/stick_fish");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_CORN_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CORN))
				.unlockedBy("has_corn", has(TagDC.ItemTag.CROP_CORN))
				.save(cons, "dcs_climate:food/stick_corn");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_VEGI_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BELL))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PUMPKIN))
				.unlockedBy("has_bellpepper", has(TagDC.ItemTag.CROP_BELL))
				.save(cons, "dcs_climate:food/stick_vegi_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_VEGI_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PAPRIKA))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PUMPKIN))
				.unlockedBy("has_paprika", has(TagDC.ItemTag.CROP_PAPRIKA))
				.save(cons, "dcs_climate:food/stick_vegi_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.STICK_FISH_RAW.get(), 1)
				.requires(Ingredient.of(Tags.Items.RODS_WOODEN))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_EDIBLE_FISH))
				.unlockedBy("has_edible_fish", has(TagDC.ItemTag.RAW_EDIBLE_FISH))
				.save(cons, "dcs_climate:food/stick_fish");

		ShapelessRecipeBuilder.shapeless(FoodInit.SWEETPOTATO_RAW.get(), 1)
				.requires(Ingredient.of(Items.PAPER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_SWEET_POTATO))
				.unlockedBy("has_sweetpotato", has(TagDC.ItemTag.CROP_SWEET_POTATO))
				.save(cons, "dcs_climate:food/cooked_sweetpotato_raw");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_BEEF_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_BEEF))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_beef", has(TagDC.ItemTag.RAW_BEEF))
				.save(cons, "dcs_climate:food/plate_steak_beef");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_MEAT_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PORK))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_pork", has(TagDC.ItemTag.RAW_PORK))
				.save(cons, "dcs_climate:food/plate_meat_pork");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_MEAT_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_MUTTON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_mutton", has(TagDC.ItemTag.RAW_MUTTON))
				.save(cons, "dcs_climate:food/plate_meat_mutton");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_MEAT_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PLANT_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_plant_meat", has(TagDC.ItemTag.RAW_PLANT_MEAT))
				.save(cons, "dcs_climate:food/plate_meat_plant");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_LEGS_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_CHICKEN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_chicken", has(TagDC.ItemTag.RAW_CHICKEN))
				.save(cons, "dcs_climate:food/plate_legs_chicken");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_LEGS_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_RABBIT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_rabbit", has(TagDC.ItemTag.RAW_RABBIT))
				.save(cons, "dcs_climate:food/plate_legs_rabbit");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_LEGS_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.FROG))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_frog", has(TagDC.ItemTag.FROG))
				.save(cons, "dcs_climate:food/plate_legs_frog");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_GARLIC_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_BEEF))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GARLIC))
				.requires(Ingredient.of(TagDC.ItemTag.BUTTER))
				.unlockedBy("has_garlic", has(TagDC.ItemTag.CROP_GARLIC))
				.save(cons, "dcs_climate:food/plate_big_steak");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_CHICKEN_BIG_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_CHICKEN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_SPICES))
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.unlockedBy("has_chicken", has(TagDC.ItemTag.RAW_CHICKEN))
				.save(cons, "dcs_climate:food/plate_big_chicken");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLATE_FISH_RAW.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_EDIBLE_FISH))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_VEGETABLES))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.unlockedBy("has_fish", has(TagDC.ItemTag.RAW_EDIBLE_FISH))
				.save(cons, "dcs_climate:food/plate_fish");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_APPLE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_APPLE))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CINNAMON))
				.requires(Ingredient.of(TagDC.ItemTag.CUSTARD))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_apple_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_PEACH_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PEACH))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_MINT))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(TagDC.ItemTag.VANILLA_CURED))
				.requires(Ingredient.of(TagDC.ItemTag.CUSTARD))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_peach_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_BERRY_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BERRY))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(TagDC.ItemTag.CUSTARD))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_berry_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_LEMON_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_LEMON))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_lemon_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_COCOA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_COCOA))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.CREAMS))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_cocoa_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_PISTACHIO_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PISTACHIO))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BERRY))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.CREAMS))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_pistachio_raw_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_PISTACHIO_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PISTACHIO))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CHERRY))
				.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.CREAMS))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_pistachio_raw_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_QUICHE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONION))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.CREAMS))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_quiche_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TART_QUICHE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTRY))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PLANT_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONION))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.CREAMS))
				.unlockedBy("has_pastry", has(TagDC.ItemTag.PASTRY))
				.save(cons, "dcs_climate:food/tart_quiche_raw_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_SHEPHERDS_PIE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.requires(Ingredient.of(Tags.Items.CROPS_CARROT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_MUTTON))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.MILKS))
				.requires(Ingredient.of(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.unlockedBy("has_meat_sauce", has(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_shepherds_pie_raw_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_SHEPHERDS_PIE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.requires(Ingredient.of(Tags.Items.CROPS_CARROT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_BEEF))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.MILKS))
				.requires(Ingredient.of(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.unlockedBy("has_meat_sauce", has(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_shepherds_pie_raw_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_SHEPHERDS_PIE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.requires(Ingredient.of(Tags.Items.CROPS_CARROT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PLANT_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(TagDC.ItemTag.MILKS))
				.requires(Ingredient.of(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.unlockedBy("has_meat_sauce", has(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_shepherds_pie_raw_3");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_GRATIN_SHRIMP_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PASTA))
				.requires(Ingredient.of(TagDC.ItemTag.PRAWN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(TagDC.ItemTag.CHEESE))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.unlockedBy("has_bechamel_sauce", has(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_gratin_shrimp_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_DORIA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_RICES))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_SAFFRON))
				.requires(Ingredient.of(TagDC.ItemTag.CHEESE))
				.requires(Ingredient.of(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.requires(Ingredient.of(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.unlockedBy("has_meat_sauce", has(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_doria_raw_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_DORIA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_RICES))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TURMERIC))
				.requires(Ingredient.of(TagDC.ItemTag.CHEESE))
				.requires(Ingredient.of(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.requires(Ingredient.of(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.unlockedBy("has_meat_sauce", has(FoodInit.FOOD_BOLOGNESE_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_doria_raw_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_MOUSSAKA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EGGPLANT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.unlockedBy("has_bechamel_sauce", has(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_moussaka_raw_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_MOUSSAKA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EGGPLANT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_PLANT_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.unlockedBy("has_bechamel_sauce", has(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_moussaka_raw_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_MOUSSAKA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EGGPLANT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.unlockedBy("has_bechamel_sauce", has(FoodInit.FOOD_BECHAMEL_SAUCE.get()))
				.save(cons, "dcs_climate:food/casserole_moussaka_raw_3");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_PARMIGIANA_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EGGPLANT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GARLIC))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BASIL))
				.requires(Ingredient.of(TagDC.ItemTag.CHEESE))
				.unlockedBy("has_eggplant", has(TagDC.ItemTag.CROP_EGGPLANT))
				.save(cons, "dcs_climate:food/casserole_parmigiana_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.CASSEROLE_JANSSONS_FRESTELESE_RAW_ITEM.get(), 1)
				.requires(Ingredient.of(FoodInit.FOOD_ANCHOVY.get()))
				.requires(Ingredient.of(Tags.Items.CROPS_POTATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONION))
				.requires(Ingredient.of(TagDC.ItemTag.CREAMS))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.unlockedBy("has_anchovy", has(FoodInit.FOOD_ANCHOVY.get()))
				.save(cons, "dcs_climate:food/casserole_janssons_frestelese_raw_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.MUESLI.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.MILKS))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_OAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_FRUITS))
				.unlockedBy("has_oat", has(TagDC.ItemTag.CROP_OAT))
				.save(cons, "dcs_climate:food/muesli_oat");

		ShapelessRecipeBuilder.shapeless(FoodInit.RICE_NAPA.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NAPA))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/nameshi_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.RICE_FISH.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_FISH))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GINGER))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/tuna_and_ginger_rice_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.CHAZUKE_UME.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(FoodInit.TEA_GREEN.get()))
				.requires(Ingredient.of(FoodInit.FOOD_UMEBOSHI.get()))
				.requires(Ingredient.of(Items.DRIED_KELP))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/ume_chazuke_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CHAZUKE_UME.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(FoodInit.TEA_GREEN.get()))
				.requires(Ingredient.of(FoodInit.FOOD_UMEBOSHI.get()))
				.requires(Ingredient.of(FoodInit.FOOD_TSUKEMONO.get()))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/ume_chazuke_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CHAZUKE_SAKE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(FoodInit.TEA_GREEN.get()))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_SALMON))
				.requires(Ingredient.of(Items.DRIED_KELP))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/sake_chazuke_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CHAZUKE_SAKE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(FoodInit.TEA_GREEN.get()))
				.requires(Ingredient.of(TagDC.ItemTag.COOKED_SALMON))
				.requires(Ingredient.of(FoodInit.FOOD_TSUKEMONO.get()))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/sake_chazuke_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CHAZUKE_TARAKO.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(FoodInit.TEA_GREEN.get()))
				.requires(Ingredient.of(TagDC.ItemTag.ROE))
				.requires(Ingredient.of(Items.DRIED_KELP))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/tarako_chazuke_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CHAZUKE_TARAKO.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.requires(Ingredient.of(FoodInit.TEA_GREEN.get()))
				.requires(Ingredient.of(TagDC.ItemTag.ROE))
				.requires(Ingredient.of(FoodInit.FOOD_TSUKEMONO.get()))
				.unlockedBy("has_boiled_rice", has(TagDC.ItemTag.BOILED_RICE))
				.save(cons, "dcs_climate:food/tarako_chazuke_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SOUP_GASPACHO.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CUCUMBER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PAPRIKA))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GARLIC))
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.unlockedBy("has_cucumber", has(TagDC.ItemTag.CROP_CUCUMBER))
				.save(cons, "dcs_climate:food/soup_gaspacho_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SOUP_GASPACHO.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CUCUMBER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BELL))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GARLIC))
				.requires(Ingredient.of(TagDC.ItemTag.BREAD))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.unlockedBy("has_cucumber", has(TagDC.ItemTag.CROP_CUCUMBER))
				.save(cons, "dcs_climate:food/soup_gaspacho_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SOUP_TARATOR.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.YOGULT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CUCUMBER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_WALNUT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GARLIC))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.unlockedBy("has_yogult", has(TagDC.ItemTag.YOGULT))
				.save(cons, "dcs_climate:food/soup_tarator_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.LARGE_BOWL_CARPACCIO.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_BEEF))
				.requires(Ingredient.of(TagDC.ItemTag.CHEESE))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.unlockedBy("has_beef", has(TagDC.ItemTag.RAW_BEEF))
				.save(cons, "dcs_climate:food/large_bowl_carpaccio_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.LARGE_BOWL_FISH_CARPACCIO.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_WHITE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.unlockedBy("has_white_fish", has(TagDC.ItemTag.FISH_WHITE))
				.save(cons, "dcs_climate:food/large_bowl_fish_carpaccio_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.LARGE_BOWL_CAPRESE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.requires(Ingredient.of(TagDC.ItemTag.CHEESE))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BASIL))
				.unlockedBy("has_tomato", has(TagDC.ItemTag.CROP_TOMATO))
				.save(cons, "dcs_climate:food/large_bowl_caprese_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_WHITE.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_WHITE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.unlockedBy("has_white_fish", has(TagDC.ItemTag.FISH_WHITE))
				.save(cons, "dcs_climate:food/sashimi_white_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_WHITE.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_WHITE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PERILLA))
				.unlockedBy("has_white_fish", has(TagDC.ItemTag.FISH_WHITE))
				.save(cons, "dcs_climate:food/sashimi_white_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_SALMON.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_SALMON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.unlockedBy("has_salmon_fish", has(TagDC.ItemTag.RAW_SALMON))
				.save(cons, "dcs_climate:food/sashimi_salmon_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_SALMON.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_SALMON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PERILLA))
				.unlockedBy("has_salmon_fish", has(TagDC.ItemTag.RAW_SALMON))
				.save(cons, "dcs_climate:food/sashimi_salmon_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_BLUE.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_BLUE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.unlockedBy("has_blue_fish", has(TagDC.ItemTag.FISH_BLUE))
				.save(cons, "dcs_climate:food/sashimi_blue_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_BLUE.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_BLUE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PERILLA))
				.unlockedBy("has_blue_fish", has(TagDC.ItemTag.FISH_BLUE))
				.save(cons, "dcs_climate:food/sashimi_blue_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_TUNA.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.TUNA))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.unlockedBy("has_tuna_fish", has(TagDC.ItemTag.TUNA))
				.save(cons, "dcs_climate:food/sashimi_tuna_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_TUNA.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.TUNA))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PERILLA))
				.unlockedBy("has_tuna_fish", has(TagDC.ItemTag.TUNA))
				.save(cons, "dcs_climate:food/sashimi_tuna_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_SQUID.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.SQUID))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.unlockedBy("has_squid", has(TagDC.ItemTag.SQUID))
				.save(cons, "dcs_climate:food/sashimi_squid_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SASHIMI_SQUID.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.SQUID))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_PERILLA))
				.unlockedBy("has_squid", has(TagDC.ItemTag.SQUID))
				.save(cons, "dcs_climate:food/sashimi_squid_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.CURRY_RICE.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.HAC_CURRY))
				.requires(Ingredient.of(TagDC.ItemTag.BOILED_RICE))
				.unlockedBy("has_curry", has(TagDC.ItemTag.HAC_CURRY))
				.save(cons, "dcs_climate:food/curry_rice_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_GREEN.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.save(cons, "dcs_climate:food/salad_green_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_POTATO.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(Items.BAKED_POTATO))
				.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.save(cons, "dcs_climate:food/salad_potato_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_NUTS.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BEANS))
				.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.save(cons, "dcs_climate:food/salad_nuts_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_MELON.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(Items.MELON_SLICE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
				.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.save(cons, "dcs_climate:food/salad_melon_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_SALMON.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(TagDC.ItemTag.RAW_SALMON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.save(cons, "dcs_climate:food/salad_salmon_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_TOFU.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.requires(Ingredient.of(FoodInit.FOOD_TOFU.get()))
				.requires(Ingredient.of(Items.DRIED_KELP))
				.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
				.save(cons, "dcs_climate:food/salad_tofu_0");

		// vanilla

		ShapelessRecipeBuilder.shapeless(Items.PACKED_MUD, 1)
				.requires(Ingredient.of(Items.MUD))
				.requires(Ingredient.of(TagDC.ItemTag.FEED_STRAW))
				.unlockedBy("has_straw", has(TagDC.ItemTag.FEED_STRAW))
				.save(cons, "dcs_climate:food/packed_mud_from_straw");

		ShapelessRecipeBuilder.shapeless(Items.PACKED_MUD, 1)
				.requires(Ingredient.of(Items.MUD))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_PLANT))
				.unlockedBy("has_dust_plant", has(TagDC.ItemTag.DUST_PLANT))
				.save(cons, "dcs_climate:food/packed_mud_from_dust");

		ShapedRecipeBuilder.shaped(Items.CAKE, 1)
				.pattern("XXX")
				.pattern("ZYZ")
				.pattern("WWW")
				.define('X', TagDC.ItemTag.MILKS)
				.define('Y', Tags.Items.EGGS)
				.define('Z', TagDC.ItemTag.SUGARS)
				.define('W', TagDC.ItemTag.DUST_WHEAT)
				.unlockedBy("has_dust_wheat", has(TagDC.ItemTag.DUST_WHEAT))
				.save(cons, "dcs_climate:core/cake_hac_recipe");

		ShapedRecipeBuilder.shaped(Items.COOKIE, 4)
				.pattern("XYX")
				.define('X', TagDC.ItemTag.DUST_WHEAT)
				.define('Y', TagDC.ItemTag.CROP_COCOA)
				.unlockedBy("has_dust_wheat", has(TagDC.ItemTag.DUST_WHEAT))
				.save(cons, "dcs_climate:core/cookie_hac_recipe");

		// drink
		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_APPLE_SODA.get(), 1)
				.requires(FoodInit.DRINK_APPLE.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_apple_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_BERRY_SODA.get(), 1)
				.requires(FoodInit.DRINK_BERRY.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_berry_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_MELON_SODA.get(), 1)
				.requires(FoodInit.DRINK_MELON.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_melon_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_GRAPE_SODA.get(), 1)
				.requires(FoodInit.DRINK_GRAPE.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_grape_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_GRAPE_WHITE_SODA.get(), 1)
				.requires(FoodInit.DRINK_GRAPE_WHITE.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_grape_white_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_PLUM_SODA.get(), 1)
				.requires(FoodInit.DRINK_PLUM.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_plum_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_PEACH_SODA.get(), 1)
				.requires(FoodInit.DRINK_PEACH.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_peach_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_GUAVA_SODA.get(), 1)
				.requires(FoodInit.DRINK_GUAVA.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_guava_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_MANGO_SODA.get(), 1)
				.requires(FoodInit.DRINK_MANGO.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_mango_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_CITRUS_SODA.get(), 1)
				.requires(FoodInit.DRINK_POMELO.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_citrus_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_CITRUS_SODA.get(), 1)
				.requires(FoodInit.DRINK_MANDARIN.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_citrus_soda_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_CITRUS_SODA.get(), 1)
				.requires(FoodInit.DRINK_LEMON.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_citrus_soda_3");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_TEA_SODA.get(), 1)
				.requires(FoodInit.TEA_BLACK.get())
				.requires(TagDC.ItemTag.SPARKLING)
				.group("drink_craft")
				.unlockedBy("has_sparkling", has(TagDC.ItemTag.SPARKLING))
				.save(cons, "dcs_climate:core/drink_tea_soda_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_LASSI_PLANE.get(), 1)
				.requires(TagDC.ItemTag.YOGULT)
				.requires(TagDC.ItemTag.WATER)
				.requires(TagDC.ItemTag.SUGARS)
				.group("drink_craft")
				.unlockedBy("has_yogult", has(TagDC.ItemTag.YOGULT))
				.save(cons, "dcs_climate:core/drink_lassi_plane_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_LASSI_PLANE.get(), 1)
				.requires(TagDC.ItemTag.YOGULT)
				.requires(TagDC.ItemTag.WATER)
				.requires(TagDC.ItemTag.DUST_SALT)
				.group("drink_craft")
				.unlockedBy("has_yogult", has(TagDC.ItemTag.YOGULT))
				.save(cons, "dcs_climate:core/drink_lassi_plane_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_LASSI_MANGO.get(), 1)
				.requires(TagDC.ItemTag.YOGULT)
				.requires(TagDC.ItemTag.WATER)
				.requires(TagDC.ItemTag.CROP_MANGO)
				.group("drink_craft")
				.unlockedBy("has_yogult", has(TagDC.ItemTag.YOGULT))
				.save(cons, "dcs_climate:core/drink_lassi_plane_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.DRINK_LASSI_CITRUS.get(), 1)
				.requires(TagDC.ItemTag.YOGULT)
				.requires(TagDC.ItemTag.WATER)
				.requires(TagDC.ItemTag.CROP_CITRUS)
				.group("drink_craft")
				.unlockedBy("has_yogult", has(TagDC.ItemTag.YOGULT))
				.save(cons, "dcs_climate:core/drink_lassi_citrus_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_BLACK_LEMON.get(), 1)
				.requires(FoodInit.TEA_BLACK.get())
				.requires(TagDC.ItemTag.CROP_LEMON)
				.group("drink_craft")
				.unlockedBy("has_lemon", has(TagDC.ItemTag.CROP_LEMON))
				.save(cons, "dcs_climate:core/drink_tea_lemon_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_MALLOW_LEMON.get(), 1)
				.requires(FoodInit.TEA_MALLOW.get())
				.requires(TagDC.ItemTag.CROP_LEMON)
				.group("drink_craft")
				.unlockedBy("has_lemon", has(TagDC.ItemTag.CROP_LEMON))
				.save(cons, "dcs_climate:core/drink_mallow_lemon_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_BLACK_MILK.get(), 1)
				.requires(FoodInit.TEA_BLACK.get())
				.requires(TagDC.ItemTag.MILKS)
				.group("drink_craft")
				.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
				.save(cons, "dcs_climate:core/drink_tea_milk_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_APPLE_MILK.get(), 1)
				.requires(FoodInit.TEA_APPLE.get())
				.requires(TagDC.ItemTag.MILKS)
				.group("drink_craft")
				.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
				.save(cons, "dcs_climate:core/drink_tea_apple_milk_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_BERRY_MILK.get(), 1)
				.requires(FoodInit.TEA_BERRY.get())
				.requires(TagDC.ItemTag.MILKS)
				.group("drink_craft")
				.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
				.save(cons, "dcs_climate:core/drink_tea_berry_milk_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_GREEN_MILK.get(), 1)
				.requires(FoodInit.TEA_GREEN.get())
				.requires(TagDC.ItemTag.MILKS)
				.group("drink_craft")
				.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
				.save(cons, "dcs_climate:core/drink_tea_green_milk_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_BLUE_MILK.get(), 1)
				.requires(FoodInit.TEA_BLUE.get())
				.requires(TagDC.ItemTag.MILKS)
				.group("drink_craft")
				.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
				.save(cons, "dcs_climate:core/drink_tea_oolomg_milk_0");

		ShapelessRecipeBuilder.shapeless(FoodInit.TEA_COCOA_MILK.get(), 1)
				.requires(FoodInit.TEA_COCOA.get())
				.requires(TagDC.ItemTag.MILKS)
				.group("drink_craft")
				.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
				.save(cons, "dcs_climate:core/drink_cocoa_milk_0");
	}

	static void mortarRecipes(Consumer<FinishedRecipe> cons) {
		// mortar

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_WHEAT.get(), 1)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT))
				.save(cons, "dcs_climate:core/mortar_wheat");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_HONEY.get(), 1)
				.requires(Items.HONEYCOMB)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_honey", has(Items.HONEYCOMB))
				.save(cons, "dcs_climate:core/mortar_honey");

		ShapelessRecipeBuilder.shapeless(FoodInit.CROP_CN_CINNAMON.get(), 1)
				.requires(FoodInit.LOG_CN_CINNAMON.get())
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_log_cinnamon", has(FoodInit.LOG_CN_CINNAMON.get()))
				.save(cons, "dcs_climate:core/mortar_cinnamon");

		ShapelessRecipeBuilder.shapeless(FoodInit.FIBER_WOOD.get(), 3)
				.requires(FoodInit.LOG_MR_PAPER.get())
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_log_paper", has(FoodInit.LOG_MR_PAPER.get()))
				.save(cons, "dcs_climate:core/mortar_fiber_wood");

		// spindle

		ShapedRecipeBuilder.shaped(FoodInit.STRING_PLANT.get(), 4)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.FIBER_PLANT)
				.define('Y', CoreInit.HAND_SPINDLE.get())
				.group("hand_spindle")
				.unlockedBy("has_fiber_plant", has(TagDC.ItemTag.FIBER_PLANT))
				.save(cons, "dcs_climate:core/spindle_string_plant");

		ShapedRecipeBuilder.shaped(FoodInit.STRING_TREE.get(), 4)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.FIBER_WOOD)
				.define('Y', CoreInit.HAND_SPINDLE.get())
				.group("hand_spindle")
				.unlockedBy("has_fiber_wood", has(TagDC.ItemTag.FIBER_WOOD))
				.save(cons, "dcs_climate:core/spindle_string_wood");

		ShapedRecipeBuilder.shaped(FoodInit.STRING_COTTON.get(), 4)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.CROP_COTTON)
				.define('Y', CoreInit.HAND_SPINDLE.get())
				.group("hand_spindle")
				.unlockedBy("has_cotton", has(TagDC.ItemTag.CROP_COTTON))
				.save(cons, "dcs_climate:core/spindle_string_cotton");

		ShapelessRecipeBuilder.shapeless(FoodInit.STRING_WOOL.get(), 2)
				.requires(ItemTags.WOOL)
				.requires(CoreInit.HAND_SPINDLE.get())
				.group("hand_spindle")
				.unlockedBy("has_wool", has(ItemTags.WOOL))
				.save(cons, "dcs_climate:core/spindle_string_wool");

		ShapelessRecipeBuilder.shapeless(Items.STRING, 1)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_string", has(Tags.Items.STRING))
				.save(cons, "dcs_climate:core/craft_string_hac");

		ShapelessRecipeBuilder.shapeless(Items.LEAD, 1)
				.requires(TagDC.ItemTag.VINE)
				.requires(TagDC.ItemTag.VINE)
				.requires(TagDC.ItemTag.VINE)
				.requires(CoreInit.HAND_SPINDLE.get())
				.group("hand_spindle")
				.unlockedBy("has_vine", has(TagDC.ItemTag.VINE))
				.save(cons, "dcs_climate:core/spindle_vanilla_lead");
	}

	static void materialRecipes(Consumer<FinishedRecipe> cons) {

		// material

		ShapelessRecipeBuilder.shapeless(FoodInit.FIBER_PLANT.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_REED))
				.unlockedBy("has_reed", has(TagDC.ItemTag.CROP_REED))
				.save(cons, "dcs_climate:food/reed_fiber_plant");

		ShapelessRecipeBuilder.shapeless(FoodInit.FIBER_PLANT.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_JUTE))
				.unlockedBy("has_jute", has(TagDC.ItemTag.CROP_JUTE))
				.save(cons, "dcs_climate:food/jute_fiber_plant");

		ShapelessRecipeBuilder.shapeless(FoodInit.FIBER_PLANT.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.WEED))
				.unlockedBy("has_weed", has(TagDC.ItemTag.WEED))
				.save(cons, "dcs_climate:food/jute_fiber_plant");

		ShapelessRecipeBuilder.shapeless(FoodInit.VINE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_BINDWEED))
				.unlockedBy("has_bindweed", has(TagDC.ItemTag.CROP_BINDWEED))
				.save(cons, "dcs_climate:food/bindweed_vine");

		ShapelessRecipeBuilder.shapeless(FoodInit.VINE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CLEMATIS))
				.unlockedBy("has_clematis", has(TagDC.ItemTag.CROP_BINDWEED))
				.save(cons, "dcs_climate:food/clematis_vine");

		ShapelessRecipeBuilder.shapeless(FoodInit.VINE.get(), 1)
				.requires(Ingredient.of(Items.VINE))
				.unlockedBy("has_vine", has(Items.VINE))
				.save(cons, "dcs_climate:food/vanilla_vine");

		ShapedRecipeBuilder.shaped(FoodInit.CLOTH_PLANT.get(), 1)
				.pattern(" X ")
				.pattern("X X")
				.pattern(" X ")
				.define('X', Ingredient.of(TagDC.ItemTag.STRING_PLANT))
				.unlockedBy("has_plant_string", has(TagDC.ItemTag.STRING_PLANT))
				.save(cons, "dcs_climate:core/cloth_plant");

		ShapedRecipeBuilder.shaped(FoodInit.CLOTH_COTTON.get(), 1)
				.pattern(" X ")
				.pattern("X X")
				.pattern(" X ")
				.define('X', Ingredient.of(TagDC.ItemTag.STRING_COTTON))
				.unlockedBy("has_cotton_string", has(TagDC.ItemTag.STRING_COTTON))
				.save(cons, "dcs_climate:core/cloth_cotton");

		ShapedRecipeBuilder.shaped(FoodInit.CLOTH_WOOL.get(), 1)
				.pattern(" X ")
				.pattern("X X")
				.pattern(" X ")
				.define('X', Ingredient.of(TagDC.ItemTag.STRING_WOOL))
				.unlockedBy("has_plant_string", has(TagDC.ItemTag.STRING_WOOL))
				.save(cons, "dcs_climate:core/cloth_wool");

		// misc

		ShapelessRecipeBuilder.shapeless(FoodInit.PLANK_CN_CAMPHOR.get(), 4)
				.requires(Ingredient.of(FoodInit.LOG_CN_CINNAMON.get()))
				.unlockedBy("has_cinnamon_log", has(FoodInit.LOG_CN_CINNAMON.get()))
				.save(cons, "dcs_climate:food/planks_camphor_from_cinnamon");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLANK_MR_MULBERRY.get(), 4)
				.requires(Ingredient.of(FoodInit.LOG_MR_PAPER.get()))
				.unlockedBy("has_kaji_log", has(FoodInit.LOG_MR_PAPER.get()))
				.save(cons, "dcs_climate:food/planks_morus_from_kaji");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLANK_MR_MULBERRY.get(), 4)
				.requires(Ingredient.of(FoodInit.LOG_MR_RUBBER.get()))
				.unlockedBy("has_rubber_log", has(FoodInit.LOG_MR_RUBBER.get()))
				.save(cons, "dcs_climate:food/planks_morus_from_rubber");

		ShapelessRecipeBuilder.shapeless(FoodInit.PLANK_SU_LACQUER.get(), 4)
				.requires(Ingredient.of(FoodInit.LOG_SU_MANGO.get()))
				.unlockedBy("has_mango_log", has(FoodInit.LOG_SU_MANGO.get()))
				.save(cons, "dcs_climate:food/planks_sumac_from_mango");

		ShapedRecipeBuilder.shaped(FoodInit.PLANK_RE_SORGHUM.get(), 1)
				.pattern("XX")
				.pattern("XX")
				.define('X', FoodInit.SORGHUM_STICK.get())
				.unlockedBy("has_stick_sorghum", has(FoodInit.SORGHUM_STICK.get()))
				.save(cons, "dcs_climate:build/planks_sorghum");

		ShapedRecipeBuilder.shaped(FoodInit.FOOD_EMPTY_PACK.get(), 16)
				.pattern(" X ")
				.pattern("X X")
				.pattern(" X ")
				.define('X', Items.PAPER)
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(cons, "dcs_climate:food/craft_empty_pack");

		ShapedRecipeBuilder.shaped(Items.PAPER, 4)
				.pattern("XXX")
				.define('X', TagDC.ItemTag.BAGASSE)
				.unlockedBy("has_bagasse", has(TagDC.ItemTag.BAGASSE))
				.save(cons, "dcs_climate:core/paper_hac_recipe");

		ShapedRecipeBuilder.shaped(Items.PAPER, 4)
				.pattern("XXX")
				.define('X', Ingredient.of(TagDC.ItemTag.FEED_STRAW))
				.unlockedBy("has_straw", has(TagDC.ItemTag.FEED_STRAW))
				.save(cons, "dcs_climate:core/paper_from_straw");

		ShapedRecipeBuilder.shaped(Items.PAPER, 4)
				.pattern("XXX")
				.define('X', Ingredient.of(TagDC.ItemTag.DUST_WOOD))
				.unlockedBy("has_dust_wood", has(TagDC.ItemTag.DUST_WOOD))
				.save(cons, "dcs_climate:core/paper_from_wood");

		ShapedRecipeBuilder.shaped(Items.PAPER, 4)
				.pattern("XXX")
				.define('X', Ingredient.of(TagDC.ItemTag.FIBER_WOOD))
				.unlockedBy("has_bark", has(TagDC.ItemTag.FIBER_WOOD))
				.save(cons, "dcs_climate:core/paper_from_bark");

		ShapedRecipeBuilder.shaped(FoodInit.PLANK_LACQUERWARE.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', ItemTags.PLANKS)
				.define('Y', TagDC.ItemTag.SAP_LACQUER)
				.unlockedBy("has_lacquer_sap", has(TagDC.ItemTag.SAP_LACQUER))
				.save(cons, "dcs_climate:food/lacquerware_planks");

		ShapedRecipeBuilder.shaped(Items.CANDLE, 1)
				.pattern("X")
				.pattern("Y")
				.define('X', Tags.Items.STRING)
				.define('Y', TagDC.ItemTag.FOOD_WAX)
				.unlockedBy("has_wax", has(TagDC.ItemTag.FOOD_WAX))
				.save(cons, "dcs_climate:food/vanilla_candle");

		// agri

		ShapelessRecipeBuilder.shapeless(FoodInit.FERTILIZER_MIXED.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_POWDER))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_ASH))
				.unlockedBy("has_fish_powder", has(TagDC.ItemTag.FISH_POWDER))
				.save(cons, "dcs_climate:food/craft_fertilizer_mixed");

		ShapelessRecipeBuilder.shapeless(FoodInit.FERTILIZER_MIXED.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PRESS_CAKE))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_ASH))
				.unlockedBy("has_press_cake", has(TagDC.ItemTag.PRESS_CAKE))
				.save(cons, "dcs_climate:food/craft_fertilizer_mixed_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.FERTILIZER_MIXED.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.LEAF_MOLD))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_ASH))
				.unlockedBy("has_leaf_mold", has(TagDC.ItemTag.LEAF_MOLD))
				.save(cons, "dcs_climate:food/craft_fertilizer_mixed3");

		ShapelessRecipeBuilder.shapeless(FoodInit.FERTILIZER_MIXED.get(), 4)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_NITER))
				.requires(Ingredient.of(TagDC.ItemTag.FISH_POWDER))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_ASH))
				.unlockedBy("has_dust_niter", has(TagDC.ItemTag.DUST_NITER))
				.save(cons, "dcs_climate:food/craft_fertilizer_mixed4");

		ShapelessRecipeBuilder.shapeless(FoodInit.BIOMASS_PELLET.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.RESIDUES))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_ALKALI))
				.unlockedBy("has_residues", has(TagDC.ItemTag.RESIDUES))
				.save(cons, "dcs_climate:food/craft_bio_briquet_raw");

		ShapelessRecipeBuilder.shapeless(Blocks.DIRT, 1)
				.requires(Ingredient.of(TagDC.ItemTag.LEAF_MOLD))
				.requires(Ingredient.of(ItemTags.SAND))
				.unlockedBy("has_leaf_mold", has(TagDC.ItemTag.LEAF_MOLD))
				.save(cons, "dcs_climate:core/dirt_another_0");

		ShapelessRecipeBuilder.shapeless(Items.GUNPOWDER, 1)
				.requires(TagDC.ItemTag.DUST_NITER)
				.requires(TagDC.ItemTag.DUST_NITER)
				.requires(TagDC.ItemTag.DUST_COAL)
				.requires(TagDC.ItemTag.DUST_SULFUR)
				.unlockedBy("has_dust_niter", has(TagDC.ItemTag.DUST_NITER))
				.save(cons, "dcs_climate:core/gunpowder_another_0");

		ShapedRecipeBuilder.shaped(FoodInit.FERTILE_PLANTER.get(), 1)
				.pattern("YXY")
				.pattern("YYY")
				.define('X', ItemTags.DIRT)
				.define('Y', Tags.Items.INGOTS_BRICK)
				.unlockedBy("has_bricks", has(Tags.Items.INGOTS_BRICK))
				.save(cons, "dcs_climate:food/fertile_planter_block_1");

		ShapedRecipeBuilder.shaped(FoodInit.FERTILE_PLANTER.get(), 1)
				.pattern("YXY")
				.pattern("YYY")
				.define('X', ItemTags.DIRT)
				.define('Y', ItemTags.TERRACOTTA)
				.unlockedBy("has_terracotta", has(ItemTags.TERRACOTTA))
				.save(cons, "dcs_climate:food/fertile_planter_block_2");

		ShapedRecipeBuilder.shaped(FoodInit.FERTILE_PLANTER_GEM.get(), 1)
				.pattern("YXY")
				.pattern("YYY")
				.define('X', ItemTags.DIRT)
				.define('Y', TagDC.ItemTag.GEM_AGATES)
				.unlockedBy("has_gem_agates", has(TagDC.ItemTag.GEM_AGATES))
				.save(cons, "dcs_climate:food/fertile_planter_gem_1");

		// food materials

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_PASTRY.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_WHEAT))
				.requires(Ingredient.of(TagDC.ItemTag.FOOD_FAT))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.unlockedBy("has_dust_wheat", has(TagDC.ItemTag.DUST_WHEAT))
				.save(cons, "dcs_climate:food/foodmaterial_pastry");

		ShapelessRecipeBuilder.shapeless(FoodInit.NOODLE_PASTA.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_WHEAT))
				.requires(Ingredient.of(TagDC.ItemTag.WATER))
				.unlockedBy("has_dust_wheat", has(TagDC.ItemTag.DUST_WHEAT))
				.save(cons, "dcs_climate:food/foodmaterial_pasta");

		ShapelessRecipeBuilder.shapeless(FoodInit.NOODLE_WHEAT.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_WHEAT))
				.requires(Ingredient.of(TagDC.ItemTag.WATER))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.unlockedBy("has_dust_wheat", has(TagDC.ItemTag.DUST_WHEAT))
				.save(cons, "dcs_climate:food/foodmaterial_wheat");

		ShapelessRecipeBuilder.shapeless(FoodInit.NOODLE_BRINE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_WHEAT))
				.requires(Ingredient.of(TagDC.ItemTag.WATER))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_TRONA))
				.unlockedBy("has_dust_wheat", has(TagDC.ItemTag.DUST_WHEAT))
				.save(cons, "dcs_climate:food/foodmaterial_chinese_noodle");

		ShapelessRecipeBuilder.shapeless(FoodInit.NOODLE_BUCKWHEAT.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_BUCKWHEAT))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_WHEAT))
				.requires(Ingredient.of(TagDC.ItemTag.WATER))
				.unlockedBy("has_dust_buckwheat", has(TagDC.ItemTag.DUST_BUCKWHEAT))
				.save(cons, "dcs_climate:food/foodmaterial_buckwheat_noodle");

		ShapelessRecipeBuilder.shapeless(FoodInit.NOODLE_RICE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_RICES))
				.requires(Ingredient.of(TagDC.ItemTag.WATER))
				.unlockedBy("has_dust_rices", has(TagDC.ItemTag.DUST_RICES))
				.save(cons, "dcs_climate:food/foodmaterial_rice_noodle");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_CREAM.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.COW_MILK))
				.unlockedBy("has_cow_milk", has(TagDC.ItemTag.COW_MILK))
				.save(cons, "dcs_climate:food/foodmaterial_cream");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_BUTTER.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CREAM))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.unlockedBy("has_cream", has(TagDC.ItemTag.CREAM))
				.save(cons, "dcs_climate:food/foodmaterial_butter");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MARGARINE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:food/foodmaterial_margarine");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MAKOMOTAKE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_WILD_RICE))
				.unlockedBy("has_zizania", has(TagDC.ItemTag.CROP_WILD_RICE))
				.save(cons, "dcs_climate:food/makomodake_zizania");

		ShapelessRecipeBuilder.shapeless(FoodInit.RAW_SAUSAGE.get(), 3)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_MEAT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.requires(Ingredient.of(TagDC.ItemTag.OFFAL))
				.unlockedBy("has_offal", has(TagDC.ItemTag.OFFAL))
				.save(cons, "dcs_climate:food/raw_sausage_meat");

		ShapelessRecipeBuilder.shapeless(FoodInit.RAW_SAUSAGE.get(), 2)
				.requires(Ingredient.of(TagDC.ItemTag.RAW_ROTTEN))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.requires(Ingredient.of(TagDC.ItemTag.OFFAL))
				.unlockedBy("has_offal", has(TagDC.ItemTag.OFFAL))
				.save(cons, "dcs_climate:food/raw_sausage_rotten");

		ShapelessRecipeBuilder.shapeless(FoodInit.RAW_SAUSAGE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.requires(Ingredient.of(TagDC.ItemTag.OFFAL))
				.unlockedBy("has_offal", has(TagDC.ItemTag.OFFAL))
				.save(cons, "dcs_climate:food/raw_sausage_offal");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_RENNET.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.OFFAL))
				.unlockedBy("has_offal", has(TagDC.ItemTag.OFFAL))
				.save(cons, "dcs_climate:food/foodmaterial_rennet");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_ROE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.FISH_WITH_ROE))
				.unlockedBy("has_fish_with_roe", has(TagDC.ItemTag.FISH_WITH_ROE))
				.save(cons, "dcs_climate:food/foodmaterial_fish_roe");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MAYONNAISE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.VINEGAR))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.unlockedBy("has_eggs", has(Tags.Items.EGGS))
				.save(cons, "dcs_climate:food/foodmaterial_mayonnaise");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MAYONNAISE.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_LEMON))
				.requires(Ingredient.of(TagDC.ItemTag.PLANT_OIL))
				.requires(Ingredient.of(Tags.Items.EGGS))
				.unlockedBy("has_eggs", has(Tags.Items.EGGS))
				.save(cons, "dcs_climate:food/foodmaterial_mayonnaise_2");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_TOMYUM_PASTE.get(), 1)
				.requires(Ingredient.of(FoodInit.FOOD_FISH_SAUSE.get()))
				.requires(Ingredient.of(FoodInit.FOOD_SHRIMP_PASTE.get()))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GINGER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CHILI))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.unlockedBy("has_shrimp_paste", has(FoodInit.FOOD_SHRIMP_PASTE.get()))
				.save(cons, "dcs_climate:food/foodmaterial_tomyum_paste");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_THAI_CURRY_PASTE.get(), 1)
				.requires(Ingredient.of(FoodInit.FOOD_SHRIMP_PASTE.get()))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CORIANDER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GINGER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CHILI))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_ONIONS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.unlockedBy("has_shrimp_paste", has(FoodInit.FOOD_SHRIMP_PASTE.get()))
				.save(cons, "dcs_climate:food/foodmaterial_thai_curry_paste");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_HERB_SALT.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_HERBS))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_SPICES))
				.unlockedBy("has_salt", has(TagDC.ItemTag.DUST_SALT))
				.save(cons, "dcs_climate:food/foodmaterial_herb_salt");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MIXED_SPICES.get(), 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CARDAMOM))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CINNAMON))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CLOVE))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_GINGER))
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CHILI))
				.requires(Ingredient.of(TagDC.ItemTag.SEED_APIUM))
				.requires(Ingredient.of(TagDC.ItemTag.SEED_FENNEL))
				.unlockedBy("has_clove", has(TagDC.ItemTag.CROP_CLOVE))
				.save(cons, "dcs_climate:food/foodmaterial_mixed_spice");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_SPARKLING.get(), 4)
				.requires(Ingredient.of(CoreInit.SPARKLING.getBucket().get()))
				.requires(Ingredient.of(TagDC.ItemTag.EMPTY_PACK))
				.requires(Ingredient.of(TagDC.ItemTag.EMPTY_PACK))
				.requires(Ingredient.of(TagDC.ItemTag.EMPTY_PACK))
				.requires(Ingredient.of(TagDC.ItemTag.EMPTY_PACK))
				.unlockedBy("has_empty_pack", has(TagDC.ItemTag.EMPTY_PACK))
				.save(cons, "dcs_climate:food/foodmaterial_pack_sparkling");

		// leaves
		ShapelessRecipeBuilder.shapeless(FoodInit.CONT_LEAVES.get(), 1)
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.requires(Ingredient.of(ItemTags.LEAVES))
				.unlockedBy("has_leaves", has(ItemTags.LEAVES))
				.save(cons, "dcs_climate:food/cont_leaves");

		ShapelessRecipeBuilder.shapeless(FoodInit.CONT_WASTE.get(), 1)
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(TagDC.ItemTag.BRAN))
				.unlockedBy("has_residues", has(TagDC.ItemTag.BRAN))
				.save(cons, "dcs_climate:food/cont_plant_wastes_1");

		ShapelessRecipeBuilder.shapeless(FoodInit.CONT_WASTE.get(), 1)
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(Tags.Items.CROPS))
				.requires(Ingredient.of(TagDC.ItemTag.PRESS_CAKE))
				.unlockedBy("has_residues", has(TagDC.ItemTag.PRESS_CAKE))
				.save(cons, "dcs_climate:food/cont_plant_wastes_2");

	}

	static void copperRecipes(Consumer<FinishedRecipe> cons) {
		ShapelessRecipeBuilder.shapeless(Blocks.COPPER_BLOCK, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.OXIDIZED_COPPER))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_block");

		ShapelessRecipeBuilder.shapeless(Blocks.CUT_COPPER, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.OXIDIZED_CUT_COPPER))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_cutted");

		ShapelessRecipeBuilder.shapeless(Blocks.CUT_COPPER_SLAB, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.OXIDIZED_CUT_COPPER_SLAB))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_slab");

		ShapelessRecipeBuilder.shapeless(Blocks.CUT_COPPER_STAIRS, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.OXIDIZED_CUT_COPPER_STAIRS))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_stair");

		ShapelessRecipeBuilder.shapeless(Blocks.WAXED_COPPER_BLOCK, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.WAXED_OXIDIZED_COPPER))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_block_waxed");

		ShapelessRecipeBuilder.shapeless(Blocks.WAXED_CUT_COPPER, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.WAXED_OXIDIZED_CUT_COPPER))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_cut_waxed");

		ShapelessRecipeBuilder.shapeless(Blocks.WAXED_CUT_COPPER_SLAB, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_slab_waxed");

		ShapelessRecipeBuilder.shapeless(Blocks.WAXED_CUT_COPPER_STAIRS, 1)
				.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
				.requires(Ingredient.of(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS))
				.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
				.save(cons, "dcs_climate:food/repair_oxidised_stairs_waxed");
	}

	private static void woodRecipes(Consumer<FinishedRecipe> cons, PlantRecipes.Wood wood) {

		if (wood.logBlock().get() != null) {
			ShapelessRecipeBuilder.shapeless(wood.plankBlock().get(), 4)
					.requires(wood.logBlock().get())
					.unlockedBy("has_" + wood.name() + "_log", has(wood.logBlock().get()))
					.save(cons, "dcs_climate:food/planks_" + wood.name());
		}

		ShapedRecipeBuilder.shaped(wood.stairsBlock().get(), 4)
				.pattern("X  ")
				.pattern("XX ")
				.pattern("XXX")
				.define('X', wood.plankBlock().get())
				.unlockedBy("has_" + wood.name() + "_planks", has(wood.plankBlock().get()))
				.save(cons, "dcs_climate:build/stairs2_" + wood.name());

		ShapedRecipeBuilder.shaped(wood.stairsBlock().get(), 4)
				.pattern("  X")
				.pattern(" XX")
				.pattern("XXX")
				.define('X', wood.plankBlock().get())
				.unlockedBy("has_" + wood.name() + "_planks", has(wood.plankBlock().get()))
				.save(cons, "dcs_climate:build/stairs3_" + wood.name());

		ShapedRecipeBuilder.shaped(wood.slabBlock().get(), 6)
				.pattern("XXX")
				.define('X', wood.plankBlock().get())
				.unlockedBy("has_" + wood.name() + "_planks", has(wood.plankBlock().get()))
				.save(cons, "dcs_climate:build/slab2_" + wood.name());

		ShapedRecipeBuilder.shaped(wood.fenceBlock().get(), 6)
				.pattern("XYX")
				.pattern("XYX")
				.define('X', wood.plankBlock().get())
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_" + wood.name() + "_planks", has(wood.plankBlock().get()))
				.save(cons, "dcs_climate:build/fence2_" + wood.name());

		if (wood.doorBlock().get() != null) {
			ShapedRecipeBuilder.shaped(wood.doorBlock().get(), 3)
					.pattern("XX")
					.pattern("XX")
					.pattern("XX")
					.define('X', wood.plankBlock().get())
					.unlockedBy("has_" + wood.name() + "_planks", has(wood.plankBlock().get()))
					.save(cons, "dcs_climate:build/door2_" + wood.name());
		}

		if (wood.trapdoorBlock().get() != null) {
			ShapedRecipeBuilder.shaped(wood.trapdoorBlock().get(), 3)
					.pattern("XXX")
					.pattern("XXX")
					.define('X', wood.plankBlock().get())
					.unlockedBy("has_" + wood.name() + "_planks", has(wood.plankBlock().get()))
					.save(cons, "dcs_climate:build/trapdoor2_" + wood.name());
		}

	}

	private static void seedingRecipes(Consumer<FinishedRecipe> cons, PlantRecipes.Seeding seed) {
		if (seed.output().get() instanceof ClimateCropBaseBlock) {
			String name = ((ClimateCropBaseBlock) seed.output().get()).getFamily().toString() + "_" + ((ClimateCropBaseBlock) seed.output().get()).getTier().toString();

			ShapelessRecipeBuilder.shapeless(seed.output().get(), seed.count())
					.requires(seed.input().get())
					.requires(CoreInit.SEEDING_POT.get())
					.group("seeding")
					.unlockedBy("has_seeding_pot", has(CoreInit.SEEDING_POT.get()))
					.save(cons, "dcs_climate:food/seeding/seeding_" + name);
		}
	}

	static void contRecipes(Consumer<FinishedRecipe> cons, ContainerRecipes.Cont pair) {
		if (pair.inputTag().get() != TagDC.ItemTag.DUMMY) {

			ShapedRecipeBuilder.shaped(pair.output().get(), 1)
					.pattern("XXX")
					.pattern("XXX")
					.pattern("XXX")
					.define('X', pair.inputTag().get())
					.unlockedBy("has_" + pair.name(), has(pair.inputTag().get()))
					.save(cons, "dcs_climate:container/cont_" + pair.name());

			ShapelessRecipeBuilder.shapeless(pair.input().get(), 9)
					.requires(pair.output().get())
					.unlockedBy("has_" + pair.name(), has(pair.inputTag().get()))
					.save(cons, "dcs_climate:container/cont_" + pair.name() + "_rev");
		} else {

			ShapedRecipeBuilder.shaped(pair.output().get(), 1)
					.pattern("XXX")
					.pattern("XXX")
					.pattern("XXX")
					.define('X', pair.input().get())
					.unlockedBy("has_" + pair.name(), has(pair.input().get()))
					.save(cons, "dcs_climate:container/cont_" + pair.name());

			ShapelessRecipeBuilder.shapeless(pair.input().get(), 9)
					.requires(pair.output().get())
					.unlockedBy("has_" + pair.name(), has(pair.input().get()))
					.save(cons, "dcs_climate:container/cont_" + pair.name() + "_rev");
		}
	}

	static void smeltingRecipes(Consumer<FinishedRecipe> cons) {
		cookingRecipe(cons, Ingredient.of(FoodInit.FOOD_WATER.get()), CoreInit.DUST_SALT.get(), 200, "dust_salt", FoodInit.FOOD_WATER.get(), "has_water");
		cookingRecipe(cons, Ingredient.of(TagDC.ItemTag.CROP_ALMOND), FoodInit.ALMOND_NUTS.get(), 200, "almond_nuts", FoodInit.CROP_CH_ALMOND.get(), "has_almond");
		cookingRecipe(cons, Ingredient.of(TagDC.ItemTag.CROP_CASHEW), FoodInit.CASHEW_NUTS.get(), 200, "cashew_nuts", FoodInit.CROP_SU_CASHEW.get(), "has_cashew");
		cookingRecipe(cons, Ingredient.of(TagDC.ItemTag.CROP_TEA), FoodInit.GREEN_TEA_LEAVES.get(), 200, "crop_tea", FoodInit.GREEN_TEA_LEAVES.get(), "has_crop_tea");
		cookingRecipe(cons, Ingredient.of(TagDC.ItemTag.PALM_FLOWER), FoodInit.FOOD_SYRUP.get(), 200, "palm_syrup", FoodInit.FOOD_PALM_FLOWER.get(), "has_flower_palm");
		cookingRecipe(cons, Ingredient.of(TagDC.ItemTag.SAP_SWEET), FoodInit.FOOD_SYRUP.get(), 200, "sap_syrup", FoodInit.SAP_SWEET.get(), "has_sweet_sap");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.CONT_LEAVES), FoodInit.DUST_ASH.get(), 200, "dust_ash1", FoodInit.CONT_LEAVES.get(), "has_cont_leaves");
		smokingRecipe(cons, Ingredient.of(TagDC.ItemTag.RAW_SAUSAGE), FoodInit.SMOKED_SAUSAGE.get(), 200, "smoked_sausage", FoodInit.RAW_SAUSAGE.get(), "has_raw_sausage");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.CONT_LOGS), FoodInit.CONT_LOG_CHARCOAL.get(), 200, "charcoal_container", TagDC.ItemTag.CONT_LOGS, "has_log_cont");
		smeltingRecipe(cons, Ingredient.of(FoodInit.BIOMASS_PELLET.get()), FoodInit.BIOMASS_BRIQUET.get(), 200, "biomass_briquet", FoodInit.BIOMASS_PELLET.get(), "has_biomass_pellet");
	}

	private static void cookingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, ItemLike output, int time, String name, ItemLike unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.campfireCooking(input, output, 0F, time * 3)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/campfire_" + name);

		SimpleCookingRecipeBuilder.smelting(input, output, 0F, time)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/smelting_" + name);
	}

	private static void smeltingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, ItemLike output, int time, String name, ItemLike unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.smelting(input, output, 0F, time)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/smelting_" + name);

		SimpleCookingRecipeBuilder.blasting(input, output, 0F, time / 2)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/blasting_" + name);
	}

	private static void smeltingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, ItemLike output, int time, String name, TagKey<Item> unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.smelting(input, output, 0F, time)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/smelting_" + name);

		SimpleCookingRecipeBuilder.blasting(input, output, 0F, time / 2)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/blasting_" + name);
	}

	private static void smokingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, ItemLike output, int time, String name, ItemLike unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.smoking(input, output, 0F, time)
				.unlockedBy(unlockName, has(unlockTarget))
				.save(cons, "dcs_climate:smelting/smoking_" + name);
	}

	@Override
	public void run(CachedOutput cache) {

		Set<ResourceLocation> set = Sets.newHashSet();
		buildCraftingRecipes((recipe) -> {
			if (!set.add(recipe.getId())) {
				// throw new IllegalStateException("Duplicate recipe " + recipe.getId());
			} else {
				saveRecipeMirror(cache, recipe.serializeRecipe(), this.recipePathProvider.json(recipe.getId()));
				JsonObject jsonobject = recipe.serializeAdvancement();
				if (jsonobject != null) {
					saveAdvancement(cache, jsonobject, this.advancementPathProvider.json(recipe.getAdvancementId()));
				}
			}
		});
	}

	private static void saveRecipeMirror(CachedOutput cach, JsonObject json, Path path) {
		try {
			DataProvider.saveStable(cach, json, path);
		} catch (IOException ioexception) {
			DCLogger.LOGGER.error("Couldn't save recipe {}", path, ioexception);
		}

	}

	protected void saveAdvancementMirror(CachedOutput cach, JsonObject json, Path path) {
		try {
			DataProvider.saveStable(cach, json, path);
		} catch (IOException ioexception) {
			DCLogger.LOGGER.error("Couldn't save recipe advancement {}", path, ioexception);
		}
	}

}
