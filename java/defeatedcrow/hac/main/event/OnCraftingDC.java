package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory matrix = event.craftMatrix;
		ItemStack craft = event.crafting;

		if (craft != null && matrix != null) {
			if (craft.getItem() instanceof ItemArmor) {
				int count = 0;
				for (int i = 0; i < matrix.getSizeInventory(); i++) {
					ItemStack check = matrix.getStackInSlot(i);
					if (!DCUtil.isEmpty(check)) {
						int[] ids = OreDictionary.getOreIDs(check);
					}
				}
			}
		}
	}

}
