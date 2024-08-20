package defeatedcrow.hac.core.client.gui;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.building.DisplayShelfTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DisplayShelfMenu extends AbstractContainerMenu {

	private final DisplayShelfTile container;
	public final boolean isOwner;

	private int h1 = 37;
	private int h2 = 95;
	private int h3 = 153;

	public DisplayShelfTile getContainer() {
		return container;
	}

	public static DisplayShelfMenu getMenu(int i, Inventory playerInv, DisplayShelfTile cont) {
		return new DisplayShelfMenu(BuildInit.DISPLAY_SHELF_MENU.get(), i, playerInv, cont);
	}

	public DisplayShelfMenu(MenuType<?> type, int s, Inventory playerInv, DisplayShelfTile cont) {
		super(type, s);
		checkContainerSize(cont, 5);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);

		if (cont.canOpen(playerInv.player)) {

			this.addSlot(new Slot(container, 0, 44 + 0 * 18, h1));
			this.addSlot(new Slot(container, 1, 44 + 1 * 18, h1 + 8));
			this.addSlot(new Slot(container, 2, 44 + 2 * 18, h1));
			this.addSlot(new Slot(container, 3, 44 + 3 * 18, h1 + 8));
			this.addSlot(new Slot(container, 4, 44 + 4 * 18, h1));

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
			if (s < 5) {
				if (!this.moveItemStackTo(check, 5, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(check, 0, 5, false)) {
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
