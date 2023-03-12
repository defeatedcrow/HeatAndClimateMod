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
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
			.save(cons, "dcs_climate:food/sandwich_fruit1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_MARMALADE_ITEM.get(), 3)
			.requires(Ingredient.of(TagDC.ItemTag.BREAD))
			.requires(Ingredient.of(TagDC.ItemTag.MARMALADE))
			.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
			.save(cons, "dcs_climate:food/sandwich_marmalade1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_EGG_ITEM.get(), 3)
			.requires(Ingredient.of(TagDC.ItemTag.BREAD))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.requires(Ingredient.of(Tags.Items.EGGS))
			.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
			.save(cons, "dcs_climate:food/sandwich_egg1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALAD_ITEM.get(), 3)
			.requires(Ingredient.of(TagDC.ItemTag.BREAD))
			.requires(Ingredient.of(TagDC.ItemTag.COOKED_MEAT))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE))
			.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
			.save(cons, "dcs_climate:food/sandwich_salad1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALMON_ITEM.get(), 3)
			.requires(Ingredient.of(TagDC.ItemTag.BREAD))
			.requires(Ingredient.of(TagDC.ItemTag.COOKED_SALMON))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
			.save(cons, "dcs_climate:food/sandwich_salmon1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SANDWICH_SALMON_ITEM.get(), 3)
			.requires(Ingredient.of(TagDC.ItemTag.BREAD))
			.requires(Ingredient.of(TagDC.ItemTag.COOKED_SALMON))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_AVOCADO))
			.unlockedBy("has_bread", has(TagDC.ItemTag.BREAD))
			.save(cons, "dcs_climate:food/sandwich_salmon2");

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

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.WATER))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_OAT))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
			.unlockedBy("has_oat", has(TagDC.ItemTag.CROP_OAT))
			.save(cons, "dcs_climate:food/porridge_oat");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.WATER))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_MILLETS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
			.unlockedBy("has_millets", has(TagDC.ItemTag.CROP_MILLETS))
			.save(cons, "dcs_climate:food/porridge_millets");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.WATER))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_PSEUDOCEREALS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
			.unlockedBy("has_pseudocereals", has(TagDC.ItemTag.CROP_PSEUDOCEREALS))
			.save(cons, "dcs_climate:food/porridge_pseudocereals");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.WATER))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_RICES))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_SALT))
			.unlockedBy("has_rices", has(TagDC.ItemTag.DUST_RICES))
			.save(cons, "dcs_climate:food/porridge_rices");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE_MILK.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.MILKS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_OAT))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_oat", has(TagDC.ItemTag.CROP_OAT))
			.save(cons, "dcs_climate:food/porridge_milk_oat");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE_MILK.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.MILKS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_MILLETS))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_millets", has(TagDC.ItemTag.CROP_MILLETS))
			.save(cons, "dcs_climate:food/porridge_milk_millets");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE_MILK.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.MILKS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_PSEUDOCEREALS))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_pseudocereals", has(TagDC.ItemTag.CROP_PSEUDOCEREALS))
			.save(cons, "dcs_climate:food/porridge_milk_pseudocereals");

		ShapelessRecipeBuilder.shapeless(FoodInit.PORRIDGE_MILK.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.MILKS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_RICES))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_rices", has(TagDC.ItemTag.DUST_RICES))
			.save(cons, "dcs_climate:food/porridge_milk_rices");

		ShapelessRecipeBuilder.shapeless(FoodInit.MUESLI.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.MILKS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_OAT))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_FRUITS))
			.unlockedBy("has_oat", has(TagDC.ItemTag.CROP_OAT))
			.save(cons, "dcs_climate:food/muesli_oat");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_GREEN.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
			.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.save(cons, "dcs_climate:food/salad_green1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_POTATO.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.requires(Ingredient.of(Tags.Items.EGGS))
			.requires(Ingredient.of(Items.BAKED_POTATO))
			.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.save(cons, "dcs_climate:food/salad_potato1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_NUTS.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_NUTS))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_BEANS))
			.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.save(cons, "dcs_climate:food/salad_nuts1");

		ShapelessRecipeBuilder.shapeless(FoodInit.SALAD_MELON.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.requires(Ingredient.of(Items.MELON_SLICE))
			.requires(Ingredient.of(TagDC.ItemTag.CROP_TOMATO))
			.unlockedBy("has_green_leaves", has(TagDC.ItemTag.CROP_GREEN_LEAFS))
			.save(cons, "dcs_climate:food/salad_melon1");

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

		// misc

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

		// agri

		ShapelessRecipeBuilder.shapeless(FoodInit.FERTILIZER_MIXED.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.FISH_POWDER))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_ASH))
			.unlockedBy("has_fish_powder", has(TagDC.ItemTag.FISH_POWDER))
			.save(cons, "dcs_climate:food/craft_fertilizer_mixed");

		ShapelessRecipeBuilder.shapeless(FoodInit.FERTILIZER_MIXED.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.PRESS_CAKE))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_ASH))
			.unlockedBy("has_press_cake", has(TagDC.ItemTag.PRESS_CAKE))
			.save(cons, "dcs_climate:food/craft_fertilizer_mixed2");

		// food materials

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

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_JAM.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_FRUITS))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_fluits", has(TagDC.ItemTag.CROP_FRUITS))
			.save(cons, "dcs_climate:food/foodmaterial_fluit_jam");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MARMALADE.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_CITRUS))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_citrus", has(TagDC.ItemTag.CROP_CITRUS))
			.save(cons, "dcs_climate:food/foodmaterial_marmalade");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_ANKO.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_ADZUKI))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.unlockedBy("has_adzuki", has(TagDC.ItemTag.CROP_ADZUKI))
			.save(cons, "dcs_climate:food/foodmaterial_anko");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_CUSTARD.get(), 3)
			.requires(Ingredient.of(TagDC.ItemTag.MILKS))
			.requires(Ingredient.of(TagDC.ItemTag.SUGARS))
			.requires(Ingredient.of(TagDC.ItemTag.DUST_WHEAT))
			.requires(Ingredient.of(Tags.Items.EGGS))
			.unlockedBy("has_milks", has(TagDC.ItemTag.MILKS))
			.save(cons, "dcs_climate:food/foodmaterial_custard");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_MAKOMOTAKE.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.CROP_WILD_RICE))
			.unlockedBy("has_zizania", has(TagDC.ItemTag.CROP_WILD_RICE))
			.save(cons, "dcs_climate:food/makomodake_zizania");

		ShapelessRecipeBuilder.shapeless(FoodInit.FOOD_SYRUP.get(), 1)
			.requires(Ingredient.of(TagDC.ItemTag.PALM_FLOWER))
			.unlockedBy("has_palm_flower", has(TagDC.ItemTag.PALM_FLOWER))
			.save(cons, "dcs_climate:food/palm_flower_syrup");

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

	static void smeltingRecipes(Consumer<FinishedRecipe> cons) {
		cookingRecipe(cons, Ingredient.of(TagDC.ItemTag.WATER), CoreInit.DUST_SALT.get(), 200, "dust_salt", FoodInit.FOOD_WATER.get(), "has_water");
		smokingRecipe(cons, Ingredient.of(TagDC.ItemTag.RAW_SAUSAGE), FoodInit.SMOKED_SAUSAGE.get(), 200, "smoked_sausage", FoodInit.RAW_SAUSAGE.get(), "has_raw_sausage");
	}

	private static void cookingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, Item output, int time, String name, Item unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.campfireCooking(input, output, 0F, time * 3)
			.unlockedBy(unlockName, has(unlockTarget))
			.save(cons, "dcs_climate:smelting/campfire_" + name);

		SimpleCookingRecipeBuilder.smelting(input, output, 0F, time)
			.unlockedBy(unlockName, has(unlockTarget))
			.save(cons, "dcs_climate:smelting/smelting_" + name);
	}

	private static void smeltingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, Item output, int time, String name, Item unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.smelting(input, output, 0F, time)
			.unlockedBy(unlockName, has(unlockTarget))
			.save(cons, "dcs_climate:smelting/smelting_" + name);

		SimpleCookingRecipeBuilder.blasting(input, output, 0F, time / 2)
			.unlockedBy(unlockName, has(unlockTarget))
			.save(cons, "dcs_climate:smelting/blasting_" + name);
	}

	private static void smokingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, Item output, int time, String name, Item unlockTarget, String unlockName) {
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
