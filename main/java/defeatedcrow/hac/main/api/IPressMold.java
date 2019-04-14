package defeatedcrow.hac.main.api;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * This API has only for the information display, and does not support the change of registered contents.
 */
public interface IPressMold {

	ItemStack setOutput(ItemStack mold, ItemStack output, int num);

	ItemStack getOutput(ItemStack mold);

	int getRecipeNumber(ItemStack mold);

	List<ItemStack> getInputs(ItemStack mold);

}
