package defeatedcrow.hac.main.item.ores;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public abstract class GemItem extends DCItem {

	protected final int maxMeta;

	public GemItem(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int j = Math.min(stack.getMetadata(), getMaxMeta());
		String name = "item.dcs_gem";
		return getNameSuffix() != null && j < getNameSuffix().length ? name + "_" + getNameSuffix()[j] : name;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/ores/gem_" + getNameSuffix()[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.COMMON;
	}

	public abstract MagicColor getColor(ItemStack stack);
}
