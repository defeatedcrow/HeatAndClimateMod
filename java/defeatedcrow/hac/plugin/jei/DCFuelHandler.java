package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.main.api.IFluidFuel;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class DCFuelHandler implements IRecipeHandler<IFluidFuel> {

	@Override
	public Class<IFluidFuel> getRecipeClass() {
		return IFluidFuel.class;
	}

	@Override
	public String getRecipeCategoryUid(IFluidFuel recipe) {
		return "dcs_climate.fuel";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(IFluidFuel recipe) {
		return new DCFuelWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(IFluidFuel recipe) {
		return recipe.getFluid() != null;
	}

}
