package defeatedcrow.hac.main.config;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class WorldGenConfig {

	private WorldGenConfig() {}

	public static final WorldGenConfig INSTANCE = new WorldGenConfig();
	private final String BR = System.getProperty("line.separator");

	public static String[] blocknames = new String[] { "minecraft:dirt:32767", "ModID:sampleBlock:sampleMeta" };
	public static final List<BlockSet> disables = Lists.newArrayList();

	// ore
	public static int[] depositGen = new int[] { 50, 30, 30, 30, 10, 30, 20 };

	public static int[] radGen = new int[] { 5, 4, 5, 4, 3, 4 };

	public static int skarnGen = 50;

	public static int windmillGen = 50;

	public static int saplingGen = 500;

	public static int caravanGen = 20;

	public static boolean mazaiLake = true;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("world setting", "This setting is for world gen.");
			cfg.addCustomCategoryComment("ore gen setting", "This setting is for ore gen. " + BR + "Please set probability as parsentage." + BR + "If you set 0, those ore deposits will not be generated.");

			Property sed_ore = cfg
					.get("ore gen setting", "Red Vain Gen Probability", depositGen[0], "Generate in High-altitude of mountain. 0-100%");

			Property char_ore = cfg
					.get("ore gen setting", "Green Vein Gen Probability", depositGen[1], "Generate in underground of mountain. 0-100%");

			Property vein_ore = cfg
					.get("ore gen setting", "Blue Vein Gen Probability", depositGen[2], "Generate in underground of plane. 0-100%");

			Property lava_ore = cfg
					.get("ore gen setting", "White Vain Gen Probability", depositGen[3], "Generate in deep-underground. 0-100%");

			Property geode_ore = cfg
					.get("ore gen setting", "Black Vain Gen Probability", depositGen[4], "Generate in deep-underground. 0-100%");

			Property s_sed_ore = cfg
					.get("ore gen setting", "Guano Gen Probability", depositGen[5], "Generate under the sea. 0-100%");

			Property neth_ore = cfg
					.get("ore gen setting", "Nether Vain Gen Probability", depositGen[6], "Generate in the nether. 0-100%");

			Property sed_rad = cfg.get("ore gen setting", "Red Vain Gen Radius", radGen[0], "Default radius: 5");

			Property s_sed_rad = cfg.get("ore gen setting", "Green Vain Gen Radius", radGen[1], "Default radius: 4");

			Property char_rad = cfg.get("ore gen setting", "Blue Vain Gen Radius", radGen[2], "Default radius: 5");

			Property vein_rad = cfg.get("ore gen setting", "White Vein Gen Radius", radGen[3], "Default radius: 4");

			Property lava_rad = cfg.get("ore gen setting", "Black Vain Gen Radius", radGen[4], "Default radius: 3");

			Property geode_rad = cfg.get("ore gen setting", "Geode Gen Radius", radGen[5], "Default radius: 4");

			Property skarn_ore = cfg
					.get("ore gen setting", "Skarn Gen Probability", skarnGen, "Generate in Forest. 0.00-100.00% (default: 0.5%)");

			Property windmill = cfg
					.get("world setting", "Windmill Gen Probability", windmillGen, "Generate in Forest or Plain. 0.00-100.00% (default: 0.5%)");

			Property caravan = cfg
					.get("world setting", "Caravanserai Gen Probability", caravanGen, "Generate in Desert or Savanna. 0.00-100.00% (default: 0.2%)");

			Property mazai = cfg
					.get("world setting", "Mana Liqueur Lake Gen", mazaiLake, "Enable genaration the mana liqueur lake in nether biome.");

			Property b_gen = cfg
					.get("ore gen setting", "Blockgen Disable List", blocknames, "Please add block registry names you want exclude from HaC vain generation.");

			Property sap = cfg
					.get("world setting", "Wild Sapling Gen Probability", saplingGen, "Generate on the surface of Overworld. 0.00-100.00% (default: 5.0%)");

			int s = sed_ore.getInt();
			if (s < 0 || s > 100) {
				s = 0;
			}
			int c = char_ore.getInt();
			if (c < 0 || c > 100) {
				c = 0;
			}
			int v = vein_ore.getInt();
			if (v < 0 || v > 100) {
				v = 0;
			}
			int l = lava_ore.getInt();
			if (l < 0 || l > 100) {
				l = 0;
			}
			int g = geode_ore.getInt();
			if (g < 0 || g > 100) {
				g = 0;
			}
			int ss = s_sed_ore.getInt();
			if (ss < 0 || ss > 100) {
				ss = 0;
			}
			int n = neth_ore.getInt();
			if (n < 0 || n > 100) {
				n = 0;
			}

			int s2 = sed_rad.getInt();
			if (s2 < 0 || s2 > 10) {
				s2 = 2;
			}
			int ss2 = s_sed_rad.getInt();
			if (ss2 < 0 || ss2 > 10) {
				ss2 = 2;
			}
			int c2 = char_rad.getInt();
			if (c2 < 0 || c2 > 10) {
				c2 = 2;
			}
			int v2 = vein_rad.getInt();
			if (v2 < 0 || v2 > 10) {
				v2 = 2;
			}
			int l2 = lava_rad.getInt();
			if (l2 < 0 || l2 > 10) {
				l2 = 2;
			}
			int g2 = geode_rad.getInt();
			if (g2 < 0 || g2 > 10) {
				g2 = 2;
			}

			int sk = skarn_ore.getInt();
			if (sk < 0 || sk > 10000) {
				sk = 0;
			}
			int wm = windmill.getInt();
			if (wm < 0 || wm > 10000) {
				wm = 0;
			}
			int cs = caravan.getInt();
			if (cs < 0 || cs > 10000) {
				cs = 0;
			}
			int sp = sap.getInt();
			if (sp < 0 || sp > 10000) {
				sp = 0;
			}

			depositGen[0] = s;
			depositGen[1] = c;
			depositGen[2] = v;
			depositGen[3] = l;
			depositGen[4] = g;
			depositGen[5] = ss;
			depositGen[6] = n;
			radGen[0] = s2;
			radGen[1] = ss2;
			radGen[2] = c2;
			radGen[3] = v2;
			radGen[4] = l2;
			radGen[5] = g2;
			skarnGen = sk;
			windmillGen = wm;
			caravanGen = cs;
			mazaiLake = mazai.getBoolean();
			blocknames = b_gen.getStringList();
			saplingGen = sp;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

	public static void leadBlockNames() {
		disables.addAll(MainUtil.getListFromStrings(blocknames, "OreGen Invalid List"));
	}

}
