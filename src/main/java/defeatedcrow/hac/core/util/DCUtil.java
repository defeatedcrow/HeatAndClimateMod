package defeatedcrow.hac.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class DCUtil {

	public static Random rand = new Random();

	public static int getRandomSign() {
		return rand.nextBoolean() ? 1 : -1;
	}

	public static final ResourceLocation DUMMY = new ResourceLocation("dcs_climate:empty");

	public static boolean isEmpty(ItemStack item) {
		if (item == null) {
			item = ItemStack.EMPTY;
			return true;
		}
		return item.getItem() == null || item.getItem() == Items.AIR || item.isEmpty();
	}

	public static boolean canEditPos(Level level, BlockPos pos) {
		return pos.getY() <= level.getMaxBuildHeight() && pos.getY() >= level.getMinBuildHeight() && level.isLoaded(pos);
	}

	public static Optional<ResourceLocation> getRes(Item item) {
		return Optional.ofNullable(ForgeRegistries.ITEMS.getKey(item));
	}

	public static Optional<ResourceLocation> getRes(Block block) {
		return Optional.ofNullable(ForgeRegistries.BLOCKS.getKey(block));
	}

	public static Optional<ResourceLocation> getRes(Fluid fluid) {
		return Optional.ofNullable(ForgeRegistries.FLUIDS.getKey(fluid));
	}

	public static String getName(Item item) {
		return getRes(item).map(ResourceLocation::getPath).orElse("empty");
	}

	public static String getName(Block block) {
		return getRes(block).map(ResourceLocation::getPath).orElse("empty");
	}

	public static String getName(TagKey<Item> tag) {
		return tag.location().getPath();
	}

	public static Optional<ResourceLocation> getLocationName(Holder<?> holder) {
		return holder.unwrap().map(res -> Optional.ofNullable(res.location()), b -> Optional.empty());
	}

	public static String getBlockRegName(Block block) {
		return getRes(block).map(res -> (res.getNamespace() + ":" + res.getPath())).orElse("empty");
	}

	public static Boolean getFalse(BlockState state, BlockGetter level, BlockPos pos) {
		return (boolean) false;
	}

	public static boolean setBlockIfReplaceable(Level level, BlockPos pos, BlockState set, boolean needAir) {
		if (!level.getBlockState(pos).is(BlockTags.FEATURES_CANNOT_REPLACE) && level.getBlockState(pos).getMaterial().isReplaceable() && (!needAir || level.getBlockState(pos).getBlock() == Blocks.AIR)) {
			return level.setBlock(pos, set, 2);
		}
		return false;
	}

	public static float getPotionResistantData(LivingEntity living, boolean isCold) {
		float prev = 0F;
		if (!isCold && living.hasEffect(MobEffects.FIRE_RESISTANCE)) {
			int f = living.getEffect(MobEffects.FIRE_RESISTANCE).getAmplifier() + 1;
			prev += 4.0F * f;
		}
		if (isCold && living.hasEffect(CoreInit.COLD_RESISTANCE.get())) {
			int f = living.getEffect(CoreInit.COLD_RESISTANCE.get()).getAmplifier() + 1;
			prev += 4.0F * f;
		}

		// 濡れ状態
		if (living.hasEffect(CoreInit.WET.get())) {
			if (isCold) {
				if (!living.isInWater())
					prev -= 1.0F;
			} else {
				prev += 1.0F;
			}
		}
		return prev;
	}

	public static boolean removeBadPotion(LivingEntity liv) {
		if (liv != null && !liv.getLevel().isClientSide) {
			List<MobEffect> remove = Lists.newArrayList();
			for (MobEffectInstance p : liv.getActiveEffects()) {
				if (p.getEffect().getCategory() == MobEffectCategory.HARMFUL)
					remove.add(p.getEffect());
			}

			for (MobEffect p2 : remove) {
				liv.removeEffect(p2);
			}
			return !remove.isEmpty();
		}

		return false;
	}

	public static Direction getDirectionInBlock(float x, float y, float z) {
		double x1 = Mth.absMax(1D - x, x);
		double y1 = Mth.absMax(1D - y, y);
		double z1 = Mth.absMax(1D - z, z);
		double x2 = absMin(1D - x, x);
		double y2 = absMin(1D - y, y);
		double z2 = absMin(1D - z, z);
		if (y > 0.5D && y > x1 && y > z1) {
			return Direction.UP;
		}
		if (y < 0.5D && y < x2 && y < z2) {
			return Direction.DOWN;
		}
		if (z > 0.5D && z > x1 && z > y1) {
			return Direction.SOUTH;
		}
		if (z < 0.5D && z < x2 && z < y2) {
			return Direction.NORTH;
		}
		if (x > 0.5D && x > y1 && x > z1) {
			return Direction.EAST;
		}
		if (x < 0.5D && x < y2 && x < z2) {
			return Direction.WEST;
		}
		return Direction.DOWN;
	}

	public static Direction[] PipeScanList = { Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP };

	public static double absMin(double a, double b) {
		if (a < 0.0D) {
			a = -a;
		}
		if (b < 0.0D) {
			b = -b;
		}
		return a > b ? b : a;
	}

	public static int getFoodTaste(ItemStack item) {
		if (item.isEmpty())
			return 0;
		if (item.getItem() instanceof IFoodTaste food) {
			return food.getTaste(item);
		} else if (item.getTag() != null && item.getTag().contains(TagKeyDC.TASTE)) {
			int taste = item.getTag().getInt(TagKeyDC.TASTE);
			taste = Mth.clamp(taste, -2, 2);
			return taste;
		} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR)) {
			if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR5)) {
				return 2;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR4)) {
				return 1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR3)) {
				return 0;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR2)) {
				return -1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR1)) {
				return -2;
			}
		}
		return 0;
	}

	public static void setFoodTaste(ItemStack item, int i) {
		if (!item.isEmpty()) {
			int taste = Mth.clamp(i, -2, 2);
			CompoundTag tag = item.getOrCreateTag();
			tag.putInt(TagKeyDC.TASTE, taste);
			item.setTag(tag);
		}
	}

	static final List<ItemStack> fishList = Lists.newArrayList();

	public static List<ItemStack> getFish(Level level, Holder<Biome> biome, LivingEntity living, ItemStack held) {
		List<ItemStack> fishes = Lists.newArrayList();
		if (fishList.isEmpty()) {
			// 一度だけ取得する
			fishList.addAll(TagUtil.getItemList(TagDC.ItemTag.FISH_ROD));
		}

		boolean isMangrove = biome.is(Biomes.MANGROVE_SWAMP);
		boolean isBeach = biome.is(BiomeTags.IS_BEACH) || biome.is(Biomes.STONY_SHORE);
		boolean isOcean = biome.is(BiomeTags.IS_OCEAN);
		boolean isDeepOcean = biome.is(BiomeTags.IS_DEEP_OCEAN);
		boolean isRiver = !isMangrove && !isBeach && !isOcean && !isDeepOcean;

		int big = held.getEnchantmentLevel(CoreInit.BIG_GAME_FISHING.get());
		int bottom = held.getEnchantmentLevel(CoreInit.BOTTOM_FISHING.get());
		int squid = held.getEnchantmentLevel(CoreInit.SQUID_FISHING.get());
		int luck = held.getEnchantmentLevel(Enchantments.FISHING_SPEED);
		if (living.hasEffect(MobEffects.LUCK)) {
			MobEffectInstance li = living.getEffect(MobEffects.LUCK);
			luck += li.getAmplifier() + 1;
		}
		if (held.is(TagDC.ItemTag.MAGIC_CARD)) {
			luck += 3;
		}

		int time = DCTimeHelper.currentTime(level);
		boolean day = time > 7 && time < 17;
		boolean night = time < 5 || time > 19;

		float temp = biome.get().getBaseTemperature();
		boolean cold = temp < 0.3F && biome.is(Tags.Biomes.IS_COLD);
		boolean warm = temp > 0.3F && biome.is(Tags.Biomes.IS_HOT);

		int rand = level.random.nextInt(100);
		if (!day && !night) {
			// マズメは確率アップ
			rand -= 10;
		}
		if (level.isRaining()) {
			// 雨天時
			rand -= 10;
		}
		rand -= luck * 10;

		List<ItemStack> cL = Lists.newArrayList();
		List<ItemStack> uL = Lists.newArrayList();
		List<ItemStack> rL = Lists.newArrayList();
		for (ItemStack fish : fishList) {
			if (night && fish.is(TagDC.ItemTag.FISH_DAY))
				continue;
			else if (day && fish.is(TagDC.ItemTag.FISH_NIGHT))
				continue;
			if (cold && fish.is(TagDC.ItemTag.FISH_TROPICAL))
				continue;
			else if (warm && fish.is(TagDC.ItemTag.FISH_COLD_WATER))
				continue;

			if (big > 0 && level.getRandom().nextInt(big + 1) > 0 && !fish.is(TagDC.ItemTag.FISH_LARGE)) {
				continue;
			} else if (bottom > 0 && level.getRandom().nextInt(bottom + 1) > 0 && !fish.is(TagDC.ItemTag.FISH_FLOOR)) {
				continue;
			} else if (squid > 0 && level.getRandom().nextInt(squid + 1) > 0 && !fish.is(TagDC.ItemTag.SQUID)) {
				continue;
			}

			boolean flag = false;
			if (isMangrove && fish.is(TagDC.ItemTag.FISH_MANGROVE))
				flag = true;
			if (isBeach && fish.is(TagDC.ItemTag.FISH_BEACH))
				flag = true;
			if (isOcean && fish.is(TagDC.ItemTag.FISH_OCEAN))
				flag = true;
			if (isDeepOcean && fish.is(TagDC.ItemTag.FISH_DEEP_OCEAN))
				flag = true;
			if (isRiver && fish.is(TagDC.ItemTag.FISH_RIVER))
				flag = true;

			if (flag) {
				if (fish.getRarity() == Rarity.RARE)
					rL.add(fish.copy());
				else if (fish.getRarity() == Rarity.UNCOMMON)
					uL.add(fish.copy());
				else
					cL.add(fish.copy());
			}
		}

		DCLogger.debugInfoLog("*** HaC fishing ***");
		DCLogger.debugInfoLog("Fishing rand" + rand);
		if (rand < 15 || big > 0 || bottom > 0 || squid > 0) {
			if (!rL.isEmpty()) {
				fishes.addAll(rL);
			}
			if (!uL.isEmpty()) {
				fishes.addAll(uL);
			}
			if (!cL.isEmpty()) {
				fishes.addAll(cL);
			}
		} else if (rand < 50) {
			if (!uL.isEmpty()) {
				fishes.addAll(uL);
			}
			if (!cL.isEmpty()) {
				fishes.addAll(cL);
			}
		} else if (!cL.isEmpty()) {
			fishes.addAll(cL);
		}

		DCLogger.debugInfoLog("Fish list: " + (fishes.isEmpty() ? "empty" : fishes.size()));
		if (!fishes.isEmpty()) {
			for (ItemStack choice : fishes) {
				DCLogger.debugInfoLog("fish:" + choice.getDisplayName().getString());
			}
		}
		if (fishes.isEmpty()) {
			ItemStack replace = ItemStack.EMPTY;
			if (isMangrove)
				replace = new ItemStack(Items.TROPICAL_FISH);
			else if (isBeach)
				replace = new ItemStack(Items.PUFFERFISH);
			else if (isOcean)
				replace = new ItemStack(Items.COD);
			else if (isDeepOcean)
				replace = new ItemStack(Items.COD);
			else if (isRiver)
				replace = new ItemStack(Items.SALMON);
			else
				replace = new ItemStack(Items.STRING);
			if (!replace.isEmpty()) {
				fishes.add(replace);
			}
		}

		return fishes;
	}

	/**
	 * ruby氏に感謝!
	 * @date 2020.02.04
	 * @author ruby
	 */
	public static Set<BlockPos> getConnectedTargetList(Level world, BlockPos pos, Block block, int limit) {
		List<BlockPos> nextTargets = new ArrayList<>();
		nextTargets.add(pos);
		Set<BlockPos> founds = new LinkedHashSet<>();
		if (limit < 1) {
			return founds;
		}
		do {
			nextTargets = nextTargets.stream().flatMap(target -> Arrays.stream(Direction.values()).map(target::relative)).filter(fixedPos -> world.getBlockState(fixedPos).getBlock().equals(block)).limit(limit - founds.size()).filter(
			    founds::add).collect(Collectors.toList());

		} while (founds.size() <= limit && !nextTargets.isEmpty());

		return founds;
	}

	public static List<BlockPos> findLog(BlockGetter world, BlockPos pos, Block block, int limit, boolean includeLeaves) {
		List<BlockPos> nextTargets = new ArrayList<>();
		nextTargets.add(pos);
		List<BlockPos> logs = new ArrayList<>();
		Set<BlockPos> founds = new LinkedHashSet<>();
		do {
			nextTargets = nextTargets.stream().flatMap(target -> Arrays.stream(Direction.values()).map(target::relative)).filter(
			    fixedPos -> (world.getBlockState(fixedPos).is(BlockTags.LEAVES) || world.getBlockState(fixedPos).is(BlockTags.LOGS))).limit(limit - founds.size()).filter(founds::add).collect(Collectors.toList());

		} while (founds.size() <= limit && logs.isEmpty() && !nextTargets.isEmpty());

		if (includeLeaves) {
			logs = founds.stream().filter(p -> world.getBlockState(p).is(block) || world.getBlockState(p).is(BlockTags.LEAVES)).toList();
		} else {
			logs = founds.stream().filter(p -> world.getBlockState(p).is(block)).toList();
		}

		return logs;
	}

	// デバッグモード
	public static boolean checkDebugModePass(String pass) {
		byte[] b = null;
		String get = "";
		MessageDigest md5;

		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(pass.getBytes());
			b = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			DCLogger.LOGGER.warn("Failed to check password...", e);
		}

		get = getStringFromBytes(b);
		DCLogger.debugLog("Get String : " + get);

		if (!get.isEmpty()) {
			boolean match = get.matches("7805f2fa0adc68cd9a8f7cb2135e0b57");
			DCLogger.infoLog("DebugMode : " + match);
			return match;
		}

		return true;
	}

	private static String getStringFromBytes(byte[] b) {

		StringBuilder builder = new StringBuilder();
		for (byte element : b) {

			if ((element & 0xff) < 0x10) {
				builder.append("0");
			}
			builder.append(Integer.toHexString(0xff & element));
		}

		return builder.toString();
	}

}
