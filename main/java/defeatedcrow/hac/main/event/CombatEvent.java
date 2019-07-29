package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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

				int venom = EnchantmentHelper.getEnchantmentLevel(MainInit.venom, ownerLiv.getHeldItemMainhand());
				if (MainCoreConfig.e_venom && venom > 0) {
					PotionEffect eff = new PotionEffect(MobEffects.WITHER, 100, venom);
					living.addPotionEffect(eff);
				}

				int robber = EnchantmentHelper.getEnchantmentLevel(MainInit.robber, ownerLiv.getHeldItemMainhand());
				if (MainCoreConfig.e_robber && robber > 0 && living.world.rand.nextInt(100) < robber * 20) {
					for (int i = 0; i < EntityEquipmentSlot.values().length; i++) {
						ItemStack item = living.getItemStackFromSlot(EntityEquipmentSlot.values()[i]);
						if (!DCUtil.isEmpty(item)) {
							EntityItem drop = new EntityItem(ownerLiv.world, living.posX, living.posY, living.posZ, item
									.copy());
							drop.motionY += 0.3D;
							if (ownerLiv.world.spawnEntity(drop)) {
								living.setItemStackToSlot(EntityEquipmentSlot.values()[i], ItemStack.EMPTY);
								ownerLiv.world
										.playSound(null, ownerLiv.posX, ownerLiv.posY, ownerLiv.posZ, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.5F, 1.0F / (ownerLiv.world.rand
												.nextFloat() * 0.4F + 1.2F) + 0.5F);
								break;
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onAttackEvent(LivingAttackEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		DamageSource source = event.getSource();
		if (target != null && source != null) {
			if (source.getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase owner = (EntityLivingBase) source.getTrueSource();
				if (owner != null) {
					if (!owner.getHeldItemMainhand().isEmpty()) {
						// tool steel sword
						if (owner.getHeldItemMainhand().getItem() == MainInit.dcSword[8]) {
							event.getSource().setDamageBypassesArmor();
						}
					}
				}
			}
		}
	}
}
