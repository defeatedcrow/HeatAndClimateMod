package defeatedcrow.hac.api.recipe;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class DCBlockUpdateEvent extends Event {

	public final World world;
	public final BlockPos pos;
	public final IBlockState state;
	public final Random rand;

	public DCBlockUpdateEvent(World worldIn, BlockPos posIn, IBlockState stateIn, Random randIn) {
		this.pos = posIn;
		this.world = worldIn;
		this.state = stateIn;
		this.rand = randIn;
	}

	public boolean post() {
		return MinecraftForge.EVENT_BUS.post(this);
	}

}
