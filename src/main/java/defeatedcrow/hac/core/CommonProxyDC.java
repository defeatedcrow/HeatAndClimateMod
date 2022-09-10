package defeatedcrow.hac.core;

import defeatedcrow.hac.core.event.ClimateTickEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxyDC {

	public void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(ClimateTickEvent::onTickEvent);
	}

	public void updatePlayerClimate() {}

}
