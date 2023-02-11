package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.event.GetSeasonEvent;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SeasonCheckEventDC {

	@SubscribeEvent
	public static void onTemp(GetSeasonEvent event) {
		if (event.getWorld() != null && event.getPos() != null) {
			Holder<Biome> biome = event.getWorld().getBiome(event.getPos());
			EnumSeason season = event.currentSeason();

			if (biome.is(BiomeTags.IS_NETHER)) {
				season = EnumSeason.SCORCHER;
			} else if (biome.is(BiomeTags.IS_END)) {
				season = EnumSeason.ARCTIC;
			}

			if (event.currentSeason() != season) {
				event.setNewSeason(season);
				event.setResult(Result.ALLOW);
			}
		}
	}

}
