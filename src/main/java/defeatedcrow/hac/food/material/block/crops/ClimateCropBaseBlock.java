package defeatedcrow.hac.food.material.block.crops;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.crop.ICropData;
import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.block.FertileBlock;
import defeatedcrow.hac.food.material.item.ItemEdibleCropDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

public abstract class ClimateCropBaseBlock extends BushBlock implements IClimateCrop, BonemealableBlock, IBlockDC, IJsonDataDC, ICropData, IRapidCollectables {

	final CropTier cropTier;

	public ClimateCropBaseBlock(CropTier t) {
		super(getProp());
		cropTier = t;
	}

	/* 基本データ */
	protected static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		int stage = DCState.getInt(state, DCState.STAGE5);
		if (stage == 0) {
			return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);
		} else if (stage == 1) {
			return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);
		}
		return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 2))
			return;
		CropStage stage = getCurrentStage(state);

		int c2 = getGrowingChance(level, pos, state);
		if (c2 > 0 && random.nextInt(c2) == 0) {

			// チャンスは一度だけ
			int c1 = getMutationChance(level, pos, state);
			random.nextInt(100);

			if ((stage == CropStage.GROUND || stage == CropStage.SAPLING) && getTier() == CropTier.WILD && c1 > 0) {
				onMutation(level, pos, state, random, c1);
			}

			onGrow(level, pos, level.getBlockState(pos));
			return;
		}

		checkAndDropBlock(level, pos, state);

	}

	public void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		if (!isSuitablePlace(world, pos.below(), world.getBlockState(pos.below()))) {
			dropResources(state, world, pos);
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
		}
	}

	public void breakAndDropSeed(Level world, BlockPos pos, BlockState state) {
		ItemStack seed = new ItemStack(getSeedItem(cropTier));
		ItemEntity drop = new ItemEntity(world, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, seed);
		if (drop != null && !world.isClientSide) {
			world.addFreshEntity(drop);
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
		}
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state == null || builder == null) {
			ret.addAll(super.getDrops(state, builder));
		} else if (state.getBlock() instanceof IClimateCrop) {
			LootContext cont = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
			IClimateCrop crop = (IClimateCrop) state.getBlock();
			ServerLevel level = cont.getLevel();
			ItemStack tool = ItemStack.EMPTY;
			if (cont.hasParam(LootContextParams.TOOL) && !DCUtil.isEmpty(cont.getParamOrNull(LootContextParams.TOOL))) {
				tool = cont.getParam(LootContextParams.TOOL);
			}

			CropStage stage = crop.getCurrentStage(state);
			CropTier tier = crop.getTier();

			float seedChance = 0.4F + stage.id * 0.2F;
			if (DCState.getBool(state, DCState.WILD) || level.random.nextFloat() <= seedChance) {
				ret.add(crop.getSeedItem(state));
			}
			if (crop.canHarvest(state)) {
				int f = tool.isEmpty() ? 0 : tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				ret.addAll(crop.getCropItems(state, f));
			}
		}
		return ret;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
		if (onHarvest(level, pos, state, player))
			return InteractionResult.SUCCESS;
		return super.use(state, level, pos, player, hand, res);
	}

	@Override
	public boolean isSuitablePlace(BlockGetter world, BlockPos pos, BlockState state) {
		return mayPlaceOn(state, world, pos);
	}

	@Override
	public ItemStack getSeedItem(BlockState thisState) {
		return new ItemStack(getSeedItem(getTier()));
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		ItemStack ret = new ItemStack(getCropItem(getTier()));
		return ImmutableList.of(ret);
	}

	/* BushBlock */

	@Override
	protected boolean mayPlaceOn(BlockState under, BlockGetter level, BlockPos pos) {
		if (under != null) {
			if (under.isAir())
				return false;
			List<SoilType> soils = getSoilTypes(cropTier);
			for (SoilType soil : soils) {
				if (soil == SoilType.WATER) {
					if (!under.getFluidState().isEmpty() && under.getFluidState().is(FluidTags.WATER))
						return true;
				} else if (soil == SoilType.LOGS) {
					if (under.is(BlockTags.LOGS))
						return true;
				} else if (TagUtil.matchTag(soil.toString().toLowerCase(), under).isPresent()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PlantType getPlantType(BlockGetter level, BlockPos pos) {
		return net.minecraftforge.common.PlantType.CROP;
	}

	// 破壊処理とシェイプ更新を兼ねているよくわからないメソッド
	@Override
	public BlockState updateShape(BlockState state, Direction face, BlockState other, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, face, other, level, pos, pos2);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos down = pos.below();
		if (state.getBlock() == this)
			return level.getBlockState(down).canSustainPlant(level, down, Direction.UP, this);
		return this.mayPlaceOn(level.getBlockState(down), level, down);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision ? true : super.isPathfindable(state, level, pos, type);
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return defaultBlockState();
		return state;
	}

	/* Bonemeal */

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean b) {
		CropStage stage = this.getCurrentStage(state);
		return stage.canUseBonemeal();
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		if (DCState.getBool(state, DCState.DOUBLE)) {
			state = level.getBlockState(pos.below());
			pos = pos.below();
		}

		CropStage stage = getCurrentStage(state);
		if (stage == CropStage.GROUND || stage == CropStage.SAPLING) {
			int c1 = getMutationChance(level, pos, state);
			random.nextInt(100);
			if ((stage == CropStage.GROUND || stage == CropStage.SAPLING) && getTier() == CropTier.WILD && c1 > 0) {
				onMutation(level, pos, state, random, c1);
			}
		}

		this.onGrow(level, pos, level.getBlockState(pos));
	}

	/* IBlockDC */

	@Override
	public ItemStack getMainDrop() {
		return this.getSeedItem(getGrownState());
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		return Lists.newArrayList();
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
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	/* IClimateCrop */

	@Override
	public boolean isSuitableForGrowing(Level world, BlockPos pos, BlockState thisState) {
		ClimateSupplier spr = new ClimateSupplier(world, pos);
		IClimate climate = spr.get();
		boolean temp = this.getSuitableTemp(cropTier).contains(climate.getHeat());
		boolean hum = this.getSuitableHum(cropTier).contains(climate.getHumidity());
		boolean air = this.getSuitableAir(cropTier).contains(climate.getAirflow());
		return temp && hum && air;
	}

	@Override
	public CropStage getCurrentStage(BlockState state) {
		int stage = DCState.getInt(state, DCState.STAGE5);
		if (stage == 0) {
			return CropStage.GROUND;
		} else if (stage == 3) {
			return CropStage.FLOWER;
		} else if (stage == 4)
			return CropStage.GROWN;
		return CropStage.YOUNG;
	}

	@Override
	public CropTier getTier() {
		return cropTier;
	}

	@Override
	public abstract CropType getFamily();

	@Override
	public abstract CropGrowType getGrowType(CropTier tier);

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(4)).setValue(DCState.WILD, true);
	}

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(4));
	}

	@Override
	public BlockState getHarvestedState(BlockState state) {
		return this.defaultBlockState();
	}

	@Override
	public BlockState setGroundState() {
		return this.defaultBlockState();
	}

	@Override
	public int getGrowingChance(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (stage != CropStage.GROWN && stage != CropStage.DEAD) {
			boolean clm = isSuitableForGrowing(world, pos, thisState);
			int ret = clm ? 12 : 80;
			BlockState under = world.getBlockState(pos.below());
			if (isFarmland(under)) {
				ret /= 2;
			}
			int f = FertileBlock.getFertile(world, pos.below(), under) + 1;
			if (f > 0) {
				ret /= f;
			}
			if (ret <= 0)
				ret = 1;
			return ret;
		}
		return 0;
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (stage == CropStage.DEAD) {
			return false;
		} else if (stage == CropStage.GROWN) {
			return false;
		} else {
			int age = DCState.getInt(thisState, DCState.STAGE5);
			if (age >= 0 && age < 4) {
				age++;
				BlockState next = thisState.setValue(DCState.STAGE5, age);
				return world.setBlock(pos, next, 3);
			}
		}
		return false;
	}

	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.GROWN;
	}

	@Override
	public boolean onHarvest(Level world, BlockPos pos, BlockState thisState, Player player) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			boolean d = DCState.getBool(thisState, DCState.DOUBLE);
			if (canHarvest(thisState)) {
				int f = 0;
				if (player != null && !player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
					f = player.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				}
				List<ItemStack> crops = this.getCropItems(thisState, f);
				boolean ret = false;
				for (ItemStack item : crops) {
					if (item.getItem() instanceof ItemEdibleCropDC crop && ConfigCommonBuilder.INSTANCE.enCropTaste.get()) {
						crop.setTaste(item, crop.getTier().getTaste() + getCropTaste(world, pos, thisState));
					}
					ItemEntity drop;
					if (player != null) {
						drop = new ItemEntity(world, player.getX(), player.getY() + 0.15D, player.getZ(), item);
					} else {
						drop = new ItemEntity(world, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, item);
					}
					if (drop != null && !world.isClientSide)
						world.addFreshEntity(drop);
					ret = true;
				}

				int exp = 0;
				if (this.getTier() == CropTier.RARE) {
					exp = 1;
				} else if (this.getTier() == CropTier.EPIC) {
					exp = 2;
				}
				if (exp > 0 && !world.isClientSide) {
					ExperienceOrb orb = new ExperienceOrb(world, player.getX(), player.getY() + 0.15D, player.getZ(), exp);
					world.addFreshEntity(orb);
				}

				if (ret) {
					afterHarvest(world, pos, thisState);
					if (isDoubleCrop(thisState) && canHarvest(thisState)) {
						if (d) {
							if (canHarvest(world.getBlockState(pos.below())))
								onHarvest(world, pos.below(), world.getBlockState(pos.below()), player);
						} else {
							if (canHarvest(world.getBlockState(pos.above())))
								onHarvest(world, pos.above(), world.getBlockState(pos.above()), player);
						}
					}
				}
				return ret;
			}
		}
		return false;
	}

	public int getCropTaste(Level world, BlockPos pos, BlockState thisState) {
		BlockState soil = world.getBlockState(pos.below());
		if (DCState.getBool(thisState, DCState.DOUBLE)) {
			soil = world.getBlockState(pos.below(2));
		}
		int fertile = FertileBlock.getFertile(world, pos, soil);
		if (DCState.getBool(thisState, DCState.WILD) || !isSuitableForGrowing(world, pos, thisState)) {
			if (world.getRandom().nextInt(3) == 0)
				return -1;
		} else if (fertile > 2 && world.getRandom().nextInt(3) == 0) {
			return 2;
		} else if (fertile > 0 && world.getRandom().nextInt(6 - fertile) == 0) {
			return 1;
		}
		return 0;
	}

	protected boolean isDoubleCrop(BlockState state) {
		return state.hasProperty(DCState.DOUBLE);
	}

	@Override
	public void afterHarvest(Level world, BlockPos pos, BlockState thisState) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			boolean d = DCState.getBool(thisState, DCState.DOUBLE);
			if (d) {
				world.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
			} else {
				// 自然生成物は再収穫できない
				boolean w = DCState.getBool(thisState, DCState.WILD);
				if (!w || forceDefault()) {
					BlockState next = this.getHarvestedState(thisState);
					world.setBlock(pos, next, 2);
				} else {
					world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
	}

	protected boolean forceDefault() {
		return false;
	}

	@Override
	public int getMutationChance(Level world, BlockPos pos, BlockState thisState) {
		if (getTier() == CropTier.WILD) {
			BlockState under = world.getBlockState(pos.below());
			if (!isFarmland(under))
				return 0;

			int f = FertileBlock.getFertile(world, pos.below(), under) + 1;
			return f > 0 ? f : 0;
		}
		return 0;
	}

	@Override
	public boolean onMutation(Level level, BlockPos pos, BlockState state, RandomSource random, int fertile) {
		int c1 = fertile * 5;
		int r = random.nextInt(100);
		// DCLogger.debugInfoLog("mutation: " + r);
		if (r < c1 + CropTier.EPIC.getMutationChance() && getMutationTarget(CropTier.EPIC).isPresent()) {
			BlockState next = getMutationTarget(CropTier.EPIC).get().defaultBlockState();
			return level.setBlock(pos, next, 3);
		} else if (r < c1 + CropTier.RARE.getMutationChance() && getMutationTarget(CropTier.RARE).isPresent()) {
			BlockState next = getMutationTarget(CropTier.RARE).get().defaultBlockState();
			return level.setBlock(pos, next, 3);
		} else if (getMutationTarget(CropTier.COMMON).isPresent()) {
			BlockState next = getMutationTarget(CropTier.COMMON).get().defaultBlockState();
			return level.setBlock(pos, next, 3);
		}
		return false;
	}

	public static boolean isFarmland(BlockState state) {
		return state.is(TagDC.BlockTag.MUTABLE_FARMLAND);
	}

	/* IRapidCollectables */

	@Override
	public TagKey<Item> collectableToolTag() {
		return TagDC.ItemTag.SCYTHES;
	}

	@Override
	public List<ItemStack> getDropList(Level level, BlockPos pos, BlockState state, ItemStack tool) {
		ImmutableList.Builder<ItemStack> ret = ImmutableList.builder();
		if (this.canHarvest(state)) {
			int f = tool.isEmpty() ? 0 : tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			ret.addAll(this.getCropItems(state, f));
		}
		return ret.build();
	}

	@Override
	public boolean doCollect(Level level, BlockPos pos, BlockState state, @Nullable Player player, ItemStack tool) {
		return this.onHarvest(level, pos, state, player);
	}

}
