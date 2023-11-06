package defeatedcrow.hac.food.event;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.material.IFoodTaste;
import net.minecraft.core.NonNullList;
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
		if (!res.isEmpty() && res.getItem() instanceof IFoodTaste && matrix != null) {
			IFoodTaste food = (IFoodTaste) res.getItem();
			int taste = 0;
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
				}
			}
			if (taste != 0) {
				// DCLogger.debugInfoLog("Taste: " + taste);
				food.setTaste(event.getCrafting(), taste);
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
		new Taste(Items.COOKED_BEEF, 2),
		new Taste(Items.COOKED_PORKCHOP, 1),
		new Taste(Items.COOKED_CHICKEN, 1),
		new Taste(Items.BAKED_POTATO, 1),
		new Taste(Items.APPLE, 1),
		new Taste(Items.SALMON, 1),
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
