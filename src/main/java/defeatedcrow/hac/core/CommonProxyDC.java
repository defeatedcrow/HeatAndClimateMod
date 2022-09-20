package defeatedcrow.hac.core;

import defeatedcrow.hac.core.event.BiomeBaseTempEventDC;
import defeatedcrow.hac.core.event.BlockUpdateEventDC;
import defeatedcrow.hac.core.event.LivingTickEventDC;
import defeatedcrow.hac.core.event.ServerTickEventDC;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxyDC {

	public void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(BiomeBaseTempEventDC::onTemp);
		MinecraftForge.EVENT_BUS.addListener(ServerTickEventDC::onTickEvent);
		MinecraftForge.EVENT_BUS.addListener(LivingTickEventDC::onLivingTick);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onCropUpdate);
	}

	public void updatePlayerClimate() {}

}
