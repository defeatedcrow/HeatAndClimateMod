package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.magic.PictureList;
import defeatedcrow.hac.main.api.DimCoord;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TilePictureBase extends DCTileEntity {

	@Override
	public void updateTile() {
		if (getColor() != null && getColor() != MagicColor.NONE) {
			DimCoord p1 = new DimCoord(getWorld().provider.getDimension(), pos);
			if (!PictureList.INSTANCE.colorMap.containsKey(p1)) {
				PictureList.INSTANCE.colorMap.put(p1, getColor());
			}
		}
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (getColor() != null && getColor() != MagicColor.NONE) {
			DimCoord p1 = new DimCoord(getWorld().provider.getDimension(), pos);
			if (!PictureList.INSTANCE.colorMap.containsKey(p1)) {
				PictureList.INSTANCE.colorMap.put(p1, getColor());
			}
		}
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		DimCoord p1 = new DimCoord(getWorld().provider.getDimension(), pos);
		PictureList.INSTANCE.colorMap.remove(p1);
	}

	protected abstract MagicColor getColor();

	/* Packet,NBT */

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}
}
