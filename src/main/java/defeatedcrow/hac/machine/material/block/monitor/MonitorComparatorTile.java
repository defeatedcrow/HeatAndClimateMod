package defeatedcrow.hac.machine.material.block.monitor;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class MonitorComparatorTile extends MonitorBaseTile {

	public MonitorComparatorTile(BlockPos pos, BlockState state) {
		super(MachineInit.MONITOR_COMPARATOR_TILE.get(), pos, state);
	}

	@Override
	protected boolean updateAmount() {
		BlockPos p1 = getPairPos();
		if (getPairPos() == null || getPairPos() == BlockPos.ZERO) {
			Direction face = DCState.getFace(getBlockState(), DCState.FACING);
			p1 = getBlockPos().relative(face);
		}
		BlockState target = getLevel().getBlockState(p1);
		int signal = target.getAnalogOutputSignal(getLevel(), p1);
		amount = signal;
		amountMax = 15;
		return amount > 0;
	}

	@Override
	protected boolean updateState() {
		boolean on = MonitorComparatorBlock.changePowerState(getLevel(), getBlockPos(), amount);
		if (on) {
			level.updateNeighbourForOutputSignal(getBlockPos(), getBlockState().getBlock());
		}
		return on;
	}

}
