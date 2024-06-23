package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MetalLadder extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape N_AABB = Block.box(0.0D, 0.0D, 2.0D, 16.0D, 16.0D, 4.0D);
	protected static final VoxelShape S_AABB = Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 14.0D);
	protected static final VoxelShape E_AABB = Block.box(12.0D, 0.0D, 0.0D, 14.0D, 16.0D, 16.0D);
	protected static final VoxelShape W_AABB = Block.box(2.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final String name;

	public MetalLadder(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH).setValue(DCState.FLAG, false).setValue(DCState.TOP, false).setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F, 30.0F).noOcclusion();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		switch (dir) {
		case NORTH:
			return N_AABB;
		case SOUTH:
			return S_AABB;
		case EAST:
			return E_AABB;
		case WEST:
			return W_AABB;
		default:
			return N_AABB;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, DCState.FLAG, DCState.TOP, WATERLOGGED);
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
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/build/metal_ladder_item"));
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
	public String getRegistryName() {
		return "build/" + name;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (player != null) {
			ItemStack held = player.getItemInHand(hand);
			if (!DCUtil.isEmpty(held) && held.getItem() == this.asItem() && hitRes != null) {
				BlockPos next = pos;
				boolean below = false;
				if ((hitRes.getLocation().y - hitRes.getBlockPos().getY()) <= 0.5D) {
					below = true;
				}
				if (below) {
					for (int y = 1; y < 64; y++) {
						BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
						mpos.setWithOffset(pos, 0, -y, 0);
						if (level.getBlockState(mpos).getBlock() == this)
							continue;
						else if (level.getBlockState(mpos).getMaterial().isReplaceable()) {
							next = pos.below(y);
							break;
						} else
							break;
					}
				} else {
					for (int y = 1; y < 64; y++) {
						BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
						mpos.setWithOffset(pos, 0, y, 0);
						if (level.getBlockState(mpos).getBlock() == this)
							continue;
						else if (level.getBlockState(mpos).getMaterial().isReplaceable()) {
							next = pos.above(y);
							break;
						} else
							break;
					}
				}
				if (!next.equals(pos)) {
					Direction dir = DCState.getFace(state, DCState.FACING);
					if (dir != null) {
						boolean top = level.getBlockState(next.above()).getMaterial().isReplaceable();
						boolean clamp = level.getBlockState(next.relative(dir)).isFaceSturdy(level, pos.relative(dir), dir.getOpposite());
						FluidState fluidstate = level.getFluidState(next);
						BlockState place = this.defaultBlockState().setValue(DCState.FACING, dir).setValue(DCState.FLAG, clamp).setValue(DCState.TOP, top).setValue(WATERLOGGED, Boolean.valueOf(
								fluidstate
										.getType() == Fluids.WATER));
						if (!level.isClientSide && level.setBlock(next, place, 3)) {
							player.getItemInHand(hand).shrink(1);
						}
						level.playSound(player, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 0.8F, 1.5F);
					}
				}
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		boolean top = level.getBlockState(pos.above()).getMaterial().isReplaceable();
		boolean clamp = false;
		FluidState fluidstate = level.getFluidState(pos);
		Direction face = DCState.getFace(s1, DCState.FACING);
		if (face != null && face.getAxis().isHorizontal()) {
			clamp = level.getBlockState(pos.relative(face)).isFaceSturdy(level, pos.relative(face), face.getOpposite());
		}
		return s1.setValue(DCState.FLAG, clamp).setValue(DCState.TOP, top).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		Direction face = cont.getHorizontalDirection();
		Direction dir = cont.getClickedFace();

		boolean top = level.getBlockState(pos.above()).getMaterial().isReplaceable();
		BlockState s1 = level.getBlockState(pos.relative(dir));
		if (s1.getBlock() == this) {
			face = DCState.getFace(s1, DCState.FACING);
		}
		boolean clamp = level.getBlockState(pos.relative(dir)).isFaceSturdy(level, pos.relative(dir), dir.getOpposite());

		return super.getStateForPlacement(cont).setValue(DCState.FACING, face).setValue(DCState.FLAG, clamp).setValue(DCState.TOP, top).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() ==
				Fluids.WATER));
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(DCState.FACING, rot.rotate(state.getValue(DCState.FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.metal_building_blocks").withStyle(ChatFormatting.GRAY);
		list.add(tex1);
	}

}
