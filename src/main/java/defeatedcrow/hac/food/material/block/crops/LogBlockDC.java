package defeatedcrow.hac.food.material.block.crops;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class LogBlockDC extends RotatedPillarBlock implements IBlockDC, IJsonDataDC {

	final String name;

	public LogBlockDC(String s) {
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
		    .strength(2.0F)
		    .sound(SoundType.WOOD));
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
		    .setValue(AXIS, Direction.Axis.Y)
		    .setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(AXIS, DCState.WILD);
	}

	@Override
	public String getRegistryName() {
		return "food/log_" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/cube_column", ImmutableMap.of("end", "dcs_climate:block/tree/" + name + "_log_top", "side", "dcs_climate:block/tree/" + name + "_log_side")),
		    new JsonModelDC("minecraft:block/cube_column_horizontal", ImmutableMap.of("end", "dcs_climate:block/tree/" + name + "_log_top", "side", "dcs_climate:block/tree/" + name + "_log_side")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("hol", "var", "hol");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_var");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return null;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
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

	// flammable
	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 5;
	}

}
