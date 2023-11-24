package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ToolHookBlock extends EntityBlockDC {

	final String name;

	protected static final VoxelShape N_AABB = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 2.0D);
	protected static final VoxelShape S_AABB = Block.box(2.0D, 2.0D, 14.0D, 14.0D, 14.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(14.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 2.0D, 2.0D, 2.0D, 14.0D, 14.0D);

	public ToolHookBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH).setValue(DCState.LIT, Boolean.valueOf(false)).setValue(WATERLOGGED,
			Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		switch (dir) {
		case NORTH:
			return N_AABB;
		case SOUTH:
			return S_AABB;
		case EAST:
			return E_AABB;
		case WEST:
			return W_AABB;
		default:
			return N_AABB;
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
		if (DCState.getBool(state, DCState.LIT)) {
			return 15;
		}
		return 0;
	}

	public static void changeLisState(Level level, BlockPos pos, boolean lit) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof ToolHookBlock && lit != DCState.getBool(state, DCState.POWERED)) {
			level.setBlock(pos, state.setValue(DCState.LIT, Boolean.valueOf(lit)), 3);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		boolean pow = cont.getLevel().hasNeighborSignal(cont.getClickedPos());
		return this.defaultBlockState().setValue(DCState.FACING, cont.getHorizontalDirection())
			.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (player != null && tile instanceof SingleItemDisplay hook) {
			ItemStack itemstack = player.getItemInHand(hand);
			if (!level.isClientSide) {
				if (!DCUtil.isEmpty(hook.inventory.getItem(0))) {
					ItemStack item = hook.inventory.getItem(0).copy();
					ItemEntity drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), item);
					level.addFreshEntity(drop);
					hook.inventory.clearContent();
					hook.setChanged();
				} else if (!DCUtil.isEmpty(itemstack)) {
					ItemStack item = itemstack.split(1);
					hook.inventory.setItem(0, item);
					hook.setChanged();
				}
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(DCState.FACING, rot.rotate(state.getValue(DCState.FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		return state.rotate(mir.getRotation(state.getValue(DCState.FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.FACING, DCState.LIT, WATERLOGGED);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof EntityBlockDC block && builder != null) {
			LootContext cont = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
			BlockEntity tile = null;
			if (cont.hasParam(LootContextParams.BLOCK_ENTITY)) {
				tile = cont.getParam(LootContextParams.BLOCK_ENTITY);
			}

			ret.add(getMainDrop());
			if (tile instanceof SingleItemDisplay base) {
				// 中身をその場に散らかす
				InventoryDC inv = base.inventory;
				ret.addAll(inv.inv);
			}
		} else {
			ret.add(getMainDrop());
		}
		return ret;
	}

	/* BlockDC */

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(0.1F, 540.0F).noOcclusion();
	}

	@Override
	public String getRegistryName() {
		return "build/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/build/" + name + "_item")));
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
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/build/" + name + "_item"));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ToolHookTile(pos, state);
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
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	/* Redstone */

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide ? null : createTickerHelper(type, BuildInit.TOOLHOOK_TILE.get(), SingleItemDisplay::serverTick);
	}

}
