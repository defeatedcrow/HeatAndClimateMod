package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.state.BlockState;

public class LuggageTile extends SimpleChestDC implements IRenderBlockData {

	public LuggageTile(BlockPos pos, BlockState state) {
		super(BuildInit.LUGGAGE_TILE.get(), pos, state);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == BuildInit.LUGGAGE_BLUE.get())
			return BLUE;
		if (block == BuildInit.LUGGAGE_BLACK.get())
			return BLACK;
		if (block == BuildInit.LUGGAGE_RED.get())
			return RED;
		if (block == BuildInit.LUGGAGE_GREEN.get())
			return GREEN;
		if (block == BuildInit.LUGGAGE_WHITE.get())
			return WHITE;
		if (block == BuildInit.LUGGAGE_NORMAL.get())
			return NORMAL;
		return NORMAL;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/luggage_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/luggage_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/luggage_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/luggage_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/luggage_green", 1F, -0.5F);
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/luggage_normal", 1F, -0.5F);

	// anim

	protected final ChestLidController chestLidController = new ChestLidController();

	public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, LuggageTile tile) {
		tile.chestLidController.tickLid();
	}

	@Override
	public float getOpenNess(float f) {
		return this.chestLidController.getOpenness(f);
	}

	@Override
	public boolean triggerEvent(int i1, int i2) {
		if (i1 == 1) {
			this.chestLidController.shouldBeOpen(i2 > 0);
			return true;
		} else {
			return super.triggerEvent(i1, i2);
		}
	}
}
