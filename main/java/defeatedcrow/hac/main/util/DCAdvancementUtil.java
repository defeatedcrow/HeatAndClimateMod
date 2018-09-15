package defeatedcrow.hac.main.util;

import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class DCAdvancementUtil {

	public static boolean unlock(EntityPlayer player, String name) {
		ResourceLocation advName = new ResourceLocation(ClimateMain.MOD_ID, name);
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP mp = (EntityPlayerMP) player;
			AdvancementManager manager = mp.getServer().getAdvancementManager();
			Advancement adv = manager.getAdvancement(advName);
			if (adv != null) {
				mp.getAdvancements().grantCriterion(adv, "code_trigger");
				return true;
			}
		}
		return false;
	}

}
