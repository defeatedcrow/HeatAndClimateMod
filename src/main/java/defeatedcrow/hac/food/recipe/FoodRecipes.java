package defeatedcrow.hac.food.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;

public class FoodRecipes {
	public static FoodRecipes INSTANCE = new FoodRecipes() {};

	public final List<Smelting> Smeltings;

	private FoodRecipes() {
		ImmutableList.Builder<Smelting> list = ImmutableList.builder();
		list.add(new Smelting(() -> FoodInit.BREAD_ROUND_BAKED_ITEM.get(), () -> FoodInit.BREAD_ROUND_RAW_ITEM.get(), 120));
		Smeltings = list.build();
	}

	public record Smelting(Supplier<Item> output, Supplier<Item> input, int time) {

	}

}
