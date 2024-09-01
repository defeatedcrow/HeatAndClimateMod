package defeatedcrow.hac.food.material.block.crops;

import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.util.DCState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class CropBaseEpiphyte extends ClimateCropBaseBlock {

	protected static final VoxelShape N_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	protected static final VoxelShape S_AABB = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
	protected static final VoxelShape D_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public CropBaseEpiphyte(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE5, Integer.valueOf(0))
				.setValue(DCState.NORTH, false)
				.setValue(DCState.SOUTH, false)
				.setValue(DCState.EAST, false)
				.setValue(DCState.WEST, false)
				.setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE5, DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST, DCState.WILD);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		int i = 0;
		boolean n = false;
		boolean s = false;
		boolean e = false;
		boolean w = false;
		if (DCState.getBool(state, DCState.NORTH)) {
			n = true;
			i++;
		}
		if (DCState.getBool(state, DCState.SOUTH)) {
			s = true;
			i++;
		}
		if (DCState.getBool(state, DCState.EAST)) {
			e = true;
			i++;
		}
		if (DCState.getBool(state, DCState.WEST)) {
			w = true;
			i++;
		}
		if (i > 1) {
			return D_AABB;
		} else {
			if (n)
				return N_AABB;
			if (s)
				return S_AABB;
			if (e)
				return E_AABB;
			if (w)
				return W_AABB;

			return D_AABB;
		}
	}

	private boolean checkVineSupport(BlockGetter level, BlockPos pos, boolean includeBelow) {
		boolean down = !includeBelow || super.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
		boolean north = isSuitableWalls(level.getBlockState(pos.north()));
		boolean south = isSuitableWalls(level.getBlockState(pos.south()));
		boolean east = isSuitableWalls(level.getBlockState(pos.east()));
		boolean west = isSuitableWalls(level.getBlockState(pos.west()));
		return down || north || south || east || west;
	}

	@Override
	public void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		boolean check = checkVineSupport(world, pos, true);

		if (!check) {
			dropResources(state, world, pos);
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
		}
	}

	@Override
	protected boolean mayPlaceOn(BlockState under, BlockGetter level, BlockPos pos) {
		return checkVineSupport(level, pos.above(), true);
	}

	public boolean isSuitableWalls(BlockState state) {
		return state.is(BlockTags.LOGS);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		BlockState next = super.getStateForPlacement(cont);
		boolean north = isSuitableWalls(level.getBlockState(pos.north()));
		boolean south = isSuitableWalls(level.getBlockState(pos.south()));
		boolean east = isSuitableWalls(level.getBlockState(pos.east()));
		boolean west = isSuitableWalls(level.getBlockState(pos.west()));
		return next.setValue(DCState.NORTH, north).setValue(DCState.SOUTH, south)
				.setValue(DCState.EAST, east).setValue(DCState.WEST, west);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		BlockState next = state;
		boolean down = super.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
		boolean north = isSuitableWalls(level.getBlockState(pos.north()));
		boolean south = isSuitableWalls(level.getBlockState(pos.south()));
		boolean east = isSuitableWalls(level.getBlockState(pos.east()));
		boolean west = isSuitableWalls(level.getBlockState(pos.west()));
		next = next.setValue(DCState.NORTH, north).setValue(DCState.SOUTH, south)
				.setValue(DCState.EAST, east).setValue(DCState.WEST, west);
		return down || north || south || east || west ? next : super.updateShape(state, dir, state2, level, pos, pos2);
	}

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(2)).setValue(DCState.WILD, true);
	}

	@Override
	public BlockState getHarvestedState(BlockState state) {
		return state.setValue(DCState.STAGE5, 2);
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.EPIPHYTE;
	}

}
