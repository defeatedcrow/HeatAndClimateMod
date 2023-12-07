package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
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
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
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

public class MetalSlab extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape AABB_U = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_D = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	final String name;

	public MetalSlab(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FLAG, Boolean.valueOf(false)).setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).sound(SoundType.METAL).strength(3.0F, 30.0F).noOcclusion();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		if (DCState.getBool(state, DCState.FLAG))
			return AABB_U;
		else
			return AABB_D;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (player != null) {
			ItemStack held = player.getItemInHand(hand);
			if (!DCUtil.isEmpty(held) && held.getItem() == this.asItem() && hitRes != null) {
				Direction dir = player.getDirection();
				float f = Float.MIN_VALUE;
				for (Direction dir1 : Direction.Plane.HORIZONTAL) {
					double x = hitRes.getLocation().x - hitRes.getBlockPos().getX() - 0.5D;
					double z = hitRes.getLocation().z - hitRes.getBlockPos().getZ() - 0.5D;
					float f1 = (float) (x * dir1.getStepX() + z * dir1.getStepZ());
					if (f1 > f) {
						f = f1;
						dir = dir1;
					}
				}
				if (level.getBlockState(pos.relative(dir)).getMaterial().isReplaceable()) {
					FluidState fluidstate = level.getFluidState(pos.relative(dir));
					BlockState place = state.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
					if (!level.isClientSide && level.setBlock(pos.relative(dir), place, 3)) {
						player.getItemInHand(hand).shrink(1);
					}
					level.playSound(player, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 0.8F, 1.5F);
				}
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FLAG, WATERLOGGED);
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
		return new JsonModelSimpleDC("dcs_climate:block/build/metal_slab_under");
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
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		FluidState fluidstate = level.getFluidState(pos);
		Direction dir = cont.getClickedFace();
		boolean flag = dir == Direction.DOWN || dir != Direction.UP && ((cont.getClickLocation().y - pos.getY()) > 0.5D);
		return super.getStateForPlacement(cont).setValue(DCState.FLAG, flag).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.metal_building_blocks");
		list.add(tex1);
	}

}
