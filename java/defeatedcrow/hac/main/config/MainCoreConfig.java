package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainCoreConfig {

	private MainCoreConfig() {}

	public static final MainCoreConfig INSTANCE = new MainCoreConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean enableAdvHUD = true;
	public static int iconX = 0;
	public static int iconY = -48;

	public static double rateVsRF = 10.0D;
	public static double rateVsEU = 2.0D;
	public static double rateVsFU = 10.0D;

	public static boolean steel = true;
	public static boolean lead = false;
	public static boolean pendant_schorl = true;
	public static boolean pendant_clam = true;

	public static String timeZone = "Asia/Tokyo";

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("render setting", "This setting is for such as display and model.");
			cfg.addCustomCategoryComment("plugin setting", "This setting is for plugin with the other mods.");
			cfg.addCustomCategoryComment("item setting", "This setting is for the items.");

			Property hud_icon = cfg.get("render setting", "Advanced Info on HUD", enableAdvHUD,
					"Enable display the advanced info on HUD.");

			Property hud_x = cfg.get("render setting", "Advanced Info Offset X", iconX,
					"Set the amount of Xoffset of the advanced info.");

			Property hud_y = cfg.get("render setting", "Advanced Info Offset Y", iconY,
					"Set the amount of Yoffset of the advanced info.");

			Property vsRF = cfg.get("plugin setting", "Conversion rate vs RF", rateVsRF,
					"Set the amount of conversion rate as RF/torque.");

			Property vsEU = cfg.get("plugin setting", "Conversion rate vs EU", rateVsEU,
					"Set the amount of conversion rate as EU/torque.");

			Property vsFU = cfg.get("plugin setting", "Conversion rate vs FU", rateVsFU,
					"Set the amount of conversion rate as FU/torque.");

			Property noSteel = cfg.get("item setting", "Enable Steel Recipe", steel,
					"Enable the climate recipe for smelting the steel block.");

			Property noLead = cfg.get("item setting", "Enable Lead Ingot", lead,
					"Enable to add the lead dust and ingot.");

			Property p_schorl = cfg.get("item setting", "Enable Schorl Pendant Effect", pendant_schorl,
					"Enable effect of schorl pendant.");

			// Property zone = cfg.get("item setting", "TimeZone Setting", timeZone,
			// "Set the time zone for Realtime Clock.");

			enableAdvHUD = hud_icon.getBoolean();
			iconX = hud_x.getInt();
			iconY = hud_y.getInt();

			rateVsRF = vsRF.getDouble();
			rateVsEU = vsEU.getDouble();
			rateVsFU = vsFU.getDouble();

			steel = noSteel.getBoolean();
			lead = noLead.getBoolean();
			pendant_schorl = p_schorl.getBoolean();
			// timeZone = zone.getString();

			// TimeZone tz = TimeZone.getTimeZone(timeZone);
			// if (tz != null) {
			// ClimateMain.CAL.setTimeZone(tz);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}
