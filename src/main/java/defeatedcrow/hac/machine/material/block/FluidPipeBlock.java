package defeatedcrow.hac.machine.material.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileFaceIOToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;

public abstract class FluidPipeBlock extends EntityBlockDC {

	protected static final VoxelShape AABB_MIDDLE = Block.box(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);
	protected static final VoxelShape AABB_N = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
	protected static final VoxelShape AABB_S = Block.box(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);
	protected static final VoxelShape AABB_W = Block.box(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
	protected static final VoxelShape AABB_E = Block.box(6.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	protected static final VoxelShape AABB_D = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
	protected static final VoxelShape AABB_U = Block.box(6.0D, 10.0D, 6.0D, 10.0D, 16.0D, 10.0D);

	public FluidPipeBlock(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(DCState.NORTH, false)
			.setValue(DCState.SOUTH, false)
			.setValue(DCState.EAST, false)
			.setValue(DCState.WEST, false)
			.setValue(DCState.UP, false)
			.setValue(DCState.DOWN, false)
			.setValue(DCState.POWERED, Boolean.valueOf(false))
			.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		boolean pow = cont.getLevel().hasNeighborSignal(cont.getClickedPos());
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		BlockPos p1 = pos.north();
		BlockPos p2 = pos.east();
		BlockPos p3 = pos.south();
		BlockPos p4 = pos.west();
		BlockPos p5 = pos.above();
		BlockPos p6 = pos.below();
		BlockState s1 = level.getBlockState(p1);
		BlockState s2 = level.getBlockState(p2);
		BlockState s3 = level.getBlockState(p3);
		BlockState s4 = level.getBlockState(p4);
		BlockState s5 = level.getBlockState(p5);
		BlockState s6 = level.getBlockState(p6);
		return this.defaultBlockState()
			.setValue(DCState.NORTH, this.connectsTo(s1, level, p1, Direction.NORTH, true))
			.setValue(DCState.EAST, this.connectsTo(s2, level, p2, Direction.EAST, true))
			.setValue(DCState.SOUTH, this.connectsTo(s3, level, p3, Direction.SOUTH, true))
			.setValue(DCState.WEST, this.connectsTo(s4, level, p4, Direction.WEST, true))
			.setValue(DCState.UP, this.connectsTo(s5, level, p5, Direction.UP, true))
			.setValue(DCState.DOWN, this.connectsTo(s6, level, p6, Direction.DOWN, true))
			.setValue(DCState.POWERED, Boolean.valueOf(pow))
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState p_53338_, BlockGetter p_53339_, BlockPos p_53340_) {
		return AABB_MIDDLE;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		if (cont != null && cont.isHoldingItem(CoreInit.SCREWDRIVER.get())) {
			return Shapes.or(AABB_MIDDLE, AABB_N, AABB_S, AABB_W, AABB_E, AABB_D, AABB_U);
		}
		return getCableShape(state);
	}

	private VoxelShape getCableShape(BlockState state) {
		VoxelShape ret = AABB_MIDDLE;
		if (DCState.getBool(state, DCState.NORTH)) {
			ret = Shapes.or(ret, AABB_N);
		}
		if (DCState.getBool(state, DCState.SOUTH)) {
			ret = Shapes.or(ret, AABB_S);
		}
		if (DCState.getBool(state, DCState.WEST)) {
			ret = Shapes.or(ret, AABB_W);
		}
		if (DCState.getBool(state, DCState.EAST)) {
			ret = Shapes.or(ret, AABB_E);
		}
		if (DCState.getBool(state, DCState.DOWN)) {
			ret = Shapes.or(ret, AABB_D);
		}
		if (DCState.getBool(state, DCState.UP)) {
			ret = Shapes.or(ret, AABB_U);
		}
		return ret;
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (dir != null && level.isAreaLoaded(pos, 1)) {
			return s1.setValue(DCState.getFacingProperty(dir), connectsTo(s2, level, pos2, dir.getOpposite(), false));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	public boolean connectsTo(BlockState target, BlockGetter level, BlockPos pos, Direction dir, boolean force) {
		if (target == null)
			return false;
		if (target.getBlock() instanceof AbstractCauldronBlock) {
			return true;
		}
		if (target.is(TagDC.BlockTag.FLUID_PIPE)) {
			BooleanProperty facing = DCState.getFacingProperty(dir);
			return force || DCState.getBool(target, facing);
		}
		BlockEntity tile = level.getBlockEntity(pos);
		return tile != null && tile.getCapability(ForgeCapabilities.FLUID_HANDLER, dir).isPresent();
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity living, ItemStack item) {
		checkConnection(level, pos, state);
		super.setPlacedBy(level, pos, state, living, item);
	}

	public void checkConnection(Level level, BlockPos pos, BlockState state) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (level.isAreaLoaded(pos, 1) && tile instanceof FluidPipeTileBaseDC cable) {
			for (Direction dir : Direction.values()) {
				BlockState target = level.getBlockState(pos.relative(dir));
				BlockEntity targetTile = level.getBlockEntity(pos.relative(dir));
				if (target.getBlock() instanceof FluidPipeBlock targetBlock && targetTile instanceof FluidPipeTileBaseDC targetCable) {
					cable.setFace(dir, FaceIO.PIPE);
					targetCable.setFace(dir.getOpposite(), FaceIO.PIPE);
					BooleanProperty targetFace = DCState.getFacingProperty(dir.getOpposite());
					if (target.hasProperty(targetFace)) {
						BlockState next = target.setValue(targetFace, true);
						level.setBlock(pos.relative(dir), next, 3);
					}
					if (level instanceof ServerLevel) {
						MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), FaceIO.PIPE.getID());
						MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos.relative(dir), dir.getOpposite().get3DDataValue(), FaceIO.PIPE.getID());
					}
				}
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
		if (!level.isClientSide) {
			boolean pow = level.hasNeighborSignal(pos);
			if (pow != DCState.getBool(state, DCState.POWERED)) {
				level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(pow)), 2);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		ItemStack held = player.getItemInHand(hand);
		if (tile instanceof FluidPipeTileBaseDC cable && hitRes != null) {
			double x = hitRes.getLocation().x - hitRes.getBlockPos().getX();
			double y = hitRes.getLocation().y - hitRes.getBlockPos().getY();
			double z = hitRes.getLocation().z - hitRes.getBlockPos().getZ();
			Direction dir = getClickDir(x, y, z);
			if (dir == null) {
				dir = hitRes.getDirection();
			}
			if (!DCUtil.isEmpty(held) && held.is(TagDC.ItemTag.CRAFT_DRIVER)) {
				if (dir != null && !level.isClientSide) {
					FaceIO next = cable.switchFace(dir);
					if (level instanceof ServerLevel)
						MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), next.getID());
					if (next == FaceIO.NONE) {
						level.setBlock(pos, state.setValue(DCState.getFacingProperty(dir), Boolean.valueOf(false)), 3);
					} else {
						level.setBlock(pos, state.setValue(DCState.getFacingProperty(dir), Boolean.valueOf(true)), 3);
					}
					MutableComponent mes = Component.literal("Connection " + dir + ":" + next);
					player.displayClientMessage(mes, true);
					return InteractionResult.SUCCESS;
				}
			}
			FluidStack f = cable.getFluidHandler().getFluid();
			if (f != null && !f.isEmpty()) {
				int head = DCFluidUtil.getHead(f);
				DCLogger.debugInfoLog("head: " + head);
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	private Direction getClickDir(double x, double y, double z) {
		if (x > 0.63D) {
			return Direction.EAST;
		}
		if (x < 0.37D) {
			return Direction.WEST;
		}
		if (z > 0.63D) {
			return Direction.SOUTH;
		}
		if (z < 0.37D) {
			return Direction.NORTH;
		}
		if (y > 0.63D) {
			return Direction.UP;
		}
		if (y < 0.37D) {
			return Direction.DOWN;
		}
		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST, DCState.UP, DCState.DOWN, DCState.POWERED, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return false;
	}

	@Override
	public ItemStack getDropItem(ItemStack item, BlockEntity tile) {
		return item;
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
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
		if (!state.is(state2.getBlock())) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof OwnableBaseTileDC) {
				level.updateNeighbourForOutputSignal(pos, state.getBlock());
			}
			super.onRemove(state, level, pos, state2, flag);
		}
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends FluidPipeTileBaseDC> tile) {
		return level.isClientSide ? null : createTickerHelper(type, tile, FluidPipeTileBaseDC::serverTick);
	}

}
