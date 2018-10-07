package defeatedcrow.hac.main.item.misc;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.item.ItemStack;

public class ItemPetternPaper extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"suit",
			"coat",
			"jacket",
			"hoodie",
			"shirt",
			"pants",
			"skirt",
			"boots",
			"wear"
	};

	public ItemPetternPaper() {
		super();
		maxMeta = 8;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: Modアイコン
	 * 1: 高温ダメージ
	 * 2: 低温ダメージ
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/pattern_paper_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	/* crafting tool */
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() == this) {
			ItemStack copy = stack.copy();
			return copy;
		}
		return stack;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

}
