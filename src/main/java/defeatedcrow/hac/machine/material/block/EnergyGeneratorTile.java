package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.machine.IPowerSource;
import defeatedcrow.hac.machine.client.gui.EnergyBatteryMenu;
import defeatedcrow.hac.machine.energy.SidedEnergySupplyer;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyGeneratorTile extends EnergyMachineBaseDC {

	public EnergyGeneratorTile(BlockPos pos, BlockState state) {
		super(MachineInit.GENERATOR_SMALL_TILE.get(), pos, state);
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		lastRate = currentRate;
		currentRate = 0;
		if (this.isActive(level, pos, state)) {
			for (Direction dir : Direction.values()) {
				BlockPos p2 = this.getBlockPos().relative(dir);
				BlockEntity targetEntity = this.getLevel().getBlockEntity(p2);
				if (targetEntity instanceof IPowerSource source) {
					int ret = source.getSteam();
					ret = source.consumeSteam(ret);
					currentRate += ret;
				}
			}
			if (currentRate > 128) {
				currentRate = 128;
			}
			battery.generateEnergy(currentRate);
		}
		return super.onTickProcess(level, pos, state);
	}

	public SidedEnergyTankDC battery = new SidedEnergySupplyer(this, getMaxEnergy(), 128);

	protected int getMaxEnergy() {
		return 4000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new EnergyBatteryMenu(MachineInit.GENERATOR_MENU.get(), i, inv, this, this.baseDataAccess);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.generator.with_owner", this.ownerName) : Component.translatable("dcs.container.generator");
	}

}
