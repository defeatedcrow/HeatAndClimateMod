package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.machine.client.gui.HeatingChamberMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

public class HeatingChamberTile extends HeatSourceTile {

	public HeatingChamberTile(BlockPos pos, BlockState state) {
		super(MachineInit.CHAMBER_IRON_TILE.get(), pos, state);
	}

	public HeatingChamberTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return HeatingChamberTile.this.currentProgress;
			case 1:
				return HeatingChamberTile.this.totalProgress;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				HeatingChamberTile.this.currentProgress = data;
				break;
			case 1:
				HeatingChamberTile.this.totalProgress = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	@Override
	public int getContainerSize() {
		return 2;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 1 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1 };
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new HeatingChamberMenu(MachineInit.CHAMBER_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public boolean isInProcess() {
		return this.totalProgress > 0;
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		if (DCState.getBool(state, ProcessTileBlock.WATERLOGGED))
			return false;
		else if (level.isRainingAt(pos))
			return false;
		return true;
	}

	@Override
	public boolean finishProcess(Level level, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean consumeInputs() {
		this.totalProgress = 0;
		this.currentProgress = 0;
		this.lastProgress = 0;
		return true;
	}

	@Override
	public boolean resetProcess() {
		this.totalProgress = 0;
		this.currentProgress = 0;
		this.lastProgress = 0;
		return true;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		// 灰を排出できるか
		if (!isInProcess() && this.getInventory().canIncrSlot(1, new ItemStack(CoreInit.DUST_ASH.get())) > 0) {
			ItemStack inp = this.getInventory().getItem(0);
			int fuel = getFuel(inp);
			if (fuel > 0) {
				// chamberはスタート時に燃料を消費する
				this.getInventory().getItem(0).split(1);
				this.getInventory().incrStackInSlot(1, new ItemStack(CoreInit.DUST_ASH.get()));
				this.totalProgress += fuel;
				this.setChanged();
				return true;
			}
		}
		return false;
	}

	protected int getFuel(ItemStack item) {
		ItemStack check = item.copy();
		check.setCount(1);
		int fuel = ForgeHooks.getBurnTime(check, RecipeType.SMELTING);
		return fuel / 2;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.chamber.with_owner", this.ownerName) : Component.translatable("dcs.container.chamber");
	}

}
