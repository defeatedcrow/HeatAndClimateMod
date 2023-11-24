package defeatedcrow.hac.food.material.block.containers;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.ClimateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

public class LogContBlock extends ClimateBlock implements IRapidCollectables {

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	final String name;
	private String domain = "container";

	public LogContBlock(String s) {
		super(getProp(), true);
		this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
		name = s;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.0F, 8.0F).sound(SoundType.WOOD).randomTicks();
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return RotatedPillarBlock.rotatePillar(state, rot);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());
	}

	/* BlockDC */

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
		return domain + "/logcont_" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		String tex = "dcs_climate:block/container/logcont_" + name;
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_piller_hol", ImmutableMap.of("top", tex + "_t", "side", tex + "_s")),
			new JsonModelDC("dcs_climate:block/dcs_piller_var", ImmutableMap.of("top", tex + "_t", "side", tex + "_s")),
			new JsonModelDC("dcs_climate:block/dcs_piller_hol", ImmutableMap.of("top", tex + "_t", "side", tex + "_s")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("hol", "var", "hol");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("axis=x", "axis=y", "axis=z");
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
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	/* IRapidCollectables */

	@Override
	public TagKey<Item> collectableToolTag() {
		return Tags.Items.TOOLS_AXES;
	}

	@Override
	public List<ItemStack> getDropList(Level level, BlockPos pos, BlockState state, ItemStack tool) {
		return ImmutableList.of(new ItemStack(this));
	}

	@Override
	public boolean doCollect(Level level, BlockPos pos, BlockState state, @Nullable Player player, ItemStack tool) {
		ItemEntity drop;
		ItemStack ret = new ItemStack(this);
		if (player != null) {
			drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), ret);
		} else {
			drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, ret);
		}
		if (drop != null && !level.isClientSide) {
			level.addFreshEntity(drop);
			level.removeBlock(pos, false);
		}
		return true;
	}

}
