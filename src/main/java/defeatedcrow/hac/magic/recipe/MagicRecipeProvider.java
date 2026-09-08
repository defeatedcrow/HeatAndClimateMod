package defeatedcrow.hac.magic.recipe;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

//@formatter:off
public class MagicRecipeProvider extends RecipeProvider {

	public MagicRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> cons) {
		craftRecipes(cons);
		mortarRecipes(cons);
	}

	static void craftRecipes(Consumer<FinishedRecipe> cons) {
		// arrow
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MagicInit.ARROW_WHITE.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', Items.ARROW)
				.define('Y', TagDC.ItemTag.EXTRACT_WHITE)
				.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
				.save(cons, "dcs_climate:magic/craft_arrow_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MagicInit.ARROW_BLUE.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', Items.ARROW)
				.define('Y', TagDC.ItemTag.EXTRACT_BLUE)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_arrow_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MagicInit.ARROW_BLACK.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', Items.ARROW)
				.define('Y', TagDC.ItemTag.EXTRACT_BLACK)
				.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
				.save(cons, "dcs_climate:magic/craft_arrow_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MagicInit.ARROW_RED.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', Items.ARROW)
				.define('Y', TagDC.ItemTag.EXTRACT_RED)
				.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
				.save(cons, "dcs_climate:magic/craft_arrow_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MagicInit.ARROW_GREEN.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', Items.ARROW)
				.define('Y', TagDC.ItemTag.EXTRACT_GREEN)
				.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
				.save(cons, "dcs_climate:magic/craft_arrow_green");

		// seedbag
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.SEEDBAG_WHITE.get(), 1)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
				.save(cons, "dcs_climate:magic/craft_seedbag_white");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.SEEDBAG_BLUE.get(), 1)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_seedbag_blue");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.SEEDBAG_BLACK.get(), 1)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
				.save(cons, "dcs_climate:magic/craft_seedbag_black");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.SEEDBAG_RED.get(), 1)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
				.save(cons, "dcs_climate:magic/craft_seedbag_red");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.SEEDBAG_GREEN.get(), 1)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(Tags.Items.SEEDS)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
				.save(cons, "dcs_climate:magic/craft_seedbag_green");

		// card
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_WHITE_1.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
				.save(cons, "dcs_climate:magic/craft_card_white_common");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_BLUE_1.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_card_blue_common");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_BLACK_1.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
				.save(cons, "dcs_climate:magic/craft_card_black_common");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_RED_1.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
				.save(cons, "dcs_climate:magic/craft_card_red_common");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_GREEN_1.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
				.save(cons, "dcs_climate:magic/craft_card_green_common");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_WHITE_2.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_WHITE)
				.unlockedBy("has_pigment_white", has(TagDC.ItemTag.PIGMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_card_white_uncommon");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_BLUE_2.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_BLUE)
				.unlockedBy("has_pigment_blue", has(TagDC.ItemTag.PIGMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_card_blue_uncommon");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_BLACK_2.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_BLACK)
				.unlockedBy("has_pigment_black", has(TagDC.ItemTag.PIGMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_card_black_uncommon");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_RED_2.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_RED)
				.unlockedBy("has_pigment_red", has(TagDC.ItemTag.PIGMENT_RED))
				.save(cons, "dcs_climate:magic/craft_card_red_uncommon");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_GREEN_2.get(), 3)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_GREEN)
				.unlockedBy("has_pigment_green", has(TagDC.ItemTag.PIGMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_card_green_uncommon");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_WU.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_WHITE)
				.requires(TagDC.ItemTag.PIGMENT_BLUE)
				.unlockedBy("has_pigment_white", has(TagDC.ItemTag.PIGMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_card_white_blue");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_WR.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
				.save(cons, "dcs_climate:magic/craft_card_white_red");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_UB.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_BLUE)
				.requires(TagDC.ItemTag.PIGMENT_BLACK)
				.unlockedBy("has_pigment_blue", has(TagDC.ItemTag.PIGMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_card_blue_black");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_UG.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_card_blue_green");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_BR.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_BLACK)
				.requires(TagDC.ItemTag.PIGMENT_RED)
				.unlockedBy("has_pigment_black", has(TagDC.ItemTag.PIGMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_card_black_red");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_BW.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
				.save(cons, "dcs_climate:magic/craft_card_black_white");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_RG.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_RED)
				.requires(TagDC.ItemTag.PIGMENT_GREEN)
				.unlockedBy("has_pigment_red", has(TagDC.ItemTag.PIGMENT_RED))
				.save(cons, "dcs_climate:magic/craft_card_red_green");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_RU.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
				.save(cons, "dcs_climate:magic/craft_card_red_blue");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_GW.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.PIGMENT_GREEN)
				.requires(TagDC.ItemTag.PIGMENT_WHITE)
				.unlockedBy("has_pigment_green", has(TagDC.ItemTag.PIGMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_card_green_white");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.CARD_GB.get(), 2)
				.requires(Items.PAPER)
				.requires(Items.PAPER)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
				.save(cons, "dcs_climate:magic/craft_card_green_black");

		// ring
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_SILVER_WHITE.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_WHITE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
				.save(cons, "dcs_climate:magic/craft_ring_silver_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_SILVER_BLUE.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_BLUE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_ring_silver_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_SILVER_BLACK.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_BLACK)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
				.save(cons, "dcs_climate:magic/craft_ring_silver_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_SILVER_RED.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_RED)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
				.save(cons, "dcs_climate:magic/craft_ring_silver_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_SILVER_GREEN.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_GREEN)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
				.save(cons, "dcs_climate:magic/craft_ring_silver_green");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_GOLD_WHITE.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_WHITE)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
				.save(cons, "dcs_climate:magic/craft_ring_gold_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_GOLD_BLUE.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_BLUE)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_ring_gold_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_GOLD_BLACK.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_BLACK)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
				.save(cons, "dcs_climate:magic/craft_ring_gold_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_GOLD_RED.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_RED)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
				.save(cons, "dcs_climate:magic/craft_ring_gold_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.RING_GOLD_GREEN.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.EXTRACT_GREEN)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
				.save(cons, "dcs_climate:magic/craft_ring_gold_green");

		// pendant
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_SILVER_WHITE.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_WHITE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_pigment_white", has(TagDC.ItemTag.PIGMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_pendant_silver_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_SILVER_BLUE.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_BLUE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_pigment_blue", has(TagDC.ItemTag.PIGMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_pendant_silver_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_SILVER_BLACK.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_BLACK)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_pigment_black", has(TagDC.ItemTag.PIGMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_pendant_silver_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_SILVER_RED.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_RED)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_pigment_red", has(TagDC.ItemTag.PIGMENT_RED))
				.save(cons, "dcs_climate:magic/craft_pendant_silver_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_SILVER_GREEN.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_GREEN)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_pigment_green", has(TagDC.ItemTag.PIGMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_pendant_silver_green");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_GOLD_WHITE.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_WHITE)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_pigment_white", has(TagDC.ItemTag.PIGMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_pendant_gold_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_GOLD_BLUE.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_BLUE)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_pigment_blue", has(TagDC.ItemTag.PIGMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_pendant_gold_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_GOLD_BLACK.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_BLACK)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_pigment_black", has(TagDC.ItemTag.PIGMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_pendant_gold_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_GOLD_RED.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_RED)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_pigment_red", has(TagDC.ItemTag.PIGMENT_RED))
				.save(cons, "dcs_climate:magic/craft_pendant_gold_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PENDANT_GOLD_GREEN.get(), 1)
				.pattern("ZZZ")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.PIGMENT_GREEN)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_pigment_green", has(TagDC.ItemTag.PIGMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_pendant_gold_green");

		// badge
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BADGE_SILVER_WHITE.get(), 1)
				.pattern(" X ")
				.pattern("ZYZ")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_white", has(TagDC.ItemTag.ELEMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_badge_silver_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BADGE_SILVER_BLUE.get(), 1)
				.pattern(" X ")
				.pattern("ZYZ")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_blue", has(TagDC.ItemTag.ELEMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_badge_silver_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BADGE_SILVER_BLACK.get(), 1)
				.pattern(" X ")
				.pattern("ZYZ")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_black", has(TagDC.ItemTag.ELEMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_badge_silver_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BADGE_SILVER_RED.get(), 1)
				.pattern(" X ")
				.pattern("ZYZ")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.ELEMENT_RED)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_red", has(TagDC.ItemTag.ELEMENT_RED))
				.save(cons, "dcs_climate:magic/craft_badge_silver_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BADGE_SILVER_GREEN.get(), 1)
				.pattern(" X ")
				.pattern("ZYZ")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.DUST_CRYSTAL)
				.define('Y', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_green", has(TagDC.ItemTag.ELEMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_badge_silver_green");

		// bracelet
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BRACELET_SILVER_WHITE.get(), 1)
				.pattern(" X ")
				.pattern("ZZZ")
				.pattern(" Y ")
				.define('X', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Y', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_white", has(TagDC.ItemTag.ELEMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_bracelet_silver_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BRACELET_SILVER_BLUE.get(), 1)
				.pattern(" X ")
				.pattern("ZZZ")
				.pattern(" Y ")
				.define('X', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Y', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_blue", has(TagDC.ItemTag.ELEMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_bracelet_silver_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BRACELET_SILVER_BLACK.get(), 1)
				.pattern(" X ")
				.pattern("ZZZ")
				.pattern(" Y ")
				.define('X', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Y', TagDC.ItemTag.ELEMENT_RED)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_black", has(TagDC.ItemTag.ELEMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_bracelet_silver_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BRACELET_SILVER_RED.get(), 1)
				.pattern(" X ")
				.pattern("ZZZ")
				.pattern(" Y ")
				.define('X', TagDC.ItemTag.ELEMENT_RED)
				.define('Y', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_red", has(TagDC.ItemTag.ELEMENT_RED))
				.save(cons, "dcs_climate:magic/craft_bracelet_silver_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.BRACELET_SILVER_GREEN.get(), 1)
				.pattern(" X ")
				.pattern("ZZZ")
				.pattern(" Y ")
				.define('X', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Y', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_green", has(TagDC.ItemTag.ELEMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_bracelet_silver_green");

		// rod
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.ROD_WHITE.get(), 1)
				.pattern("XWY")
				.pattern(" Z ")
				.pattern(" Z ")
				.define('W', Items.GLOWSTONE_DUST)
				.define('X', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Y', TagDC.ItemTag.ELEMENT_RED)
				.define('Z', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_element_white", has(TagDC.ItemTag.ELEMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_rod_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.ROD_BLUE.get(), 1)
				.pattern("XWY")
				.pattern(" Z ")
				.pattern(" Z ")
				.define('W', BuildInit.GLASS_CRYSTAL.get())
				.define('X', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Y', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Z', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_element_blue", has(TagDC.ItemTag.ELEMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_rod_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.ROD_BLACK.get(), 1)
				.pattern("XWY")
				.pattern(" Z ")
				.pattern(" Z ")
				.define('W', TagDC.ItemTag.GEM_DRAGONSEYE)
				.define('X', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Y', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Z', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_element_black", has(TagDC.ItemTag.ELEMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_rod_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.ROD_RED.get(), 1)
				.pattern("XWY")
				.pattern(" Z ")
				.pattern(" Z ")
				.define('W', TagDC.ItemTag.GEM_TOURMALINE)
				.define('X', TagDC.ItemTag.ELEMENT_RED)
				.define('Y', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Z', TagDC.ItemTag.INGOT_STEEL)
				.unlockedBy("has_element_red", has(TagDC.ItemTag.ELEMENT_RED))
				.save(cons, "dcs_climate:magic/craft_rod_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.ROD_GREEN.get(), 1)
				.pattern("XWY")
				.pattern(" Z ")
				.pattern(" Z ")
				.define('W', Items.GRASS_BLOCK)
				.define('X', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Y', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Z', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_element_green", has(TagDC.ItemTag.ELEMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_rod_green");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_WU.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Z', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_white", has(TagDC.ItemTag.ELEMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_white_blue_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_WR.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Z', TagDC.ItemTag.ELEMENT_RED)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_white", has(TagDC.ItemTag.ELEMENT_WHITE))
				.save(cons, "dcs_climate:magic/craft_white_red_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_UB.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Z', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_blue", has(TagDC.ItemTag.ELEMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_blue_black_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_UG.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Z', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_blue", has(TagDC.ItemTag.ELEMENT_BLUE))
				.save(cons, "dcs_climate:magic/craft_blue_green_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_BR.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Z', TagDC.ItemTag.ELEMENT_RED)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_black", has(TagDC.ItemTag.ELEMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_black_red_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_BW.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Z', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_black", has(TagDC.ItemTag.ELEMENT_BLACK))
				.save(cons, "dcs_climate:magic/craft_black_white_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_RG.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_RED)
				.define('Z', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_red", has(TagDC.ItemTag.ELEMENT_RED))
				.save(cons, "dcs_climate:magic/craft_red_green_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_RU.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_RED)
				.define('Z', TagDC.ItemTag.ELEMENT_BLUE)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_red", has(TagDC.ItemTag.ELEMENT_RED))
				.save(cons, "dcs_climate:magic/craft_red_blue_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_GW.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Z', TagDC.ItemTag.ELEMENT_WHITE)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_green", has(TagDC.ItemTag.ELEMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_green_white_painting");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.PICTURE_GB.get(), 1)
				.pattern("XXX")
				.pattern("WYW")
				.pattern("ZZZ")
				.define('W', Tags.Items.GEMS_DIAMOND)
				.define('X', TagDC.ItemTag.ELEMENT_GREEN)
				.define('Z', TagDC.ItemTag.ELEMENT_BLACK)
				.define('Y', Items.PAINTING)
				.unlockedBy("has_element_green", has(TagDC.ItemTag.ELEMENT_GREEN))
				.save(cons, "dcs_climate:magic/craft_green_black_painting");

	}

	static void mortarRecipes(Consumer<FinishedRecipe> cons) {
		// magic drops

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_WHITE.get(), 1)
				.requires(TagDC.ItemTag.CROP_OSMANTHUS)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_osmanthus", has(TagDC.ItemTag.CROP_OSMANTHUS))
				.save(cons, "dcs_climate:magic/mortar_crop_osmanthus");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_WHITE.get(), 1)
				.requires(TagDC.ItemTag.CROP_CHRYSANTHEMUM)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_chrysanthemum", has(TagDC.ItemTag.CROP_CHRYSANTHEMUM))
				.save(cons, "dcs_climate:magic/mortar_crop_chrysanthemum");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_WHITE.get(), 1)
				.requires(TagDC.ItemTag.CROP_DAMASCHENA)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_damaschena", has(TagDC.ItemTag.CROP_DAMASCHENA))
				.save(cons, "dcs_climate:magic/mortar_crop_damaschena");
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_WHITE.get(), 1)
				.requires(TagDC.ItemTag.CROP_GOLDBAND)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_goldband", has(TagDC.ItemTag.CROP_GOLDBAND))
				.save(cons, "dcs_climate:magic/mortar_crop_goldband");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLUE.get(), 1)
				.requires(TagDC.ItemTag.CROP_LAVENDER)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_lavender", has(TagDC.ItemTag.CROP_LAVENDER))
				.save(cons, "dcs_climate:magic/mortar_crop_lavender");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLUE.get(), 1)
				.requires(TagDC.ItemTag.CROP_IRIS)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_iris", has(TagDC.ItemTag.CROP_IRIS))
				.save(cons, "dcs_climate:magic/mortar_crop_iris");
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLUE.get(), 1)
				.requires(TagDC.ItemTag.CROP_DAFFODIL)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_daffodil", has(TagDC.ItemTag.CROP_DAFFODIL))
				.save(cons, "dcs_climate:magic/mortar_crop_daffodil");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLACK.get(), 1)
				.requires(TagDC.ItemTag.KONJAC_FLOWER)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_flower_konjac", has(TagDC.ItemTag.KONJAC_FLOWER))
				.save(cons, "dcs_climate:magic/mortar_flower_konjac");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLACK.get(), 1)
				.requires(TagDC.ItemTag.CROP_DEVILSCLAW)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_devilsclaw", has(TagDC.ItemTag.CROP_DEVILSCLAW))
				.save(cons, "dcs_climate:magic/mortar_crop_devilsclaw");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLACK.get(), 1)
				.requires(TagDC.ItemTag.CROP_MONKSHOOD)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_monkshood", has(TagDC.ItemTag.CROP_MONKSHOOD))
				.save(cons, "dcs_climate:magic/mortar_crop_monkshood");
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLACK.get(), 1)
				.requires(TagDC.ItemTag.CROP_LYCORIS)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_lycoris", has(TagDC.ItemTag.CROP_LYCORIS))
				.save(cons, "dcs_climate:magic/mortar_crop_lycoris");
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_BLACK.get(), 1)
				.requires(TagDC.ItemTag.CROP_MANCHINEEL)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_manchineel", has(TagDC.ItemTag.CROP_MANCHINEEL))
				.save(cons, "dcs_climate:magic/mortar_crop_manchineel");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_RED.get(), 1)
				.requires(TagDC.ItemTag.CROP_LANTERN)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_lantern", has(TagDC.ItemTag.CROP_LANTERN))
				.save(cons, "dcs_climate:magic/mortar_crop_lantern");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_RED.get(), 1)
				.requires(TagDC.ItemTag.CROP_TROPICAL)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_hibiscus", has(TagDC.ItemTag.CROP_TROPICAL))
				.save(cons, "dcs_climate:magic/mortar_crop_hibiscus");
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_RED.get(), 1)
				.requires(TagDC.ItemTag.CROP_IXORA)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_ixora", has(TagDC.ItemTag.CROP_IXORA))
				.save(cons, "dcs_climate:magic/mortar_crop_ixora");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_GREEN.get(), 1)
				.requires(TagDC.ItemTag.CROP_MORNING_GLORY)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_morning_glory", has(TagDC.ItemTag.CROP_MORNING_GLORY))
				.save(cons, "dcs_climate:magic/mortar_crop_morning_glory");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_GREEN.get(), 1)
				.requires(TagDC.ItemTag.CROP_CATTLEYA)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_cattleya", has(TagDC.ItemTag.CROP_CATTLEYA))
				.save(cons, "dcs_climate:magic/mortar_crop_cattleya");
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_GREEN.get(), 1)
				.requires(TagDC.ItemTag.CROP_JASMINE)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_jasmine", has(TagDC.ItemTag.CROP_JASMINE))
				.save(cons, "dcs_climate:magic/mortar_crop_jasmine");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.DROP_MANA.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.CROP_NIGHTSHADE)
				.define('Y', CoreInit.MORTAR.get())
				.unlockedBy("has_crop_nightshade", has(TagDC.ItemTag.CROP_NIGHTSHADE))
				.save(cons, "dcs_climate:magic/mortar_crop_nightshade");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.DROP_MANA.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.CROP_ASH)
				.define('Y', CoreInit.MORTAR.get())
				.unlockedBy("has_crop_ash", has(TagDC.ItemTag.CROP_ASH))
				.save(cons, "dcs_climate:magic/mortar_crop_ash");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.DROP_MANA.get(), 8)
				.requires(TagDC.ItemTag.MANA_EXTRACT)
				.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/mana_extract_to_drop");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.DROP_MANA.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', Items.GLOW_BERRIES)
				.define('Y', CoreInit.MORTAR.get())
				.unlockedBy("has_crop_glowberry", has(Items.GLOW_BERRIES))
				.save(cons, "dcs_climate:magic/mortar_crop_glowberry");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.EXTRACT_WHITE.get(), 1)
				.pattern("XXX")
				.pattern("X X")
				.pattern("XXX")
				.define('X', MagicInit.DROP_WHITE.get())
				.unlockedBy("has_drop_white", has(MagicInit.DROP_WHITE.get()))
				.save(cons, "dcs_climate:magic/craft_extract_white");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.EXTRACT_BLUE.get(), 1)
				.pattern("XXX")
				.pattern("X X")
				.pattern("XXX")
				.define('X', MagicInit.DROP_BLUE.get())
				.unlockedBy("has_drop_blue", has(MagicInit.DROP_BLUE.get()))
				.save(cons, "dcs_climate:magic/craft_extract_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.EXTRACT_BLACK.get(), 1)
				.pattern("XXX")
				.pattern("X X")
				.pattern("XXX")
				.define('X', MagicInit.DROP_BLACK.get())
				.unlockedBy("has_drop_black", has(MagicInit.DROP_BLACK.get()))
				.save(cons, "dcs_climate:magic/craft_extract_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.EXTRACT_RED.get(), 1)
				.pattern("XXX")
				.pattern("X X")
				.pattern("XXX")
				.define('X', MagicInit.DROP_RED.get())
				.unlockedBy("has_drop_red", has(MagicInit.DROP_RED.get()))
				.save(cons, "dcs_climate:magic/craft_extract_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.EXTRACT_GREEN.get(), 1)
				.pattern("XXX")
				.pattern("X X")
				.pattern("XXX")
				.define('X', MagicInit.DROP_GREEN.get())
				.unlockedBy("has_drop_green", has(MagicInit.DROP_GREEN.get()))
				.save(cons, "dcs_climate:magic/craft_extract_green");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.EXTRACT_MANA.get(), 1)
				.pattern("XXX")
				.pattern("X X")
				.pattern("XXX")
				.define('X', MagicInit.DROP_MANA.get())
				.unlockedBy("has_drop_mana", has(MagicInit.DROP_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_extract_mana");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_MANA.get(), 1)
				.requires(TagDC.ItemTag.DUST_DIAMOND)
				.requires(TagDC.ItemTag.DUST_CRYSTAL)
				.requires(Items.GLOW_INK_SAC)
				.unlockedBy("has_glow_ink", has(Items.GLOW_INK_SAC))
				.save(cons, "dcs_climate:magic/craft_extract_mana_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_WHITE.get(), 1)
				.requires(MagicInit.EXTRACT_MANA.get())
				.requires(MagicInit.EXTRACT_WHITE.get())
				.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_pigment_white");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLUE.get(), 1)
				.requires(MagicInit.EXTRACT_MANA.get())
				.requires(MagicInit.EXTRACT_BLUE.get())
				.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_pigment_blue");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLACK.get(), 1)
				.requires(MagicInit.EXTRACT_MANA.get())
				.requires(MagicInit.EXTRACT_BLACK.get())
				.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_pigment_black");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_RED.get(), 1)
				.requires(MagicInit.EXTRACT_MANA.get())
				.requires(MagicInit.EXTRACT_RED.get())
				.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_pigment_red");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_GREEN.get(), 1)
				.requires(MagicInit.EXTRACT_MANA.get())
				.requires(MagicInit.EXTRACT_GREEN.get())
				.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_pigment_green");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_WHITE.get(), 1)
				.requires(TagDC.ItemTag.GEM_HELIODOR)
				.requires(MagicInit.EXTRACT_WHITE.get())
				.unlockedBy("has_gem_heliodor", has(TagDC.ItemTag.GEM_HELIODOR))
				.save(cons, "dcs_climate:magic/craft_pigment_white_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_WHITE.get(), 1)
				.requires(TagDC.ItemTag.GEM_THUNDEREGG)
				.requires(MagicInit.EXTRACT_WHITE.get())
				.unlockedBy("has_gem_thunderegg", has(TagDC.ItemTag.GEM_THUNDEREGG))
				.save(cons, "dcs_climate:magic/craft_pigment_white_3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLUE.get(), 1)
				.requires(TagDC.ItemTag.GEM_LARIMAR)
				.requires(MagicInit.EXTRACT_BLUE.get())
				.unlockedBy("has_gem_larimar", has(TagDC.ItemTag.GEM_LARIMAR))
				.save(cons, "dcs_climate:magic/craft_pigment_blue_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLUE.get(), 1)
				.requires(TagDC.ItemTag.GEM_CELESTITE)
				.requires(MagicInit.EXTRACT_BLUE.get())
				.unlockedBy("has_gem_celestite", has(TagDC.ItemTag.GEM_CELESTITE))
				.save(cons, "dcs_climate:magic/craft_pigment_blue_3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLACK.get(), 1)
				.requires(TagDC.ItemTag.GEM_IOLITE)
				.requires(MagicInit.EXTRACT_BLACK.get())
				.unlockedBy("has_gem_iolite", has(TagDC.ItemTag.GEM_IOLITE))
				.save(cons, "dcs_climate:magic/craft_pigment_black_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLACK.get(), 1)
				.requires(TagDC.ItemTag.GEM_FANG)
				.requires(MagicInit.EXTRACT_BLACK.get())
				.unlockedBy("has_gem_fang", has(TagDC.ItemTag.GEM_FANG))
				.save(cons, "dcs_climate:magic/craft_pigment_black_3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_RED.get(), 1)
				.requires(TagDC.ItemTag.GEM_ALMANDINE)
				.requires(MagicInit.EXTRACT_RED.get())
				.unlockedBy("has_gem_almandine", has(TagDC.ItemTag.GEM_ALMANDINE))
				.save(cons, "dcs_climate:magic/craft_pigment_red_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_RED.get(), 1)
				.requires(TagDC.ItemTag.GEM_ROSINCA)
				.requires(MagicInit.EXTRACT_RED.get())
				.unlockedBy("has_gem_rosinca", has(TagDC.ItemTag.GEM_ROSINCA))
				.save(cons, "dcs_climate:magic/craft_pigment_red_3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_GREEN.get(), 1)
				.requires(TagDC.ItemTag.GEM_AMAZONITE)
				.requires(MagicInit.EXTRACT_GREEN.get())
				.unlockedBy("has_gem_amazonte", has(TagDC.ItemTag.GEM_AMAZONITE))
				.save(cons, "dcs_climate:magic/craft_pigment_green_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_GREEN.get(), 1)
				.requires(TagDC.ItemTag.GEM_OLIVINE)
				.requires(MagicInit.EXTRACT_GREEN.get())
				.unlockedBy("has_gem_olivine", has(TagDC.ItemTag.GEM_OLIVINE))
				.save(cons, "dcs_climate:magic/craft_pigment_green_3");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MagicInit.ELEMENT_MANA.get(), 1)
				.pattern("ZXZ")
				.pattern("XYX")
				.pattern("ZXZ")
				.define('X', MagicInit.EXTRACT_MANA.get())
				.define('Y', Tags.Items.GEMS_DIAMOND)
				.define('Z', TagDC.ItemTag.GEM_AGATES)
				.unlockedBy("has_extract_mana", has(MagicInit.EXTRACT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_mana");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_WHITE_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_WHITE.get())
				.requires(TagDC.ItemTag.GEM_TOPAZ)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_white_inart");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_WHITE_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_WHITE.get())
				.requires(TagDC.ItemTag.GEM_CATSEYE)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_white_inart_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_BLUE_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_BLUE.get())
				.requires(TagDC.ItemTag.GEM_SAPPHIRE)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_blue_inart");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_BLUE_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_BLUE.get())
				.requires(TagDC.ItemTag.GEM_AQUAMARINE)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_blue_inart_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_BLACK_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_BLACK.get())
				.requires(TagDC.ItemTag.GEM_OPAL)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_black_inart");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_BLACK_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_BLACK.get())
				.requires(TagDC.ItemTag.GEM_DRAGONSEYE)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_black_inart_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_BLACK_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_BLACK.get())
				.requires(TagDC.ItemTag.GEM_KUNZITE)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_black_inart_3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_RED_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_RED.get())
				.requires(TagDC.ItemTag.GEM_SPINEL)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_red_inart");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_RED_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_RED.get())
				.requires(TagDC.ItemTag.GEM_RUBY)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_red_inart_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_GREEN_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_GREEN.get())
				.requires(TagDC.ItemTag.GEM_JADEITE)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_green_inart");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.ELEMENT_GREEN_INERT.get(), 1)
				.requires(MagicInit.ELEMENT_MANA.get())
				.requires(MagicInit.PIGMENT_GREEN.get())
				.requires(TagDC.ItemTag.GEM_DEMANTOID)
				.unlockedBy("has_element_mana", has(MagicInit.ELEMENT_MANA.get()))
				.save(cons, "dcs_climate:magic/craft_element_green_inart_2");

		// gems

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_WHITE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_CHALCEDONY)
				.unlockedBy("has_gem_chalcedony", has(TagDC.ItemTag.GEM_CHALCEDONY))
				.save(cons, "dcs_climate:magic/craft_extract_white2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_WHITE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_CRYSTAL)
				.unlockedBy("has_gem_crystal", has(TagDC.ItemTag.GEM_CRYSTAL))
				.save(cons, "dcs_climate:magic/craft_extract_white3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_BLUE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(Tags.Items.GEMS_LAPIS)
				.unlockedBy("has_gem_lapis", has(Tags.Items.GEMS_LAPIS))
				.save(cons, "dcs_climate:magic/craft_extract_blue2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_BLUE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_FLUORITE)
				.unlockedBy("has_gem_fluorite", has(TagDC.ItemTag.GEM_FLUORITE))
				.save(cons, "dcs_climate:magic/craft_extract_blue3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_BLACK.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_VIVIANITE)
				.unlockedBy("has_gem_vivianite", has(TagDC.ItemTag.GEM_VIVIANITE))
				.save(cons, "dcs_climate:magic/craft_extract_black2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_BLACK.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_JET)
				.unlockedBy("has_gem_jet", has(TagDC.ItemTag.GEM_JET))
				.save(cons, "dcs_climate:magic/craft_extract_black3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_RED.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_DESERTROSE)
				.unlockedBy("has_gem_desertrose", has(TagDC.ItemTag.GEM_DESERTROSE))
				.save(cons, "dcs_climate:magic/craft_extract_red2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_RED.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_JASPER)
				.unlockedBy("has_gem_jasper", has(TagDC.ItemTag.GEM_JASPER))
				.save(cons, "dcs_climate:magic/craft_extract_red3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_GREEN.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_SERPENTINE)
				.unlockedBy("has_gem_serpentine", has(TagDC.ItemTag.GEM_SERPENTINE))
				.save(cons, "dcs_climate:magic/craft_extract_green2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.EXTRACT_GREEN.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_MALACHITE)
				.unlockedBy("has_gem_malachite", has(TagDC.ItemTag.GEM_MALACHITE))
				.save(cons, "dcs_climate:magic/craft_extract_green3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_WHITE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_HELIODOR)
				.unlockedBy("has_gem_heliodor", has(TagDC.ItemTag.GEM_HELIODOR))
				.save(cons, "dcs_climate:magic/craft_pigment_white2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_WHITE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_THUNDEREGG)
				.unlockedBy("has_gem_thunder_egg", has(TagDC.ItemTag.GEM_THUNDEREGG))
				.save(cons, "dcs_climate:magic/craft_pigment_white3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLUE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_LARIMAR)
				.unlockedBy("has_gem_larimar", has(TagDC.ItemTag.GEM_LARIMAR))
				.save(cons, "dcs_climate:magic/craft_pigment_blue2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLUE.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_CELESTITE)
				.unlockedBy("has_gem_celestite", has(TagDC.ItemTag.GEM_CELESTITE))
				.save(cons, "dcs_climate:magic/craft_pigment_blue3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLACK.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_IOLITE)
				.unlockedBy("has_gem_iolite", has(TagDC.ItemTag.GEM_IOLITE))
				.save(cons, "dcs_climate:magic/craft_pigment_black2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_BLACK.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_FANG)
				.unlockedBy("has_gem_fang", has(TagDC.ItemTag.GEM_FANG))
				.save(cons, "dcs_climate:magic/craft_pigment_black3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_RED.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_ROSINCA)
				.unlockedBy("has_gem_rosinca", has(TagDC.ItemTag.GEM_ROSINCA))
				.save(cons, "dcs_climate:magic/craft_pigment_red2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_RED.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_ALMANDINE)
				.unlockedBy("has_gem_almandine", has(TagDC.ItemTag.GEM_ALMANDINE))
				.save(cons, "dcs_climate:magic/craft_pigment_red3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_GREEN.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_AMAZONITE)
				.unlockedBy("has_gem_amazonite", has(TagDC.ItemTag.GEM_AMAZONITE))
				.save(cons, "dcs_climate:magic/craft_pigment_green2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MagicInit.PIGMENT_GREEN.get(), 1)
				.requires(MagicInit.DROP_MANA.get())
				.requires(TagDC.ItemTag.GEM_OLIVINE)
				.unlockedBy("has_gem_olivine", has(TagDC.ItemTag.GEM_OLIVINE))
				.save(cons, "dcs_climate:magic/craft_pigment_green3");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WATER_BUCKET, 1)
				.requires(MagicInit.EXTRACT_BLUE.get())
				.requires(Items.BUCKET)
				.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
				.save(cons, "dcs_climate:magic/craft_pigment_green3");

	}

}
