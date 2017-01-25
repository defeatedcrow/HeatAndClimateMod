package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemMiscDust extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"coal",
			"crystal",
			"lime",
			"wood",
			"presscake",
			"ash",
			"niter",
			"sulfur",
			"garnet"
	};

	public ItemMiscDust(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 炭
	 * 1: 石英
	 * 2: 石灰
	 * 3: 木
	 * 4: 油かす
	 * 5: 灰
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/ores/dust_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
