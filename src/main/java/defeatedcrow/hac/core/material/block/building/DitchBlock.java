package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DitchBlock extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape AABB_FULL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static final VoxelShape BOTTOM1 = Block.box(0.0D, 2.0D, 14.0D, 2.0D, 16.0D, 16.0D);
	protected static final VoxelShape BOTTOM2 = Block.box(14.0D, 2.0D, 14.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape BOTTOM3 = Block.box(0.0D, 2.0D, 0.0D, 2.0D, 16.0D, 2.0D);
	protected static final VoxelShape BOTTOM4 = Block.box(14.0D, 2.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	public static final VoxelShape BOTTOM5 = Shapes.or(BOTTOM1, BOTTOM2);
	public static final VoxelShape BOTTOM6 = Shapes.or(BOTTOM3, BOTTOM4);
	public static final VoxelShape BOTTOM7 = Shapes.or(BOTTOM5, BOTTOM6);
	public static final VoxelShape BOTTOM_AABB = Shapes.or(BOTTOM, BOTTOM7);
	public static final VoxelShape S_AABB = Block.box(2.0D, 2.0D, 14.0D, 14.0D, 16.0D, 16.0D);
	public static final VoxelShape N_AABB = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 16.0D, 2.0D);
	public static final VoxelShape W_AABB = Block.box(0.0D, 2.0D, 2.0D, 2.0D, 16.0D, 14.0D);
	public static final VoxelShape E_AABB = Block.box(14.0D, 2.0D, 2.0D, 16.0D, 16.0D, 14.0D);
	public static final VoxelShape NS_AABB = Shapes.or(N_AABB, S_AABB);
	public static final VoxelShape WE_AABB = Shapes.or(W_AABB, E_AABB);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final String name;

	public DitchBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(
		    this.stateDefinition.any().setValue(DCState.FLAG, false).setValue(DCState.NORTH, false).setValue(DCState.SOUTH, false).setValue(DCState.EAST, false).setValue(DCState.WEST, false).setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 540.0F).noOcclusion();
	}

	public static void changeLitState(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof DitchBlock) {
			boolean l = !DCState.getBool(state, DCState.FLAG);
			level.setBlock(pos, state.setValue(DCState.FLAG, l), 3);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		ItemStack held = player.getItemInHand(hand);
		if (DCUtil.isEmpty(held)) {
			if (!level.isClientSide)
				changeLitState(level, pos);
			level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.8F, 1.5F);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.use(state, level, pos, player, hand, hitRes);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		int num = getAABBIndex(state);
		return DCState.getBool(state, DCState.FLAG) ? AABB_FULL : Shapes.or(BOTTOM_AABB, getShape[num]);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		int num = getAABBIndex(state);
		return DCState.getBool(state, DCState.FLAG) ? AABB_FULL : Shapes.or(BOTTOM_AABB, getShape[num]);
	}

	protected int getAABBIndex(BlockState state) {
		int n = DCState.getBool(state, DCState.NORTH) ? 1 : 0;
		int s = DCState.getBool(state, DCState.SOUTH) ? 2 : 0;
		int w = DCState.getBool(state, DCState.WEST) ? 4 : 0;
		int e = DCState.getBool(state, DCState.EAST) ? 8 : 0;
		return n + s + w + e;
	}

	protected VoxelShape[] getShape = { Shapes.empty(), N_AABB, S_AABB, NS_AABB, W_AABB, Shapes.or(N_AABB, W_AABB), Shapes.or(S_AABB, W_AABB), Shapes.or(NS_AABB, W_AABB), E_AABB,
	    Shapes.or(N_AABB, E_AABB), Shapes.or(S_AABB, E_AABB), Shapes.or(NS_AABB, E_AABB), WE_AABB, Shapes.or(N_AABB, WE_AABB), Shapes.or(S_AABB, WE_AABB), Shapes.or(NS_AABB, WE_AABB) };

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FLAG, DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST, WATERLOGGED);
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
		return new JsonModelSimpleDC("dcs_climate:block/build/" + name + "_full");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
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
		BlockPos p1 = pos.north();
		BlockPos p2 = pos.east();
		BlockPos p3 = pos.south();
		BlockPos p4 = pos.west();
		BlockState s1 = level.getBlockState(p1);
		BlockState s2 = level.getBlockState(p2);
		BlockState s3 = level.getBlockState(p3);
		BlockState s4 = level.getBlockState(p4);

		return super.getStateForPlacement(cont).setValue(DCState.NORTH, canConnectTo(s1)).setValue(DCState.EAST, canConnectTo(s2)).setValue(DCState.SOUTH, canConnectTo(s3)).setValue(DCState.WEST, canConnectTo(s4)).setValue(WATERLOGGED,
		    fluidstate.getType() == Fluids.WATER);
	}

	protected static boolean canConnectTo(BlockState state) {
		return !(state.getBlock() instanceof DitchBlock);
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		if (dir != null && !dir.getAxis().isVertical()) {
			return s1.setValue(DCState.getFacingProperty(dir), canConnectTo(s2));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
