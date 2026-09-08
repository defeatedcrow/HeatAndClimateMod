package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.api.material.ITierItem;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;

public class FishingRodItemDC extends FishingRodItem implements IItemDC, ITierItem {

	public final TierDC tier;
	final TagKey<Item> tag;

	public FishingRodItemDC(TierDC tierIn, TagKey<Item> pair) {
		super(new Item.Properties().durability(tierIn.getUses()));
		defeatedcrow.hac.core.material.tabs.CreativeTabDC.add(CoreInit.MACHINE, this);
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
