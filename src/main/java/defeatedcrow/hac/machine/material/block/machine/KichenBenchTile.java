package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class KichenBenchTile extends OwnableContainerBaseTileDC implements IRenderBlockData {

	public KichenBenchTile(BlockPos pos, BlockState state) {
		super(MachineInit.KICHEN_BENCH_TILE.get(), pos, state);
	}

	public KichenBenchTile(BlockEntityType<KichenBenchTile> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
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

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.KICHEN_BENCH_WOOD_A.get())
			return WOOD_A;
		if (block == MachineInit.KICHEN_BENCH_WOOD_B.get())
			return WOOD_B;
		if (block == MachineInit.KICHEN_BENCH_BLACK_A.get())
			return BLACK_A;
		if (block == MachineInit.KICHEN_BENCH_BLACK_B.get())
			return BLACK_B;
		if (block == MachineInit.KICHEN_BENCH_LAB_A.get())
			return LAB_A;
		if (block == MachineInit.KICHEN_BENCH_LAB_B.get())
			return LAB_B;
		return SUS;
	}

	public static final EntityRenderData WOOD_A = new EntityRenderData("tile/kitchen_bench_wood", 1F, -0.5F);
	public static final EntityRenderData WOOD_B = new EntityRenderData("tile/kitchen_bench_wood_b", 1F, -0.5F);
	public static final EntityRenderData BLACK_A = new EntityRenderData("tile/kitchen_bench_black", 1F, -0.5F);
	public static final EntityRenderData BLACK_B = new EntityRenderData("tile/kitchen_bench_black_b", 1F, -0.5F);
	public static final EntityRenderData LAB_A = new EntityRenderData("tile/kitchen_bench_lab", 1F, -0.5F);
	public static final EntityRenderData LAB_B = new EntityRenderData("tile/kitchen_bench_lab_b", 1F, -0.5F);
	public static final EntityRenderData SUS = new EntityRenderData("tile/kitchen_bench_sus", 1F, -0.5F);

}
