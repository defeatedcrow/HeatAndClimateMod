package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.core.base.DCTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWindVane extends DCTileBlock {

	public BlockWindVane(String s) {
		super(Material.GROUND, s, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileWindVane();
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN;
	}

	// // コンパレーター出力
	// @Override
	// public boolean hasComparatorInputOverride(IBlockState state) {
	// return true;
	// }
	//
	// @Override
	// public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
	// TileEntity tile = worldIn.getTileEntity(pos);
	// if (tile instanceof TileWindVane) {
	// return getAirflow((TileWindVane) tile);
	// }
	// return 0;
	// }

	private int getAirflow(TileWindVane tile) {
		if (tile.getClimate() != null) {
			DCAirflow a = tile.getClimate().getAirflow();
			return a.getID() * 5;
		}
		return 0;
	}

}
