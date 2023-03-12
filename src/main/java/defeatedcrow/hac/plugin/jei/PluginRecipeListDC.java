package defeatedcrow.hac.plugin.jei;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import defeatedcrow.hac.food.material.block.LeavesCropBlockDC;

public class PluginRecipeListDC {

	private PluginRecipeListDC() {}

	public static final List<IClimateSmelting> SMELTING_LIST = Lists.newArrayList();
	public static final List<ClimateCropBaseBlock> CROP_LIST = Lists.newArrayList();
	public static final List<LeavesCropBlockDC> TREE_LIST = Lists.newArrayList();

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

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_ARTEMISIA.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_LETTUCE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_PYRETHRUM.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_FLOWER.get());

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

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_ROGERIA.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_SESAMI.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_DEVILSCLAW.get());

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

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_BH_COMMON.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_BH_WALNUT.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_BH_SWEET.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CM_OIL.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CM_SCHIMA.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CM_TEA.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CH_WILD.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CH_PLUM.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CH_PEACH.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CN_CAMPHOR.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CN_CINNAMON.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CN_AVOCADO.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CT_POMELO.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CT_MANDARIN.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_CT_LEMON.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_ER_HEATH.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_ER_RHODODENDRON.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_ER_BLUEBERRY.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_MR_MULBERRY.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_MR_PAPER.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_MR_RUBBER.get());

		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_OL_ASH.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_OL_OLIVE.get());
		TREE_LIST.add((LeavesCropBlockDC) FoodInit.LEAVES_OL_OSMANTHUS.get());

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_COCONUT.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_DATE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_OIL.get());

		for (IClimateSmelting recipe : DCRecipes.INSTANCE.SMELTING) {
			if (recipe.isActive())
				SMELTING_LIST.add(recipe);
		}

	}

}
