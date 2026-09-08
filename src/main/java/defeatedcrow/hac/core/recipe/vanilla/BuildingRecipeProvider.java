package defeatedcrow.hac.core.recipe.vanilla;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class BuildingRecipeProvider extends RecipeProvider {

	public BuildingRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> cons) {

		for (MaterialRecipes.Alloy alloy : MaterialRecipes.ALLOY_VARIANT) {
			alloyRecipes(cons, alloy);
		}

		for (MaterialRecipes.GemBlock gem : MaterialRecipes.GEMBLOCK_VARIANT) {
			gemBlockRecipes(cons, gem);
		}

		for (MaterialRecipes.Stone stone : MaterialRecipes.STONE_VARIANT) {
			stoneRecipes(cons, stone);
		}

		buildingRecipes(cons);

	}

	private static void alloyRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Alloy alloy) {

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, alloy.metalBlock().get().asItem())
		    .pattern("XXX")
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', alloy.ingotItem().get())
		    .group("storage_pack")
		    .unlockedBy("has_" + alloy.name() + "_ingot", has(alloy.ingotItem().get()))
		    .save(cons, "dcs_climate:core/metalblock_" + alloy.name());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, alloy.ingotItem()
		    .get(), 9)
		    .requires(alloy.getTag("storage_blocks"))
		    .group("storage_unpack")
		    .unlockedBy("has_" + alloy.name() + "_block", has(alloy.metalBlock()
		        .get()))
		    .save(cons, "dcs_climate:core/ingot_" + alloy.name());

		if (alloy == MaterialRecipes.BSCCO) {

			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, alloy.dustBlock()
			    .get())
			    .pattern("LLL")
			    .pattern("PPP")
			    .pattern("SST")
			    .define('L', CoreInit.DUST_LIME.get())
			    .define('P', alloy.dustPrimary()
			        .get())
			    .define('S', alloy.dustSecondary()
			        .get())
			    .define('T', alloy.dustTertiary()
			        .get())
			    .unlockedBy("has_" + DCUtil.getName(alloy.dustPrimary()
			        .get()), has(alloy.dustPrimary()
			            .get()))
			    .group("dustblock_pack")
			    .save(cons, "dcs_climate:core/dustblock_" + alloy.name());

		} else if (alloy == MaterialRecipes.HASTELLOY) {

			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, alloy.dustBlock()
			    .get())
			    .pattern("PPP")
			    .pattern("PSS")
			    .pattern("TTL")
			    .define('L', TagDC.ItemTag.DUST_COBALT)
			    .define('P', alloy.dustPrimary()
			        .get())
			    .define('S', alloy.dustSecondary()
			        .get())
			    .define('T', alloy.dustTertiary()
			        .get())
			    .unlockedBy("has_" + DCUtil.getName(alloy.dustPrimary()
			        .get()), has(alloy.dustPrimary()
			            .get()))
			    .group("dustblock_pack")
			    .save(cons, "dcs_climate:core/dustblock_" + alloy.name());

		} else {

			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, alloy.dustBlock()
			    .get())
			    .pattern("PPP")
			    .pattern("PPP")
			    .pattern("SST")
			    .define('P', alloy.dustPrimary()
			        .get())
			    .define('S', alloy.dustSecondary()
			        .get())
			    .define('T', alloy.dustTertiary()
			        .get())
			    .unlockedBy("has_" + DCUtil.getName(alloy.dustPrimary()
			        .get()), has(alloy.dustPrimary()
			            .get()))
			    .group("dustblock_pack")
			    .save(cons, "dcs_climate:core/dustblock_" + alloy.name());

		}

	}

	private static void gemBlockRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.GemBlock gem) {

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, gem.block()
		    .get())
		    .pattern("XXX")
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', gem.tag()
		        .get())
		    .group("storage_pack")
		    .unlockedBy("has_" + gem.name() + "_gem", has(gem.tag()
		        .get()))
		    .save(cons, "dcs_climate:core/gemblock_" + gem.name() + "_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, gem.gem()
		    .get())
		    .requires(gem.block()
		        .get())
		    .group("storage_unpack")
		    .unlockedBy("has_" + gem.name() + "_block", has(gem.block()
		        .get()))
		    .save(cons, "dcs_climate:core/gem_" + gem.name() + "_2");
	}

	private static void stoneRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Stone stone) {

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone.bricksBlock()
		    .get())
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', stone.stoneBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/bricks2_" + stone.name());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone.pillarBlock()
		    .get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', stone.bricksBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/pillar2_" + stone.name());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone.chiseledBlock()
		    .get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', stone.pillarBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/chiseled2_" + stone.name());

		ShapedRecipeBuilder.shaped( RecipeCategory.BUILDING_BLOCKS, stone.stoneBlock()
		    .get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', stone.chiseledBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/reverse2_" + stone.name());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone.stairsBlock()
		    .get(), 4)
		    .pattern("X  ")
		    .pattern("XX ")
		    .pattern("XXX")
		    .define('X', stone.stoneBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/stairs2_" + stone.name());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone.slabBlock()
		    .get(), 6)
		    .pattern("XXX")
		    .define('X', stone.stoneBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/slab2_" + stone.name());

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone.wallBlock()
		    .get(), 6)
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', stone.stoneBlock()
		        .get())
		    .unlockedBy("has_" + stone.name() + "_stone", has(stone.stoneBlock()
		        .get()))
		    .save(cons, "dcs_climate:build/wall2_" + stone.name());

	}

	private static void buildingRecipes(Consumer<FinishedRecipe> cons) {

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.PLATE_METAL.get(), 2)
		    .pattern("XYX")
		    .define('X', Tags.Items.INGOTS_IRON)
		    .define('Y', Items.IRON_BARS)
		    .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
		    .save(cons, "dcs_climate:build/plate_metal_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.PLATE_MESH.get(), 2)
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .define('Y', Items.IRON_BARS)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/plate_mesh_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.SLAB_METAL.get(), 3)
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_slab_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_METAL.get(), 3)
		    .pattern("X  ")
		    .pattern(" X ")
		    .pattern("  X")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_stairs_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_METAL.get(), 3)
		    .pattern("  X")
		    .pattern(" X ")
		    .pattern("X  ")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_stairs_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_SLIM_METAL.get(), 2)
		    .pattern("Y  ")
		    .pattern("XY ")
		    .pattern(" XY")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .define('Y', ItemTags.PLANKS)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/slim_metal_stairs_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_HOL_METAL.get(), 3)
		    .pattern("XXX")
		    .pattern("YYY")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .define('Y', Items.IRON_BARS)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/louver_hol_metal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_HOL_WHITE.get(), 1)
		    .requires(BuildInit.LOUVER_HOL_METAL.get())
		    .requires(Tags.Items.DYES_WHITE)
		    .unlockedBy("has_hol_louver", has(BuildInit.LOUVER_HOL_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_hol_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_HOL_BLUE.get(), 1)
		    .requires(BuildInit.LOUVER_HOL_METAL.get())
		    .requires(Tags.Items.DYES_BLUE)
		    .unlockedBy("has_hol_louver", has(BuildInit.LOUVER_HOL_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_hol_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_HOL_BLACK.get(), 1)
		    .requires(BuildInit.LOUVER_HOL_METAL.get())
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_hol_louver", has(BuildInit.LOUVER_HOL_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_hol_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_HOL_RED.get(), 1)
		    .requires(BuildInit.LOUVER_HOL_METAL.get())
		    .requires(Tags.Items.DYES_RED)
		    .unlockedBy("has_hol_louver", has(BuildInit.LOUVER_HOL_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_hol_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_HOL_GREEN.get(), 1)
		    .requires(BuildInit.LOUVER_HOL_METAL.get())
		    .requires(Tags.Items.DYES_GREEN)
		    .unlockedBy("has_hol_louver", has(BuildInit.LOUVER_HOL_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_hol_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_VER_METAL.get(), 3)
		    .pattern("XYX")
		    .pattern("XYX")
		    .pattern("XYX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .define('Y', Items.IRON_BARS)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/louver_ver_metal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_VER_WHITE.get(), 1)
		    .requires(BuildInit.LOUVER_VER_METAL.get())
		    .requires(Tags.Items.DYES_WHITE)
		    .unlockedBy("has_ver_louver", has(BuildInit.LOUVER_VER_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_ver_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_VER_BLUE.get(), 1)
		    .requires(BuildInit.LOUVER_VER_METAL.get())
		    .requires(Tags.Items.DYES_BLUE)
		    .unlockedBy("has_ver_louver", has(BuildInit.LOUVER_VER_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_ver_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_VER_BLACK.get(), 1)
		    .requires(BuildInit.LOUVER_VER_METAL.get())
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_ver_louver", has(BuildInit.LOUVER_VER_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_ver_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_VER_RED.get(), 1)
		    .requires(BuildInit.LOUVER_VER_METAL.get())
		    .requires(Tags.Items.DYES_RED)
		    .unlockedBy("has_ver_louver", has(BuildInit.LOUVER_VER_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_ver_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOUVER_VER_GREEN.get(), 1)
		    .requires(BuildInit.LOUVER_VER_METAL.get())
		    .requires(Tags.Items.DYES_GREEN)
		    .unlockedBy("has_ver_louver", has(BuildInit.LOUVER_VER_METAL.get()))
		    .save(cons, "dcs_climate:build/louver_ver_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_SIMPLE_METAL.get(), 6)
		    .pattern("XXX")
		    .pattern("YYY")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .define('Y', Tags.Items.GLASS)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/window_simple_metal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_FLOWER_METAL.get(), 1)
		    .requires(BuildInit.WINDOW_SIMPLE_METAL.get())
		    .requires(ItemTags.FLOWERS)
		    .unlockedBy("has_metal_simple_window", has(BuildInit.WINDOW_SIMPLE_METAL.get()))
		    .save(cons, "dcs_climate:build/window_flower_metal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_GOTHIC_METAL.get(), 1)
		    .requires(BuildInit.WINDOW_SIMPLE_METAL.get())
		    .requires(Tags.Items.GEMS_EMERALD)
		    .unlockedBy("has_metal_simple_window", has(BuildInit.WINDOW_SIMPLE_METAL.get()))
		    .save(cons, "dcs_climate:build/window_gothic_metal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_NET_METAL.get(), 1)
		    .requires(BuildInit.WINDOW_SIMPLE_METAL.get())
		    .requires(Items.IRON_BARS)
		    .unlockedBy("has_metal_simple_window", has(BuildInit.WINDOW_SIMPLE_METAL.get()))
		    .save(cons, "dcs_climate:build/window_net_metal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_FLOWER_WHITE.get(), 1)
		    .requires(BuildInit.WINDOW_FLOWER_METAL.get())
		    .requires(Tags.Items.DYES_WHITE)
		    .unlockedBy("has_metal_flower_window", has(BuildInit.WINDOW_FLOWER_METAL.get()))
		    .save(cons, "dcs_climate:build/window_flower_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_GOTHIC_WHITE.get(), 1)
		    .requires(BuildInit.WINDOW_GOTHIC_METAL.get())
		    .requires(Tags.Items.DYES_WHITE)
		    .unlockedBy("has_metal_gothic_window", has(BuildInit.WINDOW_GOTHIC_METAL.get()))
		    .save(cons, "dcs_climate:build/window_gothic_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_NET_WHITE.get(), 1)
		    .requires(BuildInit.WINDOW_NET_METAL.get())
		    .requires(Tags.Items.DYES_WHITE)
		    .unlockedBy("has_metal_net_window", has(BuildInit.WINDOW_NET_METAL.get()))
		    .save(cons, "dcs_climate:build/window_net_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_FLOWER_BLACK.get(), 1)
		    .requires(BuildInit.WINDOW_FLOWER_METAL.get())
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_metal_flower_window", has(BuildInit.WINDOW_FLOWER_METAL.get()))
		    .save(cons, "dcs_climate:build/window_flower_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_GOTHIC_BLACK.get(), 1)
		    .requires(BuildInit.WINDOW_GOTHIC_METAL.get())
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_metal_gothic_window", has(BuildInit.WINDOW_GOTHIC_METAL.get()))
		    .save(cons, "dcs_climate:build/window_gothic_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.WINDOW_NET_BLACK.get(), 1)
		    .requires(BuildInit.WINDOW_NET_METAL.get())
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_metal_net_window", has(BuildInit.WINDOW_NET_METAL.get()))
		    .save(cons, "dcs_climate:build/window_net_black_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_BARK.get(), 3)
		    .pattern("  Y")
		    .pattern(" YX")
		    .pattern("YX ")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', TagDC.ItemTag.BARKS)
		    .unlockedBy("has_barks", has(TagDC.ItemTag.BARKS))
		    .save(cons, "dcs_climate:build/bark_stairs");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_SLIM_METAL.get(), 2)
		    .pattern("  Y")
		    .pattern(" YX")
		    .pattern("YX ")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .define('Y', ItemTags.PLANKS)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/slim_metal_stairs_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_GRAY.get(), 6)
		    .pattern("X  ")
		    .pattern("XX ")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_roof_gray_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_GRAY.get(), 6)
		    .pattern("  X")
		    .pattern(" XX")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_roof_gray_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_YELLOW.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.ROOF_METAL_GRAY.get())
		    .define('Y', Tags.Items.DYES_YELLOW)
		    .unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
		    .save(cons, "dcs_climate:build/metal_roof_yellow_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_BLUE.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.ROOF_METAL_GRAY.get())
		    .define('Y', Tags.Items.DYES_BLUE)
		    .unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
		    .save(cons, "dcs_climate:build/metal_roof_blue_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_BLACK.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.ROOF_METAL_GRAY.get())
		    .define('Y', Tags.Items.DYES_BLACK)
		    .unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
		    .save(cons, "dcs_climate:build/metal_roof_black_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_RED.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.ROOF_METAL_GRAY.get())
		    .define('Y', Tags.Items.DYES_RED)
		    .unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
		    .save(cons, "dcs_climate:build/metal_roof_red_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ROOF_METAL_GREEN.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.ROOF_METAL_GRAY.get())
		    .define('Y', Tags.Items.DYES_GREEN)
		    .unlockedBy("has_metal_roof", has(BuildInit.ROOF_METAL_GRAY.get()))
		    .save(cons, "dcs_climate:build/metal_roof_green_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LADDER_METAL.get(), 7)
		    .pattern("X X")
		    .pattern("XXX")
		    .pattern("X X")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_ladder_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FENCE_METAL.get(), 6)
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.INGOT_ALUMINUM)
		    .unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:build/metal_fence_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Tags.Items.COBBLESTONE)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/normal_flintbricks");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS_BLACK.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Blocks.ANDESITE)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/black_flintbricks");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS_BLACK.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Blocks.DEEPSLATE)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/black_flintbricks2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS_WHITE.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Blocks.DIORITE)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/white_flintbricks");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS_WHITE.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Blocks.CALCITE)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/white_flintbricks2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS_RED.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Blocks.GRANITE)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/red_flintbricks");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLINTBRICKS_RED.get(), 4)
		    .pattern("XY")
		    .pattern("YX")
		    .define('X', TagDC.ItemTag.GEM_FLINT)
		    .define('Y', Blocks.NETHERRACK)
		    .unlockedBy("has_flint", has(Items.FLINT))
		    .save(cons, "dcs_climate:build/red_flintbricks2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.MOSAIC_BLACK.get(), 1)
		    .requires(TagDC.ItemTag.DUST_CRYSTAL)
		    .requires(Tags.Items.STONE)
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_dye_black", has(Tags.Items.DYES_BLACK))
		    .save(cons, "dcs_climate:build/black_mosaic_block");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.MOSAIC_BLUE.get(), 1)
		    .requires(TagDC.ItemTag.DUST_CRYSTAL)
		    .requires(Tags.Items.STONE)
		    .requires(Tags.Items.DYES_BLUE)
		    .unlockedBy("has_dye_blue", has(Tags.Items.DYES_BLUE))
		    .save(cons, "dcs_climate:build/black_mosaic_blue");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.MOSAIC_YELLOW.get(), 1)
		    .requires(TagDC.ItemTag.DUST_CRYSTAL)
		    .requires(Tags.Items.STONE)
		    .requires(Tags.Items.DYES_YELLOW)
		    .unlockedBy("has_dye_yellow", has(Tags.Items.DYES_YELLOW))
		    .save(cons, "dcs_climate:build/black_mosaic_yellow");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.MOSAIC_RED.get(), 1)
		    .requires(TagDC.ItemTag.DUST_CRYSTAL)
		    .requires(Tags.Items.STONE)
		    .requires(Tags.Items.DYES_RED)
		    .unlockedBy("has_dye_red", has(Tags.Items.DYES_RED))
		    .save(cons, "dcs_climate:build/black_mosaic_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.SLAB_DIRT.get(), 6)
		    .pattern("XXX")
		    .define('X', ItemTags.DIRT)
		    .unlockedBy("has_dirt", has(ItemTags.DIRT))
		    .save(cons, "dcs_climate:core/slab_dirt");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.DIRT, 1)
		    .pattern("X")
		    .pattern("X")
		    .define('X', BuildInit.SLAB_DIRT.get())
		    .unlockedBy("has_dirt_slab", has(BuildInit.SLAB_DIRT.get()))
		    .save(cons, "dcs_climate:core/dirt_from_slabs");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SLAB_DIRT.get(), 1)
		    .requires(BuildInit.SLAB_PATH.get())
		    .unlockedBy("has_slab_path", has(BuildInit.SLAB_PATH.get()))
		    .save(cons, "dcs_climate:core/slab_path_to_dirt");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SLAB_DIRT.get(), 1)
		    .requires(BuildInit.SLAB_GRASS.get())
		    .unlockedBy("has_slab_grass", has(BuildInit.SLAB_GRASS.get()))
		    .save(cons, "dcs_climate:core/slab_grass_to_dirt");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.SLAB_GRAVEL.get(), 6)
		    .pattern("XXX")
		    .define('X', Tags.Items.GRAVEL)
		    .unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
		    .save(cons, "dcs_climate:core/slab_gravel");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.GRAVEL, 1)
		    .pattern("X")
		    .pattern("X")
		    .define('X', BuildInit.SLAB_GRAVEL.get())
		    .unlockedBy("has_gravel_slab", has(BuildInit.SLAB_GRAVEL.get()))
		    .save(cons, "dcs_climate:core/gravel_from_slabs");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ADOBE_BLOCK_WET.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', ItemTags.DIRT)
		    .unlockedBy("has_dirt", has(ItemTags.DIRT))
		    .save(cons, "dcs_climate:core/adobe_wet");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ADOBE_BRICKS.get(), 1)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', CoreInit.ADOBE_BRICK_ITEM.get())
		    .unlockedBy("has_adobe_brick_item", has(CoreInit.ADOBE_BRICK_ITEM.get()))
		    .save(cons, "dcs_climate:core/adobe_bricks_block");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_ADOBE.get(), 4)
		    .pattern("X  ")
		    .pattern("XX ")
		    .pattern("XXX")
		    .define('X', BuildInit.ADOBE_BRICKS.get())
		    .unlockedBy("has_adobe_brick", has(BuildInit.ADOBE_BRICKS.get()))
		    .save(cons, "dcs_climate:core/adobe_bricks_stairs_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STAIRS_ADOBE.get(), 4)
		    .pattern("  X")
		    .pattern(" XX")
		    .pattern("XXX")
		    .define('X', BuildInit.ADOBE_BRICKS.get())
		    .unlockedBy("has_adobe_brick", has(BuildInit.ADOBE_BRICKS.get()))
		    .save(cons, "dcs_climate:core/adobe_bricks_stairs_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.SLAB_ADOBE.get(), 6)
		    .pattern("XXX")
		    .define('X', BuildInit.ADOBE_BRICKS.get())
		    .unlockedBy("has_adobe_brick", has(BuildInit.ADOBE_BRICKS.get()))
		    .save(cons, "dcs_climate:core/adobe_bricks_slab");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.WALL_ADOBE.get(), 6)
		    .pattern("XXX")
		    .pattern("XXX")
		    .define('X', BuildInit.ADOBE_BRICKS.get())
		    .unlockedBy("has_adobe_brick", has(BuildInit.ADOBE_BRICKS.get()))
		    .save(cons, "dcs_climate:core/adobe_bricks_wall");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.MORTAR.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(Tags.Items.SAND)
		    .requires(TagDC.ItemTag.WATER)
		    .unlockedBy("has_dust_lime", has(TagDC.ItemTag.DUST_LIME))
		    .save(cons, "dcs_climate:build/mortar_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.MORTAR.get(), 4)
		    .requires(TagDC.ItemTag.ORES_GYPSUM)
		    .requires(TagDC.ItemTag.ORES_GYPSUM)
		    .requires(Tags.Items.SAND)
		    .requires(TagDC.ItemTag.WATER)
		    .unlockedBy("has_ore_gypsum", has(TagDC.ItemTag.ORES_GYPSUM))
		    .save(cons, "dcs_climate:build/mortar_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DITCH.get(), 3)
		    .pattern("X X")
		    .pattern("XXX")
		    .define('X', BuildInit.MORTAR.get())
		    .unlockedBy("has_mortar_block", has(BuildInit.MORTAR.get()))
		    .save(cons, "dcs_climate:core/ditch_block");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_BLACK.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.BLACK_TERRACOTTA)
		    .unlockedBy("has_terracotta_black", has(Items.BLACK_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_RED.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.RED_TERRACOTTA)
		    .unlockedBy("has_terracotta_red", has(Items.RED_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_red");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_GREEN.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.GREEN_TERRACOTTA)
		    .unlockedBy("has_terracotta_green", has(Items.GREEN_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_green");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_BROWN.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.BROWN_TERRACOTTA)
		    .unlockedBy("has_terracotta_brown", has(Items.BROWN_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_brown");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_BLUE.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.BLUE_TERRACOTTA)
		    .unlockedBy("has_terracotta_blue", has(Items.BLUE_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_PURPLE.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.PURPLE_TERRACOTTA)
		    .unlockedBy("has_terracotta_purple", has(Items.PURPLE_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_purple");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_CYAN.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.CYAN_TERRACOTTA)
		    .unlockedBy("has_terracotta_cyan", has(Items.CYAN_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_cyan");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_LIGHT_GRAY.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.LIGHT_GRAY_TERRACOTTA)
		    .unlockedBy("has_terracotta_light_gray", has(Items.LIGHT_GRAY_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_light_gray");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_GRAY.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.GRAY_TERRACOTTA)
		    .unlockedBy("has_terracotta_gray", has(Items.GRAY_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_gray");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_PINK.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.PINK_TERRACOTTA)
		    .unlockedBy("has_terracotta_pink", has(Items.PINK_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_pink");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_LIME.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.LIME_TERRACOTTA)
		    .unlockedBy("has_terracotta_lime", has(Items.LIME_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_lime");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_YELLOW.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.YELLOW_TERRACOTTA)
		    .unlockedBy("has_terracotta_yellow", has(Items.YELLOW_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_yellow");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_LIGHT_BLUE.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.LIGHT_BLUE_TERRACOTTA)
		    .unlockedBy("has_terracotta_light_blue", has(Items.LIGHT_BLUE_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_light_blue");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_MAGENTA.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.MAGENTA_TERRACOTTA)
		    .unlockedBy("has_terracotta_magenta", has(Items.MAGENTA_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_magenta");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_ORANGE.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.ORANGE_TERRACOTTA)
		    .unlockedBy("has_terracotta_orange", has(Items.ORANGE_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_orange");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CLAYBRICKS_WHITE.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', Items.WHITE_TERRACOTTA)
		    .unlockedBy("has_terracotta_white", has(Items.WHITE_TERRACOTTA))
		    .save(cons, "dcs_climate:core/bricks_terracotta_white");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_BLACK.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_BLACK)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_black_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_RED.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_RED)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_red_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_GREEN.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_GREEN)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_green_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_BROWN.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_BROWN)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_brown_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_BLUE.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_BLUE)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_blue_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_PURPLE.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_PURPLE)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_purple_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_CYAN.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_CYAN)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_cyan_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_GRAY.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_GRAY)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_gray_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_LIGHT_GRAY.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_LIGHT_GRAY)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_light_gray_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_PINK.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_PINK)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_pink_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_LIME.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_LIME)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_lime_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_YELLOW.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_YELLOW)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_yellow_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_LIGHT_BLUE.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_LIGHT_BLUE)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_light_blue_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_MAGENTA.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_MAGENTA)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_magenta_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_ORANGE.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_ORANGE)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_orange_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LINOLEUM_WHITE.get(), 4)
		    .requires(TagDC.ItemTag.DUST_LIME)
		    .requires(TagDC.ItemTag.PLANT_OIL)
		    .requires(TagDC.ItemTag.SAP_RESIN)
		    .requires(TagDC.ItemTag.CLOTHS)
		    .requires(ItemTags.PLANKS)
		    .requires(Tags.Items.DYES_WHITE)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/linoleum_white_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.GLASS_LIGHT.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.GLASS_CRYSTAL.get())
		    .define('Y', Items.GLOWSTONE_DUST)
		    .unlockedBy("has_dust_glowstone", has(Items.GLOWSTONE_DUST))
		    .save(cons, "dcs_climate:build/glass_light1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.GLASS_LIGHT.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.GLASS_CRYSTAL.get())
		    .define('Y', MagicInit.EXTRACT_WHITE.get())
		    .unlockedBy("has_extract_white", has(MagicInit.EXTRACT_WHITE.get()))
		    .save(cons, "dcs_climate:build/glass_light2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.GLASS_DARK.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.GLASS_CRYSTAL.get())
		    .define('Y', TagDC.ItemTag.DUST_SILVER)
		    .unlockedBy("has_dust_silver", has(TagDC.ItemTag.DUST_SILVER))
		    .save(cons, "dcs_climate:build/glass_dark1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.GLASS_DARK.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.GLASS_CRYSTAL.get())
		    .define('Y', MagicInit.EXTRACT_BLACK.get())
		    .unlockedBy("has_extract_black", has(MagicInit.EXTRACT_BLACK.get()))
		    .save(cons, "dcs_climate:build/glass_dark2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', TagDC.ItemTag.GEM_AGATES)
		    .define('Y', Tags.Items.DUSTS_REDSTONE)
		    .unlockedBy("has_gem_agate", has(TagDC.ItemTag.GEM_AGATES))
		    .save(cons, "dcs_climate:build/chalcedony_lamp1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', TagDC.ItemTag.GEM_AGATES)
		    .define('Y', MagicInit.EXTRACT_WHITE.get())
		    .unlockedBy("has_gem_agate", has(TagDC.ItemTag.GEM_AGATES))
		    .save(cons, "dcs_climate:build/chalcedony_lamp2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_GLASS.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', Tags.Items.GLASS)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_glass");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_TABLE_GLASS.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" Z ")
		    .define('X', Tags.Items.GLASS)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .define('Z', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_table_glass");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DOWNLIGHT_WOOD.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', Tags.Items.RODS_WOODEN)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/downlight_wood");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.DOWNLIGHT_WHITE.get(), 1)
		    .requires(Tags.Items.DYES_WHITE)
		    .requires(BuildInit.DOWNLIGHT_WOOD.get())
		    .unlockedBy("has_downlight", has(BuildInit.DOWNLIGHT_WOOD.get()))
		    .save(cons, "dcs_climate:build/downlight_white");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.DOWNLIGHT_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.DOWNLIGHT_WOOD.get())
		    .unlockedBy("has_downlight", has(BuildInit.DOWNLIGHT_WOOD.get()))
		    .save(cons, "dcs_climate:build/downlight_black");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_TABLE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" Z ")
		    .define('X', TagDC.ItemTag.GEM_CHALCEDONY)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .define('Z', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_table");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_FLUORITE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" Z ")
		    .define('X', TagDC.ItemTag.GEM_FLUORITE)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .define('Z', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_fluorite");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_JET.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" Z ")
		    .define('X', TagDC.ItemTag.GEM_JET)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .define('Z', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_jet");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_DESERTROSE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" Z ")
		    .define('X', TagDC.ItemTag.GEM_DESERTROSE)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .define('Z', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_desertrose");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAL_LAMP_SERPENTINE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" Z ")
		    .define('X', TagDC.ItemTag.GEM_SERPENTINE)
		    .define('Y', BuildInit.CHAL_LAMP.get())
		    .define('Z', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_chal_lamp", has(BuildInit.CHAL_LAMP.get()))
		    .save(cons, "dcs_climate:build/chalcedony_lamp_serpentine");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_IRON.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CHAL_LAMP_TABLE.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_table_lamp", has(BuildInit.CHAL_LAMP_TABLE.get()))
		    .save(cons, "dcs_climate:build/chandelier_iron_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_LAMP.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CHAL_LAMP_TABLE.get())
		    .define('Y', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_table_lamp", has(BuildInit.CHAL_LAMP_TABLE.get()))
		    .save(cons, "dcs_climate:build/chandelier_lamp_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_FLUORITE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CHAL_LAMP_FLUORITE.get())
		    .define('Y', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_fluorite_lamp", has(BuildInit.CHAL_LAMP_FLUORITE.get()))
		    .save(cons, "dcs_climate:build/chandelier_fluorite_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_JET.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CHAL_LAMP_JET.get())
		    .define('Y', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_jet_lamp", has(BuildInit.CHAL_LAMP_JET.get()))
		    .save(cons, "dcs_climate:build/chandelier_jet_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_DESERTROSE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CHAL_LAMP_DESERTROSE.get())
		    .define('Y', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_desertrose_lamp", has(BuildInit.CHAL_LAMP_DESERTROSE.get()))
		    .save(cons, "dcs_climate:build/chandelier_desertrose_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_SERPENTINE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CHAL_LAMP_SERPENTINE.get())
		    .define('Y', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_serpentine_lamp", has(BuildInit.CHAL_LAMP_SERPENTINE.get()))
		    .save(cons, "dcs_climate:build/chandelier_serpentine_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CANDLESTICK.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', Tags.Items.INGOTS_IRON)
		    .define('Y', ItemTags.CANDLES)
		    .unlockedBy("has_candles", has(ItemTags.CANDLES))
		    .save(cons, "dcs_climate:build/candlestick_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHANDELIER_CANDLE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', BuildInit.CANDLESTICK.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_candlestick", has(BuildInit.CANDLESTICK.get()))
		    .save(cons, "dcs_climate:build/chandelier_candle_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.BERRY_LANTERN_WHITE.get(), 1)
		    .pattern(" X ")
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', Items.PAPER)
		    .define('Y', TagDC.ItemTag.CROP_LANTERN)
		    .unlockedBy("has_crop_lantern", has(TagDC.ItemTag.CROP_LANTERN))
		    .save(cons, "dcs_climate:build/berry_lantern_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BERRY_LANTERN_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.BERRY_LANTERN_WHITE.get())
		    .unlockedBy("has_white_berry_lantern", has(BuildInit.BERRY_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/berry_lantern_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BERRY_LANTERN_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.BERRY_LANTERN_WHITE.get())
		    .unlockedBy("has_white_berry_lantern", has(BuildInit.BERRY_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/berry_lantern_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BERRY_LANTERN_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.BERRY_LANTERN_WHITE.get())
		    .unlockedBy("has_white_berry_lantern", has(BuildInit.BERRY_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/berry_lantern_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BERRY_LANTERN_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.BERRY_LANTERN_WHITE.get())
		    .unlockedBy("has_white_berry_lantern", has(BuildInit.BERRY_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/berry_lantern_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BERRY_LANTERN_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.HAC_LANTERN)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .unlockedBy("has_berry_lantern", has(TagDC.ItemTag.HAC_LANTERN))
		    .save(cons, "dcs_climate:build/berry_lantern_blieaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.ANDON_LANTERN_WHITE.get(), 1)
		    .pattern("ZXZ")
		    .pattern("ZYZ")
		    .define('X', Items.PAPER)
		    .define('Y', TagDC.ItemTag.PLANT_OIL)
		    .define('Z', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_plant_oil", has(TagDC.ItemTag.PLANT_OIL))
		    .save(cons, "dcs_climate:build/andon_lantern_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.ANDON_LANTERN_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.ANDON_LANTERN_WHITE.get())
		    .unlockedBy("has_white_andon_lantern", has(BuildInit.ANDON_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/andon_lantern_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.ANDON_LANTERN_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.ANDON_LANTERN_WHITE.get())
		    .unlockedBy("has_white_andon_lantern", has(BuildInit.ANDON_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/andon_lantern_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.ANDON_LANTERN_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.ANDON_LANTERN_WHITE.get())
		    .unlockedBy("has_white_andon_lantern", has(BuildInit.ANDON_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/andon_lantern_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.ANDON_LANTERN_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.ANDON_LANTERN_WHITE.get())
		    .unlockedBy("has_white_andon_lantern", has(BuildInit.ANDON_LANTERN_WHITE.get()))
		    .save(cons, "dcs_climate:build/andon_lantern_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.ANDON_LANTERN_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.HAC_ANDON)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .unlockedBy("has_andon_lantern", has(TagDC.ItemTag.HAC_ANDON))
		    .save(cons, "dcs_climate:build/andon_lantern_blieaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.WOODEN_WALL_BEECH.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', FoodInit.PLANK_BH_COMMON.get())
		    .unlockedBy("has_plank_beech", has(FoodInit.PLANK_BH_COMMON.get()))
		    .save(cons, "dcs_climate:core/wooden_wall_beech_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.HERRINGBONE_BEECH.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', BuildInit.WOODEN_WALL_BEECH.get())
		    .unlockedBy("has_wall_beech", has(BuildInit.WOODEN_WALL_BEECH.get()))
		    .save(cons, "dcs_climate:core/herringbone_beech_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.WOODEN_WALL_WALNUT.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', FoodInit.PLANK_BH_WALNUT.get())
		    .unlockedBy("has_plank_walnut", has(FoodInit.PLANK_BH_WALNUT.get()))
		    .save(cons, "dcs_climate:core/wooden_wall_walnut_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.HERRINGBONE_WALNUT.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', BuildInit.WOODEN_WALL_WALNUT.get())
		    .unlockedBy("has_wall_walnut", has(BuildInit.WOODEN_WALL_WALNUT.get()))
		    .save(cons, "dcs_climate:core/herringbone_walnut_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.WOODEN_WALL_SWEET.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', FoodInit.PLANK_BH_SWEET.get())
		    .unlockedBy("has_plank_sweet", has(FoodInit.PLANK_BH_SWEET.get()))
		    .save(cons, "dcs_climate:core/wooden_wall_sweet_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.HERRINGBONE_SWEET.get(), 4)
		    .pattern("XX")
		    .pattern("XX")
		    .define('X', BuildInit.WOODEN_WALL_SWEET.get())
		    .unlockedBy("has_wall_sweet", has(BuildInit.WOODEN_WALL_SWEET.get()))
		    .save(cons, "dcs_climate:core/herringbone_sweet_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIN_GOLD.get(), 1)
		    .pattern("X")
		    .pattern("Y")
		    .pattern("X")
		    .define('X', Tags.Items.NUGGETS_GOLD)
		    .define('Y', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
		    .save(cons, "dcs_climate:build/chain_gold_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIN_COPPER.get(), 1)
		    .pattern("X")
		    .pattern("Y")
		    .pattern("X")
		    .define('X', Tags.Items.NUGGETS_IRON)
		    .define('Y', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
		    .save(cons, "dcs_climate:build/chain_copper_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIN_VINE.get(), 1)
		    .pattern("X")
		    .pattern("X")
		    .pattern("X")
		    .define('X', TagDC.ItemTag.VINE)
		    .unlockedBy("has_vine", has(TagDC.ItemTag.VINE))
		    .save(cons, "dcs_climate:build/chain_vine_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LADDER_VINE.get(), 3)
		    .pattern("X X")
		    .pattern("XXX")
		    .pattern("X X")
		    .define('X', TagDC.ItemTag.VINE)
		    .unlockedBy("has_vine", has(TagDC.ItemTag.VINE))
		    .save(cons, "dcs_climate:build/ladder_vine_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TOOL_HOOK.get(), 1)
		    .pattern("Z")
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Tags.Items.RODS_WOODEN)
		    .define('Z', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE))
		    .save(cons, "dcs_climate:build/wall_hook_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISPLAY_SHELF.get(), 1)
		    .pattern("Z Z")
		    .pattern("XXX")
		    .define('X', ItemTags.PLANKS)
		    .define('Z', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE))
		    .save(cons, "dcs_climate:build/display_shelf_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISPLAY_SHELF_WOOD.get(), 1)
		    .pattern("XZX")
		    .pattern("XZX")
		    .define('X', ItemTags.PLANKS)
		    .define('Z', Items.IRON_BARS)
		    .unlockedBy("has_brass", has(Items.IRON_BARS))
		    .save(cons, "dcs_climate:build/display_shelf_wood_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISPLAY_SHELF_IRON.get(), 1)
		    .pattern("XZX")
		    .pattern("XZX")
		    .define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)
		    .define('Z', Items.IRON_BARS)
		    .unlockedBy("has_brass", has(Items.IRON_BARS))
		    .save(cons, "dcs_climate:build/display_shelf_iron_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISPLAY_SHELF_IRON.get(), 1)
		    .pattern("XZX")
		    .pattern("XZX")
		    .define('X', Tags.Items.INGOTS_IRON)
		    .define('Z', Items.IRON_BARS)
		    .unlockedBy("has_brass", has(Items.IRON_BARS))
		    .save(cons, "dcs_climate:build/display_shelf_iron_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISPLAY_SHELF_LAB.get(), 1)
		    .pattern("XZX")
		    .pattern("XZX")
		    .define('X', TagDC.ItemTag.BUILDING_LINOLEUM)
		    .define('Z', Items.IRON_BARS)
		    .unlockedBy("has_brass", has(Items.IRON_BARS))
		    .save(cons, "dcs_climate:build/display_shelf_lab_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISPLAY_SHELF_GLASS.get(), 1)
		    .pattern("XZX")
		    .pattern("XZX")
		    .define('X', TagDC.ItemTag.BUILDING_LINOLEUM)
		    .define('Z', Tags.Items.GLASS)
		    .unlockedBy("has_brass", has(Tags.Items.GLASS))
		    .save(cons, "dcs_climate:build/display_shelf_glass_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.VILLAGER_CHEST.get(), 1)
		    .pattern("XZX")
		    .pattern(" Y ")
		    .pattern("X X")
		    .define('X', Tags.Items.INGOTS_GOLD)
		    .define('Y', Tags.Items.CHESTS)
		    .define('Z', Tags.Items.GEMS_EMERALD)
		    .unlockedBy("has_emerald", has(Tags.Items.GEMS_EMERALD))
		    .save(cons, "dcs_climate:build/villager_chest_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STRAW_MAT.get(), 1)
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.FEED_HAY)
		    .unlockedBy("has_hay", has(TagDC.ItemTag.FEED_HAY))
		    .save(cons, "dcs_climate:build/straw_mat_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.STRAW_MAT.get(), 1)
		    .pattern("XXX")
		    .define('X', TagDC.ItemTag.FEED_STRAW)
		    .unlockedBy("has_straw", has(TagDC.ItemTag.FEED_STRAW))
		    .save(cons, "dcs_climate:build/straw_mat_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_LINEN.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', TagDC.ItemTag.CLOTH_PLANT)
		    .unlockedBy("has_cloth_plant", has(TagDC.ItemTag.CLOTH_PLANT))
		    .save(cons, "dcs_climate:build/carpet_linen_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_WHITE.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', TagDC.ItemTag.CLOTH_COTTON)
		    .unlockedBy("has_cloth_cotton", has(TagDC.ItemTag.CLOTH_COTTON))
		    .save(cons, "dcs_climate:build/carpet_white_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_WHITE.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Items.WHITE_CARPET)
		    .unlockedBy("has_carpet_white", has(Items.WHITE_CARPET))
		    .save(cons, "dcs_climate:build/carpet_white_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_BLUE.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.CARPET_WHITE.get())
		    .define('Y', Tags.Items.DYES_BLUE)
		    .unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
		    .save(cons, "dcs_climate:build/carpet_blue_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_BLUE.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Items.BLUE_CARPET)
		    .unlockedBy("has_carpet_blue", has(Items.BLUE_CARPET))
		    .save(cons, "dcs_climate:build/carpet_blue_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_BLACK.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.CARPET_WHITE.get())
		    .define('Y', Tags.Items.DYES_BLACK)
		    .unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
		    .save(cons, "dcs_climate:build/carpet_black_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_BLACK.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Items.BLACK_CARPET)
		    .unlockedBy("has_carpet_black", has(Items.BLACK_CARPET))
		    .save(cons, "dcs_climate:build/carpet_black_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_RED.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.CARPET_WHITE.get())
		    .define('Y', Tags.Items.DYES_RED)
		    .unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
		    .save(cons, "dcs_climate:build/carpet_red_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_RED.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Items.RED_CARPET)
		    .unlockedBy("has_carpet_red", has(Items.RED_CARPET))
		    .save(cons, "dcs_climate:build/carpet_red_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_GREEN.get(), 8)
		    .pattern("XXX")
		    .pattern("XYX")
		    .pattern("XXX")
		    .define('X', BuildInit.CARPET_WHITE.get())
		    .define('Y', Tags.Items.DYES_GREEN)
		    .unlockedBy("has_carpet_cotton", has(BuildInit.CARPET_WHITE.get()))
		    .save(cons, "dcs_climate:build/carpet_green_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_GREEN.get(), 1)
		    .pattern("Y")
		    .pattern("X")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Items.GREEN_CARPET)
		    .unlockedBy("has_carpet_green", has(Items.GREEN_CARPET))
		    .save(cons, "dcs_climate:build/carpet_green_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CARPET_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_CARPET)
		    .unlockedBy("has_carpet", has(TagDC.ItemTag.HAC_CARPET))
		    .save(cons, "dcs_climate:clothing/carpet_white_bleaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_WOOD.get(), 1)
		    .pattern("XXX")
		    .pattern("Y Y")
		    .pattern("Y Y")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/table_wood_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/table_round_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_MUD.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_MUD.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_mud", has(CoreInit.STONE_MUD.get()))
		    .save(cons, "dcs_climate:build/table_round_mud_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_GYPSUM.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_GYPSUM.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_gypsum", has(CoreInit.STONE_GYPSUM.get()))
		    .save(cons, "dcs_climate:build/table_round_gypsum_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_SERPENTINE.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_SERPENTINE.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_serpentine", has(CoreInit.STONE_SERPENTINE.get()))
		    .save(cons, "dcs_climate:build/table_round_serpentine_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_GREISEN.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_GREISEN.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_greisen", has(CoreInit.STONE_GREISEN.get()))
		    .save(cons, "dcs_climate:build/table_round_greisen_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_SKARN.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_SKARN.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_skarn", has(CoreInit.STONE_SKARN.get()))
		    .save(cons, "dcs_climate:build/table_round_skarn_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_HORNFELS.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_HORNFELS.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_hornfels", has(CoreInit.STONE_HORNFELS.get()))
		    .save(cons, "dcs_climate:build/table_round_hornfels_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_MARBLE.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_MARBLE.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_marble", has(CoreInit.STONE_MARBLE.get()))
		    .save(cons, "dcs_climate:build/table_round_marble_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_SCHIST_BLUE.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_SCHIST_BLUE.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_schist_blue", has(CoreInit.STONE_SCHIST_BLUE.get()))
		    .save(cons, "dcs_climate:build/table_round_schist_blue_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_NETHER.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_SCHIST_NETHER.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_nether", has(CoreInit.STONE_SCHIST_NETHER.get()))
		    .save(cons, "dcs_climate:build/table_round_nether_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_ROUND_GRANITE.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern(" Y ")
		    .define('X', CoreInit.STONE_GRANITE.get())
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_stone_granite", has(CoreInit.STONE_GRANITE.get()))
		    .save(cons, "dcs_climate:build/table_round_granite_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_WOOD.get(), 1)
		    .pattern("  X")
		    .pattern("XXX")
		    .pattern("Y Y")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/chair_wood_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_WOOD.get(), 1)
		    .pattern("X  ")
		    .pattern("XXX")
		    .pattern("Y Y")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/chair_wood_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_LEATHER.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.LEATHER)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:build/chair_counter_leather_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_LEATHER.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', ItemTags.WOOL)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.LEATHER)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:build/chair_counter_leather_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_LEATHER.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_COUNTER_CHAIR)
		    .unlockedBy("has_chair_counters", has(TagDC.ItemTag.HAC_COUNTER_CHAIR))
		    .save(cons, "dcs_climate:clothing/chair_counter_bleaching_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_WHITE.get(), 1)
		    .requires(Tags.Items.DYES_WHITE)
		    .requires(BuildInit.CHAIR_COUNTER_LEATHER.get())
		    .unlockedBy("has_chair_counter", has(BuildInit.CHAIR_COUNTER_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/chair_counter_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.CHAIR_COUNTER_LEATHER.get())
		    .unlockedBy("has_chair_counter", has(BuildInit.CHAIR_COUNTER_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/chair_counter_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.CHAIR_COUNTER_LEATHER.get())
		    .unlockedBy("has_chair_counter", has(BuildInit.CHAIR_COUNTER_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/chair_counter_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.CHAIR_COUNTER_LEATHER.get())
		    .unlockedBy("has_chair_counter", has(BuildInit.CHAIR_COUNTER_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/chair_counter_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_COUNTER_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.CHAIR_COUNTER_LEATHER.get())
		    .unlockedBy("has_chair_counter", has(BuildInit.CHAIR_COUNTER_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/chair_counter_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_ROUND_WHITE.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.DYES_WHITE)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/chair_round_white_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_ROUND_BLUE.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.DYES_BLUE)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/chair_round_blue_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_ROUND_BLACK.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.DYES_BLACK)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/chair_round_black_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_ROUND_RED.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.DYES_RED)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/chair_round_red_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_ROUND_GREEN.get(), 1)
		    .pattern("Z")
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', Tags.Items.INGOTS_IRON)
		    .define('Z', Tags.Items.DYES_GREEN)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/chair_round_green_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_LINEN.get(), 1)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(BuildInit.TABLE_WOOD.get())
		    .unlockedBy("has_wooden_table", has(BuildInit.TABLE_WOOD.get()))
		    .save(cons, "dcs_climate:clothing/table_linen_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(BuildInit.TABLE_WOOD.get())
		    .unlockedBy("has_wooden_table", has(BuildInit.TABLE_WOOD.get()))
		    .save(cons, "dcs_climate:clothing/table_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.TABLE_WHITE.get())
		    .unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/table_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.TABLE_WHITE.get())
		    .unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/table_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.TABLE_WHITE.get())
		    .unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/table_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.TABLE_WHITE.get())
		    .unlockedBy("has_white_table", has(BuildInit.TABLE_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/table_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.TABLE_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_TABLE)
		    .unlockedBy("has_table", has(TagDC.ItemTag.HAC_TABLE))
		    .save(cons, "dcs_climate:clothing/table_white_bleaching_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_LINEN.get(), 1)
		    .requires(TagDC.ItemTag.CLOTH_PLANT)
		    .requires(BuildInit.CHAIR_WOOD.get())
		    .unlockedBy("has_wooden_chair", has(BuildInit.CHAIR_WOOD.get()))
		    .save(cons, "dcs_climate:clothing/chair_linen_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.CLOTH_COTTON)
		    .requires(BuildInit.CHAIR_WOOD.get())
		    .unlockedBy("has_wooden_chair", has(BuildInit.CHAIR_WOOD.get()))
		    .save(cons, "dcs_climate:clothing/chair_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.CHAIR_WHITE.get())
		    .unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/chair_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.CHAIR_WHITE.get())
		    .unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/chair_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.CHAIR_WHITE.get())
		    .unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/chair_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.CHAIR_WHITE.get())
		    .unlockedBy("has_white_chair", has(BuildInit.CHAIR_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/chair_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CHAIR_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_CHAIR)
		    .unlockedBy("has_chair", has(TagDC.ItemTag.HAC_CHAIR))
		    .save(cons, "dcs_climate:clothing/chair_white_bleaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_WHITE.get(), 1)
		    .pattern("XXX")
		    .pattern("Y Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/sofa_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_ORANGE.get(), 1)
		    .requires(Tags.Items.DYES_YELLOW)
		    .requires(BuildInit.SOFA_WHITE.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/sofa_orange_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.SOFA_WHITE.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/sofa_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.SOFA_WHITE.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/sofa_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_PINK.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.SOFA_WHITE.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/sofa_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.SOFA_WHITE.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/sofa_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_SOFA)
		    .unlockedBy("has_sofa", has(TagDC.ItemTag.HAC_SOFA))
		    .save(cons, "dcs_climate:clothing/sofa_white_bleaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_LEATHER.get(), 1)
		    .pattern("XXX")
		    .pattern("Y Y")
		    .define('X', Tags.Items.LEATHER)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER))
		    .save(cons, "dcs_climate:build/sofa_leather_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_LEATHER_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.SOFA_LEATHER.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/sofa_leather_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_LEATHER_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.SOFA_LEATHER.get())
		    .unlockedBy("has_white_sofa", has(BuildInit.SOFA_LEATHER.get()))
		    .save(cons, "dcs_climate:clothing/sofa_leather_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.SOFA_LEATHER.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_LEATHER_SOFA)
		    .unlockedBy("has_leather_sofa", has(TagDC.ItemTag.HAC_LEATHER_SOFA))
		    .save(cons, "dcs_climate:clothing/sofa_leather_bleaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_WHITE.get(), 1)
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', ItemTags.WOOL)
		    .unlockedBy("has_cloth", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/cushion_white_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_WHITE.get(), 1)
		    .pattern("X")
		    .pattern("Y")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', TagDC.ItemTag.CROP_COTTON)
		    .unlockedBy("has_cloth", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/cushion_white_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.CUSHION_WHITE.get())
		    .unlockedBy("has_white_cushion", has(BuildInit.CUSHION_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/cushion_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.CUSHION_WHITE.get())
		    .unlockedBy("has_white_cushion", has(BuildInit.CUSHION_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/cushion_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.CUSHION_WHITE.get())
		    .unlockedBy("has_white_cushion", has(BuildInit.CUSHION_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/cushion_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.CUSHION_WHITE.get())
		    .unlockedBy("has_white_cushion", has(BuildInit.CUSHION_WHITE.get()))
		    .save(cons, "dcs_climate:clothing/cushion_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CUSHION_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_CUSHION)
		    .unlockedBy("has_cushion", has(TagDC.ItemTag.HAC_CUSHION))
		    .save(cons, "dcs_climate:clothing/cushion_white_bleaching_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_HAMMOCK.get(), 1)
		    .pattern("XXX")
		    .pattern("YYY")
		    .define('X', TagDC.ItemTag.VINE)
		    .define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_vine", has(TagDC.ItemTag.VINE))
		    .save(cons, "dcs_climate:build/bed_hammock_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_LINEN.get(), 1)
		    .pattern("XZX")
		    .pattern("YYY")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .define('Z', ItemTags.WOOL)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/bed_linen_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_LINEN.get(), 1)
		    .pattern("XZX")
		    .pattern("YYY")
		    .define('X', TagDC.ItemTag.CLOTHS)
		    .define('Y', ItemTags.WOODEN_FENCES)
		    .define('Z', TagDC.ItemTag.CROP_COTTON)
		    .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
		    .save(cons, "dcs_climate:build/bed_linen_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_WHITE)
		    .requires(TagDC.ItemTag.HAC_BED)
		    .unlockedBy("has_bed", has(TagDC.ItemTag.HAC_BED))
		    .save(cons, "dcs_climate:clothing/bed_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_BLUE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLUE)
		    .requires(TagDC.ItemTag.HAC_BED)
		    .unlockedBy("has_bed", has(TagDC.ItemTag.HAC_BED))
		    .save(cons, "dcs_climate:clothing/bed_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_BLACK.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLACK)
		    .requires(TagDC.ItemTag.HAC_BED)
		    .unlockedBy("has_bed", has(TagDC.ItemTag.HAC_BED))
		    .save(cons, "dcs_climate:clothing/bed_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_RED.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_RED)
		    .requires(TagDC.ItemTag.HAC_BED)
		    .unlockedBy("has_bed", has(TagDC.ItemTag.HAC_BED))
		    .save(cons, "dcs_climate:clothing/bed_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.BED_GREEN.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_GREEN)
		    .requires(TagDC.ItemTag.HAC_BED)
		    .unlockedBy("has_bed", has(TagDC.ItemTag.HAC_BED))
		    .save(cons, "dcs_climate:clothing/bed_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.CABINET_NORMAL.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern("XXX")
		    .define('X', ItemTags.PLANKS)
		    .define('Y', Tags.Items.CHESTS_WOODEN)
		    .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
		    .save(cons, "dcs_climate:build/cabinet_normal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CABINET_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_WHITE)
		    .requires(BuildInit.CABINET_NORMAL.get())
		    .unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/cabinet_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CABINET_BLUE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLUE)
		    .requires(BuildInit.CABINET_NORMAL.get())
		    .unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/cabinet_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CABINET_BLACK.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLACK)
		    .requires(BuildInit.CABINET_NORMAL.get())
		    .unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/cabinet_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CABINET_RED.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_RED)
		    .requires(BuildInit.CABINET_NORMAL.get())
		    .unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/cabinet_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.CABINET_GREEN.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_GREEN)
		    .requires(BuildInit.CABINET_NORMAL.get())
		    .unlockedBy("has_cabinet", has(BuildInit.CABINET_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/cabinet_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LUGGAGE_NORMAL.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern("XXX")
		    .define('X', Tags.Items.LEATHER)
		    .define('Y', Tags.Items.CHESTS_WOODEN)
		    .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
		    .save(cons, "dcs_climate:build/luggage_normal_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LUGGAGE_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_WHITE)
		    .requires(BuildInit.LUGGAGE_NORMAL.get())
		    .unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/luggage_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LUGGAGE_BLUE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLUE)
		    .requires(BuildInit.LUGGAGE_NORMAL.get())
		    .unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/luggage_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LUGGAGE_BLACK.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLACK)
		    .requires(BuildInit.LUGGAGE_NORMAL.get())
		    .unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/luggage_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LUGGAGE_RED.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_RED)
		    .requires(BuildInit.LUGGAGE_NORMAL.get())
		    .unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/luggage_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LUGGAGE_GREEN.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_GREEN)
		    .requires(BuildInit.LUGGAGE_NORMAL.get())
		    .unlockedBy("has_luggage", has(BuildInit.LUGGAGE_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/luggage_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_NORMAL.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern("XXX")
		    .define('X', Tags.Items.INGOTS_IRON)
		    .define('Y', Tags.Items.CHESTS_WOODEN)
		    .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
		    .save(cons, "dcs_climate:build/locker_normal_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_NORMAL.get(), 1)
		    .pattern("XXX")
		    .pattern(" Y ")
		    .pattern("XXX")
		    .define('X', TagUtil.BRONZE_OR_BRASS)
		    .define('Y', Tags.Items.CHESTS_WOODEN)
		    .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
		    .save(cons, "dcs_climate:build/locker_normal_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_WHITE)
		    .requires(BuildInit.LOCKER_NORMAL.get())
		    .unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/locker_white_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_BLUE.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLUE)
		    .requires(BuildInit.LOCKER_NORMAL.get())
		    .unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/locker_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_BLACK.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_BLACK)
		    .requires(BuildInit.LOCKER_NORMAL.get())
		    .unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/locker_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_RED.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_RED)
		    .requires(BuildInit.LOCKER_NORMAL.get())
		    .unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/locker_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.LOCKER_GREEN.get(), 1)
		    .requires(TagDC.ItemTag.EXTRACT_GREEN)
		    .requires(BuildInit.LOCKER_NORMAL.get())
		    .unlockedBy("has_locker", has(BuildInit.LOCKER_NORMAL.get()))
		    .save(cons, "dcs_climate:clothing/locker_green_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISHPLATE_WHITE.get(), 1)
		    .pattern("X X")
		    .pattern(" X ")
		    .define('X', TagDC.ItemTag.GEM_AGATES)
		    .unlockedBy("has_agates", has(TagDC.ItemTag.GEM_AGATES))
		    .save(cons, "dcs_climate:build/dishplate_white_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISHPLATE_SILVER.get(), 1)
		    .pattern("X X")
		    .pattern(" X ")
		    .define('X', TagDC.ItemTag.INGOT_SILVER)
		    .unlockedBy("has_silver", has(TagDC.ItemTag.INGOT_SILVER))
		    .save(cons, "dcs_climate:build/dishplate_silver_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.DISHPLATE_SILVER.get(), 1)
		    .pattern("X X")
		    .pattern(" X ")
		    .define('X', TagDC.ItemTag.INGOT_NICKEL_SILVER)
		    .unlockedBy("has_silver", has(TagDC.ItemTag.INGOT_NICKEL_SILVER))
		    .save(cons, "dcs_climate:build/dishplate_silver_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLOWERPOT_WHITE.get(), 1)
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', TagDC.ItemTag.GEM_AGATES)
		    .define('Y', ItemTags.FLOWERS)
		    .unlockedBy("has_agates", has(TagDC.ItemTag.GEM_AGATES))
		    .save(cons, "dcs_climate:build/flowerpot_white_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.FLOWERPOT_CLAY.get(), 1)
		    .pattern("XYX")
		    .pattern(" X ")
		    .define('X', Tags.Items.INGOTS_BRICK)
		    .define('Y', ItemTags.FLOWERS)
		    .unlockedBy("has_bricks", has(Tags.Items.INGOTS_BRICK))
		    .save(cons, "dcs_climate:build/flowerpot_clay_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_WHITE.get(), 1)
		    .pattern("XXX")
		    .pattern("YYY")
		    .define('X', Tags.Items.RODS_WOODEN)
		    .define('Y', TagDC.ItemTag.CLOTHS)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:build/kitchen_curtain_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_YELLOW.get(), 1)
		    .requires(Tags.Items.DYES_YELLOW)
		    .requires(BuildInit.KITCHEN_CURTAIN_WHITE.get())
		    .unlockedBy("has_kitchen_curtain", has(BuildInit.KITCHEN_CURTAIN_WHITE.get()))
		    .save(cons, "dcs_climate:build/kitchen_curtain_yellow_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_BLUE.get(), 1)
		    .requires(Tags.Items.DYES_BLUE)
		    .requires(BuildInit.KITCHEN_CURTAIN_WHITE.get())
		    .unlockedBy("has_kitchen_curtain", has(BuildInit.KITCHEN_CURTAIN_WHITE.get()))
		    .save(cons, "dcs_climate:build/kitchen_curtain_blue_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_BLACK.get(), 1)
		    .requires(Tags.Items.DYES_BLACK)
		    .requires(BuildInit.KITCHEN_CURTAIN_WHITE.get())
		    .unlockedBy("has_kitchen_curtain", has(BuildInit.KITCHEN_CURTAIN_WHITE.get()))
		    .save(cons, "dcs_climate:build/kitchen_curtain_black_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_RED.get(), 1)
		    .requires(Tags.Items.DYES_RED)
		    .requires(BuildInit.KITCHEN_CURTAIN_WHITE.get())
		    .unlockedBy("has_kitchen_curtain", has(BuildInit.KITCHEN_CURTAIN_WHITE.get()))
		    .save(cons, "dcs_climate:build/kitchen_curtain_red_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_GREEN.get(), 1)
		    .requires(Tags.Items.DYES_GREEN)
		    .requires(BuildInit.KITCHEN_CURTAIN_WHITE.get())
		    .unlockedBy("has_kitchen_curtain", has(BuildInit.KITCHEN_CURTAIN_WHITE.get()))
		    .save(cons, "dcs_climate:build/kitchen_curtain_green_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BuildInit.KITCHEN_CURTAIN_WHITE.get(), 1)
		    .requires(TagDC.ItemTag.SOAP_OIL)
		    .requires(TagDC.ItemTag.HAC_CURTAIN)
		    .unlockedBy("has_curtains", has(TagDC.ItemTag.HAC_CURTAIN))
		    .save(cons, "dcs_climate:build/kitchen_curtain_bleaching_0");

	}

}
