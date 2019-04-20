package defeatedcrow.hac.main.item.misc;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemGearN extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"wood",
			"brass",
			"alloy",
			"steel",
			"toolsteel"
	};

	public ItemGearN(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 木の回転体
	 * 1: 真鍮の回転体
	 * 2: 合金の回転体
	 * 3: 鋼の回転体
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/spindle_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
