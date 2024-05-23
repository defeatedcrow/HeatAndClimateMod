package defeatedcrow.hac.food.event;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CraftingFoodEvent {

	@SubscribeEvent
	public static void onCraft(PlayerEvent.ItemCraftedEvent event) {
		ItemStack res = event.getCrafting();
		Container matrix = event.getInventory();
		if (!res.isEmpty() && (res.isEdible() || res.getItem() instanceof IFoodTaste) && matrix != null) {
			int taste = 0;
			boolean unsafe = false;
			for (int i = 0; i < matrix.getContainerSize(); i++) {
				ItemStack check = matrix.getItem(i);
				if (!check.isEmpty()) {
					if (check.getItem() instanceof IFoodTaste) {
						taste += ((IFoodTaste) check.getItem()).getTaste(check);
					} else {
						taste += getTaste(check.getItem()).map((vt) -> {
							return vt.f;
						}).orElse(0);
					}
					if (check.getTag() != null && check.getTag().contains(TagKeyDC.UNSAFE)) {
						if (check.getTag().getBoolean(TagKeyDC.UNSAFE)) {
							unsafe = true;
						}
					} else if (check.is(TagDC.ItemTag.HAC_UNSAFE_FOODS)) {
						unsafe = true;
					}
				}
			}
			if (res.getItem() instanceof IFoodTaste) {
				IFoodTaste food = (IFoodTaste) res.getItem();
				if (taste != 0) {
					// DCLogger.debugInfoLog("Taste: " + taste);
					food.setTaste(event.getCrafting(), taste);
				}
			}
			if (unsafe) {
				CompoundTag tag = res.getOrCreateTag();
				tag.putBoolean(TagKeyDC.UNSAFE, true);
				res.setTag(tag);
			}
		}
	}

	public static int getResultTaste(NonNullList<ItemStack> inputs, int[] cons) {
		int lim = Math.min(inputs.size(), cons.length);
		int taste = 0;
		for (int i = 0; i < lim; i++) {
			ItemStack check = inputs.get(i);
			if (!check.isEmpty()) {
				if (check.getItem() instanceof IFoodTaste) {
					taste += ((IFoodTaste) check.getItem()).getTaste(check);
				} else {
					taste += getTaste(check.getItem()).map((vt) -> {
						return vt.f;
					}).orElse(0);
				}
			}
		}
		return taste;
	}

	public static boolean checkUnsafe(NonNullList<ItemStack> inputs, int[] cons) {
		int lim = Math.min(inputs.size(), cons.length);
		boolean unsafe = false;
		for (int i = 0; i < lim; i++) {
			ItemStack check = inputs.get(i);
			if (!check.isEmpty()) {
				if (check.is(TagDC.ItemTag.HAC_UNSAFE_FOODS)) {
					unsafe = true;
				}
			}
		}
		return unsafe;
	}

	private static Optional<Taste> getTaste(Item item) {
		for (Taste t : vanilla) {
			if (t.mutch(item)) {
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}

	private static List<Taste> vanilla = ImmutableList.of(
			new Taste(Items.HONEY_BOTTLE, 1),
			new Taste(Items.BEEF, 2),
			new Taste(Items.PORKCHOP, 1),
			new Taste(Items.CHICKEN, 1),
			new Taste(Items.MUTTON, 1),
			new Taste(Items.COOKED_BEEF, 2),
			new Taste(Items.COOKED_PORKCHOP, 1),
			new Taste(Items.COOKED_CHICKEN, 1),
			new Taste(Items.COOKED_MUTTON, 1),
			new Taste(Items.BAKED_POTATO, 1),
			new Taste(Items.APPLE, 1),
			new Taste(Items.SALMON, 1),
			new Taste(Items.COOKED_SALMON, 1),
			new Taste(Items.PUFFERFISH, -1),
			new Taste(Items.TROPICAL_FISH, -1),
			new Taste(Items.CHORUS_FRUIT, -1),
			new Taste(Items.SPIDER_EYE, -2),
			new Taste(Items.ROTTEN_FLESH, -2));

	private record Taste(Item item, int f) {
		boolean mutch(Item in) {
			return in == item;
		}
	}

}
