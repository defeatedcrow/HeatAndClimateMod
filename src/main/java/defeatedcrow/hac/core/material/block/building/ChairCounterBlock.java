package defeatedcrow.hac.core.material.block.building;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.material.IColordBlock;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.material.entity.ChairEntity;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class ChairCounterBlock extends BlockDC implements SimpleWaterloggedBlock, IColordBlock {

	protected static final VoxelShape W_AABB = Block.box(11.0D, 10.0D, 4.0D, 12.0D, 15.0D, 12.0D);
	protected static final VoxelShape E_AABB = Block.box(4.0D, 10.0D, 4.0D, 5.0D, 15.0D, 12.0D);
	protected static final VoxelShape S_AABB = Block.box(4.0D, 10.0D, 4.0D, 12.0D, 15.0D, 5.0D);
	protected static final VoxelShape N_AABB = Block.box(4.0D, 10.0D, 11.0D, 12.0D, 15.0D, 12.0D);
	protected static final VoxelShape BASE_AABB = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final String name;

	public ChairCounterBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
		    .setValue(DCState.FACING, Direction.NORTH)
		    .setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.CLOTH_DECORATION, MaterialColor.WOOL)
		    .strength(1.0F, 15.0F)
		    .noOcclusion();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return switch (dir) {
		case NORTH -> Shapes.or(N_AABB, BASE_AABB);
		case SOUTH -> Shapes.or(S_AABB, BASE_AABB);
		case EAST -> Shapes.or(E_AABB, BASE_AABB);
		case WEST -> Shapes.or(W_AABB, BASE_AABB);
		default -> Shapes.or(N_AABB, BASE_AABB);
		};
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (player != null) {
			ItemStack held = player.getItemInHand(hand);
			if (getAvaiableColor(held) != MagicColor.NONE) {
				replace(level, pos, player, held, getAvaiableColor(held));
				held.shrink(1);
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
			ChairEntity bind = CoreInit.CHAIR_ENTITY.get()
			    .create(level);
			bind.setPos(pos.getX() + 0.5D, pos.getY() + 0.625D, pos.getZ() + 0.5D);
			bind.setDeltaMovement(0D, 0D, 0D);
			player.startRiding(bind);
			level.addFreshEntity(bind);
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, WATERLOGGED);
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
		return new JsonModelSimpleDC("dcs_climate:block/build/" + name + "_0");
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
		Direction dir = cont.getHorizontalDirection()
		    .getOpposite();
		return super.getStateForPlacement(cont).setValue(DCState.FACING, dir)
		    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	// colord block

	@Override
	public MagicColor getAvaiableColor(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return MagicColor.NONE;
		if (item.is(TagDC.ItemTag.EXTRACT_WHITE))
			return MagicColor.WHITE;
		if (item.is(TagDC.ItemTag.EXTRACT_BLUE))
			return MagicColor.BLUE;
		if (item.is(TagDC.ItemTag.EXTRACT_BLACK))
			return MagicColor.BLACK;
		if (item.is(TagDC.ItemTag.EXTRACT_RED))
			return MagicColor.RED;
		if (item.is(TagDC.ItemTag.EXTRACT_GREEN))
			return MagicColor.GREEN;
		return MagicColor.NONE;
	}

	@Override
	public Optional<Block> getReplaceBlock(MagicColor color) {
		if (color.isWhite)
			return Optional.of(BuildInit.CHAIR_COUNTER_WHITE.get());
		if (color.isBlue)
			return Optional.of(BuildInit.CHAIR_COUNTER_BLUE.get());
		if (color.isBlack)
			return Optional.of(BuildInit.CHAIR_COUNTER_BLACK.get());
		if (color.isRed)
			return Optional.of(BuildInit.CHAIR_COUNTER_RED.get());
		if (color.isGreen)
			return Optional.of(BuildInit.CHAIR_COUNTER_GREEN.get());
		return Optional.empty();
	}

	@Override
	public void replace(Level level, BlockPos pos, Player player, ItemStack held, MagicColor color) {
		BlockState target = level.getBlockState(pos);
		if (target != null && target.getBlock() instanceof ChairCounterBlock chair && target.getBlock() != BuildInit.CHAIR_COUNTER_LEATHER.get()) {
			chair.getReplaceBlock(color)
			    .ifPresent(block -> {
				    Direction face = DCState.getFace(target, DCState.FACING);
				    boolean water = DCState.getBool(target, WATERLOGGED);
				    BlockState replace = block.defaultBlockState()
				        .setValue(DCState.FACING, face)
				        .setValue(WATERLOGGED, water);
				    level.setBlock(pos, replace, 2);
			    });
		}
	}

}
