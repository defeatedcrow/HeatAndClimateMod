package defeatedcrow.hac.magic.client.gui;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.client.gui.DisplaySlot;
import defeatedcrow.hac.core.material.block.InventoryItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BlackRodMenu extends AbstractContainerMenu {

	private final ItemStack stack;
	private final InventoryItemDC container;
	private final int selected;

	private int h1 = 41;
	private int h2 = 95;
	private int h3 = 153;

	public ItemStack getStack() {
		return stack;
	}

	public static BlackRodMenu getMenu(int i, ItemStack stack, Player player) {
		InventoryItemDC cont = new InventoryItemDC(player);
		return new BlackRodMenu(MagicInit.BLACK_ROD_MENU.get(), i, stack, cont, player);
	}

	public BlackRodMenu(MenuType<?> type, int s, ItemStack item, InventoryItemDC cont, Player player) {
		super(type, s);
		stack = item.copy();
		container = cont;
		selected = player.getInventory().getSuitableHotbarSlot();

		for (int i = 0; i < 5; ++i) {
			this.addSlot(new CardItemSlot(cont, i, 28 + i * 26, h1));
		}

		for (int l = 0; l < 3; ++l) {
			for (int j1 = 0; j1 < 9; ++j1) {
				int slot = j1 + l * 9 + 9;
				if (slot == selected) {
					this.addSlot(new DisplaySlot(player.getInventory(), j1 + l * 9 + 9, 8 + j1 * 18, h2 + l * 18));
				} else {
					this.addSlot(new Slot(player.getInventory(), j1 + l * 9 + 9, 8 + j1 * 18, h2 + l * 18));
				}
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			if (i1 == selected) {
				this.addSlot(new DisplaySlot(player.getInventory(), i1, 8 + i1 * 18, h3));
			} else {
				this.addSlot(new Slot(player.getInventory(), i1, 8 + i1 * 18, h3));
			}
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int s) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(s);
		int max = container.getContainerSize();
		if (s != selected && slot != null && slot.hasItem()) {
			ItemStack check = slot.getItem();
			stack = check.copy();
			if (s < max) {
				if (!this.moveItemStackTo(check, max, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (slot.mayPlace(stack) && !this.moveItemStackTo(check, 0, max, false)) {
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

	public int getSelectNum() {
		if (!DCUtil.isEmpty(stack) && stack.hasTag()) {
			CompoundTag tag = stack.getTag();
			if (tag.contains(TagKeyDC.COUNTER)) {
				int num = tag.getInt(TagKeyDC.COUNTER);
				return num;
			}
		}
		return 0;
	}

}
