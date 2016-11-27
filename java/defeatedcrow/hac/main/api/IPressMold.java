package defeatedcrow.hac.main.api;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * PressMachine用金型
 */
public interface IPressMold {

	void setOutput(ItemStack mold, ItemStack output);

	ItemStack getOutput(ItemStack mold);

	List<ItemStack> getInputs(ItemStack mold);

}
