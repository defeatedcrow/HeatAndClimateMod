package defeatedcrow.hac.main.event;

import defeatedcrow.hac.api.damage.ClimateDamageEvent;
import defeatedcrow.hac.main.util.DCAdvancementUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// 実績トリガー
public class AchievementEventDC {

	@SubscribeEvent
	public void onClimateDamage(ClimateDamageEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (!player.world.isRemote && player instanceof EntityPlayerMP) {
				if (event.getAmount() >= 1.0F) {
					DCAdvancementUtil.unlock(player, "climate_damage");
				}
			}
		}
	}

}
