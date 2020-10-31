package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemAntibiotic extends DCItem {

	private final int maxMeta;

	private static String[] names = { "pcn", "cp", "anti_zombie", "poison", "zombie", "pigman", "mushroom", "mana" };

	public ItemAntibiotic() {
		super();
		maxMeta = 7;
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
		String s = "items/food/brewing/antibiotic_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
