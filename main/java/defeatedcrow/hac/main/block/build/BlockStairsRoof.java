package defeatedcrow.hac.main.block.build;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockStairsRoof extends BlockStairsBase implements ITileEntityProvider {

	public BlockStairsRoof(IBlockState state, boolean glass, boolean force) {
		super(state, "build/build_plaster", false, false);
		this.setHardness(0.5F);
		this.setResistance(15.0F);
		this.hasTileEntity = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileStairsRoof();
	}

	protected boolean isInvalidNeighbor(World worldIn, BlockPos pos, EnumFacing facing) {
		return worldIn.getBlockState(pos.offset(facing)).getMaterial() == Material.CACTUS;
	}

	protected boolean hasInvalidNeighbor(World worldIn, BlockPos pos) {
		return this.isInvalidNeighbor(worldIn, pos, EnumFacing.NORTH) || this
				.isInvalidNeighbor(worldIn, pos, EnumFacing.SOUTH) || this
						.isInvalidNeighbor(worldIn, pos, EnumFacing.WEST) || this
								.isInvalidNeighbor(worldIn, pos, EnumFacing.EAST);
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

}
