package defeatedcrow.hac.food.event;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FishingEventDC {

	static final List<ItemStack> fishList = Lists.newArrayList();

	@SubscribeEvent
	public static void onFishing(ItemFishedEvent event) {
		if (fishList.isEmpty()) {
			// 一度だけ取得する
			fishList.addAll(TagUtil.getItemList(TagDC.ItemTag.FISH_ROD));
		}
		NonNullList<ItemStack> fishes = event.getDrops();
		if (!fishes.isEmpty() && event.getHookEntity() != null && event.getEntity() != null) {
			ItemStack item = fishes.get(0);
			ItemStack held = event.getEntity().getMainHandItem();
			if (item.is(TagDC.ItemTag.FISH_VANILLA)) {
				List<ItemStack> replace = Lists.newArrayList();
				Level level = event.getHookEntity().getLevel();
				BlockPos pos = event.getHookEntity().blockPosition();
				Holder<Biome> biome = level.getBiome(pos);
				boolean isMangrove = biome.is(Biomes.MANGROVE_SWAMP);
				boolean isBeach = biome.is(BiomeTags.IS_BEACH) || biome.is(Biomes.STONY_SHORE);
				boolean isOcean = biome.is(BiomeTags.IS_OCEAN);
				boolean isDeepOcean = biome.is(BiomeTags.IS_DEEP_OCEAN);
				boolean isRiver = !isMangrove && !isBeach && !isOcean && !isDeepOcean;

				int big = held.getEnchantmentLevel(CoreInit.BIG_GAME_FISHING.get());
				int bottom = held.getEnchantmentLevel(CoreInit.BOTTOM_FISHING.get());
				int squid = held.getEnchantmentLevel(CoreInit.SQUID_FISHING.get());
				int luck = held.getEnchantmentLevel(Enchantments.FISHING_SPEED);
				if (event.getEntity().hasEffect(MobEffects.LUCK)) {
					luck++;
				}

				int time = DCTimeHelper.currentTime(level);
				boolean day = time > 7 && time < 17;
				boolean night = time < 5 || time > 19;

				float temp = biome.get().getBaseTemperature();
				boolean cold = temp < 0.3F && biome.is(Tags.Biomes.IS_COLD);
				boolean warm = temp > 0.3F && biome.is(Tags.Biomes.IS_HOT);

				int rand = level.random.nextInt(100);
				if (!day && !night) {
					// マズメは確率アップ
					rand -= 10;
				}
				rand -= luck * 10;

				List<ItemStack> cL = Lists.newArrayList();
				List<ItemStack> uL = Lists.newArrayList();
				List<ItemStack> rL = Lists.newArrayList();
				for (ItemStack fish : fishList) {
					if (night && fish.is(TagDC.ItemTag.FISH_DAY))
						continue;
					else if (day && fish.is(TagDC.ItemTag.FISH_NIGHT))
						continue;
					if (cold && fish.is(TagDC.ItemTag.FISH_TROPICAL))
						continue;
					else if (warm && fish.is(TagDC.ItemTag.FISH_COLD_WATER))
						continue;

					if (big > 0 && level.getRandom().nextInt(big + 1) > 0 && !fish.is(TagDC.ItemTag.FISH_LARGE)) {
						continue;
					} else if (bottom > 0 && level.getRandom().nextInt(bottom + 1) > 0 && !fish.is(TagDC.ItemTag.FISH_FLOOR)) {
						continue;
					} else if (squid > 0 && level.getRandom().nextInt(squid + 1) > 0 && !fish.is(TagDC.ItemTag.SQUID)) {
						continue;
					}

					boolean flag = false;
					if (isMangrove && fish.is(TagDC.ItemTag.FISH_MANGROVE))
						flag = true;
					if (isBeach && fish.is(TagDC.ItemTag.FISH_BEACH))
						flag = true;
					if (isOcean && fish.is(TagDC.ItemTag.FISH_OCEAN))
						flag = true;
					if (isDeepOcean && fish.is(TagDC.ItemTag.FISH_DEEP_OCEAN))
						flag = true;
					if (isRiver && fish.is(TagDC.ItemTag.FISH_RIVER))
						flag = true;

					if (flag) {
						if (fish.getRarity() == Rarity.RARE)
							rL.add(fish.copy());
						else if (fish.getRarity() == Rarity.UNCOMMON)
							uL.add(fish.copy());
						else
							cL.add(fish.copy());
					}
				}

				DCLogger.debugInfoLog("Event rand" + rand);
				if (rand < 15 || big > 0 || bottom > 0 || squid > 0) {
					if (!rL.isEmpty()) {
						replace.addAll(rL);
					}
					if (!uL.isEmpty()) {
						replace.addAll(uL);
					}
					if (!cL.isEmpty()) {
						replace.addAll(cL);
					}
				} else if (rand < 50) {
					if (!uL.isEmpty()) {
						replace.addAll(uL);
					}
					if (!cL.isEmpty()) {
						replace.addAll(cL);
					}
				} else if (!cL.isEmpty()) {
					replace.addAll(cL);
				}

				DCLogger.debugInfoLog("Fish list: " + (replace.isEmpty() ? "empty" : replace.size()));
				if (!replace.isEmpty()) {
					for (ItemStack fish : replace) {
						DCLogger.debugInfoLog(fish.getDisplayName().getString());
					}
				}
				if (!replace.isEmpty()) {
					ItemStack fish = ItemStack.EMPTY;
					if (replace.size() > 1) {
						int i = level.random.nextInt(replace.size());
						fish = replace.get(i).copy();
					} else {
						fish = replace.get(0).copy();
					}
					DCLogger.debugInfoLog("Event result: " + fish.getDisplayName().getString());
					if (!level.isClientSide) {
						ItemEntity drop = new ItemEntity(level, event.getHookEntity().getX(), event.getHookEntity().getY(), event.getHookEntity().getZ(), fish);
						double d0 = event.getEntity().getX() - event.getHookEntity().getX();
						double d1 = event.getEntity().getY() - event.getHookEntity().getY();
						double d2 = event.getEntity().getZ() - event.getHookEntity().getZ();
						double d3 = 0.1D;
						drop.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
						level.addFreshEntity(drop);
						level.addFreshEntity(new ExperienceOrb(level, event.getEntity().getX(), event.getEntity().getY() + 0.5D, event.getEntity().getZ() + 0.5D, level.random.nextInt(6) + 1));
						if (fish.is(ItemTags.FISHES)) {
							event.getEntity().awardStat(Stats.FISH_CAUGHT, 1);
						}
					}
					event.setCanceled(true);
				} else {
					ItemStack fish = ItemStack.EMPTY;
					if (isMangrove && fish.is(TagDC.ItemTag.FISH_MANGROVE))
						fish = new ItemStack(Items.TROPICAL_FISH);
					if (isBeach && fish.is(TagDC.ItemTag.FISH_BEACH))
						fish = new ItemStack(Items.PUFFERFISH);
					if (isOcean && fish.is(TagDC.ItemTag.FISH_OCEAN))
						fish = new ItemStack(Items.PUFFERFISH);
					if (isDeepOcean && fish.is(TagDC.ItemTag.FISH_DEEP_OCEAN))
						fish = new ItemStack(Items.COD);
					if (isRiver && fish.is(TagDC.ItemTag.FISH_RIVER))
						fish = new ItemStack(Items.SALMON);
					if (!level.isClientSide) {
						ItemEntity drop = new ItemEntity(level, event.getHookEntity().getX(), event.getHookEntity().getY(), event.getHookEntity().getZ(), fish);
						double d0 = event.getEntity().getX() - event.getHookEntity().getX();
						double d1 = event.getEntity().getY() - event.getHookEntity().getY();
						double d2 = event.getEntity().getZ() - event.getHookEntity().getZ();
						double d3 = 0.1D;
						drop.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
						level.addFreshEntity(drop);
						level.addFreshEntity(new ExperienceOrb(level, event.getEntity().getX(), event.getEntity().getY() + 0.5D, event.getEntity().getZ() + 0.5D, level.random.nextInt(6) + 1));
						if (fish.is(ItemTags.FISHES)) {
							event.getEntity().awardStat(Stats.FISH_CAUGHT, 1);
						}
					}
				}

			} else if (item.is(Items.FISHING_ROD)) {
				List<ItemStack> replace = Lists.newArrayList();
				Level level = event.getHookEntity().getLevel();
				BlockPos pos = event.getHookEntity().blockPosition();
				Holder<Biome> biome = level.getBiome(pos);
				if (level.random.nextInt(20) < 4) {
					replace.addAll(TagUtil.getItemList(TagDC.ItemTag.MAGIC_PENDANT));
				} else if (level.random.nextInt(20) < 12) {
					replace.addAll(TagUtil.getItemList(TagDC.ItemTag.MAGIC_RING));
				}
				if (!replace.isEmpty()) {
					ItemStack fish = ItemStack.EMPTY;
					if (replace.size() > 1) {
						int i = level.random.nextInt(replace.size());
						fish = replace.get(i).copy();
					} else {
						fish = replace.get(0).copy();
					}
					DCLogger.debugInfoLog("Event result " + fish.getDisplayName().getString());
					if (!level.isClientSide) {
						ItemEntity drop = new ItemEntity(level, event.getHookEntity().getX(), event.getHookEntity().getY(), event.getHookEntity().getZ(), fish);
						double d0 = event.getEntity().getX() - event.getHookEntity().getX();
						double d1 = event.getEntity().getY() - event.getHookEntity().getY();
						double d2 = event.getEntity().getZ() - event.getHookEntity().getZ();
						double d3 = 0.1D;
						drop.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
						level.addFreshEntity(drop);
						level.addFreshEntity(new ExperienceOrb(level, event.getEntity().getX(), event.getEntity().getY() + 0.5D, event.getEntity().getZ() + 0.5D, level.random.nextInt(6) + 1));
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
