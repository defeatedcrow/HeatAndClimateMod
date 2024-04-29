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

public class IBCTile extends PortableFluidTankTile implements IRenderBlockData {

	public IBCTile(BlockPos pos, BlockState state) {
		super(MachineInit.IBC_TILE.get(), pos, state);
	}

	@Override
	public int getTankCap() {
		return 1000000;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return PortableTankMenu.getLargeMenu(i, inv, this);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		return NORMAL;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/ibc_outer", 1F, 0F);
	public static final EntityRenderData INNER = new EntityRenderData("tile/ibc_inner", 1F, 0F);

}
