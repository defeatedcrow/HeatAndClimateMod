package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ConnectedGlassBlock extends BlockDC {

	final String name;
	boolean isDark = false;

	public ConnectedGlassBlock(String s, int light) {
		super(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(1.0F, 8.0F).noOcclusion().lightLevel((state) -> {
			return light;
		}));
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(DCState.NORTH, false)
			.setValue(DCState.SOUTH, false)
			.setValue(DCState.EAST, false)
			.setValue(DCState.WEST, false)
			.setValue(DCState.UP, false)
			.setValue(DCState.DOWN, false));
	}

	public ConnectedGlassBlock(String s) {
		super(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.QUARTZ).noOcclusion().strength(1.0F, 8.0F));
		name = s;
		isDark = true;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.NORTH, false).setValue(DCState.SOUTH, false).setValue(DCState.EAST, false)
			.setValue(DCState.WEST, false).setValue(DCState.UP, false).setValue(DCState.DOWN, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST, DCState.UP, DCState.DOWN);
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
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "build/glass_" + name;
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_cutout_all", ImmutableMap.of("all", "dcs_climate:block/build/glass_" + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("dcs_climate:block/dcs_cutout_all", ImmutableMap.of("all", "dcs_climate:block/build/glass_" + name));
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
		BlockPos p1 = pos.north();
		BlockPos p2 = pos.east();
		BlockPos p3 = pos.south();
		BlockPos p4 = pos.west();
		BlockPos p5 = pos.above();
		BlockPos p6 = pos.below();
		BlockState s1 = level.getBlockState(p1);
		BlockState s2 = level.getBlockState(p2);
		BlockState s3 = level.getBlockState(p3);
		BlockState s4 = level.getBlockState(p4);
		BlockState s5 = level.getBlockState(p5);
		BlockState s6 = level.getBlockState(p6);
		return super.getStateForPlacement(cont).setValue(DCState.NORTH, connectsTo(s1, level, p1, Direction.SOUTH))
			.setValue(DCState.EAST, this.connectsTo(s2, level, p2, Direction.WEST))
			.setValue(DCState.SOUTH, this.connectsTo(s3, level, p3, Direction.NORTH))
			.setValue(DCState.WEST, this.connectsTo(s4, level, p4, Direction.EAST))
			.setValue(DCState.UP, this.connectsTo(s5, level, p5, Direction.DOWN))
			.setValue(DCState.DOWN, this.connectsTo(s6, level, p6, Direction.UP));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (dir != null) {
			return s1.setValue(DCState.getFacingProperty(dir), connectsTo(s2, level, pos2, dir.getOpposite()));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	public boolean connectsTo(BlockState target, BlockGetter level, BlockPos pos, Direction dir) {
		return target != null && target.getBlock() != this;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
		return !isDark;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
		return isDark ? level.getMaxLightLevel() : 0;
	}

}
