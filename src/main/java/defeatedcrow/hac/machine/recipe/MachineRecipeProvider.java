package defeatedcrow.hac.machine.recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
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
			.save(cons, "dcs_climate:magic/craft_bricks_stove_a");

		ShapedRecipeBuilder.shaped(MachineInit.CHAMBER_BRICK_B.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("X X")
			.define('X', Tags.Items.INGOTS_BRICK)
			.define('Y', Items.CAMPFIRE)
			.unlockedBy("has_campfire", has(Items.CAMPFIRE))
			.save(cons, "dcs_climate:magic/craft_bricks_stove_b");

		ShapedRecipeBuilder.shaped(MachineInit.CHAMBER_IRON.get(), 1)
			.pattern("XXX")
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Tags.Items.INGOTS_IRON)
			.define('Y', Items.CAMPFIRE)
			.unlockedBy("has_campfire", has(Items.CAMPFIRE))
			.save(cons, "dcs_climate:magic/craft_iron_stove");

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
