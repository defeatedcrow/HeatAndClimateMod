package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnvilMoldEvent {

	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		ItemStack ret = event.getOutput();
		/* Moldのレシピ登録 */
		if (right != null) {

			if (right.getItem() instanceof IPressMold && ret == null) {
				IPressMold mold = (IPressMold) right.copy().getItem();
				if (mold.getOutput(right) == null) {
					// DCLogger.debugLog("anvil event cycle");
					ItemStack next = mold.setOutput(right, left, 0);
					if (next != null) {
						event.setOutput(next);
						event.setCost(1);
					}
				}
			} else if (left != null && left.getItem() instanceof IPressMold && right.getItem() == Items.IRON_INGOT
					&& ret == null) {
				// recipeの変更
				DCLogger.debugLog("anvil event cycle");
				IPressMold mold = (IPressMold) left.copy().getItem();
				ItemStack output = mold.getOutput(left);
				if (output != null) {
					int num = mold.getRecipeNumber(left) + 1;
					ItemStack next = mold.setOutput(left, output, num);
					if (next != null) {
						event.setOutput(next);
						event.setCost(1);
					}
				}
			}

			/* リペアパテの設定 */
			else if (left != null && left.getItem().isDamageable() && right.getItem() == MainInit.repairPutty
					&& ret == null) {
				int dam = left.getItemDamage();
				int type = right.getItemDamage();
				int count = right.stackSize;
				if (type == 0 && left.getItem().isDamaged(left)) {
					dam -= 100 * count;
					if (dam < 0) {
						dam = 0;
					}
					ItemStack next = new ItemStack(left.getItem(), left.stackSize, dam);
					if (left.hasTagCompound()) {
						next.setTagCompound(left.getTagCompound());
					}
					event.setOutput(next);
					event.setCost(5);
				} else if (type == 1) {
					Item tool = left.getItem();
					if (Enchantments.EFFICIENCY.canApply(left)
							&& EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, left) == 0) {
						ItemStack next = left.copy();
						next.addEnchantment(Enchantments.EFFICIENCY, 1);
						event.setOutput(next);
						event.setCost(1);
						event.setMaterialCost(1);
					} else if (Enchantments.SHARPNESS.canApply(left)
							&& EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, left) == 0) {
						ItemStack next = left.copy();
						next.addEnchantment(Enchantments.SHARPNESS, 1);
						event.setOutput(next);
						event.setCost(1);
						event.setMaterialCost(1);
					}
				} else if (type == 2) {
					Item armor = left.getItem();
					if (armor instanceof ItemArmor && ((ItemArmor) armor).hasColor(left)) {
						ItemStack next = left.copy();
						((ItemArmor) armor).removeColor(next);
						event.setOutput(next);
						event.setCost(1);
						event.setMaterialCost(1);
					}
				}
			}

		}
	}

}
