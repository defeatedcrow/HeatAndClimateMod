package defeatedcrow.hac.main.client.gui;

import defeatedcrow.hac.core.client.base.ContainerBaseDC;
import defeatedcrow.hac.main.block.build.TileLowChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerLowChest extends ContainerBaseDC {

	public final TileLowChest tile;
	public final InventoryPlayer playerInv;

	public ContainerLowChest(TileLowChest chest, EntityPlayer player) {
		this.tile = chest;
		this.playerInv = player.inventory;
		chest.openInventory(player);

		int count = 6;

		for (int k = 0; k < count; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new SlotLowChest(chest, i1 + k * 9, 8 + i1 * 18, 18 + k * 18));
			}
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 31 + k * 18 + count * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 89 + count * 18));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tile.isUseableByPlayer(playerIn);
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.tile.closeInventory(player);
	}

	@Override
	protected int inputMinIndex() {
		return 0;
	}

	@Override
	protected int inputMaxIndex() {
		return tile.getSizeInventory();
	}

	@Override
	protected int slotIndex() {
		return tile.getSizeInventory();
	}

}
