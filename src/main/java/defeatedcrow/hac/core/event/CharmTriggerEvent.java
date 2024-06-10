package defeatedcrow.hac.core.event;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.magic.MagicUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.item.InertElementItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;
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
			ArrayList<ItemStack> attCharms = MagicUtil.getCharms(attacker, CharmType.ATTACK);
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
				int f = living.getEffect(CoreInit.TRACER.get()).getAmplifier() + 1;
				MobEffectInstance glow = new MobEffectInstance(MobEffects.GLOWING, 600 * f, 0);
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

		ArrayList<ItemStack> difCharms = MagicUtil.getCharms(living, CharmType.DEFFENCE);
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
			ArrayList<ItemStack> attCharms = MagicUtil.getCharms(attacker, CharmType.ATTACK);
			for (ItemStack c2 : attCharms) {
				if (!c2.isEmpty() && c2.getItem() instanceof IJewelCharm charm) {
					if (charm.isActive(attacker, c2)) {
						atk *= charm.increaceDamage(attacker, living, source, amount, c2);
					}
				}
			}

			if (living instanceof Player player && (attacker instanceof Phantom || attacker.getMobType() == MobType.ARTHROPOD)) {
				SimpleEntry<Integer, ItemStack> coil = DCItemUtil.getItem(player, Ingredient.of(CoreInit.COIL_CASE.get()));
				if (!coil.getValue().isEmpty()) {
					if (!living.level.isClientSide && coil.getValue().hurt(1, living.getRandom(), null)) {
						player.getInventory().setItem(coil.getKey(), new ItemStack(CoreInit.EMPTY_COIL_CASE.get()));
						player.getInventory().setChanged();
					}
					attacker.hurt(DamageSource.playerAttack(player), 20F);
					event.setAmount(0F);

					if (player != null && attacker instanceof Phantom) {
						ClimateCore.proxy.triggerAdvancement(player, "main/mosquito_coil");
					}

					event.setCanceled(true);
				}
			}
		}

		// potion
		float f2 = 1.0F;
		if (source.isProjectile()) {
			if (living.hasEffect(CoreInit.PROJ_RESISTANCE.get())) {
				MobEffectInstance eff = living.getEffect(CoreInit.PROJ_RESISTANCE.get());
				f2 = 1F - (eff.getAmplifier() * 0.20F);
				if (f2 < 0F)
					f2 = 0F;
			}
		}

		if (source.isExplosion() || source.isDamageHelmet()) {
			if (DCItemUtil.isWearArmorItem(CoreInit.HAT_SAFETY.get(), living, EquipmentSlot.HEAD)) {
				f2 *= 0.5F;
			}
		}

		if (source == DamageSource.FREEZE) {
			float armor = DCItemUtil.getArmorResistant(living, true);
			if (armor > 1.0F) {
				amount -= armor;
			}
			if (living.hasEffect(CoreInit.COLD_RESISTANCE.get())) {
				int f = living.getEffect(CoreInit.COLD_RESISTANCE.get()).getAmplifier() + 1;
				f2 -= 4.0F * f;
			}
			if (DCItemUtil.isWearArmorItem(CoreInit.LEGGINS_WADERS.get(), living, EquipmentSlot.CHEST)) {
				f2 = 0F;
			}
		}

		if (source == DamageSource.CACTUS || source == DamageSource.HOT_FLOOR || source == DamageSource.SWEET_BERRY_BUSH) {
			if (DCItemUtil.isWearArmorItem(CoreInit.BOOTS_SAFETY.get(), living, EquipmentSlot.FEET)) {
				f2 = 0F;
			}
		}

		if (source.isFall()) {
			if (DCItemUtil.isWearArmorItem(CoreInit.BOOTS_SAFETY.get(), living, EquipmentSlot.FEET)) {
				f2 *= 0.5F;
			}
		}

		if (b1 || amount < 0.5F || amount * dif * atk * f2 < 0.5F) {
			event.setCanceled(true);
			return;
		}

		event.setAmount(amount * dif * atk * f2);
	}

	@SubscribeEvent
	public static void onXpPickup(PlayerXpEvent.PickupXp event) {
		Player player = event.getEntity();
		ExperienceOrb orb = event.getOrb();

		int count = MagicUtil.hasCharmItem(player, new ItemStack(MagicInit.PENDANT_GOLD_BLUE.get()));
		int v = orb.getValue();
		if (count > 0) {
			CompoundTag tag = new CompoundTag();
			orb.addAdditionalSaveData(tag);
			short val = tag.getShort("Value");
			val = (short) Math.min(val * (count + 1), Short.MAX_VALUE);
			tag.putShort("Value", val);
			orb.readAdditionalSaveData(tag);
			v = val;
		}

		for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
			ItemStack item = player.getInventory().getItem(i);
			if (!item.isEmpty()) {
				if (!player.level.isClientSide && item.getItem() instanceof InertElementItem element) {
					if (element.isSuitablePlace(player) && element.charge(v, item) && element.getActivatedElement().get() != Items.AIR) {
						player.getInventory().setItem(i, new ItemStack(element.getActivatedElement().get()));
					}
					player.getInventory().setChanged();
				}
			}
		}

	}

	@SubscribeEvent
	public static void onDeath(LivingDeathEvent event) {
		LivingEntity living = event.getEntity();
		if (living != null && living.isAlive()) {
			ArrayList<ItemStack> charms = MagicUtil.getCharms(living, CharmType.DEFFENCE);
			int count = 0;
			for (ItemStack c : charms) {
				if (!c.isEmpty() && c.getItem() == MagicInit.BADGE_SILVER_GREEN.get()) {
					IJewelCharm charm = (IJewelCharm) c.getItem();
					if (charm.isActive(living, c)) {
						count++;
						charm.onConsumeResource(living, c);
					}
				}
			}
			if (count > 0) {
				living.setHealth(count * 2.0F);
				event.setCanceled(true);
			}
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

		ArrayList<ItemStack> charms = MagicUtil.getCharms(player, CharmType.DIG);
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

		int range = 0;
		int count = 2 * MagicUtil.hasCharmItem(player, new ItemStack(MagicInit.BADGE_SILVER_RED.get()));
		if (count > 0 && level instanceof ServerLevel serverLevel) {
			Direction dir = player.getDirection();
			if (player.getXRot() > 45F) {
				dir = Direction.UP;
			}
			if (player.getXRot() < -45F) {
				dir = Direction.DOWN;
			}

			List<BlockPos> targetPosList = getTargetPos(pos, dir, count);
			targetPosList.forEach((p2) -> {
				BlockState s2 = level.getBlockState(p2);
				BlockEntity e2 = level.getBlockEntity(p2);
				LootContext.Builder builder = (new LootContext.Builder(serverLevel))
						.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p2))
						.withParameter(LootContextParams.BLOCK_STATE, s2)
						.withOptionalParameter(LootContextParams.BLOCK_ENTITY, e2)
						.withOptionalParameter(LootContextParams.THIS_ENTITY, player)
						.withParameter(LootContextParams.TOOL, player.getMainHandItem());
				s2.getDrops(builder).forEach((item) -> {
					Block.popResource(serverLevel, p2, item);
				});
				s2.getBlock().destroy(serverLevel, p2, s2);
				serverLevel.setBlock(p2, Blocks.AIR.defaultBlockState(), 2);
			});

			player.getMainHandItem().hurtAndBreak(2, player, (p) -> {
				p.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			});

			event.setCanceled(true);
		}
	}

	static List<BlockPos> getTargetPos(BlockPos pos, Direction face, int r) {
		List<BlockPos> ret = Lists.newArrayList();
		if (face.getAxis() == Direction.Axis.X) {
			for (int z = -r; z <= r; z++) {
				for (int y = -r; y <= r; y++) {
					BlockPos p = pos.offset(0, y, z);
					ret.add(p);
				}
			}
			return ret;
		} else if (face.getAxis() == Direction.Axis.Z) {
			for (int x = -r; x <= r; x++) {
				for (int y = -r; y <= r; y++) {
					BlockPos p = pos.offset(x, y, 0);
					ret.add(p);
				}
			}
			return ret;
		} else {
			for (int z = -r; z <= r; z++) {
				for (int x = -r; x <= r; x++) {
					BlockPos p = pos.offset(x, 0, z);
					ret.add(p);
				}
			}
			return ret;
		}
	}

	@SubscribeEvent
	public static void onPotionEffectColor(PotionColorCalculationEvent event) {
		if (event.getEffects() != null && !event.getEffects().isEmpty()) {
			if (event.getEffects().size() == 1 && event.getEffects().stream().anyMatch((e) -> e.getEffect() == CoreInit.WET.get())) {
				event.shouldHideParticles(true);
			}
		}
	}

}
