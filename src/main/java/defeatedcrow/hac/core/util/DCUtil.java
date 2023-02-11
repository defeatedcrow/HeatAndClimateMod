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

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class DCUtil {

	private static final IForgeRegistry<Item> regItem = ForgeRegistries.ITEMS;

	public static Random rand = new Random();

	public static final ResourceLocation DUMMY = new ResourceLocation("dcs_climate:empty");

	public static boolean isEmpty(ItemStack item) {
		if (item == null) {
			item = ItemStack.EMPTY;
			return true;
		}
		return item.getItem() == null || item.getItem() == Items.AIR || item.isEmpty();
	}

	public static Optional<ResourceLocation> getRes(Item item) {
		return Optional.ofNullable(ForgeRegistries.ITEMS.getKey(item));
	}

	public static Optional<ResourceLocation> getRes(Block block) {
		return Optional.ofNullable(ForgeRegistries.BLOCKS.getKey(block));
	}

	public static String getName(Item item) {
		return getRes(item).map((res) -> {
			return res.getPath();
		}).orElse("empty");
	}

	public static String getName(Block block) {
		return getRes(block).map((res) -> {
			return res.getPath();
		}).orElse("empty");
	}

	public static String getName(TagKey<Item> tag) {
		return tag.location().getPath();
	}

	public static Optional<ResourceLocation> getLocationName(Holder<?> holder) {
		return holder.unwrap().map((res) -> {
			return Optional.ofNullable(res.location());
		}, (b) -> {
			return Optional.empty();
		});
	}

	public static String getBlockRegName(Block block) {
		return getRes(block).map((res) -> {
			return res.getNamespace() + ":" + res.getPath();
		}).orElse("empty");
	}

	public static Boolean getFalse(BlockState state, BlockGetter level, BlockPos pos) {
		return (boolean) false;
	}

	public static boolean setBlockIfReplaceable(Level level, BlockPos pos, BlockState set, boolean needAir) {
		if (level.getBlockState(pos).getMaterial().isReplaceable() && (!needAir || level.getBlockState(pos).getBlock() == Blocks.AIR)) {
			return level.setBlock(pos, set, 2);
		}
		return false;
	}

	public static float getPotionResistantData(LivingEntity living, boolean isCold) {
		float prev = 0F;
		if (!isCold && living.hasEffect(MobEffects.FIRE_RESISTANCE)) {
			prev += 4.0F;
		}
		if (isCold && living.hasEffect(CoreInit.COLD_RESISTANCE.get())) {
			prev += 4.0F;
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

	/**
	 * ruby氏に感謝!
	 *
	 * @date 2020.02.04
	 * @author ruby
	 */
	public static Set<BlockPos> getLumberTargetList(Level world, BlockPos pos, Block block, int limit) {
		List<BlockPos> nextTargets = new ArrayList<>();
		nextTargets.add(pos);
		Set<BlockPos> founds = new LinkedHashSet<>();
		do {
			nextTargets = nextTargets.stream().flatMap(target -> Arrays.stream(Direction.values()).map(target::relative))
				.filter(fixedPos -> world.getBlockState(fixedPos).getBlock().equals(block)).limit(limit - founds
					.size()).filter(founds::add).collect(Collectors.toList());

		} while (founds.size() <= limit && !nextTargets.isEmpty());

		return founds;
	}

	public static boolean findLog(BlockGetter world, BlockPos pos, Block block, int limit) {
		List<BlockPos> nextTargets = new ArrayList<>();
		nextTargets.add(pos);
		Set<BlockPos> founds = new LinkedHashSet<>();
		do {
			nextTargets = nextTargets.stream().flatMap(target -> Arrays.stream(Direction.values()).map(target::relative))
				.filter(fixedPos -> world.getBlockState(fixedPos).getBlock().equals(block) || (TagUtil.matchTag("logs", world.getBlockState(fixedPos)).isPresent() && founds.add(fixedPos)))
				.limit(limit - founds.size()).collect(Collectors.toList());

		} while (founds.isEmpty() && !nextTargets.isEmpty());

		return !founds.isEmpty();
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
		for (int i = 0; i < b.length; i++) {

			if ((b[i] & 0xff) < 0x10) {
				builder.append("0");
			}
			builder.append(Integer.toHexString(0xff & b[i]));
		}

		return builder.toString();
	}

}