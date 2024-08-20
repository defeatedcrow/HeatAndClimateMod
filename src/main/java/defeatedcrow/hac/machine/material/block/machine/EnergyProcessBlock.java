package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileFaceIOToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

/**
 * 汎用マシンベースクラス (EnergyMachineBaseDCに対応)<br>
 * - Owner登録、開閉アニメーションなし
 */
public abstract class EnergyProcessBlock extends EntityBlockDC {

	public EnergyProcessBlock(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.FACING, Direction.NORTH)
				.setValue(DCState.POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		boolean pow = cont.getLevel().hasNeighborSignal(cont.getClickedPos());
		Direction face = Direction.NORTH;
		if (cont.getPlayer() != null) {
			face = cont.getPlayer().getDirection().getOpposite();
		}
		return this.defaultBlockState().setValue(DCState.FACING, face)
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
		InteractionResult check = super.use(state, level, pos, player, hand, hitRes);
		if (check == InteractionResult.SUCCESS) {
			return InteractionResult.SUCCESS;
		}
		if (check == InteractionResult.PASS) {
			return InteractionResult.PASS;
		}
		BlockEntity tile = level.getBlockEntity(pos);
		ItemStack held = player.getItemInHand(hand);
		if (tile instanceof EnergyTileBaseDC machine) {
			if (!DCUtil.isEmpty(held) && held.is(TagDC.ItemTag.CRAFT_DRIVER) && hitRes != null) {
				Direction dir = hitRes.getDirection();
				FaceIO next = machine.getEnergyHandler().switchFace(dir);
				if (level instanceof ServerLevel)
					MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), next.getID());
				DCLogger.debugInfoLog("cable connect: " + dir + " / " + next);
				return InteractionResult.SUCCESS;
			} else {
				// if (ClimateCore.isDebug && player.isCrouching()) {
				// UUID id = machine.getOwner();
				// String name = machine.getOwnerName();
				// DCLogger.debugInfoLog("### Registerd Owner: " + id.toString() + " ###");
				// DCLogger.debugInfoLog("### Registerd OwnerName: " + name + " ###");
				// }
				if (level.isClientSide) {
					return InteractionResult.SUCCESS;
				} else {
					if (machine.canOpen(player) && player instanceof ServerPlayer && machine.createMenu(0, player.getInventory(), player) != null) {
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
		Direction dir = DCState.getFace(state, DCState.FACING);
		return state.setValue(DCState.FACING, rot.rotate(dir));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return state.setValue(DCState.FACING, mir.mirror(dir));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.FACING, DCState.POWERED, WATERLOGGED);
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
		return EnergyProcessTile.getContainerOutputSignal(level.getBlockEntity(pos));
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

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends EnergyTileBaseDC> tile) {
		return level.isClientSide ? null : createTickerHelper(type, tile, EnergyTileBaseDC::serverTick);
	}

}
