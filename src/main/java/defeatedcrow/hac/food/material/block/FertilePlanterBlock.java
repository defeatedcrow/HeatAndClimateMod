package defeatedcrow.hac.food.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FertilePlanterBlock extends FertileBlock {

	protected static final VoxelShape AABB = Block.box(0.1D, 0.0D, 0.1D, 15.0D, 15.0D, 15.0D);

	private final boolean mutable;

	public FertilePlanterBlock(boolean b) {
		super();
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(MOISTURE, Integer.valueOf(7))
				.setValue(FERTILE, Integer.valueOf(0))
				.setValue(DCState.NORTH, false)
				.setValue(DCState.SOUTH, false)
				.setValue(DCState.EAST, false)
				.setValue(DCState.WEST, false));
		mutable = b;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return AABB;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
		entity.causeFallDamage(f, 1.0F, DamageSource.FALL);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(MOISTURE, FERTILE, DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST);
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

	@Override
	public int getFertile(BlockGetter level, BlockPos pos, BlockState state) {
		return DCState.getInt(state, FERTILE);
	}

	@Override
	public BlockState fertileTargetSoil(int fertile, BlockState state) {
		int f = Mth.clamp(fertile, 0, 3);
		return state.setValue(FERTILE, f).setValue(MOISTURE, 7);
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
		return mutable ? "food/fertile_planter_gem" : "food/fertile_planter";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("item");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_item");
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.fertile.fertile");
		list.add(tex1);
		if (!mutable) {
			MutableComponent tex2 = Component.translatable("dcs.tip.fertile.mutation.off").withStyle(ChatFormatting.DARK_GREEN);
			list.add(tex2);
		}
	}

}
