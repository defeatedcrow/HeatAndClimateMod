package defeatedcrow.hac.main.util;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class MainUtil {

	public static final String[] DYES = {
			"dyeWhite", "dyeOrange", "dyeMagenda", "dyeLightblue", "dyeYellow", "dyeLime", "dyePink", "dyeGray",
			"dyeLightGray", "dyeCyan", "dyePurple", "dyeBlue", "dyeBrown", "dyeGreen", "dyeRed", "dyeBlack",
	};

	public static final int[][][] MATRIX = new int[][][] {
			{
					{
							0, 0, -1
					}, {
							0, 0, 1
					}
			}, {
					{
							-1, 0, 0
					}, {
							1, 0, 0
					}
			}, {
					{
							-1, -1, 0
					}, {
							1, 0, 0
					}
			}, {
					{
							-1, 0, 0
					}, {
							1, -1, 0
					}
			}, {
					{
							0, 0, -1
					}, {
							0, -1, 1
					}
			}, {
					{
							0, -1, -1
					}, {
							0, 0, 1
					}
			}, {
					{
							0, 0, 1
					}, {
							1, 0, 0
					}
			}, {
					{
							0, 0, 1
					}, {
							-1, 0, 0
					}
			}, {
					{
							0, 0, -1
					}, {
							-1, 0, 0
					}
			}, {
					{
							0, 0, -1
					}, {
							1, 0, 0
					}
			}
	};

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

}
