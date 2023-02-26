package defeatedcrow.hac.core.config;

import org.apache.commons.lang3.tuple.Pair;

import defeatedcrow.hac.api.climate.EnumSeason;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigCommonBuilder {

	public static final ForgeConfigSpec CONFIG_COMMON;
	public static final ConfigCommonBuilder INSTANCE;

	// climate
	public final ForgeConfigSpec.BooleanValue enTempDamage;
	public final ForgeConfigSpec.BooleanValue enPeacefulDamage;
	public final ForgeConfigSpec.BooleanValue enMobDamage;

	public final ForgeConfigSpec.IntValue vUpdateInterval;
	public final ForgeConfigSpec.IntValue vDifficulty;

	public final ForgeConfigSpec.DoubleValue vSeasonSpr;
	public final ForgeConfigSpec.DoubleValue vSeasonSmr;
	public final ForgeConfigSpec.DoubleValue vSeasonAut;
	public final ForgeConfigSpec.DoubleValue vSeasonWtr;
	public final ForgeConfigSpec.DoubleValue vSeasonHell;
	public final ForgeConfigSpec.DoubleValue vSeasonArc;

	public final ForgeConfigSpec.DoubleValue vWeatherRain;
	public final ForgeConfigSpec.DoubleValue vNight;

	// block
	public final ForgeConfigSpec.BooleanValue enFarmland;
	public final ForgeConfigSpec.BooleanValue enVanillaCrop;
	public final ForgeConfigSpec.BooleanValue enSnow;

	// item
	public final ForgeConfigSpec.BooleanValue enDropSmelting;
	public final ForgeConfigSpec.BooleanValue enPotionSharing;

	// date
	// public final ForgeConfigSpec.IntValue vYear;
	// public final ForgeConfigSpec.IntValue vStartSpr;
	// public final ForgeConfigSpec.IntValue vStartSmr;
	// public final ForgeConfigSpec.IntValue vStartAut;
	// public final ForgeConfigSpec.IntValue vStartWtr;
	// public final ForgeConfigSpec.IntValue vStartDate;
	// public final ForgeConfigSpec.ConfigValue<String> setFormat;
	// public final ForgeConfigSpec.BooleanValue enRealTime;
	// public EnumSeason overYear = EnumSeason.WINTER_LATE;
	// public String dateFormat = "yyyy/MM/dd";

	// hardmode
	public final ForgeConfigSpec.BooleanValue enInferno;
	public final ForgeConfigSpec.BooleanValue enWetEffect;
	public final ForgeConfigSpec.BooleanValue enTightDeep;
	public final ForgeConfigSpec.BooleanValue enTightEffect;

	public final ForgeConfigSpec.BooleanValue enMagicCost;
	public final ForgeConfigSpec.IntValue vMagicCost;

	// public final ForgeConfigSpec.BooleanValue enCookingFlavor;

	ConfigCommonBuilder(ForgeConfigSpec.Builder builder) {

		builder.comment("========= Climate Setting ========", "Setting for the climate function.").push("climate_config");

		this.enTempDamage = builder
			.comment("Enable damage from hot or cold climate.")
			.define("Enable Climate Damage", true);

		this.enPeacefulDamage = builder
			.comment("Enables climate damage in peaceful mode.")
			.define("Enable Peaceful Climate Damage", true);

		this.enMobDamage = builder
			.comment("Enables climate damage to mobs. If disabled, only the player will take damage.")
			.define("Enable Mob Climate Damage", true);

		this.vDifficulty = builder
			.comment("Set difficulty of climate damage. 0:sweet 1:normal 2:bitter")
			.defineInRange("Damage Difficulty", 1, 0, 2);

		this.vUpdateInterval = builder
			.comment("Set the number of tick of entity update interval.")
			.defineInRange("Climate Damage Interval Tick", 60, 20, 3600);

		this.vSeasonSpr = builder
			.comment("Set the value of temperature variation. (Spring)")
			.defineInRange("Spring Temperature", 0.05D, -6.0D, 120.0D);

		this.vSeasonSmr = builder
			.comment("Set the value of the temperature variation. (Summer)")
			.defineInRange("Summer Temperature", 0.4D, -6.0D, 120.0D);

		this.vSeasonAut = builder
			.comment("Set the value of the temperature variation. (Autumn)")
			.defineInRange("Autumn Temperature", 0.0D, -6.0D, 120.0D);

		this.vSeasonWtr = builder
			.comment("Set the value of the temperature variation. (Winter)")
			.defineInRange("Winter Temperature", -0.4D, -6.0D, 120.0D);

		this.vSeasonHell = builder
			.comment("Set the value of the temperature variation. (Nether)")
			.defineInRange("Nether Temperature", 2.0D, -6.0D, 120.0D);

		this.vSeasonArc = builder
			.comment("Set the value of the temperature variation. (End)")
			.defineInRange("End Temperature", -2.0D, -6.0D, 120.0D);

		this.vWeatherRain = builder
			.comment("Set the value of the temperature drop dur to rain.")
			.defineInRange("Rain Temperature", -0.2D, -6.0D, 120.0D);

		this.vNight = builder
			.comment("Set the value of nighttime temperature drop.")
			.defineInRange("Night Temperature", -0.2D, -6.0D, 120.0D);

		builder.pop();

		builder.comment("========= Hardmode Setting ========", "Setting for Hard Mode.").push("hardmode_config");

		this.enInferno = builder
			.comment("Set the temperature of Nether to maximum.")
			.define("Infernal Inferno", false);

		this.enWetEffect = builder
			.comment("Enable humidity effect that affects the player's perceived temperature.")
			.define("Humidity Effect", false);

		this.enTightEffect = builder
			.comment("Enable suffocation effect when mobs or players in tight space.")
			.define("Tight Effect", false);

		this.enTightDeep = builder
			.comment("When enabled, the airflow in the room is TIGHT in deep caves (Y<0).")
			.define("Tight in Deep", false);

		this.enMagicCost = builder
			.comment("When enabled, EXP is consumed to cast the magic.")
			.define("Magic EXP Cost", false);

		this.vMagicCost = builder
			.comment("Sets the amount of EXP consumed by magic. (When MagicEXPCost is enebled.)")
			.defineInRange("Amount of EXP Cost", 1, 0, 100);

		builder.pop();

		builder.comment("========= Block and Item Setting ========", "Setting for block and item effects.").push("block_and_item_config");

		this.enFarmland = builder
			.comment("Enable to moisture farmland in WET humidity.")
			.define("Enable Farmland Effect", true);

		this.enVanillaCrop = builder
			.comment("Enables the growth-promoting effect of vanilla crops.")
			.define("Enable Vanilla Crop Effect", true);

		this.enSnow = builder
			.comment("Enables the climate to affect the snow layer.")
			.define("Enable Snow Layer Effect", true);

		this.enPotionSharing = builder
			.comment("Enable sharing potion effects with riding mob.")
			.define("Enable Sharing Potion Effect", true);

		this.enDropSmelting = builder
			.comment("*Optional* Enabling configs increases the load on the computer.")
			.comment("Enable all climate smelting and vanilla smelting in drop item state.")
			.define("Enable Drop Item Smelting", false);

		builder.pop();

		// builder.comment("========= Date Setting ========", "Setting for date in the game.").push("date_config");
		//
		// this.vYear = builder
		// .comment("Set the number of days in the year.")
		// .defineInRange("Length of the Year", 240, 1, 365);
		//
		// this.vStartSpr = builder
		// .comment("Set the dates for the beginning of Spring.")
		// .defineInRange("Period of Spring", 1, 1, 365);
		//
		// this.vStartSmr = builder
		// .comment("Set the dates for the beginning of Summer.")
		// .defineInRange("Period of Summer", 61, 1, 365);
		//
		// this.vStartAut = builder
		// .comment("Set the dates for the beginning of Autumn.")
		// .defineInRange("Period of Autumn", 121, 1, 365);
		//
		// this.vStartWtr = builder
		// .comment("Set the dates for the beginning of Winter.")
		// .defineInRange("Period of Winter", 181, 1, 365);
		//
		// this.vStartDate = builder
		// .comment("Sets the in-game date at server start.")
		// .defineInRange("Game Start Date", 0, 1, 365);
		//
		// this.enRealTime = builder
		// .comment("Set the date format used in real-time settings.")
		// .define("Enable Real Time", false);
		//
		// this.setFormat = builder
		// .comment("Sets the date display format.")
		// .define("Date Display Format", "yyyy/MM/dd");

		// builder.pop();
	}

	static {
		Pair<ConfigCommonBuilder, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder()
			.configure(ConfigCommonBuilder::new);
		CONFIG_COMMON = pair.getRight();
		INSTANCE = pair.getLeft();
	}

	public float getSeasonTempOffset(EnumSeason season) {
		switch (season.season) {
		case 2:
			return vSeasonAut.get().floatValue();
		case 0:
			return vSeasonSpr.get().floatValue();
		case 1:
			return vSeasonSmr.get().floatValue();
		case 3:
			return vSeasonWtr.get().floatValue();
		case 4:
			return vSeasonHell.get().floatValue();
		case 5:
			return vSeasonArc.get().floatValue();
		default:
			return vSeasonSpr.getDefault().floatValue();

		}
	}

	// public void setDateFormat() {
	// String s = this.setFormat.get();
	// if (s == null) {
	// dateFormat = "yyyy/MM/dd";
	// } else {
	// if (s.contains("yy") || s.contains("MM") || s.contains("dd")) {
	// dateFormat = s;
	// } else {
	// dateFormat = "yyyy/MM/dd";
	// }
	// }
	// }

	// public void setSeasonOverYear() {
	// int s = 0;
	// overYear = EnumSeason.WINTER_LATE;
	// // 開始日が最も遅い季節を探す
	// if (s < this.vStartSpr.get()) {
	// s = this.vStartSpr.get();
	// overYear = EnumSeason.SPRING_LATE;
	// }
	// if (s < this.vStartSmr.get()) {
	// s = this.vStartSmr.get();
	// overYear = EnumSeason.SUMMER_LATE;
	// }
	// if (s < this.vStartAut.get()) {
	// s = this.vStartAut.get();
	// overYear = EnumSeason.AUTUMN_LATE;
	// }
	// if (s < this.vStartWtr.get()) {
	// s = this.vStartWtr.get();
	// overYear = EnumSeason.WINTER_LATE;
	// }
	// }
}
