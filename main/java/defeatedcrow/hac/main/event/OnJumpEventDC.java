package defeatedcrow.hac.main.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.hac.main.MainInit;

public class OnJumpEventDC {

	@SubscribeEvent
	public void onEvent(LivingEvent.LivingJumpEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living != null) {
			if (living.isPotionActive(MainInit.gravity)) {
				PotionEffect effect = living.getActivePotionEffect(MainInit.gravity);
				float neg = 0.15F + effect.getAmplifier() * 0.15F;
				living.motionY -= neg;
			}
		}
	}

}
