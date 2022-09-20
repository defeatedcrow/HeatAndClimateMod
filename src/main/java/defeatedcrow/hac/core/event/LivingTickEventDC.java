package defeatedcrow.hac.core.event;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.damage.ClimateDamageEvent;
import defeatedcrow.hac.api.damage.ClimateDamageEvent.DamageSet;
import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class LivingTickEventDC {

	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingTickEvent event) {
		LivingEntity living = event.getEntity();
		if (living != null && living.level != null) {
			if (living.level.getGameTime() % CoreConfigDC.entityInterval == 0) {
				if (!living.level.isClientSide) {
					if (living instanceof Player && CoreConfigDC.sharePotionWithRidingMob) {
						onLivingPotionUpdate(living);
					}

					onLivingUpdate(living);
				}
			}
		}
	}

	public static void onLivingUpdate(LivingEntity living) {
		if (living.hasEffect(MobEffects.JUMP)) {
			living.fallDistance = 0.0F;
		}
		onLivingCharmUpdate(living);

		if (living instanceof Player || CoreConfigDC.mobClimateDamage) {
			onLivingClimateUpdate(living);
		}
	}

	public static void onLivingPotionUpdate(LivingEntity living) {
		/* Potion */
		ArrayList<MobEffect> potions = Lists.newArrayList();

		boolean f = false;
		if (living.getVehicle() != null && !living.getActiveEffects().isEmpty()) {
			f = living.getVehicle() instanceof LivingEntity;
		}

		if (f) {
			LivingEntity riding = (LivingEntity) living.getVehicle();
			// PotionEffectのリスト
			living.getActiveEffects().forEach(effect -> {
				if (effect.getEffect() != null && !riding.hasEffect(effect.getEffect())) {
					riding.addEffect(effect);
				}
			});
		}
	}

	public static void onLivingCharmUpdate(LivingEntity living) {
		NonNullList<ItemStack> charms = DCItemUtil.getCharms(living, CharmType.CONSTANT);
		for (ItemStack item2 : charms) {
			IJewelCharm charm = (IJewelCharm) item2.getItem();
			charm.constantEffect(living, item2);
		}
		charms.clear();
	}

	public static void onLivingClimateUpdate(LivingEntity living) {
		/* climate damage */

		if (CoreConfigDC.climateDam) {

			// ピースフルではダメージがない
			if (living.level.getDifficulty() == Difficulty.PEACEFUL && !CoreConfigDC.peacefulDam) {
				return;
			}

			for (EntityType<?> c : CoreConfigDC.blackListEntity) {
				if (c.tryCast(living) != null)
					return;
			}

			IClimate clm = new ClimateSupplier(living.level, living.blockPosition()).get();

			DCHeatTier heat = clm.getHeat();

			float prevTemp = 2.0F; // normal
			if (living instanceof Player) {
				prevTemp = 1.0F * (3 - CoreConfigDC.damageDifficulty); // 1.0F ~ 3.0F
			} else {
				// mobごとの特性
				prevTemp = ClimateAPI.registerMob.getHeatResistance(living, heat);
			}
			float damTemp = Math.abs(heat.getTier()) * 1.0F; // hot 0F ~ 8.0F / cold 0F ~ 10.0F
			boolean isCold = heat.getTier() < 0;
			DamageSourceClimate source = isCold ? DamageSourceClimate.climateColdDamage :
					DamageSourceClimate.climateHeatDamage;

			// 基礎ダメージ
			if (isCold) {
				damTemp -= prevTemp;
				damTemp *= 2.0F;
			} else {
				damTemp -= prevTemp;
			}

			// この時点でダメージ無しならスキップ
			if (damTemp >= 1.0F) {
				// 次に装備と耐性計算

				/* damage判定 */
				prevTemp = 0F;
				// 防具の計算
				IItemHandler handler =
						living.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.NORTH).orElse(null);
				if (handler != null) {
					for (int s = 0; s < handler.getSlots(); s++) {
						ItemStack item = handler.getStackInSlot(s);
						if (item.isEmpty())
							continue;

						float p = DCItemUtil.getItemResistantData(item, isCold);
						prevTemp += p;
					}
				}

				// charm
				NonNullList<ItemStack> charms = DCItemUtil.getCharms(living, CharmType.ALL);
				for (ItemStack check : charms) {
					IJewelCharm charm = (IJewelCharm) check.getItem();
					prevTemp += charm.reduceDamage(living, source, damTemp, check);
				}
				charms.clear();

				damTemp -= prevTemp;

				ClimateDamageEvent fireEvent = new ClimateDamageEvent(living, source, clm, damTemp);
				DamageSet result = fireEvent.result();
				damTemp = result.damage;
				DamageSource source2 = result.source;

				// 2.0F未満の場合はとどめを刺さない
				if (damTemp < 2.0F && living.getHealth() < 2.0F) {
					damTemp = 0.0F;
				}

				if (damTemp >= 1.0F) {
					living.hurt(source2, damTemp);
					if (living instanceof Animal || living instanceof Monster) {
						Vec3 vec = null;
						BlockPos p2 = null;
						if (isCold) {
							p2 = ClimateAPI.calculator.getMaxColdPos(living.level, living.blockPosition(), 3);
						} else {
							p2 = ClimateAPI.calculator.getMaxHeatPos(living.level, living.blockPosition(), 3);
						}

						if (p2 != null) {
							vec = Vec3.atBottomCenterOf(p2);
							// 逃げるAIを差し込む
							PathfinderMob animal = (PathfinderMob) living;
							animal.getPersistentData().putFloat("dcs_lastDamage", damTemp);
							for (WrappedGoal task : animal.goalSelector.getAvailableGoals()) {
								if (task.getGoal() instanceof AvoidHeatDamageGoal) {
									((AvoidHeatDamageGoal) task.getGoal()).avoidPos = vec;
									return;
								}
							}
							animal.goalSelector.addGoal(3, new AvoidHeatDamageGoal(animal, 1.0F, vec));
						}
					}
				}

			} else {
				if (living instanceof PathfinderMob && living.getPersistentData().contains("dcs_lastDamage")) {
					living.getPersistentData().remove("dcs_lastDamage");
				}
			}
		}
	}

}
