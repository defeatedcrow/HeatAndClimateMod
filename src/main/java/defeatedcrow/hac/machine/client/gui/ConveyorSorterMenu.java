package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSortingTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ConveyorSorterMenu extends AbstractContainerMenu {
	private final ConveyorSortingTile container;
	private final ContainerData data;

	public final boolean isOwner;
	private int h1 = 25;
	private int h2 = 100;
	private int h3 = 158;

	public ConveyorSortingTile getContainer() {
		return container;
	}

	public static ConveyorSorterMenu getMenu(int i, Inventory playerInv, ConveyorSortingTile cont) {
		return new ConveyorSorterMenu(MachineInit.CONVEYOR_SORTER_MENU.get(), i, playerInv, cont, new SimpleContainerData(3));
	}

	public ConveyorSorterMenu(MenuType<?> type, int s, Inventory playerInv, ConveyorSortingTile cont, ContainerData d) {
		super(type, s);
		checkContainerSize(cont, 3);
		checkContainerDataCount(d, 3);
		container = cont;
		data = d;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);

		if (cont.canOpen(playerInv.player)) {

			this.addSlot(new Slot(container, 1, 51, 25));
			this.addSlot(new Slot(container, 2, 15, 55));
			this.addSlot(new Slot(container, 3, 145, 55));

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
			if (s < 3) {
				if (!this.moveItemStackTo(check, 3, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(check, 0, 2, false)) {
				return ItemStack.EMPTY;
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

	public ConveyorSortingTile.SortingType getFrontSortingType() {
		int i = this.data.get(0);
		return ConveyorSortingTile.SortingType.fronInt(i);
	}

	public ConveyorSortingTile.SortingType getLeftSortingType() {
		int i = this.data.get(1);
		return ConveyorSortingTile.SortingType.fronInt(i);
	}

	public ConveyorSortingTile.SortingType getRightSortingType() {
		int i = this.data.get(2);
		return ConveyorSortingTile.SortingType.fronInt(i);
	}
}
