package defeatedcrow.hac.plugin;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.api.magic.SearchPlayerCharmEvent;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

public class CuriosPluginDC {

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(CuriosPluginDC::onCharmSerchEvent);
	}

	@SubscribeEvent
	public static void onCharmSerchEvent(SearchPlayerCharmEvent event) {
		ArrayList<ItemStack> ret = Lists.newArrayList();
		ICuriosItemHandler handler = event.getOwnerEntity().getCapability(CuriosCapability.INVENTORY).orElse(null);
		if (handler != null) {
			handler.getCurios().forEach((id, slot) -> {
				if (id.equals("ring") || id.equals("necklace") || id.equals("charm")) {
					for (int i = 0; i < slot.getStacks().getSlots(); i++) {
						ItemStack check = slot.getStacks().getStackInSlot(i);
						if (isCharmItem(check, event.getCharmType())) {
							boolean b = false;
							for (ItemStack c2 : ret) {
								if (DCItemUtil.isSameItem(check, c2, false)) {
									c2.grow(check.getCount());
									b = true;
									break;
								}
							}
							if (!b) {
								ret.add(check.copy());
							}
						}
					}
				}
			});
		}

		if (!ret.isEmpty()) {
			ret.forEach(check -> {
				boolean b = false;
				for (ItemStack c2 : event.getCharmList()) {
					if (DCItemUtil.isSameItem(check, c2, false)) {
						c2.grow(check.getCount());
						b = true;
						break;
					}
				}
				if (!b) {
					event.getCharmList().add(check.copy());
				}
			});
			event.setResult(Result.ALLOW);
		}
	}

	private static boolean isCharmItem(ItemStack check, CharmType type) {
		if (!check.isEmpty()) {
			if (type == null) {
				return true;
			} else if (check.getItem() instanceof IJewel) {
				IJewel charm = (IJewel) check.getItem();
				if (charm.getType() == MagicType.INVENTORY_TOP && charm.getCharmType().match(type)) {
					return true;
				}
			}
		}
		return false;
	}

}
