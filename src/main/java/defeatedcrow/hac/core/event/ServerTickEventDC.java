package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.magic.MagicUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ServerTickEventDC {

	private static int lastSec = 0;

	@SubscribeEvent
	public static void onTickEvent(TickEvent.LevelTickEvent event) {
		if (event.level != null) {
			if (!event.level.isClientSide && event.side == LogicalSide.SERVER && event.level instanceof ServerLevel server) {
				ResourceLocation dim = event.level.dimension().registry();
				int time = DCTimeHelper.realSecond();
				if (time % 3 == 0) {
					// 3秒ごと
					if (time != lastSec) {
						lastSec = time;
						WeatherChecker.INSTANCE.setWeather(server);
						MagicPictureEvent.setPictureList(MagicUtil.getPictureEntity(server));
					}
				}
			}
		}
	}

}
