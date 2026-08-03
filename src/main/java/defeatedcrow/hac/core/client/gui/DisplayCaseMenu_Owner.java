package defeatedcrow.hac.core.client.gui;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.building.DisplayCaseTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DisplayCaseMenu_Owner extends AbstractContainerMenu {

	private final DisplayCaseTile container;
	public final boolean isOwner;

	private int h1 = 37;
	private int h2 = 100;
	private int h3 = 158;

	public DisplayCaseTile getContainer() {
		return container;
	}

	public static DisplayCaseMenu_Owner getMenu(int i, Inventory playerInv, DisplayCaseTile cont) {
		return new DisplayCaseMenu_Owner(BuildInit.DISPLAY_SHELF_MENU.get(), i, playerInv, cont);
	}

	public DisplayCaseMenu_Owner(MenuType<?> type, int s, Inventory playerInv, DisplayCaseTile cont) {
		super(type, s);
		checkContainerSize(cont, 4);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);

		if (cont.canOpen(playerInv.player)) {

			this.addSlot(new Slot(container, 0, 50, 27));
			this.addSlot(new Slot(container, 1, 100, 27));
			this.addSlot(new Slot(container, 2, 50, 54));
			this.addSlot(new Slot(container, 3, 100, 54));

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
