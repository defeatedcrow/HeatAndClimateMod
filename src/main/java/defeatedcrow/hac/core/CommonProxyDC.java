package defeatedcrow.hac.core;

import defeatedcrow.hac.core.event.LivingTickEventDC;
import defeatedcrow.hac.core.event.ServerTickEventDC;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxyDC {

	public void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(ServerTickEventDC::onTickEvent);
		MinecraftForge.EVENT_BUS.addListener(LivingTickEventDC::onLivingTick);
	}

	public void updatePlayerClimate() {}

}
