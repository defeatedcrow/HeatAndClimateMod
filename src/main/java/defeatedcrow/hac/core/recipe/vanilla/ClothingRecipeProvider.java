package defeatedcrow.hac.core.recipe.vanilla;

import java.util.function.Consumer;
import java.util.stream.Stream;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

public class ClothingRecipeProvider extends RecipeProvider {

	public ClothingRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> cons) {
		clothingRecipes(cons);
	}


	private static void clothingRecipes(Consumer<FinishedRecipe> cons) {
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.PATTERN_HAT.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .define('X', Items.PAPER)
		    .define('Y', Items.CHARCOAL)
		    .unlockedBy("has_paper", has(Items.PAPER))
		    .save(cons, "dcs_climate:clothing/pattern_hat");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.PATTERN_JACKET.get(), 1)
		    .pattern("XYX")
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', Items.PAPER)
		    .define('Y', Items.CHARCOAL)
		    .unlockedBy("has_paper", has(Items.PAPER))
		    .save(cons, "dcs_climate:clothing/pattern_jacket");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.PATTERN_TUNIC.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', Items.PAPER)
		    .define('Y', Items.CHARCOAL)
		    .unlockedBy("has_paper", has(Items.PAPER))
		    .save(cons, "dcs_climate:clothing/pattern_tunic");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.PATTERN_SHIRT.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("X X")
		    .define('X', Items.PAPER)
		    .define('Y', Items.CHARCOAL)
		    .unlockedBy("has_paper", has(Items.PAPER))
		    .save(cons, "dcs_climate:clothing/pattern_shirt");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.PATTERN_SUITS.get(), 1)
		    .pattern("XXX")
		    .pattern("XXX")
		    .pattern("XYX")
		    .define('X', Items.PAPER)
		    .define('Y', Items.CHARCOAL)
		    .unlockedBy("has_paper", has(Items.PAPER))
		    .save(cons, "dcs_climate:clothing/pattern_suits");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.PATTERN_PANTS.get(), 1)
		    .pattern("X X")
		    .pattern("XYX")
		    .define('X', Items.PAPER)
		    .define('Y', Items.CHARCOAL)
		    .unlockedBy("has_paper", has(Items.PAPER))
		    .save(cons, "dcs_climate:clothing/pattern_pants");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.MET_BRONZE.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/helmet_bronze");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.CHEST_BRONZE.get(), 1)
		    .pattern("XYX")
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/plate_bronze");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.LEGGINS_BRONZE.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("X X")
		    .define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/chain_mail_bronze");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.BOOTS_BRONZE.get(), 1)
		    .pattern("X X")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/boots_bronze");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.MET_STEEL.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_STEEL)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/helmet_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.CHEST_STEEL.get(), 1)
		    .pattern("XYX")
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_STEEL)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/plate_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.LEGGINS_STEEL.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("X X")
		    .define('X', TagDC.ItemTag.INGOT_STEEL)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/chain_mail_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.BOOTS_STEEL.get(), 1)
		    .pattern("X X")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_STEEL)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:clothing/boots_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.HAT_SAFETY.get(), 1)
		    .pattern("XXX")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_STEEL)
		    .define('Y', TagDC.ItemTag.CLOTH_RUBBER)
		    .unlockedBy("has_rubber_cloth", has(TagDC.ItemTag.CLOTH_RUBBER))
		    .save(cons, "dcs_climate:clothing/helmet_safety");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.BOOTS_SAFETY.get(), 1)
		    .pattern("Z Z")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_STEEL)
		    .define('Y', TagDC.ItemTag.CLOTH_RUBBER)
		    .define('Z', Tags.Items.LEATHER)
		    .unlockedBy("has_rubber_cloth", has(TagDC.ItemTag.CLOTH_RUBBER))
		    .save(cons, "dcs_climate:clothing/boots_safety");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.LEGGINS_WADERS.get(), 1)
		    .pattern("XYX")
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.CLOTH_RUBBER)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_rubber_cloth", has(TagDC.ItemTag.CLOTH_RUBBER))
		    .save(cons, "dcs_climate:clothing/rubber_waders");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.FUR_SHAWL.get(), 1)
		    .pattern("XYX")
		    .define('X', Items.RABBIT_HIDE)
		    .define('Y', Tags.Items.STRING)
		    .unlockedBy("has_rabbit_hide", has(Items.RABBIT_HIDE))
		    .save(cons, "dcs_climate:clothing/shawl_fur_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.FUR_SHAWL.get(), 1)
		    .pattern("XYX")
		    .define('X', Tags.Items.LEATHER)
		    .define('Y', Tags.Items.STRING)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:clothing/shawl_fur_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.FUR_LOINCLOTH.get(), 1)
		    .pattern(" Y ")
		    .pattern("XXX")
		    .define('X', Items.RABBIT_HIDE)
		    .define('Y', Tags.Items.STRING)
		    .unlockedBy("has_rabbit_hide", has(Items.RABBIT_HIDE))
		    .save(cons, "dcs_climate:clothing/shawl_loincloth_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC , CoreInit.FUR_LOINCLOTH.get(), 1)
		    .pattern(" Y ")
		    .pattern("XXX")
		    .define('X', Tags.Items.LEATHER)
		    .define('Y', Tags.Items.STRING)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:clothing/shawl_loincloth_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAT_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.FIBER_PLANT)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_fiber_plant", has(TagDC.ItemTag.FIBER_PLANT))
		    .save(cons, "dcs_climate:clothing/hat_linen");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAT_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.FEED_HAY)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_hay", has(TagDC.ItemTag.FEED_HAY))
		    .save(cons, "dcs_climate:clothing/hat_linen_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAT_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.FEED_STRAW)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_straw", has(TagDC.ItemTag.FEED_STRAW))
		    .save(cons, "dcs_climate:clothing/hat_linen_3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.JACKET_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_JACKET.get())
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
		    .save(cons, "dcs_climate:clothing/jacket_linen");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SHIRT_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_SHIRT.get())
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
		    .save(cons, "dcs_climate:clothing/shirt_linen");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.PANTS_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_PANTS.get())
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
		    .save(cons, "dcs_climate:clothing/pants_linen");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SKIRT_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_PANTS.get())
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.GEM_AGATES)
		    .unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
		    .save(cons, "dcs_climate:clothing/skirt_linen");

		ShapelessRecipeBuilder.shapeless(RecipeCategory .MISC ,CoreInit.DRESS_LINEN.get(), 1)
		    .requires(CoreInit.PATTERN_TUNIC.get())
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(TagDC.ItemTag.GEM_AGATES)
		    .unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
		    .save(cons, "dcs_climate:clothing/dress_linen");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAT_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/hat_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.LEATHER)
		    .requires(TagDC.ItemTag.GEM_AGATES)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/hair_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.JACKET_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_JACKET.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/jacket_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.BLOUSE_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_JACKET.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.GEMS)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/blouse_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.TUNIC_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_TUNIC.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .requires(Tags.Items.LEATHER)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/tunic_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SHIRT_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_SHIRT.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/shirt_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SUITS_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_SUITS.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(TagDC.ItemTag.CROP_COTTON), new Ingredient.TagValue(ItemTags.WOOL))))
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/suits_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.PANTS_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_PANTS.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/pants_cotton");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.DRESS_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_TUNIC.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_FLOWERS)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/dress_cotton_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.DRESS_CLOTH.get(), 1)
		    .requires(CoreInit.PATTERN_TUNIC.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(Tags.Items.STRING)
		    .requires(ItemTags.FLOWERS)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/dress_cotton_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.JACKET_WOOL.get(), 1)
		    .requires(CoreInit.PATTERN_JACKET.get())
		    .requires(TagDC.ItemTag.CLOTH_WOOL)
		    .requires(TagDC.ItemTag.CLOTH_WOOL)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_wool_cloth", has(TagDC.ItemTag.CLOTH_WOOL))
		    .save(cons, "dcs_climate:clothing/jacket_wool");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SUITS_WOOL.get(), 1)
		    .requires(CoreInit.PATTERN_SUITS.get())
		    .requires(TagDC.ItemTag.CLOTH_WOOL)
		    .requires(TagDC.ItemTag.CLOTH_WOOL)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_wool_cloth", has(TagDC.ItemTag.CLOTH_WOOL))
		    .save(cons, "dcs_climate:clothing/suits_wool");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.TIGHTS_WOOL.get(), 1)
		    .requires(CoreInit.PATTERN_PANTS.get())
		    .requires(TagDC.ItemTag.CLOTH_WOOL)
		    .requires(TagDC.ItemTag.CLOTH_WOOL)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_wool_cloth", has(TagDC.ItemTag.CLOTH_WOOL))
		    .save(cons, "dcs_climate:clothing/tights_wool");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SUITS_LEATHER.get(), 1)
		    .requires(CoreInit.PATTERN_SUITS.get())
		    .requires(Tags.Items.LEATHER)
		    .requires(Tags.Items.LEATHER)
		    .requires(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(TagDC.ItemTag.CROP_COTTON), new Ingredient.TagValue(ItemTags.WOOL))))
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_lether", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:clothing/suits_lether");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.SUITS_LEATHER.get(), 1)
		    .requires(CoreInit.PATTERN_SUITS.get())
		    .requires(Tags.Items.LEATHER)
		    .requires(Tags.Items.LEATHER)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_lether", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:clothing/suits_lether_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.CHAPS_LEATHER.get(), 1)
		    .requires(CoreInit.PATTERN_PANTS.get())
		    .requires(Tags.Items.LEATHER)
		    .requires(Tags.Items.LEATHER)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_lether", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:clothing/chaps_leather");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.LONG_MAID.get(), 1)
		    .requires(CoreInit.PATTERN_SUITS.get())
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(TagDC.ItemTag.DUST_SUGAR)
		    .requires(Tags.Items.STRING)
		    .unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:clothing/dress_maid");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_SNOWDROP.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_SNOWDROP)
		    .unlockedBy("has_crop_snowdrop", has(TagDC.ItemTag.CROP_SNOWDROP))
		    .save(cons, "dcs_climate:clothing/hair_flower_snowdrop_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_AMARYLLIS.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_AMARYLLIS)
		    .unlockedBy("has_crop_amaryllis", has(TagDC.ItemTag.CROP_AMARYLLIS))
		    .save(cons, "dcs_climate:clothing/hair_flower_amaryllis_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_DAFFODIL.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_DAFFODIL)
		    .unlockedBy("has_crop_daffodil", has(TagDC.ItemTag.CROP_DAFFODIL))
		    .save(cons, "dcs_climate:clothing/hair_flower_daffodil_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_LYCORIS.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_LYCORIS)
		    .unlockedBy("has_crop_lycoris", has(TagDC.ItemTag.CROP_LYCORIS))
		    .save(cons, "dcs_climate:clothing/hair_flower_lycoris_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_ASTER.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_CHRYSANTHEMUM)
		    .unlockedBy("has_crop_aster", has(TagDC.ItemTag.CROP_CHRYSANTHEMUM))
		    .save(cons, "dcs_climate:clothing/hair_flower_aster_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_PYRETHRUM.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_PYRETHRUM)
		    .unlockedBy("has_crop_pyrethrum", has(TagDC.ItemTag.CROP_PYRETHRUM))
		    .save(cons, "dcs_climate:clothing/hair_flower_pyrethrum_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_KONJAC.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.KONJAC_FLOWER)
		    .unlockedBy("has_flower_konjac", has(TagDC.ItemTag.KONJAC_FLOWER))
		    .save(cons, "dcs_climate:clothing/hair_flower_konjac_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_CAMELLIA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(FoodInit.FLOWER_CAMELLIA.get())
		    .unlockedBy("has_flower_camellia", has(FoodInit.FLOWER_CAMELLIA.get()))
		    .save(cons, "dcs_climate:clothing/hair_flower_camellia_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_SCHIMA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(FoodInit.FLOWER_SCHIMA.get())
		    .unlockedBy("has_flower_schima", has(FoodInit.FLOWER_SCHIMA.get()))
		    .save(cons, "dcs_climate:clothing/hair_flower_schima_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_CHERRY.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CHERRY_FLOWER)
		    .unlockedBy("has_flower_cherry", has(TagDC.ItemTag.CHERRY_FLOWER))
		    .save(cons, "dcs_climate:clothing/hair_flower_cherry_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_PLUM.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.PLUM_FLOWER)
		    .unlockedBy("has_flower_plum", has(TagDC.ItemTag.PLUM_FLOWER))
		    .save(cons, "dcs_climate:clothing/hair_flower_plum_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_HEATH.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_HEATH)
		    .unlockedBy("has_crop_heath", has(TagDC.ItemTag.CROP_HEATH))
		    .save(cons, "dcs_climate:clothing/hair_flower_heath_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_RHODODENDRON.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_RHODODENDRON)
		    .unlockedBy("has_crop_rhododendron", has(TagDC.ItemTag.CROP_RHODODENDRON))
		    .save(cons, "dcs_climate:clothing/hair_flower_rhododendron_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_LAVENDER.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_LAVENDER)
		    .unlockedBy("has_crop_lavender", has(TagDC.ItemTag.CROP_LAVENDER))
		    .save(cons, "dcs_climate:clothing/hair_flower_lavender_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_CROCUS.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_CROCUS)
		    .unlockedBy("has_crop_crocus", has(TagDC.ItemTag.CROP_CROCUS))
		    .save(cons, "dcs_climate:clothing/hair_flower_crocus_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_IRIS.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_IRIS)
		    .unlockedBy("has_crop_iris", has(TagDC.ItemTag.CROP_IRIS))
		    .save(cons, "dcs_climate:clothing/hair_flower_iris_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_AMANA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_AMANA)
		    .unlockedBy("has_crop_amana", has(TagDC.ItemTag.CROP_AMANA))
		    .save(cons, "dcs_climate:clothing/hair_flower_amana_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_FAWN.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_FAWN)
		    .unlockedBy("has_crop_fawn", has(TagDC.ItemTag.CROP_FAWN))
		    .save(cons, "dcs_climate:clothing/hair_flower_fawn_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_LILY.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_GOLDBAND)
		    .unlockedBy("has_crop_goldband", has(TagDC.ItemTag.CROP_GOLDBAND))
		    .save(cons, "dcs_climate:clothing/hair_flower_goldband_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_MALLOW.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_BLUE_MALLOW)
		    .unlockedBy("has_crop_mallow", has(TagDC.ItemTag.CROP_BLUE_MALLOW))
		    .save(cons, "dcs_climate:clothing/hair_flower_mallow_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_TROPICAL.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_TROPICAL)
		    .unlockedBy("has_crop_tropical", has(TagDC.ItemTag.CROP_TROPICAL))
		    .save(cons, "dcs_climate:clothing/hair_flower_tropical_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_BINDWEED.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_BINDWEED)
		    .unlockedBy("has_crop_bindweed", has(TagDC.ItemTag.CROP_BINDWEED))
		    .save(cons, "dcs_climate:clothing/hair_flower_bindweed_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_MORNING.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_MORNING_GLORY)
		    .unlockedBy("has_crop_morning", has(TagDC.ItemTag.CROP_MORNING_GLORY))
		    .save(cons, "dcs_climate:clothing/hair_flower_morning_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_OSMANTHUS.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_OSMANTHUS)
		    .unlockedBy("has_crop_osmanthus", has(TagDC.ItemTag.CROP_OSMANTHUS))
		    .save(cons, "dcs_climate:clothing/hair_flower_osmanthus_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_JASMINE.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_JASMINE)
		    .unlockedBy("has_crop_jasmine", has(TagDC.ItemTag.CROP_JASMINE))
		    .save(cons, "dcs_climate:clothing/hair_flower_jasmine_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_SPIRANTHES.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_SPIRANTHES)
		    .unlockedBy("has_crop_spiranthes", has(TagDC.ItemTag.CROP_SPIRANTHES))
		    .save(cons, "dcs_climate:clothing/hair_flower_spiranthes_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_CYMBIDIUM.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_CYMBIDIUM)
		    .unlockedBy("has_crop_cymbidium", has(TagDC.ItemTag.CROP_CYMBIDIUM))
		    .save(cons, "dcs_climate:clothing/hair_flower_cymbidium_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_CATTLEYA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_CATTLEYA)
		    .unlockedBy("has_crop_cattleya", has(TagDC.ItemTag.CROP_CATTLEYA))
		    .save(cons, "dcs_climate:clothing/hair_flower_cattleya_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_DELPHINIUM.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_DELPHINIUM)
		    .unlockedBy("has_crop_delphinium", has(TagDC.ItemTag.CROP_DELPHINIUM))
		    .save(cons, "dcs_climate:clothing/hair_flower_delphinium_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_CLEMATIS.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_CLEMATIS)
		    .unlockedBy("has_crop_clematis", has(TagDC.ItemTag.CROP_CLEMATIS))
		    .save(cons, "dcs_climate:clothing/hair_flower_clematis_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_MONKSHOOD.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_MONKSHOOD)
		    .unlockedBy("has_crop_monkshood", has(TagDC.ItemTag.CROP_MONKSHOOD))
		    .save(cons, "dcs_climate:clothing/hair_flower_monkshood_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_DAMASCHENA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_DAMASCHENA)
		    .unlockedBy("has_crop_damaschena", has(TagDC.ItemTag.CROP_DAMASCHENA))
		    .save(cons, "dcs_climate:clothing/hair_flower_damaschena_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_GARDENIA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.GARDENIA_FLOWER)
		    .unlockedBy("has_flower_gardenia", has(TagDC.ItemTag.GARDENIA_FLOWER))
		    .save(cons, "dcs_climate:clothing/hair_flower_gardenia_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , CoreInit.HAIR_FLOWER_IXORA.get(), 1)
		    .requires(CoreInit.PATTERN_HAT.get())
		    .requires(TagDC.ItemTag.CROP_SCHIMA)
		    .requires(Tags.Items.STRING)
		    .requires(TagDC.ItemTag.CROP_IXORA)
		    .unlockedBy("has_crop_ixora", has(TagDC.ItemTag.CROP_IXORA))
		    .save(cons, "dcs_climate:clothing/hair_flower_ixora_0");

		clothColorRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "linen_hat");
		clothColorRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_PINK.get(), Tags.Items.DYES_PINK, "pink", "linen_hat");
		clothColorRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_RED.get(), Tags.Items.DYES_RED, "red", "linen_hat");

		clothBleachRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_BLUE.get(), "linen_hat", 1);
		clothBleachRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_PINK.get(), "linen_hat", 2);
		clothBleachRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_RED.get(), "linen_hat", 3);

		clothColorRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_hat");
		clothColorRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_hat");
		clothColorRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_GREEN.get(), Tags.Items.DYES_GREEN, "green", "cloth_hat");

		clothBleachRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_BLACK.get(), "cloth_hat", 1);
		clothBleachRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_BLUE.get(), "cloth_hat", 2);
		clothBleachRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_GREEN.get(), "cloth_hat", 3);

		clothColorRecipe(cons, CoreInit.HAIR_CLOTH.get(), CoreInit.HAIR_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_hair");
		clothColorRecipe(cons, CoreInit.HAIR_CLOTH.get(), CoreInit.HAIR_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_hair");
		clothColorRecipe(cons, CoreInit.HAIR_CLOTH.get(), CoreInit.HAIR_CLOTH_RED.get(), Tags.Items.DYES_MAGENTA, "magenta", "cloth_hair");

		clothBleachRecipe(cons, CoreInit.HAIR_CLOTH.get(), CoreInit.HAIR_CLOTH_BLACK.get(), "cloth_hair", 1);
		clothBleachRecipe(cons, CoreInit.HAIR_CLOTH.get(), CoreInit.HAIR_CLOTH_BLUE.get(), "cloth_hair", 2);
		clothBleachRecipe(cons, CoreInit.HAIR_CLOTH.get(), CoreInit.HAIR_CLOTH_RED.get(), "cloth_hair", 3);

		clothColorRecipe(cons, CoreInit.FUR_SHAWL.get(), CoreInit.FUR_SHAWL_BLACK.get(), Tags.Items.DYES_BLACK, "black", "fur_shawl");
		clothColorRecipe(cons, CoreInit.FUR_SHAWL.get(), CoreInit.FUR_SHAWL_SILVER.get(), Tags.Items.DYES_LIGHT_GRAY, "silver", "fur_shawl");
		clothColorRecipe(cons, CoreInit.FUR_SHAWL.get(), CoreInit.FUR_SHAWL_WHITE.get(), Tags.Items.DYES_WHITE, "white", "fur_shawl");

		clothBleachRecipe(cons, CoreInit.FUR_SHAWL.get(), CoreInit.FUR_SHAWL_BLACK.get(), "fur_shawl", 1);
		clothBleachRecipe(cons, CoreInit.FUR_SHAWL.get(), CoreInit.FUR_SHAWL_SILVER.get(), "fur_shawl", 2);
		clothBleachRecipe(cons, CoreInit.FUR_SHAWL.get(), CoreInit.FUR_SHAWL_WHITE.get(), "fur_shawl", 3);

		clothColorRecipe(cons, CoreInit.FUR_LOINCLOTH.get(), CoreInit.FUR_LOINCLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "fur_loincloth");
		clothColorRecipe(cons, CoreInit.FUR_LOINCLOTH.get(), CoreInit.FUR_LOINCLOTH_SILVER.get(), Tags.Items.DYES_LIGHT_GRAY, "silver", "fur_loincloth");
		clothColorRecipe(cons, CoreInit.FUR_LOINCLOTH.get(), CoreInit.FUR_LOINCLOTH_WHITE.get(), Tags.Items.DYES_WHITE, "white", "fur_loincloth");

		clothBleachRecipe(cons, CoreInit.FUR_LOINCLOTH.get(), CoreInit.FUR_LOINCLOTH_BLACK.get(), "fur_loincloth", 1);
		clothBleachRecipe(cons, CoreInit.FUR_LOINCLOTH.get(), CoreInit.FUR_LOINCLOTH_SILVER.get(), "fur_loincloth", 2);
		clothBleachRecipe(cons, CoreInit.FUR_LOINCLOTH.get(), CoreInit.FUR_LOINCLOTH_WHITE.get(), "fur_loincloth", 3);

		clothColorRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_BLACK.get(), Tags.Items.DYES_BLACK, "black", "linen_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "linen_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "linen_jacket");

		clothBleachRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_BLACK.get(), "linen_jacket", 1);
		clothBleachRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_BLUE.get(), "linen_jacket", 2);
		clothBleachRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_GRAY.get(), "linen_jacket", 3);

		clothColorRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "cloth_jacket");

		clothBleachRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_BLACK.get(), "cloth_jacket", 1);
		clothBleachRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_BLUE.get(), "cloth_jacket", 2);
		clothBleachRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_GRAY.get(), "cloth_jacket", 3);

		clothColorRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_CYAN.get(), Tags.Items.DYES_CYAN, "cyan", "wool_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "wool_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_RED.get(), Tags.Items.DYES_RED, "red", "wool_jacket");

		clothBleachRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_CYAN.get(), "wool_jacket", 1);
		clothBleachRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_LIGHTBLUE.get(), "wool_jacket", 2);
		clothBleachRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_RED.get(), "wool_jacket", 3);

		clothColorRecipe(cons, CoreInit.BLOUSE_CLOTH.get(), CoreInit.BLOUSE_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_blouse");
		clothColorRecipe(cons, CoreInit.BLOUSE_CLOTH.get(), CoreInit.BLOUSE_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_blouse");
		clothColorRecipe(cons, CoreInit.BLOUSE_CLOTH.get(), CoreInit.BLOUSE_CLOTH_PINK.get(), Tags.Items.DYES_PINK, "pink", "cloth_blouse");

		clothBleachRecipe(cons, CoreInit.BLOUSE_CLOTH.get(), CoreInit.BLOUSE_CLOTH_BLACK.get(), "cloth_blouse", 1);
		clothBleachRecipe(cons, CoreInit.BLOUSE_CLOTH.get(), CoreInit.BLOUSE_CLOTH_BLUE.get(), "cloth_blouse", 2);
		clothBleachRecipe(cons, CoreInit.BLOUSE_CLOTH.get(), CoreInit.BLOUSE_CLOTH_PINK.get(), "cloth_blouse", 3);

		clothColorRecipe(cons, CoreInit.TUNIC_CLOTH.get(), CoreInit.TUNIC_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_tunic");
		clothColorRecipe(cons, CoreInit.TUNIC_CLOTH.get(), CoreInit.TUNIC_CLOTH_GREEN.get(), Tags.Items.DYES_GREEN, "green", "cloth_tunic");
		clothColorRecipe(cons, CoreInit.TUNIC_CLOTH.get(), CoreInit.TUNIC_CLOTH_ORANGE.get(), Tags.Items.DYES_ORANGE, "orange", "cloth_tunic");

		clothBleachRecipe(cons, CoreInit.TUNIC_CLOTH.get(), CoreInit.TUNIC_CLOTH_BLACK.get(), "cloth_tunic", 1);
		clothBleachRecipe(cons, CoreInit.TUNIC_CLOTH.get(), CoreInit.TUNIC_CLOTH_GREEN.get(), "cloth_tunic", 2);
		clothBleachRecipe(cons, CoreInit.TUNIC_CLOTH.get(), CoreInit.TUNIC_CLOTH_ORANGE.get(), "cloth_tunic", 3);

		clothColorRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_CYAN.get(), Tags.Items.DYES_CYAN, "cyan", "linen_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_GREEN.get(), Tags.Items.DYES_GREEN, "green", "linen_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_PINK.get(), Tags.Items.DYES_PINK, "pink", "linen_shirt");

		clothBleachRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_CYAN.get(), "linen_shirt", 1);
		clothBleachRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_GREEN.get(), "linen_shirt", 2);
		clothBleachRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_PINK.get(), "linen_shirt", 3);

		clothColorRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "cloth_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_RED.get(), Tags.Items.DYES_RED, "red", "cloth_shirt");

		clothBleachRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_BLACK.get(), "cloth_shirt", 1);
		clothBleachRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_LIGHTBLUE.get(), "cloth_shirt", 2);
		clothBleachRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_RED.get(), "cloth_shirt", 3);

		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_BLACK.get(), Tags.Items.DYES_BLACK, "black", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_GREEN.get(), Tags.Items.DYES_GREEN, "green", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_ORANGE.get(), Tags.Items.DYES_ORANGE, "orange", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_WHITE.get(), Tags.Items.DYES_WHITE, "white", "linen_pants");

		clothBleachRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_GREEN.get(), "linen_pants", 1);
		clothBleachRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_ORANGE.get(), "linen_pants", 2);
		clothBleachRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_WHITE.get(), "linen_pants", 3);
		clothBleachRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_BLACK.get(), "linen_pants", 4);
		clothBleachRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_GRAY.get(), "linen_pants", 5);
		clothBleachRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_BLUE.get(), "linen_pants", 6);

		clothColorRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_pants");
		clothColorRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "cloth_pants");
		clothColorRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "cloth_pants");

		clothBleachRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_BLUE.get(), "cloth_pants", 1);
		clothBleachRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_LIGHTBLUE.get(), "cloth_pants", 2);
		clothBleachRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_GRAY.get(), "cloth_pants", 3);

		clothColorRecipe(cons, CoreInit.TIGHTS_WOOL.get(), CoreInit.TIGHTS_WOOL_BLACK.get(), Tags.Items.DYES_BLACK, "black", "knit_tights");
		clothColorRecipe(cons, CoreInit.TIGHTS_WOOL.get(), CoreInit.TIGHTS_WOOL_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "knit_tights");
		clothColorRecipe(cons, CoreInit.TIGHTS_WOOL.get(), CoreInit.TIGHTS_WOOL_RED.get(), Tags.Items.DYES_RED, "red", "knit_tights");

		clothBleachRecipe(cons, CoreInit.TIGHTS_WOOL.get(), CoreInit.TIGHTS_WOOL_BLACK.get(), "knit_tights", 1);
		clothBleachRecipe(cons, CoreInit.TIGHTS_WOOL.get(), CoreInit.TIGHTS_WOOL_BLUE.get(), "knit_tights", 2);
		clothBleachRecipe(cons, CoreInit.TIGHTS_WOOL.get(), CoreInit.TIGHTS_WOOL_RED.get(), "knit_tights", 3);

		clothColorRecipe(cons, CoreInit.CHAPS_LEATHER.get(), CoreInit.CHAPS_LEATHER_BLACK.get(), Tags.Items.DYES_BLACK, "black", "chaps_leather");
		clothColorRecipe(cons, CoreInit.CHAPS_LEATHER.get(), CoreInit.CHAPS_LEATHER_WHITE.get(), Tags.Items.DYES_WHITE, "white", "chaps_leather");

		clothBleachRecipe(cons, CoreInit.CHAPS_LEATHER.get(), CoreInit.CHAPS_LEATHER_BLACK.get(), "chaps_leather", 1);
		clothBleachRecipe(cons, CoreInit.CHAPS_LEATHER.get(), CoreInit.CHAPS_LEATHER_WHITE.get(), "chaps_leather", 2);

		clothColorRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_PINK.get(), Tags.Items.DYES_PINK, "black", "linen_skirt");
		clothColorRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_GRAY.get(), Tags.Items.DYES_GRAY, "blue", "linen_skirt");
		clothColorRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_BLUE.get(), Tags.Items.DYES_BLUE, "red", "linen_skirt");
		clothColorRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_GREEN.get(), Tags.Items.DYES_GREEN, "green", "linen_skirt");

		clothBleachRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_PINK.get(), "linen_skirt", 1);
		clothBleachRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_GRAY.get(), "linen_skirt", 2);
		clothBleachRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_BLUE.get(), "linen_skirt", 3);
		clothBleachRecipe(cons, CoreInit.SKIRT_LINEN.get(), CoreInit.SKIRT_LINEN_GREEN.get(), "linen_skirt", 4);

		clothColorRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_suits");
		clothColorRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_BROWN.get(), Tags.Items.DYES_BROWN, "brown", "cloth_suits");
		clothColorRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_GREEN.get(), Tags.Items.DYES_GREEN, "green", "cloth_suits");

		clothBleachRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_BLUE.get(), "cloth_suits", 1);
		clothBleachRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_BROWN.get(), "cloth_suits", 2);
		clothBleachRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_GREEN.get(), "cloth_suits", 3);

		clothColorRecipe(cons, CoreInit.SUITS_WOOL.get(), CoreInit.SUITS_WOOL_BLACK.get(), Tags.Items.DYES_BLACK, "black", "wool_suits");
		clothColorRecipe(cons, CoreInit.SUITS_WOOL.get(), CoreInit.SUITS_WOOL_YELLOW.get(), Tags.Items.DYES_YELLOW, "yellow", "wool_suits");
		clothColorRecipe(cons, CoreInit.SUITS_WOOL.get(), CoreInit.SUITS_WOOL_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "wool_suits");

		clothBleachRecipe(cons, CoreInit.SUITS_WOOL.get(), CoreInit.SUITS_WOOL_BLACK.get(), "wool_suits", 1);
		clothBleachRecipe(cons, CoreInit.SUITS_WOOL.get(), CoreInit.SUITS_WOOL_YELLOW.get(), "wool_suits", 2);
		clothBleachRecipe(cons, CoreInit.SUITS_WOOL.get(), CoreInit.SUITS_WOOL_BLUE.get(), "wool_suits", 3);

		clothColorRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_BLACK.get(), Tags.Items.DYES_BLACK, "black", "leather_suits");
		clothColorRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_BROWN.get(), Tags.Items.DYES_BROWN, "brown", "leather_suits");
		clothColorRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_WHITE.get(), Tags.Items.DYES_WHITE, "white", "leather_suits");

		clothBleachRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_BLACK.get(), "leather_suits", 1);
		clothBleachRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_BROWN.get(), "leather_suits", 2);
		clothBleachRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_WHITE.get(), "leather_suits", 3);

		clothColorRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_BLACK.get(), Tags.Items.DYES_BLACK, "black", "linen_dress");
		clothColorRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_GREEN.get(), Tags.Items.DYES_GREEN, "green", "linen_dress");
		clothColorRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_CYAN.get(), Tags.Items.DYES_CYAN, "cyan", "linen_dress");
		clothColorRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_MAGENTA.get(), Tags.Items.DYES_MAGENTA, "magenta", "linen_dress");
		clothColorRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_WHITE.get(), Tags.Items.DYES_WHITE, "white", "linen_dress");

		clothBleachRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_BLACK.get(), "linen_dress", 1);
		clothBleachRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_GREEN.get(), "linen_dress", 2);
		clothBleachRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_CYAN.get(), "linen_dress", 3);
		clothBleachRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_MAGENTA.get(), "linen_dress", 4);
		clothBleachRecipe(cons, CoreInit.DRESS_LINEN.get(), CoreInit.DRESS_LINEN_WHITE.get(), "linen_dress", 5);

		clothColorRecipe(cons, CoreInit.DRESS_CLOTH.get(), CoreInit.DRESS_CLOTH_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "cotton_dress");
		clothColorRecipe(cons, CoreInit.DRESS_CLOTH.get(), CoreInit.DRESS_CLOTH_PINK.get(), Tags.Items.DYES_PINK, "pink", "cotton_dress");
		clothColorRecipe(cons, CoreInit.DRESS_CLOTH.get(), CoreInit.DRESS_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cotton_dress");

		clothBleachRecipe(cons, CoreInit.DRESS_CLOTH.get(), CoreInit.DRESS_CLOTH_LIGHTBLUE.get(), "cotton_dress", 1);
		clothBleachRecipe(cons, CoreInit.DRESS_CLOTH.get(), CoreInit.DRESS_CLOTH_PINK.get(), "cotton_dress", 2);
		clothBleachRecipe(cons, CoreInit.DRESS_CLOTH.get(), CoreInit.DRESS_CLOTH_BLACK.get(), "cotton_dress", 3);

		clothColorRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_BLACK.get(), Tags.Items.DYES_BLACK, "black", "dress_maid");
		clothColorRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_BROWN.get(), Tags.Items.DYES_BROWN, "brown", "dress_maid");
		clothColorRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "dress_maid");

		clothBleachRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_BLACK.get(), "dress_maid", 1);
		clothBleachRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_BROWN.get(), "dress_maid", 2);
		clothBleachRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_GRAY.get(), "dress_maid", 3);
	}

	private static void clothColorRecipe(Consumer<FinishedRecipe> cons, ItemLike in, ItemLike out, TagKey<Item> color, String cname, String name) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC , out, 1)
		    .requires(in)
		    .requires(color)
		    .group("cloth_coloring")
		    .unlockedBy("has_" + name, has(in))
		    .save(cons, "dcs_climate:clothing/coloring_" + name + "_" + cname);
	}

	private static void clothBleachRecipe(Consumer<FinishedRecipe> cons, ItemLike out, ItemLike in, String name, int num) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,out, 1)
		    .requires(in)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .group("bleaching")
		    .unlockedBy("has_" + name, has(out))
		    .save(cons, "dcs_climate:clothing/bleaching_" + name + "_" + num);
	}

}
