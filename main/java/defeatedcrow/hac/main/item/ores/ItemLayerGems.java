package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.item.ItemStack;

public class ItemLayerGems extends DCItem {

	private final int maxMeta;

	/*
	 * 0: 石膏
	 * 1: 岩塩
	 * 2: 硝石
	 * 3: 硫黄
	 * 4: ボーキサイト
	 * 5: ビスマス
	 * 6: アパタイト
	 */
	private static String[] names = { "gypsum", "salt", "niter", "sulfur", "bauxite", "bismuth", "apatite" };

	public ItemLayerGems(int max) {
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
	public String getUnlocalizedName(ItemStack stack) {
		int j = Math.min(stack.getMetadata(), getMaxMeta());
		String name = "item.dcs_gem";
		return getNameSuffix() != null && j < getNameSuffix().length ? name + "_" + getNameSuffix()[j] : name;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/ores/gem_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
