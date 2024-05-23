package defeatedcrow.hac.core.recipe.mill;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class MillsDC {

	public static MillsDC INSTANCE = new MillsDC() {};

	public final List<Crops> cropRecipe;
	public final List<Miscs> miscRecipe;
	public final List<Sieve> sieveRecipe;

	private MillsDC() {
		ImmutableList.Builder<Crops> list1 = ImmutableList.builder();
		ImmutableList.Builder<Miscs> list2 = ImmutableList.builder();
		ImmutableList.Builder<Sieve> list3 = ImmutableList.builder();

		list1.add(new Crops("goosefoot", FoodInit.CROP_AM_GOOSEFOOT, FoodInit.FOOD_AMARANTH, () -> Items.STICK));
		list1.add(new Crops("grasswort", FoodInit.CROP_AM_GLASSWORT, CoreInit.DUST_TRONA, CoreInit.DUST_PLANT));
		list1.add(new Crops("parsnip", FoodInit.CROP_AP_PARSNIP, FoodInit.FOOD_SYRUP, CoreInit.DUST_PLANT));
		list1.add(new Crops("rapeseed", FoodInit.CROP_BR_RAPESEED, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));
		list1.add(new Crops("oat", FoodInit.CROP_CR_OAT, FoodInit.FOOD_OAT, FoodInit.FEED_STRAW));
		list1.add(new Crops("ray", FoodInit.CROP_CR_RYE, FoodInit.FOOD_RYE, FoodInit.FEED_STRAW));
		list1.add(new Crops("barley", FoodInit.CROP_CR_BARLEY, FoodInit.FOOD_BARLEY, FoodInit.FEED_STRAW));
		list1.add(new Crops("turmeric", FoodInit.CROP_GN_TURMERIC, () -> Items.YELLOW_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("crocus", FoodInit.CROP_IR_CROCUS, () -> Items.PURPLE_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("saffron", FoodInit.CROP_IR_SAFFRON, () -> Items.YELLOW_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("buckwheat", FoodInit.CROP_KN_BUCKWHEAT, FoodInit.FOOD_BUCKWHEAT, CoreInit.DUST_PLANT));
		list1.add(new Crops("indigo", FoodInit.CROP_KN_INDIGO, () -> Items.BLUE_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("cotton", FoodInit.CROP_ML_COTTON, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));
		list1.add(new Crops("blue_mallow", FoodInit.CROP_ML_BLUE, () -> Items.MAGENTA_DYE, FoodInit.FIBER_PLANT));
		list1.add(new Crops("bindweed", FoodInit.CROP_MO_BINDWEED, () -> Items.PINK_DYE, FoodInit.VINE));
		list1.add(new Crops("spiranthes", FoodInit.CROP_OR_SPIRANTHES, () -> Items.PINK_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("cymbidium", FoodInit.CROP_OR_CYMBIDIUM, () -> Items.LIME_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("rogeria", FoodInit.CROP_PD_ROGERIA, FoodInit.FOOD_PLANT_OIL, CoreInit.DUST_PLANT));
		list1.add(new Crops("sesami", FoodInit.CROP_PD_SESAMI, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));
		list1.add(new Crops("soy", FoodInit.CROP_PE_SOY, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_DEFATTED_SOY));
		list1.add(new Crops("anemone", FoodInit.CROP_RA_ANEMONE, () -> Items.WHITE_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("delphinium", FoodInit.CROP_RA_DELPHINIUM, () -> Items.LIGHT_BLUE_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("clematis", FoodInit.CROP_RA_CLEMATIS, () -> Items.PINK_DYE, FoodInit.VINE));
		list1.add(new Crops("reed", FoodInit.CROP_RE_COMMON, FoodInit.FEED_STRAW, FoodInit.FIBER_PLANT));
		list1.add(new Crops("sorghum", FoodInit.CROP_RE_SORGHUM, FoodInit.FOOD_SORGHUM, FoodInit.SORGHUM_STICK));
		list1.add(new Crops("corn", FoodInit.CROP_RE_CORN, FoodInit.FOOD_MASA, FoodInit.FIBER_PLANT));
		list1.add(new Crops("zizania", FoodInit.CROP_RI_ZIZANIA, FoodInit.FOOD_ZIZANIA, FoodInit.FEED_STRAW));
		list1.add(new Crops("rice", FoodInit.CROP_RI_SHORT, FoodInit.FOOD_RICE, FoodInit.FEED_STRAW));
		list1.add(new Crops("aroma_rice", FoodInit.CROP_RI_AROMA, FoodInit.FOOD_AROMA_RICE, FoodInit.FEED_STRAW));
		list1.add(new Crops("avocado", FoodInit.CROP_CN_AVOCADO, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));
		list1.add(new Crops("olive", FoodInit.CROP_OL_OLIVE, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));
		list1.add(new Crops("camellia", FoodInit.CROP_CM_OIL, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));
		list1.add(new Crops("heath", FoodInit.CROP_ER_HEATH, () -> Items.LIGHT_GRAY_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("rhododendron", FoodInit.CROP_ER_RHODODENDRON, () -> Items.MAGENTA_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("blueberry", FoodInit.CROP_ER_BLUEBERRY, () -> Items.PURPLE_DYE, CoreInit.DUST_PLANT));
		list1.add(new Crops("coconut", FoodInit.CROP_PL_COCONUT, FoodInit.FOOD_COCONUT_MILK, FoodInit.FIBER_PLANT));
		list1.add(new Crops("almond", FoodInit.CROP_CH_ALMOND, FoodInit.FOOD_ALMOND_MILK, CoreInit.DUST_PLANT));
		list1.add(new Crops("date", FoodInit.CROP_PL_DATE, FoodInit.FOOD_SYRUP, CoreInit.DUST_PLANT));
		list1.add(new Crops("oil_palm", FoodInit.CROP_PL_OIL, FoodInit.FOOD_PLANT_OIL, FoodInit.FOOD_PRESS_CAKE));

		list2.add(new Miscs("ore_iron", 1, () -> Tags.Items.ORES_IRON, () -> Items.RAW_IRON, () -> Items.RAW_IRON));
		list2.add(new Miscs("ore_copper", 1, () -> Tags.Items.ORES_COPPER, () -> Items.RAW_COPPER, () -> Items.RAW_COPPER));
		list2.add(new Miscs("ore_gold", 1, () -> Tags.Items.ORES_GOLD, () -> Items.RAW_GOLD, () -> Items.RAW_GOLD));
		list2.add(new Miscs("ore_redstone", 6, () -> Tags.Items.ORES_REDSTONE, () -> Items.REDSTONE, CoreInit.OREDUST_GREEN3));
		list2.add(new Miscs("cobblestone", 1, () -> Tags.Items.COBBLESTONE, () -> Items.GRAVEL, () -> Items.FLINT));
		list2.add(new Miscs("gravel", 1, () -> Tags.Items.GRAVEL, () -> Items.FLINT, () -> Items.SAND));
		list2.add(new Miscs("magma", 1, () -> TagDC.ItemTag.MAGMA, () -> Items.BLAZE_POWDER, () -> Items.BLAZE_POWDER));
		list2.add(new Miscs("bone", 5, () -> Tags.Items.BONES, () -> Items.BONE_MEAL, () -> Items.BONE_MEAL));
		list2.add(new Miscs("sugar_cane", 3, () -> TagDC.ItemTag.CROP_SUGAR, () -> Items.SUGAR, FoodInit.FOOD_SYRUP));
		list2.add(new Miscs("sandstone", 1, () -> Tags.Items.SANDSTONE, () -> Items.SAND, CoreInit.DUST_LIME));

		list2.add(new Miscs("gem_salt", 2, () -> TagDC.ItemTag.GEM_SALT, CoreInit.DUST_SALT, CoreInit.DUST_SALT));
		list2.add(new Miscs("gem_niter", 2, () -> TagDC.ItemTag.GEM_NITER, CoreInit.DUST_NITER, CoreInit.DUST_NITER));
		list2.add(new Miscs("gem_sulfur", 2, () -> TagDC.ItemTag.GEM_SULFUR, CoreInit.DUST_SULFUR, CoreInit.DUST_SULFUR));
		list2.add(new Miscs("ore_trona", 3, () -> TagDC.ItemTag.ORES_NATRON, CoreInit.DUST_TRONA, CoreInit.DUST_SALT));
		list2.add(new Miscs("ore_lime", 3, () -> TagDC.ItemTag.ORES_LIME, CoreInit.DUST_LIME, CoreInit.DUST_LIME));
		list2.add(new Miscs("ore_travertine", 1, () -> TagDC.ItemTag.ORES_TRAVERTINE, CoreInit.DUST_LIME, CoreInit.DUST_SULFUR));
		list2.add(new Miscs("gem_agates", 1, () -> TagDC.ItemTag.GEM_AGATES, CoreInit.DUST_CRYSTAL, CoreInit.DUST_CRYSTAL));
		list2.add(new Miscs("gem_coal", 1, () -> TagDC.ItemTag.GEM_COAL, CoreInit.DUST_COAL, CoreInit.DUST_COAL));
		list2.add(new Miscs("dripstones", 1, () -> TagDC.ItemTag.DRIPSTONES, CoreInit.DUST_LIME, CoreInit.DUST_LIME));
		list2.add(new Miscs("planks", 1, () -> ItemTags.PLANKS, CoreInit.DUST_WOOD, CoreInit.DUST_WOOD));
		list2.add(new Miscs("weeds", 1, () -> TagDC.ItemTag.WEED, CoreInit.DUST_PLANT, CoreInit.DUST_PLANT));

		list2.add(new Miscs("ore_salt", 1, () -> TagDC.ItemTag.ORES_SALT, CoreInit.GEM_SALT, CoreInit.GEM_SALT));
		list2.add(new Miscs("ore_niter", 1, () -> TagDC.ItemTag.ORES_NITER, CoreInit.GEM_NITER, CoreInit.GEM_NITER));
		list2.add(new Miscs("ore_sulfur", 1, () -> TagDC.ItemTag.ORES_SULFUR, CoreInit.GEM_SULFUR, CoreInit.GEM_SULFUR));

		list2.add(new Miscs("fish_powder", 1, () -> TagDC.ItemTag.RAW_ALL_FISH, FoodInit.FOOD_FISH_POWDER, FoodInit.FOOD_FISH_POWDER));

		list3.add(new Sieve("ore_coal", 1, () -> Tags.Items.ORES_COAL, () -> Items.COAL, () -> Items.COAL));
		list3.add(new Sieve("ore_lapis", 8, () -> Tags.Items.ORES_LAPIS, () -> Items.LAPIS_LAZULI, () -> Items.LAPIS_LAZULI));
		list3.add(new Sieve("ore_emerald", 1, () -> Tags.Items.ORES_EMERALD, () -> Items.EMERALD, () -> Items.EMERALD));
		list3.add(new Sieve("ore_diamond", 1, () -> Tags.Items.ORES_DIAMOND, () -> Items.DIAMOND, () -> Items.DIAMOND));
		list3.add(new Sieve("ore_quartz", 1, () -> Tags.Items.ORES_QUARTZ, () -> Items.QUARTZ, () -> Items.QUARTZ));

		cropRecipe = list1.build();
		miscRecipe = list2.build();
		sieveRecipe = list3.build();
	}

	public record Crops(
			String name,
			Supplier<Item> input,
			Supplier<Item> outputPri,
			Supplier<Item> outputSec) {}

	public record Miscs(
			String name,
			int outputCount,
			Supplier<TagKey<Item>> input,
			Supplier<Item> outputPri,
			Supplier<Item> outputSec) {}

	public record Sieve(
			String name,
			int outputCount,
			Supplier<TagKey<Item>> input,
			Supplier<Item> outputPri,
			Supplier<Item> outputSec) {}

}
