package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ClientTickEventDC {

	private static int i = 10;

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.side == LogicalSide.CLIENT) {
			if (i <= 0) {
				i = 10;
				ClimateCore.proxy.updatePlayerClimate();
			} else {
				i--;
			}

		}
	}

}
