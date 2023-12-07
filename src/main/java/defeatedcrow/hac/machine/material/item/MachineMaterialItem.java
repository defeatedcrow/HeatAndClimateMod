package defeatedcrow.hac.machine.material.item;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MachineMaterialItem extends MaterialItemDC {

	public MachineMaterialItem(Rarity rare, String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.MACHINE).rarity(rare), s, pair);
		domain = "machine";
	}

}
