package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.client.gui.DisplayCaseMenu_Owner;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayCaseTile extends ItemDisplayTile {

	public DisplayCaseTile(BlockPos pos, BlockState state) {
		super(BuildInit.DISPLAY_CASE_TILE.get(), pos, state);
	}

	@Override
	protected void changeLitState(Level level, BlockPos pos, int lit) {
		DisplayCaseBlock.changeLitState(level, pos, lit);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.display.with_owner", this.ownerName) : Component.translatable("dcs.container.display");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return DisplayCaseMenu_Owner.getMenu(i, inv, this);
	}

	@Override
	public int getContainerSize() {
		return 4;
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return new int[] { 0, 1, 2, 3 };
	}

}
