package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class WorldGenConfig {

	private WorldGenConfig() {}

	public static final WorldGenConfig INSTANCE = new WorldGenConfig();
	private final String BR = System.getProperty("line.separator");

	// ore
	public static int[] depositGen = new int[] {
			35,
			50,
			15,
			100,
			30
	};

	public static int skarnGen = 5;

	public static int windmillGen = 50;

	public static int caravanGen = 2;

	public static boolean mazaiLake = true;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("world setting", "This setting is for world gen.");
			cfg.addCustomCategoryComment("ore gen setting", "This setting is for ore gen. " + BR +
					"Please set probability as parsentage (0 - 100)." + BR +
					"If you set 0, those ore deposits will not be generated.");

			Property sed_ore = cfg.get("ore gen setting", "Sedimentary Gen Probability", depositGen[0],
					"Generate in High-altitude of mountain. 1-100%");

			Property char_ore = cfg.get("ore gen setting", "Chalcopyrite Gen Probability", depositGen[1],
					"Generate in underground of mountain. 1-100%");

			Property vein_ore = cfg.get("ore gen setting", "Quartz Vein Gen Probability", depositGen[2],
					"Generate in underground of plane. 1-100%");

			Property lava_ore = cfg.get("ore gen setting", "Magnetite Gen Probability", depositGen[3],
					"Generate in deep-underground. 1-100%");

			Property geode_ore = cfg.get("ore gen setting", "Geode Gen Probability", depositGen[4],
					"Generate in deep-underground. 1-100%");

			Property skarn_ore = cfg.get("ore gen setting", "Skarn Gen Probability", skarnGen,
					"Generate in Forest or Plain. 0.1-100.0% (default: 0.5%)");

			Property windmill = cfg.get("world setting", "Windmill Gen Probability", windmillGen,
					"Generate in Forest or Plain. 0.1-100.0% (default: 5.0%)");

			Property caravan = cfg.get("world setting", "Caravanserai Gen Probability", caravanGen,
					"Generate in Desert or Savanna. 0.1-100.0% (default: 0.2%)");

			Property mazai = cfg.get("world setting", "Mana Liqueur Lake Gen", mazaiLake,
					"Enable genaration the mana liqueur lake in nether biome.");

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
			int sk = skarn_ore.getInt();
			if (sk < 0 || sk > 1000) {
				sk = 0;
			}
			int wm = windmill.getInt();
			if (wm < 0 || wm > 1000) {
				wm = 0;
			}
			int cs = caravan.getInt();
			if (cs < 0 || cs > 1000) {
				cs = 0;
			}

			depositGen[0] = s;
			depositGen[1] = c;
			depositGen[2] = v;
			depositGen[3] = l;
			depositGen[4] = g;
			skarnGen = sk;
			windmillGen = wm;
			caravanGen = cs;
			mazaiLake = mazai.getBoolean();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}
