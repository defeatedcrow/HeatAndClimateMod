package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class GrassSlab extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape AABB_D = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

	final String name;

	public GrassSlab(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, false).setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.GRASS).sound(SoundType.GRASS).strength(0.3F).randomTicks();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return AABB_D;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (state != null && state.is(TagDC.BlockTag.DIRT_SLABS)) {
			if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			} else {
				ItemStack held = player.getItemInHand(hand);
				if (!DCUtil.isEmpty(held) && held.is(Tags.Items.TOOLS_SHOVELS)) {
					BlockState next = BuildInit.SLAB_PATH.get().defaultBlockState();
					level.setBlock(pos, next, 2);
					level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
					held.hurtAndBreak(1, player, (i) -> {
						i.broadcastBreakEvent(hand);
					});
				}
				return InteractionResult.CONSUME;
			}
		} else {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	public static boolean canBeGrass(Level level, BlockPos pos) {
		BlockPos above = pos.above();
		BlockState upper = level.getBlockState(above);
		if (upper.is(Blocks.SNOW) && upper.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		} else if (upper.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LayerLightEngine.getLightBlockInto(level, level.getBlockState(pos), pos, upper, above, Direction.UP, upper.getLightBlock(level, above));
			return i < level.getMaxLightLevel();
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (!level.isAreaLoaded(pos, 1))
			return;
		if (!canBeGrass(level, pos)) {
			level.setBlockAndUpdate(pos, BuildInit.SLAB_DIRT.get().defaultBlockState());
		} else if (DCState.getBool(state, SNOWY) && !isSnowy(level, pos)) {
			level.setBlockAndUpdate(pos, state.setValue(SNOWY, false));
		} else {
			if (!level.isAreaLoaded(pos, 3))
				return;
			if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
				Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
				BlockPos p2 = pos.relative(dir);
				if (canBeGrass(level, p2) && !level.getFluidState(p2).is(FluidTags.WATER)) {
					if (level.getBlockState(p2).is(Blocks.DIRT)) {
						level.setBlockAndUpdate(p2, Blocks.GRASS_BLOCK.defaultBlockState().setValue(SNOWY, Boolean.valueOf(level.getBlockState(p2.above()).is(Blocks.SNOW))));
					} else if (level.getBlockState(p2).is(BuildInit.SLAB_DIRT.get())) {
						level.setBlockAndUpdate(p2, this.defaultBlockState().setValue(SNOWY, Boolean.valueOf(DCState.getBool(state, SNOWY))));
					}
				}
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(WATERLOGGED, SNOWY);
	}

	@Override
	public List<String> getStateNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/cube_all", ImmutableMap.of("all", "block/dirt")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/build/" + name + "_0");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.SHOVEL;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(BuildInit.SLAB_DIRT.get());
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
	public String getRegistryName() {
		return "build/" + name;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		Level level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		boolean snowy = isSnowy(level, pos);
		return super.getStateForPlacement(cont).setValue(WATERLOGGED, Boolean.valueOf(!snowy && fluidstate.getType() == Fluids.WATER)).setValue(SNOWY, Boolean.valueOf(snowy));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		boolean snowy = dir.getAxis().isHorizontal() && isSnowy(level.getBlockState(pos.relative(dir)));
		return snowy ? s1.setValue(SNOWY, Boolean.valueOf(snowy)) : super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	private static boolean isSnowy(LevelAccessor level, BlockPos pos) {
		if (!level.isAreaLoaded(pos, 1))
			return false;
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState check = level.getBlockState(pos.relative(dir));
			if (isSnowy(check))
				return true;
		}
		return false;
	}

	private static boolean isSnowy(BlockState check) {
		if (check.is(BlockTags.SNOW))
			return true;
		if (SnowyDirtBlock.class.isInstance(check.getBlock()) && DCState.getBool(check, SNOWY))
			return true;
		return false;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
