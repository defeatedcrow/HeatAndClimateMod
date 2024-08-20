package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.client.gui.DisplayShelfMenu;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayShelfTile extends ItemDisplayTile {

	public DisplayShelfTile(BlockPos pos, BlockState state) {
		super(BuildInit.DISPLAY_SHELF_TILE.get(), pos, state);
	}

	@Override
	protected void changeLitState(Level level, BlockPos pos, boolean lit) {
		DisplayShelfBlock.changeLisState(level, pos, lit);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.display.with_owner", this.ownerName) : Component.translatable("dcs.container.display");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return DisplayShelfMenu.getMenu(i, inv, this);
	}

}
