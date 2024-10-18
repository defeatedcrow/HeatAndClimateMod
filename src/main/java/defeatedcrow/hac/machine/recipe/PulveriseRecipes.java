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
		// amaranth
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_AMARANTH.get(), 3), new ItemStack(Items.STICK), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GOOSEFOOT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.SQUEEZE,
				new ItemStack(CoreInit.DUST_SALT.get(), 2), new ItemStack(CoreInit.DUST_TRONA.get()), 100, new ItemStack(FoodInit.DUST_PLANT.get()), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_GLASSWORT)));

		// apium
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_SYRUP.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_PARSNIP)));

		// aster
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.GREEN_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ARTEMISIA)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_WHITE.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CHRYSANTHEMUM)));

		// beech
		DeviceRecipeList.addPulverizeRecipe(8, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 500),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WALNUT)));

		// brassica
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 500),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RAPESEED)));

		// camellia
		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 500),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CAMELLIA)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.RED_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.FLOWER_CAMELLIA.get())));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.WHITE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.FLOWER_SCHIMA.get())));

		// cereals
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_OAT.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OAT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_RYE.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RYE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_BARLEY.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BARLEY)));

		// cherry
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_ALMOND_MILK.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ALMOND)));

		// cinnamon
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.CROP_CN_CINNAMON.get(), 2), new ItemStack(FoodInit.DUST_WOOD.get(), 4), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.LOG_CN_CINNAMON.get())));

		// erica
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.GRAY_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_HEATH)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RHODODENDRON)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BLUEBERRY)));

		// ginger
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TURMERIC)));

		// grape
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PURPLE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WILD_GRAPE)));

		// knotweed
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_BUCKWHEAT.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BUCKWHEAT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BLUE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_INDIGO)));

		// herb
		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.CROP_HB_PERILLA.get())));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLUE.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LAVENDER)));

		// iris
		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PURPLE_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CROCUS)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SAFFRON)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLUE.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_IRIS)));

		// mallow
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FIBER_PLANT.get(), 4), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_JUTE)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.MAGENTA_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BLUE_MALLOW)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_RED.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, new ItemStack(FoodInit.MALLOW_CALYCES.get()), 10, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_TROPICAL)));

		// morninggrory
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.VINE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_BINDWEED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_GREEN.get(), 3), new ItemStack(FoodInit.VINE.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MORNING_GLORY)));

		// morus
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FIBER_WOOD.get(), 6), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(FoodInit.LOG_MR_PAPER.get())));

		// olive
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_MANA.get(), 2), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ASH)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 3), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 1000),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OLIVE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_WHITE.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OSMANTHUS)));

		// orchid
		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.PINK_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SPIRANTHES)));

		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.YELLOW_DYE, 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CYMBIDIUM)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_GREEN.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CATTLEYA)));

		// palm
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_COCONUT_MILK.get(), 3), new ItemStack(FoodInit.FIBER_PLANT.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_COCONUT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_SYRUP.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DATE)));

		DeviceRecipeList.addPulverizeRecipe(4, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 3), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 1000),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_OIL_PALM)));

		// peas
		DeviceRecipeList.addPulverizeRecipe(7, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_DEFATTED_SOY.get()), 100, new ItemStack(FoodInit.FOOD_GERM.get()), 100,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 500),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SOY)));

		// pedaria
		DeviceRecipeList.addPulverizeRecipe(5, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 500),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_ROGERIA)));

		DeviceRecipeList.addPulverizeRecipe(6, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.FOOD_PRESS_CAKE.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 500),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SESAMI)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_BLACK.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DEVILSCLAW)));

		// ranunculus
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
				new ItemStack(MagicInit.DROP_BLACK.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_MONKSHOOD)));

		// reed
		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FIBER_PLANT.get(), 4), new ItemStack(FoodInit.FEED_STRAW.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_REED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_SORGHUM.get(), 3), new ItemStack(FoodInit.SORGHUM_STICK.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SORGHUM)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_CORNMEAL.get(), 3), new ItemStack(FoodInit.FEED_STRAW.get()), 100, new ItemStack(FoodInit.FOOD_GERM.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_CORN)));

		// rice
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_ZIZANIA.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_WILD_RICE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_RICE.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_RICE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_AROMA_RICE.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_AROMA_RICE)));

		// rose
		DeviceRecipeList.addPulverizeRecipe(3, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_WHITE.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_DAMASCHENA)));

		// solanum
		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_MANA.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_NIGHTSHADE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(MagicInit.DROP_RED.get(), 3), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LANTERN)));

		// other
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_FISH_POWDER.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.RAW_ALL_FISH)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.BONE_MEAL, 6), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.BONES)));

		DeviceRecipeList.addPulverizeRecipe(9, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 250),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.BRAN)));

		DeviceRecipeList.addPulverizeRecipe(10, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_PLANT_OIL.get(), 1), new ItemStack(FoodInit.DUST_PLANT.get()), 100, ItemStack.EMPTY, 0,
				new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 250),
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GERM)));

		// vanilla

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_WHEAT.get(), 3), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, new ItemStack(FoodInit.FEED_STRAW.get()), 100, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.CROPS_WHEAT)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.FOOD_WHEAT.get(), 2), new ItemStack(FoodInit.FOOD_BRAN.get()), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.SEEDS_WHEAT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(Items.SUGAR, 5), new ItemStack(FoodInit.FOOD_BAGASSE.get()), 100, new ItemStack(FoodInit.FOOD_SYRUP.get()), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_SUGAR)));

		// ores

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE1.get(), 3), new ItemStack(CoreInit.OREITEM_WHITE2.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_WHITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE2.get(), 3), new ItemStack(CoreInit.OREITEM_WHITE3.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_WHITE_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLUE1.get(), 3), new ItemStack(CoreInit.OREITEM_BLUE2.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLUE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLUE2.get(), 3), new ItemStack(CoreInit.OREITEM_BLUE3.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLUE_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLACK1.get(), 3), new ItemStack(CoreInit.OREITEM_BLACK2.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLACK)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_BLACK2.get(), 3), new ItemStack(CoreInit.OREITEM_BLACK3.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLACK_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_RED1.get(), 3), new ItemStack(CoreInit.OREITEM_RED2.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_RED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_RED2.get(), 3), new ItemStack(CoreInit.OREITEM_RED3.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_RED_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_GREEN1.get(), 3), new ItemStack(CoreInit.OREITEM_GREEN2.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_GREEN)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_GREEN2.get(), 3), new ItemStack(CoreInit.OREITEM_GREEN3.get()), 50, ItemStack.EMPTY, 0, FluidStack.EMPTY,
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

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(CoreInit.GEM_SALT.get(), 4), new ItemStack(CoreInit.DUST_TRONA.get(), 1), 50, new ItemStack(CoreInit.DUST_LITHIUM.get(), 1), 5, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_SALT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.GEM_NITER.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_NITER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.GEM_SULFUR.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_SULFUR)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_TRONA.get(), 4), new ItemStack(CoreInit.DUST_SALT.get(), 1), 50, new ItemStack(CoreInit.DUST_LITHIUM.get(), 1), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_NATRON)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_LIME.get(), 3), new ItemStack(CoreInit.DUST_SULFUR.get(), 1), 100, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_TRAVERTINE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_CRYSTAL.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_AGATES)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_ALUMINA.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_SAPPHIRE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_ALUMINA.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_RUBY)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_LITHIUM.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_KUNZITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_DIAMOND.get(), 1), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.GEMS_DIAMOND)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_SALT.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_SALT)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_NITER.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_NITER)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.DUST_SULFUR.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.GEM_SULFUR)));

		// vanilla
		// ores
		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_RED1.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_IRON)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE2.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_GOLD)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(CoreInit.OREITEM_WHITE1.get(), 3), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_COPPER)));

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

		// plants
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
				new ItemStack(Items.SAND, 4), ItemStack.EMPTY, 0, new ItemStack(CoreInit.DUST_BORAX.get(), 1), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.SANDSTONE)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.PULVERISE,
				new ItemStack(Items.CLAY_BALL, 1), ItemStack.EMPTY, 0, new ItemStack(CoreInit.DUST_BORAX.get(), 1), 50, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.SAND)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.STRING_WOOL.get(), 4), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.WOOL)));

		DeviceRecipeList.addPulverizeRecipe(2, RecipeTypeDC.SQUEEZE,
				new ItemStack(FoodInit.STRING_WOOL.get(), 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(ItemTags.WOOL_CARPETS)));

		// vanilla flowers
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

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.PULVERISE,
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

		// sieve

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_CRYSTAL.get(), 2), new ItemStack(CoreInit.GEM_THUNDEREGG.get()), 30, new ItemStack(Items.DIAMOND), 3, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_WHITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_THUNDEREGG.get(), 2), new ItemStack(Items.DIAMOND), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_WHITE_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_FLUORITE.get(), 2), new ItemStack(CoreInit.GEM_CELESTITE.get()), 30, new ItemStack(CoreInit.GEM_SAPPHIRE.get()), 3, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLUE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_CELESTITE.get(), 2), new ItemStack(CoreInit.GEM_SAPPHIRE.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLUE_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_VIVIANITE.get(), 2), new ItemStack(CoreInit.GEM_FANG.get()), 30, new ItemStack(CoreInit.GEM_KUNZITE.get()), 3, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLACK)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_FANG.get(), 2), new ItemStack(CoreInit.GEM_KUNZITE.get()), 30, new ItemStack(CoreInit.GEM_TOURMALINE.get()), 2, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_BLACK_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_JASPER.get(), 2), new ItemStack(CoreInit.GEM_ALMANDINE.get()), 30, new ItemStack(CoreInit.GEM_RUBY.get()), 3, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_RED)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_ALMANDINE.get(), 2), new ItemStack(CoreInit.GEM_RUBY.get()), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_RED_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_MALACHITE.get(), 2), new ItemStack(CoreInit.GEM_OLIVINE.get()), 30, new ItemStack(Items.EMERALD), 3, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_GREEN)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_OLIVINE.get(), 2), new ItemStack(Items.EMERALD), 30, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_GREEN_DEEP)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_CHALCEDONY.get(), 2), new ItemStack(CoreInit.GEM_HELIODOR.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_CHALCEDONY)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_HELIODOR.get(), 2), new ItemStack(CoreInit.GEM_TOPAZ.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_HELIODOR)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_TOPAZ.get(), 2), new ItemStack(Items.DIAMOND), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_TOPAZ)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_FLUORITE.get(), 2), new ItemStack(CoreInit.GEM_LARIMAR.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_FLUORITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_LARIMAR.get(), 2), new ItemStack(CoreInit.GEM_AQUAMARINE.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_LARIMAR)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_AQUAMARINE.get(), 2), new ItemStack(CoreInit.GEM_SAPPHIRE.get()), 10, new ItemStack(CoreInit.GEM_TOURMALINE.get()), 2, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_AQUAMARINE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_JET.get(), 2), new ItemStack(CoreInit.GEM_IOLITE.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_JET)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_IOLITE.get(), 2), new ItemStack(CoreInit.GEM_SAKURA.get()), 30, new ItemStack(CoreInit.GEM_OPAL.get()), 10, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_IOLITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_OPAL.get(), 2), new ItemStack(Items.ENDER_PEARL), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_OPAL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_DESERTROSE.get(), 2), new ItemStack(CoreInit.GEM_ROSINCA.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_DESERTROSE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_ROSINCA.get(), 2), new ItemStack(CoreInit.GEM_SPINEL.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_ROSINCA)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_SPINEL.get(), 2), new ItemStack(CoreInit.GEM_RUBY.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_SPINEL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_SERPENTINE.get(), 2), new ItemStack(CoreInit.GEM_AMAZONITE.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_SERPENTINE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_AMAZONITE.get(), 2), new ItemStack(CoreInit.GEM_JADEITE.get()), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_AMAZONITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_JADEITE.get(), 2), new ItemStack(Items.EMERALD), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_JADEITE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(CoreInit.GEM_DRAGONSEYE.get(), 2), new ItemStack(Items.GHAST_TEAR), 10, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(TagDC.ItemTag.ORES_DRAGONSEYE)));

		// vanilla
		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.COAL, 4), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_COAL)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.LAPIS_LAZULI, 6), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_LAPIS)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.REDSTONE, 6), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_REDSTONE)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.EMERALD, 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_EMERALD)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.DIAMOND, 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_DIAMOND)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.QUARTZ, 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_QUARTZ)));

		DeviceRecipeList.addPulverizeRecipe(1, RecipeTypeDC.SIEVE,
				new ItemStack(Items.NETHERITE_SCRAP, 2), ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY,
				ImmutableList.of(Ingredient.of(Tags.Items.ORES_NETHERITE_SCRAP)));
	}

}
