package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class SilverBracelet extends MagicJewelBase {

	public SilverBracelet(MagicColor c) {
		super("bracelet_s_" + c.getMainColor().toString(), c, Rarity.EPIC, TagDC.ItemTag.MAGIC_BRACELET);
	}

	@Override
	public CharmType getCharmType() {
		if (getColor() == MagicColor.WHITE_BLUE || getColor() == MagicColor.BLUE_GREEN || getColor() == MagicColor.GREEN_BLACK)
			return CharmType.CONSTANT;
		return CharmType.SPECIAL;
	}

	@Override
	public MagicType getMagicType() {
		return MagicType.OFFHAND;
	}

	@Override
	public void constantEffect(LivingEntity owner, ItemStack charm) {
		if (owner != null && !owner.level.isClientSide) {
			int i = DCUtil.isEmpty(charm) ? 0 : charm.getCount() - 1;
			MagicColor color = getColor();
			if (color == MagicColor.WHITE_BLUE) {
				owner.addEffect(new MobEffectInstance(CoreInit.BIRD.get(), 300));
			} else if (color == MagicColor.BLUE_GREEN) {
				owner.addEffect(new MobEffectInstance(CoreInit.FISH.get(), 300));
			} else if (color == MagicColor.GREEN_BLACK) {
				if (owner.isCrouching()) {
					owner.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 300));
				} else {
					owner.removeEffect(MobEffects.INVISIBILITY);
				}
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		return InteractionResultHolder.pass(itemstack);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.bracelet_s.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);

		if (flag) {
			MutableComponent itemTip = Component.translatable("dcs.tip.bracelet_s.desc." + getColor().toString());
			list.add(itemTip);

			if (ConfigCommonBuilder.INSTANCE.enFlavorText.get()) {
				MutableComponent itemTip2 = Component.translatable("dcs.tip.bracelet_s.flavor." + getColor().toString());
				itemTip2.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
				list.add(itemTip2);
			}
		}
	}

}
