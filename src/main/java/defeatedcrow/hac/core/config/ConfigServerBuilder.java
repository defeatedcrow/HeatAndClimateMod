package defeatedcrow.hac.core.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigServerBuilder {

	public static final ForgeConfigSpec CONFIG_SERVER;
	public static final ConfigServerBuilder INSTANCE;

	// date
	public final ForgeConfigSpec.IntValue vYear;
	public final ForgeConfigSpec.IntValue vStartSpr;
	public final ForgeConfigSpec.IntValue vStartSmr;
	public final ForgeConfigSpec.IntValue vStartAut;
	public final ForgeConfigSpec.IntValue vStartWtr;
	public final ForgeConfigSpec.IntValue vStartDate;
	public final ForgeConfigSpec.IntValue vOverYear;
	public final ForgeConfigSpec.ConfigValue<String> setFormat;
	public final ForgeConfigSpec.BooleanValue enRealTime;
	public String dateFormat = "yyyy/MM/dd";

	public static int HUD_type = 0;

	ConfigServerBuilder(ForgeConfigSpec.Builder builder) {

		builder.comment("========= Date Setting ========", "Setting for date in the game.").push("date_config");

		this.vYear = builder
			.comment("Set the number of days in the year.")
			.defineInRange("Length of the Year", 240, 1, 365);

		this.vStartSpr = builder
			.comment("Set the dates for the beginning of Spring.")
			.defineInRange("Start of Spring", 1, 1, 365);

		this.vStartSmr = builder
			.comment("Set the dates for the beginning of Summer.")
			.defineInRange("Start of Summer", 61, 1, 365);

		this.vStartAut = builder
			.comment("Set the dates for the beginning of Autumn.")
			.defineInRange("Start of Autumn", 121, 1, 365);

		this.vStartWtr = builder
			.comment("Set the dates for the beginning of Winter.")
			.defineInRange("Start of Winter", 181, 1, 365);

		this.vStartDate = builder
			.comment("Sets the in-game date at server start.")
			.defineInRange("Game Start Date", 0, 1, 365);

		this.vOverYear = builder
			.comment("Sets the id of season for the biginning of the years.")
			.defineInRange("Biginning Year Season ID", 3, 0, 3);

		this.enRealTime = builder
			.comment("Set the date format used in  real-date settings.")
			.define("Enable Real Time", false);

		this.setFormat = builder
			.comment("Sets the date display format.")
			.define("Date Display Format", "yyyy/MM/dd");

		builder.pop();
	}

	public static void setDateFormat() {
		String s = INSTANCE.setFormat.get();
		if (s == null) {
			INSTANCE.dateFormat = "yyyy/MM/dd";
		} else {
			if (s.contains("yy") || s.contains("MM") || s.contains("dd")) {
				INSTANCE.dateFormat = s;
			} else {
				INSTANCE.dateFormat = "yyyy/MM/dd";
			}
		}
	}

	static {
		Pair<ConfigServerBuilder, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder()
			.configure(ConfigServerBuilder::new);
		CONFIG_SERVER = pair.getRight();
		INSTANCE = pair.getLeft();
	}
}
