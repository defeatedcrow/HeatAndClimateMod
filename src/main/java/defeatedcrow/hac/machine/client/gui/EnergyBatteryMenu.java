package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.EnergyMachineBaseDC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnergyBatteryMenu extends AbstractContainerMenu {

	private final EnergyMachineBaseDC container;
	public final boolean isOwner;
	private final ContainerData data;

	private int h1 = 76;
	private int h2 = 130;
	private int h3 = 188;

	public EnergyMachineBaseDC getContainer() {
		return container;
	}

	public static EnergyBatteryMenu getMenu(int i, Inventory playerInv, EnergyMachineBaseDC cont) {
		return new EnergyBatteryMenu(MachineInit.BATTERY_MENU.get(), i, playerInv, cont, new SimpleContainerData(9));
	}

	public static EnergyBatteryMenu getGeneratorMenu(int i, Inventory playerInv, EnergyMachineBaseDC cont) {
		return new EnergyBatteryMenu(MachineInit.GENERATOR_MENU.get(), i, playerInv, cont, new SimpleContainerData(9));
	}

	public EnergyBatteryMenu(MenuType<?> type, int s, Inventory playerInv, EnergyMachineBaseDC cont, ContainerData d) {
		super(type, s);
		checkContainerDataCount(d, 9);
		container = cont;
		isOwner = cont.isOwner(playerInv.player);
		data = d;

		if (cont.canOpen(playerInv.player)) {

			for (int l = 0; l < 3; ++l) {
				for (int j1 = 0; j1 < 9; ++j1) {
					this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, h2 + l * 18));
				}
			}

			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, h3));
			}
		}

		this.addDataSlots(data);
	}

	@Override
	public boolean stillValid(Player player) {
		return container.canOpen(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int s) {
		ItemStack stack = ItemStack.EMPTY;
		return stack;
	}

	public int getFlow() {
		int i = this.data.get(0);
		int j = this.data.get(1);
		float rate = j + (i - j) * Minecraft.getInstance().getPartialTick();
		return Mth.floor(rate);
	}

	public int getBurnProgress() {
		int i = this.data.get(0);
		int j = this.data.get(1);
		float rate = j + (i - j) * Minecraft.getInstance().getPartialTick();
		rate = rate / this.container.getEnergyHandler().getFlowRate();
		int ret = Mth.floor(rate * 17);
		return ret;
	}

	public int getBatteryCount() {
		return DCState.getInt(container.getBlockState(), DCState.STAGE9);
	}

	public int getFaceIO(int num) {
		return this.data.get(num + 2);
	}

	public int getEnergy() {
		return this.data.get(8);
	}

	public boolean isPowerd() {
		return DCState.getBool(container.getBlockState(), DCState.FLAG);
	}

	public boolean isRS() {
		return DCState.getBool(container.getBlockState(), DCState.POWERED);
	}

	public boolean isActive() {
		return !isPowerd() && !isRS();
	}

}
