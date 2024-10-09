package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.client.gui.MachineResultSlot;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.RollCrusherTile;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RollCrusherMenu extends AbstractContainerMenu {

	private final RollCrusherTile container;
	public final boolean isOwner;
	private final ContainerData data;

	private int h1 = 26;
	private int h2 = 130;
	private int h3 = 188;

	public RollCrusherTile getContainer() {
		return container;
	}

	public static RollCrusherMenu getMenu(int i, Inventory playerInv, RollCrusherTile cont) {
		return new RollCrusherMenu(MachineInit.CRUSHER_MENU.get(), i, playerInv, cont, new SimpleContainerData(8));
	}

	public RollCrusherMenu(MenuType<?> type, int s, Inventory playerInv, RollCrusherTile cont, ContainerData d) {
		super(type, s);
		checkContainerSize(cont, 10);
		checkContainerDataCount(d, 8);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		data = d;

		if (cont.canOpen(playerInv.player)) {
			this.addSlot(new Slot(cont, 0, 14, 26));
			this.addSlot(new Slot(cont, 1, 40, 66));

			this.addSlot(new Slot(cont, 2, 126, 35));
			this.addSlot(new MachineResultSlot(cont, 3, 126, 61));

			for (int i2 = 0; i2 < 6; ++i2) {
				this.addSlot(new MachineResultSlot(cont, 4 + i2, 61 + i2 * 18, 90));
			}

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
	public ItemStack quickMoveStack(Player player, int s) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(s);
		if (slot != null && slot.hasItem()) {
			ItemStack check = slot.getItem();
			stack = check.copy();
			if (s > 9) {
				if (!this.moveItemStackTo(check, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(check, 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}

			if (check.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (check.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, check);
		}

		return stack;
	}

	@Override
	public boolean stillValid(Player player) {
		return container.stillValid(player);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.container.stopOpen(player);
	}

	public int getBatteryCount() {
		int i = this.data.get(2);
		int ret = i * 8 / 16000;
		return ret;
	}

	public int getEnergy() {
		return this.data.get(2);
	}

	public int getBurnProgress() {
		int i = this.data.get(0);
		int ret = i * 11 / 640;
		return ret;
	}

	public boolean isRS() {
		return DCState.getBool(container.getBlockState(), DCState.POWERED);
	}

	public boolean errorPrimary() {
		return this.data.get(4) > 0;
	}

	public boolean errorContainer() {
		return this.data.get(3) > 0;
	}

	public boolean errorSecondary() {
		return this.data.get(5) > 0;
	}

	public boolean errorTertiary() {
		return this.data.get(6) > 0;
	}

	public boolean errorFluid() {
		return this.data.get(7) > 0;
	}

	public FluidStack getOutputFluid() {
		return container.outputTank.getFluid();
	}

	public Component getOutputFluidName() {
		return container.outputTank.getFluid().isEmpty() ? Component.literal("EMPTY") : container.outputTank.getFluid().getDisplayName();
	}

	public Component getOutputFluidAmount() {
		return Component.literal(container.outputTank.getFluidAmount() + " mB");
	}

	public int getOutputFluidGauge() {
		int i = container.outputTank.getFluidAmount();
		int j = container.outputTank.getCapacity();
		if (j == 0 || i == 0)
			return 0;
		int ret = i * 40 / j;
		if (i > 0 && ret == 0)
			return 1;
		return ret;
	}

}
