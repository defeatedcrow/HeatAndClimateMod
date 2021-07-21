package defeatedcrow.hac.food.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemMeatMaterials extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"viscera",
			"rennet",
			"raw_squid",
			"agar",
			"gelatine",
			"soymeat",
			"miso",
			"soysauce",
			"siroan",
			"raw_prawn",
			"raw_roe",
			"anko"
	};

	public ItemMeatMaterials() {
		super();
		maxMeta = 11;
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
		String s = "items/food/food_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
