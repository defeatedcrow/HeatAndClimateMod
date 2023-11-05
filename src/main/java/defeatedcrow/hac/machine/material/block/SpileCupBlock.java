package defeatedcrow.hac.machine.material.block;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.FoodMaterialItemDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpileCupBlock extends BlockDC {

	final String name;

	protected static final VoxelShape N_AABB = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 11.0D, 8.0D);
	protected static final VoxelShape S_AABB = Block.box(2.0D, 0.0D, 8.0D, 14.0D, 11.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(8.0D, 0.0D, 2.0D, 16.0D, 11.0D, 14.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 2.0D, 8.0D, 11.0D, 14.0D);

	public SpileCupBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH).setValue(DCState.STAGE5, Integer.valueOf(0)));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.METAL).strength(3.0F, 6.0F).randomTicks();
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		int i = DCState.getInt(state, DCState.STAGE5);
		return i < 4;
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
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 2) || random.nextInt(3) != 0)
			return;
		Direction dir = DCState.getFace(state, DCState.FACING);
		BlockState log = level.getBlockState(pos.relative(dir));
		int i = DCState.getInt(state, DCState.STAGE5);
		if (log != null && log.is(TagDC.BlockTag.LOG_SAP) && i < 4) {
			BlockState nextState = state.setValue(DCState.STAGE5, i + 1);
			level.setBlock(pos, nextState, 2);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
		int i = DCState.getInt(state, DCState.STAGE5);
		Direction dir = DCState.getFace(state, DCState.FACING);
		if (i > 0 && dir != null) {
			BlockState log = level.getBlockState(pos.relative(dir));
			ItemStack item = ItemStack.EMPTY;
			if (log != null && log.is(TagDC.BlockTag.LOG_SWEET)) {
				item = new ItemStack(FoodInit.SAP_SWEET.get(), i);
				if (log.getBlock() == FoodInit.LOG_BH_SWEET.get()) {
					((FoodMaterialItemDC) item.getItem()).setTaste(item, 1);
				}
			}
			if (log != null && log.is(TagDC.BlockTag.LOG_RESIN)) {
				item = new ItemStack(FoodInit.SAP_RESIN.get(), i);
			}
			if (log != null && log.is(TagDC.BlockTag.LOG_LATEX)) {
				item = new ItemStack(FoodInit.SAP_LATEX.get(), i);
			}
			if (log != null && log.is(TagDC.BlockTag.LOG_LACQUER)) {
				item = new ItemStack(FoodInit.SAP_SUMAC.get(), i);
			}
			if (!item.isEmpty()) {
				ItemEntity drop;
				if (player != null) {
					drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), item);
				} else {
					drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, item);
				}
				if (drop != null && !level.isClientSide)
					level.addFreshEntity(drop);
			}
			if (!level.isClientSide) {
				BlockState next = state.setValue(DCState.STAGE5, 0);
				level.setBlock(pos, next, 2);
			}
			return InteractionResult.SUCCESS;
		}
		return super.use(state, level, pos, player, hand, res);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state == null || builder == null) {
			ret.addAll(super.getDrops(state, builder));
		} else {
			int i = DCState.getInt(state, DCState.STAGE5);
			Direction dir = DCState.getFace(state, DCState.FACING);
			ret.add(new ItemStack(this));
			if (i > 0) {
				LootContext cont = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
				IClimateCrop crop = (IClimateCrop) state.getBlock();
				ServerLevel level = cont.getLevel();
				Vec3 v3 = null;
				if (cont.hasParam(LootContextParams.ORIGIN)) {
					v3 = cont.getParam(LootContextParams.ORIGIN);
					BlockPos pos = new BlockPos(v3);
					BlockState log = level.getBlockState(pos.relative(dir));
					if (log != null && log.is(TagDC.BlockTag.LOG_SWEET)) {
						ret.add(new ItemStack(FoodInit.SAP_SWEET.get(), i));
					}
					if (log != null && log.is(TagDC.BlockTag.LOG_RESIN)) {
						ret.add(new ItemStack(FoodInit.SAP_RESIN.get(), i));
					}
					if (log != null && log.is(TagDC.BlockTag.LOG_LATEX)) {
						ret.add(new ItemStack(FoodInit.SAP_LATEX.get(), i));
					}
					if (log != null && log.is(TagDC.BlockTag.LOG_LACQUER)) {
						ret.add(new ItemStack(FoodInit.SAP_SUMAC.get(), i));
					}
				}
			}
		}
		return ret;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, DCState.STAGE5);
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public String getRegistryName() {
		return "machine/spilecup";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/machine/spilecup_base")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/machine/spilecup_sap1")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/machine/spilecup_sap2")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/machine/spilecup_sap3")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/machine/spilecup_sap4")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "4");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/machine/spilecup_base");
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
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		Direction dir = cont.getClickedFace().getOpposite();
		if (dir.getAxis().isVertical())
			dir = cont.getHorizontalDirection();
		return super.getStateForPlacement(cont).setValue(DCState.FACING, dir);
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return getMainDrop();
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, BlockEntity tile) {
		return Lists.newArrayList();
	}

}
