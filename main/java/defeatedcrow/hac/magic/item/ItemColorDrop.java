package defeatedcrow.hac.magic.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemColorDrop extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"drop_u",
			"drop_g",
			"drop_r",
			"drop_b",
			"drop_w",
			"extract_u",
			"extract_g",
			"extract_r",
			"extract_b",
			"extract_w"
	};

	public ItemColorDrop() {
		super();
		maxMeta = 9;
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
		String s = "items/magic/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
