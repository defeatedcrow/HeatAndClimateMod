package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.magic.PictureList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TilePictureBase extends DCTileEntity {

	@Override
	public void updateTile() {
		if (getColor() != null && getColor() != MagicColor.NONE) {
			if (!PictureList.INSTANCE.colorMap.containsKey(pos)) {
				PictureList.INSTANCE.colorMap.put(pos, getColor());
			}
		}
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (getColor() != null && getColor() != MagicColor.NONE) {
			if (!PictureList.INSTANCE.colorMap.containsKey(pos)) {
				PictureList.INSTANCE.colorMap.put(pos, getColor());
			}
		}
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		PictureList.INSTANCE.colorMap.remove(pos);
	}

	protected abstract MagicColor getColor();

	/* Packet,NBT */

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

}
