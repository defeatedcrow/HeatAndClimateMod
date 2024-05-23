package defeatedcrow.hac.food.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;

public class PlantRecipes {
	public static PlantRecipes INSTANCE = new PlantRecipes() {};

	public final List<Seeding> seedings;
	public final List<Wood> woods;

	private PlantRecipes() {
		ImmutableList.Builder<Seeding> list1 = ImmutableList.builder();
		ImmutableList.Builder<Wood> list2 = ImmutableList.builder();

		list1.add(new Seeding(FoodInit.BLOCK_AL_WILD, FoodInit.CROP_AL_WILD, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AL_ONION, FoodInit.CROP_AL_ONION, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AL_GARLIC, FoodInit.CROP_AL_GARLIC, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AM_GOOSEFOOT, FoodInit.CROP_AM_GOOSEFOOT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AM_GLASSWORT, FoodInit.CROP_AM_GLASSWORT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AM_SPINACH, FoodInit.CROP_AM_SPINACH, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AP_CELERY, FoodInit.CROP_AP_CELERY, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AP_FENNEL, FoodInit.CROP_AP_FENNEL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AP_PARSNIP, FoodInit.CROP_AP_PARSNIP, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AP_CORIANDER, FoodInit.CROP_AP_CORIANDER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AS_ARTEMISIA, FoodInit.CROP_AS_ARTEMISIA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AS_LETTUCE, FoodInit.CROP_AS_LETTUCE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AS_PYRETHRUM, FoodInit.CROP_AS_PYRETHRUM, 1));
		list1.add(new Seeding(FoodInit.BLOCK_AS_FLOWER, FoodInit.CROP_AS_FLOWER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_BR_RAPESEED, FoodInit.CROP_BR_RAPESEED, 1));
		list1.add(new Seeding(FoodInit.BLOCK_BR_GREEN, FoodInit.CROP_BR_GREEN, 1));
		list1.add(new Seeding(FoodInit.BLOCK_BR_CABBAGE, FoodInit.CROP_BR_CABBAGE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_BR_RADISH, FoodInit.CROP_BR_RADISH, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CA_CHILI, FoodInit.CROP_CA_CHILI, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CA_PAPRIKA, FoodInit.CROP_CA_PAPRIKA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CA_BELL, FoodInit.CROP_CA_BELL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CR_OAT, FoodInit.CROP_CR_OAT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CR_RYE, FoodInit.CROP_CR_RYE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CR_BARLEY, FoodInit.CROP_CR_BARLEY, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GN_COMMON, FoodInit.CROP_GN_COMMON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GN_CARDAMOM, FoodInit.CROP_GN_CARDAMOM, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GN_TURMERIC, FoodInit.CROP_GN_TURMERIC, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GO_CALABASH, FoodInit.CROP_GO_CALABASH, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GO_CUCUMBER, FoodInit.CROP_GO_CUCUMBER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GO_CANTALOUP, FoodInit.CROP_GO_CANTALOUP, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GR_WILD, FoodInit.CROP_GR_WILD, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GR_COMMON, FoodInit.CROP_GR_COMMON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_GR_WHITE, FoodInit.CROP_GR_WHITE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_HB_MINT, FoodInit.CROP_HB_MINT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_HB_BASIL, FoodInit.CROP_HB_BASIL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_HB_PERILLA, FoodInit.CROP_HB_PERILLA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_HB_LAVENDER, FoodInit.CROP_HB_LAVENDER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_IR_CROCUS, FoodInit.CROP_IR_CROCUS, 1));
		list1.add(new Seeding(FoodInit.BLOCK_IR_SAFFRON, FoodInit.CROP_IR_SAFFRON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_IR_IRIS, FoodInit.CROP_IR_IRIS, 1));
		list1.add(new Seeding(FoodInit.BLOCK_KN_SORREL, FoodInit.CROP_KN_SORREL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_KN_BUCKWHEAT, FoodInit.CROP_KN_BUCKWHEAT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_KN_INDIGO, FoodInit.CROP_KN_INDIGO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ML_JUTE, FoodInit.CROP_ML_JUTE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ML_COTTON, FoodInit.CROP_ML_COTTON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ML_BLUE, FoodInit.CROP_ML_BLUE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ML_TROPICAL, FoodInit.CROP_ML_TROPICAL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MO_BINDWEED, FoodInit.CROP_MO_BINDWEED, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MO_WATER, FoodInit.CROP_MO_WATER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MO_POTATO, FoodInit.CROP_MO_POTATO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MO_FLOWER, FoodInit.CROP_MO_FLOWER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OR_SPIRANTHES, FoodInit.CROP_OR_SPIRANTHES, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OR_CYMBIDIUM, FoodInit.CROP_OR_CYMBIDIUM, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OR_VANILLA, FoodInit.CROP_OR_VANILLA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OR_CATTLEYA, FoodInit.CROP_OR_CATTLEYA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PD_ROGERIA, FoodInit.CROP_PD_ROGERIA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PD_SESAMI, FoodInit.CROP_PD_SESAMI, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PD_DEVILSCLAW, FoodInit.CROP_PD_DEVILSCLAW, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PE_GREEN, FoodInit.CROP_PE_GREEN, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PE_GARBANZO, FoodInit.CROP_PE_GARBANZO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PE_SOY, FoodInit.CROP_PE_SOY, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PE_ADZUKI, FoodInit.CROP_PE_ADZUKI, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RA_ANEMONE, FoodInit.CROP_RA_ANEMONE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RA_DELPHINIUM, FoodInit.CROP_RA_DELPHINIUM, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RA_CLEMATIS, FoodInit.CROP_RA_CLEMATIS, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RA_MONKSHOOD, FoodInit.CROP_RA_MONKSHOOD, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RE_COMMON, FoodInit.CROP_RE_COMMON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RE_SORGHUM, FoodInit.CROP_RE_SORGHUM, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RE_CORN, FoodInit.CROP_RE_CORN, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RI_ZIZANIA, FoodInit.CROP_RI_ZIZANIA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RI_SHORT, FoodInit.CROP_RI_SHORT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RI_AROMA, FoodInit.CROP_RI_AROMA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SL_NIGHTSHADE, FoodInit.CROP_SL_NIGHTSHADE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SL_EGGPLANT, FoodInit.CROP_SL_EGGPLANT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SL_TOMATO, FoodInit.CROP_SL_TOMATO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SL_LANTERN, FoodInit.CROP_SL_LANTERN, 1));

		list1.add(new Seeding(FoodInit.BLOCK_BH_COMMON, FoodInit.CROP_BH_COMMON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_BH_WALNUT, FoodInit.CROP_BH_WALNUT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_BH_SWEET, FoodInit.CROP_BH_SWEET, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CM_OIL, FoodInit.CROP_CM_OIL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CM_SCHIMA, FoodInit.CROP_CM_SCHIMA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CM_TEA, FoodInit.CROP_CM_TEA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CH_WILD, FoodInit.CROP_CH_WILD, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CH_PLUM, FoodInit.CROP_CH_PLUM, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CH_PEACH, FoodInit.CROP_CH_PEACH, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CH_ALMOND, FoodInit.CROP_CH_ALMOND, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CN_AVOCADO, FoodInit.CROP_CN_AVOCADO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CT_POMELO, FoodInit.CROP_CT_POMELO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CT_MANDARIN, FoodInit.CROP_CT_MANDARIN, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CT_LEMON, FoodInit.CROP_CT_LEMON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_CT_PEPPER, FoodInit.CROP_CT_PEPPER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ER_HEATH, FoodInit.CROP_ER_HEATH, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ER_RHODODENDRON, FoodInit.CROP_ER_RHODODENDRON, 1));
		list1.add(new Seeding(FoodInit.BLOCK_ER_BLUEBERRY, FoodInit.CROP_ER_BLUEBERRY, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MR_MULBERRY, FoodInit.CROP_MR_MULBERRY, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MR_PAPER, FoodInit.CROP_MR_PAPER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MR_RUBBER, FoodInit.CROP_MR_RUBBER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MY_GUAVA, FoodInit.CROP_MY_GUAVA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_MY_CLOVE, FoodInit.CROP_MY_CLOVE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OL_ASH, FoodInit.CROP_OL_ASH, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OL_OLIVE, FoodInit.CROP_OL_OLIVE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_OL_OSMANTHUS, FoodInit.CROP_OL_OSMANTHUS, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PL_COCONUT, FoodInit.CROP_PL_COCONUT, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PL_DATE, FoodInit.CROP_PL_DATE, 1));
		list1.add(new Seeding(FoodInit.BLOCK_PL_OIL, FoodInit.CROP_PL_OIL, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RO_RUGOSA, FoodInit.CROP_RO_RUGOSA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RO_RASPBERRY, FoodInit.CROP_RO_RASPBERRY, 1));
		list1.add(new Seeding(FoodInit.BLOCK_RO_DAMASCHENA, FoodInit.CROP_RO_DAMASCHENA, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SU_LACQUER, FoodInit.CROP_SU_LACQUER, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SU_MANGO, FoodInit.CROP_SU_MANGO, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SU_CASHEW, FoodInit.CROP_SU_CASHEW, 1));
		list1.add(new Seeding(FoodInit.BLOCK_SU_PISTACHIO, FoodInit.CROP_SU_PISTACHIO, 1));
		seedings = list1.build();

		list2.add(
				new Wood("beech_common", FoodInit.LOG_BH_COMMON, FoodInit.PLANK_BH_COMMON, BuildInit.STAIRS_BEECH, BuildInit.SLAB_BEECH, BuildInit.FENCE_BEECH, BuildInit.DOOR_BEECH, BuildInit.TRAPDOOR_BEECH));
		list2.add(
				new Wood("beech_walnut", FoodInit.LOG_BH_WALNUT, FoodInit.PLANK_BH_WALNUT, BuildInit.STARIS_WALNUT, BuildInit.SLAB_WALNUT, BuildInit.FENCE_WALNUT, BuildInit.DOOR_WALNUT, BuildInit.TRAPDOOR_WALNUT));
		list2.add(
				new Wood("beech_sweet", FoodInit.LOG_BH_SWEET, FoodInit.PLANK_BH_SWEET, BuildInit.STAIRS_SWEET, BuildInit.SLAB_SWEET, BuildInit.FENCE_SWEET, BuildInit.DOOR_SWEET, BuildInit.TRAPDOOR_SWEET));
		list2.add(
				new Wood("cherry_wild", FoodInit.LOG_CH_WILD, FoodInit.PLANK_CH_WILD, BuildInit.STAIRS_CHERRY, BuildInit.SLAB_CHERRY, BuildInit.FENCE_CHERRY, BuildInit.DOOR_CHERRY, BuildInit.TRAPDOOR_CHERRY));
		list2.add(
				new Wood("cinnamon_camphor", FoodInit.LOG_CN_CAMPHOR, FoodInit.PLANK_CN_CAMPHOR, BuildInit.STAIRS_CAMPHOR, BuildInit.SLAB_CAMPHOR, BuildInit.FENCE_CAMPHOR, BuildInit.DOOR_CAMPHOR, BuildInit.TRAPDOOR_CAMPHOR));
		list2.add(
				new Wood("citrus_pomelo", FoodInit.LOG_CT_POMELO, FoodInit.PLANK_CT_POMELO, BuildInit.STAIRS_CITRUS, BuildInit.SLAB_CITRUS, BuildInit.FENCE_CITRUS, BuildInit.DOOR_CITRUS, BuildInit.TRAPDOOR_CITRUS));
		list2.add(
				new Wood("morus_mulberry", FoodInit.LOG_MR_MULBERRY, FoodInit.PLANK_MR_MULBERRY, BuildInit.STAIRS_MORUS, BuildInit.SLAB_MORUS, BuildInit.FENCE_MORUS, BuildInit.DOOR_MORUS, BuildInit.TRAPDOOR_MORUS));
		list2.add(
				new Wood("myrtle_eucalyptus", FoodInit.LOG_MY_EUCALYPTUS, FoodInit.PLANK_MY_EUCALYPTUS, BuildInit.STAIRS_EUCALYPTUS, BuildInit.SLAB_EUCALYPTUS, BuildInit.FENCE_EUCALYPTUS, BuildInit.DOOR_EUCALYPTUS, BuildInit.TRAPDOOR_EUCALYPTUS));
		list2.add(new Wood("olive_ash", FoodInit.LOG_OL_ASH, FoodInit.PLANK_OL_ASH, BuildInit.STAIRS_ASH, BuildInit.SLAB_ASH, BuildInit.FENCE_ASH, BuildInit.DOOR_ASH, BuildInit.TRAPDOOR_ASH));
		list2.add(new Wood("palm_coconut", FoodInit.LOG_PL_COCONUT, FoodInit.PLANK_PL_COCONUT, BuildInit.STAIRS_PALM, BuildInit.SLAB_PALM, BuildInit.FENCE_PALM, () -> null, () -> null));
		list2.add(
				new Wood("sumac_lacquer", FoodInit.LOG_SU_LACQUER, FoodInit.PLANK_SU_LACQUER, BuildInit.STAIRS_SUMAC, BuildInit.SLAB_SUMAC, BuildInit.FENCE_SUMAC, BuildInit.DOOR_SUMAC, BuildInit.TRAPDOOR_SUMAC));
		list2.add(
				new Wood("lacquerware", () -> null, FoodInit.PLANK_LACQUERWARE, BuildInit.STAIRS_LACQUER, BuildInit.SLAB_LACQUER, BuildInit.FENCE_LACQUER, BuildInit.DOOR_LACQUER, BuildInit.TRAPDOOR_LACQUER));
		list2.add(new Wood("sorghum", () -> null, FoodInit.PLANK_RE_SORGHUM, BuildInit.STAIRS_SORGHUM, BuildInit.SLAB_SORGHUM, BuildInit.FENCE_SORGHUM, () -> null, () -> null));
		woods = list2.build();
	}

	public record Seeding(
			Supplier<? extends ItemLike> output,
			Supplier<Item> input,
			int count) {}

	public record Wood(
			String name,
			Supplier<Block> logBlock,
			Supplier<Block> plankBlock,
			Supplier<Block> stairsBlock,
			Supplier<Block> slabBlock,
			Supplier<Block> fenceBlock,
			Supplier<Block> doorBlock,
			Supplier<Block> trapdoorBlock) {}

	public static void addCompostables() {
		ComposterBlock.COMPOSTABLES.put(CoreInit.DUST_ASH.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_BR_RAPESEED.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_BR_GREEN.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_BR_CABBAGE.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_BR_RADISH.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_CR_OAT.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_CR_RYE.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_CR_BARLEY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_PE_GREEN.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_PE_GARBANZO.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_PE_SOY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_PE_ADZUKI.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_RE_SORGHUM.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.CROP_RE_CORN.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FEED_STRAW.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FEED_HAY.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FEED_COMPOUND.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FOOD_LEAF_MOLD.get(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FOOD_PRESS_CAKE.get(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FOOD_FISH_POWDER.get(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(FoodInit.FERTILIZER_MIXED.get(), 1.0F);
	}

}
