package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.main.client.gui.ContainerDisplayCase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TileDisplayCase extends TileDisplayShelf {

	public TileDisplayCase() {
		super();
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerDisplayCase(this, playerIn);
	}

}
