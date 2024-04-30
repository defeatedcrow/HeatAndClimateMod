package defeatedcrow.hac.machine.material.block.monitor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.ITileNBTHolder;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MonitorRSBlock extends EntityBlockDC implements ITileNBTHolder {

	final String name;

	protected static final VoxelShape N_AABB = Block.box(5.0D, 2.0D, 0.0D, 11.0D, 14.0D, 8.0D);
	protected static final VoxelShape S_AABB = Block.box(5.0D, 2.0D, 8.0D, 11.0D, 14.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(8.0D, 2.0D, 5.0D, 16.0D, 14.0D, 11.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 2.0D, 5.0D, 8.0D, 14.0D, 11.0D);
	protected static final VoxelShape U_AABB = Block.box(5.0D, 8.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape D_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);

	public MonitorRSBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(DCState.DIRECTION, Direction.NORTH)
			.setValue(DCState.POWERED, Boolean.valueOf(false))
			.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(2.0F, 540.0F).noOcclusion();
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
		if (DCState.getBool(state, DCState.POWERED) || state.getBlock() == MachineInit.MONITOR_RS_PILOT.get()) {
			return 8;
		}
		return 0;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		switch (dir) {
		case NORTH:
			return N_AABB;
		case SOUTH:
			return S_AABB;
		case EAST:
			return E_AABB;
		case WEST:
			return W_AABB;
		case UP:
			return U_AABB;
		default:
			return D_AABB;
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		Direction face = cont.getClickedFace().getOpposite();
		return this.defaultBlockState().setValue(DCState.DIRECTION, face)
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	public static boolean changePowerState(Level level, BlockPos pos, boolean pow) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof MonitorRSBlock && DCState.getBool(state, DCState.POWERED) != pow) {
			level.setBlock(pos, state.setValue(DCState.POWERED, pow), 3);
			return true;
		}
		return false;
	}

	@Override
	public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
		return state.getValue(DCState.POWERED) ? 15 : 0;
	}

	@Override
	public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
		return state.getValue(DCState.POWERED) && DCState.getFace(state, DCState.DIRECTION) == dir ? 15 : 0;
	}

	@Override
	public boolean isSignalSource(BlockState p_51114_) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.DIRECTION, DCState.POWERED, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return false;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
		if (!state.is(state2.getBlock())) {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof MonitorBaseTile) {
				level.updateNeighbourForOutputSignal(pos, state.getBlock());
			}
			super.onRemove(state, level, pos, state2, flag);
		}
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.MONITOR_RS_TILE.get(), MonitorBaseTile::serverTick) : null;
	}

	@Override
	public ItemStack getDropItem(ItemStack item, BlockEntity tile) {
		if (!DCUtil.isEmpty(item) && tile instanceof MonitorBaseTile basetile) {
			CompoundTag tag = item.getOrCreateTag();
			basetile.writeTag(tag);
			item.setTag(tag);
		}
		return item;
	}

	@Override
	public void setNBTFromItem(ItemStack item, BlockEntity tile) {
		if (!DCUtil.isEmpty(item) && item.hasTag() && tile instanceof MonitorBaseTile basetile) {
			CompoundTag tag = item.getTag();
			basetile.load(tag);
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MonitorRSTile(pos, state);
	}

	@Override
	public ToolType getToolType() {
		return ToolType.NONE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public String getRegistryName() {
		return "machine/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/machine/" + name + "_item")));
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/machine/" + name + "_item"));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("");
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}
}
