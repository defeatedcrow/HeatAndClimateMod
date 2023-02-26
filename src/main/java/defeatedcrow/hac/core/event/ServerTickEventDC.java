package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.climate.WeatherChecker;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ServerTickEventDC {

	private static int lastSec = 0;

	@SubscribeEvent
	public static void onTickEvent(TickEvent.LevelTickEvent event) {
		if (event.level != null) {
			ResourceLocation dim = event.level.dimension().registry();
			int time = DCTimeHelper.realSecond();
			if (time != lastSec) {
				lastSec = time;

				// 10秒ごと
				if (!event.level.isClientSide && event.side == LogicalSide.SERVER) {
					if (time % 10 == 0) {
						WeatherChecker.INSTANCE.setWeather(event.level);
					}
				}
			}
		}
	}

}
