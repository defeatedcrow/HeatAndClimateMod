package defeatedcrow.hac.main.util;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.plugin.baubles.DCPluginBaubles;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class MainUtil {

	public static final String BR = System.getProperty("line.separator");

	public static final String[] DYES = {
			"dyeWhite",
			"dyeOrange",
			"dyeMagenta",
			"dyeLightBlue",
			"dyeYellow",
			"dyeLime",
			"dyePink",
			"dyeGray",
			"dyeLightGray",
			"dyeCyan",
			"dyePurple",
			"dyeBlue",
			"dyeBrown",
			"dyeGreen",
			"dyeRed",
			"dyeBlack",
	};

	public static TextFormatting[] DYE_CHAT_COLOR = {
			TextFormatting.WHITE,
			TextFormatting.GOLD,
			TextFormatting.LIGHT_PURPLE,
			TextFormatting.AQUA,
			TextFormatting.YELLOW,
			TextFormatting.GREEN,
			TextFormatting.LIGHT_PURPLE,
			TextFormatting.DARK_GRAY,
			TextFormatting.GRAY,
			TextFormatting.DARK_AQUA,
			TextFormatting.DARK_PURPLE,
			TextFormatting.BLUE,
			TextFormatting.DARK_RED,
			TextFormatting.GREEN,
			TextFormatting.RED,
			TextFormatting.DARK_GRAY
	};

	public static ItemStack getIngot(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 15)
			meta = 15;
		return new ItemStack(MainInit.oreIngot, 1, meta);
	}

	public static ItemStack getGem(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 22)
			meta = 22;
		return new ItemStack(MainInit.gems, 1, meta);
	}

	public static ItemStack getRandomGem(int i) {
		int meta = DCUtil.rand.nextInt(23);
		if (meta == 7)
			meta = 4;
		return new ItemStack(MainInit.gems, i, meta);
	}

	public static ItemStack getRandomGem2(int i) {
		int meta = 4 + DCUtil.rand.nextInt(18);
		if (meta == 7)
			meta = 4;
		return new ItemStack(MainInit.gems, i, meta);
	}

	public static ItemStack getCrop(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 12)
			meta = 12;
		return new ItemStack(FoodInit.crops, 1, meta);
	}

	public static ItemStack getRandomCrop(int i) {
		int meta = DCUtil.rand.nextInt(18);
		return new ItemStack(FoodInit.crops, i, meta);
	}

	public static ItemStack getSeed(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 9)
			meta = 9;
		return new ItemStack(FoodInit.seeds, 1, meta);
	}

	public static ItemStack getRandomSeed(int i) {
		int meta = DCUtil.rand.nextInt(14);
		return new ItemStack(FoodInit.seeds, i, meta);
	}

	public static ItemStack getCloth(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 9)
			meta = 9;
		return new ItemStack(MainInit.clothes, 1, meta);
	}

	public static ItemStack getBasket(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 11)
			meta = 11;
		return new ItemStack(MainInit.cropBasket, 1, meta);
	}

	public static ItemStack getRandomBasket(int i) {
		int meta = DCUtil.rand.nextInt(12);
		return new ItemStack(MainInit.cropBasket, i, meta);
	}

	public static ItemStack getRandomBag(int i) {
		int meta = DCUtil.rand.nextInt(7);
		return new ItemStack(MainInit.dustBags, i, meta);
	}

	public static ItemStack getRandomCoating(int i) {
		int meta = DCUtil.rand.nextInt(9);
		return new ItemStack(MachineInit.platingChrome, i, meta);
	}

	public static ItemStack getRandomSapling(int i) {
		int meta = DCUtil.rand.nextInt(4);
		return new ItemStack(FoodInit.saplings, i, meta);
	}

	public static boolean removeBadPotion(EntityLivingBase liv) {
		if (liv != null && !liv.getEntityWorld().isRemote) {
			List<PotionEffect> remove = Lists.newArrayList();
			for (PotionEffect p : liv.getActivePotionEffects()) {
				if (p.getPotion().isBadEffect())
					remove.add(p);
			}

			for (PotionEffect p2 : remove) {
				liv.removeActivePotionEffect(p2.getPotion());
			}
			return !remove.isEmpty();
		}

		return false;
	}

	public static boolean isPlayerHeldItem(Item item, EntityPlayer player) {
		if (item == null || player == null)
			return false;

		return isPlayerHeldItem(new ItemStack(item, 1, 0), player);
	}

	public static boolean isPlayerHeldItem(ItemStack item, EntityPlayer player) {
		if (DCUtil.isEmpty(item) || player == null)
			return false;

		if (player.getHeldItem(EnumHand.MAIN_HAND) != null) {
			if (DCUtil.isSameItem(item, player.getHeldItem(EnumHand.MAIN_HAND), false))
				return true;
		}
		if (player.getHeldItem(EnumHand.OFF_HAND) != null) {
			if (DCUtil.isSameItem(item, player.getHeldItem(EnumHand.OFF_HAND), false))
				return true;
		}

		return false;
	}

	public static boolean hasCharmItem(EntityLivingBase living, ItemStack charm) {
		if (living == null || DCUtil.isEmpty(charm) || !(charm.getItem() instanceof IJewelCharm)) {
			return false;
		}
		if (living instanceof EntityPlayer) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = ((EntityPlayer) living).inventory.getStackInSlot(i);
				if (!DCUtil.isEmpty(check) && check.getItem() == charm.getItem()) {
					int m = check.getMetadata();
					if (m == charm.getItemDamage()) {
						hasCharm = true;
					}
				}
			}

			if (Loader.isModLoaded("baubles") && !hasCharm) {
				if (DCPluginBaubles.hasBaublesCharm(((EntityPlayer) living), charm)) {
					hasCharm = true;
				}
			}
			return hasCharm;
		} else {
			Map<Integer, ItemStack> map = DCUtil.getMobCharm(living);
			boolean hasCharm = false;
			if (!map.isEmpty()) {
				for (ItemStack item : map.values()) {
					if (item.getItem() == charm.getItem() && item.getItemDamage() == charm.getItemDamage()) {
						hasCharm = true;
					}
				}
			}
			return hasCharm;
		}
	}

	public static ItemStack getCharmItem(EntityLivingBase living, ItemStack item) {
		if (living == null || DCUtil.isEmpty(item))
			return ItemStack.EMPTY;

		if (living instanceof EntityPlayer) {
			for (int i = 9; i < 18; i++) {
				ItemStack check = ((EntityPlayer) living).inventory.getStackInSlot(i);
				if (!DCUtil.isEmpty(check) && OreDictionary.itemMatches(check, item, false)) {
					return check;
				}
			}
			if (Loader.isModLoaded("baubles")) {
				for (ItemStack check : DCPluginBaubles.getBaublesCharm((EntityPlayer) living, null)) {
					if (!DCUtil.isEmpty(check) && OreDictionary.itemMatches(check, item, false)) {
						return check;
					}
				}
			}
		} else {
			Map<Integer, ItemStack> charms = DCUtil.getMobCharm(living);
			for (ItemStack check : charms.values()) {
				if (!DCUtil.isEmpty(check) && OreDictionary.itemMatches(check, item, false)) {
					return check;
				}
			}
		}
		return ItemStack.EMPTY;
	}

	public static float magicSuitEff(EntityPlayer player) {
		if (player != null) {
			float f = 1.0F;
			for (ItemStack armor : player.getArmorInventoryList()) {
				if (!DCUtil.isEmpty(armor)) {
					if (armor.getItem() == MainInit.magicCoat) {
						f += 0.25F;
					}
					if (armor.getItem() == MainInit.magicUnder) {
						f += 0.25F;
					}
				}
			}
			return f;
		}
		return 1.0F;
	}

	public static boolean hasSameDic(ItemStack item, ItemStack check) {
		if (!DCUtil.isEmpty(item) && !DCUtil.isEmpty(check)) {
			int[] ids = OreDictionary.getOreIDs(check);
			int[] ids2 = OreDictionary.getOreIDs(item);
			if (ids.length < 1 || ids2.length < 1) {
				return false;
			}
			for (int id : ids) {
				for (int id2 : ids2) {
					if (id == id2) {
						String s = OreDictionary.getOreName(id);
						if (!s.contains("All") && !s.contains("dye") && !s.contains("list") && !s.contains("Any"))
							return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean hasDic(ItemStack item, String check) {
		if (!DCUtil.isEmpty(item) && check != null) {
			int[] ids2 = OreDictionary.getOreIDs(item);
			if (ids2 == null || ids2.length < 1) {
				return false;
			}
			for (int id2 : ids2) {
				String s = OreDictionary.getOreName(id2);
				if (s.equalsIgnoreCase(check))
					return true;
			}
		}
		return false;
	}

	public static boolean hasDicPart(String name, ItemStack item) {
		if (name == null || DCUtil.isEmpty(item)) {
			return false;
		} else {
			int[] ids2 = OreDictionary.getOreIDs(item);
			if (ids2 == null || ids2.length < 1) {
				return false;
			}
			for (int id2 : ids2) {
				String s = OreDictionary.getOreName(id2);
				if (s.contains(name))
					return true;
			}
		}
		return false;
	}

	public static List<BlockSet> getListFromStrings(String[] names, String logname) {
		List<BlockSet> list = Lists.newArrayList();
		if (names != null && names.length > 0) {
			for (String name : names) {
				if (name != null) {
					String itemName = name;
					String modid = "minecraft";
					int meta = 32767;
					if (name.contains(":")) {
						String[] n2 = name.split(":");
						if (n2 != null && n2.length > 0) {
							if (n2.length > 2) {
								Integer m = null;
								try {
									m = Integer.parseInt(n2[2]);
								} catch (NumberFormatException e) {
									DCLogger.debugLog("Tried to parse non Integer target: " + n2[2]);
								}
								if (m != null && m >= 0) {
									meta = m;
								}
							}

							if (n2.length == 1) {
								itemName = n2[0];
							} else {
								modid = n2[0];
								itemName = n2[1];
							}
						}
					}

					Block block = Block.REGISTRY.getObject(new ResourceLocation(modid, itemName));
					if (block != null && block != Blocks.AIR) {
						DCLogger.infoLog(logname + " add target: " + modid + ":" + itemName + ", " + meta);
						BlockSet set = new BlockSet(block, meta);
						list.add(set);
					} else {
						DCLogger.infoLog("Failed find target: " + modid + ":" + itemName);
					}
				}
			}
		}
		return list;
	}

	public static final int[][][] MATRIX = new int[][][] {
			{
					{
							0,
							0,
							-1
					},
					{
							0,
							0,
							1
					}
			},
			{
					{
							-1,
							0,
							0
					},
					{
							1,
							0,
							0
					}
			},
			{
					{
							-1,
							-1,
							0
					},
					{
							1,
							0,
							0
					}
			},
			{
					{
							-1,
							0,
							0
					},
					{
							1,
							-1,
							0
					}
			},
			{
					{
							0,
							0,
							-1
					},
					{
							0,
							-1,
							1
					}
			},
			{
					{
							0,
							-1,
							-1
					},
					{
							0,
							0,
							1
					}
			},
			{
					{
							0,
							0,
							1
					},
					{
							1,
							0,
							0
					}
			},
			{
					{
							0,
							0,
							1
					},
					{
							-1,
							0,
							0
					}
			},
			{
					{
							0,
							0,
							-1
					},
					{
							-1,
							0,
							0
					}
			},
			{
					{
							0,
							0,
							-1
					},
					{
							1,
							0,
							0
					}
			}
	};

}
