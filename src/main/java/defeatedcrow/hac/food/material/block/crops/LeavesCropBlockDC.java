package defeatedcrow.hac.food.material.block.crops;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.crop.ICropData;
import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.Tags;

public abstract class LeavesCropBlockDC extends BlockDC implements IClimateCrop, IBlockDC, IJsonDataDC, ICropData, IForgeShearable, IRapidCollectables {

	final CropType cropType;
	final CropTier cropTier;
	final boolean defoliation;

	public final List<EnumSeason> flowerSeasons = Lists.newArrayList();
	public final List<EnumSeason> cropSeasons = Lists.newArrayList();

	public LeavesCropBlockDC(CropType f, CropTier t, boolean isDefoliation) {
		super(getProp());
		cropType = f;
		cropTier = t;
		defoliation = isDefoliation;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, Integer.valueOf(0)).setValue(DCState.FLAG, false).setValue(DCState.DIST, Integer.valueOf(0)));
	}

	public LeavesCropBlockDC setSeason(EnumSeason flower, EnumSeason crop) {
		flowerSeasons.add(flower);
		cropSeasons.add(crop);
		return this;
	}

	/* model */

	protected String texName() {
		return cropType.toString() + "_" + this.getSpeciesName(cropTier);
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		if (defoliation) {
			return ImmutableList.of(
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_spr")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_smr")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_aut")),
					new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_wtr")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_f")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_c")));
		} else {
			return ImmutableList.of(
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_d")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_f")),
					new JsonModelDC("dcs_climate:block/dcs_leaves", ImmutableMap.of("all", "dcs_climate:block/tree/" + texName() + "_leaves_c")));
		}
	}

	@Override
	public List<String> getModelNameSuffix() {
		if (defoliation)
			return Lists.newArrayList("0", "1", "2", "3", "4", "5");
		else
			return Lists.newArrayList("0", "0", "0", "d", "f", "c");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.DIST, DCState.FLAG, DCState.STAGE6);
	}

	/* 基本データ */
	protected static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isSuffocating(DCUtil::getFalse).isViewBlocking(DCUtil::getFalse);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 2))
			return;

		if (onGrow(level, pos, level.getBlockState(pos))) {
			return;
		}

		checkAndDropBlock(level, pos, state);

	}

	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		int pre = DCState.getInt(state, DCState.DIST);
		world.setBlock(pos, updateDistance(state, world, pos), 3);
		int post = DCState.getInt(state, DCState.DIST);
		if (DCState.getBool(state, DCState.FLAG)) {
			if (post == 7) {
				if (!world.isClientSide)
					dropResources(state, world, pos);
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			} else if (pre != post) {
				world.scheduleTick(pos, this, 5);
				world.updateNeighborsAt(pos, this);
			}
		}
	}

	private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
		int pre = DCState.getInt(state, DCState.DIST);
		int i = 7;
		BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();

		for (Direction direction : Direction.values()) {
			mpos.setWithOffset(pos, direction);
			i = Math.min(i, getDistanceAt(level.getBlockState(mpos)) + 1);
			if (i > 7) {
				i = 7;
			}
			if (i == 1) {
				break;
			}
		}
		return DCState.setInt(state, DCState.DIST, i);
	}

	private static int getDistanceAt(BlockState state) {
		if (state.is(BlockTags.LOGS)) {
			return 0;
		} else if (state.getBlock() instanceof LogBlockDC) {
			return 0;
		} else if (state.getBlock() instanceof LeavesCropBlockDC) {
			return DCState.getInt(state, DCState.DIST);
		} else if (state.is(BlockTags.LEAVES)) {
			if (state.hasProperty(BlockStateProperties.DISTANCE)) {
				return DCState.getInt(state, BlockStateProperties.DISTANCE);
			} else if (state.hasProperty(DCState.DIST)) {
				return DCState.getInt(state, DCState.DIST);
			} else {
				return 3;
			}
		}
		return 7;
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

			if (tool.is(Tags.Items.SHEARS) || tool.is(TagDC.ItemTag.SCYTHES) || tool.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0) {
				ret.add(new ItemStack(this));
			}

			CropStage stage = crop.getCurrentStage(state);
			CropTier tier = crop.getTier();

			float seedChance = getSeedChance();
			if (level.random.nextFloat() <= seedChance) {
				ret.add(crop.getSeedItem(state));
			}
			if (crop.canHarvest(state)) {
				int f = tool.isEmpty() ? 0 : tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				ret.addAll(crop.getCropItems(state, f));
			}
		}
		return ret;
	}

	protected float getSeedChance() {
		return 0.05F;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
		if (player.getItemInHand(hand).isEmpty()) {
			if (onHarvest(level, pos, state, player))
				return InteractionResult.SUCCESS;
		}
		return super.use(state, level, pos, player, hand, res);
	}

	@Override
	public boolean isSuitablePlace(BlockGetter world, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public ItemStack getSeedItem(BlockState thisState) {
		return new ItemStack(getSeedItem(cropTier));
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		int i = 1;
		if (fortune > 0) {
			int rand = DCUtil.rand.nextInt(fortune * 100);
			int count = rand / 100;
			int f = count % 100;
			i += count + DCUtil.rand.nextInt(f + 1);
		}
		ItemStack ret = new ItemStack(getCropItem(cropTier), i);
		return ImmutableList.of(ret);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos down = pos.below();
		return isSuitablePlace(level, down, level.getBlockState(down));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	/* IBlockDC */

	@Override
	public ItemStack getMainDrop() {
		return this.getSeedItem(getGrownState());
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
		int stage = DCState.getInt(state, DCState.STAGE6);
		if (stage == 4) {
			return CropStage.FLOWER;
		} else if (stage == 5)
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
		return this.defaultBlockState();
	}

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, Integer.valueOf(5));
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
	public int getGrowingChance(Level world, BlockPos pos, BlockState state) {
		return 5;
	}

	@Override
	public boolean onGrow(Level level, BlockPos pos, BlockState state) {
		int current = DCState.getInt(state, DCState.STAGE6);
		int next = this.getSeasonLeafStage(level, pos, state);
		BlockState nextState = state.setValue(DCState.STAGE6, next);
		level.setBlock(pos, nextState, 2);
		return current != next;
	}

	public int getSeasonLeafStage(Level world, BlockPos pos, BlockState state) {
		EnumSeason season = DCTimeHelper.getSeasonEnum(world);
		ClimateSupplier spr = new ClimateSupplier(world, pos);
		IClimate climate = spr.get();

		// 暑すぎる場合
		if (tooHot(climate.getHeat())) {
			return EnumSeason.SUMMER_EARLY.getSeasonLimitedID();
		}
		// 寒すぎる場合
		if (tooCold(climate.getHeat())) {
			int cur = DCState.getInt(state, DCState.STAGE6);
			if (cur == EnumSeason.AUTUMN_EARLY.getSeasonLimitedID())
				return EnumSeason.WINTER_EARLY.getSeasonLimitedID();
			else
				return EnumSeason.AUTUMN_EARLY.getSeasonLimitedID();
		}

		if (cropSeasons.contains(season))
			return 5;
		else if (flowerSeasons.contains(season))
			return 4;
		else if (defoliation)
			return season.getSeasonLimitedID();
		else
			return 0; // 春固定
	}

	private boolean tooHot(DCHeatTier heat) {
		for (DCHeatTier check : this.getSuitableTemp(cropTier)) {
			if (heat.getTier() <= check.getTier())
				return false;
		}
		return true;
	}

	private boolean tooCold(DCHeatTier heat) {
		for (DCHeatTier check : this.getSuitableTemp(cropTier)) {
			if (heat.getTier() >= check.getTier())
				return false;
		}
		return true;
	}

	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.GROWN;
	}

	@Override
	public boolean onHarvest(Level world, BlockPos pos, BlockState thisState, Player player) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			if (canHarvest(thisState)) {
				int f = 0;
				if (player != null && !player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
					f = player.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				}
				List<ItemStack> crops = this.getCropItems(thisState, f);
				boolean ret = false;
				for (ItemStack item : crops) {
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
				if (ret) {
					afterHarvest(world, pos, thisState);
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
				return ret;
			}
		}
		return false;
	}

	@Override
	public void afterHarvest(Level world, BlockPos pos, BlockState thisState) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			BlockState next = this.getHarvestedState(thisState);
			int m = this.getSeasonLeafStage(world, pos, next);
			if (m > 3) {
				m = DCTimeHelper.getSeasonEnum(world).getSeasonLimitedID();
			}
			world.setBlock(pos, next.setValue(DCState.STAGE6, m), 2);
		}
	}

	// 変異なし

	@Override
	public int getMutationChance(Level world, BlockPos pos, BlockState thisState) {
		return 1;
	}

	@Override
	public boolean onMutation(Level level, BlockPos pos, BlockState state, RandomSource random, int fertile) {
		return false;
	}

	// IForgeShearable
	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level level, BlockPos pos, int fortune) {
		List<ItemStack> ret = Lists.newArrayList();
		ret.add(new ItemStack(this));

		BlockState state = level.getBlockState(pos);
		if (canHarvest(state)) {
			ret.addAll(this.getCropItems(level.getBlockState(pos), fortune));
		}

		return ret;
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
