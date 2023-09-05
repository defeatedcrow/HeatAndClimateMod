package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class HeatSourceBlock extends ProcessTileBlock implements IHeatTile {

	public HeatSourceBlock(Properties prop) {
		super(prop);
	}

	@Override
	public DCHeatTier getHeatTier(Level level, BlockPos targrt, BlockPos source) {
		BlockEntity entity = level.getBlockEntity(source);
		if (entity instanceof HeatSourceTile tile) {
			return tile.getHeatTier();
		}
		return DCHeatTier.NORMAL;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity instanceof HeatSourceTile tile) {
			int i = tile.getHeatTier().getID() - DCHeatTier.NORMAL.getID();
			return i * 2;
		}
		return 0;
	}

}
