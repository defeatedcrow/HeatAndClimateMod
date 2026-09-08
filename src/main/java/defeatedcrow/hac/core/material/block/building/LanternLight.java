package defeatedcrow.hac.core.material.block.building;

import java.util.Optional;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.material.IColordBlock;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LanternLight extends SimpleLightDC implements SimpleWaterloggedBlock, IColordBlock {

	protected static final VoxelShape D_AABB = Block.box(3.0D, 1.0D, 3.0D, 13.0D, 15.0D, 13.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final LampType type;

	public LanternLight(String s, LampType t) {
		super(getProp(), s);
		type = t;
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 6.0F).noOcclusion().lightLevel((state) -> {
			return 15;
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return D_AABB;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(WATERLOGGED);
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		Direction dir = cont.getClickedFace();
		return super.getStateForPlacement(cont).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
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

	// colord block
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (player != null) {
			ItemStack held = player.getItemInHand(hand);
			if (getAvaiableColor(held) != MagicColor.NONE) {
				replace(level, pos, player, held, getAvaiableColor(held));
				held.shrink(1);
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public MagicColor getAvaiableColor(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return MagicColor.NONE;
		if (item.is(TagDC.ItemTag.EXTRACT_WHITE))
			return MagicColor.WHITE;
		if (item.is(TagDC.ItemTag.EXTRACT_BLUE))
			return MagicColor.BLUE;
		if (item.is(TagDC.ItemTag.EXTRACT_BLACK))
			return MagicColor.BLACK;
		if (item.is(TagDC.ItemTag.EXTRACT_RED))
			return MagicColor.RED;
		if (item.is(TagDC.ItemTag.EXTRACT_GREEN))
			return MagicColor.GREEN;
		return MagicColor.NONE;
	}

	@Override
	public Optional<Block> getReplaceBlock(MagicColor color) {
		if (type == LampType.BERRY) {
			if (color.isWhite)
				return Optional.of(BuildInit.BERRY_LANTERN_WHITE.get());
			if (color.isBlue)
				return Optional.of(BuildInit.BERRY_LANTERN_BLUE.get());
			if (color.isBlack)
				return Optional.of(BuildInit.BERRY_LANTERN_BLACK.get());
			if (color.isRed)
				return Optional.of(BuildInit.BERRY_LANTERN_RED.get());
			if (color.isGreen)
				return Optional.of(BuildInit.BERRY_LANTERN_GREEN.get());
		} else if (type == LampType.PAPER) {
			if (color.isWhite)
				return Optional.of(BuildInit.ANDON_LANTERN_WHITE.get());
			if (color.isBlue)
				return Optional.of(BuildInit.ANDON_LANTERN_BLUE.get());
			if (color.isBlack)
				return Optional.of(BuildInit.ANDON_LANTERN_BLACK.get());
			if (color.isRed)
				return Optional.of(BuildInit.ANDON_LANTERN_RED.get());
			if (color.isGreen)
				return Optional.of(BuildInit.ANDON_LANTERN_GREEN.get());
		}

		return Optional.empty();
	}

	@Override
	public void replace(Level level, BlockPos pos, Player player, ItemStack held, MagicColor color) {
		BlockState target = level.getBlockState(pos);
		if (target != null && target.getBlock() instanceof LanternLight light) {
			light.getReplaceBlock(color).ifPresent(block -> {
				boolean water = DCState.getBool(target, WATERLOGGED);
				BlockState replace = block.defaultBlockState().setValue(WATERLOGGED, water);
				level.setBlock(pos, replace, 2);
			});
		}
	}

	public enum LampType {
		BERRY,
		PAPER;
	}

}
