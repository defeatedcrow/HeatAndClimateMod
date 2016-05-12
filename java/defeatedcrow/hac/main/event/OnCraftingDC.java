package defeatedcrow.hac.main.event;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory craftMatrix = event.craftMatrix;
		ItemStack crafting = event.crafting;
		if (player != null && !player.worldObj.isRemote) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() instanceof ItemMagicalPendant) {
					int m = check.getItemDamage();
					if (m == 8) {
						hasCharm = true;
					}
				}
			}

			int exp = player.worldObj.rand.nextInt(4) + 2;
			if (player.worldObj.rand.nextInt(3) == 0 && hasCharm) {
				player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, player.posX, player.posY, player.posZ, exp));
			}
		}
	}

}
