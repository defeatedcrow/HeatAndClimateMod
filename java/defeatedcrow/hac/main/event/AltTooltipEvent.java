package defeatedcrow.hac.main.event;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.main.util.DCArmorMaterial;

@SideOnly(Side.CLIENT)
public class AltTooltipEvent {

	private ArrayList<String> ores = new ArrayList<String>();

	@SubscribeEvent
	public void advancedTooltip(ItemTooltipEvent event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack target = event.itemStack;
		boolean flag = false;

		if (player != null && player instanceof EntityPlayerSP && target != null && CoreConfigDC.showAltTips) {
			Item tI = target.getItem();

			// charm
			ItemStack[] inside = new ItemStack[9];
			for (int i = 0; i < 9; i++) {
				inside[i] = player.inventory.getStackInSlot(i + 9);
			}
			List<ItemStack> charms = new ArrayList<ItemStack>();
			for (ItemStack item1 : inside) {
				if (item1 != null && item1.getItem() != null && item1.getItem() instanceof ItemMagicalPendant) {
					int m = item1.getItemDamage();
					if (m == 4) {
						flag = true;
					}
				}
			}

			if (event.showAdvancedItemTooltips) {
				// まず耐久値
				if (tI.isDamageable() && target.getItemDamage() == 0) {
					int max = target.getMaxDamage();
					String ret = StatCollector.translateToLocal("dcs_climate.tip.durability") + ": " + max;
					event.toolTip.add(ret);
				}

				// tool tier
				if (tI instanceof ItemTool) {
					int tier = ((ItemTool) tI).getToolMaterial().getHarvestLevel();
					String ret = StatCollector.translateToLocal("dcs_climate.tip.harvestlevel") + ": " + tier;
					event.toolTip.add(ret);
				}

				// climate reg
				if (tI instanceof ItemArmor) {
					ArmorMaterial mat = ((ItemArmor) tI).getArmorMaterial();
					float reg = DCArmorMaterial.getClimateResistance(mat);
					String ret = StatCollector.translateToLocal("dcs_climate.tip.resistance") + ": " + reg;
					event.toolTip.add(ret);
				}
			}

			if (flag) {
				this.ores = this.getOre(target);
				if (!ores.isEmpty())
					event.toolTip.addAll(ores);
			}
		}
	}

	private ArrayList<String> getOre(ItemStack item) {
		ArrayList<String> ore = new ArrayList<String>();

		int[] id = OreDictionary.getOreIDs(item);
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				String s = OreDictionary.getOreName(id[i]);
				ore.add(s);
			}
		}

		return ore;
	}

}
