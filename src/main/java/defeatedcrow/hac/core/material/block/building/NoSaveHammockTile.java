package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class NoSaveHammockTile extends NoSaveBedTile {

	public NoSaveHammockTile(BlockPos pos, BlockState state) {
		super(BuildInit.NO_SAVE_HAMMOCK_TILE.get(), pos, state);
	}

	@Override
	public AABB getRenderBoundingBox() {
		return new AABB(getBlockPos().offset(-2.25D, 0D, -2.25D), getBlockPos().offset(2.25D, 1D, 2.25D));
	}
}
