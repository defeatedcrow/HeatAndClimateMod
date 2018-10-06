package defeatedcrow.hac.main.event;

import java.util.Map;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OnCraftingDC {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory matrix = event.craftMatrix;
		ItemStack craft = event.crafting;
		int asbest = 0;
		int synthetic = 0;

		if (!DCUtil.isEmpty(craft)) {
			if (craft.getItem() == MagicInit.badge) {
				int count = 0;
				NBTTagCompound tag = new NBTTagCompound();
				for (int i = 0; i < matrix.getSizeInventory(); i++) {
					ItemStack check = matrix.getStackInSlot(i);
					if (OreDictionary.itemMatches(craft, check, true)) {
						NBTTagCompound tag2 = check.getTagCompound();
						if (tag2 != null && tag2.hasKey("dcs.itemdam")) {
							count += tag2.getInteger("dcs.itemdam");
						}
					}
				}
				if (count > 0) {
					tag.setInteger("dcs.itemdam", count);
					craft.setTagCompound(tag);
					DCLogger.infoLog("current coount: " + count);
				}
			} else if (craft.getItem() instanceof ItemArmor) {
				for (int i = 0; i < matrix.getSizeInventory(); i++) {
					ItemStack check = matrix.getStackInSlot(i);
					if (MainUtil.hasDicPart("Synthetic", check)) {
						synthetic++;
					}
					if (MainUtil.hasDicPart("Asbest", check)) {
						asbest++;
					}
				}
				if (synthetic > 0) {
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(craft);
					map.put(Enchantments.PROTECTION, synthetic);
					EnchantmentHelper.setEnchantments(map, craft);
				} else if (asbest > 0) {
					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(craft);
					map.put(Enchantments.FIRE_ASPECT, asbest);
					EnchantmentHelper.setEnchantments(map, craft);
				}
			}
		}
	}

}
