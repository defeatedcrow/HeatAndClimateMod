package defeatedcrow.hac.main.api;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * PressMachine用金型
 */
public interface IPressMold {

	ItemStack setOutput(ItemStack mold, ItemStack output, int num);

	ItemStack getOutput(ItemStack mold);

	int getRecipeNumber(ItemStack mold);

	List<ItemStack> getInputs(ItemStack mold);

}
