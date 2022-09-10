package defeatedcrow.hac.api.event;

import defeatedcrow.hac.api.climate.DCHeatTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.HasResult;

/**
 * BiomeのHeatTier参照時のイベント。<br>
 * Result.ALLOWの場合、結果を変更できる
 */
@HasResult
public class WorldHeatTierEvent extends Event {

	private final Level world;
	private final BlockPos pos;
	private final DCHeatTier prevTier;
	private DCHeatTier newTier;
	private final EventType type;

	public WorldHeatTierEvent(Level worldIn, BlockPos posIn, DCHeatTier prev, boolean isHeat) {
		this.pos = posIn;
		this.world = worldIn;
		this.prevTier = prev;
		this.newTier = prev;
		type = isHeat ? EventType.HOT : EventType.COLD;
	}

	public DCHeatTier result() {
		MinecraftForge.EVENT_BUS.post(this);
		if (hasResult() && getResult() == Result.ALLOW) {
			return newTier;
		}

		return prevTier;
	}

	public Level getWorld() {
		return world;
	}

	public BlockPos getPos() {
		return pos;
	}

	public DCHeatTier currentClimate() {
		return prevTier;
	}

	public EventType getType() {
		return type;
	}

	public void setNewClimate(DCHeatTier tier) {
		if (tier != null)
			newTier = tier;
	}

	public static enum EventType {
		HOT,
		COLD;
	}
}
