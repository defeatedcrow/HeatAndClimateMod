package defeatedcrow.hac.machine.recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
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

		ShapedRecipeBuilder.shaped(MachineInit.MOTOR_TIER2.get(), 1)
				.pattern("XYX")
				.pattern("ZYZ")
				.pattern("ZYZ")
				.define('X', TagDC.ItemTag.INGOT_MAGNET)
				.define('Y', TagDC.ItemTag.INGOT_STEEL)
				.define('Z', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_ingot_magnet", has(TagDC.ItemTag.INGOT_MAGNET))
				.save(cons, "dcs_climate:machine/middle_motor_0");

		ShapedRecipeBuilder.shaped(MachineInit.IMPELLER_TIER1.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.MOTOR_T1)
				.unlockedBy("has_motor_t1", has(TagDC.ItemTag.MOTOR_T1))
				.save(cons, "dcs_climate:machine/small_impeller_0");

		ShapedRecipeBuilder.shaped(MachineInit.IMPELLER_TIER2.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.INGOT_SUS)
				.define('Y', TagDC.ItemTag.MOTOR_T2)
				.unlockedBy("has_motor_t1", has(TagDC.ItemTag.MOTOR_T2))
				.save(cons, "dcs_climate:machine/middle_impeller_0");

		ShapedRecipeBuilder.shaped(MachineInit.BATTERYITEM_TIER1.get(), 1)
				.pattern("XYX")
				.pattern("XZX")
				.pattern("XWX")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', TagDC.ItemTag.DUST_NICKEL)
				.define('Z', TagDC.ItemTag.DUST_TRONA)
				.define('W', Items.BLAZE_POWDER)
				.unlockedBy("has_dust_nickel", has(TagDC.ItemTag.DUST_NICKEL))
				.save(cons, "dcs_climate:machine/small_battery_0");

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

		ShapedRecipeBuilder.shaped(MachineInit.FUEL_BURNER.get(), 1)
				.pattern("XYX")
				.pattern("ZWZ")
				.define('X', MachineInit.PIPE_BRASS.get())
				.define('Y', Items.FLINT_AND_STEEL)
				.define('Z', TagDC.ItemTag.INGOT_STEEL)
				.define('W', Items.BUCKET)
				.unlockedBy("has_pipe", has(MachineInit.PIPE_BRASS.get()))
				.save(cons, "dcs_climate:machine/fuel_burner_0");

		ShapedRecipeBuilder.shaped(MachineInit.KICHEN_BENCH_BRICK.get(), 1)
				.pattern("XYX")
				.pattern("XZX")
				.define('X', Tags.Items.INGOTS_BRICK)
				.define('Y', Items.CRAFTING_TABLE)
				.define('Z', Tags.Items.CHESTS)
				.unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
				.save(cons, "dcs_climate:machine/bricks_kichen_bench_0");

		ShapedRecipeBuilder.shaped(MachineInit.KICHEN_SINK_BRICK.get(), 1)
				.pattern("XYX")
				.pattern("XZX")
				.define('X', Tags.Items.INGOTS_BRICK)
				.define('Y', Items.BUCKET)
				.define('Z', MachineInit.PIPE_BRASS.get())
				.unlockedBy("has_pipe", has(MachineInit.PIPE_BRASS.get()))
				.save(cons, "dcs_climate:machine/bricks_sink_1");

		ShapelessRecipeBuilder.shapeless(MachineInit.KICHEN_SINK_BRICK.get(), 1)
				.requires(MachineInit.HALF_SINK_BRICK.get())
				.unlockedBy("has_half_sink", has(MachineInit.HALF_SINK_BRICK.get()))
				.save(cons, "dcs_climate:machine/bricks_sink_2");

		ShapelessRecipeBuilder.shapeless(MachineInit.HALF_SINK_BRICK.get(), 1)
				.requires(MachineInit.KICHEN_SINK_BRICK.get())
				.unlockedBy("has_kichen_sink", has(MachineInit.KICHEN_SINK_BRICK.get()))
				.save(cons, "dcs_climate:machine/bricks_half_sink_0");

		ShapedRecipeBuilder.shaped(MachineInit.HOPPER_FILTER.get(), 1)
				.pattern("XYX")
				.pattern("XZX")
				.pattern(" X ")
				.define('X', Tags.Items.INGOTS_IRON)
				.define('Y', Items.IRON_BARS)
				.define('Z', Tags.Items.CHESTS)
				.unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON))
				.save(cons, "dcs_climate:machine/hopper_filter_1");

		ShapedRecipeBuilder.shaped(MachineInit.HOPPER_FILTER.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', Items.HOPPER)
				.define('Y', Items.IRON_BARS)
				.unlockedBy("has_ingot_iron", has(Tags.Items.INGOTS_IRON))
				.save(cons, "dcs_climate:machine/hopper_filter_2");

		ShapedRecipeBuilder.shaped(MachineInit.HOPPER_GOLD.get(), 1)
				.pattern("X X")
				.pattern("XZX")
				.pattern(" X ")
				.define('X', Tags.Items.INGOTS_GOLD)
				.define('Z', Tags.Items.CHESTS)
				.unlockedBy("has_ingot_gold", has(Tags.Items.INGOTS_GOLD))
				.save(cons, "dcs_climate:machine/hopper_gold_1");

		ShapedRecipeBuilder.shaped(MachineInit.HOPPER_FILTER_GOLD.get(), 1)
				.pattern("XYX")
				.pattern("XZX")
				.pattern(" X ")
				.define('X', Tags.Items.INGOTS_GOLD)
				.define('Y', Items.IRON_BARS)
				.define('Z', Tags.Items.CHESTS)
				.unlockedBy("has_ingot_gold", has(Tags.Items.INGOTS_GOLD))
				.save(cons, "dcs_climate:machine/hopper_filter_gold_1");

		ShapedRecipeBuilder.shaped(MachineInit.HOPPER_FILTER_GOLD.get(), 1)
				.pattern("Y")
				.pattern("X")
				.define('X', MachineInit.HOPPER_GOLD.get())
				.define('Y', Items.IRON_BARS)
				.unlockedBy("has_hopper_gold", has(MachineInit.HOPPER_GOLD.get()))
				.save(cons, "dcs_climate:machine/hopper_filter_gold_2");

		ShapedRecipeBuilder.shaped(MachineInit.CONVEYOR.get(), 8)
				.pattern("XYX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', TagDC.ItemTag.MOTOR_T1)
				.unlockedBy("has_motor_t1", has(TagDC.ItemTag.MOTOR_T1))
				.save(cons, "dcs_climate:machine/conveyor_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.CONVEYOR_DROPPER.get(), 1)
				.requires(MachineInit.CONVEYOR.get())
				.requires(Items.DROPPER)
				.unlockedBy("has_conveyor", has(MachineInit.CONVEYOR.get()))
				.save(cons, "dcs_climate:machine/conveyor_dropper_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.CONVEYOR_FILLER.get(), 1)
				.requires(MachineInit.CONVEYOR.get())
				.requires(Items.BUCKET)
				.requires(Items.REPEATER)
				.unlockedBy("has_conveyor", has(MachineInit.CONVEYOR.get()))
				.save(cons, "dcs_climate:machine/conveyor_filler_0");

		ShapedRecipeBuilder.shaped(MachineInit.FAUCET_A.get(), 1)
				.pattern("XYX")
				.pattern("ZZZ")
				.pattern("Z  ")
				.define('X', TagDC.ItemTag.WATER)
				.define('Y', TagDC.ItemTag.CLOTH_RUBBER)
				.define('Z', TagDC.ItemTag.INGOT_SUS)
				.unlockedBy("has_ingot_sus", has(TagDC.ItemTag.INGOT_SUS))
				.save(cons, "dcs_climate:machine/faucet_a_1");

		ShapelessRecipeBuilder.shapeless(MachineInit.FAUCET_A.get(), 1)
				.requires(MachineInit.FAUCET_D.get())
				.unlockedBy("has_faucet_d", has(MachineInit.FAUCET_D.get()))
				.save(cons, "dcs_climate:machine/faucet_a_2");

		ShapelessRecipeBuilder.shapeless(MachineInit.FAUCET_B.get(), 1)
				.requires(MachineInit.FAUCET_A.get())
				.unlockedBy("has_faucet_a", has(MachineInit.FAUCET_A.get()))
				.save(cons, "dcs_climate:machine/faucet_b_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.FAUCET_C.get(), 1)
				.requires(MachineInit.FAUCET_B.get())
				.unlockedBy("has_faucet_b", has(MachineInit.FAUCET_B.get()))
				.save(cons, "dcs_climate:machine/faucet_c_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.FAUCET_D.get(), 1)
				.requires(MachineInit.FAUCET_C.get())
				.unlockedBy("has_faucet_c", has(MachineInit.FAUCET_C.get()))
				.save(cons, "dcs_climate:machine/faucet_d_0");

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

		ShapedRecipeBuilder.shaped(MachineInit.CABLE_COPPER.get(), 8)
				.pattern("XXX")
				.define('X', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_ingot_copper", has(Tags.Items.INGOTS_COPPER))
				.save(cons, "dcs_climate:machine/copper_cable_0");

		ShapedRecipeBuilder.shaped(MachineInit.CABLE_COPPER_COATED.get(), 8)
				.pattern("YYY")
				.pattern("XXX")
				.define('Y', TagDC.ItemTag.CLOTH_RUBBER)
				.define('X', Tags.Items.INGOTS_COPPER)
				.unlockedBy("has_ingot_copper", has(Tags.Items.INGOTS_COPPER))
				.save(cons, "dcs_climate:machine/copper_cable_coated_0");

		ShapedRecipeBuilder.shaped(MachineInit.PIPE_BRASS.get(), 8)
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_BRASS)
				.unlockedBy("has_ingot_brass", has(TagDC.ItemTag.INGOT_BRASS))
				.save(cons, "dcs_climate:machine/brass_pipe_0");

		ShapedRecipeBuilder.shaped(MachineInit.BATTERY_SMALL.get(), 1)
				.pattern("XXX")
				.pattern("YYY")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', MachineInit.BATTERYITEM_TIER1.get())
				.unlockedBy("has_cbatteryitem_t1", has(MachineInit.BATTERYITEM_TIER1.get()))
				.save(cons, "dcs_climate:machine/battery_small_0");

		ShapedRecipeBuilder.shaped(MachineInit.GENERATOR_SMALL.get(), 1)
				.pattern("XXX")
				.pattern("YZY")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', MachineInit.BATTERYITEM_TIER1.get())
				.define('Z', TagDC.ItemTag.IMPELLER_T1)
				.unlockedBy("has_cbatteryitem_t1", has(MachineInit.BATTERYITEM_TIER1.get()))
				.save(cons, "dcs_climate:machine/generator_small_0");

		ShapedRecipeBuilder.shaped(MachineInit.BOILER_BIOMASS.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern("XZX")
				.define('X', TagDC.ItemTag.INGOT_STEEL)
				.define('Y', Items.BUCKET)
				.define('Z', MachineInit.CHAMBER_IRON.get())
				.unlockedBy("has_chamber", has(MachineInit.CHAMBER_IRON.get()))
				.save(cons, "dcs_climate:machine/boiler_biomass_0");

		ShapedRecipeBuilder.shaped(MachineInit.HYDRO_TURBINE.get(), 1)
				.pattern("XXX")
				.pattern("ZYZ")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_SUS)
				.define('Y', TagDC.ItemTag.IMPELLER_T2)
				.define('Z', MachineInit.CABLE_COPPER_COATED.get())
				.unlockedBy("has_impeller_t2", has(TagDC.ItemTag.IMPELLER_T2))
				.save(cons, "dcs_climate:machine/hydro_turbine_0");

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

		ShapedRecipeBuilder.shaped(MachineInit.WATER_PUMP.get(), 1)
				.pattern("XYX")
				.pattern("XZX")
				.pattern("XWX")
				.define('X', TagDC.ItemTag.INGOT_NICKEL_SILVER)
				.define('Y', TagDC.ItemTag.FLUID_PIPE)
				.define('Z', Items.BUCKET)
				.define('W', TagDC.ItemTag.IMPELLER_T1)
				.unlockedBy("has_impeller_t1", has(TagDC.ItemTag.IMPELLER_T1))
				.save(cons, "dcs_climate:machine/water_pump_0");

		ShapedRecipeBuilder.shaped(MachineInit.INTAKE_FAN.get(), 1)
				.pattern("XXX")
				.pattern("YZY")
				.pattern("XXX")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.define('Y', Items.IRON_BARS)
				.define('Z', TagDC.ItemTag.IMPELLER_T2)
				.unlockedBy("has_impeller_t2", has(TagDC.ItemTag.IMPELLER_T2))
				.save(cons, "dcs_climate:machine/intake_fan_0");

		ShapedRecipeBuilder.shaped(MachineInit.EXHAUST_VENT.get(), 1)
				.pattern(" X ")
				.pattern("XYX")
				.pattern(" X ")
				.define('X', TagDC.ItemTag.INGOT_ALUMINUM)
				.define('Y', Items.IRON_BARS)
				.unlockedBy("has_iron_bars", has(Items.IRON_BARS))
				.save(cons, "dcs_climate:machine/exhaust_vent_0");

		ShapedRecipeBuilder.shaped(MachineInit.ITEM_ASPIRATOR.get(), 1)
				.pattern("XYX")
				.pattern("X X")
				.pattern("XZX")
				.define('X', TagDC.ItemTag.INGOT_SUS)
				.define('Y', MachineInit.INTAKE_FAN.get())
				.define('Z', Items.REDSTONE_TORCH)
				.unlockedBy("has_intake_fan", has(MachineInit.INTAKE_FAN.get()))
				.save(cons, "dcs_climate:machine/item_aspirator_0");

		ShapedRecipeBuilder.shaped(MachineInit.CROP_ASPIRATOR.get(), 1)
				.pattern("XYX")
				.pattern("XWX")
				.pattern("XZX")
				.define('X', TagDC.ItemTag.INGOT_SUS)
				.define('Y', MachineInit.INTAKE_FAN.get())
				.define('Z', Items.REDSTONE_TORCH)
				.define('W', TagDC.ItemTag.SCYTHES)
				.unlockedBy("has_intake_fan", has(MachineInit.INTAKE_FAN.get()))
				.save(cons, "dcs_climate:machine/crop_aspirator_1");

		ShapelessRecipeBuilder.shapeless(MachineInit.CROP_ASPIRATOR.get(), 1)
				.requires(MachineInit.ITEM_ASPIRATOR.get())
				.requires(TagDC.ItemTag.SCYTHES)
				.unlockedBy("has_item_aspirator", has(MachineInit.ITEM_ASPIRATOR.get()))
				.save(cons, "dcs_climate:machine/crop_aspirator_2");

		ShapedRecipeBuilder.shaped(MachineInit.MONITOR_RS.get(), 1)
				.pattern("XZX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.DUST_BISMUTH)
				.define('Y', Tags.Items.STONE)
				.define('Z', Items.REDSTONE_TORCH)
				.unlockedBy("has_dust_bismuth", has(TagDC.ItemTag.DUST_BISMUTH))
				.save(cons, "dcs_climate:machine/monitor_rs_lamp_0");

		ShapedRecipeBuilder.shaped(MachineInit.MONITOR_RS_PILOT.get(), 1)
				.pattern("XZX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.DUST_BISMUTH)
				.define('Y', Tags.Items.STONE)
				.define('Z', Items.REDSTONE_LAMP)
				.unlockedBy("has_dust_bismuth", has(TagDC.ItemTag.DUST_BISMUTH))
				.save(cons, "dcs_climate:machine/monitor_rs_pilot_0");

		ShapedRecipeBuilder.shaped(MachineInit.MONITOR_COMPARATOR.get(), 1)
				.pattern("XZX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.DUST_BISMUTH)
				.define('Y', Tags.Items.STONE)
				.define('Z', Items.COMPARATOR)
				.unlockedBy("has_dust_bismuth", has(TagDC.ItemTag.DUST_BISMUTH))
				.save(cons, "dcs_climate:machine/monitor_comparator_0");

		ShapedRecipeBuilder.shaped(MachineInit.MONITOR_ANDON_LAMP.get(), 1)
				.pattern("XZX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.DUST_BISMUTH)
				.define('Y', TagDC.ItemTag.GEM_AGATES)
				.define('Z', TagDC.ItemTag.CRAFT_DRIVER)
				.unlockedBy("has_dust_bismuth", has(TagDC.ItemTag.DUST_BISMUTH))
				.save(cons, "dcs_climate:machine/monitor_andon_lamp_0");

		ShapedRecipeBuilder.shaped(MachineInit.MONITOR_TEMP.get(), 1)
				.pattern("XZX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.DUST_BISMUTH)
				.define('Y', TagDC.ItemTag.GEM_AGATES)
				.define('Z', CoreInit.TEMPMETER.get())
				.unlockedBy("has_dust_bismuth", has(TagDC.ItemTag.DUST_BISMUTH))
				.save(cons, "dcs_climate:machine/monitor_temp_0");

		ShapedRecipeBuilder.shaped(MachineInit.MONITOR_ENERGY.get(), 1)
				.pattern("XZX")
				.pattern("YYY")
				.define('X', TagDC.ItemTag.DUST_BISMUTH)
				.define('Y', TagDC.ItemTag.GEM_AGATES)
				.define('Z', CoreInit.ENERGYMETER.get())
				.unlockedBy("has_dust_bismuth", has(TagDC.ItemTag.DUST_BISMUTH))
				.save(cons, "dcs_climate:machine/monitor_energy_0");

		ShapelessRecipeBuilder.shapeless(MachineInit.STORMGLASS.get(), 1)
				.requires(TagDC.ItemTag.DUST_NITER)
				.requires(TagDC.ItemTag.CAMPHOR)
				.requires(TagDC.ItemTag.LIQUOR)
				.requires(TagDC.ItemTag.WATER)
				.unlockedBy("has_niter_dust", has(TagDC.ItemTag.DUST_NITER))
				.save(cons, "dcs_climate:machine/stormglass_0");

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
