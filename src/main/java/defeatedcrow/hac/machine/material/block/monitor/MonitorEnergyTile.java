package defeatedcrow.hac.machine.material.block.monitor;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;

public class MonitorEnergyTile extends MonitorBaseTile {

	public MonitorEnergyTile(BlockPos pos, BlockState state) {
		super(MachineInit.MONITOR_ENERGY_TILE.get(), pos, state);
	}

	public int count2 = 4;

	public static void clientTick(Level level, BlockPos pos, BlockState state, MonitorEnergyTile tile) {
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
		BlockEntity target = getLevel().getBlockEntity(p1);
		IEnergyStorage handler = target.getCapability(ForgeCapabilities.ENERGY).orElseGet(null);
		if (handler != null) {
			int i1 = handler.getEnergyStored();
			int i2 = handler.getMaxEnergyStored();
			amount = Mth.floor(90F * i1 / i2);
			amountMax = 90;
			return amount > 0;
		}
		return false;
	}

	@Override
	protected boolean updateState() {
		int count = Mth.floor(amount * 15F / amountMax);
		if (amount > 0 && count == 0) {
			count = 1;
		}
		boolean on = MonitorEnergyBlock.changePowerState(getLevel(), getBlockPos(), count);
		if (on) {
			level.updateNeighbourForOutputSignal(getBlockPos(), getBlockState().getBlock());
		}
		return on;
	}

}
