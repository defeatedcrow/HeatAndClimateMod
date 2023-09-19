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
