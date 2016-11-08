package defeatedcrow.hac.magic.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemMaceCore extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"light",
			"moon",
			"bird",
			"ice",
			"ocean" };

	public ItemMaceCore(int max) {
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
		String s = "items/magic/macecore_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
