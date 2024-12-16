package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.core.client.gui.DisplayShelfMenu;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayDoubleShelfTile extends ItemDisplayTile implements IRenderBlockData {

	public DisplayDoubleShelfTile(BlockPos pos, BlockState state) {
		super(BuildInit.DISPLAY_DOUBLE_SHELF_TILE.get(), pos, state);
	}

	@Override
	protected void changeLitState(Level level, BlockPos pos, int lit) {
		DisplayShelfBlock.changeLisState(level, pos, lit);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.display.with_owner", this.ownerName) : Component.translatable("dcs.container.display");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return DisplayShelfMenu.getDoubleMenu(i, inv, this);
	}

	@Override
	public int getContainerSize() {
		return 10;
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == BuildInit.DISPLAY_SHELF_LAB.get()) {
			return LAB;
		}
		if (block == BuildInit.DISPLAY_SHELF_GLASS.get()) {
			return GLASS;
		}
		return IRON;
	}

	public static final EntityRenderData IRON = new EntityRenderData("tile/display_shelf_iron", 1F, -0.5F);
	public static final EntityRenderData LAB = new EntityRenderData("tile/display_shelf_lab", 1F, -0.5F);
	public static final EntityRenderData GLASS = new EntityRenderData("tile/display_shelf_glass", 1F, -0.5F);

}
