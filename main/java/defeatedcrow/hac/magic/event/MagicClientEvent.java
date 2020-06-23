package defeatedcrow.hac.magic.event;

import java.util.ArrayList;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@SideOnly(Side.CLIENT)
public class MagicClientEvent {

	private ArrayList<String> ores = new ArrayList<String>();

	@SubscribeEvent
	public void advancedTooltip(ItemTooltipEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		ItemStack target = event.getItemStack();
		boolean flag = false;

		if (player != null && player instanceof EntityPlayerSP && !DCUtil.isEmpty(target)) {
			Item tI = target.getItem();

			if (CoreConfigDC.showAltTips) {
				// charm
				if (DCUtil.hasCharmItem(player, new ItemStack(MagicInit.colorRing, 1, 0))) {
					flag = true;
				}
			}

			if (flag) {
				this.ores = this.getOre(target);
				if (!ores.isEmpty()) {
					event.getToolTip().addAll(ores);
				}
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
