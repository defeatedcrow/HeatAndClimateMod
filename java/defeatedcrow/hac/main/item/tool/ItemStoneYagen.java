package defeatedcrow.hac.main.item.tool;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemStoneYagen extends DCItem {

	public ItemStoneYagen() {
		super();
		this.setMaxDamage(192);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/yagen_iron";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = { "normal" };
		return s;
	}

	/* crafting tool */
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		if (stack != null && stack.getItem() == this) {
			Random rand = Item.itemRand;
			boolean flag = stack.attemptDamageItem(1, rand);
			return flag ? null : stack;
		}
		return null;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

}
