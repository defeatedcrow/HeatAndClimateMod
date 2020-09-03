package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.main.api.brewing.EnumMedium;

public class ItemMedium extends DCItem {

	private final int maxMeta;

	private static String[] names = { "simple", "standard", "potato", "giblets", "bacteria" };

	public ItemMedium() {
		super();
		maxMeta = 4;
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
		String s = "items/food/brewing/medium_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	public EnumMedium getMedium(int meta) {
		switch (meta) {
		case 0:
			return EnumMedium.SIMPLE;
		case 1:
			return EnumMedium.STANDARD;
		case 2:
			return EnumMedium.POTATO;
		case 3:
			return EnumMedium.GIBLETS;
		case 4:
			return EnumMedium.BACTERIA;
		default:
			return EnumMedium.SIMPLE;
		}
	}

}
