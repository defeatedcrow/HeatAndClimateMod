package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.item.ItemStack;

public class MoldItem {

	public final ItemStack mold;
	public final IPressMold rec;

	public MoldItem(ItemStack i, IPressMold m) {
		mold = i;
		rec = m;
	}

}
