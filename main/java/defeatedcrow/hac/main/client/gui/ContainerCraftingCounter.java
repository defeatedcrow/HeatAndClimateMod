package defeatedcrow.hac.main.client.gui;

import defeatedcrow.hac.main.block.device.TileCraftingCounter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerCraftingCounter extends Container {

	public final TileCraftingCounter tile;
	public final InventoryPlayer playerInv;
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public InventoryCraftResult craftResult = new InventoryCraftResult();
	private final World world;
	private final BlockPos pos;
	private final EntityPlayer player;

	public ContainerCraftingCounter(TileCraftingCounter chest, EntityPlayer playerIn, World worldIn, BlockPos posIn) {
		this.world = worldIn;
		this.pos = posIn;
		this.tile = chest;
		this.playerInv = playerIn.inventory;
		player = playerIn;
		chest.openInventory(player);

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 5; ++i1) {
				this.addSlotToContainer(new Slot(chest, i1 + k * 5, 84 + i1 * 18, 16 + k * 18));
			}
		}

		this.addSlotToContainer(new SlotCrafting(player, this.craftMatrix, this.craftResult, 0, 63, 16));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 9 + j * 18, 16 + i * 18));
			}
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 10 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInv, l, 10 + l * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tile.isUsableByPlayer(playerIn);
	}

	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
	}

	protected void slotChangedCraftingGrid(World world, EntityPlayer player, InventoryCrafting crafting,
			InventoryCraftResult result) {
		if (!world.isRemote) {
			EntityPlayerMP entityplayermp = (EntityPlayerMP) player;
			ItemStack itemstack = ItemStack.EMPTY;
			IRecipe irecipe = CraftingManager.findMatchingRecipe(crafting, world);

			if (irecipe != null && (irecipe.isDynamic() || !world.getGameRules()
					.getBoolean("doLimitedCrafting") || entityplayermp.getRecipeBook().isUnlocked(irecipe))) {
				result.setRecipeUsed(irecipe);
				itemstack = irecipe.getCraftingResult(crafting);
			}

			// 完成品は15番目
			result.setInventorySlotContents(15, itemstack);
			entityplayermp.connection.sendPacket(new SPacketSetSlot(this.windowId, 15, itemstack));
		}
	}

	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if (!this.world.isRemote) {
			this.clearContainer(playerIn, this.world, this.craftMatrix);
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		int lim = tile.getSizeInventory();

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			/*
			 * 0~14: チェスト
			 * 15: 完成品
			 * 16~24: クラフト
			 * 25~51: インベントリ1
			 * 52~60: インベントリ2
			 */

			if (index >= 0 && index < 15) {
				if (!this.mergeItemStack(itemstack1, 16, 24, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index == 15) {
				itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

				if (!this.mergeItemStack(itemstack1, 25, 60, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 16 && index < 25) {
				if (!this.mergeItemStack(itemstack1, 25, 60, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 16, 24, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

			if (index == 0) {
				playerIn.dropItem(itemstack2, false);
			}
		}

		return itemstack;
	}

}
