package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.api.material.ITierItem;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class ItemAxeDC extends AxeItem implements IItemDC, ITierItem {

	public final TierDC tier;
	final TagKey<Item> tag;

	public ItemAxeDC(TierDC tierIn, TagKey<Item> pair) {
		super(tierIn, tierIn.getAttackDamageBonus(), -3.0F, new Item.Properties().durability(tierIn.getUses()).tab(CoreInit.MACHINE));
		tier = tierIn;
		tag = pair;
	}

	@Override
	public TagKey<Item> getPairTag() {
		return tag;
	}

	@Override
	public TierDC getTier() {
		return tier;
	}

}
