package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import defeatedcrow.hac.machine.client.gui.KichenBenchMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.state.BlockState;

public class KichenBenchTile extends OwnableContainerBaseTileDC {

	public KichenBenchTile(BlockPos pos, BlockState state) {
		super(MachineInit.KICHEN_BENCH_TILE.get(), pos, state);
	}

	public InventoryDC inventory = new InventoryDC(this.getContainerSize(), this);

	@Override
	public InventoryDC getInventory() {
		return inventory;
	}

	@Override
	public int getContainerSize() {
		return 18;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.work_bench.with_owner", this.ownerName) : Component.translatable("dcs.container.work_bench");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new KichenBenchMenu(MachineInit.KICHEN_BENCH_MENU.get(), i, inv, this, ContainerLevelAccess.create(level, getBlockPos()));
	}

	protected final int[] singleSlots = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return singleSlots;
	}

}
