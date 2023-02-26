package defeatedcrow.hac.core.recipe.vanilla;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.recipe.mill.MillsDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class VanillaRecipeProvider extends RecipeProvider {

	public VanillaRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> cons) {

		for (MaterialRecipes.Color color : MaterialRecipes.COLOR_VARIANT) {
			mortarMetalRecipes(cons, color);
		}

		for (MaterialRecipes.Gem gem : MaterialRecipes.GEM_VARIANT) {
			mortarGemRecipes(cons, gem);
		}

		for (MillsDC.Crops crop : MillsDC.INSTANCE.cropRecipe) {
			mortarMillsRecipes(cons, crop);
		}

		mortarOtherRecipes(cons);

		for (MaterialRecipes.Alloy alloy : MaterialRecipes.ALLOY_VARIANT) {
			alloyRecipes(cons, alloy);
		}

		for (MaterialRecipes.Stone stone : MaterialRecipes.STONE_VARIANT) {
			stoneRecipes(cons, stone);
		}

		buildingRecipes(cons);

		otherRecipes(cons);

		smeltingRecipes(cons);

	}

	private static void alloyRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Alloy alloy) {

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

		if (alloy == MaterialRecipes.BSCCO) {

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

		} else if (alloy == MaterialRecipes.HASTELLOY) {

			ShapedRecipeBuilder.shaped(alloy.dustBlock().get())
				.pattern("PPP")
				.pattern("PSS")
				.pattern("TTL")
				.define('L', TagDC.ItemTag.DUST_COBALT)
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

	private static void mortarMetalRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Color color) {

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

	private static void mortarGemRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Gem gem) {
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

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_TRONA.get(), 1)
			.requires(TagDC.ItemTag.ORES_NATRON)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_natron_ore", has(TagDC.ItemTag.ORES_NATRON))
			.save(cons, "dcs_climate:core/mortar_dust_trona");

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

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_SALT.get(), 1)
			.requires(TagDC.ItemTag.GEM_SALT)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_salt_gem", has(TagDC.ItemTag.GEM_SALT))
			.save(cons, "dcs_climate:core/mortar_dust_salt");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_COAL.get(), 1)
			.requires(TagDC.ItemTag.GEM_COAL)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_coal_gem", has(TagDC.ItemTag.GEM_COAL))
			.save(cons, "dcs_climate:core/mortar_dust_coal");

		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_WOOD.get(), 1)
			.requires(ItemTags.PLANKS)
			.requires(CoreInit.MORTAR.get())
			.group("crusher_mortar")
			.unlockedBy("has_planks", has(ItemTags.PLANKS))
			.save(cons, "dcs_climate:core/mortar_dust_wood");

	}

	private static void mortarMillsRecipes(Consumer<FinishedRecipe> cons, MillsDC.Crops mill) {
		if (mill.input().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) mill.input().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(mill.outputPri().get(), 1)
				.requires(ore)
				.requires(CoreInit.MORTAR.get())
				.group("crusher_mortar")
				.unlockedBy("has_crop_" + mill.name(), has(mill.input().get().asItem()))
				.save(cons, "dcs_climate:food/mortar_crop_" + mill.name());
		}
	}

	private static void stoneRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Stone stone) {

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
			.save(cons, "dcs_climate:build/stairs2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.slabBlock().get(), 6)
			.pattern("XXX")
			.define('X', stone.stoneBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:build/slab2_" + stone.name());

		ShapedRecipeBuilder.shaped(stone.wallBlock().get(), 6)
			.pattern("XXX")
			.pattern("XXX")
			.define('X', stone.stoneBlock().get())
			.unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock().get()))
			.save(cons, "dcs_climate:build/wall2_" + stone.name());

	}

	private static void buildingRecipes(Consumer<FinishedRecipe> cons) {

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Tags.Items.COBBLESTONE)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/normal_flintbricks");

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS_BLACK.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Blocks.ANDESITE)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/black_flintbricks");

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS_BLACK.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Blocks.DEEPSLATE)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/black_flintbricks2");

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS_WHITE.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Blocks.DIORITE)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/white_flintbricks");

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS_WHITE.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Blocks.CALCITE)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/white_flintbricks2");

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS_RED.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Blocks.GRANITE)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/red_flintbricks");

		ShapedRecipeBuilder.shaped(BuildInit.FLINTBRICKS_RED.get(), 4)
			.pattern("XY")
			.pattern("YX")
			.define('X', TagDC.ItemTag.GEM_FLINT)
			.define('Y', Blocks.NETHERRACK)
			.unlockedBy("has_flint", has(Items.FLINT))
			.save(cons, "dcs_climate:build/red_flintbricks2");

		ShapelessRecipeBuilder.shapeless(BuildInit.MOSAIC_BLACK.get(), 1)
			.requires(TagDC.ItemTag.DUST_CRYSTAL)
			.requires(Tags.Items.STONE)
			.requires(Tags.Items.DYES_BLACK)
			.unlockedBy("has_dye_black", has(Tags.Items.DYES_BLACK))
			.save(cons, "dcs_climate:build/black_mosaic_block");

		ShapelessRecipeBuilder.shapeless(BuildInit.MOSAIC_BLUE.get(), 1)
			.requires(TagDC.ItemTag.DUST_CRYSTAL)
			.requires(Tags.Items.STONE)
			.requires(Tags.Items.DYES_BLUE)
			.unlockedBy("has_dye_blue", has(Tags.Items.DYES_BLUE))
			.save(cons, "dcs_climate:build/black_mosaic_blue");

		ShapelessRecipeBuilder.shapeless(BuildInit.MOSAIC_YELLOW.get(), 1)
			.requires(TagDC.ItemTag.DUST_CRYSTAL)
			.requires(Tags.Items.STONE)
			.requires(Tags.Items.DYES_YELLOW)
			.unlockedBy("has_dye_yellow", has(Tags.Items.DYES_YELLOW))
			.save(cons, "dcs_climate:build/black_mosaic_yellow");

		ShapelessRecipeBuilder.shapeless(BuildInit.MOSAIC_RED.get(), 1)
			.requires(TagDC.ItemTag.DUST_CRYSTAL)
			.requires(Tags.Items.STONE)
			.requires(Tags.Items.DYES_RED)
			.unlockedBy("has_dye_red", has(Tags.Items.DYES_RED))
			.save(cons, "dcs_climate:build/black_mosaic_red");

	}

	private static void otherRecipes(Consumer<FinishedRecipe> cons) {
		ShapedRecipeBuilder.shaped(CoreInit.MORTAR.get(), 1)
			.pattern("X X")
			.pattern("XXX")
			.define('X', TagDC.ItemTag.GEM_AGATES)
			.unlockedBy("has_chalcedony", has(TagDC.ItemTag.GEM_CHALCEDONY))
			.save(cons, "dcs_climate:core/agate_mortar");

		ShapedRecipeBuilder.shaped(CoreInit.HAND_SPINDLE.get(), 1)
			.pattern("Y")
			.pattern("X")
			.pattern("Y")
			.define('X', ItemTags.PLANKS)
			.define('Y', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(cons, "dcs_climate:core/hand_spindle");

		ShapedRecipeBuilder.shaped(CoreInit.SEEDING_POT.get(), 1)
			.pattern("XYX")
			.pattern("XXX")
			.define('X', Items.PAPER)
			.define('Y', ItemTags.DIRT)
			.unlockedBy("has_dirt", has(ItemTags.DIRT))
			.save(cons, "dcs_climate:core/seeding_pot");

		ShapedRecipeBuilder.shaped(CoreInit.SCYTHE_BRASS.get(), 1)
			.pattern("YYX")
			.pattern("  X")
			.pattern(" X ")
			.define('X', TagDC.ItemTag.INGOT_BRASS)
			.define('Y', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
			.save(cons, "dcs_climate:core/scytheitem_brass");
	}

	private static void smeltingRecipes(Consumer<FinishedRecipe> cons) {

		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.GEM_FLINT), CoreInit.GEM_CHALCEDONY.get(), 200, "gem_chalcedony", Items.FLINT, "has_flint");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_PLANT), CoreInit.DUST_ASH.get(), 200, "dust_ash", CoreInit.DUST_PLANT.get(), "has_dust_plant");
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

	private static void smeltingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, Item output, int time, String name, Item unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.smelting(input, output, 0F, time)
			.unlockedBy(unlockName, has(unlockTarget))
			.save(cons, "dcs_climate:smelting/smelting_" + name);

		SimpleCookingRecipeBuilder.blasting(input, output, 0F, time / 2)
			.unlockedBy(unlockName, has(unlockTarget))
			.save(cons, "dcs_climate:smelting/blasting_" + name);
	}

}
