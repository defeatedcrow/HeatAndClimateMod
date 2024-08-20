package defeatedcrow.hac.machine.material.block.machine;

import java.util.function.ToIntFunction;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.util.DCState;
import net.minecraft.core.BlockPos;
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

	public static void changeLitState(Level level, BlockPos pos, boolean lit) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof HeatSourceBlock) {
			boolean l = lit && !DCState.getBool(state, DCState.POWERED) && !DCState.getBool(state, WATERLOGGED);
			level.setBlock(pos, state.setValue(DCState.LIT, Boolean.valueOf(l)), 3);
		}
	}

	public static void changeFlagState(Level level, BlockPos pos, boolean flag) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof HeatSourceBlock && flag != DCState.getBool(state, DCState.FLAG)) {
			level.setBlock(pos, state.setValue(DCState.FLAG, Boolean.valueOf(flag)), 3);
		}
	}

	public static ToIntFunction<BlockState> emission(int light) {
		return (state) -> {
			return state.getValue(DCState.LIT) ? DCState.getBool(state, DCState.FLAG) ? light : light / 2 : 0;
		};
	}

}
