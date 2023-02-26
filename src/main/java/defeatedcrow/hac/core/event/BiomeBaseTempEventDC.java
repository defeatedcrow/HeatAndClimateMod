package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.event.DCBiomeTempEvent;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BiomeBaseTempEventDC {

	@SubscribeEvent
	public static void onTemp(DCBiomeTempEvent event) {
		if (event.biome != null && DCTimeHelper.staticSeason != null) {
			Biome biome = event.biome;
			float temp = event.defaultTemp;
			float offset = ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(DCTimeHelper.staticSeason);

			// 凍結回避
			if (temp == 0.5F) {
				temp = 0.55F;
			}

			event.newTemp = temp + offset;
			if (event.newTemp != event.defaultTemp) {
				event.setResult(Result.ALLOW);
			}
		}
	}

}
