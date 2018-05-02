package defeatedcrow.hac.main.worldgen;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CaravanGenEvent {

	@SubscribeEvent
	public void populate(PopulateChunkEvent.Post event) {
		if (!event.isHasVillageGenerated() && CaravanGenPos.isSuitableChunk(event.getChunkX(), event.getChunkZ(),
				event.getWorld())) {
			WorldGenCaravanBase wgn = new WorldGenCaravanBase();
			wgn.generate(event.getRand(), event.getChunkX(), event.getChunkZ(), event.getWorld(), event.getGen(),
					event.getWorld().getChunkProvider());
		}
	}

}
