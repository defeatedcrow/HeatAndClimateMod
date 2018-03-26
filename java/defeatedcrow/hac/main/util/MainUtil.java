package defeatedcrow.hac.main.util;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class MainUtil {

	public static final String BR = System.getProperty("line.separator");

	public static final String[] DYES = {
			"dyeWhite",
			"dyeOrange",
			"dyeMagenda",
			"dyeLightblue",
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
		if (meta > 21)
			meta = 21;
		return new ItemStack(MainInit.gems, 1, meta);
	}

	public static ItemStack getRandomGem(int i) {
		int meta = DCUtil.rand.nextInt(22);
		return new ItemStack(MainInit.gems, i, meta);
	}

	public static ItemStack getRandomGem2(int i) {
		int meta = 4
				+ DCUtil.rand.nextInt(17);
		return new ItemStack(MainInit.gems, i, meta);
	}

	public static ItemStack getCrop(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 11)
			meta = 11;
		return new ItemStack(FoodInit.crops, 1, meta);
	}

	public static ItemStack getRandomCrop(int i) {
		int meta = DCUtil.rand.nextInt(12);
		return new ItemStack(FoodInit.crops, i, meta);
	}

	public static ItemStack getSeed(int meta) {
		if (meta < 0)
			meta = 0;
		if (meta > 8)
			meta = 8;
		return new ItemStack(FoodInit.seeds, 1, meta);
	}

	public static ItemStack getRandomSeed(int i) {
		int meta = DCUtil.rand.nextInt(9);
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
		int meta = DCUtil.rand.nextInt(3);
		return new ItemStack(MainInit.dustBags, i, meta);
	}

	public static ItemStack getRandomCoating(int i) {
		int meta = DCUtil.rand.nextInt(9);
		return new ItemStack(MachineInit.platingChrome, i, meta);
	}

	public static ItemStack getRandomSapling(int i) {
		int meta = DCUtil.rand.nextInt(3);
		return new ItemStack(FoodInit.saplings, i, meta);
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

	public static boolean isPlayerHeldItem(Item item, EntityPlayer player) {
		if (item == null
				|| player == null)
			return false;

		return isPlayerHeldItem(new ItemStack(item, 1, 0), player);
	}

	public static boolean isPlayerHeldItem(ItemStack item, EntityPlayer player) {
		if (DCUtil.isEmpty(item)
				|| player == null)
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

}
