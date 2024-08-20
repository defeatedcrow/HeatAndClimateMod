package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolHookTile extends ItemDisplayTile {

	public ToolHookTile(BlockPos pos, BlockState state) {
		super(BuildInit.TOOLHOOK_TILE.get(), pos, state);
	}

	@Override
	protected void changeLitState(Level level, BlockPos pos, boolean lit) {
		ToolHookBlock.changeLisState(level, pos, lit);
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return new int[] { 0 };
	}

	@Override
	protected Component getDefaultName() {
		return null;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

	@Override
	public boolean hasMenu() {
		return false;
	}

}
