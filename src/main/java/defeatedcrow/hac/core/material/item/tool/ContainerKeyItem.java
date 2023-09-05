package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ContainerKeyItem extends MaterialItemDC {

	public ContainerKeyItem(String s, TagKey<Item> pair) {
		super(CoreInit.MACHINE, s, pair);
	}

}
