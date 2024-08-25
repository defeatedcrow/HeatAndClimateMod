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
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_WILD.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GOOSEFOOT.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_CELERY.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_ARTEMISIA.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_CHILI.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_OAT.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_COMMON.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GO_CALABASH.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GR_WILD.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_MINT.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_IR_CROCUS.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_SORREL.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_JUTE.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_BINDWEED.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OR_SPIRANTHES.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_ROGERIA.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GREEN.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RA_ANEMONE.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_COMMON.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_ZIZANIA.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_NIGHTSHADE.get());

		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_COMMON.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CH_WILD.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CM_OIL.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CN_CAMPHOR.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CT_POMELO.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ER_HEATH.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MR_MULBERRY.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MY_EUCALYPTUS.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OL_ASH.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_COCONUT.get());
		INSTANCE.targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RO_RUGOSA.get());
		INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SU_LACQUER.get());

		if (ConfigCommonBuilder.INSTANCE.enCommonCrop.get()) {
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_ONION.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GLASSWORT.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_FENNEL.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_LETTUCE.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_GREEN.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_BELL.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_RYE.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_CARDAMOM.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GO_CUCUMBER.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GR_COMMON.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_BASIL.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_IR_SAFFRON.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_BUCKWHEAT.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_COTTON.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_WATER.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OR_CYMBIDIUM.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_SESAMI.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GARBANZO.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RA_DELPHINIUM.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_SORGHUM.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_SHORT.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_EGGPLANT.get());

			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_WALNUT.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CH_PLUM.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CM_SCHIMA.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CN_CINNAMON.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CT_MANDARIN.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ER_RHODODENDRON.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MR_PAPER.get());
			INSTANCE.targetTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MY_GUAVA.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OL_OLIVE.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_DATE.get());
			INSTANCE.commonList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RO_RASPBERRY.get());
			INSTANCE.commonTreeList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SU_MANGO.get());
		}
	}

}
