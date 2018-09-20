package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockWallShelf extends BlockLowChest {

	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0.4375D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.5625D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.4375D, 0D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0D, 0D, 0.5625D, 1D, 1D);

	public BlockWallShelf(Material m, String s, boolean b) {
		super(m, s, b);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case EAST:
			return AABB_EAST;
		case NORTH:
			return AABB_NORTH;
		case SOUTH:
			return AABB_SOUTH;
		case WEST:
			return AABB_WEST;
		default:
			return AABB_NORTH;

		}
	}

}
