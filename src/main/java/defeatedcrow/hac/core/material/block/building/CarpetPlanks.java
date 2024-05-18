package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

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

public class CarpetPlanks extends BlockDC {

	final String name;

	public CarpetPlanks(String s) {
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.0F, 15.0F).noOcclusion());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(DCState.NORTH, false)
			.setValue(DCState.SOUTH, false)
			.setValue(DCState.EAST, false)
			.setValue(DCState.WEST, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST);
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
		return "build/" + name;
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
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0000");
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
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		BlockPos p1 = pos.north();
		BlockPos p2 = pos.east();
		BlockPos p3 = pos.south();
		BlockPos p4 = pos.west();
		BlockState s1 = level.getBlockState(p1);
		BlockState s2 = level.getBlockState(p2);
		BlockState s3 = level.getBlockState(p3);
		BlockState s4 = level.getBlockState(p4);
		return super.getStateForPlacement(cont)
			.setValue(DCState.NORTH, this.connectsTo(s1, level, p1, Direction.SOUTH))
			.setValue(DCState.EAST, this.connectsTo(s2, level, p2, Direction.WEST))
			.setValue(DCState.SOUTH, this.connectsTo(s3, level, p3, Direction.NORTH))
			.setValue(DCState.WEST, this.connectsTo(s4, level, p4, Direction.EAST));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (dir != null && dir.getAxis().isHorizontal()) {
			return s1.setValue(DCState.getFacingProperty(dir), connectsTo(s2, level, pos2, dir.getOpposite()));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	public boolean connectsTo(BlockState target, BlockGetter level, BlockPos pos, Direction dir) {
		return target != null && target.getBlock() == this;
	}

}
