package defeatedcrow.hac.main.config;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainCoreConfig {

	private MainCoreConfig() {}

	public static final MainCoreConfig INSTANCE = new MainCoreConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean enableAdvHUD = true;
	public static int iconX = 0;
	public static int iconY = -48;
	public static String tex1 = "textures/gui/hud_climate_normal.png";
	public static String tex2 = "textures/gui/hud_climate_drought.png";
	public static String tex3 = "textures/gui/hud_climate_rain.png";
	public static boolean showBiome = true;
	public static boolean showSeason = true;
	public static boolean showDay = true;
	public static boolean showClimate = true;
	public static int[] offsetBiome = {
			15,
			5
	};
	public static int[] offsetSeason = {
			5,
			-8
	};
	public static int[] offsetClimate = {
			10,
			15
	};

	public static double rateVsRF = 10.0D;
	public static double rateVsEU = 2.0D;
	public static double rateVsFU = 10.0D;

	public static boolean steel_hardmode = true;
	public static boolean steel = true;
	public static boolean pendant_clam = true;
	public static boolean bird_effect = true;
	public static boolean ocean_effect = true;
	public static boolean armor_effect = false;

	public static double gun_damage = 12.0D;

	public static String[] blocknames = new String[] {
			"minecraft:stone:32767",
			"minecraft:dirt:32767",
			"minecraft:bedrock:32767",
			"ModID:sampleBlock:sampleMeta"
	};
	public static final List<BlockSet> disables = Lists.newArrayList();

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("render setting", "This setting is for such as display and model.");
			cfg.addCustomCategoryComment("plugin setting", "This setting is for plugin with the other mods.");
			cfg.addCustomCategoryComment("item setting", "This setting is for the items.");

			Property hud_icon = cfg
					.get("render setting", "Climate HUD Info", enableAdvHUD, "Enable display the climate info on HUD.");

			Property hud_x = cfg
					.get("render setting", "Climate HUD Info Offset X", iconX, "Set the amount of Xoffset of the climate info.");

			Property hud_y = cfg
					.get("render setting", "Climate HUD Info Offset Y", iconY, "Set the amount of Yoffset of the climate info.");

			Property hud_biome = cfg
					.get("render setting", "Enable HUD Biome Name", showBiome, "Enable display the biome name on HUD.");

			Property hud_season = cfg
					.get("render setting", "Enable Season Name", showSeason, "Enable display the season name on HUD.");

			Property hud_day = cfg.get("render setting", "Enable HUD Date", showDay, "Enable display the date on HUD.");

			Property hud_climate = cfg
					.get("render setting", "Enable HUD Climate Name", showClimate, "Enable display the climate parameter on HUD.");

			Property off_biome = cfg
					.get("render setting", "Offset HUD Biome Name", offsetBiome, "Set the amount of offset of the biome name in HUD.");

			Property off_season = cfg
					.get("render setting", "Offset HUD Season Name", offsetSeason, "Set the amount of offset of the season name in HUD.");

			Property off_climate = cfg
					.get("render setting", "Offset HUD Climate Name", offsetClimate, "Set the amount of offset of the climate parameter in HUD.");

			Property vsRF = cfg
					.get("plugin setting", "Conversion rate vs RF", rateVsRF, "Set the amount of conversion rate as RF/torque.");

			Property vsEU = cfg
					.get("plugin setting", "Conversion rate vs EU", rateVsEU, "Set the amount of conversion rate as EU/torque.");

			Property noSteel = cfg
					.get("item setting", "Enable Steel Recipe", steel, "Enable the climate recipe for smelting the steel block.");

			Property b_dia = cfg
					.get("item setting", "Diamond Badge Disable List", blocknames, "Please add block registry names you want exclude from red badge effect.");

			Property p_bird = cfg
					.get("item setting", "Enable Wing Potion Effect", bird_effect, "Enable effect of Wing Blessing potion.");

			Property p_ocean = cfg
					.get("item setting", "Enable Ocean Potion Effect", ocean_effect, "Enable effect of Ocean Blessing potion.");

			Property armor_e = cfg
					.get("item setting", "Show Armor Enchantment Effect", armor_effect, "Enable rendering effect of enchanted HaC armor at wearing.");

			Property gun_d = cfg
					.get("item setting", "Gun Base Damage", gun_damage, "Set default damage amount of guns (brass bullet).");

			Property metal_b = cfg
					.get("item setting", "Enable HardMode Metallurgy", steel_hardmode, "Enable Heat Treatment Process for Metallurgy.");

			// Property zone = cfg.get("item setting", "TimeZone Setting", timeZone,
			// "Set the time zone for Realtime Clock.");

			enableAdvHUD = hud_icon.getBoolean();
			iconX = hud_x.getInt();
			iconY = hud_y.getInt();
			showBiome = hud_biome.getBoolean();
			showSeason = hud_season.getBoolean();
			showDay = hud_day.getBoolean();
			showClimate = hud_climate.getBoolean();
			offsetBiome = off_biome.getIntList();
			offsetSeason = off_season.getIntList();
			offsetClimate = off_climate.getIntList();
			gun_damage = gun_d.getDouble();
			steel_hardmode = metal_b.getBoolean();

			rateVsRF = vsRF.getDouble();
			rateVsEU = vsEU.getDouble();
			rateVsFU = vsRF.getDouble();

			steel = noSteel.getBoolean();
			bird_effect = p_bird.getBoolean();
			ocean_effect = p_ocean.getBoolean();
			// timeZone = zone.getString();

			blocknames = b_dia.getStringList();

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

	public static void leadBlockNames() {
		disables.addAll(MainUtil.getListFromStrings(blocknames, "DiamondBadge Invalid List"));
	}

}
