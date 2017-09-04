package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemThermalScope extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"steel"
	};

	public ItemThermalScope() {
		super();
		maxMeta = 0;
		this.setMaxStackSize(1);
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
		String s = "items/tool/thermal_scope_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
