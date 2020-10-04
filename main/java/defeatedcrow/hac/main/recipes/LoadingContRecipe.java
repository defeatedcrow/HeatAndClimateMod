package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.ICompressionRecipe;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class LoadingContRecipe {

	public static void add(RecipeResourcesMain res) {
		addCompressionRecipe(MainInit.logCont);
		addCompressionRecipe(MainInit.cropCont);

		addCompressionRecipe(MainInit.dropCont);
		addCompressionRecipe(MainInit.miscCont);
		addCompressionRecipe(MainInit.cardboard);
		addCompressionRecipe(MainInit.dustBags);
		addCompressionRecipe(MainInit.cropJute);
		addCompressionRecipe(MainInit.dustCake);
	}

	public static void addCompressionRecipe(Block block) {
		addCompressionRecipe(block, "main_container");
	}

	public static void addCompressionRecipe(Block block, String name) {
		if (block instanceof ICompressionRecipe) {
			ICompressionRecipe cont = (ICompressionRecipe) block;
			for (int i = 0; i < cont.containedItem().length; i++) {
				Object ret1 = cont.getInputDic(i);
				DCRecipe.jsonShapedRecipe(name, new ItemStack(block, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					ret1 });

				ItemStack ret2 = cont.getOutputItem(i);
				DCRecipe.jsonShapelessRecipe(name, ret2, new Object[] { new ItemStack(block, 1, i) });
			}
		}
	}

}
