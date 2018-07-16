package defeatedcrow.hac.main.util;

import net.minecraft.client.resources.I18n;

public enum EnumFixedName {
	HEAT("dcs.tip.heat"),
	HUM("dcs.tip.hum"),
	AIR("dcs.tip.air"),
	CLIMATE("dcs.tip.climate"),
	ITEM("dcs.tip.item"),
	FLUID("dcs.tip.fluid"),
	TORQUE("dcs.tip.torque"),
	AMOUNT("dcs.tip.amount"),
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
	PAIRING("dcs.tip.pairing"),
	ON_ANVIL("dcs.tip.on_anvil");

	private final String name;

	private EnumFixedName(String n) {
		name = n;
	}

	public String getLocalizedName() {
		return I18n.format(name);
	}

}
