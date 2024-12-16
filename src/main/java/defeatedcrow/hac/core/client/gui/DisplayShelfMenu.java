package defeatedcrow.hac.core.client.gui;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.building.ItemDisplayTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DisplayShelfMenu extends AbstractContainerMenu {

	private final ItemDisplayTile container;
	public final boolean isOwner;

	private int h1 = 37;
	private int h2 = 95;
	private int h3 = 153;

	public ItemDisplayTile getContainer() {
		return container;
	}

	public static DisplayShelfMenu getMenu(int i, Inventory playerInv, ItemDisplayTile cont) {
		return new DisplayShelfMenu(BuildInit.DISPLAY_SHELF_MENU.get(), i, playerInv, cont, false);
	}

	public static DisplayShelfMenu getDoubleMenu(int i, Inventory playerInv, ItemDisplayTile cont) {
		return new DisplayShelfMenu(BuildInit.DISPLAY_DOUBLE_SHELF_MENU.get(), i, playerInv, cont, true);
	}

	public DisplayShelfMenu(MenuType<?> type, int s, Inventory playerInv, ItemDisplayTile cont, boolean d) {
		super(type, s);
		checkContainerSize(cont, d ? 10 : 5);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);

		if (cont.canOpen(playerInv.player)) {

			if (d) {
				h1 = 27;
				for (int i = 0; i < 5; i++) {
					this.addSlot(new Slot(container, i, 44 + i * 18, (i & 1) == 0 ? h1 : h1 + 4));
				}
				for (int i = 5; i < 10; i++) {
					this.addSlot(new Slot(container, i, 44 + i * 18 - 90, (i & 1) == 0 ? h1 + 29 : h1 + 25));
				}
			} else {
				for (int i = 0; i < 5; i++) {
					this.addSlot(new Slot(container, i, 44 + i * 18, (i & 1) == 0 ? h1 : h1 + 8));
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
		int max = container.getContainerSize();
		if (slot != null && slot.hasItem()) {
			ItemStack check = slot.getItem();
			stack = check.copy();
			if (s < max) {
				if (!this.moveItemStackTo(check, max, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(check, 0, max, false)) {
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
