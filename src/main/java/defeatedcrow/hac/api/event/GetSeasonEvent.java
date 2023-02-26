package defeatedcrow.hac.api.event;

import defeatedcrow.hac.api.climate.EnumSeason;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.HasResult;

/**
 * 各BlockのHeatTier参照時に発火するイベント。<br>
 * Result.ALLOWの場合、結果を変更できる
 */
@HasResult
public class GetSeasonEvent extends Event {

	private final Level world;
	private final EnumSeason prevSeason;
	private EnumSeason newSeason;

	public GetSeasonEvent(Level worldIn, EnumSeason prev) {
		this.world = worldIn;
		this.prevSeason = prev;
		this.newSeason = prev;
	}

	public EnumSeason result() {
		MinecraftForge.EVENT_BUS.post(this);
		if (hasResult() && getResult() == Result.ALLOW) {
			return newSeason;
		}

		return prevSeason;
	}

	public Level getWorld() {
		return world;
	}

	public EnumSeason currentSeason() {
		return prevSeason;
	}

	public void setNewSeason(EnumSeason season) {
		if (season != null)
			newSeason = season;
	}
}
