package defeatedcrow.hac.food.event;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
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
					if (check.is(TagDC.ItemTag.HAC_SEASONING)) {
						taste += 3;
					} else {
						taste += IFoodTaste.getFoodTaste(check);
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
				if (res.is(TagDC.ItemTag.HAC_SEASONING) || food.isSeasoning()) {
					food.setTaste(event.getCrafting(), 2);
				} else if (taste != 0) {
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
				if (check.is(TagDC.ItemTag.HAC_SEASONING)) {
					taste += 3;
				} else {
					taste += IFoodTaste.getFoodTaste(check);
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

}
