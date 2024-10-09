package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.EnergyMachineBaseDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BatteryMiddleBlock extends BatterySmallBlock {

	public BatteryMiddleBlock(String s) {
		super(s);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EnergyMiddleBatteryTile(pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.BATTERY_MIDDLE_TILE.get(), EnergyMachineBaseDC::serverTick) : null;
	}

}
