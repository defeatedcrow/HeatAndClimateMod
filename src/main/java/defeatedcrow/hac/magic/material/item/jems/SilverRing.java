package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class SilverRing extends MagicJewelBase {

	public SilverRing(MagicColor c) {
		super("ring_s_" + c.toString(), c, Rarity.COMMON, TagDC.ItemTag.MAGIC_RING);
	}

	@Override
	public CharmType getCharmType() {
		return CharmType.CONSTANT;
	}

	@Override
	public void constantEffect(LivingEntity owner, ItemStack charm) {
		if (owner != null && !owner.level.isClientSide) {
			int i = DCUtil.isEmpty(charm) ? 0 : charm.getCount() - 1;
			MagicColor color = getColor();
			if (color.isWhite) {
				owner.addEffect(new MobEffectInstance(MobEffects.JUMP, 300, i));
			} else if (color.isBlue) {
				owner.addEffect(new MobEffectInstance(CoreInit.COLD_RESISTANCE.get(), 300));
			} else if (color.isBlack) {
				owner.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
			} else if (color.isRed) {
				owner.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300));
			} else if (color.isGreen) {
				if (owner.getLevel().getGameTime() % 10 == 0) {
					double d = 4D + (2D * i);
					List<ItemEntity> list = owner.level.getEntitiesOfClass(ItemEntity.class, owner.getBoundingBox().inflate(d), EntitySelector.ENTITY_STILL_ALIVE);
					list.stream().forEach(drop -> {
						drop.setPos(owner.getX(), owner.getY() + 0.15D, owner.getZ());
					});
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.ring_s.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.ring_s.desc." + getColor().toString());
			list.add(itemTip);
		}
		super.appendHoverText(item, level, list, flag);
	}

}
