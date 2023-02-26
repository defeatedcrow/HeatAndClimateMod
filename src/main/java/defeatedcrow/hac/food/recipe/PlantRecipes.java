package defeatedcrow.hac.food.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class PlantRecipes {
	public static PlantRecipes INSTANCE = new PlantRecipes() {};

	public final List<Seeding> seedings;
	public final List<Wood> woods;

	private PlantRecipes() {
		ImmutableList.Builder<Seeding> list1 = ImmutableList.builder();
		ImmutableList.Builder<Wood> list2 = ImmutableList.builder();

		list1.add(new Seeding(() -> FoodInit.BLOCK_AL_WILD.get(), () -> FoodInit.CROP_AL_WILD.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AL_ONION.get(), () -> FoodInit.CROP_AL_ONION.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AL_GARLIC.get(), () -> FoodInit.CROP_AL_GARLIC.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AM_GOOSEFOOT.get(), () -> FoodInit.CROP_AM_GOOSEFOOT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AM_GLASSWORT.get(), () -> FoodInit.CROP_AM_GLASSWORT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AM_SPINACH.get(), () -> FoodInit.CROP_AM_SPINACH.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AP_CELERY.get(), () -> FoodInit.CROP_AP_CELERY.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AP_FENNEL.get(), () -> FoodInit.CROP_AP_FENNEL.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_AP_PARSNIP.get(), () -> FoodInit.CROP_AP_PARSNIP.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_BR_RAPESEED.get(), () -> FoodInit.CROP_BR_RAPESEED.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_BR_GREEN.get(), () -> FoodInit.CROP_BR_GREEN.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_BR_CABAGGE.get(), () -> FoodInit.CROP_BR_CABAGGE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_BR_RADISH.get(), () -> FoodInit.CROP_BR_RADISH.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CA_CHILI.get(), () -> FoodInit.CROP_CA_CHILI.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CA_PAPRIKA.get(), () -> FoodInit.CROP_CA_PAPRIKA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CA_BELL.get(), () -> FoodInit.CROP_CA_BELL.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CR_OAT.get(), () -> FoodInit.CROP_CR_OAT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CR_RYE.get(), () -> FoodInit.CROP_CR_RYE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CR_BARLEY.get(), () -> FoodInit.CROP_CR_BARLEY.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_GN_COMMON.get(), () -> FoodInit.CROP_GN_COMMON.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_GN_CARDAMOM.get(), () -> FoodInit.CROP_GN_CARDAMOM.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_GN_TURMERIC.get(), () -> FoodInit.CROP_GN_TURMERIC.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_HB_MINT.get(), () -> FoodInit.CROP_HB_MINT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_HB_BASIL.get(), () -> FoodInit.CROP_HB_BASIL.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_HB_PERILLA.get(), () -> FoodInit.CROP_HB_PERILLA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_HB_LAVENDER.get(), () -> FoodInit.CROP_HB_LAVENDER.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_KN_SORREL.get(), () -> FoodInit.CROP_KN_SORREL.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_KN_BUCKWHEAT.get(), () -> FoodInit.CROP_KN_BUCKWHEAT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_KN_INDIGO.get(), () -> FoodInit.CROP_KN_INDIGO.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_ML_JUTE.get(), () -> FoodInit.CROP_ML_JUTE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_ML_COTTON.get(), () -> FoodInit.CROP_ML_COTTON.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_ML_BLUE.get(), () -> FoodInit.CROP_ML_BLUE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MO_BINDWEED.get(), () -> FoodInit.CROP_MO_BINDWEED.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MO_WATER.get(), () -> FoodInit.CROP_MO_WATER.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MO_POTATO.get(), () -> FoodInit.CROP_MO_POTATO.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MO_FLOWER.get(), () -> FoodInit.CROP_MO_FLOWER.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PD_ROGERIA.get(), () -> FoodInit.CROP_PD_ROGERIA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PD_SESAMI.get(), () -> FoodInit.CROP_PD_SESAMI.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PD_DEVILSCLAW.get(), () -> FoodInit.CROP_PD_DEVILSCLAW.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PE_GREEN.get(), () -> FoodInit.CROP_PE_GREEN.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PE_GARBANZO.get(), () -> FoodInit.CROP_PE_GARBANZO.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PE_SOY.get(), () -> FoodInit.CROP_PE_SOY.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PE_ADZUKI.get(), () -> FoodInit.CROP_PE_ADZUKI.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_RE_COMMON.get(), () -> FoodInit.CROP_RE_COMMON.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_RE_SORGHUM.get(), () -> FoodInit.CROP_RE_SORGHUM.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_RE_CORN.get(), () -> FoodInit.CROP_RE_CORN.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_RI_ZIZANIA.get(), () -> FoodInit.CROP_RI_ZIZANIA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_RI_SHORT.get(), () -> FoodInit.CROP_RI_SHORT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_RI_AROMA.get(), () -> FoodInit.CROP_RI_AROMA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_SL_NIGHTSHADE.get(), () -> FoodInit.CROP_SL_NIGHTSHADE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_SL_EGGPLANT.get(), () -> FoodInit.CROP_SL_EGGPLANT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_SL_TOMATO.get(), () -> FoodInit.CROP_SL_TOMATO.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_SL_LANTERN.get(), () -> FoodInit.CROP_SL_LANTERN.get(), 1));

		list1.add(new Seeding(() -> FoodInit.BLOCK_BH_COMMON.get(), () -> FoodInit.CROP_BH_COMMON.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_BH_WALNUT.get(), () -> FoodInit.CROP_BH_WALNUT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_BH_SWEET.get(), () -> FoodInit.CROP_BH_SWEET.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CM_OIL.get(), () -> FoodInit.CROP_CM_OIL.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CM_SCHIMA.get(), () -> FoodInit.CROP_CM_SCHIMA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CM_TEA.get(), () -> FoodInit.CROP_CM_TEA.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CH_WILD.get(), () -> FoodInit.CROP_CH_WILD.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CH_PLUM.get(), () -> FoodInit.CROP_CH_PLUM.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CH_PEACH.get(), () -> FoodInit.CROP_CH_PEACH.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CN_AVOCADO.get(), () -> FoodInit.CROP_CN_AVOCADO.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CT_POMELO.get(), () -> FoodInit.CROP_CT_POMELO.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CT_MANDARIN.get(), () -> FoodInit.CROP_CT_MANDARIN.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_CT_LEMON.get(), () -> FoodInit.CROP_CT_LEMON.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_ER_HEATH.get(), () -> FoodInit.CROP_ER_HEATH.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_ER_RHODODENDRON.get(), () -> FoodInit.CROP_ER_RHODODENDRON.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_ER_BLUEBERRY.get(), () -> FoodInit.CROP_ER_BLUEBERRY.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MR_MULBERRY.get(), () -> FoodInit.CROP_MR_MULBERRY.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MR_PAPER.get(), () -> FoodInit.CROP_MR_PAPER.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_MR_RUBBER.get(), () -> FoodInit.CROP_MR_RUBBER.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_OL_ASH.get(), () -> FoodInit.CROP_OL_ASH.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_OL_OLIVE.get(), () -> FoodInit.CROP_OL_OLIVE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_OL_OSMANTHUS.get(), () -> FoodInit.CROP_OL_OSMANTHUS.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PL_COCONUT.get(), () -> FoodInit.CROP_PL_COCONUT.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PL_DATE.get(), () -> FoodInit.CROP_PL_DATE.get(), 1));
		list1.add(new Seeding(() -> FoodInit.BLOCK_PL_OIL.get(), () -> FoodInit.CROP_PL_OIL.get(), 1));
		seedings = list1.build();

		list2.add(new Wood("beech_common", () -> FoodInit.LOG_BH_COMMON.get(), () -> FoodInit.PLANK_BH_COMMON.get(), () -> BuildInit.STAIRS_BEECH.get(), () -> BuildInit.SLAB_BEECH
			.get(), () -> BuildInit.FENCE_BEECH.get()));
		list2.add(new Wood("beech_walnut", () -> FoodInit.LOG_BH_WALNUT.get(), () -> FoodInit.PLANK_BH_WALNUT.get(), () -> BuildInit.STARIS_WALNUT.get(), () -> BuildInit.SLAB_WALNUT
			.get(), () -> BuildInit.FENCE_WALNUT.get()));
		list2.add(new Wood("beech_sweet", () -> FoodInit.LOG_BH_SWEET.get(), () -> FoodInit.PLANK_BH_SWEET.get(), () -> BuildInit.STAIRS_SWEET.get(), () -> BuildInit.SLAB_SWEET
			.get(), () -> BuildInit.FENCE_SWEET.get()));
		list2.add(new Wood("cherry_wild", () -> FoodInit.LOG_CH_WILD.get(), () -> FoodInit.PLANK_CH_WILD.get(), () -> BuildInit.STAIRS_CHERRY.get(), () -> BuildInit.SLAB_CHERRY
			.get(), () -> BuildInit.FENCE_CHERRY.get()));
		list2.add(new Wood("cinnamon_camphor", () -> FoodInit.LOG_CN_CAMPHOR.get(), () -> FoodInit.PLANK_CN_CAMPHOR.get(), () -> BuildInit.STAIRS_CAMPHOR.get(), () -> BuildInit.SLAB_CAMPHOR
			.get(), () -> BuildInit.FENCE_CAMPHOR.get()));
		list2.add(new Wood("citrus_pomelo", () -> FoodInit.LOG_CT_POMELO.get(), () -> FoodInit.PLANK_CT_POMELO.get(), () -> BuildInit.STAIRS_CITRUS.get(), () -> BuildInit.SLAB_CITRUS
			.get(), () -> BuildInit.FENCE_CITRUS.get()));
		list2.add(new Wood("morus_mulberry", () -> FoodInit.LOG_MR_MULBERRY.get(), () -> FoodInit.PLANK_MR_MULBERRY.get(), () -> BuildInit.STAIRS_MORUS.get(), () -> BuildInit.SLAB_MORUS
			.get(), () -> BuildInit.FENCE_MORUS.get()));
		list2.add(new Wood("olive_ash", () -> FoodInit.LOG_OL_ASH.get(), () -> FoodInit.PLANK_OL_ASH.get(), () -> BuildInit.STAIRS_ASH.get(), () -> BuildInit.SLAB_ASH
			.get(), () -> BuildInit.FENCE_ASH.get()));
		list2.add(new Wood("palm_coconut", () -> FoodInit.LOG_PL_COCONUT.get(), () -> FoodInit.PLANK_PL_COCONUT.get(), () -> BuildInit.STAIRS_PALM.get(), () -> BuildInit.SLAB_PALM
			.get(), () -> BuildInit.FENCE_PALM.get()));
		woods = list2.build();
	}

	public record Seeding(
			Supplier<ItemLike> output,
			Supplier<Item> input,
			int count) {}

	public record Wood(
			String name,
			Supplier<Block> logBlock,
			Supplier<Block> plankBlock,
			Supplier<Block> stairsBlock,
			Supplier<Block> slabBlock,
			Supplier<Block> fenceBlock) {}

}
