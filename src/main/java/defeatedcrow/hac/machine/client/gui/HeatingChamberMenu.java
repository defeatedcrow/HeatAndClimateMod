package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.core.client.gui.FuelItemSlot;
import defeatedcrow.hac.core.client.gui.MachineResultSlot;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.HeatingChamberTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class HeatingChamberMenu extends AbstractContainerMenu {

	private final HeatingChamberTile container;
	public final boolean isOwner;
	private final ContainerData data;

	private int h1 = 26;
	private int h2 = 100;
	private int h3 = 158;

	public HeatingChamberTile getContainer() {
		return container;
	}

	public static HeatingChamberMenu getMenu(int i, Inventory playerInv, HeatingChamberTile cont) {
		return new HeatingChamberMenu(MachineInit.CHAMBER_MENU.get(), i, playerInv, cont, new SimpleContainerData(2));
	}

	public HeatingChamberMenu(MenuType<?> type, int s, Inventory playerInv, HeatingChamberTile cont, ContainerData d) {
		super(type, s);
		checkContainerSize(cont, 2);
		checkContainerDataCount(d, 2);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		data = d;

		if (cont.canOpen(playerInv.player)) {
			this.addSlot(new FuelItemSlot(cont, 0, 60, 62));
			this.addSlot(new MachineResultSlot(cont, 1, 100, 62));

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
			if (s > 1) {
				if (net.minecraftforge.common.ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0) {
					if (!this.moveItemStackTo(stack, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else {
				if (!this.moveItemStackTo(check, 2, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			}

			if (check.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
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

	public int getBurnProgress() {
		int i = this.data.get(0);
		int j = this.data.get(1);
		if (j == 0 || i == 0)
			return 0;
		int ret = i * 14 / j;
		if (i > 0 && ret == 0)
			return 1;
		return ret;
	}

	public int getTempID() {
		return container.getHeatTier().getID();
	}

	public int getAirID() {
		return container.clientClimate.getAirflow().getID();
	}

}
