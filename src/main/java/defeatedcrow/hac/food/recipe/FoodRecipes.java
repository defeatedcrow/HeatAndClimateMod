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
		list.add(new Smelting(FoodInit.BREAD_ROUND_BAKED_ITEM, FoodInit.BREAD_ROUND_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_SQUARE_BAKED_ITEM, FoodInit.BREAD_SQUARE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_NUTS_BAKED_ITEM, FoodInit.BREAD_NUTS_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_CINNAMON_BAKED_ITEM, FoodInit.BREAD_CINNAMON_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_ANKO_BAKED_ITEM, FoodInit.BREAD_ANKO_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_CREAM_BAKED_ITEM, FoodInit.BREAD_CREAM_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_SAUSAGE_BAKED_ITEM, FoodInit.BREAD_SAUSAGE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.STICK_BEEF_COOKED, FoodInit.STICK_BEEF_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_PORK_COOKED, FoodInit.STICK_PORK_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_MUTTON_COOKED, FoodInit.STICK_MUTTON_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_CHICKEN_COOKED, FoodInit.STICK_CHICKEN_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_OFFAL_COOKED, FoodInit.STICK_OFFAL_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_BEEF_COOKED, FoodInit.PLATE_BEEF_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_MEAT_COOKED, FoodInit.PLATE_MEAT_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_LEGS_COOKED, FoodInit.PLATE_LEGS_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_GARLIC_COOKED, FoodInit.PLATE_GARLIC_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_CHICKEN_BIG_COOKED, FoodInit.PLATE_CHICKEN_BIG_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_FISH_COOKED, FoodInit.PLATE_FISH_RAW, 120));
		Smeltings = list.build();
	}

	public record Smelting(
			Supplier<Item> output,
			Supplier<Item> input, int time) {}

}
