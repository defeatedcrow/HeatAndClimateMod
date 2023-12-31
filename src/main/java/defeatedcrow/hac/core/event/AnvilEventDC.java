package defeatedcrow.hac.core.event;

import java.util.Map;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AnvilEventDC {

	@SubscribeEvent
	public static void onEvent(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		ItemStack ret = event.getOutput();
		if (!DCUtil.isEmpty(left) && !DCUtil.isEmpty(right) && DCUtil.isEmpty(ret)) {
			Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(left);
			if (right.getItem() == MagicInit.ELEMENT_WHITE.get() && left.canApplyAtEnchantingTable(Enchantments.MENDING)) {
				if (!map.containsKey(Enchantments.MENDING)) {
					map.put(Enchantments.MENDING, 1);
					ret = left.copy();
					EnchantmentHelper.setEnchantments(map, ret);
					event.setOutput(ret);
					event.setMaterialCost(1);
					event.setCost(1);
					event.setResult(Result.ALLOW);
				}
			} else if (right.getItem() == MagicInit.ELEMENT_BLUE.get() && left.canApplyAtEnchantingTable(Enchantments.AQUA_AFFINITY)) {
				if (!map.containsKey(Enchantments.AQUA_AFFINITY)) {
					map.put(Enchantments.AQUA_AFFINITY, 1);
					ret = left.copy();
					EnchantmentHelper.setEnchantments(map, ret);
					event.setOutput(ret);
					event.setMaterialCost(1);
					event.setCost(1);
					event.setResult(Result.ALLOW);
				}
			} else if (right.getItem() == MagicInit.ELEMENT_BLACK.get() && left.canApplyAtEnchantingTable(Enchantments.INFINITY_ARROWS)) {
				if (!map.containsKey(Enchantments.INFINITY_ARROWS)) {
					map.put(Enchantments.INFINITY_ARROWS, 1);
					ret = left.copy();
					EnchantmentHelper.setEnchantments(map, ret);
					event.setOutput(ret);
					event.setMaterialCost(1);
					event.setCost(1);
					event.setResult(Result.ALLOW);
				}
			} else if (right.getItem() == MagicInit.ELEMENT_RED.get() && left.canApplyAtEnchantingTable(Enchantments.SILK_TOUCH)) {
				if (!map.containsKey(Enchantments.SILK_TOUCH)) {
					map.put(Enchantments.SILK_TOUCH, 1);
					ret = left.copy();
					EnchantmentHelper.setEnchantments(map, ret);
					event.setOutput(ret);
					event.setMaterialCost(1);
					event.setCost(1);
					event.setResult(Result.ALLOW);
				}
			} else if (right.getItem() == MagicInit.ELEMENT_GREEN.get()) {
				if (left.canApplyAtEnchantingTable(Enchantments.BLOCK_FORTUNE)) {
					int a = left.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
					if (a < 3) {
						a++;
						map.put(Enchantments.BLOCK_FORTUNE, a);
						ret = left.copy();
						EnchantmentHelper.setEnchantments(map, ret);
						event.setOutput(ret);
						event.setMaterialCost(1);
						event.setCost(a);
						event.setResult(Result.ALLOW);
					}
				} else if (left.canApplyAtEnchantingTable(Enchantments.MOB_LOOTING)) {
					int a = left.getEnchantmentLevel(Enchantments.MOB_LOOTING);
					if (a < 3) {
						a++;
						map.put(Enchantments.MOB_LOOTING, a);
						ret = left.copy();
						EnchantmentHelper.setEnchantments(map, ret);
						event.setOutput(ret);
						event.setMaterialCost(1);
						event.setCost(a);
						event.setResult(Result.ALLOW);
					}
				}
			}
		}
	}

}
