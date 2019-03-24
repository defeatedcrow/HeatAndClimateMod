package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemOreCrystal extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"red_1",
			"green_1",
			"blue_1",
			"white_1",
			"black_1",
			"red_2",
			"green_2",
			"blue_2",
			"white_2",
			"black_2",
			"red_3",
			"green_3",
			"blue_3",
			"white_3",
			"black_3"
	};

	public ItemOreCrystal(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * Tier1
	 * 0: 銅 chalcopyrite
	 * 1: 錫石 cassiterite
	 * 2: 閃亜鉛鉱 sphalerite
	 * 3: 赤鉄鋼 hematite
	 * 4: 磁鉄鉱 magnetite
	 * Tier2
	 * 5: ボーキサイト bauxite
	 * 6: 珪ニッケル garnierite
	 * 7: 方鉛鉱 galena
	 * 8: 金 gold
	 * 9: 銀 argentite
	 * Tier3
	 * 10: 菱マンガン rhodochrosite
	 * 11: クロム(ウヴァロバイト) chromite/uvarovite
	 * 12: 輝蒼鉛鉱 bismuthinite
	 * 13: 金紅石 rutile
	 * 14: 輝水鉛鉱 molybdenite
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/ores/oreitem_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
