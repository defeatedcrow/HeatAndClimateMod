package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// 戦闘関連
public class CombatEvent {

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		DamageSource source = event.getSource();
		float newDam = event.getAmount();
		if (source instanceof EntityDamageSource) {
			Entity owner = ((EntityDamageSource) source).getTrueSource();
			if (owner != null && owner instanceof EntityLivingBase && owner.isEntityAlive()) {
				EntityLivingBase ownerLiv = (EntityLivingBase) owner;
				// Invisible
				if (ownerLiv.isPotionActive(MobEffects.INVISIBILITY)) {
					if (living instanceof EntityLiving) {
						EntityLiving mob = (EntityLiving) living;
						// no AI target
						if (living.getAttackingEntity() == null) {
							double d1 = living.getPosition().distanceSq(ownerLiv.getPosition().getX(),
									mob.getPosition().getY(), ownerLiv.getPosition().getZ());
							IAttributeInstance iattributeinstance = mob
									.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
							double range = iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
							// in FOLLOW_RANGE
							if (d1 < range * range) {
								// 成功
								newDam *= 2.0F;
								DCLogger.infoLog("convat event : stelth");
								event.setAmount(newDam);
							}
						}
					}
				}

				int venom = EnchantmentHelper.getEnchantmentLevel(MainInit.venom, ownerLiv.getHeldItemMainhand());
				if (venom > 0) {
					PotionEffect eff = new PotionEffect(MobEffects.WITHER, 100, venom);
					living.addPotionEffect(eff);
					DCLogger.infoLog("convat event : poison");
				}
			}
		}
	}
}
