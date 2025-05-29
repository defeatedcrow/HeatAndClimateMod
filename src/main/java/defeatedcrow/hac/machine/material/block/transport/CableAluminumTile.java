package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CableAluminumTile extends CableCopperTile {

	public CableAluminumTile(BlockPos pos, BlockState state) {
		super(MachineInit.CABLE_ALUMINUM_TILE.get(), pos, state);
	}

	public SidedEnergyTankDC battery = new SidedEnergyTankDC(this, getMaxEnergy(), 128).setAllFases(FaceIO.NONE);

	@Override
	protected int getMaxEnergy() {
		return 1280;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

}
