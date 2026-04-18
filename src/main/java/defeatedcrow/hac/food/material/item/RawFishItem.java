package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;

public class RawFishItem extends FoodMaterialItemDC {

	public RawFishItem(String s, Rarity rare, TagKey<Item> pair) {
		super(new Item.Properties().rarity(rare).tab(FoodInit.FOOD), s, pair);
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity drop) {
		if (drop != null && !DCUtil.isEmpty(stack) && drop.getThrower() != null && drop.getAge() > 100) {
			Player owner = drop.getThrower() != null ? drop.getLevel().getPlayerByUUID(drop.getThrower()) : null;
			float f = drop.getEyeHeight();
			if (owner != null && drop.getItem().hasTag() && drop.getItem().getTag().contains("Released_Fish"))
				if (drop.isInWater() && drop.getFluidHeight(FluidTags.WATER) > f) {
					if (!drop.getLevel().isClientSide()) {
						ServerLevel serverlevel = (ServerLevel) drop.getLevel();
						drop.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (drop.getLevel().random.nextFloat() - drop.getLevel().random.nextFloat()) * 0.4F);
						double d3 = drop.getY() + 0.5D;
						serverlevel.sendParticles(ParticleTypes.BUBBLE, drop.getX(), d3, drop.getZ(), (int) (1.0F + drop.getBbWidth() * 20.0F), (double) drop.getBbWidth(), 0.0D, (double) drop.getBbWidth(), (double) 0.2F);
						serverlevel.sendParticles(ParticleTypes.SPLASH, drop.getX(), d3, drop.getZ(), (int) (1.0F + drop.getBbWidth() * 20.0F), (double) drop.getBbWidth(), 0.0D, (double) drop.getBbWidth(), (double) 0.2F);

						// playerに幸運を与える
						int i = 600;
						if (owner.hasEffect(MobEffects.LUCK)) {
							i += owner.getEffect(MobEffects.LUCK).getDuration();
						}
						owner.addEffect(new MobEffectInstance(MobEffects.LUCK, i));
						drop.setItem(ItemStack.EMPTY);
					}
				}
		}
		return false;
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent s0 = Component.translatable("dcs.tip.fishdata.luck").withStyle(ChatFormatting.GRAY);
		list.add(s0);
		MutableComponent s1 = Component.literal("=== Fish Data ===").withStyle(ChatFormatting.AQUA);
		list.add(s1);
		MutableComponent s2 = Component.translatable("dcs.tip.fishdata.biome");
		if (item.is(TagDC.ItemTag.FISH_RIVER)) {
			s2.append(Component.translatable("dcs.tip.fishdata.river"));
		}
		if (item.is(TagDC.ItemTag.FISH_BEACH)) {
			s2.append(Component.translatable("dcs.tip.fishdata.beach"));
		}
		if (item.is(TagDC.ItemTag.FISH_MANGROVE)) {
			s2.append(Component.translatable("dcs.tip.fishdata.mangrove"));
		}
		if (item.is(TagDC.ItemTag.FISH_OCEAN)) {
			s2.append(Component.translatable("dcs.tip.fishdata.ocean"));
		}
		if (item.is(TagDC.ItemTag.FISH_DEEP_OCEAN)) {
			s2.append(Component.translatable("dcs.tip.fishdata.deep_ocean"));
		}
		if (item.is(TagDC.ItemTag.FISH_SHALLOW)) {
			s2.append("(").append(Component.translatable("dcs.tip.fishdata.shallow")).append(")");
		} else if (item.is(TagDC.ItemTag.FISH_FLOOR)) {
			s2.append("(").append(Component.translatable("dcs.tip.fishdata.floor")).append(")");
		}
		list.add(s2);
		if (item.is(TagDC.ItemTag.FISH_COLD_WATER)) {
			MutableComponent s4 = Component.translatable("dcs.tip.fishdata.coldwater").withStyle(ChatFormatting.BLUE);
			list.add(s4);
		} else if (item.is(TagDC.ItemTag.FISH_TROPICAL)) {
			MutableComponent s4 = Component.translatable("dcs.tip.fishdata.tropical").withStyle(ChatFormatting.GOLD);
			list.add(s4);
		}
		if (item.is(TagDC.ItemTag.FISH_NIGHT)) {
			MutableComponent s3 = Component.translatable("dcs.tip.fishdata.time").append(Component.translatable("dcs.tip.fishdata.night").withStyle(ChatFormatting.DARK_PURPLE));
			list.add(s3);
		} else if (item.is(TagDC.ItemTag.FISH_DAY)) {
			MutableComponent s3 = Component.translatable("dcs.tip.fishdata.time").append(Component.translatable("dcs.tip.fishdata.day").withStyle(ChatFormatting.YELLOW));
			list.add(s3);
		}
		MutableComponent s5 = Component.literal("================").withStyle(ChatFormatting.AQUA);
		list.add(s5);
	}

}
