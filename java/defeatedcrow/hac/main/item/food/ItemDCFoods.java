package defeatedcrow.hac.main.item.food;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFoodItem;

public class ItemDCFoods extends DCFoodItem {

	private final int maxMeta;

	private static String[] names = { "baked_apple" };

	public ItemDCFoods(int max, boolean f) {
		super(f);
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 焼きリンゴ
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 4;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.6F;
	}

}
