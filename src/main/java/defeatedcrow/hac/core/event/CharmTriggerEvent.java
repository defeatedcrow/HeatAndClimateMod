package defeatedcrow.hac.core.event;

import java.util.ArrayList;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CharmTriggerEvent {

	public static void onAttack(LivingAttackEvent event) {
		LivingEntity living = event.getEntity();
		DamageSource source = event.getSource();
		float amount = event.getAmount();
		boolean b2 = false;
		if (living == null || !living.isAlive())
			return;

		if (source.getEntity() instanceof LivingEntity attacker) {
			ArrayList<ItemStack> attCharms = DCItemUtil.getCharms(attacker, CharmType.ATTACK);
			for (ItemStack c2 : attCharms) {
				if (!c2.isEmpty() && c2.getItem() instanceof IJewelCharm charm) {
					if (charm.isActive(attacker, c2)) {
						if (charm.onAttacking(attacker, living, source, amount, c2)) {
							b2 = true;
							charm.onConsumeResource(attacker, c2);
						}
					}
				}
			}

			// tracer
			if (living.hasEffect(CoreInit.TRACER.get())) {
				MobEffectInstance glow = new MobEffectInstance(MobEffects.GLOWING, 600, 0);
				attacker.addEffect(glow);
			}
		}

		if (b2) {
			event.setCanceled(true);
			return;
		}
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		LivingEntity living = event.getEntity();
		DamageSource source = event.getSource();
		float amount = event.getAmount();
		if (living == null || !living.isAlive())
			return;

		boolean b1 = false;
		float dif = 1.0F;

		ArrayList<ItemStack> difCharms = DCItemUtil.getCharms(living, CharmType.DEFFENCE);
		for (ItemStack c1 : difCharms) {
			if (!c1.isEmpty() && c1.getItem() instanceof IJewelCharm charm) {
				if (charm.isActive(living, c1)) {
					if (charm.onDiffence(living, source, amount, c1)) {
						b1 = true;
						charm.onConsumeResource(living, c1);
					} else {
						dif *= charm.reduceDamage(living, source, amount, c1);
					}
				}
			}
		}

		float atk = 1.0F;
		if (source.getEntity() instanceof LivingEntity attacker) {
			ArrayList<ItemStack> attCharms = DCItemUtil.getCharms(attacker, CharmType.ATTACK);
			for (ItemStack c2 : attCharms) {
				if (!c2.isEmpty() && c2.getItem() instanceof IJewelCharm charm) {
					if (charm.isActive(attacker, c2)) {
						atk *= charm.increaceDamage(attacker, living, source, amount, c2);
					}
				}
			}
		}

		// potion
		float f2 = 1.0F;
		if (source.isProjectile() && living != null) {
			if (living.hasEffect(CoreInit.PROJ_RESISTANCE.get())) {
				MobEffectInstance eff = living.getEffect(CoreInit.PROJ_RESISTANCE.get());
				f2 = 1F - (eff.getAmplifier() * 0.20F);
				if (f2 < 0F)
					f2 = 0F;
			}
		}

		if (source == DamageSource.FREEZE && living != null) {
			if (living.hasEffect(CoreInit.COLD_RESISTANCE.get())) {
				f2 = 0F;
			}
		}

		if (b1 || amount * dif * atk * f2 < 0.5F) {
			event.setCanceled(true);
			return;
		}

		event.setAmount(amount * dif * atk * f2);
	}

	@SubscribeEvent
	public static void onXpPickup(PlayerXpEvent.PickupXp event) {
		Player player = event.getEntity();
		ExperienceOrb orb = event.getOrb();

		int count = DCItemUtil.hasCharmItem(player, new ItemStack(MagicInit.PENDANT_GOLD_BLUE.get()));
		int v = orb.getValue();
		if (count > 0) {
			CompoundTag tag = new CompoundTag();
			orb.addAdditionalSaveData(tag);
			short val = tag.getShort("Value");
			val = (short) Math.min(val * (count + 1), Short.MAX_VALUE);
			tag.putShort("Value", val);
			orb.readAdditionalSaveData(tag);
		}
	}

	@SubscribeEvent
	public static void onDig(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		BlockState state = event.getState();
		BlockPos pos = event.getPos();
		Level level = player.level;
		if (level.isClientSide || player.isCrouching())
			return;

		ArrayList<ItemStack> charms = DCItemUtil.getCharms(player, CharmType.DIG);
		for (ItemStack c : charms) {
			if (!c.isEmpty() && c.getItem() instanceof IJewelCharm charm) {
				if (charm.isActive(player, c)) {
					if (charm.onToolUsing(player, pos, state, c)) {
						charm.onConsumeResource(player, c);
						event.setCanceled(true);
						break;
					}
				}
			}
		}
	}

}
