package defeatedcrow.hac.food.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class FoodRecipes {
	public static FoodRecipes INSTANCE = new FoodRecipes() {};

	public final List<Smelting> Smeltings;

	public final List<SmeltingBlock> SmeltingBlocks;

	private FoodRecipes() {
		ImmutableList.Builder<Smelting> list = ImmutableList.builder();
		list.add(new Smelting(FoodInit.BREAD_ROUND_BAKED_ITEM, FoodInit.BREAD_ROUND_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_SQUARE_BAKED_ITEM, FoodInit.BREAD_SQUARE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_NUTS_BAKED_ITEM, FoodInit.BREAD_NUTS_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_CINNAMON_BAKED_ITEM, FoodInit.BREAD_CINNAMON_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_ANKO_BAKED_ITEM, FoodInit.BREAD_ANKO_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_CREAM_BAKED_ITEM, FoodInit.BREAD_CREAM_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_SAUSAGE_BAKED_ITEM, FoodInit.BREAD_SAUSAGE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_FLAT_BAKED_ITEM, FoodInit.BREAD_FLAT_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.BREAD_TORTILLA_BAKED_ITEM, FoodInit.BREAD_TORTILLA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.PIZZA_TOMATO_BAKED_ITEM, FoodInit.PIZZA_TOMATO_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.PIZZA_MARGHERITA_BAKED_ITEM, FoodInit.PIZZA_MARGHERITA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.PIZZA_ROMANA_BAKED_ITEM, FoodInit.PIZZA_ROMANA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.PIZZA_TONNO_BAKED_ITEM, FoodInit.PIZZA_TONNO_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.PIZZA_SAUSAGE_BAKED_ITEM, FoodInit.PIZZA_SAUSAGE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.PIZZA_MENTAI_BAKED_ITEM, FoodInit.PIZZA_MENTAI_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.STICK_BEEF_COOKED, FoodInit.STICK_BEEF_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_PORK_COOKED, FoodInit.STICK_PORK_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_MUTTON_COOKED, FoodInit.STICK_MUTTON_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_CHICKEN_COOKED, FoodInit.STICK_CHICKEN_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_OFFAL_COOKED, FoodInit.STICK_OFFAL_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_FISH_COOKED, FoodInit.STICK_FISH_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_VEGI_COOKED, FoodInit.STICK_VEGI_RAW, 120));
		list.add(new Smelting(FoodInit.STICK_CORN_COOKED, FoodInit.STICK_CORN_RAW, 120));
		list.add(new Smelting(FoodInit.SWEETPOTATO_COOKED, FoodInit.SWEETPOTATO_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_BEEF_COOKED, FoodInit.PLATE_BEEF_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_MEAT_COOKED, FoodInit.PLATE_MEAT_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_LEGS_COOKED, FoodInit.PLATE_LEGS_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_GARLIC_COOKED, FoodInit.PLATE_GARLIC_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_CHICKEN_BIG_COOKED, FoodInit.PLATE_CHICKEN_BIG_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_FISH_COOKED, FoodInit.PLATE_FISH_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_BONE_MARROW_COOKED, FoodInit.PLATE_BONE_MARROW_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_STUFFED_BELL_COOKED, FoodInit.PLATE_STUFFED_BELL_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_STUFFED_PAPRIKA_COOKED, FoodInit.PLATE_STUFFED_PAPRIKA_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_PUMPKIN_COOKED, FoodInit.PLATE_PUMPKIN_RAW, 120));
		list.add(new Smelting(FoodInit.PLATE_POTATO_COOKED, FoodInit.PLATE_POTATO_RAW, 120));
		list.add(new Smelting(FoodInit.TART_APPLE_BAKED_ITEM, FoodInit.TART_APPLE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.TART_BERRY_BAKED_ITEM, FoodInit.TART_BERRY_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.TART_PEACH_BAKED_ITEM, FoodInit.TART_PEACH_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.TART_LEMON_BAKED_ITEM, FoodInit.TART_LEMON_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.TART_COCOA_BAKED_ITEM, FoodInit.TART_COCOA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.TART_PISTACHIO_BAKED_ITEM, FoodInit.TART_PISTACHIO_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.TART_QUICHE_BAKED_ITEM, FoodInit.TART_QUICHE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_DORIA_BAKED_ITEM, FoodInit.CASSEROLE_DORIA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_GRATIN_SHRIMP_BAKED_ITEM, FoodInit.CASSEROLE_GRATIN_SHRIMP_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_GRATIN_ONION_BAKED_ITEM, FoodInit.CASSEROLE_GRATIN_ONION_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_JANSSONS_FRESTELESE_BAKED_ITEM, FoodInit.CASSEROLE_JANSSONS_FRESTELESE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_SHEPHERDS_PIE_BAKED_ITEM, FoodInit.CASSEROLE_SHEPHERDS_PIE_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_PARMIGIANA_BAKED_ITEM, FoodInit.CASSEROLE_PARMIGIANA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_MOUSSAKA_BAKED_ITEM, FoodInit.CASSEROLE_MOUSSAKA_RAW_ITEM, 120));
		list.add(new Smelting(FoodInit.CASSEROLE_STUFFING_BAKED_ITEM, FoodInit.CASSEROLE_STUFFING_RAW_ITEM, 120));
		Smeltings = list.build();

		ImmutableList.Builder<SmeltingBlock> list2 = ImmutableList.builder();
		list2.add(new SmeltingBlock(FoodInit.CONT_LOG_CHARCOAL, () -> TagDC.ItemTag.CONT_LOGS, 120));
		SmeltingBlocks = list2.build();
	}

	public record Smelting(
			Supplier<Item> output,
			Supplier<Item> input, int time) {}

	public record SmeltingBlock(
			Supplier<Block> output,
			Supplier<TagKey<Item>> input, int time) {}

}
