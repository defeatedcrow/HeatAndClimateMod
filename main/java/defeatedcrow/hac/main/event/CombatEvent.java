package defeatedcrow.hac.main.event;

import codechicken.lib.math.MathHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.entity.EntityIronBullet;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// 戦闘関連
public class CombatEvent {

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living == null)
			return;

		DamageSource source = event.getSource();
		float newDam = event.getAmount();
		if (source instanceof EntityDamageSource) {
			Entity owner = ((EntityDamageSource) source).getTrueSource();
			if (owner != null && owner instanceof EntityLivingBase && owner.isEntityAlive()) {
				EntityLivingBase ownerLiv = (EntityLivingBase) owner;

				// enchantment

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
							if (!ownerLiv.world.isRemote && ownerLiv.world.spawnEntity(drop)) {
								living.setItemStackToSlot(EntityEquipmentSlot.values()[i], ItemStack.EMPTY);
								ownerLiv.world
										.playSound(null, ownerLiv.posX, ownerLiv.posY, ownerLiv.posZ, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.5F, 1.0F / (ownerLiv.world.rand
												.nextFloat() * 0.4F + 1.2F) + 0.5F);
								break;
							}
						}
					}
				}

				// potion effect

				boolean b = false;

				if (source instanceof EntityDamageSourceIndirect) {
					if (living.isPotionActive(MainInit.reflexion)) {
						b = true;
						PotionEffect eff = living.getActivePotionEffect(MainInit.reflexion);
						float damage = 4.0F * (eff.getAmplifier() + 1.0F);
						Entity proj = source.getImmediateSource();
						double dx = owner.posX - living.posX;
						double dz = owner.posZ - living.posZ;
						double dy = owner.getEntityBoundingBox().minY + (owner.height / 2D) - source
								.getImmediateSource().posY;
						EntityIronBullet bullet = new EntityIronBullet(living.world, living);
						bullet.setDamage(damage);
						bullet.shoot(dx, dy, dz, 2.0F, 5.0F);
						bullet.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 4.0F);
						if (!living.world.isRemote) {
							living.world.spawnEntity(bullet);
						}
					} else if (living.isPotionActive(MainInit.projectileResistant)) {
						PotionEffect eff = living.getActivePotionEffect(MainInit.projectileResistant);
						float red = 1.0F - (eff.getAmplifier() * 0.5F);
						if (red <= 0F) {
							b = true;
						} else {
							newDam *= red;
							event.setAmount(newDam);
						}
					}
				} else if (living.isPotionActive(MainInit.absorptionEXP)) {
					PotionEffect eff = living.getActivePotionEffect(MainInit.absorptionEXP);
					float abs = eff.getAmplifier() * 0.5F * newDam;
					b = true;
					if (!living.world.isRemote) {
						EntityXPOrb orb = new EntityXPOrb(living.world, living.posX, living.posY, living.posZ,
								MathHelper.ceil(abs));
						living.world.spawnEntity(orb);
					}
				}

				if (b) {
					event.setCanceled(true);
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

	@SubscribeEvent
	public void onHealEvent(LivingHealEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		float amount = event.getAmount();
		if (MainInit.unrepair != null && target.isPotionActive(MainInit.unrepair)) {
			PotionEffect p = target.getActivePotionEffect(MainInit.unrepair);
			float amo = p.getAmplifier() * 1.0F;
			amount -= amo;
			if (amount <= 0F) {
				event.setCanceled(true);
			} else {
				event.setAmount(amount);
			}
		}
	}

}
