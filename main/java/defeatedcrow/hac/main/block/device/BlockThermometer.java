package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.core.base.DCTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockThermometer extends DCTileBlock {

	public BlockThermometer(String s) {
		super(Material.GLASS, s, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileThermometer();
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
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
	// if (tile instanceof TileThermometer) {
	// return getHeatTier((TileThermometer) tile);
	// }
	// return 0;
	// }

	private int getHeatTier(TileThermometer tile) {
		if (tile.getClimate() != null) {
			return tile.getClimate().getHeat().getID();
		}
		return 0;
	}

}
