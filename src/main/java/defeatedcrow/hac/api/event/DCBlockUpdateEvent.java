package defeatedcrow.hac.api.event;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

// for Mixin
@Cancelable
public class DCBlockUpdateEvent extends Event {

	public final Level level;
	public final BlockPos pos;
	public final BlockState state;
	public final RandomSource rand;

	public DCBlockUpdateEvent(BlockState stateIn, Level worldIn, BlockPos posIn, RandomSource randIn) {
		this.pos = posIn;
		this.level = worldIn;
		this.state = stateIn;
		this.rand = randIn;
	}

	public boolean post() {
		MinecraftForge.EVENT_BUS.post(this);
		return this.isCanceled();
	}
}
