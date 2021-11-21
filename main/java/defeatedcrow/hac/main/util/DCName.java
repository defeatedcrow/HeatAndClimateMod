package defeatedcrow.hac.main.util;

import java.util.Calendar;
import java.util.Locale;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.ClimateCore;

public enum DCName {
	HEAT("dcs.tip.heat"),
	TEMP("dcs.tip.temp"),
	HUM("dcs.tip.hum"),
	AIR("dcs.tip.air"),
	TEMP2("dcs.tip.temp2"),
	HUM2("dcs.tip.hum2"),
	AIR2("dcs.tip.air2"),
	CLIMATE("dcs.tip.climate"),
	CLIMATE_R("dcs.tip.climate_r"),
	ITEM("dcs.tip.item"),
	FLUID("dcs.tip.fluid"),
	TORQUE("dcs.tip.torque"),
	AMOUNT("dcs.tip.amount"),
	EMPTY("dcs.tip.empty"),
	RANGE("dcs.tip.range"),
	INPUT("dcs.tip.input"),
	OUTPUT("dcs.tip.output"),
	RACIPE("dcs.tip.racipe"),
	STAGE("dcs.tip.stage"),
	FUEL("dcs.tip.fuel"),
	FUEL_SOLID("dcs.tip.fuel_s"),
	FUEL_FLUID("dcs.tip.fuel_f"),
	FUEL_GAS("dcs.tip.fuel_g"),
	RS("dcs.tip.rs"),
	COMPARATOR("dcs.tip.comparator"),
	TRANSPORT("dcs.tip.transport"),
	NON_POWERED("dcs.tip.non_powered"),
	WATER_CONSUMPTION("dcs.tip.water_consumption"),
	REQUIRE_CLIMATE("dcs.tip.require_recipe_climate"),
	OUTPUT_ITEM("dcs.tip.output_item"),
	OUTPUT_FLUID("dcs.tip.output_fluid"),
	PLACEABLE_ENTITY("dcs.tip.placeable_entity"),
	TURN_OFF("dcs.tip.turn_off"),
	DRAIN_SIDED_TANK("dcs.tip.drain_sided_tank"),
	RIGHT_CLICK("dcs.tip.right_click"),
	RIGHT_CLICK_WRANCH("dcs.tip.right_click_wrench"),
	PAIRING("dcs.tip.pairing"),
	ON_ANVIL("dcs.tip.on_anvil"),
	BURN_TIME("dcs.tip.burn_time"),
	STOPPING("dcs.tip.stopping"),
	FACING("dcs.tip.facing"),
	POWER("dcs.tip.power"),
	TARGET("dcs.tip.target"),
	REMOVE_BAD_POTION("dcs.tip.remove_bad_potion"),
	DRINK_CUSTOMIZE("dcs.tip.drink_customize"),
	MAGIC_CHARM("dcs.tip.allcharm"),
	MAGIC_AMULET("dcs.tip.amulet"),
	MAGIC_OFFHAND("dcs.tip.offhand_tool"),
	MAGIC_CLICK_POS("dcs.tip.r_click_pos"),
	MAGIC_CLICK_USE("dcs.tip.r_click_use"),
	MAGIC_CLICK_REMOVE("dcs.tip.r_click_remove"),
	MAGIC_COST("dcs.tip.magic_cost"),
	MAGIC_PLACE_LIMIT("dcs.tip.magic_limit");

	private final String name;

	private DCName(String n) {
		name = n;
	}

	public String getLocalizedName() {
		if (ClimateCore.proxy.getPlayer() != null) {
			return net.minecraft.client.resources.I18n.format(name);
		} else {
			return this.name();
		}
	}

	public static String getLocalizedDate() {
		return String.format(Locale.getDefault(), "%1$tb %1$te, %1$tY", Calendar.getInstance());
	}

	public static String getMagicCost() {
		if (CoreConfigDC.harderMagicCost == 1) {
			return "Hunger";
		}
		if (CoreConfigDC.harderMagicCost == 2) {
			return "Health";
		}
		return "EXP";

	}

}
