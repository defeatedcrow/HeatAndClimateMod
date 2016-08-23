package defeatedcrow.hac.food.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemDCCrops extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"rice",
			"onion",
			"spinach",
			"tomato",
			"coffee",
			"cotton",
			"lemon",
			"olive",
			"tea",
			"harb" };

	public ItemDCCrops(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/crop_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
