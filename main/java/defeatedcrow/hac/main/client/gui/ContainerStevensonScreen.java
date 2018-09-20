package defeatedcrow.hac.main.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;

public class ContainerStevensonScreen extends Container {

	public final TileStevensonScreen tile;
	public final EntityPlayer player;

	private int climateNum;
	private int weatherNum;

	public ContainerStevensonScreen(TileStevensonScreen t, EntityPlayer p) {
		tile = t;
		player = p;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tile != null;
	}

}
