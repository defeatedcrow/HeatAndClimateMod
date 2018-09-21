package defeatedcrow.hac.main.item.misc;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemDCIcon extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"hac",
			"burning",
			"freezing"
	};

	public ItemDCIcon() {
		super();
		maxMeta = 2;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: Modアイコン
	 * 1: 高温ダメージ
	 * 2: 低温ダメージ
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/icon_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
