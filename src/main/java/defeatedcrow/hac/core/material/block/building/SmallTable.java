package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallTable extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_HALF = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final boolean isHalf;
	final String name;

	public SmallTable(String s, boolean half) {
		super(prop());
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
		isHalf = half;
		name = s;
	}

	public static BlockBehaviour.Properties prop() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.0F, 6.0F).noOcclusion();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return isHalf ? AABB_HALF : AABB;
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
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public String getRegistryName() {
		return "build/table_round_" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_table_round", ImmutableMap.of("base", "dcs_climate:block/build/plate_steel", "top", "dcs_climate:block/ore/stone_" + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/build/table_round_" + name + "_0");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

}
