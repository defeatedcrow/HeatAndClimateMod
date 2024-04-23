package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.client.gui.MachineResultSlot;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.StoneMillTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MillMenu extends AbstractContainerMenu {

	private final StoneMillTile container;
	public final boolean isOwner;
	private final ContainerData data;

	private int h1 = 26;
	private int h2 = 100;
	private int h3 = 158;

	public StoneMillTile getContainer() {
		return container;
	}

	public static MillMenu getMenu(int i, Inventory playerInv, StoneMillTile cont) {
		return new MillMenu(MachineInit.MILL_MENU.get(), i, playerInv, cont, new SimpleContainerData(3));
	}

	public MillMenu(MenuType<?> type, int s, Inventory playerInv, StoneMillTile cont, ContainerData d) {
		super(type, s);
		checkContainerSize(cont, 5);
		checkContainerDataCount(d, 3);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		data = d;

		if (cont.canOpen(playerInv.player)) {
			this.addSlot(new Slot(cont, 0, 40, 29));
			this.addSlot(new MachineResultSlot(cont, 1, 106, 36));
			this.addSlot(new MachineResultSlot(cont, 2, 124, 36));
			this.addSlot(new MachineResultSlot(cont, 3, 106, 54));
			this.addSlot(new MachineResultSlot(cont, 4, 124, 54));

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
			if (s > 4) {
				if (!this.moveItemStackTo(check, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(check, 5, this.slots.size(), true)) {
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
		int ret = i * 8 / 4000;
		return ret;
	}

	public int getEnergy() {
		return this.data.get(2);
	}

	public int getBurnProgress() {
		int i = this.data.get(0);
		int ret = i * 11 / 320;
		return ret;
	}

	public boolean isRS() {
		return DCState.getBool(container.getBlockState(), DCState.POWERED);
	}

}
