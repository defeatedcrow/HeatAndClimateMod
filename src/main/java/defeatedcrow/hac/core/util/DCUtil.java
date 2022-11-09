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

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.tag.TagUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
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

	public static Block getBlockFromString(String name) {
		if (name == null || name.equalsIgnoreCase("empty")) {
			return Blocks.AIR;
		} else {
			String itemName = name;
			String modid = "minecraft";

			if (name.contains(":")) {
				String[] n2 = name.split(":");
				if (n2 != null && n2.length > 0) {
					if (n2.length == 1) {
						itemName = n2[0];
					} else {
						modid = n2[0];
						itemName = n2[1];
					}
				}
			}

			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, itemName));
			if (block != null && block != Blocks.AIR) {
				// DCLogger.debugTrace("Find target: " + modid + ":" + itemName);
				return block;
			} else {
				DCLogger.debugLog("Failed find target: " + modid + ":" + itemName);
			}
		}
		return Blocks.AIR;
	}

	public static List<Block> getBlockListFromStrings(String[] names, String logname) {
		List<Block> list = Lists.newArrayList();
		if (names != null && names.length > 0) {
			for (String name : names) {
				if (name != null) {
					String itemName = name;
					String modid = "minecraft";
					if (name.contains(":")) {
						String[] n2 = name.split(":");
						if (n2 != null && n2.length > 0) {
							if (n2.length == 1) {
								itemName = n2[0];
							} else {
								modid = n2[0];
								itemName = n2[1];
							}
						}
					}

					Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, itemName));
					if (block != null && block != Blocks.AIR) {
						DCLogger.infoLog(logname + " add target: " + modid + ":" + itemName);
						list.add(block);
					} else {
						DCLogger.infoLog("Failed find target: " + modid + ":" + itemName);
					}
				}
			}
		}
		return list;
	}

	public static List<Item> getItemListFromStrings(String[] names, String logname) {
		List<Item> list = Lists.newArrayList();
		if (names != null && names.length > 0) {
			for (String name : names) {
				if (name != null) {
					String itemName = name;
					String modid = "minecraft";
					if (name.contains(":")) {
						String[] n2 = name.split(":");
						if (n2 != null && n2.length > 0) {
							if (n2.length == 1) {
								itemName = n2[0];
							} else {
								modid = n2[0];
								itemName = n2[1];
							}
						}
					}

					Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, itemName));
					if (item != null && item != Items.AIR) {
						DCLogger.infoLog(logname + " add target: " + modid + ":" + itemName);
						list.add(item);
					} else {
						DCLogger.infoLog("Failed find target: " + modid + ":" + itemName);
					}
				}
			}
		}
		return list;
	}

	public static List<EntityType<?>> getEntityListFromStrings(String[] names, String logname) {
		List<EntityType<?>> list = Lists.newArrayList();
		if (names != null && names.length > 0) {
			for (String name : names) {
				if (name != null) {
					ResourceLocation res = new ResourceLocation(name);
					if (res.getNamespace().equalsIgnoreCase("minecraft")) {
						String n = res.getPath();
						res = new ResourceLocation(n);
					}
					if (ForgeRegistries.ENTITY_TYPES.containsKey(res)) {
						EntityType<?> entity = ForgeRegistries.ENTITY_TYPES.getValue(res);
						if (entity != null) {
							list.add(entity);
							DCLogger.infoLog("Registered to the update blacklist: " + name);
						}
						continue;
					}
				}
				DCLogger.infoLog("Failed find target: " + name);
			}
		}
		return list;
	}

	public static Boolean getFalse(BlockState state, BlockGetter level, BlockPos pos) {
		return (boolean) false;
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
