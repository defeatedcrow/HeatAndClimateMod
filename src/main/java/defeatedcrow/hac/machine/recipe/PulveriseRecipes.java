package defeatedcrow.hac.machine.recipe;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.recipe.device.DeviceRecipeList;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

public class PulveriseRecipes {
	public static PulveriseRecipes INSTANCE = new PulveriseRecipes() {};

	public static void init() {

		// crops
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_AMARANTH.get(), 3), new ItemStack(Items.STICK), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GOOSEFOOT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_SALT.get(), 2), new ItemStack(CoreInit.DUST_TRONA.get()), 100, new ItemStack(FoodInit.DUST_PLANT.get()), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GLASSWORT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_SYRUP.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_PARSNIP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.GREEN_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ARTEMISIA)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_WHITE.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CHRYSANTHEMUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 2), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RAPESEED)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 2), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CAMELLIA)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_OAT.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OAT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_RYE.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RYE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_BARLEY.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BARLEY)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.LIGHT_GRAY_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_HEATH)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RHODODENDRON)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BLUEBERRY)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TURMERIC)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PURPLE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WILD_GRAPE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLUE.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LAVENDER)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PURPLE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CROCUS)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SAFFRON)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLUE.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_IRIS)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_BUCKWHEAT.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BUCKWHEAT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_INDIGO)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FIBER_PLANT.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_JUTE)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BLUE_MALLOW)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_RED.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TROPICAL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.VINE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BINDWEED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_GREEN.get(), 2), new ItemStack(FoodInit.VINE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MORNING_GLORY)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_MANA.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ASH)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 2), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OLIVE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_WHITE.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OSUMANTHUS)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SPIRANTHES)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CYMBIDIUM)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_GREEN.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CATTLEYA)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_COCONUT_MILK.get(), 3), new ItemStack(FoodInit.FIBER_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_COCONUT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_ALMOND_MILK.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ALMOND)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_SYRUP.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DATE)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 2), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OIL_PALM)));

		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ROGERIA)));

		DeviceRecipeList.addPulverizeRecipe(6, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 2), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SESAMI)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLACK.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DEVILSCLAW)));

		DeviceRecipeList.addPulverizeRecipe(7, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 2), new ItemStack(FoodInit.FOOD_DEFATTED_SOY.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SOY)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.WHITE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ANEMONE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.LIGHT_BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DELPHINIUM)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.VINE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CLEMATIS)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLACK.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MONKSHOOD)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FIBER_PLANT.get(), 3), new ItemStack(FoodInit.FEED_STRAW.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_REED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_SORGHUM.get(), 3), new ItemStack(FoodInit.SORGHUM_STICK.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SORGHUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_ZIZANIA.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WILD_RICE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_RICE.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RICE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_AROMA_RICE.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_AROMA_RICE)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_WHITE.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DAMASCHENA)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_MANA.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_NIGHTSHADE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_RED.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LANTERN)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.CROP_CN_CINNAMON.get(), 1), new ItemStack(FoodInit.DUST_WOOD.get(), 4), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.LOG_CN_CINNAMON.get())));

		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.CROP_HB_PERILLA.get())));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.RED_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.FLOWER_CAMELLIA.get())));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.WHITE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.FLOWER_SCHIMA.get())));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_FISH_POWDER.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_ALL_FISH)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BONE_MEAL, 6), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.BONES)));

		// vanilla

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_WHEAT.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.CROPS_WHEAT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.FOOD_WHEAT.get(), 2), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.SEEDS_WHEAT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.SUGAR, 5), new ItemStack(FoodInit.FOOD_BAGASSE.get()), 100, new ItemStack(FoodInit.FOOD_SYRUP.get()), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SUGAR)));

		// ores

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE1.get(), 3), new ItemStack(CoreInit.OREITEM_WHITE2.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_WHITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE2.get(), 3), new ItemStack(CoreInit.OREITEM_WHITE3.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_WHITE_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLUE1.get(), 3), new ItemStack(CoreInit.OREITEM_BLUE2.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLUE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLUE2.get(), 3), new ItemStack(CoreInit.OREITEM_BLUE3.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLUE_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLACK1.get(), 3), new ItemStack(CoreInit.OREITEM_BLACK2.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLACK)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLACK2.get(), 3), new ItemStack(CoreInit.OREITEM_BLACK3.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLACK_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_RED1.get(), 3), new ItemStack(CoreInit.OREITEM_RED2.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_RED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_RED2.get(), 3), new ItemStack(CoreInit.OREITEM_RED3.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_RED_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_GREEN1.get(), 3), new ItemStack(CoreInit.OREITEM_GREEN2.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_GREEN)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_GREEN2.get(), 3), new ItemStack(CoreInit.OREITEM_GREEN3.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_GREEN_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_WHITE1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.RAW_MATERIALS_COPPER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_WHITE2.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.RAW_MATERIALS_GOLD)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_WHITE3.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_TUNGSTEN)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_BLUE1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_ZINC)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_BLUE2.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_BISMUTH)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_BLUE3.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_COBALT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_BLACK1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_MAGNETITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_BLACK2.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_SILVER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_BLACK3.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_MOLYBDENUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_RED1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.RAW_MATERIALS_IRON)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_RED2.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_ALUMINUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_RED3.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_TITANIUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_GREEN1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_TIN)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_GREEN2.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_NICKEL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREDUST_GREEN3.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_CHROMIUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.GEM_CHALCEDONY.get(), 1), new ItemStack(CoreInit.DUST_CRYSTAL.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(CoreInit.STONE_QUARTZ.get())));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.GEM_SALT.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_SALT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.GEM_NITER.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_NITER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.GEM_SULFUR.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_SULFUR)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_TRONA.get(), 4), new ItemStack(CoreInit.DUST_SALT.get(), 1), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_NATRON)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_LIME.get(), 2), new ItemStack(CoreInit.DUST_SULFUR.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_TRAVERTINE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_CRYSTAL.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_AGATES)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_SALT.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_SALT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_NITER.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_NITER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_SULFUR.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_SULFUR)));

		// vanilla

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_RED1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_IRON)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE2.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_GOLD)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE1.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_COPPER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.REDSTONE, 6), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_REDSTONE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.GRAVEL, 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.COBBLESTONE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.FLINT, 1), new ItemStack(Items.SAND, 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.GRAVEL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_LIME.get(), 4), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.DRIPSTONES)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_COAL.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_COAL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.DUST_WOOD.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.PLANKS)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.DUST_PLANT.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.WEED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.CLAY_BALL, 1), new ItemStack(Items.SAND, 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.DIRT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.SAND, 4), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.SANDSTONE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.CLAY_BALL, 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.SAND)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.STRING_WOOL.get(), 4), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.WOOL)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(FoodInit.STRING_WOOL.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.WOOL_CARPETS)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.WHITE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.LILY_OF_THE_VALLEY)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.WHITE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.WHITE_TULIP)));

		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.WHITE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.AZURE_BLUET)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.ORANGE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.ORANGE_TULIP)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.ALLIUM)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.LILAC)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.LIGHT_BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.BLUE_ORCHID)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.DANDELION)));

		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.SUNFLOWER)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.PINK_TULIP)));

		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.PEONY)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.LIGHT_GRAY_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.OXEYE_DAISY)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.CORNFLOWER)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BROWN_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.COCOA_BEANS)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.GREEN_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.CACTUS)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.RED_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.POPPY)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.RED_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.ROSE_BUSH)));

		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.RED_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Items.RED_TULIP)));

		DeviceRecipeList.addPulverizeRecipe(6, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.RED_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.CROPS_BEETROOT)));
	}

}
