package defeatedcrow.hac.machine.recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class MachineRecipeProvider extends RecipeProvider {

	public MachineRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> cons) {
		craftRecipes(cons);
	}

	static void craftRecipes(Consumer<FinishedRecipe> cons) {
		ShapedRecipeBuilder.shaped(MachineInit.MOTOR_TIER1.get(), 1)
			.pattern("XYX")
			.pattern("ZYZ")
			.pattern("ZYZ")
			.define('X', TagDC.ItemTag.RAW_MAGNETITE)
			.define('Y', Tags.Items.INGOTS_IRON)
			.define('Z', Tags.Items.INGOTS_COPPER)
			.unlockedBy("has_raw_magnetite", has(TagDC.ItemTag.RAW_MAGNETITE))
			.save(cons, "dcs_climate:machine/small_motor_0");

		ShapedRecipeBuilder.shaped(MachineInit.CHAMBER_BRICK_A.get(), 1)
			.pattern("X X")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Tags.Items.INGOTS_BRICK)
			.define('Y', Items.CAMPFIRE)
			.unlockedBy("has_campfire", has(Items.CAMPFIRE))
			.save(cons, "dcs_climate:machine/bricks_stove_a_0");

		ShapedRecipeBuilder.shaped(MachineInit.CHAMBER_BRICK_B.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("X X")
			.define('X', Tags.Items.INGOTS_BRICK)
			.define('Y', Items.CAMPFIRE)
			.unlockedBy("has_campfire", has(Items.CAMPFIRE))
			.save(cons, "dcs_climate:machine/bricks_stove_b_0");

		ShapedRecipeBuilder.shaped(MachineInit.CHAMBER_IRON.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Tags.Items.INGOTS_IRON)
			.define('Y', Items.CAMPFIRE)
			.unlockedBy("has_campfire", has(Items.CAMPFIRE))
			.save(cons, "dcs_climate:machine/iron_stove_0");

		ShapedRecipeBuilder.shaped(MachineInit.CHAMBER_IRON.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', TagUtil.BRONZE_OR_BRASS)
			.define('Y', Items.CAMPFIRE)
			.unlockedBy("has_campfire", has(Items.CAMPFIRE))
			.save(cons, "dcs_climate:machine/iron_stove_1");

		ShapedRecipeBuilder.shaped(MachineInit.PORTABLE_CAN.get(), 1)
			.pattern(" X ")
			.pattern("XYX")
			.pattern(" X ")
			.define('X', Tags.Items.INGOTS_IRON)
			.define('Y', Items.BUCKET)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(cons, "dcs_climate:machine/portable_can_0");

		ShapedRecipeBuilder.shaped(MachineInit.PORTABLE_CAN.get(), 1)
			.pattern(" X ")
			.pattern("XYX")
			.pattern(" X ")
			.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
			.define('Y', Items.BUCKET)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(cons, "dcs_climate:machine/portable_can_1");

		ShapedRecipeBuilder.shaped(MachineInit.PORTABLE_CAN.get(), 1)
			.pattern(" X ")
			.pattern("XYX")
			.pattern(" X ")
			.define('X', TagUtil.BRONZE_OR_BRASS)
			.define('Y', Items.BUCKET)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(cons, "dcs_climate:machine/portable_can_2");

		ShapelessRecipeBuilder.shapeless(MachineInit.PORTABLE_CAN_WHITE.get(), 1)
			.requires(MachineInit.PORTABLE_CAN.get())
			.requires(TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_portable_can", has(MachineInit.PORTABLE_CAN.get()))
			.save(cons, "dcs_climate:machine/portable_can_white_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.PORTABLE_CAN_BLUE.get(), 1)
			.requires(MachineInit.PORTABLE_CAN.get())
			.requires(TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_portable_can", has(MachineInit.PORTABLE_CAN.get()))
			.save(cons, "dcs_climate:machine/portable_can_blue_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.PORTABLE_CAN_BLACK.get(), 1)
			.requires(MachineInit.PORTABLE_CAN.get())
			.requires(TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_portable_can", has(MachineInit.PORTABLE_CAN.get()))
			.save(cons, "dcs_climate:machine/portable_can_black_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.PORTABLE_CAN_RED.get(), 1)
			.requires(MachineInit.PORTABLE_CAN.get())
			.requires(TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_portable_can", has(MachineInit.PORTABLE_CAN.get()))
			.save(cons, "dcs_climate:machine/portable_can_red_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.PORTABLE_CAN_GREEN.get(), 1)
			.requires(MachineInit.PORTABLE_CAN.get())
			.requires(TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_portable_can", has(MachineInit.PORTABLE_CAN.get()))
			.save(cons, "dcs_climate:machine/portable_can_green_0");

		ShapedRecipeBuilder.shaped(MachineInit.IBC.get(), 1)
			.pattern("ZYZ")
			.pattern("XXX")
			.define('X', TagDC.ItemTag.INGOT_NICKEL_SILVER)
			.define('Y', Tags.Items.GLASS)
			.define('Z', Items.IRON_BARS)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(cons, "dcs_climate:machine/ibc_0");

		ShapedRecipeBuilder.shaped(MachineInit.STONE_MILL.get(), 1)
			.pattern("ZYZ")
			.pattern("XXX")
			.pattern("XWX")
			.define('X', TagDC.ItemTag.INGOT_STEEL)
			.define('Y', TagDC.ItemTag.MOTOR_T1)
			.define('Z', Tags.Items.STONE)
			.define('W', Tags.Items.CHESTS)
			.unlockedBy("has_motor_t1", has(TagDC.ItemTag.MOTOR_T1))
			.save(cons, "dcs_climate:machine/stone_mill_0");

		ShapedRecipeBuilder.shaped(MachineInit.COOKING_POT_NORMAL.get(), 1)
			.pattern("XYX")
			.pattern("X X")
			.pattern("XXX")
			.define('X', TagDC.ItemTag.INGOT_STEEL)
			.define('Y', Tags.Items.GLASS)
			.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
			.save(cons, "dcs_climate:machine/cooking_pot_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.COOKING_POT_WHITE.get(), 1)
			.requires(MachineInit.COOKING_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_cooking_pot", has(MachineInit.COOKING_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/cooking_pot_white_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.COOKING_POT_BLUE.get(), 1)
			.requires(MachineInit.COOKING_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_cooking_pot", has(MachineInit.COOKING_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/cooking_pot_blue_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.COOKING_POT_BLACK.get(), 1)
			.requires(MachineInit.COOKING_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_cooking_pot", has(MachineInit.COOKING_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/cooking_pot_black_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.COOKING_POT_RED.get(), 1)
			.requires(MachineInit.COOKING_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_cooking_pot", has(MachineInit.COOKING_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/cooking_pot_red_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.COOKING_POT_GREEN.get(), 1)
			.requires(MachineInit.COOKING_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_cooking_pot", has(MachineInit.COOKING_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/cooking_pot_green_0");

		ShapedRecipeBuilder.shaped(MachineInit.TEA_POT_NORMAL.get(), 1)
			.pattern(" X ")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', TagDC.ItemTag.INGOT_STEEL)
			.define('Y', TagDC.ItemTag.CLOTHS)
			.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
			.save(cons, "dcs_climate:machine/tea_pot_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.TEA_POT_WHITE.get(), 1)
			.requires(MachineInit.TEA_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_cooking_pot", has(MachineInit.TEA_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/tea_pot_white_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.TEA_POT_BLUE.get(), 1)
			.requires(MachineInit.TEA_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_cooking_pot", has(MachineInit.TEA_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/tea_pot_blue_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.TEA_POT_BLACK.get(), 1)
			.requires(MachineInit.TEA_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_cooking_pot", has(MachineInit.TEA_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/tea_pot_black_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.TEA_POT_RED.get(), 1)
			.requires(MachineInit.TEA_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_cooking_pot", has(MachineInit.TEA_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/tea_pot_red_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.TEA_POT_GREEN.get(), 1)
			.requires(MachineInit.TEA_POT_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_cooking_pot", has(MachineInit.TEA_POT_NORMAL.get()))
			.save(cons, "dcs_climate:machine/tea_pot_green_0");

		ShapedRecipeBuilder.shaped(MachineInit.FERMANTATION_JAR_NORMAL.get(), 1)
			.pattern(" X ")
			.pattern("X X")
			.pattern("XXX")
			.define('X', Tags.Items.INGOTS_BRICK)
			.unlockedBy("has_bricks", has(Tags.Items.INGOTS_BRICK))
			.save(cons, "dcs_climate:machine/farmentation_jar_0");

		ShapedRecipeBuilder.shaped(MachineInit.FERMANTATION_JAR_NORMAL.get(), 1)
			.pattern(" X ")
			.pattern("X X")
			.pattern("XXX")
			.define('X', ItemTags.TERRACOTTA)
			.unlockedBy("has_terracotta", has(ItemTags.TERRACOTTA))
			.save(cons, "dcs_climate:machine/farmentation_jar_1");

		ShapelessRecipeBuilder.shapeless(MachineInit.FERMANTATION_JAR_WHITE.get(), 1)
			.requires(MachineInit.FERMANTATION_JAR_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_WHITE)
			.unlockedBy("has_farmentation_jar", has(MachineInit.FERMANTATION_JAR_NORMAL.get()))
			.save(cons, "dcs_climate:machine/farmentation_jar_white_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.FERMANTATION_JAR_BLUE.get(), 1)
			.requires(MachineInit.FERMANTATION_JAR_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_BLUE)
			.unlockedBy("has_farmentation_jar", has(MachineInit.FERMANTATION_JAR_NORMAL.get()))
			.save(cons, "dcs_climate:machine/farmentation_jar_blue_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.FERMANTATION_JAR_BLACK.get(), 1)
			.requires(MachineInit.FERMANTATION_JAR_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_BLACK)
			.unlockedBy("has_farmentation_jar", has(MachineInit.FERMANTATION_JAR_NORMAL.get()))
			.save(cons, "dcs_climate:machine/farmentation_jar_black_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.FERMANTATION_JAR_RED.get(), 1)
			.requires(MachineInit.FERMANTATION_JAR_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_RED)
			.unlockedBy("has_farmentation_jar", has(MachineInit.FERMANTATION_JAR_NORMAL.get()))
			.save(cons, "dcs_climate:machine/farmentation_jar_red_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.FERMANTATION_JAR_GREEN.get(), 1)
			.requires(MachineInit.FERMANTATION_JAR_NORMAL.get())
			.requires(TagDC.ItemTag.EXTRACT_GREEN)
			.unlockedBy("has_farmentation_jar", has(MachineInit.FERMANTATION_JAR_NORMAL.get()))
			.save(cons, "dcs_climate:machine/farmentation_jar_green_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.SPILE.get(), 1)
			.requires(Items.BUCKET)
			.requires(Tags.Items.STRING)
			.requires(Items.TRIPWIRE_HOOK)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(cons, "dcs_climate:machine/spile_and_cup_0");

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
