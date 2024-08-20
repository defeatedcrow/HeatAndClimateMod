package defeatedcrow.hac.machine.material.block.monitor;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MonitorTempTile extends MonitorBaseTile {

	public MonitorTempTile(BlockPos pos, BlockState state) {
		super(MachineInit.MONITOR_TEMP_TILE.get(), pos, state);
	}

	public int count2 = 4;

	public static void clientTick(Level level, BlockPos pos, BlockState state, MonitorTempTile tile) {
		if (tile.count2 > 0) {
			tile.count2--;
		} else {
			tile.count2 = 4;
			tile.last = tile.amount;
		}
	}

	@Override
	protected boolean updateAmount() {
		BlockPos p1 = getPairPos();
		if (getPairPos() == null || getPairPos() == BlockPos.ZERO) {
			Direction face = DCState.getFace(getBlockState(), DCState.FACING);
			p1 = getBlockPos().relative(face);
		}
		ClimateSupplier sup = new ClimateSupplier(getLevel(), p1);
		int signal = sup.get().getHeat().getID();
		amount = signal;
		amountMax = 13;
		return amount > 0;
	}

	@Override
	protected boolean updateState() {
		boolean on = MonitorTempBlock.changePowerState(getLevel(), getBlockPos(), amount);
		if (on) {
			level.updateNeighbourForOutputSignal(getBlockPos(), getBlockState().getBlock());
		}
		return on;
	}

}
