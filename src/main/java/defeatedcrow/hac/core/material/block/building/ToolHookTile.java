package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolHookTile extends SingleItemDisplay {

	public ToolHookTile(BlockPos pos, BlockState state) {
		super(BuildInit.TOOLHOOK_TILE.get(), pos, state);
	}

	@Override
	protected void changeLitState(Level level, BlockPos pos, boolean lit) {
		ToolHookBlock.changeLisState(level, pos, lit);
	}

}
