package defeatedcrow.hac.magic.material.item.card;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.ICardMagic;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.magic.material.MagicInit;
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
		return "magic/" + name;
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
		return CharmType.TOOL;
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
		return true;
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
			player.startUsingItem(hand);
			if (level instanceof ServerLevel && isActive(player, itemstack)) {
				level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
					onConsumeResource(player, itemstack);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				player.swing(hand, true);
				return onUsing(level, player, hand, itemstack);
			}
		}
		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack card, BlockHitResult res) {
		return InteractionResultHolder.pass(card);
	}

	public InteractionResultHolder<ItemStack> onUsing(Level level, Player player, InteractionHand hand, ItemStack card) {
		return InteractionResultHolder.success(card);
	}

}
