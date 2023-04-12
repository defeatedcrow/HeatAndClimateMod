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
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

	protected final String name;
	private final MagicColor color;
	private final Rarity rarity;

	public MagicCardBase(MagicColor c, Rarity rare, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC).stacksTo(16).rarity(rare), pair);
		name = c.toString() + "_" + rare.toString().toLowerCase();
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
		if (level instanceof ServerLevel && isActive(player, card)) {
			if (onUsing(level, player, res.getBlockPos(), card)) {
				level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
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
		if (level instanceof ServerLevel && isActive(player, card)) {
			if (onUsing(level, player, player.blockPosition(), card)) {
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 0.6F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
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

	public boolean onUsing(Level level, Player player, BlockPos pos, ItemStack card) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.translatable(color.name() + " " + item.getRarity());
		tier.withStyle(color.chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.magic_card.name." + color.toString() + "_" + item.getRarity().toString().toLowerCase());
		itemName.withStyle(color.chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.magic_card.desc." + color.toString() + "_" + item.getRarity().toString().toLowerCase());
			list.add(itemTip);
		}
		super.appendHoverText(item, level, list, flag);
	}

}
