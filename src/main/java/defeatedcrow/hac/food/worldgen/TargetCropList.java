package defeatedcrow.hac.food.worldgen;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;

public class TargetCropList {

	public static final TargetCropList INSTANCE = new TargetCropList();

	static final List<ClimateCropBaseBlock> targetList = Lists.newArrayList();
	static final List<ClimateCropBaseBlock> commonList = Lists.newArrayList();
	static final List<ClimateCropBaseBlock> targetTreeList = Lists.newArrayList();
	static final List<ClimateCropBaseBlock> commonTreeList = Lists.newArrayList();

	public static void init() {
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_WILD.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GOOSEFOOT.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AMR_SNOWDROP.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_CELERY.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AR_BUCE.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_ARTEMISIA.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_CHILI.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_OAT.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_COMMON.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GO_CALABASH.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GR_WILD.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_MINT.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_IR_CROCUS.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_LI_AMANA.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_SORREL.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_JUTE.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_BINDWEED.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OR_SPIRANTHES.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_ROGERIA.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GREEN.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RA_ANEMONE.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_COMMON.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_ZIZANIA.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_NIGHTSHADE.get());

		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_COMMON.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CH_WILD.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CM_OIL.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CN_CAMPHOR.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CT_POMELO.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ER_HEATH.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_EU_KUKUI.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MR_MULBERRY.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MY_EUCALYPTUS.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OL_ASH.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_COCONUT.get());
		TargetCropList.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RO_RUGOSA.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RU_GARDENIA.get());
		TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SU_LACQUER.get());

		if (ConfigCommonBuilder.INSTANCE.enCommonCrop.get()) {
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_ONION.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GLASSWORT.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AMR_AMARYLLIS.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_FENNEL.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AR_TARO.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_LETTUCE.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_GREEN.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_BELL.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_RYE.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_EU_CASSAVA.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_CARDAMOM.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GO_CUCUMBER.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GR_COMMON.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_BASIL.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_IR_SAFFRON.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_LI_FAWN.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_BUCKWHEAT.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_COTTON.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_WATER.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OR_CYMBIDIUM.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_SESAMI.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GARBANZO.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RA_DELPHINIUM.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_SORGHUM.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_SHORT.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_EGGPLANT.get());

			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_WALNUT.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CH_PLUM.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CM_SCHIMA.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CN_CINNAMON.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CT_MANDARIN.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ER_RHODODENDRON.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MR_PAPER.get());
			TargetCropList.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MY_GUAVA.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OL_OLIVE.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_DATE.get());
			TargetCropList.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RO_RASPBERRY.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RU_COFFEE.get());
			TargetCropList.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SU_MANGO.get());
		}
	}

}
