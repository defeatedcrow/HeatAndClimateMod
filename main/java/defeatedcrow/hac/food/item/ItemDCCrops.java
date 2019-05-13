package defeatedcrow.hac.food.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFoodItem;

public class ItemDCCrops extends DCFoodItem {

	private final int maxMeta;

	private static String[] names = {
			"rice",
			"onion",
			"spinach",
			"tomato",
			"coffee",
			"cotton",
			"lemon",
			"olive",
			"tea",
			"herb",
			"lotus_seed",
			"mulberry",
			"young_soy",
			"chili",
			"garlic",
			"lettuce",
			"walnut",
			"dates",
			"vine"
	};

	public ItemDCCrops(int max) {
		super(false);
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
		String s = "items/food/crop_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getFoodAmo(int meta) {
		switch (meta) {
		case 3:
		case 17:
			return 5;
		case 10:
		case 11:
		case 15:
			return 3;
		case 1:
		case 2:
		case 4:
		case 6:
			return 2;
		case 18:
			return 0;
		default:
			return 1;
		}
	}

	@Override
	public float getSaturation(int meta) {
		switch (meta) {
		case 18:
			return 0;
		default:
			return 0.25F;
		}
	}

}
