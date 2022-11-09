package defeatedcrow.hac.plugin.jei;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;

public class PluginRecipeListDC {

	private PluginRecipeListDC() {}

	public static final List<ClimateCropBaseBlock> CROP_LIST = Lists.newArrayList();

	public static void init() {
		loadFood();
	}

	static void loadFood() {
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_WILD.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_ONION.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_GARLIC.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GOOSEFOOT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GLASSWORT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_SPINACH.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_CELERY.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_FENNEL.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_PARSNIP.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_GREEN.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_CABAGGE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RADISH.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_CHILI.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_PAPRIKA.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_BELL.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_OAT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_RYE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_BARLEY.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_COMMON.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_CARDAMOM.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_TURMERIC.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_MINT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_BASIL.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_PERILLA.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_LAVENDER.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_SORREL.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_BUCKWHEAT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_INDIGO.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_JUTE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_COTTON.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_BLUE.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_BINDWEED.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_WATER.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_POTATO.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_FLOWER.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GREEN.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GARBANZO.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_SOY.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_ADZUKI.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_COMMON.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_SORGHUM.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_CORN.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_ZIZANIA.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_SHORT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_AROMA.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_NIGHTSHADE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_EGGPLANT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_TOMATO.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_LANTERN.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_COMMON.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_WALNUT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_SWEET.get());

	}

}
