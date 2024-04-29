package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.transport.HopperBaseTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class HopperFilterMenu extends AbstractContainerMenu {
	private final HopperBaseTile container;
	public final boolean isOwner;

	public final boolean isFilter;
	public final boolean isGold;

	private int h1 = 25;
	private int h2 = 100;
	private int h3 = 158;

	public HopperBaseTile getContainer() {
		return container;
	}

	public static HopperFilterMenu filterMenu(int i, Inventory playerInv, HopperBaseTile cont) {
		return new HopperFilterMenu(MachineInit.HOPPER_FILTER_MENU.get(), i, playerInv, cont, true, false);
	}

	public static HopperFilterMenu filterGoldMenu(int i, Inventory playerInv, HopperBaseTile cont) {
		return new HopperFilterMenu(MachineInit.HOPPER_GOLD_MENU.get(), i, playerInv, cont, false, true);
	}

	public static HopperFilterMenu goldMenu(int i, Inventory playerInv, HopperBaseTile cont) {
		return new HopperFilterMenu(MachineInit.HOPPER_FILTER_GOLD_MENU.get(), i, playerInv, cont, true, true);
	}

	public HopperFilterMenu(MenuType<?> type, int s, Inventory playerInv, HopperBaseTile cont, boolean filter, boolean gold) {
		super(type, s);
		checkContainerSize(cont, 10);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		isFilter = filter;
		isGold = gold;

		if (cont.canOpen(playerInv.player)) {

			if (filter) {
				for (int j = 0; j < 2; ++j) {
					for (int k = 0; k < 5; ++k) {
						this.addSlot(new Slot(container, k + j * 5, 44 + k * 18, h1 + j * 29));
					}
				}
			} else {
				for (int j = 0; j < 2; ++j) {
					for (int k = 0; k < 5; ++k) {
						this.addSlot(new Slot(container, k + j * 5, 44 + k * 18, h1 + 6 + j * 18));
					}
				}
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
	}

	@Override
	public ItemStack quickMoveStack(Player player, int s) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(s);
		if (slot != null && slot.hasItem()) {
			ItemStack check = slot.getItem();
			stack = check.copy();
			if (s < 10) {
				if (!this.moveItemStackTo(check, 10, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(check, 0, 9, false)) {
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
}
