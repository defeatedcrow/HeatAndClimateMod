package defeatedcrow.hac.core.recipe.vanilla;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.recipe.mill.MillsDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class VanillaRecipeProvider extends RecipeProvider {

	public VanillaRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> cons) {

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

		otherRecipes(cons);

		smeltingRecipes(cons);

	}

	private static void mortarMetalRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Color color) {

		if (color.block().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.block().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.orePri().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).group("crusher_mortar").unlockedBy("has_" + color.name() + "_ore", has(color.block().get().asItem()))
			    .save(cons, "dcs_climate:core/mortar_ore_" + color.name());

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.gemPri().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).requires(CoreInit.SIEVE.get()).group("crusher_mortar")
			    .unlockedBy("has_" + color.name() + "_ore", has(color.block().get().asItem())).save(cons, "dcs_climate:core/sieve_ore_" + color.name());
		}

		if (color.blockDeep().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.blockDeep().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.oreSec().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).group("crusher_mortar")
			    .unlockedBy("has_" + color.name() + "_deepore", has(color.blockDeep().get().asItem())).save(cons, "dcs_climate:core/mortar_deepore_" + color.name());

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.gemSec().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).requires(CoreInit.SIEVE.get()).group("crusher_mortar")
			    .unlockedBy("has_" + color.name() + "_deepore", has(color.blockDeep().get().asItem())).save(cons, "dcs_climate:core/sieve_deepore_" + color.name());
		}

		if (color.orePri().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.orePri().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.dustPri().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).group("crusher_mortar").unlockedBy("has_" + color.name() + "_raw_1", has(ore))
			    .save(cons, "dcs_climate:core/mortar_gem1_" + color.name());
		}

		if (color.oreSec().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreSec().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.dustSec().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).group("crusher_mortar").unlockedBy("has_" + color.name() + "_raw_2", has(ore))
			    .save(cons, "dcs_climate:core/mortar_gem2_" + color.name());
		}

		if (color.oreTert().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) color.oreTert().get()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, color.dustTert().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).group("crusher_mortar").unlockedBy("has_" + color.name() + "_raw_3", has(ore))
			    .save(cons, "dcs_climate:core/mortar_gem3_" + color.name());
		}

	}

	private static void mortarGemRecipes(Consumer<FinishedRecipe> cons, MaterialRecipes.Gem gem) {
		if (gem.ore().get().asItem() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) gem.ore().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, gem.gem().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).requires(CoreInit.SIEVE.get()).group("crusher_mortar").unlockedBy("has_" + gem.name() + "_ore", has(ore))
			    .save(cons, "dcs_climate:core/sieve_ore_" + gem.name());
		}
	}

	private static void mortarOtherRecipes(Consumer<FinishedRecipe> cons) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.QUARTZ, 1).requires(CoreInit.STONE_QUARTZ.get()).requires(CoreInit.MORTAR.get()).requires(CoreInit.SIEVE.get()).group("crusher_mortar")
		    .unlockedBy("has_stone_quartz", has(CoreInit.STONE_QUARTZ.get())).save(cons, "dcs_climate:core/sieve_stone_quartz");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CoreInit.DUST_CRYSTAL.get(), 1).requires(CoreInit.STONE_QUARTZ.get()).requires(CoreInit.MORTAR.get()).group("crusher_mortar")
		    .unlockedBy("has_stone_quartz", has(CoreInit.STONE_QUARTZ.get())).save(cons, "dcs_climate:core/mortar_stone_quartz");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.QUARTZ, 4).requires(Blocks.QUARTZ_BLOCK).requires(CoreInit.MORTAR.get()).requires(CoreInit.SIEVE.get()).group("crusher_mortar")
		    .unlockedBy("has_block_quartz", has(Blocks.QUARTZ_BLOCK)).save(cons, "dcs_climate:core/sieve_block_quartz");

	}

	private static void mortarMillsRecipes(Consumer<FinishedRecipe> cons, MillsDC.Crops mill) {
		if (mill.input().get() instanceof IItemDC) {
			TagKey<Item> ore = ((IItemDC) mill.input().get().asItem()).getPairTag();

			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, mill.outputPri().get(), 1).requires(ore).requires(CoreInit.MORTAR.get()).group("crusher_mortar").unlockedBy("has_crop_" + mill.name(), has(ore))
			    .save(cons, "dcs_climate:food/mortar_crop_" + mill.name());
		}
	}

	private static void mortarMiscRecipes(Consumer<FinishedRecipe> cons, MillsDC.Miscs mill) {
		if (mill.input().get() != null) {
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, mill.outputPri().get(), mill.outputCount()).requires(mill.input().get()).requires(CoreInit.MORTAR.get()).group("crusher_mortar")
			    .unlockedBy("has_" + mill.name(), has(mill.input().get())).save(cons, "dcs_climate:core/mortar_" + mill.name());
		}
	}

	private static void sieveRecipes(Consumer<FinishedRecipe> cons, MillsDC.Sieve mill) {
		if (mill.input().get() != null) {
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, mill.outputPri().get(), mill.outputCount()).requires(mill.input().get()).requires(CoreInit.MORTAR.get()).requires(CoreInit.SIEVE.get()).group("crusher_mortar")
			    .unlockedBy("has_" + mill.name(), has(mill.input().get())).save(cons, "dcs_climate:core/sieve_" + mill.name());
		}
	}

	private static void otherRecipes(Consumer<FinishedRecipe> cons) {

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CoreInit.DUSTBLOCK_ALUMINUM.get()).pattern("PPP").pattern("PPP").pattern("PPT").define('P', TagDC.ItemTag.DUST_ALUMINUM).define('T', TagDC.ItemTag.DUST_TRONA)
		    .unlockedBy("has_dust_aluminum", has(TagDC.ItemTag.DUST_ALUMINUM)).group("dustblock_pack").save(cons, "dcs_climate:core/dustblock_aluminum_2");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.IRON_INGOT, 9).requires(CoreInit.METAL_STEEL_FAIL.get()).group("storage_unpack").unlockedBy("has_brittle_steel_block", has(CoreInit.METAL_STEEL_FAIL.get()))
		    .save(cons, "dcs_climate:core/ingot_iron_from_brittle_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.MORTAR.get(), 1).pattern("X X").pattern("XXX").define('X', TagDC.ItemTag.GEM_AGATES).unlockedBy("has_chalcedony", has(TagDC.ItemTag.GEM_CHALCEDONY))
		    .save(cons, "dcs_climate:core/agate_mortar");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SIEVE.get(), 1).pattern("XYX").pattern(" X ").define('X', Tags.Items.INGOTS_IRON).define('Y', Items.IRON_BARS).unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON))
		    .save(cons, "dcs_climate:core/gem_sieve");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HAND_SPINDLE.get(), 1).pattern("Y").pattern("X").pattern("Y").define('X', ItemTags.PLANKS).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN)).save(cons, "dcs_climate:core/hand_spindle");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SEEDING_POT.get(), 1).pattern("XYX").pattern("XXX").define('X', Items.PAPER).define('Y', ItemTags.DIRT).unlockedBy("has_dirt", has(ItemTags.DIRT))
		    .save(cons, "dcs_climate:core/seeding_pot");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HANDY_BELLOW.get(), 1).pattern("XY").pattern("YZ").define('X', Tags.Items.INGOTS_IRON).define('Y', ItemTags.PLANKS).define('Z', Tags.Items.LEATHER)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER)).save(cons, "dcs_climate:core/bellow_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HANDY_BELLOW.get(), 1).pattern("XY").pattern("YZ").define('X', TagUtil.BRONZE_OR_BRASS).define('Y', ItemTags.PLANKS).define('Z', Tags.Items.LEATHER)
		    .unlockedBy("has_leather", has(Tags.Items.LEATHER)).save(cons, "dcs_climate:core/bellow_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SCREWDRIVER.get(), 1).pattern("X").pattern("Y").pattern("Z").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', ItemTags.PLANKS).define('Z', Tags.Items.LEATHER)
		    .unlockedBy("has_ingot_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/screwdriver_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.ALTIMETER.get(), 1).pattern("X").pattern("Y").pattern("Z").define('X', Tags.Items.GLASS).define('Y', TagDC.ItemTag.CRAFT_DRIVER).define('Z', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_screwdriver", has(TagDC.ItemTag.CRAFT_DRIVER)).save(cons, "dcs_climate:core/altimeter_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.TEMPMETER.get(), 1).pattern("X").pattern("Y").pattern("Z").define('X', Tags.Items.GLASS).define('Y', TagDC.ItemTag.CRAFT_DRIVER).define('Z', Tags.Items.INGOTS_COPPER)
		    .unlockedBy("has_screwdriver", has(TagDC.ItemTag.CRAFT_DRIVER)).save(cons, "dcs_climate:core/tempmeter_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.FLOWMETER.get(), 1).pattern("X").pattern("Y").pattern("Z").define('X', Tags.Items.GLASS).define('Y', TagDC.ItemTag.CRAFT_DRIVER).define('Z', Tags.Items.INGOTS_GOLD)
		    .unlockedBy("has_screwdriver", has(TagDC.ItemTag.CRAFT_DRIVER)).save(cons, "dcs_climate:core/flowmeter_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.ENERGYMETER.get(), 1).pattern("X").pattern("Y").pattern("Z").define('X', Tags.Items.GLASS).define('Y', TagDC.ItemTag.CRAFT_DRIVER).define('Z', Tags.Items.DUSTS_REDSTONE)
		    .unlockedBy("has_screwdriver", has(TagDC.ItemTag.CRAFT_DRIVER)).save(cons, "dcs_climate:core/energymeter_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.CHOPSTICKS.get(), 1).pattern("XX").define('X', Tags.Items.RODS_WOODEN).unlockedBy("has_rod_wooden", has(Tags.Items.RODS_WOODEN)).save(cons, "dcs_climate:core/chopsticks_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.FORK.get(), 1).pattern("YYY").pattern(" Y ").pattern(" X ").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.INGOT_SILVER)
		    .unlockedBy("has_ingot_silver", has(TagDC.ItemTag.INGOT_SILVER)).save(cons, "dcs_climate:core/fork_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SPOON.get(), 1).pattern("Y").pattern("Y").pattern("X").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.INGOT_SILVER)
		    .unlockedBy("has_ingot_silver", has(TagDC.ItemTag.INGOT_SILVER)).save(cons, "dcs_climate:core/spoon_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.FORK.get(), 1).pattern("YYY").pattern(" Y ").pattern(" X ").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.INGOT_NICKEL_SILVER)
		    .unlockedBy("has_ingot_nickelsilver", has(TagDC.ItemTag.INGOT_NICKEL_SILVER)).save(cons, "dcs_climate:core/fork_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SPOON.get(), 1).pattern("Y").pattern("Y").pattern("X").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.INGOT_NICKEL_SILVER)
		    .unlockedBy("has_ingot_nickelsilver", has(TagDC.ItemTag.INGOT_NICKEL_SILVER)).save(cons, "dcs_climate:core/spoon_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.EMPTY_COIL_CASE.get(), 1).pattern("XXY").pattern("XX ").define('X', Tags.Items.INGOTS_IRON).define('Y', Items.CHAIN).unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON))
		    .save(cons, "dcs_climate:core/insence_case_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CoreInit.COIL_CASE.get(), 1).requires(CoreInit.EMPTY_COIL_CASE.get()).requires(CoreInit.MOSQUITO_COIL.get()).unlockedBy("has_mosquito_coil", has(CoreInit.MOSQUITO_COIL.get()))
		    .save(cons, "dcs_climate:core/mosquito_coil_case_1");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CoreInit.MOSQUITO_COIL.get(), 1).requires(TagDC.ItemTag.WATER).requires(TagDC.ItemTag.DUST_WOOD).requires(TagDC.ItemTag.CAMPHOR).requires(TagDC.ItemTag.CROP_PYRETHRUM)
		    .unlockedBy("has_crop_pyrethrum", has(TagDC.ItemTag.CROP_PYRETHRUM)).save(cons, "dcs_climate:core/mosquito_coil_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.AXE_BRASS.get(), 1).pattern("YYX").pattern(" XX").define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)).save(cons, "dcs_climate:core/axeitem_brass");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.PICKAXE_BRASS.get(), 1).pattern("  X").pattern("YYX").pattern("  X").define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)).save(cons, "dcs_climate:core/pickaxeitem_brass");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SHOVEL_BRASS.get(), 1).pattern("YYX").define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)).save(cons, "dcs_climate:core/shovelitem_brass");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HOE_BRASS.get(), 1).pattern("YYX").pattern("  X").define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)).save(cons, "dcs_climate:core/hoeitem_brass");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SCYTHE_BRASS.get(), 1).pattern("YYX").pattern("  X").pattern(" X ").define('X', TagDC.ItemTag.INGOT_BRASS_OR_BRONZE).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_brass", has(TagDC.ItemTag.INGOT_BRASS_OR_BRONZE)).save(cons, "dcs_climate:core/scytheitem_brass");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.AXE_STEEL.get(), 1).pattern("YYX").pattern(" XX").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/axeitem_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.PICKAXE_STEEL.get(), 1).pattern("  X").pattern("YYX").pattern("  X").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/pickaxeitem_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SHOVEL_STEEL.get(), 1).pattern("YYX").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', Tags.Items.RODS_WOODEN).unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL))
		    .save(cons, "dcs_climate:core/shovelitem_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HOE_STEEL.get(), 1).pattern("YYX").pattern("  X").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/hoeitem_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SCYTHE_STEEL.get(), 1).pattern("YYX").pattern("  X").pattern(" X ").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/scytheitem_steel");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.AXE_COBALT.get(), 1).pattern("YYX").pattern(" XX").define('X', TagDC.ItemTag.INGOT_COBALT).define('Y', TagDC.ItemTag.INGOT_TITANIUM)
		    .unlockedBy("has_cobalt", has(TagDC.ItemTag.INGOT_COBALT)).save(cons, "dcs_climate:core/axeitem_cobalt");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.PICKAXE_COBALT.get(), 1).pattern("  X").pattern("YYX").pattern("  X").define('X', TagDC.ItemTag.INGOT_COBALT).define('Y', TagDC.ItemTag.INGOT_TITANIUM)
		    .unlockedBy("has_cobalt", has(TagDC.ItemTag.INGOT_COBALT)).save(cons, "dcs_climate:core/pickaxeitem_cobalt");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SHOVEL_COBALT.get(), 1).pattern("YYX").define('X', TagDC.ItemTag.INGOT_COBALT).define('Y', TagDC.ItemTag.INGOT_TITANIUM)
		    .unlockedBy("has_cobalt", has(TagDC.ItemTag.INGOT_COBALT)).save(cons, "dcs_climate:core/shovelitem_cobalt");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HOE_COBALT.get(), 1).pattern("YYX").pattern("  X").define('X', TagDC.ItemTag.INGOT_COBALT).define('Y', TagDC.ItemTag.INGOT_TITANIUM)
		    .unlockedBy("has_cobalt", has(TagDC.ItemTag.INGOT_COBALT)).save(cons, "dcs_climate:core/hoeitem_cobalt");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SCYTHE_COBALT.get(), 1).pattern("YYX").pattern("  X").pattern(" X ").define('X', TagDC.ItemTag.INGOT_COBALT).define('Y', TagDC.ItemTag.INGOT_TITANIUM)
		    .unlockedBy("has_cobalt", has(TagDC.ItemTag.INGOT_COBALT)).save(cons, "dcs_climate:core/scytheitem_cobalt");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SCISSORS_COBALT.get(), 1).pattern("X ").pattern(" X").define('X', TagDC.ItemTag.INGOT_COBALT).unlockedBy("has_cobalt", has(TagDC.ItemTag.INGOT_COBALT))
		    .save(cons, "dcs_climate:core/scissorsitem_cobalt");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.SCYTHE_FLINT.get(), 1).pattern("YYX").pattern("  X").pattern(" X ").define('X', TagDC.ItemTag.GEM_FLINT).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_flint", has(TagDC.ItemTag.GEM_FLINT)).save(cons, "dcs_climate:core/scytheitem_flint");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HARPOON_FLINT.get(), 1).pattern("  Y").pattern(" X ").pattern("XZ ").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.GEM_FLINT).define('Z', Tags.Items.STRING)
		    .unlockedBy("has_flint", has(TagDC.ItemTag.GEM_FLINT)).save(cons, "dcs_climate:core/harpoon_flint_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HARPOON_STEEL.get(), 1).pattern("  Y").pattern(" X ").pattern("XZ ").define('X', Tags.Items.INGOTS_IRON).define('Y', TagDC.ItemTag.INGOT_STEEL).define('Z', Tags.Items.STRING)
		    .unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/harpoon_steel_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HARPOON_FLINT.get(), 1).pattern("  Y").pattern(" X ").pattern("XZ ").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.GEM_FLINT).define('Z', TagDC.ItemTag.VINE)
		    .unlockedBy("has_flint", has(TagDC.ItemTag.GEM_FLINT)).save(cons, "dcs_climate:core/harpoon_flint_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.HARPOON_STEEL.get(), 1).pattern("  Y").pattern(" X ").pattern("XZ ").define('X', Tags.Items.INGOTS_IRON).define('Y', TagDC.ItemTag.INGOT_STEEL)
		    .define('Z', TagDC.ItemTag.VINE).unlockedBy("has_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/harpoon_steel_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.FISHING_ROD_STEEL.get(), 1).pattern("  X").pattern(" XY").pattern("X Y").define('X', TagDC.ItemTag.INGOT_STEEL).define('Y', Tags.Items.STRING)
		    .unlockedBy("has_ingot_steel", has(TagDC.ItemTag.INGOT_STEEL)).save(cons, "dcs_climate:core/fishing_rod_steel_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.LURE_FISHING_HOOK.get(), 1).pattern("  X").pattern("X X").pattern("XYX").define('X', Tags.Items.NUGGETS_IRON).define('Y', Tags.Items.INGOTS_IRON)
		    .unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON)).save(cons, "dcs_climate:core/lure_fishing_hook_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.LURE_JIG_IRON.get(), 1).pattern("Y  ").pattern(" XX").pattern(" XX").define('X', Tags.Items.INGOTS_IRON).define('Y', CoreInit.LURE_FISHING_HOOK.get())
		    .unlockedBy("has_fishing_hook", has(CoreInit.LURE_FISHING_HOOK.get())).save(cons, "dcs_climate:core/lure_metal_jig_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.LURE_JIG_GLITTER.get(), 1).pattern("Y  ").pattern(" XZ").pattern(" ZX").define('X', Tags.Items.INGOTS_IRON).define('Y', CoreInit.LURE_FISHING_HOOK.get())
		    .define('Z', Tags.Items.DUSTS_GLOWSTONE).unlockedBy("has_fishing_hook", has(CoreInit.LURE_FISHING_HOOK.get())).save(cons, "dcs_climate:core/lure_glitter_jig_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.LURE_EGI_FIRE.get(), 1).pattern("Y  ").pattern(" XZ").pattern(" ZX").define('X', ItemTags.PLANKS).define('Y', CoreInit.LURE_FISHING_HOOK.get())
		    .define('Z', Items.BLAZE_POWDER).unlockedBy("has_fishing_hook", has(CoreInit.LURE_FISHING_HOOK.get())).save(cons, "dcs_climate:core/lure_fire_egi_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.LURE_WORM_CLAW.get(), 1).pattern("Y  ").pattern(" XZ").pattern(" ZX").define('X', TagDC.ItemTag.CLOTH_RUBBER).define('Y', CoreInit.LURE_FISHING_HOOK.get())
		    .define('Z', Tags.Items.DUSTS_REDSTONE).unlockedBy("has_fishing_hook", has(CoreInit.LURE_FISHING_HOOK.get())).save(cons, "dcs_climate:core/lure_craw_worm_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.LURE_MAGNET.get(), 1).pattern("Y  ").pattern(" XZ").pattern(" ZX").define('X', TagDC.ItemTag.INGOT_MAGNET).define('Y', CoreInit.LURE_FISHING_HOOK.get())
		    .define('Z', Tags.Items.INGOTS_IRON).unlockedBy("has_fishing_hook", has(CoreInit.LURE_FISHING_HOOK.get())).save(cons, "dcs_climate:core/lure_magnet_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.CANOE_ITEM.get(), 1).pattern(" Y ").pattern("X X").pattern("XXX").define('X', FoodInit.PLANK_EU_KUKUI.get()).define('Y', ItemTags.LOGS_THAT_BURN)
		    .unlockedBy("has_kukui_planks", has(FoodInit.PLANK_EU_KUKUI.get())).save(cons, "dcs_climate:core/kukui_canoe");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CoreInit.CALABASH_BUCKET.get(), 1).requires(TagDC.ItemTag.CROP_CALABASH).requires(TagDC.ItemTag.SAP_LACQUER).requires(Tags.Items.STRING)
		    .unlockedBy("has_crop_calabash", has(TagDC.ItemTag.CROP_CALABASH)).save(cons, "dcs_climate:core/bucket_calabash_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CoreInit.DUSTBLOCK_RUBBER.get()).pattern("XXX").pattern("XYX").pattern("XXX").define('X', TagDC.ItemTag.SAP_LATEX).define('Y', TagDC.ItemTag.DUST_SULFUR)
		    .unlockedBy("has_sap_latex", has(TagDC.ItemTag.SAP_LATEX)).save(cons, "dcs_climate:core/dustblock_rubber_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CoreInit.BLOCK_RUBBER.get()).pattern("XXX").pattern("XXX").pattern("XXX").define('X', TagDC.ItemTag.CLOTH_RUBBER).group("storage_pack")
		    .unlockedBy("has_cloth_rubber", has(TagDC.ItemTag.CLOTH_RUBBER)).save(cons, "dcs_climate:core/block_rubber_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CoreInit.CLOTH_RUBBER.get(), 9).requires(TagDC.ItemTag.BLOCK_RUBBER).group("storage_unpack").unlockedBy("has_block_rubber", has(TagDC.ItemTag.BLOCK_RUBBER))
		    .save(cons, "dcs_climate:core/cloth_rubber_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.CASTING_QUARTZ_RAW.get()).pattern("XYX").define('X', TagDC.ItemTag.ORES_GYPSUM).define('Y', TagDC.ItemTag.DUST_CRYSTAL)
		    .unlockedBy("has_dust_crystal", has(TagDC.ItemTag.DUST_CRYSTAL)).save(cons, "dcs_climate:core/casting_quartz_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, Items.QUARTZ, 1).requires(CoreInit.CASTING_QUARTZ.get()).unlockedBy("has_casting_quartz", has(CoreInit.CASTING_QUARTZ.get()))
		    .save(cons, "dcs_climate:core/casting_quartz_open_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, CoreInit.CASTING_TOURMARINE_RAW.get()).pattern("XYX").pattern("WZV").pattern("XYX").define('V', TagDC.ItemTag.DUST_BORAX).define('W', TagDC.ItemTag.DUST_CRYSTAL)
		    .define('X', TagDC.ItemTag.ORES_GYPSUM).define('Y', TagDC.ItemTag.DUST_ALUMINA).define('Z', TagDC.ItemTag.DUST_LITHIUM).unlockedBy("has_dust_lithium", has(TagDC.ItemTag.DUST_LITHIUM))
		    .save(cons, "dcs_climate:core/casting_tourmarine_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CoreInit.GEM_TOURMALINE.get(), 1).requires(CoreInit.CASTING_TOURMARINE.get()).unlockedBy("has_casting_tourmarine", has(CoreInit.CASTING_TOURMARINE.get()))
		    .save(cons, "dcs_climate:core/casting_tourmarine_open_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CoreInit.DUST_BAKING_SODA.get(), 1).requires(TagDC.ItemTag.DUST_TRONA).requires(TagDC.ItemTag.DUST_LIME).unlockedBy("has_dust_trona", has(TagDC.ItemTag.DUST_TRONA))
		    .save(cons, "dcs_climate:core/dust_baking_soda_0");

		// vanilla another

		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.OAK_BOAT, 1).pattern("X X").pattern("XXX").define('X', ItemTags.PLANKS).unlockedBy("has_planks", has(ItemTags.PLANKS)).save(cons, "dcs_climate:core/boat_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PAINTING, 1).pattern("YYY").pattern("YXY").pattern("YYY").define('X', TagDC.ItemTag.CLOTHS).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS)).save(cons, "dcs_climate:core/painting_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.WHITE_BED, 1).pattern("XXX").pattern("YYY").define('X', TagDC.ItemTag.CLOTHS).define('Y', ItemTags.PLANKS).unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:core/bed_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.YELLOW_BED, 1).pattern("XXX").pattern("YYY").define('X', BuildInit.STRAW_MAT.get()).define('Y', ItemTags.PLANKS)
		    .unlockedBy("has_straw_mat", has(BuildInit.STRAW_MAT.get())).save(cons, "dcs_climate:core/bed_another_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.WHITE_BANNER, 1).pattern("XXX").pattern("XXX").pattern(" Y ").define('X', TagDC.ItemTag.CLOTHS).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS)).save(cons, "dcs_climate:core/banner_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.LOOM, 1).pattern("XX").pattern("YY").define('X', TagDC.ItemTag.CLOTHS).define('Y', ItemTags.PLANKS).unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:core/loom_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SCAFFOLDING, 6).pattern("XYX").pattern("X X").pattern("X X").define('X', TagDC.ItemTag.STICK_SORGHUM).define('Y', Tags.Items.STRING)
		    .unlockedBy("has_rod_sorghum", has(TagDC.ItemTag.STICK_SORGHUM)).save(cons, "dcs_climate:core/scaffolding_another_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SCAFFOLDING, 4).pattern("XYX").pattern("X X").pattern("X X").define('X', Tags.Items.RODS_WOODEN).define('Y', TagDC.ItemTag.VINE)
		    .unlockedBy("has_vine", has(TagDC.ItemTag.VINE)).save(cons, "dcs_climate:core/scaffolding_another_2");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.BOW, 1).pattern(" XY").pattern("X Y").pattern(" XY").define('X', Tags.Items.RODS_WOODEN).define('Y', Tags.Items.STRING).unlockedBy("has_strings", has(Tags.Items.STRING))
		    .save(cons, "dcs_climate:core/bow_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.BOOK, 1).pattern("YY").pattern("YX").define('X', TagDC.ItemTag.CLOTHS).define('Y', Items.PAPER).unlockedBy("has_clothes", has(TagDC.ItemTag.CLOTHS))
		    .save(cons, "dcs_climate:core/book_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.SPYGLASS, 1).pattern("Y").pattern("X").pattern("X").define('X', Tags.Items.INGOTS_COPPER).define('Y', TagDC.ItemTag.GEM_FLUORITE)
		    .unlockedBy("has_fluorite", has(TagDC.ItemTag.GEM_FLUORITE)).save(cons, "dcs_climate:core/spyglass_another_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1).requires(TagDC.ItemTag.DUST_NITER).requires(TagDC.ItemTag.DUST_NITER).requires(TagDC.ItemTag.DUST_COAL).requires(TagDC.ItemTag.DUST_SULFUR)
		    .unlockedBy("has_dust_niter", has(TagDC.ItemTag.DUST_NITER)).save(cons, "dcs_climate:core/gunpowder_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BUCKET, 1).pattern("X X").pattern(" X ").define('X', TagDC.ItemTag.INGOT_ALUMINUM).unlockedBy("has_aluminum", has(TagDC.ItemTag.INGOT_ALUMINUM))
		    .save(cons, "dcs_climate:core/bucket_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.FLOWER_POT, 1).pattern("X X").pattern(" X ").define('X', Tags.Items.INGOTS_BRICK).unlockedBy("has_ingot_brick", has(Tags.Items.INGOTS_BRICK))
		    .save(cons, "dcs_climate:core/flower_pot_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TORCH, 4).pattern("X").pattern("Y").define('X', FoodInit.BIOMASS_BRIQUET.get()).define('Y', Tags.Items.RODS_WOODEN)
		    .unlockedBy("has_briquet", has(FoodInit.BIOMASS_BRIQUET.get())).save(cons, "dcs_climate:core/torch_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CAMPFIRE, 1).pattern(" Y ").pattern("YXY").pattern("ZZZ").define('X', FoodInit.BIOMASS_BRIQUET.get()).define('Y', Tags.Items.RODS_WOODEN)
		    .define('Z', ItemTags.LOGS_THAT_BURN).unlockedBy("has_briquet", has(FoodInit.BIOMASS_BRIQUET.get())).save(cons, "dcs_climate:core/campfire_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.ARROW, 4).pattern("X").pattern("Y").pattern("Z").define('Z', Tags.Items.FEATHERS).define('Y', Tags.Items.RODS_WOODEN).define('X', TagDC.ItemTag.GEM_AGATES)
		    .unlockedBy("has_agate", has(TagDC.ItemTag.GEM_AGATES)).save(cons, "dcs_climate:core/arrow_another_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.FLINT, 1).requires(Tags.Items.GRAVEL).unlockedBy("has_gravel", has(Tags.Items.GRAVEL)).save(cons, "dcs_climate:core/flint_another_0");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, Items.FLINT_AND_STEEL, 1).requires(Tags.Items.INGOTS_IRON).requires(TagDC.ItemTag.GEM_AGATES).unlockedBy("has_agate", has(TagDC.ItemTag.GEM_AGATES))
		    .save(cons, "dcs_climate:core/firestarter_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Items.STICKY_PISTON, 1).pattern("X").pattern("Y").define('X', TagDC.ItemTag.SAP_RESIN).define('Y', Items.PISTON).unlockedBy("has_sap_resin", has(TagDC.ItemTag.SAP_RESIN))
		    .save(cons, "dcs_climate:core/stickey_piston_another_0");

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Items.DAYLIGHT_DETECTOR, 1).pattern("XXX").pattern("YYY").pattern("ZZZ").define('X', Tags.Items.GLASS).define('Y', TagDC.ItemTag.GEM_AGATES).define('Z', ItemTags.WOODEN_SLABS)
		    .unlockedBy("has_sap_resin", has(TagDC.ItemTag.SAP_RESIN)).save(cons, "dcs_climate:core/daylight_detector_another_0");

		// bleaching

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.WHITE_WOOL, 8).pattern("XXX").pattern("XYX").pattern("XXX").define('Y', TagDC.ItemTag.SOAP_OIL).define('X', TagDC.ItemTag.COLORED_WOOL).group("bliaching")
		    .unlockedBy("has_soap", has(TagDC.ItemTag.SOAP_OIL)).save(cons, "dcs_climate:core/bleaching_wool_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.TERRACOTTA, 8).pattern("XXX").pattern("XYX").pattern("XXX").define('Y', TagDC.ItemTag.SOAP_OIL).define('X', TagDC.ItemTag.COLORED_TERRACOTTA).group("bliaching")
		    .unlockedBy("has_soap", has(TagDC.ItemTag.SOAP_OIL)).save(cons, "dcs_climate:core/bleaching_tetrracotta_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.GLASS, 8).pattern("XXX").pattern("XYX").pattern("XXX").define('Y', TagDC.ItemTag.SOAP_OIL).define('X', TagDC.ItemTag.COLORED_GLASS).group("bliaching")
		    .unlockedBy("has_soap", has(TagDC.ItemTag.SOAP_OIL)).save(cons, "dcs_climate:core/bleaching_glass_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.GLASS_PANE, 8).pattern("XXX").pattern("XYX").pattern("XXX").define('Y', TagDC.ItemTag.SOAP_OIL).define('X', TagDC.ItemTag.COLORED_GLASS_PLATE).group("bliaching")
		    .unlockedBy("has_soap", has(TagDC.ItemTag.SOAP_OIL)).save(cons, "dcs_climate:core/bleaching_glass_pane_1");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CANDLE, 8).pattern("XXX").pattern("XYX").pattern("XXX").define('Y', TagDC.ItemTag.SOAP_OIL).define('X', TagDC.ItemTag.COLORED_CANDLE).group("bliaching")
		    .unlockedBy("has_soap", has(TagDC.ItemTag.SOAP_OIL)).save(cons, "dcs_climate:core/bleaching_candle_1");

	}

	private static void smeltingRecipes(Consumer<FinishedRecipe> cons) {

		smeltingRecipe(cons, Ingredient.of(Tags.Items.GRAVEL), Items.FLINT, 200, "gem_flint", Items.GRAVEL, "has_gravel");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.GEM_FLINT), CoreInit.GEM_CHALCEDONY.get(), 200, "gem_chalcedony", Items.FLINT, "has_flint");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_CRYSTAL), BuildInit.GLASS_CRYSTAL.get().asItem(), 200, "dust_crystal", CoreInit.DUST_CRYSTAL.get(), "has_dust_crystal");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_PLANT), FoodInit.DUST_ASH.get(), 200, "dust_ash", FoodInit.DUST_PLANT.get(), "has_dust_plant");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_PLANT), FoodInit.DUST_ASH.get(), 200, "dust_wood", FoodInit.DUST_WOOD.get(), "has_dust_wood");

		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_IRON), Items.IRON_INGOT, 200, "ingot_iron", CoreInit.OREDUST_RED1.get(), "has_dust_iron");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_COPPER), Items.COPPER_INGOT, 200, "ingot_copper", CoreInit.OREDUST_WHITE1.get(), "has_dust_copper");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUST_GOLD), Items.GOLD_INGOT, 200, "ingot_gold", CoreInit.OREDUST_WHITE2.get(), "has_dust_gold");

		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUSTBLOCK_STEEL), Items.IRON_BLOCK, 200, "container_iron", CoreInit.DUSTBLOCK_STEEL.get().asItem(), "has_dustblock_iron");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUSTBLOCK_BRASS), CoreInit.METALBLOCK_BRASS.get().asItem(), 200, "container_brass", CoreInit.DUSTBLOCK_BRASS.get().asItem(), "has_dustblock_brass");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUSTBLOCK_BRONZE), CoreInit.METALBLOCK_BRONZE.get().asItem(), 200, "container_bronze", CoreInit.DUSTBLOCK_BRONZE.get().asItem(), "has_dustblock_bronze");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.DUSTBLOCK_SILVER), CoreInit.METALBLOCK_SILVER.get().asItem(), 200, "container_silver", CoreInit.DUSTBLOCK_SILVER.get().asItem(), "has_dustblock_silver");

		// アルミ建材の還元
		smeltingRecipe(cons, Ingredient.of(BuildInit.SLAB_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_slab_metal", BuildInit.SLAB_METAL.get().asItem(), "has_slab_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.FENCE_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_fence_metal", BuildInit.FENCE_METAL.get().asItem(), "has_fence_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.LADDER_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_ladder_metal", BuildInit.LADDER_METAL.get().asItem(), "has_ladder_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.STAIRS_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_stairs_metal", BuildInit.STAIRS_METAL.get().asItem(), "has_stairs_metal");
		smeltingRecipe(cons, Ingredient.of(BuildInit.STAIRS_SLIM_METAL.get()), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_slim_stairs_metal", BuildInit.STAIRS_SLIM_METAL.get().asItem(), "has_slim_stairs_metal");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.ALMINUM_LOUVERS), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_louver_metal", BuildInit.LOUVER_HOL_METAL.get().asItem(), "has_louver_metal");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.ALMINUM_WINDOWS), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_window_metal", BuildInit.WINDOW_SIMPLE_METAL.get().asItem(), "has_window_metal");
		smeltingRecipe(cons, Ingredient.of(TagDC.ItemTag.ALMINUM_ROOFS), CoreInit.INGOT_ALUMINUM.get(), 200, "reduction_roof_metal", BuildInit.ROOF_METAL_GRAY.get().asItem(), "has_roof_metal");
	}

	private static void smeltingRecipe(Consumer<FinishedRecipe> cons, Ingredient input, Item output, int time, String name, Item unlockTarget, String unlockName) {
		SimpleCookingRecipeBuilder.smelting(input, RecipeCategory.MISC, output, 0F, time).unlockedBy(unlockName, has(unlockTarget)).save(cons, "dcs_climate:smelting/smelting_" + name);

		SimpleCookingRecipeBuilder.blasting(input, RecipeCategory.MISC, output, 0F, time / 2).unlockedBy(unlockName, has(unlockTarget)).save(cons, "dcs_climate:smelting/blasting_" + name);
	}

}
