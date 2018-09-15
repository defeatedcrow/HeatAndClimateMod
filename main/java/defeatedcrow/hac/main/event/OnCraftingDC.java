package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory matrix = event.craftMatrix;
		ItemStack craft = event.crafting;

		if (!DCUtil.isEmpty(craft)) {
			if (craft.getItem() == MagicInit.badge) {
				int count = 0;
				NBTTagCompound tag = new NBTTagCompound();
				for (int i = 0; i < matrix.getSizeInventory(); i++) {
					ItemStack check = matrix.getStackInSlot(i);
					if (OreDictionary.itemMatches(craft, check, true)) {
						NBTTagCompound tag2 = check.getTagCompound();
						if (tag2 != null && tag2.hasKey("dcs.itemdam")) {
							count += tag2.getInteger("dcs.itemdam");
						}
					}
				}
				if (count > 0) {
					tag.setInteger("dcs.itemdam", count);
					craft.setTagCompound(tag);
					DCLogger.infoLog("current coount: " + count);
				}
			}
		}
	}

}
