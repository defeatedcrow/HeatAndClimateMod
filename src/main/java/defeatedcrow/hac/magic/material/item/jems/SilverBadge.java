package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SilverBadge extends MagicJewelBase {

	public SilverBadge(MagicColor c) {
		super("badge_s_" + c.toString(), c, Rarity.RARE, TagDC.ItemTag.MAGIC_BADGE);
	}

	@Override
	public CharmType getCharmType() {
		MagicColor c = getColor();
		if (c.isBlue) {
			return CharmType.KEY;
		}
		if (c.isBlack) {
			return CharmType.ATTACK;
		}
		return CharmType.SPECIAL;
	}

	@Override
	public boolean onAttacking(LivingEntity attacker, LivingEntity target, DamageSource source, float damage, ItemStack charm) {
		if (getColor().isBlack) {
			int f = charm.getCount();
			if (attacker != null && target != null) {
				boolean flag = false;
				if (attacker instanceof OwnableEntity ownable) {
					Entity owner = ownable.getOwner();
					if (owner instanceof Player player) {
						source = DamageSource.playerAttack(player);
					} else if (owner instanceof LivingEntity mob) {
						source = DamageSource.mobAttack(mob);
					} else {
						source = DamageSource.MAGIC;
					}
					flag = true;
				} else if (attacker instanceof Player player) {
					Monster monster = player.getLevel().getNearestEntity(Monster.class, TargetingConditions.forCombat().range(16D),
						target, target.getX(), target.getY(), target.getZ(), player.getBoundingBox().inflate(16D));
					if (monster != null) {
						source = DamageSource.mobAttack(monster);
					} else {
						source = DamageSource.MAGIC;
					}
					flag = true;
				}
				if (flag) {
					target.hurt(source, damage);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult res = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (res.getType() == HitResult.Type.BLOCK) {
			return onBlockHit(level, player, hand, itemstack, ((BlockHitResult) res).getBlockPos(), ((BlockHitResult) res).getDirection());
		} else {
			return onBlockHit(level, player, hand, itemstack, player.blockPosition(), Direction.UP);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack charm, BlockPos pos, Direction dir) {
		if (!DCUtil.isEmpty(charm) && getColor().isBlue && level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = Vec3.atBottomCenterOf(pos.relative(dir));
			BlockPos p1 = new BlockPos(vec3);
			BlockPos p2 = p1.above();
			if (level.getBlockState(p1).getMaterial().isReplaceable() && level.getBlockState(p2).getMaterial().isReplaceable()) {
				ResourceKey<Level> dim = serverLevel.dimension();
				CompoundTag tag = charm.getOrCreateTag();
				tag.putString(TagKeyDC.DIM_LOCATION, dim.location().toString());
				tag.putInt(TagKeyDC.POS_X, p1.getX());
				tag.putInt(TagKeyDC.POS_Y, p1.getY());
				tag.putInt(TagKeyDC.POS_Z, p1.getZ());
				charm.setTag(tag);
			}
		}
		return InteractionResultHolder.success(charm);
	}

	@Override
	public boolean onUsing(ServerPlayer owner, ItemStack charm) {
		if (!DCUtil.isEmpty(charm) && getColor().isBlue) {
			ServerLevel serverLevel = owner.getLevel();
			if (charm.hasTag() && charm.getTag().contains(TagKeyDC.DIM_LOCATION)) {
				CompoundTag tag = charm.getTag();
				String s1 = tag.getString(TagKeyDC.DIM_LOCATION);
				double dx = tag.getInt(TagKeyDC.POS_X) + 0.5D;
				double dy = tag.getInt(TagKeyDC.POS_Y) + 0.05D;
				double dz = tag.getInt(TagKeyDC.POS_Z) + 0.5D;
				ResourceKey<Level> dim = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(s1));
				if (!serverLevel.dimension().equals(dim)) {
					ServerLevel nextLevel = serverLevel.getServer().getLevel(dim);
					nextLevel.getProfiler().push("portal");
					owner.setPortalCooldown();
					owner.changeDimension(nextLevel);
					nextLevel.getProfiler().pop();
				}
				owner.teleportToWithTicket(dx, dy, dz);
				owner.resetFallDistance();
				return true;
			}
		}
		return false;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.badge_s.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.badge_s.desc." + getColor().toString());
			list.add(itemTip);

			if (getColor().isBlack || getColor().isBlue) {
				MutableComponent itemTip2 = Component.translatable("dcs.tip.badge_s.desc2." + getColor().toString());
				list.add(itemTip2);
			}

			if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
				if (!getColor().isBlue) {
					int i = this.getMagicCostEXP(item);
					MutableComponent cost = Component.literal("COST: " + i + "Xp");
					list.add(cost);
				}
			}
		}
		super.appendHoverText(item, level, list, flag);
	}

}
