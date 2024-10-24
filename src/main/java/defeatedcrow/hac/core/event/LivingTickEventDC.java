package defeatedcrow.hac.core.event;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.damage.ClimateDamageEvent;
import defeatedcrow.hac.api.damage.ClimateDamageEvent.DamageSet;
import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingTickEventDC {

	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingTickEvent event) {
		LivingEntity living = event.getEntity();
		if (living != null && living.level != null) {
			if (living.level.getGameTime() % ConfigCommonBuilder.INSTANCE.vUpdateInterval.get() == 0) {
				if (living instanceof Player || ConfigCommonBuilder.INSTANCE.enMobDamage.get()) {
					onLivingClimateUpdate(living);
				}
			}
			if (living.level.getGameTime() % 20 == 0) {
				if (!living.level.isClientSide) {
					if (living instanceof Player && ConfigCommonBuilder.INSTANCE.enPotionSharing.get()) {
						onLivingPotionSharing(living);
					}
					onLivingUpdate(living);
					if (living instanceof Villager villager) {
						onVillagerUpdate(villager);
					}
				}
			}
		}
	}

	public static void onLivingUpdate(LivingEntity living) {
		if (living.hasEffect(MobEffects.JUMP) || living.hasEffect(CoreInit.BIRD.get())) {
			living.fallDistance = 0.0F;
		}
		if (living.hasEffect(CoreInit.FISH.get()) && living.getAirSupply() < living.getMaxAirSupply()) {
			living.setAirSupply(living.getMaxAirSupply());
		}
		onLivingCharmUpdate(living);
	}

	public static void onLivingPotionSharing(LivingEntity living) {
		/* Potion */
		ArrayList<MobEffect> potions = Lists.newArrayList();

		if (living == null || living.isDeadOrDying())
			return;

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
		List<ItemStack> charms = MagicUtil.getCharms(living, CharmType.CONSTANT);
		for (ItemStack item2 : charms) {
			IJewelCharm charm = (IJewelCharm) item2.getItem();
			charm.constantEffect(living, item2);
		}
		charms.clear();
	}

	public static void onLivingEffectUpdate(LivingEntity living) {

	}

	public static void onLivingClimateUpdate(LivingEntity living) {
		if (living == null || living.isDeadOrDying())
			return;

		/* climate damage */

		if (ConfigCommonBuilder.INSTANCE.enTempDamage.get()) {

			// ピースフルではダメージがない
			if (living.level.getDifficulty() == Difficulty.PEACEFUL && !ConfigCommonBuilder.INSTANCE.enPeacefulDamage.get()) {
				return;
			}

			// for (EntityType<?> c : CoreConfigDC.blackListEntity) {
			// if (c.tryCast(living) != null)
			// return;
			// }

			IClimate clm = new ClimateSupplier(living.level, living.blockPosition()).get();

			DCHeatTier heat = clm.getHeat();

			float prevTemp = 2.0F; // normal
			if (living instanceof Player) {
				prevTemp = 1.0F * (3 - ConfigCommonBuilder.INSTANCE.vDifficulty.get()); // 1.0F ~ 3.0F
			} else {
				// mobごとの特性
				// COOL ~ WARM まではダメージがない
				prevTemp = 1.0F + ClimateAPI.registerMob.getHeatResistance(living, heat);
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

				// potion
				prevTemp += DCUtil.getPotionResistantData(living, isCold);

				// 防具の計算
				prevTemp += DCItemUtil.getArmorResistant(living, isCold);

				// charm
				List<ItemStack> charms = MagicUtil.getCharms(living, CharmType.ALL);
				for (ItemStack check : charms) {
					IJewelCharm charm = (IJewelCharm) check.getItem();
					prevTemp += charm.reduceDamage(living, source, damTemp, check);
				}
				charms.clear();

				float finalDam = damTemp - prevTemp;

				ClimateDamageEvent fireEvent = new ClimateDamageEvent(living, source, clm, finalDam);
				DamageSet result = fireEvent.result();
				finalDam = result.damage;
				DamageSource source2 = result.source;

				// 2.0F未満の場合はとどめを刺さない
				if (finalDam < 2.0F && living.getHealth() < 2.0F) {
					finalDam = 0.0F;
				}

				if (finalDam >= 1.0F) {
					if (living.hurt(source2, finalDam) && living instanceof Player && !isCold) {
						ClimateCore.proxy.triggerAdvancement(living, "main/damage");
					}
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
							animal.getPersistentData().putFloat("dcs_last_damage", finalDam);
							for (WrappedGoal task : animal.goalSelector.getAvailableGoals()) {
								if (task.getGoal() instanceof AvoidHeatDamageGoal) {
									((AvoidHeatDamageGoal) task.getGoal()).avoidPos = vec;
									return;
								}
							}
							animal.goalSelector.addGoal(3, new AvoidHeatDamageGoal(animal, 1.0F, vec));
						}
					}
				} else {
					if (living instanceof PathfinderMob && living.getPersistentData().contains("dcs_last_damage")) {
						living.getPersistentData().remove("dcs_last_damage");
					}
					if (damTemp > 2.0F && living instanceof Player) {
						ClimateCore.proxy.triggerAdvancement(living, "main/wear");
					}
				}

			}

			/* wet effect */

			if (living instanceof Player && ConfigCommonBuilder.INSTANCE.enWetEffect.get() && !DCItemUtil.isWearArmorItem(CoreInit.LEGGINS_WADERS.get(), living, EquipmentSlot.LEGS))
				if (!living.getLevel().isClientSide && clm.getHumidity() == DCHumidity.UNDERWATER || living.isInWaterRainOrBubble()) {
					if (!living.hasEffect(CoreInit.WET.get()) || living.getEffect(CoreInit.WET.get()).getDuration() < 20) {
						living.addEffect(new MobEffectInstance(CoreInit.WET.get(), 600, 0));
					}
				}

			/* Goal Interval */
			int i = living.getPersistentData().getInt("dcs_fulfill_interval");
			if (i > 0) {
				i--;
				living.getPersistentData().putInt("dcs_fulfill_interval", i);
			}

		}
	}

	public static void onVillagerUpdate(Villager vil) {
		if (ConfigCommonBuilder.INSTANCE.enVillagerEatToHeal.get()) {
			int i = vil.getPersistentData().getInt("dcs_fulfill_interval");
			if (i > 0) {
				i--;
				if (i <= 0) {
					vil.getPersistentData().remove("dcs_fulfill_interval");
				} else {
					vil.getPersistentData().putInt("dcs_fulfill_interval", i);
				}
			} else {
				if (vil.getHealth() < vil.getMaxHealth()) {
					int slot = countFoodPoints(vil);
					if (slot >= 0) {
						ItemStack food = vil.getInventory().getItem(slot).copy();
						vil.getInventory().removeItem(slot, 1);
						int point = Villager.FOOD_POINTS.get(food.getItem());
						vil.heal(point * 2F);
						vil.getPersistentData().putInt("dcs_fulfill_interval", 5);
						if (vil.getLevel() instanceof ServerLevel serverLevel)
							MsgEffectToC.sendToClient(serverLevel, vil.position().add(0D, 2.4D, 0D), 43);
					}
				}
				if (vil.getInventory().countItem(Items.POTATO) > 3 && vil.getInventory().countItem(Items.BAKED_POTATO) < 16) {
					vil.getInventory().removeItemType(Items.POTATO, 1);
					ItemStack potato = vil.getInventory().addItem(new ItemStack(Items.BAKED_POTATO, 1));
					if (!potato.isEmpty()) {
						vil.spawnAtLocation(potato, 0.5F);
					}
				}
			}
		}
	}

	private static int countFoodPoints(Villager vil) {
		SimpleContainer container = vil.getInventory();
		for (int i = 0; i < container.getContainerSize(); i++) {
			ItemStack item = container.getItem(i);
			if (!DCUtil.isEmpty(item) && Villager.FOOD_POINTS.keySet().contains(item.getItem())) {
				return i;
			}
		}
		return -1;
	}

}
