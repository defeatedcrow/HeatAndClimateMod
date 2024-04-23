package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.core.client.gui.MachineResultSlot;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.BoilerBiomassTile;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;

public class BoilerBiomassMenu extends AbstractContainerMenu {

	private final BoilerBiomassTile container;
	public final boolean isOwner;
	private final ContainerData data;

	private int h1 = 26;
	private int h2 = 121;
	private int h3 = 179;

	public BoilerBiomassTile getContainer() {
		return container;
	}

	public static BoilerBiomassMenu getMenu(int i, Inventory playerInv, BoilerBiomassTile cont) {
		return new BoilerBiomassMenu(MachineInit.BOILER_BIOMASS_MENU.get(), i, playerInv, cont, new SimpleContainerData(3));
	}

	public BoilerBiomassMenu(MenuType<?> type, int s, Inventory playerInv, BoilerBiomassTile cont, ContainerData d) {
		super(type, s);
		checkContainerSize(cont, 8);
		checkContainerDataCount(d, 3);
		container = cont;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);
		data = d;

		if (cont.canOpen(playerInv.player)) {

			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 2; ++j) {
					this.addSlot(new Slot(cont, i + j * 3, 13 + j * 18, 46 + i * 18));
				}
			}

			this.addSlot(new MachineResultSlot(cont, 6, 128, 82));

			this.addSlot(new Slot(cont, 7, 128, 28));
			this.addSlot(new MachineResultSlot(cont, 8, 128, 54));

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
			if (s > 8) {
				if (net.minecraftforge.common.ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0) {
					if (!this.moveItemStackTo(check, 0, 5, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else {
				if (!this.moveItemStackTo(check, 9, this.slots.size(), true)) {
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

	public int getPower() {
		return this.data.get(2);
	}

	public int getTempID() {
		return container.getHeatTier().getID();
	}

	public int getAirID() {
		return container.clientClimate.getAirflow().getID();
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
