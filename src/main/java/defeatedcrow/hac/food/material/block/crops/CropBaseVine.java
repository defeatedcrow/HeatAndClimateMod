package defeatedcrow.hac.food.material.block.crops;

import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.util.DCState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class CropBaseVine extends ClimateCropBaseBlock {

	protected static final VoxelShape N_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape S_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape U_AABB = Block.box(0.0D, 1.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape D_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public CropBaseVine(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE5, Integer.valueOf(0))
			.setValue(DCState.NORTH, false)
			.setValue(DCState.SOUTH, false)
			.setValue(DCState.EAST, false)
			.setValue(DCState.WEST, false)
			.setValue(DCState.UP, false)
			.setValue(DCState.DOWN, false)
			.setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE5, DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST, DCState.UP, DCState.DOWN, DCState.WILD);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		if (DCState.getBool(state, DCState.UP)) {
			return U_AABB;
		} else {
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
				return U_AABB;
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
	}

	private boolean checkVineSupport(BlockGetter level, BlockPos pos, boolean includeBelow) {
		boolean b1 = !includeBelow;
		boolean b2 = false;
		boolean b3 = false;
		for (Direction dir : Direction.values()) {
			BlockPos p2 = pos.relative(dir);
			BlockState check = level.getBlockState(p2);
			if (dir == Direction.DOWN) {
				if (super.mayPlaceOn(check, level, p2))
					b1 = true;
			} else if (dir == Direction.UP) {
				if (check.getMaterial().isSolid())
					b3 = true;
			} else if (check.getMaterial().isSolid()) {
				b2 = true;
			}
		}
		return b1 || b3 || b2;
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

	public boolean checkFlag(BlockGetter world, BlockPos pos) {
		for (Direction dir : Direction.values()) {
			BlockState check = world.getBlockState(pos.relative(dir));
			if (check.getBlock() == this) {
				return true;
			}
		}
		return false;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		BlockState next = super.getStateForPlacement(cont);
		boolean down = super.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
		boolean up = level.getBlockState(pos.above()).getMaterial().isSolid();
		boolean north = level.getBlockState(pos.north()).getMaterial().isSolid();
		boolean south = level.getBlockState(pos.south()).getMaterial().isSolid();
		boolean east = level.getBlockState(pos.east()).getMaterial().isSolid();
		boolean west = level.getBlockState(pos.west()).getMaterial().isSolid();
		boolean flag = down || checkFlag(level, pos);
		return next.setValue(DCState.DOWN, down).setValue(DCState.UP, up).setValue(DCState.NORTH, north).setValue(DCState.SOUTH, south)
			.setValue(DCState.EAST, east).setValue(DCState.WEST, west);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		BlockState next = state;
		boolean down = super.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
		boolean up = level.getBlockState(pos.above()).getMaterial().isSolid();
		boolean north = level.getBlockState(pos.north()).getMaterial().isSolid();
		boolean south = level.getBlockState(pos.south()).getMaterial().isSolid();
		boolean east = level.getBlockState(pos.east()).getMaterial().isSolid();
		boolean west = level.getBlockState(pos.west()).getMaterial().isSolid();
		boolean flag = down || checkFlag(level, pos);
		next = next.setValue(DCState.DOWN, down).setValue(DCState.UP, up).setValue(DCState.NORTH, north).setValue(DCState.SOUTH, south)
			.setValue(DCState.EAST, east).setValue(DCState.WEST, west);
		return flag || up || north || south || east || west ? next : super.updateShape(state, dir, state2, level, pos, pos2);
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState state) {
		// 伸長
		int stage = DCState.getInt(state, DCState.STAGE5);
		int i = this.getGrowingChance(world, pos, state) * 2;
		if (i > 0 && stage > 1 && world.random.nextInt(i) == 0) {
			boolean b = false;
			if (!DCState.getBool(state, DCState.UP)) {
				// 上に伸びるのを優先する
				BlockState check = world.getBlockState(pos.above());
				if (check.getMaterial() == Material.AIR && checkVineSupport(world, pos.above(), false)) {
					BlockState put = this.updateShape(this.defaultBlockState(), Direction.UP, state, world, pos.above(), pos.above());
					b = world.setBlock(pos.above(), put, 3);
				}
			}
			if (!b && !DCState.getBool(state, DCState.DOWN)) {
				int d = world.random.nextInt(4);
				Direction dir = Direction.from2DDataValue(d);
				BlockState check = world.getBlockState(pos.relative(dir));
				if (check.getMaterial() == Material.AIR && checkVineSupport(world, pos.relative(dir), false)) {
					BlockState put = this.updateShape(this.defaultBlockState(), dir, state, world, pos.relative(dir), pos.relative(dir));
					world.setBlock(pos.relative(dir), this.defaultBlockState().setValue(DCState.STAGE5, 2), 3);
				}
			}
		}
		return super.onGrow(world, pos, state);
	}

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(2)).setValue(DCState.WILD, true);
	}

	@Override
	public boolean isSuitableForGrowing(Level world, BlockPos pos, BlockState state) {
		if (!checkVineSupport(world, pos, true)) {
			return false;
		}
		return super.isSuitableForGrowing(world, pos, state);
	}

	@Override
	public BlockState getHarvestedState(BlockState state) {
		return state.setValue(DCState.STAGE5, 2);
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.VINE;
	}

}
