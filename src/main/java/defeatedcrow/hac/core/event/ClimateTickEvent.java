package defeatedcrow.hac.core.event;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.BlockSet;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ClimateTickEvent {

	private static int lastSec = 0;

	@SubscribeEvent
	public static void onTickEvent(TickEvent.LevelTickEvent event) {
		if (event.level != null) {
			ResourceLocation dim = event.level.dimension().registry();
			int time = DCTimeHelper.realSecond();
			if (time != lastSec) {
				lastSec = time;

				// 15秒ごと
				if (!event.level.isClientSide && event.side == LogicalSide.SERVER) {
					if (time % 15 == 0) {
						WeatherChecker.INSTANCE.setWeather(event.level);
						BlockSet cp = new BlockSet(Blocks.CAMPFIRE, "lit", Lists.newArrayList("true"));
					}
				}
			}
		}
	}

	private static int i = 10;

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.side == LogicalSide.CLIENT) {
			if (i <= 0) {
				i = 10;
				ClimateCore.proxy.updatePlayerClimate();
			} else {
				i--;
			}

		}
	}

}
