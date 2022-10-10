package defeatedcrow.hac.core;

import defeatedcrow.hac.core.client.DCTextureStitch;
import defeatedcrow.hac.core.climate.ClientClimateData;
import defeatedcrow.hac.core.climate.ClimateHUDEvent;
import defeatedcrow.hac.core.event.ClientTickEventDC;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxyDC extends CommonProxyDC {

	@Override
	public void registerEvent() {
		super.registerEvent();
		MinecraftForge.EVENT_BUS.addListener(ClientTickEventDC::onClientTick);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::render);
		MinecraftForge.EVENT_BUS.addListener(DCTextureStitch::register);
	}

	@Override
	public void updatePlayerClimate() {
		ClientClimateData.INSTANCE.updatePlayerClimate();
	}
}
