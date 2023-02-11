package defeatedcrow.hac.core.recipe.mill;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class MillsDC {

	public static final Crops GOOSEFOOT = new Crops("goosefoot", () -> FoodInit.CROP_AM_GOOSEFOOT.get(), () -> FoodInit.FOOD_SORGHUM.get(), () -> Items.STICK, false);
	public static final Crops GLASSWEAT = new Crops("grasswort", () -> FoodInit.CROP_AM_GLASSWORT.get(), () -> CoreInit.DUST_TRONA.get(), () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops PARSNIP = new Crops("parsnip", () -> FoodInit.CROP_AP_PARSNIP.get(), () -> Items.SUGAR, () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops RAPESEED = new Crops("rapeseed", () -> FoodInit.CROP_BR_RAPESEED.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get(), true);
	public static final Crops OAT = new Crops("oat", () -> FoodInit.CROP_CR_OAT.get(), () -> FoodInit.FOOD_OAT.get(), () -> FoodInit.FEED_STRAW.get(), false);
	public static final Crops RAY = new Crops("ray", () -> FoodInit.CROP_CR_RYE.get(), () -> FoodInit.FOOD_RYE.get(), () -> FoodInit.FEED_STRAW.get(), false);
	public static final Crops BARLEY = new Crops("barley", () -> FoodInit.CROP_CR_BARLEY.get(), () -> FoodInit.FOOD_BARLEY.get(), () -> FoodInit.FEED_STRAW.get(), false);
	public static final Crops TURMERIC = new Crops("turmeric", () -> FoodInit.CROP_GN_TURMERIC.get(), () -> Items.YELLOW_DYE, () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops BUCKWHEAT = new Crops("buckwheat", () -> FoodInit.CROP_KN_BUCKWHEAT.get(), () -> FoodInit.FOOD_BUCKWHEAT.get(), () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops INDIGO = new Crops("indigo", () -> FoodInit.CROP_KN_INDIGO.get(), () -> Items.BLUE_DYE, () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops COTTON = new Crops("cotton", () -> FoodInit.CROP_ML_COTTON.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_PRESS_CAKE.get(), true);
	public static final Crops BLUE_MALLOW = new Crops("blue_mallow", () -> FoodInit.CROP_ML_BLUE.get(), () -> Items.MAGENTA_DYE, () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops BINDWEED = new Crops("bindweed", () -> FoodInit.CROP_MO_BINDWEED.get(), () -> Items.PINK_DYE, () -> FoodInit.FOOD_VINE.get(), false);
	public static final Crops SOY = new Crops("soy", () -> FoodInit.CROP_PE_SOY.get(), () -> FoodInit.FOOD_PLANT_OIL.get(), () -> FoodInit.FOOD_DEFATTED_SOY.get(), true);
	public static final Crops REED = new Crops("reed", () -> FoodInit.CROP_RE_COMMON.get(), () -> FoodInit.FEED_STRAW.get(), () -> Items.STICK, false);
	public static final Crops SORGHUM = new Crops("sorghum", () -> FoodInit.CROP_RE_SORGHUM.get(), () -> FoodInit.FOOD_SORGHUM.get(), () -> Items.STICK, false);
	public static final Crops CORN = new Crops("corn", () -> FoodInit.CROP_RE_CORN.get(), () -> FoodInit.FOOD_MASA.get(), () -> CoreInit.DUST_PLANT.get(), false);
	public static final Crops ZIZANIA = new Crops("zizania", () -> FoodInit.CROP_RI_ZIZANIA.get(), () -> FoodInit.FOOD_ZIZANIA.get(), () -> FoodInit.FEED_STRAW.get(), false);
	public static final Crops RICE = new Crops("rice", () -> FoodInit.CROP_RI_SHORT.get(), () -> FoodInit.FOOD_RICE.get(), () -> FoodInit.FEED_STRAW.get(), false);
	public static final Crops AROMA = new Crops("aroma_rice", () -> FoodInit.CROP_RI_AROMA.get(), () -> FoodInit.FOOD_AROMA_RICE.get(), () -> FoodInit.FEED_STRAW.get(), false);

	public static final Crops[] VARIANT = { GOOSEFOOT, GLASSWEAT, PARSNIP, RAPESEED, OAT, RAY, BARLEY, TURMERIC, BUCKWHEAT, INDIGO, COTTON, BLUE_MALLOW, BINDWEED, SOY,
		REED, SORGHUM, CORN, ZIZANIA, RICE, AROMA };

	public record Crops(
			String name,
			Supplier<Item> input,
			Supplier<Item> outputPri,
			Supplier<Item> outputSec,
			boolean needPack) {}

}
