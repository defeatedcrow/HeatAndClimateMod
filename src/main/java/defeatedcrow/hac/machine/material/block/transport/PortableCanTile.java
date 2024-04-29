package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.machine.client.gui.PortableTankMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PortableCanTile extends PortableFluidTankTile implements IRenderBlockData {

	public PortableCanTile(BlockPos pos, BlockState state) {
		super(MachineInit.PORTABLE_CAN_TILE.get(), pos, state);
	}

	@Override
	public int getTankCap() {
		return 18000;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return PortableTankMenu.getMenu(i, inv, this);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.PORTABLE_CAN_WHITE.get()) {
			return WHITE;
		}
		if (block == MachineInit.PORTABLE_CAN_BLUE.get()) {
			return BLUE;
		}
		if (block == MachineInit.PORTABLE_CAN_BLACK.get()) {
			return BLACK;
		}
		if (block == MachineInit.PORTABLE_CAN_RED.get()) {
			return RED;
		}
		if (block == MachineInit.PORTABLE_CAN_GREEN.get()) {
			return GREEN;
		}
		return NORMAL;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/portable_can_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/portable_can_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/portable_can_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/portable_can_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/portable_can_green", 1F, -0.5F);
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/portable_can_normal", 1F, -0.5F);

}
