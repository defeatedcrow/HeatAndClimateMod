package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.util.DCState;
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
		if (DCState.getBool(state, DCState.LIT)) {
			return 12;
		}
		return 0;
	}

	public static void changeLisState(Level level, BlockPos pos, boolean lit) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof HeatSourceBlock) {
			boolean l = lit & !DCState.getBool(state, DCState.POWERED);
			level.setBlock(pos, state.setValue(DCState.LIT, Boolean.valueOf(l)), 3);
		}
	}

}
