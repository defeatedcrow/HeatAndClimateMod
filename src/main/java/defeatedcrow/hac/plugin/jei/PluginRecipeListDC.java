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

		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_GREEN.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_CABAGGE.get());
		CROP_LIST.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RADISH.get());
	}

}
