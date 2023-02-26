package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.event.GetSeasonEvent;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SeasonCheckEventDC {

	@SubscribeEvent
	public static void onTemp(GetSeasonEvent event) {
		if (event.getWorld() != null) {
			Level level = event.getWorld();
			EnumSeason season = event.currentSeason();

			// if (level.dimension() == Level.NETHER) {
			// season = EnumSeason.SCORCHER;
			// } else if (level.dimension() == Level.END) {
			// season = EnumSeason.ARCTIC;
			// }

			if (event.currentSeason() != season) {
				event.setNewSeason(season);
				event.setResult(Result.ALLOW);
			}
		}
	}

}
