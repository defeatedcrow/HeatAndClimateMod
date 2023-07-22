package defeatedcrow.hac.core.material.block.tile;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ChandelierTile extends BlockEntity implements IRenderBlockData {

	public ChandelierTile(BlockPos pos, BlockState state) {
		super(BuildInit.CHANDELIER_TILE.get(), pos, state);
	}

	@Override
	public AABB getRenderBoundingBox() {
		AABB bb = INFINITE_EXTENT_AABB;
		BlockPos pos = getBlockPos();
		bb = new AABB(pos.offset(-1, -1, -1), pos.offset(2, 2, 2));
		return bb;
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == BuildInit.CHANDELIER_LAMP.get())
			return CHALCEDONY;
		if (block == BuildInit.CHANDELIER_FLUORITE.get())
			return FLUORITE;
		if (block == BuildInit.CHANDELIER_JET.get())
			return JET;
		if (block == BuildInit.CHANDELIER_DESERTROSE.get())
			return DESERTROSE;
		if (block == BuildInit.CHANDELIER_SERPENTINE.get())
			return SERPENTINE;
		if (block == BuildInit.CHANDELIER_IRON.get())
			return IRON;
		return CHALCEDONY;
	}

	public static final EntityRenderData CHALCEDONY = new EntityRenderData("tile/chandelier_crystal", 1.5F, 0.65F);
	public static final EntityRenderData FLUORITE = new EntityRenderData("tile/chandelier_crystal_fluorite", 1.5F, 0.65F);
	public static final EntityRenderData JET = new EntityRenderData("tile/chandelier_crystal_jet", 1.5F, 0.65F);
	public static final EntityRenderData DESERTROSE = new EntityRenderData("tile/chandelier_crystal_desertrose", 1.5F, 0.65F);
	public static final EntityRenderData SERPENTINE = new EntityRenderData("tile/chandelier_crystal_serpentine", 1.5F, 0.65F);

	public static final EntityRenderData IRON = new EntityRenderData("tile/chandelier_iron", 1.5F, 0.65F);

}
