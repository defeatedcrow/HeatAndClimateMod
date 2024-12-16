package defeatedcrow.hac.core.material.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NoTabBlockItemDC extends BlockItemDC {

	public NoTabBlockItemDC(String regName, Block block, Properties prop, TagKey<Item> pair) {
		super(regName, block, prop, pair);
	}

	@Override
	protected boolean allowedIn(CreativeModeTab tab) {
		return false;
	}

}
