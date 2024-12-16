package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChairRoundTile extends BlockEntity implements IRenderBlockData {

	public ChairRoundTile(BlockPos pos, BlockState state) {
		super(BuildInit.CHAIR_ROUND_TILE.get(), pos, state);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == BuildInit.CHAIR_ROUND_WHITE.get())
			return WHITE;
		if (block == BuildInit.CHAIR_ROUND_BLUE.get())
			return BLUE;
		if (block == BuildInit.CHAIR_ROUND_BLACK.get())
			return BLACK;
		if (block == BuildInit.CHAIR_ROUND_RED.get())
			return RED;
		if (block == BuildInit.CHAIR_ROUND_GREEN.get())
			return GREEN;
		return WHITE;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/chair_lab_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/chair_lab_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/chair_lab_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/chair_lab_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/chair_lab_green", 1F, -0.5F);

}
