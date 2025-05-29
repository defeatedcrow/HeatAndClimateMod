package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.magic.MagicUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;
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
						MagicPictureEvent.setPictureList(MagicUtil.getPictureEntity(server));

						// Thunderstorm
						if (MagicPictureEvent.getList().stream().anyMatch(MagicPictureEvent.checkColor(MagicColor.RED_BLUE))) {
							LevelData data = server.getLevelData();
							if (data instanceof ServerLevelData sData) {
								if (!sData.isThundering() || sData.getThunderTime() < 2000) {
									sData.setClearWeatherTime(0);
									sData.setThundering(true);
									sData.setRaining(true);
									sData.setThunderTime(12000);
									sData.setRainTime(12000);
								}
							}
						}

						WeatherChecker.INSTANCE.setWeather(server);
					}
				}
			}
		}
	}

}
