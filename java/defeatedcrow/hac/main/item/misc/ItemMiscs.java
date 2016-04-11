package defeatedcrow.hac.main.item.misc;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemMiscs extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"string_linen",
			"string_cotton",
			"cloth_linen",
			"cloth_cotton",
			"spindle_wood" };

	public ItemMiscs(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 亜麻糸
	 * 1: 木綿糸
	 * 2: 亜麻布
	 * 3: 木綿布
	 * 4: 木の回転体
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
