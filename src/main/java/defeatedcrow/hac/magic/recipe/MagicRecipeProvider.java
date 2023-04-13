package defeatedcrow.hac.magic.recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class MagicRecipeProvider extends RecipeProvider {

	public MagicRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> cons) {
		craftRecipes(cons);
		mortarRecipes(cons);
	}

	static void craftRecipes(Consumer<FinishedRecipe> cons) {
		// arrow
		ShapedRecipeBuilder.shaped(MagicInit.ARROW_WHITE.get(), 8)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Items.ARROW)
			.define('Y', TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
			.save(cons, "dcs_climate:magic/craft_arrow_white");

		ShapedRecipeBuilder.shaped(MagicInit.ARROW_BLUE.get(), 8)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Items.ARROW)
			.define('Y', TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
			.save(cons, "dcs_climate:magic/craft_arrow_blue");

		ShapedRecipeBuilder.shaped(MagicInit.ARROW_BLACK.get(), 8)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Items.ARROW)
			.define('Y', TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
			.save(cons, "dcs_climate:magic/craft_arrow_black");

		ShapedRecipeBuilder.shaped(MagicInit.ARROW_RED.get(), 8)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Items.ARROW)
			.define('Y', TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
			.save(cons, "dcs_climate:magic/craft_arrow_red");

		ShapedRecipeBuilder.shaped(MagicInit.ARROW_GREEN.get(), 8)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Items.ARROW)
			.define('Y', TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
			.save(cons, "dcs_climate:magic/craft_arrow_green");

		// seedbag
		ShapelessRecipeBuilder.shapeless(MagicInit.SEEDBAG_WHITE.get(), 1)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
			.save(cons, "dcs_climate:magic/craft_seedbag_white");

		ShapelessRecipeBuilder.shapeless(MagicInit.SEEDBAG_BLUE.get(), 1)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
			.save(cons, "dcs_climate:magic/craft_seedbag_blue");

		ShapelessRecipeBuilder.shapeless(MagicInit.SEEDBAG_BLACK.get(), 1)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
			.save(cons, "dcs_climate:magic/craft_seedbag_black");

		ShapelessRecipeBuilder.shapeless(MagicInit.SEEDBAG_RED.get(), 1)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
			.save(cons, "dcs_climate:magic/craft_seedbag_red");

		ShapelessRecipeBuilder.shapeless(MagicInit.SEEDBAG_GREEN.get(), 1)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(Tags.Items.SEEDS)
			.requires(TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
			.save(cons, "dcs_climate:magic/craft_seedbag_green");

		// card
		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_WHITE_1.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
			.save(cons, "dcs_climate:magic/craft_card_white_common");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_BLUE_1.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
			.save(cons, "dcs_climate:magic/craft_card_blue_common");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_BLACK_1.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
			.save(cons, "dcs_climate:magic/craft_card_black_common");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_RED_1.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
			.save(cons, "dcs_climate:magic/craft_card_red_common");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_GREEN_1.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
			.save(cons, "dcs_climate:magic/craft_card_green_common");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_WHITE_2.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.PIGMENT_WHITE)
			.unlockedBy("has_pigment_white", has(TagDC.ItemTag.PIGMENT_WHITE))
			.save(cons, "dcs_climate:magic/craft_card_white_uncommon");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_BLUE_2.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.PIGMENT_BLUE)
			.unlockedBy("has_pigment_blue", has(TagDC.ItemTag.PIGMENT_BLUE))
			.save(cons, "dcs_climate:magic/craft_card_blue_uncommon");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_BLACK_2.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.PIGMENT_BLACK)
			.unlockedBy("has_pigment_black", has(TagDC.ItemTag.PIGMENT_BLACK))
			.save(cons, "dcs_climate:magic/craft_card_black_uncommon");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_RED_2.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.PIGMENT_RED)
			.unlockedBy("has_pigment_red", has(TagDC.ItemTag.PIGMENT_RED))
			.save(cons, "dcs_climate:magic/craft_card_red_uncommon");

		ShapelessRecipeBuilder.shapeless(MagicInit.CARD_GREEN_2.get(), 1)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(Items.PAPER)
			.requires(TagDC.ItemTag.PIGMENT_GREEN)
			.unlockedBy("has_pigment_green", has(TagDC.ItemTag.PIGMENT_GREEN))
			.save(cons, "dcs_climate:magic/craft_card_green_uncommon");

		// ring
		ShapedRecipeBuilder.shaped(MagicInit.RING_SILVER_WHITE.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_WHITE)
			.define('Z', TagDC.ItemTag.INGOT_SILVER)
			.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
			.save(cons, "dcs_climate:magic/craft_ring_silver_white");

		ShapedRecipeBuilder.shaped(MagicInit.RING_SILVER_BLUE.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_BLUE)
			.define('Z', TagDC.ItemTag.INGOT_SILVER)
			.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
			.save(cons, "dcs_climate:magic/craft_ring_silver_blue");

		ShapedRecipeBuilder.shaped(MagicInit.RING_SILVER_BLACK.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_BLACK)
			.define('Z', TagDC.ItemTag.INGOT_SILVER)
			.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
			.save(cons, "dcs_climate:magic/craft_ring_silver_black");

		ShapedRecipeBuilder.shaped(MagicInit.RING_SILVER_RED.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_RED)
			.define('Z', TagDC.ItemTag.INGOT_SILVER)
			.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
			.save(cons, "dcs_climate:magic/craft_ring_silver_red");

		ShapedRecipeBuilder.shaped(MagicInit.RING_SILVER_GREEN.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_GREEN)
			.define('Z', TagDC.ItemTag.INGOT_SILVER)
			.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
			.save(cons, "dcs_climate:magic/craft_ring_silver_green");

		ShapedRecipeBuilder.shaped(MagicInit.RING_GOLD_WHITE.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_WHITE)
			.define('Z', Tags.Items.INGOTS_GOLD)
			.unlockedBy("has_extract_white", has(TagDC.ItemTag.EXTRACT_WHITE))
			.save(cons, "dcs_climate:magic/craft_ring_gold_white");

		ShapedRecipeBuilder.shaped(MagicInit.RING_GOLD_BLUE.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_BLUE)
			.define('Z', Tags.Items.INGOTS_GOLD)
			.unlockedBy("has_extract_blue", has(TagDC.ItemTag.EXTRACT_BLUE))
			.save(cons, "dcs_climate:magic/craft_ring_gold_blue");

		ShapedRecipeBuilder.shaped(MagicInit.RING_GOLD_BLACK.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_BLACK)
			.define('Z', Tags.Items.INGOTS_GOLD)
			.unlockedBy("has_extract_black", has(TagDC.ItemTag.EXTRACT_BLACK))
			.save(cons, "dcs_climate:magic/craft_ring_gold_black");

		ShapedRecipeBuilder.shaped(MagicInit.RING_GOLD_RED.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_RED)
			.define('Z', Tags.Items.INGOTS_GOLD)
			.unlockedBy("has_extract_red", has(TagDC.ItemTag.EXTRACT_RED))
			.save(cons, "dcs_climate:magic/craft_ring_gold_red");

		ShapedRecipeBuilder.shaped(MagicInit.RING_GOLD_GREEN.get(), 1)
			.pattern("X")
			.pattern("Y")
			.pattern("Z")
			.define('X', TagDC.ItemTag.DUST_CRYSTAL)
			.define('Y', TagDC.ItemTag.EXTRACT_GREEN)
			.define('Z', Tags.Items.INGOTS_GOLD)
			.unlockedBy("has_extract_green", has(TagDC.ItemTag.EXTRACT_GREEN))
			.save(cons, "dcs_climate:magic/craft_ring_gold_green");
	}

	static void mortarRecipes(Consumer<FinishedRecipe> cons) {
		// magic drops

		ShapelessRecipeBuilder.shapeless(MagicInit.DROP_WHITE.get(), 1)
			.requires(TagDC.ItemTag.CROP_OSUMANTHUS)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crop_osmanthus", has(TagDC.ItemTag.CROP_OSUMANTHUS))
			.save(cons, "dcs_climate:magic/mortar_crop_osmanthus");

		ShapelessRecipeBuilder.shapeless(MagicInit.DROP_WHITE.get(), 1)
			.requires(TagDC.ItemTag.CROP_CHRYSANTHEMUM)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crop_chrysanthemum", has(TagDC.ItemTag.CROP_CHRYSANTHEMUM))
			.save(cons, "dcs_climate:magic/mortar_crop_chrysanthemum");

		ShapelessRecipeBuilder.shapeless(MagicInit.DROP_BLUE.get(), 1)
			.requires(TagDC.ItemTag.CROP_LAVENDER)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crop_lavender", has(TagDC.ItemTag.CROP_LAVENDER))
			.save(cons, "dcs_climate:magic/mortar_crop_lavender");

		ShapelessRecipeBuilder.shapeless(MagicInit.DROP_BLACK.get(), 1)
			.requires(TagDC.ItemTag.CROP_DEVILSCLAW)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crop_devilsclaw", has(TagDC.ItemTag.CROP_DEVILSCLAW))
			.save(cons, "dcs_climate:magic/mortar_crop_devilsclaw");

		ShapelessRecipeBuilder.shapeless(MagicInit.DROP_RED.get(), 1)
			.requires(TagDC.ItemTag.CROP_LANTERN)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crop_lantern", has(TagDC.ItemTag.CROP_LANTERN))
			.save(cons, "dcs_climate:magic/mortar_crop_lantern");

		ShapelessRecipeBuilder.shapeless(MagicInit.DROP_GREEN.get(), 1)
			.requires(TagDC.ItemTag.CROP_MORNING_GLORY)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crop_morning_glory", has(TagDC.ItemTag.CROP_MORNING_GLORY))
			.save(cons, "dcs_climate:magic/mortar_crop_morning_glory");

		ShapedRecipeBuilder.shaped(MagicInit.DROP_MANA.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', TagDC.ItemTag.CROP_NIGHTSHADE)
			.define('Y', CoreInit.MORTAR.get())
			.unlockedBy("has_crop_nightshade", has(TagDC.ItemTag.CROP_NIGHTSHADE))
			.save(cons, "dcs_climate:magic/mortar_crop_nightshade");

		ShapedRecipeBuilder.shaped(MagicInit.DROP_MANA.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', TagDC.ItemTag.CROP_ASH)
			.define('Y', CoreInit.MORTAR.get())
			.unlockedBy("has_crop_ash", has(TagDC.ItemTag.CROP_ASH))
			.save(cons, "dcs_climate:magic/mortar_crop_ash");

		ShapedRecipeBuilder.shaped(MagicInit.EXTRACT_WHITE.get(), 1)
			.pattern("XXX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', MagicInit.DROP_WHITE.get())
			.unlockedBy("has_drop_white", has(MagicInit.DROP_WHITE.get()))
			.save(cons, "dcs_climate:magic/craft_extract_white");

		ShapedRecipeBuilder.shaped(MagicInit.EXTRACT_BLUE.get(), 1)
			.pattern("XXX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', MagicInit.DROP_BLUE.get())
			.unlockedBy("has_drop_blue", has(MagicInit.DROP_BLUE.get()))
			.save(cons, "dcs_climate:magic/craft_extract_blue");

		ShapedRecipeBuilder.shaped(MagicInit.EXTRACT_BLACK.get(), 1)
			.pattern("XXX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', MagicInit.DROP_BLACK.get())
			.unlockedBy("has_drop_black", has(MagicInit.DROP_BLACK.get()))
			.save(cons, "dcs_climate:magic/craft_extract_black");

		ShapedRecipeBuilder.shaped(MagicInit.EXTRACT_RED.get(), 1)
			.pattern("XXX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', MagicInit.DROP_RED.get())
			.unlockedBy("has_drop_red", has(MagicInit.DROP_RED.get()))
			.save(cons, "dcs_climate:magic/craft_extract_red");

		ShapedRecipeBuilder.shaped(MagicInit.EXTRACT_GREEN.get(), 1)
			.pattern("XXX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', MagicInit.DROP_GREEN.get())
			.unlockedBy("has_drop_green", has(MagicInit.DROP_GREEN.get()))
			.save(cons, "dcs_climate:magic/craft_extract_green");

		ShapedRecipeBuilder.shaped(MagicInit.EXTRACT_MANA.get(), 1)
			.pattern("XXX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', MagicInit.DROP_MANA.get())
			.unlockedBy("has_drop_mana", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_mana");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_WHITE.get(), 1)
			.requires(MagicInit.EXTRACT_MANA.get())
			.requires(MagicInit.EXTRACT_WHITE.get())
			.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_white");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_BLUE.get(), 1)
			.requires(MagicInit.EXTRACT_MANA.get())
			.requires(MagicInit.EXTRACT_BLUE.get())
			.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_blue");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_BLACK.get(), 1)
			.requires(MagicInit.EXTRACT_MANA.get())
			.requires(MagicInit.EXTRACT_BLACK.get())
			.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_black");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_RED.get(), 1)
			.requires(MagicInit.EXTRACT_MANA.get())
			.requires(MagicInit.EXTRACT_RED.get())
			.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_red");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_GREEN.get(), 1)
			.requires(MagicInit.EXTRACT_MANA.get())
			.requires(MagicInit.EXTRACT_GREEN.get())
			.unlockedBy("has_extrast_mana", has(MagicInit.EXTRACT_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_green");

		// gems

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_WHITE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_CHALCEDONY)
			.unlockedBy("has_gem_chalcedony", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_white2");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_WHITE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_CRYSTAL)
			.unlockedBy("has_gem_crystal", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_white3");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_BLUE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(Tags.Items.GEMS_LAPIS)
			.unlockedBy("has_gem_lapis", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_blue2");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_BLUE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_FLUORITE)
			.unlockedBy("has_gem_fluorite", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_blue3");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_BLACK.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_VIVIANITE)
			.unlockedBy("has_gem_vivianite", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_black2");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_BLACK.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_JET)
			.unlockedBy("has_gem_jet", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_black3");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_RED.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_DESERTROSE)
			.unlockedBy("has_gem_desertrose", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_red2");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_RED.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_JASPER)
			.unlockedBy("has_gem_jasper", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_red3");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_GREEN.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_SERPENTINE)
			.unlockedBy("has_gem_serpentine", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_green2");

		ShapelessRecipeBuilder.shapeless(MagicInit.EXTRACT_GREEN.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_MALACHITE)
			.unlockedBy("has_gem_malachite", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_extract_green3");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_WHITE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_HELIODOR)
			.unlockedBy("has_gem_heliodor", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_white2");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_WHITE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_THUNDEREGG)
			.unlockedBy("has_gem_thunder_egg", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_white3");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_BLUE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_LARIMAR)
			.unlockedBy("has_gem_larimar", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_blue2");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_BLUE.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_CELESTITE)
			.unlockedBy("has_gem_celestite", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_blue3");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_BLACK.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_IOLITE)
			.unlockedBy("has_gem_iolite", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_black2");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_BLACK.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_FANG)
			.unlockedBy("has_gem_fang", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_black3");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_RED.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_ROSINCA)
			.unlockedBy("has_gem_rosinca", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_red2");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_RED.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_ALMANDINE)
			.unlockedBy("has_gem_almandine", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_red3");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_GREEN.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_AMAZONITE)
			.unlockedBy("has_gem_amazonite", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_green2");

		ShapelessRecipeBuilder.shapeless(MagicInit.PIGMENT_GREEN.get(), 1)
			.requires(MagicInit.DROP_MANA.get())
			.requires(TagDC.ItemTag.GEM_OLIVINE)
			.unlockedBy("has_gem_olivine", has(MagicInit.DROP_MANA.get()))
			.save(cons, "dcs_climate:magic/craft_pigment_green3");

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
