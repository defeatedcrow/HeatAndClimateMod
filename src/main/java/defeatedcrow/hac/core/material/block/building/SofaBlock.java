package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.material.entity.ChairEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SofaBlock extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape AABB_FULL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
	protected static final VoxelShape HALF_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	public static final VoxelShape N_AABB = Block.box(0.0D, 8.0D, 12.0D, 16.0D, 15.0D, 16.0D);
	public static final VoxelShape E_AABB = Block.box(0.0D, 8.0D, 0.0D, 4.0D, 15.0D, 16.0D);
	public static final VoxelShape S_AABB = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 4.0D);
	public static final VoxelShape W_AABB = Block.box(12.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final String name;

	public SofaBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH)
				.setValue(DCState.LEFT, false).setValue(DCState.RIGHT, false).setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.0F, 15.0F).noOcclusion();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		switch (dir) {
		case NORTH:
			return Shapes.or(N_AABB, HALF_AABB);
		case SOUTH:
			return Shapes.or(S_AABB, HALF_AABB);
		case EAST:
			return Shapes.or(E_AABB, HALF_AABB);
		case WEST:
			return Shapes.or(W_AABB, HALF_AABB);
		default:
			return HALF_AABB;
		}
	}

	// ぽよんぽよん
	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
		if (entity.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(level, entity);
		} else {
			Vec3 vec3 = entity.getDeltaMovement();
			if (vec3.y < 0.0D) {
				double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
				entity.setDeltaMovement(vec3.x, -vec3.y * 0.66F * d0, vec3.z);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (player != null) {
			ChairEntity bind = CoreInit.CHAIR_ENTITY.get().create(level);
			bind.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
			bind.setDeltaMovement(0D, 0D, 0D);
			player.startRiding(bind);
			level.addFreshEntity(bind);
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, DCState.LEFT, DCState.RIGHT, WATERLOGGED);
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/build/" + name + "_b");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, BlockEntity tile) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "build/" + name;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		Direction dir = cont.getHorizontalDirection().getOpposite();
		BlockPos left = pos.relative(dir.getCounterClockWise());
		BlockPos right = pos.relative(dir.getClockWise());

		return super.getStateForPlacement(cont).setValue(DCState.FACING, dir)
				.setValue(DCState.LEFT, Boolean.valueOf(canConnectTo(level.getBlockState(left))))
				.setValue(DCState.RIGHT, Boolean.valueOf(canConnectTo(level.getBlockState(right))))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	protected static boolean canConnectTo(BlockState state) {
		return state.getBlock() instanceof SofaBlock;
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		if (dir.getAxis().getPlane() == Direction.Plane.HORIZONTAL) {
			Direction sofaDir = DCState.getFace(s1, DCState.FACING);
			BlockState next = super.updateShape(s1, dir, s2, level, pos, pos2);
			if (sofaDir.getCounterClockWise() == dir) {
				boolean left = canConnectTo(s2);
				return next.setValue(DCState.LEFT, Boolean.valueOf(left));
			}
			if (sofaDir.getClockWise() == dir) {
				boolean right = canConnectTo(s2);
				return next.setValue(DCState.RIGHT, Boolean.valueOf(right));
			}
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
