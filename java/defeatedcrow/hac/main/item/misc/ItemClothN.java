package defeatedcrow.hac.main.item.misc;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemClothN extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"string_linen", "string_cotton", "cloth_linen", "cloth_cotton", "string_chrysotile", "cloth_chrysotile",
			"string_silk", "cloth_silk", "string_magic", "cloth_magic"
	};

	public ItemClothN(int max) {
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
	 * 4: 石綿糸
	 * 5: 石綿布
	 * 6: 絹糸
	 * 7: 絹布
	 * 8: 魔法糸
	 * 9: 魔法布
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
