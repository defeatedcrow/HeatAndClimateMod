package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.block.machine.ProcessTileBaseDC;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.network.NetworkHooks;

/**
 * 水位のある水槽Blockのベースクラス (ProcessTileBaseDCに対応)<br>
 * - Owner登録あり
 */
public abstract class SimpleBasinBlock extends EntityBlockDC {

	public SimpleBasinBlock(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.POWERED, false).setValue(WATERLOGGED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		boolean pow = cont.getLevel().hasNeighborSignal(cont.getClickedPos());
		return this.defaultBlockState().setValue(DCState.POWERED, pow).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
		if (!level.isClientSide) {
			boolean pow = level.hasNeighborSignal(pos);
			if (pow != DCState.getBool(state, DCState.POWERED)) {
				level.setBlock(pos, state.setValue(DCState.POWERED, pow), 3);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		InteractionResult check = super.use(state, level, pos, player, hand, hitRes);
		if (check == InteractionResult.SUCCESS) {
			return InteractionResult.SUCCESS;
		}
		if (check == InteractionResult.PASS) {
			return InteractionResult.PASS;
		}

		if (tile instanceof PortableFluidTankTile tank) {
			if (ClimateCore.isDebug && player.isCrouching()) {
				DCLogger.debugInfoLog("### Fluid: " + tank.getTank().getFluid().getDisplayName().getString() + "/" + tank.getTank().getFluid().getAmount() + " ###");
			}
			ItemStack held = player.getItemInHand(hand);
			if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			} else if (!DCUtil.isEmpty(held) && FluidUtil.getFluidHandler(held.copy()).isPresent()) {
				if (DCFluidUtil.exchangeFluid(level, player.position(), tank.getTank(), held)) {
					tile.setChanged();
					player.getInventory().setChanged();
					level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.2F);
				}
				return InteractionResult.CONSUME;
			} else {
				if (tank.canOpen(player) && player instanceof ServerPlayer) {
					NetworkHooks.openScreen((ServerPlayer) player, tank, pos);
				}
				return InteractionResult.CONSUME;
			}
		} else {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.POWERED, WATERLOGGED);
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
		return PortableFluidTankTile.getFluidOutputSignal(level.getBlockEntity(pos));
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
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends ProcessTileBaseDC> tile) {
		return level.isClientSide ? null : createTickerHelper(type, tile, ProcessTileBaseDC::serverTick);
	}

}
