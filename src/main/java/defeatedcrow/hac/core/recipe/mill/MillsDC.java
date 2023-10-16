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

		list1.add(new Crops("goosefoot", () -> FoodInit.CROP_AM_GOOSEFOOT.get(), () -> FoodInit.FOOD_AMARANTH.get(), () -> Items.STICK));
		list1.add(new Crops("grasswort", () -> FoodInit.CROP_AM_GLASSWORT.get(), () -> CoreInit.DUST_TRONA.get(), () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("parsnip", () -> FoodInit.CROP_AP_PARSNIP.get(), () -> FoodInit.FOOD_SYRUP.get(), () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("rapeseed", () -> FoodInit.CROP_BR_RAPESEED.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get()));
		list1.add(new Crops("oat", () -> FoodInit.CROP_CR_OAT.get(), () -> FoodInit.FOOD_OAT.get(), () -> FoodInit.FEED_STRAW.get()));
		list1.add(new Crops("ray", () -> FoodInit.CROP_CR_RYE.get(), () -> FoodInit.FOOD_RYE.get(), () -> FoodInit.FEED_STRAW.get()));
		list1.add(new Crops("barley", () -> FoodInit.CROP_CR_BARLEY.get(), () -> FoodInit.FOOD_BARLEY.get(), () -> FoodInit.FEED_STRAW.get()));
		list1.add(new Crops("turmeric", () -> FoodInit.CROP_GN_TURMERIC.get(), () -> Items.YELLOW_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("crocus", () -> FoodInit.CROP_IR_CROCUS.get(), () -> Items.PURPLE_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("saffron", () -> FoodInit.CROP_IR_SAFFRON.get(), () -> Items.YELLOW_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("buckwheat", () -> FoodInit.CROP_KN_BUCKWHEAT.get(), () -> FoodInit.FOOD_BUCKWHEAT.get(), () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("indigo", () -> FoodInit.CROP_KN_INDIGO.get(), () -> Items.BLUE_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("cotton", () -> FoodInit.CROP_ML_COTTON.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get()));
		list1.add(new Crops("blue_mallow", () -> FoodInit.CROP_ML_BLUE.get(), () -> Items.MAGENTA_DYE, () -> FoodInit.FIBER_PLANT.get()));
		list1.add(new Crops("bindweed", () -> FoodInit.CROP_MO_BINDWEED.get(), () -> Items.PINK_DYE, () -> FoodInit.VINE.get()));
		list1.add(new Crops("spiranthes", () -> FoodInit.CROP_OR_SPIRANTHES.get(), () -> Items.PINK_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("cymbidium", () -> FoodInit.CROP_OR_CYMBIDIUM.get(), () -> Items.LIME_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("rogeria", () -> FoodInit.CROP_PD_ROGERIA.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("sesami", () -> FoodInit.CROP_PD_SESAMI.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_DEFATTED_SOY.get()));
		list1.add(new Crops("soy", () -> FoodInit.CROP_PE_SOY.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_DEFATTED_SOY.get()));
		list1.add(new Crops("anemone", () -> FoodInit.CROP_RA_ANEMONE.get(), () -> Items.WHITE_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("delphinium", () -> FoodInit.CROP_RA_DELPHINIUM.get(), () -> Items.LIGHT_BLUE_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("clematis", () -> FoodInit.CROP_RA_CLEMATIS.get(), () -> Items.PINK_DYE, () -> FoodInit.VINE.get()));
		list1.add(new Crops("reed", () -> FoodInit.CROP_RE_COMMON.get(), () -> FoodInit.FEED_STRAW.get(), () -> FoodInit.FIBER_PLANT.get()));
		list1.add(new Crops("sorghum", () -> FoodInit.CROP_RE_SORGHUM.get(), () -> FoodInit.FOOD_SORGHUM.get(), () -> FoodInit.SORGHUM_STICK.get()));
		list1.add(new Crops("corn", () -> FoodInit.CROP_RE_CORN.get(), () -> FoodInit.FOOD_MASA.get(), () -> FoodInit.FIBER_PLANT.get()));
		list1.add(new Crops("zizania", () -> FoodInit.CROP_RI_ZIZANIA.get(), () -> FoodInit.FOOD_ZIZANIA.get(), () -> FoodInit.FEED_STRAW.get()));
		list1.add(new Crops("rice", () -> FoodInit.CROP_RI_SHORT.get(), () -> FoodInit.FOOD_RICE.get(), () -> FoodInit.FEED_STRAW.get()));
		list1.add(new Crops("aroma_rice", () -> FoodInit.CROP_RI_AROMA.get(), () -> FoodInit.FOOD_AROMA_RICE.get(), () -> FoodInit.FEED_STRAW.get()));
		list1.add(new Crops("avocado", () -> FoodInit.CROP_CN_AVOCADO.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get()));
		list1.add(new Crops("camellia", () -> FoodInit.CROP_CM_OIL.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get()));
		list1.add(new Crops("heath", () -> FoodInit.CROP_ER_HEATH.get(), () -> Items.LIGHT_GRAY_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("rhododendron", () -> FoodInit.CROP_ER_RHODODENDRON.get(), () -> Items.MAGENTA_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("blueberry", () -> FoodInit.CROP_ER_BLUEBERRY.get(), () -> Items.PURPLE_DYE, () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("coconut", () -> FoodInit.CROP_PL_COCONUT.get(), () -> FoodInit.FOOD_COCONUT_MILK.get(), () -> FoodInit.FIBER_PLANT.get()));
		list1.add(new Crops("date", () -> FoodInit.CROP_PL_DATE.get(), () -> FoodInit.FOOD_SYRUP.get(), () -> CoreInit.DUST_PLANT.get()));
		list1.add(new Crops("oil_palm", () -> FoodInit.CROP_PL_OIL.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get()));

		list2.add(new Miscs("ore_iron", 1, () -> Tags.Items.ORES_IRON, () -> Items.RAW_IRON, () -> Items.RAW_IRON));
		list2.add(new Miscs("ore_copper", 1, () -> Tags.Items.ORES_COPPER, () -> Items.RAW_COPPER, () -> Items.RAW_COPPER));
		list2.add(new Miscs("ore_gold", 1, () -> Tags.Items.ORES_GOLD, () -> Items.RAW_GOLD, () -> Items.RAW_GOLD));
		list2.add(new Miscs("ore_redstone", 6, () -> Tags.Items.ORES_REDSTONE, () -> Items.REDSTONE, () -> CoreInit.OREDUST_GREEN3.get()));
		list2.add(new Miscs("cobblestone", 1, () -> Tags.Items.COBBLESTONE, () -> Items.GRAVEL, () -> Items.FLINT));
		list2.add(new Miscs("gravel", 1, () -> Tags.Items.GRAVEL, () -> Items.SAND, () -> CoreInit.DUST_CRYSTAL.get()));
		list2.add(new Miscs("magma", 1, () -> TagDC.ItemTag.MAGMA, () -> Items.BLAZE_POWDER, () -> Items.BLAZE_POWDER));
		list2.add(new Miscs("bone", 5, () -> Tags.Items.BONES, () -> Items.BONE_MEAL, () -> Items.BONE_MEAL));
		list2.add(new Miscs("sugar_cane", 3, () -> TagDC.ItemTag.CROP_SUGAR, () -> Items.SUGAR, () -> FoodInit.FOOD_SYRUP.get()));

		list2.add(new Miscs("gem_salt", 2, () -> TagDC.ItemTag.GEM_SALT, () -> CoreInit.DUST_SALT.get(), () -> CoreInit.DUST_SALT.get()));
		list2.add(new Miscs("gem_niter", 2, () -> TagDC.ItemTag.GEM_NITER, () -> CoreInit.DUST_NITER.get(), () -> CoreInit.DUST_NITER.get()));
		list2.add(new Miscs("gem_sulfur", 2, () -> TagDC.ItemTag.GEM_SULFUR, () -> CoreInit.DUST_SULFUR.get(), () -> CoreInit.DUST_SULFUR.get()));
		list2.add(new Miscs("ore_trona", 3, () -> TagDC.ItemTag.ORES_NATRON, () -> CoreInit.DUST_TRONA.get(), () -> CoreInit.DUST_SALT.get()));
		list2.add(new Miscs("ore_lime", 3, () -> TagDC.ItemTag.ORES_LIME, () -> CoreInit.DUST_LIME.get(), () -> CoreInit.DUST_LIME.get()));
		list2.add(new Miscs("gem_agates", 1, () -> TagDC.ItemTag.GEM_AGATES, () -> CoreInit.DUST_CRYSTAL.get(), () -> CoreInit.DUST_CRYSTAL.get()));
		list2.add(new Miscs("gem_coal", 1, () -> TagDC.ItemTag.GEM_COAL, () -> CoreInit.DUST_COAL.get(), () -> CoreInit.DUST_COAL.get()));
		list2.add(new Miscs("dripstones", 1, () -> TagDC.ItemTag.DRIPSTONES, () -> CoreInit.DUST_LIME.get(), () -> CoreInit.DUST_LIME.get()));
		list2.add(new Miscs("planks", 1, () -> ItemTags.PLANKS, () -> CoreInit.DUST_WOOD.get(), () -> CoreInit.DUST_WOOD.get()));
		list2.add(new Miscs("weeds", 1, () -> TagDC.ItemTag.WEED, () -> CoreInit.DUST_PLANT.get(), () -> CoreInit.DUST_PLANT.get()));

		list2.add(new Miscs("fish_powder", 1, () -> TagDC.ItemTag.RAW_ALL_FISH, () -> FoodInit.FOOD_FISH_POWDER.get(), () -> FoodInit.FOOD_FISH_POWDER.get()));

		list3.add(new Sieve("ore_salt", 1, () -> TagDC.ItemTag.ORES_SALT, () -> CoreInit.GEM_SALT.get(), () -> CoreInit.GEM_SALT.get()));
		list3.add(new Sieve("ore_niter", 1, () -> TagDC.ItemTag.ORES_NITER, () -> CoreInit.GEM_NITER.get(), () -> CoreInit.GEM_NITER.get()));
		list3.add(new Sieve("ore_sulfur", 1, () -> TagDC.ItemTag.ORES_SULFUR, () -> CoreInit.GEM_SULFUR.get(), () -> CoreInit.GEM_SULFUR.get()));

		list3.add(new Sieve("gravel", 1, () -> Tags.Items.GRAVEL, () -> Items.FLINT, () -> CoreInit.GEM_CHALCEDONY.get()));
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
