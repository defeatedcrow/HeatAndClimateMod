package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.core.client.gui.MachineResultSlot;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.TeaPotTile;
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

public class TeaPotMenu extends AbstractContainerMenu {

	private final TeaPotTile container;
	public final boolean isOwner;
	private final ContainerData data;

	private int h1 = 65;
	private int h2 = 121;
	private int h3 = 179;

	public TeaPotTile getContainer() {
		return container;
	}

	public static TeaPotMenu getMenu(int i, Inventory playerInv, TeaPotTile cont) {
		return new TeaPotMenu(MachineInit.TEA_POT_MENU.get(), i, playerInv, cont, new SimpleContainerData(2));
	}

	public TeaPotMenu(MenuType<?> type, int s, Inventory playerInv, TeaPotTile cont, ContainerData d) {
		super(type, s);
		checkContainerSize(cont, 7);
		checkContainerDataCount(d, 2);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		data = d;

		if (cont.canOpen(playerInv.player)) {
			this.addSlot(new Slot(cont, 0, h1, 32));
			this.addSlot(new Slot(cont, 1, h1, 32 + 18));
			this.addSlot(new Slot(cont, 2, h1, 32 + 36));

			this.addSlot(new MachineResultSlot(cont, 3, 143, 37));
			this.addSlot(new MachineResultSlot(cont, 4, 143, 59));

			this.addSlot(new Slot(cont, 5, 27, 39));
			this.addSlot(new MachineResultSlot(cont, 6, 27, 65));

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
			if (s > 6) {
				if (!this.moveItemStackTo(check, 0, 3, false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(check, 7, this.slots.size(), true)) {
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

	public FluidStack getInputFluid() {
		return container.inputTank.getFluid();
	}

	public Component getInputFluidName() {
		return container.inputTank.getFluid().isEmpty() ? Component.literal("EMPTY") : container.inputTank.getFluid().getDisplayName();
	}

	public Component getInputFluidAmount() {
		return Component.literal(container.inputTank.getFluidAmount() + " mB");
	}

	public int getInputFluidGauge() {
		int i = container.inputTank.getFluidAmount();
		int j = container.inputTank.getCapacity();
		if (j == 0 || i == 0)
			return 0;
		int ret = i * 40 / j;
		if (i > 0 && ret == 0)
			return 1;
		return ret;
	}

	public int getBurnProgress() {
		int i = this.data.get(0);
		if (i == 0)
			return 0;
		int ret = i * 14 / 90;
		if (i > 0 && ret == 0)
			return 1;
		return ret;
	}

	public int getTempID() {
		return container.clientClimate.getHeat().getID();
	}

	public int getHumID() {
		return container.clientClimate.getHumidity().getID();
	}

	public int getAirID() {
		return container.clientClimate.getAirflow().getID();
	}

}
