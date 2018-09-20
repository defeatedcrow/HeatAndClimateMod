package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemCircuitChal extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"chalcedony"
	};

	public ItemCircuitChal() {
		super();
		maxMeta = names.length - 1;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: カルセドニー
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/plugin/circuit_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
