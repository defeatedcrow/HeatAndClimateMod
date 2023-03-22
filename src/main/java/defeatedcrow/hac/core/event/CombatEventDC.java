package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CombatEventDC {

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		LivingEntity living = event.getEntity();
		DamageSource source = event.getSource();
		float amount = event.getAmount();
		if (living == null || !living.isAlive())
			return;

		// tracer
		if (living.hasEffect(CoreInit.TRACER.get())) {
			if (source.getEntity() != null && source.getEntity() instanceof LivingEntity owner) {
				MobEffectInstance glow = new MobEffectInstance(MobEffects.GLOWING, 600, 0);
				owner.addEffect(glow);
			}
		}

	}

}
