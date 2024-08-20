package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

/**
 * ホッパーベースクラス (HopperFilterBaseに対応)<br>
 * - Owner登録あり、開閉アニメーションなし
 */
public abstract class HopperBaseBlockDC extends EntityBlockDC {

	private static final VoxelShape TOP = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape TOP_FUNNEL = Block.box(2.0D, 1.0D, 2.0D, 14.0D, 8.0D, 14.0D);
	private static final VoxelShape BOTTOM_FUNNEL = Block.box(2.0D, 8.0D, 2.0D, 14.0D, 15.0D, 14.0D);
	private static final VoxelShape CONVEX_TOP = Shapes.or(TOP_FUNNEL, TOP);
	private static final VoxelShape CONVEX_BOTTOM = Shapes.or(BOTTOM_FUNNEL, BOTTOM);

	public HopperBaseBlockDC(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.DIRECTION, Direction.DOWN)
				.setValue(DCState.FLAG, Boolean.valueOf(false))
				.setValue(DCState.POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		if (DCState.getBool(state, DCState.FLAG)) {
			return CONVEX_BOTTOM;
		} else {
			return CONVEX_TOP;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		boolean pow = cont.getLevel().hasNeighborSignal(cont.getClickedPos());
		Direction face = cont.getClickedFace().getOpposite();
		boolean flag = false;
		if (face.getAxis().isVertical()) {
			face = Direction.DOWN;
		}
		if (this.defaultBlockState().is(TagDC.BlockTag.HOPPER_FILTER)) {
			if (cont.getPlayer() != null) {
				face = cont.getPlayer().getDirection();
			} else {
				face = Direction.NORTH;
			}
		}
		if (cont.getPlayer() != null) {
			flag = cont.getClickLocation().y - cont.getClickedPos().getY() > 0.5D;
		}
		return this.defaultBlockState().setValue(DCState.DIRECTION, face)
				.setValue(DCState.FLAG, Boolean.valueOf(flag))
				.setValue(DCState.POWERED, Boolean.valueOf(pow))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
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
		if (super.use(state, level, pos, player, hand, hitRes) == InteractionResult.SUCCESS) {
			return InteractionResult.SUCCESS;
		}
		BlockEntity tile = level.getBlockEntity(pos);
		ItemStack held = player.getItemInHand(hand);
		if (tile instanceof HopperBaseTile machine) {
			if (!DCUtil.isEmpty(held) && held.is(TagDC.ItemTag.CRAFT_DRIVER) && hitRes != null) {
				level.setBlock(pos, rotate(state, Rotation.CLOCKWISE_90), 2);
				return InteractionResult.SUCCESS;
			} else {
				if (level.isClientSide) {
					return InteractionResult.SUCCESS;
				} else {
					if (player instanceof ServerPlayer && machine.canOpen(player) && machine.createMenu(0, player.getInventory(), player) != null) {
						NetworkHooks.openScreen((ServerPlayer) player, machine, pos);
					}
					return InteractionResult.CONSUME;
				}
			}
		} else {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		return state.setValue(DCState.DIRECTION, rot.rotate(dir));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		Boolean flag = DCState.getBool(state, DCState.FLAG);
		return state.setValue(DCState.FLAG, !flag);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.DIRECTION, DCState.FLAG, DCState.POWERED, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return false;
	}

	/* BlockDC */

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
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
		return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) level.getBlockEntity(pos));
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
		if (!state.is(state2.getBlock())) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof OwnableContainerBaseTileDC) {
				level.updateNeighbourForOutputSignal(pos, state.getBlock());
			}
			super.onRemove(state, level, pos, state2, flag);
		}
	}

}
