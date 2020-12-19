package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.api.climate.ItemSet;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;

public class ItemGems extends DCItem {

	private final int maxMeta;

	/*
	 * 0: 青カルセドニー
	 * 1: 赤カルセドニー
	 * 2: 白カルセドニー
	 * 3: 石膏
	 * 4: サファイア
	 * 5: マラカイト
	 * 6: セレスタイト
	 * 7: ハマグリ
	 * 8: 岩塩
	 * 9: 硝石
	 * 10: 硫黄
	 * 11: ショール
	 * 12: 蛇紋石
	 * 13: カンラン石
	 * 14: アルマンディン
	 * 15: ルチル
	 * 16: ボーキサイト
	 * 17: ビスマス,
	 * 18: アパタイト,
	 * 19: 翡翠
	 * 20: 月長石
	 * 21: リシア輝石
	 * 22: ブラックオパール
	 * 23: 含油頁岩
	 * 24: チタン酸ストロンチウム
	 */
	private static String[] names = {
		"chal_blue",
		"chal_red",
		"chal_white",
		"gypsum",
		"sapphire",
		"malachite",
		"celestite",
		"clam",
		"salt",
		"niter",
		"sulfur",
		"schorl",
		"serpentine",
		"olivine",
		"almandine",
		"rutile",
		"bauxite",
		"bismuth",
		"apatite",
		"jadeite",
		"moonstone",
		"kunzite",
		"opal",
		"crude_oil",
		"tausonite" };

	public ItemGems(int max) {
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
		String s = "items/ores/gem_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
		if (i == 23)
			return 1600;
		else
			return 0;
	}

	public static ItemSet[] table = {
		new ItemSet(MainInit.gems_blue, 0),
		new ItemSet(MainInit.gems_red, 0),
		new ItemSet(MainInit.gems_white, 0),
		new ItemSet(MainInit.gems_layer, 0),
		new ItemSet(MainInit.gems_blue, 1),
		new ItemSet(MainInit.gems_green, 0),
		new ItemSet(MainInit.gems_white, 2),
		new ItemSet(MainInit.gems_black, 5),
		new ItemSet(MainInit.gems_layer, 1),
		new ItemSet(MainInit.gems_layer, 2),
		new ItemSet(MainInit.gems_layer, 3),
		new ItemSet(MainInit.gems_black, 1),
		new ItemSet(MainInit.gems_green, 1),
		new ItemSet(MainInit.gems_green, 1),
		new ItemSet(MainInit.gems_red, 1),
		new ItemSet(MainInit.gems_white, 1),
		new ItemSet(MainInit.gems_layer, 4),
		new ItemSet(MainInit.gems_layer, 5),
		new ItemSet(MainInit.gems_layer, 6),
		new ItemSet(MainInit.gems_green, 2),
		new ItemSet(MainInit.gems_blue, 2),
		new ItemSet(MainInit.gems_red, 2),
		new ItemSet(MainInit.gems_black, 2),
		new ItemSet(MainInit.gems_black, 0),
		new ItemSet(MainInit.gems_white, 4) };
}
