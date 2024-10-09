package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.machine.client.gui.EnergyBatteryMenu;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.EnergyMachineBaseDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyMiddleBatteryTile extends EnergyMachineBaseDC {

	public EnergyMiddleBatteryTile(BlockPos pos, BlockState state) {
		super(MachineInit.BATTERY_MIDDLE_TILE.get(), pos, state);
	}

	public SidedEnergyTankDC battery = new SidedEnergyTankDC(this, getMaxEnergy(), 128);

	protected int getMaxEnergy() {
		return 128000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new EnergyBatteryMenu(MachineInit.BATTERY_MENU.get(), i, inv, this, this.baseDataAccess);
	}

}
