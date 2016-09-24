package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainCoreConfig {

	private MainCoreConfig() {
	}

	public static final MainCoreConfig INSTANCE = new MainCoreConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean enableAdvHUD = true;
	public static int iconX = 0;
	public static int iconY = -50;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("render setting", "This setting is for such as display and model.");

			Property hud_icon = cfg.get("render setting", "Advanced Info on HUD", enableAdvHUD,
					"Enable display the advanced info on HUD.");

			Property hud_x = cfg.get("render setting", "Advanced Info Offset X", iconX,
					"Set the amount of Xoffset of the advanced info.");

			Property hud_y = cfg.get("render setting", "Advanced Info Offset Y", iconY,
					"Set the amount of Yoffset of the advanced info.");

			enableAdvHUD = hud_icon.getBoolean();
			iconX = hud_x.getInt();
			iconY = hud_y.getInt();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}
