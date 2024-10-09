package defeatedcrow.hac.magic.material.item.card;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.ICardMagic;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.magic.MagicUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class MagicCardBase extends ItemDC implements ICardMagic {

	protected String name;
	private final MagicColor color;
	private final Rarity rarity;

	public MagicCardBase(MagicColor c, Rarity rare, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC).stacksTo(16).rarity(rare), pair);
		name = c.isBasic ? c.toString() + "_" + rare.toString().toLowerCase() : c.toString();
		color = c;
		rarity = rare;
	}

	@Override
	public String getRegistryName() {
		return "magic/card_" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/card_" + name));
	}

	@Override
	public Item getItem() {
		return this;
	}

	@Override
	public CharmType getCharmType() {
		return CharmType.INSTANT;
	}

	@Override
	public MagicType getType() {
		return MagicType.INSTANT;
	}

	@Override
	public MagicColor getColor() {
		return color;
	}

	@Override
	public Rarity getTier() {
		return rarity;
	}

	@Override
	public boolean isActive(LivingEntity owner, ItemStack card) {
		if (owner != null && owner instanceof Player player) {
			int cost = getMagicCostEXP(card);
			if (cost < 1 || player.totalExperience > cost) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onConsumeResource(LivingEntity owner, ItemStack card) {
		if (owner != null && owner instanceof Player player) {
			if (isActive(player, card)) {
				int cost = getMagicCostEXP(card);
				player.giveExperiencePoints(-cost);
			}
		}
	}

	@Override
	public int getMagicCostEXP(ItemStack card) {
		if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
			int base = ConfigCommonBuilder.INSTANCE.vMagicCost.get();
			switch (rarity) {
			case EPIC:
				base *= 10;
				break;
			case RARE:
				base *= 5;
				break;
			case UNCOMMON:
				base *= 2;
				break;
			case COMMON:
			default:
				break;
			}
			return base;
		}
		return 0;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult res = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (res.getType() == HitResult.Type.BLOCK) {
			return onBlockHit(level, player, hand, itemstack, (BlockHitResult) res);
		} else {
			return onEmptyHit(level, player, hand, itemstack);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack card, BlockHitResult res) {
		player.startUsingItem(hand);
		if (!level.isClientSide && level instanceof ServerLevel serverLevel && isActive(player, card)) {
			float boost = MagicUtil.getMagicBooster(player);
			if (onUsing(level, player, res.getBlockPos(), res.getDirection(), card, boost)) {
				MsgEffectToC.sendToClient(serverLevel, res.getBlockPos(), 31);
				if (!player.getAbilities().instabuild) {
					card.shrink(1);
					onConsumeResource(player, card);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				player.swing(hand, true);
			}
			return InteractionResultHolder.success(card);
		}
		return InteractionResultHolder.success(card);
	}

	public InteractionResultHolder<ItemStack> onEmptyHit(Level level, Player player, InteractionHand hand, ItemStack card) {
		player.startUsingItem(hand);
		if (!level.isClientSide && level instanceof ServerLevel serverLevel && isActive(player, card)) {
			float boost = MagicUtil.getMagicBooster(player);
			if (onUsing(level, player, player.blockPosition(), player.getDirection(), card, boost)) {
				MsgEffectToC.sendToClient(serverLevel, player.position().add(0D, 1.0D, 0D), 31);
				if (!player.getAbilities().instabuild) {
					card.shrink(1);
					onConsumeResource(player, card);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				player.swing(hand, true);
			}
			return InteractionResultHolder.success(card);
		}
		return InteractionResultHolder.success(card);
	}

	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float boost) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.literal(color.isBasic ? color.name() + " " + item.getRarity() : color.name());
		tier.withStyle(color.chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.magic_card.name." + name);
		itemName.withStyle(color.chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.magic_card.desc." + name);
			list.add(itemTip);
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable Level level, List<Component> list) {
		// if (ClimateCore.proxy.keyShiftPushed() && !getColor().isBasic) {
		// MutableComponent itemTip = Component.translatable("dcs.tip.magic_card.flavor." + name);
		// list.add(itemTip);
		// }
	}

}
