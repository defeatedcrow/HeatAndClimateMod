package defeatedcrow.hac.main.api;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * This API has only for the information display, and does not support the change of registered contents.
 */
public interface IHeatTreatmentRegister {

	List<IHeatTreatment> getRecipeList();

	void registerRecipe(IHeatTreatment recipe);

	boolean isRegisteredAsInput(ItemStack input);

	IHeatTreatment getRecipe(ItemStack input);

}
