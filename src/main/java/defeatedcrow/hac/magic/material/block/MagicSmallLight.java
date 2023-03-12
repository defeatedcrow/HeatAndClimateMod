package defeatedcrow.hac.magic.material.block;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

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

public class MagicSmallLight extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape N_AABB = Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 1.0D);
	protected static final VoxelShape S_AABB = Block.box(5.0D, 5.0D, 15.0D, 11.0D, 11.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(15.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 5.0D, 5.0D, 1.0D, 11.0D, 11.0D);
	protected static final VoxelShape U_AABB = Block.box(5.0D, 15.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape D_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public MagicSmallLight() {
		super(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(1.0F, 8.0F).noOcclusion().noLootTable().lightLevel((state) -> {
			return 15;
		}));
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.DIRECTION, Direction.DOWN).setValue(WATERLOGGED, false));
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.DIRECTION, WATERLOGGED);
	}

	@Override
	public ItemStack getMainDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "magic/magic_small_light";
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
		return new JsonModelSimpleDC("dcs_climate:block/magic/magic_small_light");
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
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		Direction dir = cont.getClickedFace();
		return super.getStateForPlacement(cont).setValue(DCState.DIRECTION, dir.getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public FluidState getFluidState(BlockState p_52362_) {
		return p_52362_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_52362_);
	}

}
