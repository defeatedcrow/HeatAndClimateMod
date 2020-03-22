package defeatedcrow.hac.main.event;

import java.util.Map;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory matrix = event.craftMatrix;
		ItemStack craft = event.crafting;

		if (!DCUtil.isEmpty(craft)) {
			if (craft.getItem() instanceof ItemArmor) {
				int asbest = 0;
				int synthetic = 0;
				int silk = 0;
				for (int i = 0; i < matrix.getSizeInventory(); i++) {
					ItemStack check = matrix.getStackInSlot(i);
					if (MainUtil.hasDicPart("Synthetic", check)) {
						synthetic++;
					}
					if (MainUtil.hasDicPart("Asbest", check)) {
						asbest++;
					}
					if (MainUtil.hasDicPart("Magic", check)) {
						silk++;
					}
				}
				if (synthetic > 0) {
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(craft);
					map.put(Enchantments.PROJECTILE_PROTECTION, synthetic);
					EnchantmentHelper.setEnchantments(map, craft);
				}
				if (asbest > 0) {
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(craft);
					map.put(Enchantments.FIRE_ASPECT, asbest);
					EnchantmentHelper.setEnchantments(map, craft);
				}
				if (silk > 0) {
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(craft);
					map.put(Enchantments.BLAST_PROTECTION, silk);
					EnchantmentHelper.setEnchantments(map, craft);
				}
			}
		}
	}

}
