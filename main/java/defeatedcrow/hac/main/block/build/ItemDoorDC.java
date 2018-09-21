package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;

public class ItemDoorDC extends ItemDoor {

	public ItemDoorDC(Block block) {
		super(block);
		this.setCreativeTab(ClimateMain.build);
	}

}
