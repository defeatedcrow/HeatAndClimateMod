package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class FluidPipeNickelsilverTile extends FluidPipeAlloyTile {

	public FluidPipeNickelsilverTile(BlockPos pos, BlockState state) {
		super(MachineInit.PIPE_NICKELSILVER_TILE.get(), pos, state);
	}

	// caps
	public PipeTank headtank = new PipeTank(2400, 128, this);

	@Override
	public PipeTank getFluidHandler() {
		return headtank;
	}

}
