package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 汎用マシンベースクラス (RedstoneMachineBaseDCに対応)<br>
 * - RSがONになったときに動作する
 */
public abstract class RedstoneMachineBlock extends EntityBlockDC {

	public RedstoneMachineBlock(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.DIRECTION, Direction.UP)
				.setValue(DCState.POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		Direction face = cont.getClickedFace();
		if (cont.getPlayer() != null && cont.getPlayer().isCrouching()) {
			face = cont.getPlayer().getDirection().getOpposite();
		}
		return this.defaultBlockState().setValue(DCState.DIRECTION, face).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
		boolean current = DCState.getBool(state, DCState.POWERED);
		boolean pow = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
		if (!current && pow) {
			level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(true)), 2);
			level.scheduleTick(pos, this, 4);
		} else if (current && !pow) {
			level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(false)), 2);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (triggerOn(state, level, pos, rand)) {
			boolean pow = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
			if (!pow) {
				level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(false)), 2);
			}
		}
	}

	public abstract boolean triggerOn(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand);

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		boolean current = DCState.getBool(state, DCState.POWERED);
		if (!current) {
			level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(true)), 2);
			level.scheduleTick(pos, this, 4);
		}
		return super.use(state, level, pos, player, hand, hitRes);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		return state.setValue(DCState.DIRECTION, rot.rotate(dir));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		return state.setValue(DCState.DIRECTION, mir.mirror(dir));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.DIRECTION, DCState.POWERED, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return false;
	}

	/* BlockDC */

	@Override
	public ToolType getToolType() {
		return ToolType.NONE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	/* Redstone */

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
		return RedstoneMachineBaseDC.getEnergyOutputSignal(level.getBlockEntity(pos));
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
		if (!state.is(state2.getBlock())) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof RedstoneMachineBaseDC) {
				level.updateNeighbourForOutputSignal(pos, state.getBlock());
			}
			super.onRemove(state, level, pos, state2, flag);
		}
	}

}
