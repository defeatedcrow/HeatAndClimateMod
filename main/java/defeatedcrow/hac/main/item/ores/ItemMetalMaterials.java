package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemMetalMaterials extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"oredust_zinc_dirty",
			"shard_zinc",
			"chunk_zinc",
			"crystal_zinc",
			"oredust_nickel_dirty",
			"shard_nickel",
			"chunk_nickel",
			"crystal_nickel"
	};

	public ItemMetalMaterials() {
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
		String s = "items/plugin/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
