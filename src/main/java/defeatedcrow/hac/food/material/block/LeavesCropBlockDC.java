package defeatedcrow.hac.food.material.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.crop.ICropData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
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
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

public abstract class LeavesCropBlockDC extends BlockDC implements IClimateCrop, IBlockDC, IJsonDataDC, ICropData {

	final CropType cropType;
	final CropTier cropTier;

	private EnumSeason flowerSeason = EnumSeason.SUMMER;
	private EnumSeason cropSeason = EnumSeason.AUTUMN;

	public LeavesCropBlockDC(CropType f, CropTier t) {
		super(getProp());
		cropType = f;
		cropTier = t;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, Integer.valueOf(0)).setValue(DCState.FLAG, false));
	}

	public LeavesCropBlockDC setSeason(EnumSeason flower, EnumSeason crop) {
		flowerSeason = flower;
		cropSeason = crop;
		return this;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FLAG, DCState.STAGE6);
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
		int stage = state.getValue(DCState.STAGE6);
		int next = this.getSeasonLeafStage(level);

		if (stage != next) {
			onGrow(level, pos, level.getBlockState(pos));
			return;
		}

		checkAndDropBlock(level, pos, state);

	}

	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		if (DCState.getBool(state, DCState.FLAG) && !findLog(world, pos, this, 24)) {
			if (!world.isClientSide)
				dropResources(state, world, pos);
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
		}
	}

	public static boolean findLog(BlockGetter world, BlockPos pos, Block block, int limit) {
		List<BlockPos> leaves = new ArrayList<>();
		List<BlockPos> log = new ArrayList<>();
		List<BlockPos> found = new ArrayList<>();
		List<BlockPos> next = new ArrayList<>();
		leaves.add(pos);
		found.add(pos);
		for (int i = 0; i < limit; i++) {
			for (BlockPos p : leaves) {
				Arrays.stream(Direction.values()).map(p::relative).forEach(p2 -> {
					if (world.getBlockState(p2).getBlock() instanceof LogBlockDC) {
						log.add(p2);
					} else if (world.getBlockState(p2).getBlock().equals(block) && !found.contains(p2)) {
						found.add(p2);
						if (!next.contains(p2))
							next.add(p2);
					}
				});
				if (!log.isEmpty())
					return true;
			}

			if (next.isEmpty())
				return false;

			leaves.clear();
			leaves.addAll(next);
			next.clear();
		}

		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof IClimateCrop) {
			IClimateCrop crop = (IClimateCrop) state.getBlock();
			ServerLevel level = builder.getLevel();

			CropStage stage = crop.getCurrentStage(state);
			CropTier tier = crop.getTier();

			float seedChance = 0.1F;
			if (level.random.nextFloat() <= seedChance) {
				ret.add(crop.getSeedItem(state));
			}
			if (crop.canHarvest(state)) {
				ItemStack tool = builder.getParameter(LootContextParams.TOOL);
				int f = tool.isEmpty() ? 0 : tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
				ret.addAll(crop.getCropItems(state, f));
			}
		}
		return ret;
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
		ItemStack ret = new ItemStack(getCropItem(cropTier));
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
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity) {
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
	public BlockState getGrownState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, Integer.valueOf(5));
	}

	@Override
	public BlockState getHarvestedState(BlockState state) {
		return this.defaultBlockState().setValue(DCState.STAGE6, Integer.valueOf(cropSeason.id));
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
		int next = this.getSeasonLeafStage(level);
		BlockState nextState = state.setValue(DCState.STAGE6, next);
		level.setBlock(pos, nextState, 2);
		return true;
	}

	public int getSeasonLeafStage(Level world) {
		int day = DCTimeHelper.getDay(world);
		EnumSeason season = DCTimeHelper.getSeasonEnum(world);
		if (season == cropSeason)
			return 5;
		else if (season == flowerSeason)
			return 4;
		else
			return season.id;
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
					BlockState next = this.getHarvestedState(thisState);
					world.setBlock(pos, next, 2);
				}
				return ret;
			}
		}
		return false;
	}

	// 変異なし

	@Override
	public int getMutationChance(Level world, BlockPos pos, BlockState thisState) {
		return 1;
	}

	@Override
	public boolean onMutation(Level level, BlockPos pos, BlockState state, RandomSource random) {
		return false;
	}

}
