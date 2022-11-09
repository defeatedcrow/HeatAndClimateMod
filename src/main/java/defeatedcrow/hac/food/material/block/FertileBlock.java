package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.IFertileBlock;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class FertileBlock extends FarmBlock implements IFertileBlock, IBlockDC, IJsonDataDC {

	public static final IntegerProperty FERTILE = DCState.FERTILE;

	public FertileBlock() {
		super(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).randomTicks().strength(1.0F, 1.0F));
		this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)).setValue(FERTILE, Integer.valueOf(0)));
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (DCState.getInt(state, FERTILE) <= 0) {
			int i = DCState.getInt(state, MOISTURE);
			level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState().setValue(MOISTURE, i));
		}
		super.tick(state, level, pos, random);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(MOISTURE, FERTILE);
	}

	@Override
	public int isFertile(BlockGetter level, BlockPos pos, BlockState state) {
		return DCState.getInt(state, FERTILE);
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(Blocks.DIRT);
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
		return "food/fertile";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("minecraft:block/template_farmland", ImmutableMap.of("dirt", "minecraft:block/dirt", "top", "dcs_climate:block/crop/fertile_dry")),
			new JsonModelDC("minecraft:block/template_farmland", ImmutableMap.of("dirt", "minecraft:block/dirt", "top", "dcs_climate:block/crop/fertile_moist")));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.of(new String[] { "dry", "moist" });
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_dry");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.NONE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

}
