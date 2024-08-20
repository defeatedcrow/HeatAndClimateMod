package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.InventoryDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * コンベアベースクラス (ConveyorTileに対応)
 */
public abstract class ConveyorBlockBase extends EntityBlockDC {

	private static final VoxelShape AABB = Block.box(0.0D, 1.0D, 0.0D, 16.0D, 3.0D, 16.0D);

	public ConveyorBlockBase(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.FACING, Direction.NORTH)
				.setValue(DCState.POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return AABB;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		Direction face = cont.getHorizontalDirection();
		return this.defaultBlockState().setValue(DCState.FACING, face)
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
	public BlockState rotate(BlockState state, Rotation rot) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return state.setValue(DCState.FACING, rot.rotate(dir));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return state.setValue(DCState.FACING, dir.getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.FACING, DCState.POWERED, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return true;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof ConveyorBlockBase cont && builder != null) {
			LootContext context = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
			BlockEntity tile = null;
			if (context.hasParam(LootContextParams.BLOCK_ENTITY)) {
				tile = context.getParam(LootContextParams.BLOCK_ENTITY);
			}

			ret.add(getMainDrop());
			if (tile instanceof ConveyorTile base) {
				// 中身をその場に散らかす
				InventoryDC inv = base.getInventory();
				ret.addAll(inv.inv);
			}
		} else {
			ret.add(getMainDrop());
		}
		return ret;
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

}
