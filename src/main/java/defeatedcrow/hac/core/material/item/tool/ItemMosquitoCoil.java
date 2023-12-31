package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemMosquitoCoil extends MaterialItemDC {

	public ItemMosquitoCoil(String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.MACHINE).defaultDurability(8).craftRemainder(CoreInit.MOSQUITO_COIL.get()), s, pair);
	}

}
