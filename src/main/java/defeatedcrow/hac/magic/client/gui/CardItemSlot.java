package defeatedcrow.hac.magic.client.gui;

import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CardItemSlot extends Slot {

	public CardItemSlot(Container cont, int i1, int i2, int i3) {
		super(cont, i1, i2, i3);
	}

	@Override
	public boolean mayPlace(ItemStack item) {
		return isMagicCard(item);
	}

	protected boolean isMagicCard(ItemStack item) {
		return !DCUtil.isEmpty(item) && item.is(TagDC.ItemTag.MAGIC_CARD);
	}

}
