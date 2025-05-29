package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MetalWindowBlock extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape N_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape S_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final String name;
	final MagicColor color;

	public MetalWindowBlock(String s, MagicColor c) {
		super(getProp());
		name = s;
		color = c;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH).setValue(DCState.BOTTOM, false).setValue(DCState.TOP, false).setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).sound(SoundType.METAL).strength(3.0F, 120.0F).noOcclusion();
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, DCState.BOTTOM, DCState.TOP, WATERLOGGED);
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
		// return ImmutableList.of(
		// new JsonModelDC("dcs_climate:block/build/window_frame_bottom",
		// ImmutableMap.of("base", "dcs_climate:block/build/window_frame_" + color.toString(), "glass", "dcs_climate:block/build/window_glass_" + name + "_b")),
		// new JsonModelDC("dcs_climate:block/build/window_frame_middle",
		// ImmutableMap.of("base", "dcs_climate:block/build/window_frame_" + color.toString(), "glass", "dcs_climate:block/build/window_glass_" + name + "_m")),
		// new JsonModelDC("dcs_climate:block/build/window_frame_single",
		// ImmutableMap.of("base", "dcs_climate:block/build/window_frame_" + color.toString(), "glass", "dcs_climate:block/build/window_glass_" + name + "_s")),
		// new JsonModelDC("dcs_climate:block/build/window_frame_top",
		// ImmutableMap.of("base", "dcs_climate:block/build/window_frame_" + color.toString(), "glass", "dcs_climate:block/build/window_glass_" + name + "_t")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
		// return ImmutableList.of("bottom", "middle", "single", "top");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/build/window/window_" + name + "_" + color + "_single");
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
		return new ItemStack(this);
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, BlockEntity tile) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "build/window_" + name + "_" + color;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		Direction dir = cont.getHorizontalDirection();
		boolean top = level.getBlockState(pos.above()) != null && level.getBlockState(pos.above()).getBlock() == this && DCState.getFace(level.getBlockState(pos.above()), DCState.FACING) == dir;
		boolean bottom = level.getBlockState(pos.below()) != null && level.getBlockState(pos.below()).getBlock() == this && DCState.getFace(level.getBlockState(pos.below()), DCState.FACING) == dir;
		return super.getStateForPlacement(cont).setValue(DCState.BOTTOM, bottom).setValue(DCState.TOP, top).setValue(DCState.FACING, dir).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() ==
				Fluids.WATER));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		if (dir.getAxis().getPlane() == Direction.Plane.VERTICAL) {
			Direction dir2 = DCState.getFace(s1, DCState.FACING);
			BlockState next = super.updateShape(s1, dir, s2, level, pos, pos2);
			BlockState avobe = level.getBlockState(pos.above());
			BlockState below = level.getBlockState(pos.below());
			boolean top = avobe != null && avobe.getBlock() == this && DCState.getFace(avobe, DCState.FACING) == dir2;
			boolean bottom = below != null && below.getBlock() == this && DCState.getFace(below, DCState.FACING) == dir2;
			return next.setValue(DCState.TOP, top).setValue(DCState.BOTTOM, bottom);
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
