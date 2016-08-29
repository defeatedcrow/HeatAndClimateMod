package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemOreDusts extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"copper",
			"zinc",
			"nickel",
			"silver",
			"gold",
			"iron",
			"steel",
			"magnet",
			"tin" };

	public ItemOreDusts(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 銅
	 * 1: 亜鉛
	 * 2: ニッケル
	 * 3: 銀
	 * 4: 金
	 * 5: 鉄
	 * 6: 鋼
	 * 7: 磁石
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/ores/oredust_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
