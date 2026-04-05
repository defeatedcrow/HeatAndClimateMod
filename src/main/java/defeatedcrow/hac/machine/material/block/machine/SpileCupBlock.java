package defeatedcrow.hac.machine.material.block.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.FoodMaterialItemDC;
import defeatedcrow.hac.food.material.item.SapType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpileCupBlock extends EntityBlockDC {

	final String name;

	protected static final VoxelShape N_AABB = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 11.0D, 8.0D);
	protected static final VoxelShape S_AABB = Block.box(2.0D, 0.0D, 8.0D, 14.0D, 11.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(8.0D, 0.0D, 2.0D, 16.0D, 11.0D, 14.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 2.0D, 8.0D, 11.0D, 14.0D);

	protected static final EnumProperty<SapType> TYPE = SapType.TYPE;

	public SpileCupBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.FACING, Direction.NORTH).setValue(TYPE, SapType.SWEET).setValue(DCState.STAGE5, 0).setValue(EntityBlockDC.WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.METAL).strength(3.0F, 6.0F).randomTicks();
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		return switch (dir) {
		case NORTH -> N_AABB;
		case SOUTH -> S_AABB;
		case EAST -> E_AABB;
		case WEST -> W_AABB;
		default -> N_AABB;
		};
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 2) || random.nextInt(3) != 0)
			return;
		Direction dir = DCState.getFace(state, DCState.FACING);
		BlockState log = level.getBlockState(pos.relative(dir));
		int i = DCState.getInt(state, DCState.STAGE5);
		if (log != null && log.is(TagDC.BlockTag.LOG_SAP) && i < 4) {
			if (log != null && log.is(TagDC.BlockTag.LOG_RESIN)) {
				BlockState nextState = state.setValue(TYPE, SapType.RESIN).setValue(DCState.STAGE5, i + 1);
				level.setBlock(pos, nextState, 2);
			} else if (log != null && log.is(TagDC.BlockTag.LOG_LATEX)) {
				BlockState nextState = state.setValue(TYPE, SapType.LATEX).setValue(DCState.STAGE5, i + 1);
				level.setBlock(pos, nextState, 2);
			} else if (log != null && log.is(TagDC.BlockTag.LOG_LACQUER)) {
				BlockState nextState = state.setValue(TYPE, SapType.LACQUER).setValue(DCState.STAGE5, i + 1);
				level.setBlock(pos, nextState, 2);
			} else if (log != null && log.is(TagDC.BlockTag.LOG_SWEET)) {
				BlockState nextState = state.setValue(TYPE, SapType.SWEET).setValue(DCState.STAGE5, i + 1);
				level.setBlock(pos, nextState, 2);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
		int i = DCState.getInt(state, DCState.STAGE5);
		Direction dir = DCState.getFace(state, DCState.FACING);
		SapType type = SapType.getFromState(state);
		if (i > 0 && dir != null && type != null) {
			BlockState log = level.getBlockState(pos.relative(dir));
			ItemStack item = new ItemStack(type.getDrop(), i);
			if (log != null && log.is(TagDC.BlockTag.LOG_SWEET)) {
				item = new ItemStack(FoodInit.SAP_SWEET.get(), i);
				if (log.getBlock() == FoodInit.LOG_BH_SWEET.get()) {
					((FoodMaterialItemDC) item.getItem()).setTaste(item, 1);
				}
			}

			ItemEntity drop;
			if (player != null) {
				drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), item);
			} else {
				drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, item);
			}
			if (drop != null && !level.isClientSide)
				level.addFreshEntity(drop);

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
			ret.add(new ItemStack(this));
		} else {
			int i = DCState.getInt(state, DCState.STAGE5);
			Direction dir = DCState.getFace(state, DCState.FACING);
			SapType type = SapType.getFromState(state);
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
					ItemStack item = new ItemStack(type.getDrop(), i);
					if (log != null && log.is(TagDC.BlockTag.LOG_SWEET)) {
						item = new ItemStack(FoodInit.SAP_SWEET.get(), i);
						if (log.getBlock() == FoodInit.LOG_BH_SWEET.get()) {
							((FoodMaterialItemDC) item.getItem()).setTaste(item, 1);
						}
						ret.add(item);
					}

				}
			}
		}
		return ret;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(DCState.FACING, rot.rotate(state.getValue(DCState.FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mir) {
		return state.rotate(mir.getRotation(state.getValue(DCState.FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, TYPE, DCState.STAGE5, EntityBlockDC.WATERLOGGED);
	}

	@Override
	public boolean requireStateJson() {
		return true;
	}

	@Override
	public String getRegistryName() {
		return "machine/spilecup";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
		    new JsonModelDC("dcs_climate:block/machine/spilecup_0", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_1", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_2", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_3", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_4", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_1", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_resin")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_2", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_resin")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_3", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_resin")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_4", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_resin")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_1", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_latex")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_2", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_latex")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_3", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_latex")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_4", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_latex")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_1", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_lacquer")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_2", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_lacquer")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_3", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_lacquer")),
		    new JsonModelDC("dcs_climate:block/machine/spilecup_4", ImmutableMap.of("base", "dcs_climate:block/machine/spilecup", "sap", "dcs_climate:block/machine/spilecup_sap_lacquer")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("base", "sweet_1", "sweet_2", "sweet_3", "sweet_4", "resin_1", "resin_2", "resin_3", "resin_4",
		    "latex_1", "latex_2", "latex_3", "latex_4", "lacquer_1", "lacquer_2", "lacquer_3", "lacquer_4");
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

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.spile", "logs_can_collect_sap");
		list.add(tex1);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SpileCupTile(pos, state);
	}

	/* Redstone */

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
		int i = DCState.getInt(state, DCState.STAGE5);
		return i == 0 ? 0 : i * 4 - 1;
	}

}
