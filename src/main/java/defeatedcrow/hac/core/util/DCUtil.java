package defeatedcrow.hac.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.DCLogger;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class DCUtil {

	public static Random rand = new Random();

	public static final ResourceLocation DUMMY = new ResourceLocation("dcs_climate:empty");

	public static Optional<ResourceLocation> getRes(Item item) {
		return Optional.ofNullable(ForgeRegistries.ITEMS.getKey(item));
	}

	public static Optional<ResourceLocation> getRes(Block block) {
		return Optional.ofNullable(ForgeRegistries.BLOCKS.getKey(block));
	}

	public static Optional<ResourceLocation> getLocationName(Holder<?> holder) {
		return holder.unwrap().map((res) -> {
			return Optional.ofNullable(res.location());
		}, (b) -> {
			return Optional.empty();
		});
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
