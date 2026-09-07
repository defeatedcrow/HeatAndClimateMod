package defeatedcrow.hac.core.material.block.building;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NoSaveBedBlock extends EntityBlockDC {

	final String name;
	private String domain = "build";
	protected static final VoxelShape BASE_AABB = Block.box(0.0D, 4.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

	public NoSaveBedBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DCState.FACING, Direction.NORTH)
				.setValue(DCState.FLAG, true)
				.setValue(OCCUPIED, false)
				.setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.2F, 30.0F);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, DCState.FLAG, OCCUPIED, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return BASE_AABB;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (level.isClientSide) {
			return InteractionResult.CONSUME;
		} else {
			if (DCState.getBool(state, DCState.FLAG)) {
				Direction face = DCState.getFace(state, DCState.FACING);
				pos = pos.relative(face);
				state = level.getBlockState(pos);
				if (!state.is(this)) {
					return InteractionResult.CONSUME;
				}
			}

			if (DCState.getBool(state, OCCUPIED)) {
				player.displayClientMessage(Component.translatable("block.minecraft.bed.occupied"), true);
				return InteractionResult.SUCCESS;
			} else {
				BlockEntity tile = level.getBlockEntity(pos);
				if (tile instanceof NoSaveBedTile bedTile) {
					BlockPos lastPos = bedTile.getSleepPos();
					player.startSleepInBed(pos).ifLeft((playerEntity) -> {
						if (playerEntity.getMessage() != null) {
							player.displayClientMessage(playerEntity.getMessage(), true);
						}
					}).ifRight((playerEntity) -> {
						bedTile.setSleepPos(player.blockPosition());
					});
				}
				return InteractionResult.SUCCESS;
			}
		}
	}

	private static Direction getNeighbourDirection(boolean isFoot, Direction dir) {
		return isFoot ? dir : dir.getOpposite();
	}

	@Override
	public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, @Nullable Entity player) {
		return true;
	}

	@Override
	public Optional<Vec3> getRespawnPosition(BlockState state, EntityType<?> type, LevelReader levelReader, BlockPos pos, float orientation, @Nullable LivingEntity entity) {
		if (levelReader instanceof Level level) {
			BlockEntity tile = level.getBlockEntity(pos);
			if (tile instanceof NoSaveBedTile bedTile && bedTile.getSleepPos() != BlockPos.ZERO) {
				return Optional.of(Vec3.upFromBottomCenterOf(bedTile.getSleepPos(), 0D));
			} else {

			}
		}
		return Optional.empty();
	}

	public void setBedOccupied(Level level, BlockPos pos, LivingEntity sleeper, boolean occupied) {
		setBedOccupied(this.defaultBlockState(), level, pos, sleeper, occupied);
	}

	@Override
	public Direction getBedDirection(BlockState state, LevelReader level, BlockPos pos) {
		return DCState.getFace(state, DCState.FACING);
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		boolean flag = DCState.getBool(s1, DCState.FLAG);
		Direction face = DCState.getFace(s1, DCState.FACING);
		Direction face2 = getNeighbourDirection(flag, face);
		if (s1.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		if (dir == face2) {
			if (s2.is(this) && flag != DCState.getBool(s2, DCState.FLAG) && face == DCState.getFace(s2, DCState.FACING)) {
				boolean oc = DCState.getBool(s2, OCCUPIED);
				return s1.setValue(OCCUPIED, oc);
			} else {
				return Blocks.AIR.defaultBlockState();
			}
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		Level level = cont.getLevel();
		Direction dir = cont.getHorizontalDirection();
		BlockPos pos = cont.getClickedPos();
		BlockPos pos2 = pos.relative(dir);
		return level.getBlockState(pos2).canBeReplaced(cont) && level.getWorldBorder().isWithinBounds(pos2) ? this.defaultBlockState().setValue(DCState.FACING, dir) : null;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity living, ItemStack item) {
		super.setPlacedBy(level, pos, state, living, item);
		if (!level.isClientSide) {
			BlockPos pos2 = pos.relative(DCState.getFace(state, DCState.FACING));
			level.setBlock(pos2, state.setValue(DCState.FLAG, false), 2);
			level.blockUpdated(pos, this);
			state.updateNeighbourShapes(level, pos, 3);
		}
	}

	// ぽよんぽよんする
	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
		super.fallOn(level, state, pos, entity, f * 0.5F);
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
		if (entity.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(level, entity);
		} else {
			Vec3 vec3 = entity.getDeltaMovement();
			if (vec3.y < 0.0D) {
				entity.setDeltaMovement(vec3.x, -vec3.y * 0.5D, vec3.z);
			}
		}
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public ItemStack getMainDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (!DCState.getBool(state, DCState.FLAG)) {
			ret.add(new ItemStack(this));
		}
		return ret;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
				new JsonModelDC("dcs_climate:block/dcs_bed_foot",
						ImmutableMap.of("bottom", "dcs_climate:block/build/panel_wood",
								"cloth", "dcs_climate:block/build/bed_cloth_white",
								"frame", "dcs_climate:block/build/bed_frame_wood",
								"top", "dcs_climate:block/build/bed_cloth_white_top")),
				new JsonModelDC("dcs_climate:block/dcs_bed_head",
						ImmutableMap.of("bottom", "dcs_climate:block/build/panel_wood",
								"cloth", "dcs_climate:block/build/bed_cloth_white",
								"frame", "dcs_climate:block/build/bed_frame_wood")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("foot", "head");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/build/" + name + "_item"));
	}

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NoSaveBedTile(pos, state);
	}

}
