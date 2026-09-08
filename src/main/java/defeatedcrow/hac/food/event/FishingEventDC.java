package defeatedcrow.hac.food.event;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FishingEventDC {

	@SubscribeEvent
	public static void onFishing(ItemFishedEvent event) {
		NonNullList<ItemStack> fishes = event.getDrops();
		if (!fishes.isEmpty() && event.getHookEntity() != null && event.getEntity() != null) {
			ItemStack item = fishes.get(0);
			ItemStack held = event.getEntity().getMainHandItem();
			if (item.is(TagDC.ItemTag.FISH_VANILLA)) {
				List<ItemStack> replace = Lists.newArrayList();
				Level level = event.getHookEntity().level();
				BlockPos pos = event.getHookEntity().blockPosition();
				Holder<Biome> biome = level.getBiome(pos);
				replace.addAll(DCUtil.getFish(level, biome, event.getEntity(), held));

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
				}
			} else if (item.is(Items.FISHING_ROD)) {
				List<ItemStack> replace = Lists.newArrayList();
				Level level = event.getHookEntity().level();
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
