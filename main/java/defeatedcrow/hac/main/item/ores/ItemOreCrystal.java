package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.item.ItemStack;

public class ItemOreCrystal extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"red_1",
			"green_1",
			"blue_1",
			"white_1",
			"black_1",
			"red_2",
			"green_2",
			"blue_2",
			"white_2",
			"black_2",
			"red_3",
			"green_3",
			"blue_3",
			"white_3",
			"black_3"
	};

	public ItemOreCrystal(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * Tier1
	 * 0: 銅
	 * 1: 錫石
	 * 2: 閃亜鉛鉱
	 * 3: 黃鉄鋼
	 * 4: オイルシェール
	 * Tier2
	 * 5: ボーキサイト
	 * 6: 珪ニッケル
	 * 7: 方鉛鉱
	 * 8: 金
	 * 9: 磁鉄鉱
	 * Tier3
	 * 10: 菱マンガン
	 * 11: クロム
	 * 12: ビスマス
	 * 13: ルチル
	 * 14: 銀
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/ores/oreitem_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
		if (i == 4)
			return 1600;
		else
			return 0;
	}

}
