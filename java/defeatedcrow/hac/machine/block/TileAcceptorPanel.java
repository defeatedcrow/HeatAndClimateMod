package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileAcceptorPanel extends DCTileEntity {

	protected boolean isActive() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockAdapterPanel) {
			boolean flag = DCState.getBool(state, DCState.POWERED);
			return flag;
		}
		return false;
	}

	protected EnumSide getSide() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockAdapterPanel) {
			EnumSide flag = DCState.getSide(state, DCState.SIDE);
			return flag;
		}
		return null;
	}

	protected TileEntity targetTile() {
		if (getSide() != null) {
			EnumFacing face = getSide().face;
			return world.getTileEntity(pos.offset(face));
		}
		return null;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	/* inventory handler */

	@Override
	public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability,
			net.minecraft.util.EnumFacing facing) {
		if (isActive() && targetTile() != null)
			return targetTile().hasCapability(capability, getSide().face.getOpposite());

		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (isActive() && targetTile() != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return targetTile().getCapability(capability, getSide().face.getOpposite());
		return super.getCapability(capability, facing);
	}

}
