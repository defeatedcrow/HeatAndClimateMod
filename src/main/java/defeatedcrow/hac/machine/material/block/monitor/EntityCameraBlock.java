package defeatedcrow.hac.machine.material.block.monitor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EntityCameraBlock extends BlockDC {

	final String name;
	protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

	public EntityCameraBlock() {
		super(getProp());
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH).setValue(DCState.LIT_LEVEL, 0));
		name = "entity_camera";
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).sound(SoundType.STONE).strength(0.3F).randomTicks().noOcclusion();
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state2, boolean b) {
		if (!level.isClientSide)
			level.scheduleTick(pos, state.getBlock(), 4);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		super.tick(state, level, pos, rand);
		Direction dir = DCState.getFace(state, DCState.FACING);
		int power = DCState.getInt(state, DCState.LIT_LEVEL);
		if (!level.isClientSide && dir != null && state.getBlock() != null) {
			int count = 0;
			AABB aabb = new AABB(pos.relative(dir, 3));
			aabb = aabb.inflate(2.0D, 1.0D, 2.0D);
			// block
			List<Entity> list = level.getEntities((Entity) null, aabb, EntitySelector.LIVING_ENTITY_STILL_ALIVE);
			if (!list.isEmpty())
				count = list.size();
			if (count > 15)
				count = 15;

			if (count != power) {
				level.setBlock(pos, state.setValue(DCState.LIT_LEVEL, count), 3);
			}

		}
		level.scheduleTick(pos, state.getBlock(), 4);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		Direction face = Direction.NORTH;
		boolean flag = false;
		if (cont.getPlayer() != null) {
			face = cont.getPlayer().getDirection().getOpposite();
		}
		return this.defaultBlockState().setValue(DCState.FACING, face);
	}

	@Override
	public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
		return state.getValue(DCState.LIT_LEVEL);
	}

	@Override
	public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
		return state.getValue(DCState.LIT_LEVEL);
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public String getRegistryName() {
		return "machine/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		String tex = "dcs_climate:block/machine/" + name;
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_front", ImmutableMap.of("base", tex + "_base", "front", tex + "_front")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.FACING, DCState.LIT_LEVEL);
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
		List<ItemStack> ret = Lists.newArrayList();
		return ret;
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
	public void appendHoverText(ItemStack item, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.energy.indicator").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD);
		list.add(tex1);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tex2 = Component.translatable("dcs.tip.entity_camera").withStyle(ChatFormatting.GRAY);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex2);
		}
	}

}
