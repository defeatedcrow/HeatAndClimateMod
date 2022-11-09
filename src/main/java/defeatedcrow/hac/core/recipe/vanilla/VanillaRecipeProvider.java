package defeatedcrow.hac.core.recipe.vanilla;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.material.tag.TagDC;
import defeatedcrow.hac.core.recipe.MatDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class VanillaRecipeProvider extends RecipeProvider {

	public VanillaRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> cons) {

		for (MatDC.Color color : MatDC.COLOR_VARIANT) {
			mortarMetalRecipes(cons, color);
		}

		for (MatDC.Gem gem : MatDC.GEM_VARIANT) {
			mortarGemRecipes(cons, gem);
		}

		mortarOtherRecipes(cons);

		for (MatDC.Alloy alloy : MatDC.ALLOY_VARIANT) {
			alloyRecipes(cons, alloy);
		}

		for (MatDC.Wood wood : MatDC.WOOD_VARIANT) {
			woodRecipes(cons, wood);
		}

		for (MatDC.Stone stone : MatDC.STONE_VARIANT) {
			stoneRecipes(cons, stone);
		}

	}

	private static void alloyRecipes(Consumer<FinishedRecipe> cons, MatDC.Alloy alloy) {

		ShapedRecipeBuilder.shaped(alloy.metalBlock().get())
			.pattern("XXX")
			.pattern("XXX")
			.pattern("XXX")
			.define('X', alloy.ingotItem().get())
			.group("storage_pack")
			.unlockedBy("has_" + alloy.name() + "_ingot", has(alloy.ingotItem().get()))
			.save(cons, "dcs_climate:core/metalblock_" + alloy.name());

		ShapelessRecipeBuilder.shapeless(alloy.ingotItem().get(), 9)
			.requires(alloy.getTag("storage_blocks"))
			.group("storage_unpack")
			.unlockedBy("has_" + alloy.name() + "_ingot", has(alloy.ingotItem().get()))
			.save(cons, "dcs_climate:core/ingot_" + alloy.name());

		if (alloy == MatDC.BSCCO) {

			ShapedRecipeBuilder.shaped(alloy.dustBlock().get())
				.pattern("LLL")
				.pattern("PPP")
				.pattern("SST")
				.define('L', CoreInit.DUST_LIME.get())
				.define('P', alloy.dustPrimary().get())
				.define('S', alloy.dustSecondary().get())
				.define('T', alloy.dustTertiary().get())
				.unlockedBy("has_" + DCUtil.getName(alloy.dustPrimary().get()), has(alloy.dustPrimary().get()))
				.group("dustblock_pack")
				.save(cons, "dcs_climate:core/dustblock_" + alloy.name());

		} else if (alloy == MatDC.TOOL_STEEL) {

			ShapedRecipeBuilder.shaped(alloy.dustBlock().get())
				.pattern("PPP")
				.pattern("PPP")
				.pattern("LST")
				.define('L', TagDC.ItemTag.DUST_MANGANESE)
				.define('P', alloy.dustPrimary().get())
				.define('S', alloy.dustSecondary().get())
				.define('T', alloy.dustTertiary().get())
				.unlockedBy("has_" + DCUtil.getName(alloy.dustPrimary().get()), has(alloy.dustPrimary().get()))
				.group("dustblock_pack")
				.save(cons, "dcs_climate:core/dustblock_" + alloy.name());

		} else {

			ShapedRecipeBuilder.shaped(alloy.dustBlock().get())
				.pattern("PPP")
				.pattern("PPP")
				.pattern("SST")
				.define('P', alloy.dustPrimary().get())
				.define('S', alloy.dustSecondary().get())
				.define('T', alloy.dustTertiary().get())
				.unlockedBy("has_" + DCUtil.getName(alloy.dustPrimary().get()), has(alloy.dustPrimary().get()))
				.group("dustblock_pack")
				.save(cons, "dcs_climate:core/dustblock_" + alloy.name());

		}

	}

	private static void mortarMetalRecipes(Consumer<FinishedRecipe> cons, MatDC.Color color) {

		if (color.oreBlock().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreBlock().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.orePri().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_" + color.name() + "_ore", has(color.oreBlock().get().asItem()))
				.save(cons, "dcs_climate:core/mortar_ore_" + color.name());
		}

		if (color.oreDeep().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreDeep().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.oreSec().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_" + color.name() + "_deepore", has(color.oreDeep().get().asItem()))
				.save(cons, "dcs_climate:core/mortar_deepore_" + color.name());
		}

		if (color.gemPri().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.orePri().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.dustPri().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_" + color.name() + "_ore", has(color.oreBlock().get().asItem()))
				.save(cons, "dcs_climate:core/mortar_gem1_" + color.name());
		}

		if (color.gemSec().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreSec().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.dustSec().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_" + color.name() + "_deepore", has(color.oreDeep().get().asItem()))
				.save(cons, "dcs_climate:core/mortar_gem2_" + color.name());
		}

		if (color.gemTert().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreTert().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.dustTert().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_" + color.name() + "_deepore", has(color.oreDeep().get().asItem()))
				.save(cons, "dcs_climate:core/mortar_gem3_" + color.name());
		}

	}

	private static void mortarGemRecipes(Consumer<FinishedRecipe> cons, MatDC.Gem gem) {
		if (gem.ore().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) gem.ore().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(gem.gem().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_" + gem.name() + "_ore", has(gem.ore().get().asItem()))
				.save(cons, "dcs_climate:core/mortar_ore_" + gem.name());
		}
	}

	private static void mortarOtherRecipes(Consumer<FinishedRecipe> cons) {

		ShapelessRecipeBuilder.shapeless(CoreInit.GEM_SALT.get(), 1)
			.requires(TagDC.ItemTag.ORES_SALT)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_salt_ore", has(TagDC.ItemTag.ORES_SALT))
			.save(cons, "dcs_climate:core/mortar_gem_salt");

		ShapelessRecipeBuilder.shapeless(CoreInit.GEM_NITER.get(), 1)
			.requires(TagDC.ItemTag.ORES_NITER)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_niter_ore", has(TagDC.ItemTag.ORES_NITER))
			.save(cons, "dcs_climate:core/mortar_gem_niter");

		ShapelessRecipeBuilder.shapeless(CoreInit.GEM_SULFUR.get(), 1)
			.requires(TagDC.ItemTag.ORES_SULFUR)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_sulfur_ore", has(TagDC.ItemTag.ORES_SULFUR))
			.save(cons, "dcs_climate:core/mortar_gem_sulfur");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_CRYSTAL.get(), 1)
			.requires(TagDC.ItemTag.GEM_CHALCEDONY)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_chalcedony_gem", has(TagDC.ItemTag.GEM_CHALCEDONY))
			.save(cons, "dcs_climate:core/mortar_dust_crystal1");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_CRYSTAL.get(), 1)
			.requires(TagDC.ItemTag.GEM_CRYSTAL)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_crystal_gem", has(TagDC.ItemTag.GEM_CRYSTAL))
			.save(cons, "dcs_climate:core/mortar_dust_crystal2");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_CRYSTAL.get(), 1)
			.requires(Tags.Items.GEMS_QUARTZ)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_quartz", has(Tags.Items.GEMS_QUARTZ))
			.save(cons, "dcs_climate:core/mortar_dust_crystal3");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_LIME.get(), 1)
			.requires(TagDC.ItemTag.ORES_LIME)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_lime", has(TagDC.ItemTag.ORES_LIME))
			.save(cons, "dcs_climate:core/mortar_dust_lime1");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_NITER.get(), 1)
			.requires(TagDC.ItemTag.GEM_NITER)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_niter_gem", has(TagDC.ItemTag.GEM_NITER))
			.save(cons, "dcs_climate:core/mortar_dust_niter");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_SULFUR.get(), 1)
			.requires(TagDC.ItemTag.GEM_SULFUR)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_sulfur_gem", has(TagDC.ItemTag.GEM_SULFUR))
			.save(cons, "dcs_climate:core/mortar_dust_sulfur");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_COAL.get(), 1)
			.requires(TagDC.ItemTag.GEM_COAL)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_coal_gem", has(TagDC.ItemTag.GEM_COAL))
			.save(cons, "dcs_climate:core/mortar_dust_coal");

	}

	private static void woodRecipes(Consumer<FinishedRecipe> cons, MatDC.Wood wood) {

		ShapelessRecipeBuilder.shapeless(wood.plankBlock().get(), 4)
			.requires(wood.logBlock().get())
			.unlockedBy("has_" + wood.name() + "_log", has(wood.logBlock().get()))
			.save(cons, "dcs_climate:food/planks_" + wood.name());

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

	private static void stoneRecipes(Consumer<FinishedRecipe> cons, MatDC.Stone stone) {

		ShapedRecipeBuilder.shaped(stone.bricksBlock().get(), 4)
			.pattern("XX")
			.pattern("XX")
			.define('X', stone.stoneBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:build/bricks2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.pillarBlock().get(), 4)
			.pattern("XX")
			.pattern("XX")
			.define('X', stone.bricksBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:build/pillar2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.chiseledBlock().get(), 4)
			.pattern("XX")
			.pattern("XX")
			.define('X', stone.pillarBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:build/chiseled2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.stoneBlock().get(), 4)
			.pattern("XX")
			.pattern("XX")
			.define('X', stone.chiseledBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:build/reverse2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.stairsBlock().get(), 4)
			.pattern("X  ")
			.pattern("XX ")
			.pattern("XXX")
			.define('X', stone.stoneBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:core/stairs2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.slabBlock().get(), 6)
			.pattern("XXX")
			.define('X', stone.stoneBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:core/slab2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.wallBlock().get(), 6)
			.pattern("XXX")
			.pattern("XXX")
			.define('X', stone.stoneBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:core/wall2_" + stone.name());

	}

	@Override
	public void run(CachedOutput cache) {

		Set<ResourceLocation> set = Sets.newHashSet();
		buildCraftingRecipes((recipe) -> {
			if (!set.add(recipe.getId())) {
				throw new IllegalStateException("Duplicate recipe " + recipe.getId());
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
