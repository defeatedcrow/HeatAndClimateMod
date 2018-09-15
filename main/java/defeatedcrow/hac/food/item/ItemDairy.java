package defeatedcrow.hac.food.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFoodItem;

public class ItemDairy extends DCFoodItem {

	private final int maxMeta;

	private static String[] names = {
			"butter",
			"cheese",
			"custard",
			"whip",
			"margarine"
	};

	public ItemDairy() {
		super(false);
		maxMeta = 4;
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

	@Override
	public int getFoodAmo(int meta) {
		switch (meta) {
		case 1:
			return 6;
		default:
			return 2;
		}
	}

	@Override
	public float getSaturation(int meta) {
		return 0.1F;
	}

}
