package defeatedcrow.hac.machine.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemSynthetic extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"string",
			"cloth",
			"plate"
	};

	public ItemSynthetic() {
		super();
		maxMeta = names.length - 1;
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
		String s = "items/misc/" + names[meta] + "_synthetic";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
