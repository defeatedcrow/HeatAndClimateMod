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

	public static double rateVsRF = 10.0D;
	public static double rateVsEU = 2.0D;
	public static double rateVsFU = 10.0D;

	public static boolean steel_hardmode = true;
	public static boolean steel = true;

	public static boolean bird_effect = true;
	public static boolean ocean_effect = true;
	public static boolean armor_effect = false;

	public static boolean e_corrosion = true;
	public static boolean e_robber = true;
	public static boolean e_venom = true;

	public static double gun_damage = 12.0D;

	public static int aging_day = 60;

	public static String[] blocknames = new String[] {
		"minecraft:stone:32767",
		"minecraft:dirt:32767",
		"minecraft:bedrock:32767",
		"ModID:sampleBlock:sampleMeta" };
	public static final List<BlockSet> disables = Lists.newArrayList();

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("render setting", "This setting is for such as display and model.");
			cfg.addCustomCategoryComment("plugin setting", "This setting is for plugin with the other mods.");
			cfg.addCustomCategoryComment("item setting", "This setting is for the items.");
			cfg.addCustomCategoryComment("enchantment setting", "This setting is for the enchantment.");

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

			// Property e_cor = cfg
			// .get("enchantment setting", "Enable Corrosion", e_corrosion, "Enable effect of the Corrosion
			// Enchantment.");

			Property e_vem = cfg
					.get("enchantment setting", "Enable Venom", e_venom, "Enable effect of the Venom Enchantment.");

			Property e_rob = cfg
					.get("enchantment setting", "Enable Robber", e_robber, "Enable effect of the Highway Robber Enchantment.");

			Property aging_i = cfg
					.get("item setting", "Aging Day", aging_day, "Set the number of days required to age the drink in a barrel.");

			// Property zone = cfg.get("item setting", "TimeZone Setting", timeZone,
			// "Set the time zone for Realtime Clock.");

			gun_damage = gun_d.getDouble();
			steel_hardmode = metal_b.getBoolean();

			rateVsRF = vsRF.getDouble();
			rateVsEU = vsEU.getDouble();
			rateVsFU = vsRF.getDouble();

			steel = noSteel.getBoolean();
			bird_effect = p_bird.getBoolean();
			ocean_effect = p_ocean.getBoolean();
			// timeZone = zone.getString();

			// e_corrosion = e_cor.getBoolean();
			e_venom = e_vem.getBoolean();
			e_robber = e_rob.getBoolean();

			blocknames = b_dia.getStringList();

			aging_day = aging_i.getInt();

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

	public static void loadBlockNames() {
		disables.addAll(MainUtil.getListFromStrings(blocknames, "DiamondBadge Invalid List"));
	}

}
