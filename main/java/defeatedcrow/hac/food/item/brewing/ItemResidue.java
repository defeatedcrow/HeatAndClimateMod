package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemResidue extends DCItem {

	private final int maxMeta;

	private static String[] names = { "bsg", "sake", "wine", "silage", "ex_yeast", "ex_peptone", "ex_whey" };

	public ItemResidue() {
		super();
		maxMeta = 6;
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
		String s = "items/food/brewing/residue_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
