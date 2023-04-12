package defeatedcrow.hac.magic.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.MagicInit;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class ColorSeedBagItem extends ItemDC {

	final MagicColor color;

	public ColorSeedBagItem(MagicColor c) {
		super(new Item.Properties().tab(MagicInit.MAGIC).stacksTo(16), null);
		color = c;
	}

	@Override
	public String getRegistryName() {
		return "magic/seedbag_" + color.toString();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/seedbag_" + color.toString()));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack item = player.getItemInHand(hand);
		player.startUsingItem(hand);
		if (level instanceof ServerLevel) {
			if (onUsing(level, player, player.blockPosition(), item)) {
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 0.6F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				if (!player.getAbilities().instabuild) {
					item.shrink(1);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				player.swing(hand, true);
			}
			return InteractionResultHolder.success(item);
		}
		return InteractionResultHolder.success(item);
	}

	public boolean onUsing(Level level, Player player, BlockPos pos, ItemStack card) {
		TagKey<Item> tag = TagDC.ItemTag.SEED_WHITE;
		if (color.isBlue)
			tag = TagDC.ItemTag.SEED_BLUE;
		if (color.isBlack)
			tag = TagDC.ItemTag.SEED_BLACK;
		if (color.isRed)
			tag = TagDC.ItemTag.SEED_RED;
		if (color.isGreen)
			tag = TagDC.ItemTag.SEED_GREEN;

		Ingredient target = Ingredient.of(tag);
		if (!target.isEmpty() && target.getItems().length > 0) {
			ItemStack[] seeds = target.getItems();
			int i = level.getRandom().nextInt(seeds.length);
			ItemStack seed = seeds[i];
			if (!DCUtil.isEmpty(seed)) {
				ItemEntity drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), seed.copy());
				level.addFreshEntity(drop);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.literal(color.name());
		tier.withStyle(color.chatColor);
		list.add(tier);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.seedbag.desc");
			list.add(itemTip);
			TagKey<Item> tag = TagDC.ItemTag.SEED_WHITE;
			if (color.isBlue)
				tag = TagDC.ItemTag.SEED_BLUE;
			if (color.isBlack)
				tag = TagDC.ItemTag.SEED_BLACK;
			if (color.isRed)
				tag = TagDC.ItemTag.SEED_RED;
			if (color.isGreen)
				tag = TagDC.ItemTag.SEED_GREEN;
			Ingredient target = Ingredient.of(tag);
			if (!target.isEmpty()) {
				for (ItemStack seed : target.getItems()) {
					MutableComponent name = Component.literal("-");
					name.append(seed.getDisplayName());
					list.add(name);
				}
			}
		} else {
			list.add(Component.translatable("dcs.tip.shift"));
		}
		super.appendHoverText(item, level, list, flag);
	}

}
