package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class FaucetBlock extends EntityBlockDC {

	protected static final VoxelShape N_AABB = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 10.0D, 10.0D);
	protected static final VoxelShape S_AABB = Block.box(4.0D, 0.0D, 6.0D, 12.0D, 10.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(6.0D, 0.0D, 4.0D, 16.0D, 10.0D, 12.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 4.0D, 10.0D, 10.0D, 12.0D);

	final String name;

	public FaucetBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.FACING, Direction.NORTH)
				.setValue(DCState.POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(2.0F, 540.0F).noOcclusion();
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
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		FluidState fluidstate = cont.getLevel().getFluidState(cont.getClickedPos());
		Direction face = cont.getHorizontalDirection();
		return this.defaultBlockState().setValue(DCState.FACING, face)
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		ItemStack held = player.getItemInHand(hand);
		if (DCUtil.isEmpty(held)) {
			if (!level.isClientSide)
				changeLisState(level, pos);
			level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.8F, 1.5F);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			if (FluidUtil.getFluidHandler(held.copy()).isPresent()) {
				ItemStack copy = held.copy();
				copy.setCount(1);
				if (FluidUtil.getFluidHandler(copy).map(handler -> {
					FluidStack fluid = handler.getFluidInTank(0);
					if (fluid.isEmpty()) {
						FluidStack drain = new FluidStack(Fluids.WATER, 1000);
						int d = handler.fill(drain, FluidAction.EXECUTE);
						if (d > 0) {
							ItemStack ret = handler.getContainer().copy();
							if (!ret.isEmpty()) {
								held.shrink(1);
								ret.setCount(1);
								ItemEntity drop = new ItemEntity(level, player.getX() + 0.5D, player.getY() + 0.25D, player.getZ() + 0.5D, ret);
								level.addFreshEntity(drop);
								return true;
							}
						}
					}
					return false;
				}).orElse(false)) {
					player.getInventory().setChanged();
					level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.2F);
				}
				return InteractionResult.CONSUME;
			} else {
				return super.use(state, level, pos, player, hand, hitRes);
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
		if (!level.isClientSide) {
			boolean pow = level.hasNeighborSignal(pos);
			if (pow != DCState.getBool(state, DCState.POWERED)) {
				level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(pow)), 2);
			}
		}
	}

	public static void changeLisState(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof FaucetBlock) {
			boolean l = !DCState.getBool(state, DCState.POWERED);
			level.setBlock(pos, state.setValue(DCState.POWERED, Boolean.valueOf(l)), 3);
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return state.setValue(DCState.FACING, rot.rotate(dir));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return state.setValue(DCState.FACING, dir.getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(DCState.FACING, DCState.POWERED, WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return true;
	}

	/* BlockDC */

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FaucetTile(pos, state);
	}

	@Override
	public String getRegistryName() {
		return "machine/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/machine/" + name + "_item")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/machine/" + name + "_item"));
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.FAUCET_TILE.get(), FaucetTile::serverTick) : null;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.flow.tier2").withStyle(ChatFormatting.YELLOW);
		MutableComponent tex2 = Component.translatable("dcs.tip.energy.rs_signal_machine");
		MutableComponent tex3 = Component.translatable("dcs.tip.faucet.desc").withStyle(ChatFormatting.GRAY);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);
			list.add(tex2);
			list.add(tex3);
		} else {
			list.add(tex1);
		}
		super.appendHoverText(stack, level, list, flag);
	}

}
