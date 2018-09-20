package defeatedcrow.hac.main.item.misc;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemFoodDust extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"bran",
			"plant",
			"molasses",
			"malt",
			"defatted_soy",
			"soy_pulp",
			"baking_soda"
	};

	public ItemFoodDust(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: ブラン
	 * 1: 繊維くず
	 * 2: 廃糖蜜
	 * 3: 麦芽
	 * 4: 脱脂大豆
	 * 5: おから
	 * 6: 重曹
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
