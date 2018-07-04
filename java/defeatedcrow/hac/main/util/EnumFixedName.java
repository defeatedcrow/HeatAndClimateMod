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
	RS("dcs.tip.rs"),
	TRANSPORT("dcs.tip.transport"),
	SPRING("dcs.tip.spring"),
	SUMMER("dcs.tip.summer"),
	AUTUMN("dcs.tip.autumn"),
	WINTER("dcs.tip.winter");

	private final String name;

	private EnumFixedName(String n) {
		name = n;
	}

	public String getLocalizedName() {
		return I18n.format(name);
	}

}
