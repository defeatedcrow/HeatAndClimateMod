package defeatedcrow.hac.main.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.achievement.AcvHelper;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory craftMatrix = event.craftMatrix;
		ItemStack craft = event.crafting;

		// achievement
		if (craft != null) {
			if (player != null) {
				if (craft.getItem() == DCInit.climate_checker) {
					AcvHelper.addClimateAcievement(player, AchievementClimate.CLIMATE_CHECKER);
				} else if (craft.getItem() == MainInit.materials && craft.getItemDamage() == 5) {
					AcvHelper.addMachineAcievement(player, AchievementClimate.MACHINE_GEAR);
				} else if (craft.getItem() == MagicInit.daggerSilver) {
					AcvHelper.addMagicAcievement(player, AchievementClimate.MAGIC_KNIFE);
				}
			}
		}
	}

}
