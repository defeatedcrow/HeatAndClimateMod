package defeatedcrow.hac.main.api;

import net.minecraft.item.ItemStack;

public interface ICompressionRecipe {

	ItemStack[] containedItem();

	ItemStack getOutputItem(int i);

	Object getInputDic(int i);

}
