package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.machine.client.gui.HopperFilterMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class HopperFilterGoldTile extends HopperBaseTile {

	public HopperFilterGoldTile(BlockPos pos, BlockState state) {
		super(MachineInit.HOPPER_FILTER_GOLD_TILE.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 10;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 5, 6, 7, 8, 9 };
	}

	@Override
	protected int[] getFilterSlots() {
		return new int[] { 0, 1, 2, 3, 4 };
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.hopper.with_owner", this.ownerName) : Component.translatable("dcs.container.hopper");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return HopperFilterMenu.filterGoldMenu(i, inv, this);
	}

}
