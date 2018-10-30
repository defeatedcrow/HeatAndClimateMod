package defeatedcrow.hac.main.event;

import java.util.Map;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.plugin.baubles.DCPluginBaubles;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.fml.common.Loader;
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
							double d1 = living.getPosition().distanceSq(ownerLiv.getPosition().getX(), mob.getPosition()
									.getY(), ownerLiv.getPosition().getZ());
							IAttributeInstance iattributeinstance = mob
									.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
							double range = iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
							// in FOLLOW_RANGE
							if (d1 < range * range) {
								// 成功
								newDam *= 2.0F;
								// DCLogger.infoLog("convat event : stelth");
								event.setAmount(newDam);
							}
						}
					}
				}

				int venom = EnchantmentHelper.getEnchantmentLevel(MainInit.venom, ownerLiv.getHeldItemMainhand());
				if (venom > 0) {
					PotionEffect eff = new PotionEffect(MobEffects.WITHER, 100, venom);
					living.addPotionEffect(eff);
				}

				int robber = EnchantmentHelper.getEnchantmentLevel(MainInit.robber, ownerLiv.getHeldItemMainhand());
				if (robber > 0 && living.world.rand.nextInt(100) < robber * 20) {
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
			if (source.getTrueSource() instanceof EntityTameable) {
				EntityTameable living = (EntityTameable) source.getTrueSource();

				Map<Integer, ItemStack> map = DCUtil.getAmulets(living);
				boolean amu = false;
				if (!map.isEmpty()) {
					for (ItemStack item : map.values()) {
						if (item.getItem() == MagicInit.amulet && item.getItemDamage() == 4) {
							amu = true;
						}
					}
				}
				if (amu && living.getOwner() instanceof EntityPlayer) {
					DCLogger.debugInfoLog("on amulet process");
					target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) living.getOwner()), event
							.getAmount());
					event.setCanceled(true);
				}
			} else if (source.getTrueSource() instanceof EntityPlayer && !(target instanceof IMob)) {
				EntityPlayer player = (EntityPlayer) source.getTrueSource();
				if (player != null) {
					if (DCUtil.hasItemInTopSlots(player, new ItemStack(MagicInit.pendant, 1, 19))) {
						event.setCanceled(true);
					} else if (Loader.isModLoaded("baubles")) {
						if (DCPluginBaubles.hasBaublesCharm(player, new ItemStack(MagicInit.pendant, 1, 19))) {
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}
}
