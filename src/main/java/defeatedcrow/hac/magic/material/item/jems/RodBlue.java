package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RodBlue extends MagicJewelBase {

	public RodBlue() {
		super("rod_blue_black", MagicColor.BLUE_BLACK, Rarity.EPIC, TagDC.ItemTag.MAGIC_STUFF);
	}

	@Override
	public CharmType getCharmType() {
		return CharmType.SPECIAL;
	}

	@Override
	public MagicType getMagicType() {
		return MagicType.MAINHAND;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult res = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (player != null && player.isCrouching() && res.getType() == HitResult.Type.BLOCK) {
			return onBlockHit(level, player, hand, itemstack, (BlockHitResult) res);
		} else if (res.getType() == HitResult.Type.ENTITY) {
			Entity target = ((EntityHitResult) res).getEntity();
			if (target instanceof LivingEntity living) {
				InteractionResult ret = interactLivingEntity(itemstack, player, living, hand);
				return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide);
			} else {
				return InteractionResultHolder.pass(itemstack);
			}
		} else {
			return InteractionResultHolder.pass(itemstack);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack charm, BlockHitResult res) {
		if (!DCUtil.isEmpty(charm) && level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = Vec3.atBottomCenterOf(res.getBlockPos().relative(res.getDirection()));
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

				if (player instanceof ServerPlayer sp) {
					MutableComponent mes = Component.translatable("dcs.tip.coodinate");
					MutableComponent mes2 = Component.literal(" X:" + p1.getX());
					mes2.append(Component.literal(" Y:" + p1.getY()));
					mes2.append(Component.literal(" Z:" + p1.getZ()));
					sp.sendSystemMessage(mes);
					sp.sendSystemMessage(mes2);
				}
			}
		}
		return InteractionResultHolder.success(charm);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack charm, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity != null && entity.isAlive() && player.getLevel() instanceof ServerLevel serverLevel) {
			if (charm.hasTag() && charm.getTag().contains(TagKeyDC.DIM_LOCATION) && isActive(player, charm)) {
				Vec3 origin = entity.getEyePosition();
				CompoundTag tag = charm.getTag();
				String s1 = tag.getString(TagKeyDC.DIM_LOCATION);
				double dx = tag.getInt(TagKeyDC.POS_X) + 0.5D;
				double dy = tag.getInt(TagKeyDC.POS_Y) + 0.05D;
				double dz = tag.getInt(TagKeyDC.POS_Z) + 0.5D;
				ResourceKey<Level> dim = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(s1));
				if (!serverLevel.dimension().equals(dim)) {
					ServerLevel nextLevel = serverLevel.getServer().getLevel(dim);
					nextLevel.getProfiler().push("portal");
					entity.setPortalCooldown();
					entity.changeDimension(nextLevel);
					nextLevel.getProfiler().pop();
				}
				entity.teleportToWithTicket(dx, dy, dz);
				entity.resetFallDistance();
				MsgEffectToC.sendToClient(serverLevel, origin, 77);
				this.onConsumeResource(player, charm);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
			int i = this.getMagicCostEXP(item);
			MutableComponent cost = Component.literal("COST: " + i + "Xp");
			list.add(cost);
		}
		MutableComponent itemName = Component.translatable("dcs.tip.rod.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);

		if (flag) {
			MutableComponent itemTip = Component.translatable("dcs.tip.rod.desc." + getColor().toString());
			list.add(itemTip);
			MutableComponent itemTip3 = Component.translatable("dcs.tip.rod.desc." + getColor().toString() + "_2");
			list.add(itemTip3);

			if (ConfigCommonBuilder.INSTANCE.enFlavorText.get()) {
				MutableComponent itemTip2 = Component.translatable("dcs.tip.rod.flavor." + getColor().toString());
				itemTip2.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
				list.add(itemTip2);
			}

			if (item.hasTag() && item.getTag().contains(TagKeyDC.DIM_LOCATION)) {
				CompoundTag tag = item.getTag();
				String s1 = tag.getString(TagKeyDC.DIM_LOCATION);
				int dx = tag.getInt(TagKeyDC.POS_X);
				int dy = tag.getInt(TagKeyDC.POS_Y);
				int dz = tag.getInt(TagKeyDC.POS_Z);
				list.add(Component.translatable("dcs.tip.coodinate").withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("DIM: " + s1).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("X: " + dx).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("Y: " + dy).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("Z: " + dz).withStyle(ChatFormatting.GRAY));
			}
		}
	}

}
