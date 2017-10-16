package defeatedcrow.hac.main.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory craftMatrix = event.craftMatrix;
		ItemStack craft = event.crafting;

		// achievement
		if (craft != null) {
			if (player != null) {}
		}
	}

}
