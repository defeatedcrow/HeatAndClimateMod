package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.crop.ForageType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class MealFeederBlock extends BlockDC {

	protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.5D, 14.0D);

	public MealFeederBlock() {
		super(getProp());
		this.registerDefaultState(this.defaultBlockState().setValue(DCState.TYPE4, 0).setValue(DCState.STAGE16, 0));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		ItemStack held = player.getItemInHand(hand);
		int feed = DCState.getInt(state, DCState.STAGE16);
		int type = DCState.getInt(state, DCState.TYPE4);
		if (feed < 15 && !DCUtil.isEmpty(held)) {
			ForageType itemType = getTypeFromItem(held);
			if (itemType != ForageType.EMPTY && (type == 0 || itemType.getId() == type)) {
			int count = 1;
				if (itemType == ForageType.COMPOUND) {
					count = 2;
			}
			player.getItemInHand(hand).shrink(1);
			count = Math.min(feed + count, 15);
			level.setBlockAndUpdate(pos, state.setValue(DCState.STAGE16, count).setValue(DCState.TYPE4, itemType.getId()));
			level.playSound(player, pos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 0.8F, 1.5F);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}
		return InteractionResult.FAIL;
	}

	private ForageType getTypeFromItem(ItemStack item) {
		if (item.is(TagDC.ItemTag.FEED_COMPOUND) || item.is(TagDC.ItemTag.FEED_SILAGE)) {
			return ForageType.COMPOUND;
		} else if (item.is(TagDC.ItemTag.FEED_HAY) || item.is(TagDC.ItemTag.FEED_STRAW)) {
			return ForageType.HAY;
		} else if (item.is(TagDC.ItemTag.FEEDS) || item.is(Tags.Items.SEEDS)) {
			return ForageType.SEED;
		} else {
			return ForageType.EMPTY;
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (DCState.getInt(state, DCState.STAGE16) > 0) {
			int type = DCState.getInt(state, DCState.TYPE4);
			List<Entity> list = Lists.newArrayList();
			if (type == 1) {
				list = level.getEntities((Entity) null, new AABB(pos.getX() - 8D, pos.getY() - 2D, pos.getZ() - 8D, pos.getX() + 8D, pos.getY() + 2D, pos.getZ() + 8D), HOOVES);
			}
			if (type == 2) {
				list = level.getEntities((Entity) null, new AABB(pos.getX() - 8D, pos.getY() - 2D, pos.getZ() - 8D, pos.getX() + 8D, pos.getY() + 2D, pos.getZ() + 8D), BIRDS);
			}
			if (type == 3) {
				list = level.getEntities((Entity) null, new AABB(pos.getX() - 8D, pos.getY() - 2D, pos.getZ() - 8D, pos.getX() + 8D, pos.getY() + 2D, pos.getZ() + 8D), HERBIVIORES);
			}

			if (!list.isEmpty()) {
				for (int j = 0; j < 2; j++) {
					int i = level.getRandom().nextInt(list.size());
					Entity target = list.get(i);
					if (target instanceof Animal animal && target.isAlive()) {
						boolean flag = false;
						if (animal.getHealth() < animal.getMaxHealth()) {
							animal.heal(animal.getMaxHealth());
							flag = true;
						} else if (animal.isBaby()) {
							animal.ageUp(12000);
							flag = true;
						} else if (animal.canFallInLove()) {
							animal.setInLove((Player) null);
							flag = true;
						}
						if (flag) {
							int feed = Math.max(DCState.getInt(state, DCState.STAGE16) - 1, 0);
							if (feed == 0) {
								level.setBlockAndUpdate(pos, state.setValue(DCState.STAGE16, 0).setValue(DCState.TYPE4, 0));
							} else {
								level.setBlockAndUpdate(pos, state.setValue(DCState.STAGE16, feed));
							}
						}
					}
				}
			}
		}
		super.tick(state, level, pos, random);
	}

	private static final Predicate<Entity> HERBIVIORES = (target) -> {
		if (target.isAlive()) {
			return target instanceof Chicken || target instanceof Cow || target instanceof Pig || target instanceof Rabbit
					|| target instanceof Sheep || target instanceof AbstractHorse;
		} else {
			return false;
		}
	};

	private static final Predicate<Entity> HOOVES = (target) -> {
		if (target.isAlive()) {
			return target instanceof Cow || target instanceof Pig || target instanceof Rabbit
					|| target instanceof Sheep || target instanceof AbstractHorse;
		} else {
			return false;
		}
	};

	private static final Predicate<Entity> BIRDS = (target) -> {
		if (target.isAlive()) {
			return target instanceof Chicken || target instanceof Parrot;
		} else {
			return false;
		}
	};

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		return AABB;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.2F, 8.0F).sound(SoundType.WOOD).noOcclusion().randomTicks();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DCState.STAGE16, DCState.TYPE4);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState();
	}

	/* BlockDC */

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return new ItemStack(this);
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "food/meal_feeder";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/food/meal_feeder_item")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

}
