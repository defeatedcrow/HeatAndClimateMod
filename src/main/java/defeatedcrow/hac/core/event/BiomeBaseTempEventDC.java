package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.event.DCBiomeTempEvent;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BiomeBaseTempEventDC {

	@SubscribeEvent
	public static void onTemp(DCBiomeTempEvent event) {
		if (event.biome != null && DCTimeHelper.currentSeason != null) {
			Biome biome = event.biome;
			float temp = event.defaultTemp;
			float offset = (float) CoreConfigDC.seasonEffects[DCTimeHelper.currentSeason.id];

			// 凍結回避
			if (temp == 0.5F) {
				temp += 0.05F;
			}

			event.newTemp = temp + offset;
			if (event.newTemp != event.defaultTemp) {
				event.setResult(Result.ALLOW);
			}
		}
	}

}
