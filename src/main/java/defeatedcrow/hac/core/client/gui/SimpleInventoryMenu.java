package defeatedcrow.hac.core.client.gui;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.building.SimpleChestDC;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SimpleInventoryMenu extends AbstractContainerMenu {

	private final SimpleChestDC container;
	private final int row;
	public final boolean isOwner;

	private int h1 = 26;
	private int h2 = 112;
	private int h3 = 170;

	public SimpleChestDC getContainer() {
		return container;
	}

	public static SimpleInventoryMenu singleMenu(int i, Inventory playerInv, SimpleChestDC cont) {
		return new SimpleInventoryMenu(CoreInit.SIMPLE_SINGLE.get(), i, playerInv, cont, 3);
	}

	public static SimpleInventoryMenu doubleMenu(int i, Inventory playerInv, SimpleChestDC cont) {
		return new SimpleInventoryMenu(CoreInit.SIMPLE_DOUBLE.get(), i, playerInv, cont, 6);
	}

	public static SimpleInventoryMenu unlockedMenu(int i, Inventory playerInv, SimpleChestDC cont) {
		return new SimpleInventoryMenu(CoreInit.UNLOCKED_DOUBLE.get(), i, playerInv, cont, 6);
	}

	public SimpleInventoryMenu(MenuType<?> type, int s, Inventory playerInv, SimpleChestDC cont, int rows) {
		super(type, s);
		checkContainerSize(cont, rows * 9);
		container = cont;
		row = rows;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);

		if (cont.canOpen(playerInv.player)) {
			int i = (row - 4) * 18;

			for (int j = 0; j < row; ++j) {
				for (int k = 0; k < 9; ++k) {
					this.addSlot(new Slot(container, k + j * 9, 8 + k * 18, h1 + j * 18));
				}
			}

			for (int l = 0; l < 3; ++l) {
				for (int j1 = 0; j1 < 9; ++j1) {
					this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, h2 + l * 18 + i));
				}
			}

			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, h3 + i));
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
			if (s < row * 9) {
				if (!this.moveItemStackTo(check, row * 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(check, 0, row * 9, false)) {
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
