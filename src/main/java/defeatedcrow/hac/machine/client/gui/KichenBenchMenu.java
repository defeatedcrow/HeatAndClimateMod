package defeatedcrow.hac.machine.client.gui;

import java.util.Optional;

import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.KichenBenchTile;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class KichenBenchMenu extends RecipeBookMenu<CraftingContainer> {

	private final CraftingContainer craftSlots = new CraftingContainer(this, 3, 3);
	private final ResultContainer resultSlots = new ResultContainer();
	private final ContainerLevelAccess access;
	private final Player player;
	private final KichenBenchTile container;
	public final boolean isOwner;

	private int h1 = 92;
	private int h2 = 148;
	private int h3 = 206;

	public KichenBenchTile getContainer() {
		return container;
	}

	public static KichenBenchMenu getMenu(int i, Inventory playerInv, KichenBenchTile cont) {
		return new KichenBenchMenu(MachineInit.KICHEN_BENCH_MENU.get(), i, playerInv, cont, ContainerLevelAccess.NULL);
	}

	public KichenBenchMenu(MenuType<?> type, int s, Inventory playerInv, KichenBenchTile cont, ContainerLevelAccess accessIn) {
		super(type, s);
		container = cont;
		access = accessIn;
		player = playerInv.player;
		container.startOpen(playerInv.player);
		isOwner = cont.isOwner(playerInv.player);

		if (cont.canOpen(playerInv.player)) {

			// 0
			this.addSlot(new ResultSlot(playerInv.player, this.craftSlots, this.resultSlots, 0, 120, 47));

			// 1-9
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					this.addSlot(new Slot(this.craftSlots, j + i * 3, 36 + j * 18, 29 + i * 18));
				}
			}

			// 10-27
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 9; ++k) {
					this.addSlot(new Slot(container, k + j * 9, 8 + k * 18, h1 + j * 18));
				}
			}

			// 28-63
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

	protected static void slotChangedCraftingGrid(AbstractContainerMenu menu, Level level, Player playerIn, CraftingContainer cont, ResultContainer slot) {
		if (!level.isClientSide) {
			ServerPlayer serverplayer = (ServerPlayer) playerIn;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<CraftingRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, cont, level);
			if (optional.isPresent()) {
				CraftingRecipe craftingrecipe = optional.get();
				if (slot.setRecipeUsed(level, serverplayer, craftingrecipe)) {
					itemstack = craftingrecipe.assemble(cont);
				}
			}

			slot.setItem(0, itemstack);
			menu.setRemoteSlot(0, itemstack);
			serverplayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, itemstack));
		}
	}

	@Override
	public void slotsChanged(Container container) {
		this.access.execute((level, pos) -> {
			slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots);
		});
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedContents contents) {
		this.craftSlots.fillStackedContents(contents);
		// for (ItemStack itemstack : this.container.inventory.inv) {
		// contents.accountSimpleStack(itemstack);
		// }
	}

	@Override
	public void clearCraftingContent() {
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}

	@Override
	public boolean recipeMatches(Recipe<? super CraftingContainer> recipe) {
		return recipe.matches(this.craftSlots, this.player.level);
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		this.access.execute((level, pos) -> {
			this.clearContainer(playerIn, this.craftSlots);
		});
		this.container.stopOpen(player);
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return container.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int s) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(s);
		if (slot != null && slot.hasItem()) {
			ItemStack check = slot.getItem();
			stack = check.copy();
			if (s == 0) {
				this.access.execute((level, pos) -> {
					check.getItem().onCraftedBy(check, level, playerIn);
				});
				if (!this.moveItemStackTo(check, 28, 63, true)) {
					return ItemStack.EMPTY;
				}
				slot.onQuickCraft(check, stack);
			} else if (s >= 10 && s < 63) {
				if (!this.moveItemStackTo(check, 1, 10, false)) {
					if (s < 28) {
						if (!this.moveItemStackTo(check, 28, 63, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.moveItemStackTo(check, 10, 27, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.moveItemStackTo(check, 28, 63, false)) {
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

			slot.onTake(playerIn, check);
			if (s == 0) {
				playerIn.drop(check, false);
			}
		}

		return stack;
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public int getResultSlotIndex() {
		return 0;
	}

	@Override
	public int getGridWidth() {
		return this.craftSlots.getWidth();
	}

	@Override
	public int getGridHeight() {
		return this.craftSlots.getHeight();
	}

	@Override
	public int getSize() {
		return 10;
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.CRAFTING;
	}

	@Override
	public boolean shouldMoveToInventory(int slotId) {
		return slotId != this.getResultSlotIndex();
	}

}
