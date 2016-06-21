package defeatedcrow.hac.main.event;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.hac.main.MainInit;

public class OnMiningEventDC {

	private final Random rand = new Random();

	@SubscribeEvent
	public void preMining(PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer() != null) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = event.getEntityPlayer().inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() == MainInit.pendant) {
					int m = check.getMetadata();
					if (m == 9) {
						hasCharm = true;
					}
				}
			}

			event.setNewSpeed(event.getOriginalSpeed());
			if (hasCharm) {
				event.setNewSpeed(event.getNewSpeed() + 2.0F);
				event.setCanceled(false);
			}
		}
	}

}
