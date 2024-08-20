package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.machine.energy.SidedEnergyReceiver;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ItemAspiratorTile extends RedstoneMachineBaseDC {

	public ItemAspiratorTile(BlockPos pos, BlockState state) {
		super(MachineInit.ITEM_ASPIRATOR_TILE.get(), pos, state);
	}

	/* battery */

	public SidedEnergyReceiver battery = new SidedEnergyReceiver(this, getMaxEnergy(), 32);

	protected int getMaxEnergy() {
		return 4000;
	}

	@Override
	public SidedEnergyReceiver getEnergyHandler() {
		return battery;
	}

}
