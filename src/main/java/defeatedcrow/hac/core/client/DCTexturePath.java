package defeatedcrow.hac.core.client;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.resources.ResourceLocation;

public enum DCTexturePath {
	POTION("textures/gui/icons_potion.png"),
	HUD("textures/gui/hud_climate.png"),
	GRAY("textures/blocks/gray_effect.png"),
	HOT_DISP("textures/gui/hud_hot.png"),
	COLD_DISP("textures/gui/hud_cold.png"),
	GUI_INV_SINGLE("textures/gui/simple_inv_single.png"),
	GUI_INV_DOUBLE("textures/gui/simple_inv_double.png"),
	GUI_DISPLAY_SHELF("textures/gui/display_shelf_inv.png"),
	GUI_KICHEN_BENCH("textures/gui/kichen_bench_inv.png"),
	GUI_CHAMBER_ITEM("textures/gui/chamber_item_inv.png"),
	GUI_CHAMBER_FLUID("textures/gui/chamber_fluid_inv.png"),
	GUI_FLUID_TANK("textures/gui/fluid_tank_inv.png"),
	GUI_FLUID_TANK_LARGE("textures/gui/fluid_tank_large_inv.png"),
	GUI_COOKING_POT("textures/gui/cooking_pot_inv.png"),
	GUI_FERMENTATION_JAR("textures/gui/fermentation_jar_inv.png"),
	GUI_TEA_POT("textures/gui/tea_pot_inv.png"),
	GUI_MILL("textures/gui/stonemill_inv.png"),
	GUI_CRUSHER("textures/gui/roll_crusher_inv.png"),
	GUI_BATTERY("textures/gui/battery_small_inv.png"),
	GUI_GENERATOR("textures/gui/generator_small_inv.png"),
	GUI_BOILER_BIOMASS("textures/gui/boiler_biomass_inv.png"),
	GUI_HOPPER_FILTER("textures/gui/hopper_filter_inv.png"),
	GUI_HOPPER_GOLD("textures/gui/hopper_gold_inv.png"),
	GUI_HOPPER_GOLD_FILTER("textures/gui/hopper_filter_gold_inv.png"),
	GUI_CONVEYOR_SORTER("textures/gui/conveyor_sorter_inv.png"),
	GUI_MONITOR_ANDON("textures/gui/andon_signal_gui.png"),
	GUI_BORING("textures/gui/boring_survey.png");

	private final String name;

	private DCTexturePath(String nameIn) {
		name = nameIn;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getLocation() {
		return new ResourceLocation("dcs_climate", name);
	}

	public static List<DCTexturePath> elements() {
		return ImmutableList.of(POTION, HUD, GRAY, HOT_DISP, COLD_DISP, GUI_INV_SINGLE, GUI_INV_DOUBLE, GUI_DISPLAY_SHELF, GUI_CHAMBER_ITEM, GUI_CHAMBER_FLUID, GUI_FLUID_TANK,
				GUI_FLUID_TANK_LARGE, GUI_COOKING_POT, GUI_TEA_POT, GUI_FERMENTATION_JAR, GUI_MILL, GUI_CRUSHER, GUI_BATTERY, GUI_GENERATOR, GUI_BOILER_BIOMASS,
				GUI_HOPPER_FILTER, GUI_HOPPER_GOLD, GUI_HOPPER_GOLD_FILTER, GUI_CONVEYOR_SORTER, GUI_KICHEN_BENCH, GUI_MONITOR_ANDON, GUI_BORING);
	}
}
