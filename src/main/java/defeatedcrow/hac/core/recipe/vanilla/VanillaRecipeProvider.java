package defeatedcrow.hac.core.recipe.vanilla;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.recipe.mill.MillsDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.magic.material.MagicInit;
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
import net.minecraft.world.level.ItemLike;
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

		for (MillsDC.Miscs misc : MillsDC.INSTANCE.miscRecipe) {
			mortarMiscRecipes(cons, misc);
		}

		for (MillsDC.Sieve sieve : MillsDC.INSTANCE.sieveRecipe) {
			sieveRecipes(cons, sieve);
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

		clothingRecipes(cons);

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

		if (color.block().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.block().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.orePri().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_ore", has(color.block().get().asItem()))
					.save(cons, "dcs_climate:core/mortar_ore_" + color.name());

			ShapelessRecipeBuilder.shapeless(color.gemPri().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.requires(CoreInit.SIEVE.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_ore", has(color.block().get().asItem()))
					.save(cons, "dcs_climate:core/sieve_ore_" + color.name());
		}

		if (color.blockDeep().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.blockDeep().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.oreSec().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_deepore", has(color.blockDeep().get().asItem()))
					.save(cons, "dcs_climate:core/mortar_deepore_" + color.name());

			ShapelessRecipeBuilder.shapeless(color.gemSec().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.requires(CoreInit.SIEVE.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_deepore", has(color.blockDeep().get().asItem()))
					.save(cons, "dcs_climate:core/sieve_deepore_" + color.name());
		}

		if (color.orePri().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.orePri().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.dustPri().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_ore", has(color.block().get().asItem()))
					.save(cons, "dcs_climate:core/mortar_gem1_" + color.name());
		}

		if (color.oreSec().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreSec().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.dustSec().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_deepore", has(color.blockDeep().get().asItem()))
					.save(cons, "dcs_climate:core/mortar_gem2_" + color.name());
		}

		if (color.oreTert().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreTert().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(color.dustTert().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + color.name() + "_deepore", has(color.blockDeep().get().asItem()))
					.save(cons, "dcs_climate:core/mortar_gem3_" + color.name());
		}

	}

	private static void mortarGemRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Gem gem) {
		if (gem.ore().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) gem.ore().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(gem.gem().get(), 1)
					.requires(ore)
					.requires(CoreInit.MORTAR.get())
					.requires(CoreInit.SIEVE.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + gem.name() + "_ore", has(gem.ore().get().asItem()))
					.save(cons, "dcs_climate:core/sieve_ore_" + gem.name());
		}
	}

	private static void mortarOtherRecipes(Consumer<FinishedRecipe> cons) {
		ShapelessRecipeBuilder.shapeless(CoreInit.DUST_CRYSTAL.get(), 1)
				.requires(CoreInit.STONE_QUARTZ.get())
				.requires(CoreInit.MORTAR.get())
				.requires(CoreInit.SIEVE.get())
				.group("crusher_mortar")
				.unlockedBy("has_stone_quartz", has(CoreInit.STONE_QUARTZ.get()))
				.save(cons, "dcs_climate:core/sieve_stone_quartz");
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

	private static void mortarMiscRecipes(Consumer<FinishedRecipe> cons, MillsDC.Miscs mill) {
		if (mill.input().get() != null) {
			ShapelessRecipeBuilder.shapeless(mill.outputPri().get(), mill.outputCount())
					.requires(mill.input().get())
					.requires(CoreInit.MORTAR.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + mill.name(), has(mill.input().get()))
					.save(cons, "dcs_climate:core/mortar_" + mill.name());
		}
	}

	private static void sieveRecipes(Consumer<FinishedRecipe> cons, MillsDC.Sieve mill) {
		if (mill.input().get() != null) {
			ShapelessRecipeBuilder.shapeless(mill.outputPri().get(), mill.outputCount())
					.requires(mill.input().get())
					.requires(CoreInit.MORTAR.get())
					.requires(CoreInit.SIEVE.get())
					.group("crusher_mortar")
					.unlockedBy("has_" + mill.name(), has(mill.input().get()))
					.save(cons, "dcs_climate:core/sieve_" + mill.name());
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

		ShapedRecipeBuilder.shaped(BuildInit.SLAB_METAL.get(), 3)
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_slab_1");

		ShapedRecipeBuilder.shaped(BuildInit.STAIRS_METAL.get(), 3)
				.pattern("X  ")
				.pattern(" X ")
				.pattern("  X")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_stairs_1");

		ShapedRecipeBuilder.shaped(BuildInit.STAIRS_METAL.get(), 3)
				.pattern("  X")
				.pattern(" X ")
				.pattern("X  ")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_stairs_2");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_GRAY.get(), 6)
				.pattern("X  ")
				.pattern("XX ")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_roof_gray_1");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_GRAY.get(), 6)
				.pattern("  X")
				.pattern(" XX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_roof_gray_2");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_YELLOW.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.ROOF_METAL_GRAY.get())
				.define('Y', Tags.Items.DYES_YELLOW)
				.unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
				.save(cons, "dcs_climate:build/metal_roof_yellow_1");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_BLUE.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.ROOF_METAL_GRAY.get())
				.define('Y', Tags.Items.DYES_BLUE)
				.unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
				.save(cons, "dcs_climate:build/metal_roof_blue_1");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_BLACK.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.ROOF_METAL_GRAY.get())
				.define('Y', Tags.Items.DYES_BLACK)
				.unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
				.save(cons, "dcs_climate:build/metal_roof_black_1");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_RED.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.ROOF_METAL_GRAY.get())
				.define('Y', Tags.Items.DYES_RED)
				.unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
				.save(cons, "dcs_climate:build/metal_roof_red_1");

		ShapedRecipeBuilder.shaped(BuildInit.ROOF_METAL_GREEN.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.ROOF_METAL_GRAY.get())
				.define('Y', Tags.Items.DYES_GREEN)
				.unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
				.save(cons, "dcs_climate:build/metal_roof_green_1");

		ShapedRecipeBuilder.shaped(BuildInit.LADDER_METAL.get(), 7)
				.pattern("X X")
				.pattern("XXX")
				.pattern("X X")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_ladder_1");

		ShapedRecipeBuilder.shaped(BuildInit.FENCE_METAL.get(), 6)
				.pattern("XXX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:build/metal_fence_1");

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

		ShapedRecipeBuilder.shaped(BuildInit.SLAB_DIRT.get(), 6)
				.pattern("XXX")
				.define('X', ItemTags.DIRT)
				.unlockedBy("has_dirt", has(ItemTags.DIRT))
				.save(cons, "dcs_climate:core/slab_dirt");

		ShapedRecipeBuilder.shaped(Blocks.DIRT, 1)
				.pattern("X")
				.pattern("X")
				.define('X', BuildInit.SLAB_DIRT.get())
				.unlockedBy("has_dirt_slab", has(BuildInit.SLAB_DIRT.get()))
				.save(cons, "dcs_climate:core/dirt_from_slabs");

		ShapedRecipeBuilder.shaped(BuildInit.SLAB_GRAVEL.get(), 6)
				.pattern("XXX")
				.define('X', Tags.Items.GRAVEL)
				.unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
				.save(cons, "dcs_climate:core/slab_gravel");

		ShapedRecipeBuilder.shaped(Blocks.GRAVEL, 1)
				.pattern("X")
				.pattern("X")
				.define('X', BuildInit.SLAB_GRAVEL.get())
				.unlockedBy("has_gravel_slab", has(BuildInit.SLAB_GRAVEL.get()))
				.save(cons, "dcs_climate:core/gravel_from_slabs");

		ShapedRecipeBuilder.shaped(BuildInit.ADOBE_BLOCK_WET.get(), 4)
				.pattern("XX")
				.pattern("XX")
				.define('X', ItemTags.DIRT)
				.unlockedBy("has_dirt", has(ItemTags.DIRT))
				.save(cons, "dcs_climate:core/adobe_wet");

		ShapedRecipeBuilder.shaped(BuildInit.ADOBE_BRICKS.get(), 1)
				.pattern("XX")
				.pattern("XX")
				.define('X', CoreInit.ADOBE_BRICK_ITEM.get())
				.unlockedBy("has_adobe_brick_item", has(CoreInit.ADOBE_BRICK_ITEM.get()))
				.save(cons, "dcs_climate:core/adobe_bricks_block");

		ShapelessRecipeBuilder.shapeless(BuildInit.MORTAR.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(Tags.Items.SAND)
				.requires(TagDC.ItemTag.WATER)
				.unlockedBy("has_dust_lime", has(TagDC.ItemTag.DUST_LIME))
				.save(cons, "dcs_climate:build/mortar_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.MORTAR.get(), 4)
				.requires(TagDC.ItemTag.ORES_GYPSUM)
				.requires(TagDC.ItemTag.ORES_GYPSUM)
				.requires(Tags.Items.SAND)
				.requires(TagDC.ItemTag.WATER)
				.unlockedBy("has_ore_gypsum", has(TagDC.ItemTag.ORES_GYPSUM))
				.save(cons, "dcs_climate:build/mortar_2");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_BLACK.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_BLACK)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_black_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_RED.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_RED)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_red_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_GREEN.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_GREEN)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_green_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_BROWN.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_BROWN)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_brown_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_BLUE.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_BLUE)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_blue_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_PURPLE.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_PURPLE)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_purple_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_CYAN.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_CYAN)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_cyan_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_LIGHT_GRAY.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_LIGHT_GRAY)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_light_gray_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_PINK.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_PINK)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_pink_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_LIME.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_LIME)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_lime_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_YELLOW.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_YELLOW)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_yellow_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_LIGHT_BLUE.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_LIGHT_BLUE)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_light_blue_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_MAGENTA.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_MAGENTA)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_magenta_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_ORANGE.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_ORANGE)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_orange_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LINOLEUM_WHITE.get(), 4)
				.requires(TagDC.ItemTag.DUST_LIME)
				.requires(TagDC.ItemTag.PLANT_OIL)
				.requires(TagDC.ItemTag.SAP_RESIN)
				.requires(TagDC.ItemTag.CLOTHS)
				.requires(ItemTags.PLANKS)
				.requires(Tags.Items.DYES_WHITE)
				.unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
				.save(cons, "dcs_climate:build/linoleum_white_1");

		ShapedRecipeBuilder.shaped(BuildInit.GLASS_LIGHT.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.GLASS_CRYSTAL.get())
				.define('Y', Items.GLOWSTONE_DUST)
				.unlockedBy("has_dust_glowstone", has(Items.GLOWSTONE_DUST))
				.save(cons, "dcs_climate:build/glass_light1");

		ShapedRecipeBuilder.shaped(BuildInit.GLASS_LIGHT.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.GLASS_CRYSTAL.get())
				.define('Y', MagicInit.EXTRACT_WHITE.get())
				.unlockedBy("has_extract_white", has(MagicInit.EXTRACT_WHITE.get()))
				.save(cons, "dcs_climate:build/glass_light2");

		ShapedRecipeBuilder.shaped(BuildInit.GLASS_DARK.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.GLASS_CRYSTAL.get())
				.define('Y', TagDC.ItemTag.DUST_SILVER)
				.unlockedBy("has_dust_silver", has(TagDC.ItemTag.DUST_SILVER))
				.save(cons, "dcs_climate:build/glass_dark1");

		ShapedRecipeBuilder.shaped(BuildInit.GLASS_DARK.get(), 8)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.GLASS_CRYSTAL.get())
				.define('Y', MagicInit.EXTRACT_BLACK.get())
				.unlockedBy("has_extract_black", has(MagicInit.EXTRACT_BLACK.get()))
				.save(cons, "dcs_climate:build/glass_dark2");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.GEM_AGATES)
				.define('Y', Tags.Items.DUSTS_REDSTONE)
				.unlockedBy("has_gem_agate", has(TagDC.ItemTag.GEM_AGATES))
				.save(cons, "dcs_climate:build/chalcedony_lamp1");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.GEM_AGATES)
				.define('Y', MagicInit.EXTRACT_WHITE.get())
				.unlockedBy("has_gem_agate", has(TagDC.ItemTag.GEM_AGATES))
				.save(cons, "dcs_climate:build/chalcedony_lamp2");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP_GLASS.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', Tags.Items.GLASS)
				.define('Y', BuildInit.CHAL_LAMP.get())
				.unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
				.save(cons, "dcs_climate:build/chalcedony_lamp_glass");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP_TABLE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.GEM_CHALCEDONY)
				.define('Y', BuildInit.CHAL_LAMP.get())
				.define('Z', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
				.save(cons, "dcs_climate:build/chalcedony_lamp_table");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP_FLUORITE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.GEM_FLUORITE)
				.define('Y', BuildInit.CHAL_LAMP.get())
				.define('Z', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
				.save(cons, "dcs_climate:build/chalcedony_lamp_fluorite");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP_JET.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.GEM_JET)
				.define('Y', BuildInit.CHAL_LAMP.get())
				.define('Z', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
				.save(cons, "dcs_climate:build/chalcedony_lamp_jet");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP_DESERTROSE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.GEM_DESERTROSE)
				.define('Y', BuildInit.CHAL_LAMP.get())
				.define('Z', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
				.save(cons, "dcs_climate:build/chalcedony_lamp_desertrose");

		ShapedRecipeBuilder.shaped(BuildInit.CHAL_LAMP_SERPENTINE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" Z ")
				.define('X', TagDC.ItemTag.GEM_SERPENTINE)
				.define('Y', BuildInit.CHAL_LAMP.get())
				.define('Z', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
				.save(cons, "dcs_climate:build/chalcedony_lamp_serpentine");

		ShapedRecipeBuilder.shaped(BuildInit.CHANDELIER_IRON.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', BuildInit.CHAL_LAMP_TABLE.get())
				.define('Y', Tags.Items.INGOTS_IRON)
				.unlockedBy("has_table_lamp", has(BuildInit.CHAL_LAMP_TABLE.get()))
				.save(cons, "dcs_climate:build/chandelier_iron_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHANDELIER_LAMP.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', BuildInit.CHAL_LAMP_TABLE.get())
				.define('Y', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_table_lamp", has(BuildInit.CHAL_LAMP_TABLE.get()))
				.save(cons, "dcs_climate:build/chandelier_lamp_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHANDELIER_FLUORITE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', BuildInit.CHAL_LAMP_FLUORITE.get())
				.define('Y', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_fluorite_lamp", has(BuildInit.CHAL_LAMP_FLUORITE.get()))
				.save(cons, "dcs_climate:build/chandelier_fluorite_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHANDELIER_JET.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', BuildInit.CHAL_LAMP_JET.get())
				.define('Y', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_jet_lamp", has(BuildInit.CHAL_LAMP_JET.get()))
				.save(cons, "dcs_climate:build/chandelier_jet_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHANDELIER_DESERTROSE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', BuildInit.CHAL_LAMP_DESERTROSE.get())
				.define('Y', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_desertrose_lamp", has(BuildInit.CHAL_LAMP_DESERTROSE.get()))
				.save(cons, "dcs_climate:build/chandelier_desertrose_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHANDELIER_SERPENTINE.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', BuildInit.CHAL_LAMP_SERPENTINE.get())
				.define('Y', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_serpentine_lamp", has(BuildInit.CHAL_LAMP_SERPENTINE.get()))
				.save(cons, "dcs_climate:build/chandelier_serpentine_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHAIN_GOLD.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("X")
				.define('X', Tags.Items.NUGGETS_GOLD)
				.define('Y', Tags.Items.INGOTS_GOLD)
				.unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
				.save(cons, "dcs_climate:build/chain_gold_0");

		ShapedRecipeBuilder.shaped(BuildInit.TOOL_HOOK.get(), 1)
				.pattern("Z")
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.define('Z', TagDC.ItemTag.INGOT_BRASS)
				.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:build/wall_hook_0");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_LINEN.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', TagDC.ItemTag.CLOTH_PLANT)
				.unlockedBy("has_cloth_plant", has(TagDC.ItemTag.CLOTH_PLANT))
				.save(cons, "dcs_climate:build/carpet_linen_0");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_WHITE.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', TagDC.ItemTag.CLOTH_COTTON)
				.unlockedBy("has_cloth_cotton", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:build/carpet_white_1");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_WHITE.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', Items.WHITE_CARPET)
				.unlockedBy("has_carpet_white", has(Items.WHITE_CARPET))
				.save(cons, "dcs_climate:build/carpet_white_2");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_BLUE.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.CARPET_WHITE.get())
				.define('Y', Tags.Items.DYES_BLUE)
				.unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
				.save(cons, "dcs_climate:build/carpet_blue_1");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_BLUE.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', Items.BLUE_CARPET)
				.unlockedBy("has_carpet_blue", has(Items.BLUE_CARPET))
				.save(cons, "dcs_climate:build/carpet_blue_2");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_BLACK.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.CARPET_WHITE.get())
				.define('Y', Tags.Items.DYES_BLACK)
				.unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
				.save(cons, "dcs_climate:build/carpet_black_1");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_BLACK.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', Items.BLACK_CARPET)
				.unlockedBy("has_carpet_black", has(Items.BLACK_CARPET))
				.save(cons, "dcs_climate:build/carpet_black_2");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_RED.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.CARPET_WHITE.get())
				.define('Y', Tags.Items.DYES_RED)
				.unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
				.save(cons, "dcs_climate:build/carpet_red_1");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_RED.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', Items.RED_CARPET)
				.unlockedBy("has_carpet_red", has(Items.RED_CARPET))
				.save(cons, "dcs_climate:build/carpet_red_2");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_GREEN.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', BuildInit.CARPET_WHITE.get())
				.define('Y', Tags.Items.DYES_GREEN)
				.unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
				.save(cons, "dcs_climate:build/carpet_green_1");

		ShapedRecipeBuilder.shaped(BuildInit.CARPET_GREEN.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', ItemTags.PLANKS)
				.define('Y', Items.GREEN_CARPET)
				.unlockedBy("has_carpet_green", has(Items.GREEN_CARPET))
				.save(cons, "dcs_climate:build/carpet_green_2");

		ShapedRecipeBuilder.shaped(BuildInit.TABLE_WOOD.get(), 1)
				.pattern("XXX")
				.pattern("Y Y")
				.pattern("Y Y")
				.define('X', ItemTags.PLANKS)
				.define('Y', ItemTags.WOODEN_FENCES)
				.unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
				.save(cons, "dcs_climate:build/table_wood_0");

		ShapedRecipeBuilder.shaped(BuildInit.CHAIR_WOOD.get(), 1)
				.pattern("  X")
				.pattern("XXX")
				.pattern("Y Y")
				.define('X', ItemTags.PLANKS)
				.define('Y', ItemTags.WOODEN_FENCES)
				.unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
				.save(cons, "dcs_climate:build/chair_wood_1");

		ShapedRecipeBuilder.shaped(BuildInit.CHAIR_WOOD.get(), 1)
				.pattern("X  ")
				.pattern("XXX")
				.pattern("Y Y")
				.define('X', ItemTags.PLANKS)
				.define('Y', ItemTags.WOODEN_FENCES)
				.unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
				.save(cons, "dcs_climate:build/chair_wood_2");

		ShapelessRecipeBuilder.shapeless(BuildInit.TABLE_LINEN.get(), 1)
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(BuildInit.TABLE_WOOD.get())
				.unlockedBy("has_wooden_table", has(BuildInit.TABLE_WOOD.get()))
				.save(cons, "dcs_climate:clothing/table_linen_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.TABLE_WHITE.get(), 1)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(BuildInit.TABLE_WOOD.get())
				.unlockedBy("has_wooden_table", has(BuildInit.TABLE_WOOD.get()))
				.save(cons, "dcs_climate:clothing/table_white_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.TABLE_BLUE.get(), 1)
				.requires(Tags.Items.DYES_BLUE)
				.requires(BuildInit.TABLE_WHITE.get())
				.unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
				.save(cons, "dcs_climate:clothing/table_blue_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.TABLE_BLACK.get(), 1)
				.requires(Tags.Items.DYES_BLACK)
				.requires(BuildInit.TABLE_WHITE.get())
				.unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
				.save(cons, "dcs_climate:clothing/table_black_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.TABLE_RED.get(), 1)
				.requires(Tags.Items.DYES_RED)
				.requires(BuildInit.TABLE_WHITE.get())
				.unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
				.save(cons, "dcs_climate:clothing/table_red_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.TABLE_GREEN.get(), 1)
				.requires(Tags.Items.DYES_GREEN)
				.requires(BuildInit.TABLE_WHITE.get())
				.unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
				.save(cons, "dcs_climate:clothing/table_green_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CHAIR_LINEN.get(), 1)
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(BuildInit.CHAIR_WOOD.get())
				.unlockedBy("has_wooden_chair", has(BuildInit.CHAIR_WOOD.get()))
				.save(cons, "dcs_climate:clothing/chair_linen_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CHAIR_WHITE.get(), 1)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(BuildInit.CHAIR_WOOD.get())
				.unlockedBy("has_wooden_chair", has(BuildInit.CHAIR_WOOD.get()))
				.save(cons, "dcs_climate:clothing/chair_white_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CHAIR_BLUE.get(), 1)
				.requires(Tags.Items.DYES_BLUE)
				.requires(BuildInit.CHAIR_WHITE.get())
				.unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
				.save(cons, "dcs_climate:clothing/chair_blue_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CHAIR_BLACK.get(), 1)
				.requires(Tags.Items.DYES_BLACK)
				.requires(BuildInit.CHAIR_WHITE.get())
				.unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
				.save(cons, "dcs_climate:clothing/chair_black_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CHAIR_RED.get(), 1)
				.requires(Tags.Items.DYES_RED)
				.requires(BuildInit.CHAIR_WHITE.get())
				.unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
				.save(cons, "dcs_climate:clothing/chair_red_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CHAIR_GREEN.get(), 1)
				.requires(Tags.Items.DYES_GREEN)
				.requires(BuildInit.CHAIR_WHITE.get())
				.unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
				.save(cons, "dcs_climate:clothing/chair_green_0");

		ShapedRecipeBuilder.shaped(BuildInit.CABINET_NORMAL.get(), 1)
				.pattern("XXX")
				.pattern(" Y ")
				.pattern("XXX")
				.define('X', ItemTags.PLANKS)
				.define('Y', Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
				.save(cons, "dcs_climate:build/cabinet_normal_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CABINET_WHITE.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.requires(BuildInit.CABINET_NORMAL.get())
				.unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/cabinet_white_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CABINET_BLUE.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.requires(BuildInit.CABINET_NORMAL.get())
				.unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/cabinet_blue_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CABINET_BLACK.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.requires(BuildInit.CABINET_NORMAL.get())
				.unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/cabinet_black_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CABINET_RED.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.requires(BuildInit.CABINET_NORMAL.get())
				.unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/cabinet_red_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.CABINET_GREEN.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.requires(BuildInit.CABINET_NORMAL.get())
				.unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/cabinet_green_0");

		ShapedRecipeBuilder.shaped(BuildInit.LUGGAGE_NORMAL.get(), 1)
				.pattern("XXX")
				.pattern(" Y ")
				.pattern("XXX")
				.define('X', Tags.Items.LEATHER)
				.define('Y', Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
				.save(cons, "dcs_climate:build/luggage_normal_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LUGGAGE_WHITE.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.requires(BuildInit.LUGGAGE_NORMAL.get())
				.unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/luggage_white_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LUGGAGE_BLUE.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.requires(BuildInit.LUGGAGE_NORMAL.get())
				.unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/luggage_blue_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LUGGAGE_BLACK.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.requires(BuildInit.LUGGAGE_NORMAL.get())
				.unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/luggage_black_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LUGGAGE_RED.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.requires(BuildInit.LUGGAGE_NORMAL.get())
				.unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/luggage_red_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LUGGAGE_GREEN.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.requires(BuildInit.LUGGAGE_NORMAL.get())
				.unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/luggage_green_0");

		ShapedRecipeBuilder.shaped(BuildInit.LOCKER_NORMAL.get(), 1)
				.pattern("XXX")
				.pattern(" Y ")
				.pattern("XXX")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
				.save(cons, "dcs_climate:build/locker_normal_0");

		ShapedRecipeBuilder.shaped(BuildInit.LOCKER_NORMAL.get(), 1)
				.pattern("XXX")
				.pattern(" Y ")
				.pattern("XXX")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
				.save(cons, "dcs_climate:build/locker_normal_1");

		ShapelessRecipeBuilder.shapeless(BuildInit.LOCKER_WHITE.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_WHITE)
				.requires(BuildInit.LOCKER_NORMAL.get())
				.unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/locker_white_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LOCKER_BLUE.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_BLUE)
				.requires(BuildInit.LOCKER_NORMAL.get())
				.unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/locker_blue_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LOCKER_BLACK.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_BLACK)
				.requires(BuildInit.LOCKER_NORMAL.get())
				.unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/locker_black_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LOCKER_RED.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_RED)
				.requires(BuildInit.LOCKER_NORMAL.get())
				.unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/locker_red_0");

		ShapelessRecipeBuilder.shapeless(BuildInit.LOCKER_GREEN.get(), 1)
				.requires(TagDC.ItemTag.EXTRACT_GREEN)
				.requires(BuildInit.LOCKER_NORMAL.get())
				.unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
				.save(cons, "dcs_climate:clothing/locker_green_0");

	}

	private static void otherRecipes(Consumer<FinishedRecipe> cons) {
		ShapedRecipeBuilder.shaped(CoreInit.MORTAR.get(), 1)
				.pattern("X X")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.GEM_AGATES)
				.unlockedBy("has_chalcedony", has(TagDC.ItemTag.GEM_CHALCEDONY))
				.save(cons, "dcs_climate:core/agate_mortar");

		ShapedRecipeBuilder.shaped(CoreInit.SIEVE.get(), 1)
				.pattern("XYX")
				.pattern(" X ")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', Items.IRON_BARS)
				.unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON))
				.save(cons, "dcs_climate:core/gem_sieve");

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

		ShapedRecipeBuilder.shaped(CoreInit.HANDY_BELLOW.get(), 1)
				.pattern("XY")
				.pattern("YZ")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', ItemTags.PLANKS)
				.define('Z', Tags.Items.LEATHER)
				.unlockedBy("has_leather", has(Tags.Items.LEATHER))
				.save(cons, "dcs_climate:core/bellow_0");

		ShapedRecipeBuilder.shaped(CoreInit.HANDY_BELLOW.get(), 1)
				.pattern("XY")
				.pattern("YZ")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', ItemTags.PLANKS)
				.define('Z', Tags.Items.LEATHER)
				.unlockedBy("has_leather", has(Tags.Items.LEATHER))
				.save(cons, "dcs_climate:core/bellow_1");

		ShapedRecipeBuilder.shaped(CoreInit.SCREWDRIVER.get(), 1)
				.pattern("X")
				.pattern("Y")
				.pattern("Z")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', ItemTags.PLANKS)
				.define('Z', Tags.Items.LEATHER)
				.unlockedBy("has_ingot_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/screwdriver_0");

		ShapedRecipeBuilder.shaped(CoreInit.CHOPSTICKS.get(), 1)
				.pattern("XX")
				.define('X', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_rod_wooden", has(Tags.Items.RODS_WOODEN))
				.save(cons, "dcs_climate:core/chopsticks_0");

		ShapedRecipeBuilder.shaped(CoreInit.FORK.get(), 1)
				.pattern("YYY")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('Y', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_ingot_silver", has(TagDC.ItemTag.INGOT_SILVER))
				.save(cons, "dcs_climate:core/fork_1");

		ShapedRecipeBuilder.shaped(CoreInit.SPOON.get(), 1)
				.pattern("Y")
				.pattern("Y")
				.pattern("X")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('Y', TagDC.ItemTag.INGOT_SILVER)
				.unlockedBy("has_ingot_silver", has(TagDC.ItemTag.INGOT_SILVER))
				.save(cons, "dcs_climate:core/spoon_1");

		ShapedRecipeBuilder.shaped(CoreInit.FORK.get(), 1)
				.pattern("YYY")
				.pattern(" Y ")
				.pattern(" X ")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('Y', TagDC.ItemTag.INGOT_NICKEL_SILVER)
				.unlockedBy("has_ingot_nickelsilver", has(TagDC.ItemTag.INGOT_NICKEL_SILVER))
				.save(cons, "dcs_climate:core/fork_2");

		ShapedRecipeBuilder.shaped(CoreInit.SPOON.get(), 1)
				.pattern("Y")
				.pattern("Y")
				.pattern("X")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('Y', TagDC.ItemTag.INGOT_NICKEL_SILVER)
				.unlockedBy("has_ingot_nickelsilver", has(TagDC.ItemTag.INGOT_NICKEL_SILVER))
				.save(cons, "dcs_climate:core/spoon_2");

		ShapedRecipeBuilder.shaped(CoreInit.EMPTY_COIL_CASE.get(), 1)
				.pattern("XXY")
				.pattern("XX ")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', Items.CHAIN)
				.unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON))
				.save(cons, "dcs_climate:core/insence_case_1");

		ShapelessRecipeBuilder.shapeless(CoreInit.COIL_CASE.get(), 1)
				.requires(CoreInit.EMPTY_COIL_CASE.get())
				.requires(CoreInit.MOSQUITO_COIL.get())
				.unlockedBy("has_mosquito_coil", has(CoreInit.MOSQUITO_COIL.get()))
				.save(cons, "dcs_climate:core/mosquito_coil_case_1");

		ShapelessRecipeBuilder.shapeless(CoreInit.MOSQUITO_COIL.get(), 1)
				.requires(TagDC.ItemTag.WATER)
				.requires(TagDC.ItemTag.DUST_WOOD)
				.requires(TagDC.ItemTag.CAMPHOR)
				.requires(TagDC.ItemTag.CROP_PYRETHRUM)
				.unlockedBy("has_crop_pyrethrum", has(TagDC.ItemTag.CROP_PYRETHRUM))
				.save(cons, "dcs_climate:core/mosquito_coil_1");

		ShapedRecipeBuilder.shaped(CoreInit.AXE_BRASS.get(), 1)
				.pattern("YYX")
				.pattern(" XX")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:core/axeitem_brass");

		ShapedRecipeBuilder.shaped(CoreInit.PICKAXE_BRASS.get(), 1)
				.pattern("  X")
				.pattern("YYX")
				.pattern("  X")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:core/pickaxeitem_brass");

		ShapedRecipeBuilder.shaped(CoreInit.SHOVEL_BRASS.get(), 1)
				.pattern("YYX")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:core/shovelitem_brass");

		ShapedRecipeBuilder.shaped(CoreInit.HOE_BRASS.get(), 1)
				.pattern("YYX")
				.pattern("  X")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:core/hoeitem_brass");

		ShapedRecipeBuilder.shaped(CoreInit.SCYTHE_BRASS.get(), 1)
				.pattern("YYX")
				.pattern("  X")
				.pattern(" X ")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:core/scytheitem_brass");

		ShapedRecipeBuilder.shaped(CoreInit.AXE_STEEL.get(), 1)
				.pattern("YYX")
				.pattern(" XX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/axeitem_steel");

		ShapedRecipeBuilder.shaped(CoreInit.PICKAXE_STEEL.get(), 1)
				.pattern("  X")
				.pattern("YYX")
				.pattern("  X")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/pickaxeitem_steel");

		ShapedRecipeBuilder.shaped(CoreInit.SHOVEL_STEEL.get(), 1)
				.pattern("YYX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/shovelitem_steel");

		ShapedRecipeBuilder.shaped(CoreInit.HOE_STEEL.get(), 1)
				.pattern("YYX")
				.pattern("  X")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/hoeitem_steel");

		ShapedRecipeBuilder.shaped(CoreInit.SCYTHE_STEEL.get(), 1)
				.pattern("YYX")
				.pattern("  X")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/scytheitem_steel");

		ShapedRecipeBuilder.shaped(CoreInit.SCYTHE_FLINT.get(), 1)
				.pattern("YYX")
				.pattern("  X")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.GEM_FLINT)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_flint", has(TagDC.ItemTag.GEM_FLINT))
				.save(cons, "dcs_climate:core/scytheitem_flint");

		ShapedRecipeBuilder.shaped(CoreInit.HARPOON_FLINT.get(), 1)
				.pattern("  Y")
				.pattern(" X ")
				.pattern("XZ ")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('Y', TagDC.ItemTag.GEM_FLINT)
				.define('Z', Tags.Items.STRING)
				.unlockedBy("has_flint", has(TagDC.ItemTag.GEM_FLINT))
				.save(cons, "dcs_climate:core/harpoon_flint_1");

		ShapedRecipeBuilder.shaped(CoreInit.HARPOON_STEEL.get(), 1)
				.pattern("  Y")
				.pattern(" X ")
				.pattern("XZ ")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', TagDC.ItemTag.INGOT_STEEL)
				.define('Z', Tags.Items.STRING)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/harpoon_steel_1");

		ShapedRecipeBuilder.shaped(CoreInit.HARPOON_FLINT.get(), 1)
				.pattern("  Y")
				.pattern(" X ")
				.pattern("XZ ")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('Y', TagDC.ItemTag.GEM_FLINT)
				.define('Z', TagDC.ItemTag.VINE)
				.unlockedBy("has_flint", has(TagDC.ItemTag.GEM_FLINT))
				.save(cons, "dcs_climate:core/harpoon_flint_2");

		ShapedRecipeBuilder.shaped(CoreInit.HARPOON_STEEL.get(), 1)
				.pattern("  Y")
				.pattern(" X ")
				.pattern("XZ ")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', TagDC.ItemTag.INGOT_STEEL)
				.define('Z', TagDC.ItemTag.VINE)
				.unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
				.save(cons, "dcs_climate:core/harpoon_steel_2");

		ShapelessRecipeBuilder.shapeless(CoreInit.CALABASH_BUCKET.get(), 1)
				.requires(TagDC.ItemTag.CROP_CALABASH)
				.requires(TagDC.ItemTag.SAP_LACQUER)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_crop_calabash", has(TagDC.ItemTag.CROP_CALABASH))
				.save(cons, "dcs_climate:core/bucket_calabash_0");

		ShapedRecipeBuilder.shaped(CoreInit.DUSTBLOCK_RUBBER.get())
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.SAP_LATEX)
				.define('Y', TagDC.ItemTag.DUST_SULFUR)
				.unlockedBy("has_sap_latex", has(TagDC.ItemTag.SAP_LATEX))
				.save(cons, "dcs_climate:core/dustblock_rubber_0");

		ShapedRecipeBuilder.shaped(CoreInit.BLOCK_RUBBER.get())
				.pattern("XXX")
				.pattern("XXX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.CLOTH_RUBBER)
				.group("storage_pack")
				.unlockedBy("has_cloth_rubber", has(TagDC.ItemTag.CLOTH_RUBBER))
				.save(cons, "dcs_climate:core/block_rubber_0");

		ShapelessRecipeBuilder.shapeless(CoreInit.CLOTH_RUBBER.get(), 9)
				.requires(TagDC.ItemTag.BLOCK_RUBBER)
				.group("storage_unpack")
				.unlockedBy("has_block_rubber", has(TagDC.ItemTag.BLOCK_RUBBER))
				.save(cons, "dcs_climate:core/cloth_rubber_0");

		// vanilla another

		ShapedRecipeBuilder.shaped(Items.PAINTING, 1)
				.pattern("YYY")
				.pattern("YXY")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.CLOTHS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:core/painting_another_0");

		ShapedRecipeBuilder.shaped(Items.WHITE_BED, 1)
				.pattern("XXX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.CLOTHS)
				.define('Y', ItemTags.PLANKS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:core/bed_another_0");

		ShapedRecipeBuilder.shaped(Items.WHITE_BANNER, 1)
				.pattern("XXX")
				.pattern("XXX")
				.pattern(" Y ")
				.define('X', TagDC.ItemTag.CLOTHS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:core/banner_another_0");

		ShapedRecipeBuilder.shaped(Items.LOOM, 1)
				.pattern("XX")
				.pattern("YY")
				.define('X', TagDC.ItemTag.CLOTHS)
				.define('Y', ItemTags.PLANKS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:core/loom_another_0");

		ShapedRecipeBuilder.shaped(Items.BOOK, 1)
				.pattern("YY")
				.pattern("YX")
				.define('X', TagDC.ItemTag.CLOTHS)
				.define('Y', Items.PAPER)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:core/book_another_0");

		ShapedRecipeBuilder.shaped(Items.SPYGLASS, 1)
				.pattern("Y")
				.pattern("X")
				.pattern("X")
				.define('X', Tags.Items.INGOTS_COPPER)
				.define('Y', TagDC.ItemTag.GEM_FLUORITE)
				.unlockedBy("has_fluorite", has(TagDC.ItemTag.GEM_FLUORITE))
				.save(cons, "dcs_climate:core/spyglass_another_0");

		ShapelessRecipeBuilder.shapeless(Items.GUNPOWDER, 1)
				.requires(TagDC.ItemTag.DUST_NITER)
				.requires(TagDC.ItemTag.DUST_NITER)
				.requires(TagDC.ItemTag.DUST_COAL)
				.requires(TagDC.ItemTag.DUST_SULFUR)
				.unlockedBy("has_dust_niter", has(TagDC.ItemTag.DUST_NITER))
				.save(cons, "dcs_climate:core/gunpowder_another_0");

		ShapedRecipeBuilder.shaped(Items.BUCKET, 1)
				.pattern("X X")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
				.save(cons, "dcs_climate:core/bucket_another_0");

		ShapedRecipeBuilder.shaped(Items.TORCH, 4)
				.pattern("X")
				.pattern("Y")
				.define('X', FoodInit.BIOMASS_BRIQUET.get())
				.define('Y', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_briquet", has(FoodInit.BIOMASS_BRIQUET.get()))
				.save(cons, "dcs_climate:core/torch_another_0");

		ShapedRecipeBuilder.shaped(Items.CAMPFIRE, 1)
				.pattern(" Y ")
				.pattern("YXY")
				.pattern("ZZZ")
				.define('X', FoodInit.BIOMASS_BRIQUET.get())
				.define('Y', Tags.Items.RODS_WOODEN)
				.define('Z', ItemTags.LOGS_THAT_BURN)
				.unlockedBy("has_briquet", has(FoodInit.BIOMASS_BRIQUET.get()))
				.save(cons, "dcs_climate:core/campfire_another_0");

		ShapedRecipeBuilder.shaped(Items.ARROW, 4)
				.pattern("Y")
				.pattern("X")
				.pattern("Z")
				.define('Z', Tags.Items.FEATHERS)
				.define('Y', Tags.Items.RODS_WOODEN)
				.define('X', TagDC.ItemTag.GEM_AGATES)
				.unlockedBy("has_agate", has(TagDC.ItemTag.GEM_AGATES))
				.save(cons, "dcs_climate:core/arrow_another_0");
	}

	private static void clothingRecipes(Consumer<FinishedRecipe> cons) {
		ShapedRecipeBuilder.shaped(CoreInit.PATTERN_HAT.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.define('X', Items.PAPER)
				.define('Y', Items.CHARCOAL)
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(cons, "dcs_climate:clothing/pattern_hat");

		ShapedRecipeBuilder.shaped(CoreInit.PATTERN_JACKET.get(), 1)
				.pattern("XYX")
				.pattern("XXX")
				.pattern("XXX")
				.define('X', Items.PAPER)
				.define('Y', Items.CHARCOAL)
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(cons, "dcs_climate:clothing/pattern_jacket");

		ShapedRecipeBuilder.shaped(CoreInit.PATTERN_SHIRT.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("X X")
				.define('X', Items.PAPER)
				.define('Y', Items.CHARCOAL)
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(cons, "dcs_climate:clothing/pattern_shirt");

		ShapedRecipeBuilder.shaped(CoreInit.PATTERN_SUITS.get(), 1)
				.pattern("XXX")
				.pattern("XXX")
				.pattern("XYX")
				.define('X', Items.PAPER)
				.define('Y', Items.CHARCOAL)
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(cons, "dcs_climate:clothing/pattern_suits");

		ShapedRecipeBuilder.shaped(CoreInit.PATTERN_PANTS.get(), 1)
				.pattern("X X")
				.pattern("XYX")
				.define('X', Items.PAPER)
				.define('Y', Items.CHARCOAL)
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(cons, "dcs_climate:clothing/pattern_pants");

		ShapedRecipeBuilder.shaped(CoreInit.MET_BRONZE.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/helmet_bronze");

		ShapedRecipeBuilder.shaped(CoreInit.CHEST_BRONZE.get(), 1)
				.pattern("XYX")
				.pattern("XXX")
				.pattern("XXX")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/plate_bronze");

		ShapedRecipeBuilder.shaped(CoreInit.LEGGINS_BRONZE.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("X X")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/chain_mail_bronze");

		ShapedRecipeBuilder.shaped(CoreInit.BOOTS_BRONZE.get(), 1)
				.pattern("X X")
				.pattern("XYX")
				.define('X', TagUtil.BRONZE_OR_BRASS)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/boots_bronze");

		ShapedRecipeBuilder.shaped(CoreInit.MET_STEEL.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/helmet_steel");

		ShapedRecipeBuilder.shaped(CoreInit.CHEST_STEEL.get(), 1)
				.pattern("XYX")
				.pattern("XXX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/plate_steel");

		ShapedRecipeBuilder.shaped(CoreInit.LEGGINS_STEEL.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("X X")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/chain_mail_steel");

		ShapedRecipeBuilder.shaped(CoreInit.BOOTS_STEEL.get(), 1)
				.pattern("X X")
				.pattern("XYX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
				.save(cons, "dcs_climate:clothing/boots_steel");

		ShapedRecipeBuilder.shaped(CoreInit.HAT_SAFETY.get(), 1)
				.pattern("XXX")
				.pattern("XYX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.CLOTH_RUBBER)
				.unlockedBy("has_rubber_cloth", has(TagDC.ItemTag.CLOTH_RUBBER))
				.save(cons, "dcs_climate:clothing/helmet_safety");

		ShapedRecipeBuilder.shaped(CoreInit.BOOTS_SAFETY.get(), 1)
				.pattern("Z Z")
				.pattern("XYX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.CLOTH_RUBBER)
				.define('Z', Tags.Items.LEATHER)
				.unlockedBy("has_rubber_cloth", has(TagDC.ItemTag.CLOTH_RUBBER))
				.save(cons, "dcs_climate:clothing/boots_safety");

		ShapedRecipeBuilder.shaped(CoreInit.LEGGINS_WADERS.get(), 1)
				.pattern("XYX")
				.pattern("XXX")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.CLOTH_RUBBER)
				.define('Y', TagDC.ItemTag.CLOTHS)
				.unlockedBy("has_rubber_cloth", has(TagDC.ItemTag.CLOTH_RUBBER))
				.save(cons, "dcs_climate:clothing/rubber_waders");

		ShapedRecipeBuilder.shaped(CoreInit.FUR_SHAWL.get(), 1)
				.pattern("XYX")
				.define('X', Items.RABBIT_HIDE)
				.define('Y', Tags.Items.STRING)
				.unlockedBy("has_rabbit_hide", has(Items.RABBIT_HIDE))
				.save(cons, "dcs_climate:clothing/shawl_fur_1");

		ShapedRecipeBuilder.shaped(CoreInit.FUR_SHAWL.get(), 1)
				.pattern("XYX")
				.define('X', Tags.Items.LEATHER)
				.define('Y', Tags.Items.STRING)
				.unlockedBy("has_leather", has(Tags.Items.LEATHER))
				.save(cons, "dcs_climate:clothing/shawl_fur_2");

		ShapedRecipeBuilder.shaped(CoreInit.FUR_LOINCLOTH.get(), 1)
				.pattern(" Y ")
				.pattern("XXX")
				.define('X', Items.RABBIT_HIDE)
				.define('Y', Tags.Items.STRING)
				.unlockedBy("has_rabbit_hide", has(Items.RABBIT_HIDE))
				.save(cons, "dcs_climate:clothing/shawl_loincloth_1");

		ShapedRecipeBuilder.shaped(CoreInit.FUR_LOINCLOTH.get(), 1)
				.pattern(" Y ")
				.pattern("XXX")
				.define('X', Tags.Items.LEATHER)
				.define('Y', Tags.Items.STRING)
				.unlockedBy("has_leather", has(Tags.Items.LEATHER))
				.save(cons, "dcs_climate:clothing/shawl_loincloth_2");

		ShapelessRecipeBuilder.shapeless(CoreInit.HAT_LINEN.get(), 1)
				.requires(CoreInit.PATTERN_HAT.get())
				.requires(TagDC.ItemTag.FIBER_PLANT)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_fiber_plant", has(TagDC.ItemTag.FIBER_PLANT))
				.save(cons, "dcs_climate:clothing/hat_linen");

		ShapelessRecipeBuilder.shapeless(CoreInit.JACKET_LINEN.get(), 1)
				.requires(CoreInit.PATTERN_JACKET.get())
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
				.save(cons, "dcs_climate:clothing/jacket_linen");

		ShapelessRecipeBuilder.shapeless(CoreInit.SHIRT_LINEN.get(), 1)
				.requires(CoreInit.PATTERN_SHIRT.get())
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
				.save(cons, "dcs_climate:clothing/shirt_linen");

		ShapelessRecipeBuilder.shapeless(CoreInit.PANTS_LINEN.get(), 1)
				.requires(CoreInit.PATTERN_PANTS.get())
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(TagDC.ItemTag.CLOTH_PLANT)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_linen_cloth", has(TagDC.ItemTag.CLOTH_PLANT))
				.save(cons, "dcs_climate:clothing/pants_linen");

		ShapelessRecipeBuilder.shapeless(CoreInit.HAT_CLOTH.get(), 1)
				.requires(CoreInit.PATTERN_HAT.get())
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:clothing/hat_cotton");

		ShapelessRecipeBuilder.shapeless(CoreInit.JACKET_CLOTH.get(), 1)
				.requires(CoreInit.PATTERN_JACKET.get())
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:clothing/jacket_cotton");

		ShapelessRecipeBuilder.shapeless(CoreInit.SHIRT_CLOTH.get(), 1)
				.requires(CoreInit.PATTERN_SHIRT.get())
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:clothing/shirt_cotton");

		ShapelessRecipeBuilder.shapeless(CoreInit.SUITS_CLOTH.get(), 1)
				.requires(CoreInit.PATTERN_SUITS.get())
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(TagDC.ItemTag.CROP_COTTON), new Ingredient.TagValue(ItemTags.WOOL))))
				.requires(Tags.Items.STRING)
				.unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:clothing/suits_cotton");

		ShapelessRecipeBuilder.shapeless(CoreInit.PANTS_CLOTH.get(), 1)
				.requires(CoreInit.PATTERN_PANTS.get())
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:clothing/pants_cotton");

		ShapelessRecipeBuilder.shapeless(CoreInit.JACKET_WOOL.get(), 1)
				.requires(CoreInit.PATTERN_JACKET.get())
				.requires(TagDC.ItemTag.CLOTH_WOOL)
				.requires(TagDC.ItemTag.CLOTH_WOOL)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_wool_cloth", has(TagDC.ItemTag.CLOTH_WOOL))
				.save(cons, "dcs_climate:clothing/jacket_wool");

		ShapelessRecipeBuilder.shapeless(CoreInit.SUITS_LEATHER.get(), 1)
				.requires(CoreInit.PATTERN_SUITS.get())
				.requires(Tags.Items.LEATHER)
				.requires(Tags.Items.LEATHER)
				.requires(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(TagDC.ItemTag.CROP_COTTON), new Ingredient.TagValue(ItemTags.WOOL))))
				.requires(Tags.Items.STRING)
				.unlockedBy("has_lether", has(Tags.Items.LEATHER))
				.save(cons, "dcs_climate:clothing/suits_lether");

		ShapelessRecipeBuilder.shapeless(CoreInit.LONG_MAID.get(), 1)
				.requires(CoreInit.PATTERN_SUITS.get())
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(TagDC.ItemTag.CLOTH_COTTON)
				.requires(TagDC.ItemTag.DUST_SUGAR)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_cotton_cloth", has(TagDC.ItemTag.CLOTH_COTTON))
				.save(cons, "dcs_climate:clothing/dress_maid");

		clothColorRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "linen_hat");
		clothColorRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_PINK.get(), Tags.Items.DYES_PINK, "pink", "linen_hat");
		clothColorRecipe(cons, CoreInit.HAT_LINEN.get(), CoreInit.HAT_LINEN_RED.get(), Tags.Items.DYES_RED, "red", "linen_hat");

		clothColorRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_hat");
		clothColorRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_hat");
		clothColorRecipe(cons, CoreInit.HAT_CLOTH.get(), CoreInit.HAT_CLOTH_GREEN.get(), Tags.Items.DYES_GREEN, "green", "cloth_hat");

		clothColorRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_BLACK.get(), Tags.Items.DYES_BLACK, "black", "linen_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "linen_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_LINEN.get(), CoreInit.JACKET_LINEN_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "linen_jacket");

		clothColorRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_CLOTH.get(), CoreInit.JACKET_CLOTH_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "cloth_jacket");

		clothColorRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_CYAN.get(), Tags.Items.DYES_CYAN, "cyan", "wool_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "wool_jacket");
		clothColorRecipe(cons, CoreInit.JACKET_WOOL.get(), CoreInit.JACKET_WOOL_RED.get(), Tags.Items.DYES_RED, "red", "wool_jacket");

		clothColorRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_CYAN.get(), Tags.Items.DYES_CYAN, "cyan", "linen_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_GREEN.get(), Tags.Items.DYES_GREEN, "green", "linen_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_LINEN.get(), CoreInit.SHIRT_LINEN_PINK.get(), Tags.Items.DYES_PINK, "pink", "linen_shirt");

		clothColorRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_BLACK.get(), Tags.Items.DYES_BLACK, "black", "cloth_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "cloth_shirt");
		clothColorRecipe(cons, CoreInit.SHIRT_CLOTH.get(), CoreInit.SHIRT_CLOTH_RED.get(), Tags.Items.DYES_RED, "red", "cloth_shirt");

		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_GREEN.get(), Tags.Items.DYES_GREEN, "green", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_ORANGE.get(), Tags.Items.DYES_ORANGE, "orange", "linen_pants");
		clothColorRecipe(cons, CoreInit.PANTS_LINEN.get(), CoreInit.PANTS_WHITE.get(), Tags.Items.DYES_WHITE, "white", "linen_pants");

		clothColorRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_BLUE.get(), Tags.Items.DYES_BLACK, "blue", "cloth_pants");
		clothColorRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_LIGHTBLUE.get(), Tags.Items.DYES_LIGHT_BLUE, "lightblue", "cloth_pants");
		clothColorRecipe(cons, CoreInit.PANTS_CLOTH.get(), CoreInit.PANTS_CLOTH_GRAY.get(), Tags.Items.DYES_RED, "gray", "cloth_pants");

		clothColorRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_BLUE.get(), Tags.Items.DYES_BLUE, "blue", "cloth_suits");
		clothColorRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_BROWN.get(), Tags.Items.DYES_BROWN, "brown", "cloth_suits");
		clothColorRecipe(cons, CoreInit.SUITS_CLOTH.get(), CoreInit.SUITS_CLOTH_GREEN.get(), Tags.Items.DYES_GREEN, "green", "cloth_suits");

		clothColorRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_BLACK.get(), Tags.Items.DYES_BLACK, "black", "leather_suits");
		clothColorRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_BROWN.get(), Tags.Items.DYES_BROWN, "brown", "leather_suits");
		clothColorRecipe(cons, CoreInit.SUITS_LEATHER.get(), CoreInit.SUITS_LEATHER_WHITE.get(), Tags.Items.DYES_WHITE, "white", "leather_suits");

		clothColorRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_BLACK.get(), Tags.Items.DYES_BLACK, "black", "dress_maid");
		clothColorRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_BROWN.get(), Tags.Items.DYES_BROWN, "brown", "dress_maid");
		clothColorRecipe(cons, CoreInit.LONG_MAID.get(), CoreInit.LONG_MAID_GRAY.get(), Tags.Items.DYES_GRAY, "gray", "dress_maid");
	}

	private static void clothColorRecipe(Consumer<FinishedRecipe> cons, ItemLike in, ItemLike out, TagKey<Item> color, String cname, String name) {
		ShapelessRecipeBuilder.shapeless(out, 1)
				.requires(in)
				.requires(color)
				.group("cloth_coloring")
				.unlockedBy("has_" + name, has(in))
				.save(cons, "dcs_climate:clothing/coloring_" + name + "_" + cname);
	}

	private static void smeltingRecipes(Consumer<FinishedRecipe> cons) {

		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.GEM_FLINT), CoreInit.GEM_CHALCEDONY.get(), 200, "gem_chalcedony", Items.FLINT, "has_flint");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_CRYSTAL), BuildInit.GLASS_CRYSTAL.get().asItem(), 200, "dust_crystal", CoreInit.DUST_CRYSTAL.get(), "has_dust_crystal");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_PLANT), CoreInit.DUST_ASH.get(), 200, "dust_ash", CoreInit.DUST_PLANT.get(), "has_dust_plant");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_PLANT), CoreInit.DUST_ASH.get(), 200, "dust_wood", CoreInit.DUST_WOOD.get(), "has_dust_wood");

		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_IRON), Items.IRON_INGOT, 200, "ingot_iron", CoreInit.OREDUST_RED1.get(), "has_dust_iron");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_COPPER), Items.COPPER_INGOT, 200, "ingot_copper", CoreInit.OREDUST_WHITE1.get(), "has_dust_copper");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_GOLD), Items.GOLD_INGOT, 200, "ingot_gold", CoreInit.OREDUST_WHITE2.get(), "has_dust_gold");

		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUSTBLOCK_STEEL), Items.IRON_BLOCK, 200, "container_iron", CoreInit.DUSTBLOCK_STEEL.get().asItem(), "has_dustblock_iron");

		// アルミ建材の還元
		smeltingRecipe(cons, Ingredient.of(BuildInit.SLAB_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_slab_metal", BuildInit.SLAB_METAL.get().asItem(), "has_slab_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.FENCE_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_fence_metal", BuildInit.FENCE_METAL.get().asItem(), "has_fence_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.LADDER_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_ladder_metal", BuildInit.LADDER_METAL.get().asItem(), "has_ladder_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.STAIRS_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_stairs_metal", BuildInit.STAIRS_METAL.get().asItem(), "has_stairs_metal");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.ALMINUM_ROOFS), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_roof_metal", BuildInit.ROOF_METAL_GRAY.get().asItem(), "has_roof_metal");
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
