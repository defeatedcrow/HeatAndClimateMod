package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.core.client.gui.MachineResultSlot;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.PortableFluidTankTile;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class PortableTankMenu extends AbstractContainerMenu {

	private final PortableFluidTankTile container;
	public final boolean isOwner;
	public final boolean isLarge;

	private int h1 = 72;
	private int h2 = 100;
	private int h3 = 158;

	public PortableFluidTankTile getContainer() {
		return container;
	}

	public static PortableTankMenu getMenu(int i, Inventory playerInv, PortableFluidTankTile cont) {
		return new PortableTankMenu(MachineInit.FLUID_MENU.get(), i, playerInv, cont, false);
	}

	public static PortableTankMenu getLargeMenu(int i, Inventory playerInv, PortableFluidTankTile cont) {
		return new PortableTankMenu(MachineInit.FLUID_MENU_LARGE.get(), i, playerInv, cont, true);
	}

	public PortableTankMenu(MenuType<?> type, int s, Inventory playerInv, PortableFluidTankTile cont, boolean large) {
		super(type, s);
		checkContainerSize(cont, 2);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		isLarge = large;

		if (cont.canOpen(playerInv.player)) {
			if (isLarge) {
				h1 = 58;
			}
			this.addSlot(new Slot(cont, 0, h1, 27));
			this.addSlot(new MachineResultSlot(cont, 1, h1, 53));

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
			if (s > 1) {
				// if (FluidUtil.getFluidHandler(check).isPresent()) {
				if (!this.moveItemStackTo(check, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
				// }
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

	public FluidStack getFluid() {
		return container.tank.getFluid();
	}

	public Component getFluidName() {
		return container.tank.getFluid().isEmpty() ? Component.literal("EMPTY") : container.tank.getFluid().getDisplayName();
	}

	public Component getFluidAmount() {
		return Component.literal(container.tank.getFluidAmount() + " mB");
	}

	public int getFluidGauge() {
		int i = container.tank.getFluidAmount();
		int j = container.tank.getCapacity();
		if (j == 0 || i == 0)
			return 0;
		int ret = i * 40 / j;
		if (i > 0 && ret == 0)
			return 1;
		return ret;
	}

}
