package defeatedcrow.hac.machine.material.block.monitor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StormglassBlock extends EntityBlockDC {

	final String name;

	protected static final VoxelShape AABB = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

	public StormglassBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.METAL).requiresCorrectToolForDrops().strength(2.0F, 540.0F).noOcclusion();
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (level instanceof ServerLevel server) {
			LevelData data = level.getLevelData();
			if (data instanceof ServerLevelData sData) {
				int clear = sData.getClearWeatherTime();
				int thunder = sData.getThunderTime();
				int rain = sData.getRainTime();
				boolean bt = server.getThunderLevel(1.0F) > 0.01;
				boolean br = server.getRainLevel(1.0F) > 0.01;
				DCLogger.debugInfoLog("##### Weather Data #####");
				if (clear > 0) {
					DCLogger.debugInfoLog("Force clear the weather : rem " + clear);
				} else {
					if (bt) {
						DCLogger.debugInfoLog("Tundering : rem " + thunder);
					} else {
						DCLogger.debugInfoLog("Stop Tundering : rem " + thunder);
					}
					if (bt) {
						DCLogger.debugInfoLog("Raining : rem " + rain);
					} else {
						DCLogger.debugInfoLog("Stop Raining : rem " + rain);
					}
				}
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return AABB;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathCom) {
		return false;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.STORMGLASS_TILE.get(), StormglassTile::serverTick) : null;
	}

	@Override
	public ItemStack getDropItem(ItemStack item, BlockEntity tile) {
		return item;
	}

	@Override
	public void setNBTFromItem(ItemStack item, BlockEntity tile) {}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StormglassTile(pos, state);
	}

	@Override
	public ToolType getToolType() {
		return ToolType.NONE;
	}

	@Override
	public int getToolTier() {
		return 0;
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
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/machine/" + name + "_item"));
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
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.stormglass").withStyle(ChatFormatting.GRAY);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);
		} else {
			list.add(Component.translatable("dcs.tip.shift"));
		}
		super.appendHoverText(stack, level, list, flag);
	}
}
