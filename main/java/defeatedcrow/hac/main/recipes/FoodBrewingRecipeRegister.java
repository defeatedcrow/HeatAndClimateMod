package defeatedcrow.hac.main.recipes;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IAgingRecipeDC;
import defeatedcrow.hac.main.api.brewing.IBrewingRecipeDC;
import defeatedcrow.hac.main.api.brewing.IBrewingRecipeRegister;
import defeatedcrow.hac.main.api.brewing.IStillRecipeDC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class FoodBrewingRecipeRegister implements IBrewingRecipeRegister {

	public IBrewingRecipeRegister instance() {
		return MainAPIManager.brewingRegister;
	}

	private static List<IBrewingRecipeDC> brewing;
	private static List<IStillRecipeDC> still;
	private static List<IAgingRecipeDC> aging;

	public FoodBrewingRecipeRegister() {
		brewing = new ArrayList<IBrewingRecipeDC>();
		still = new ArrayList<IStillRecipeDC>();
		aging = new ArrayList<IAgingRecipeDC>();
	}

	@Override
	public List<IBrewingRecipeDC> getBrewingList() {
		return brewing;
	}

	@Override
	public List<IStillRecipeDC> getStillList() {
		return still;
	}

	@Override
	public List<IAgingRecipeDC> getAgingList() {
		return aging;
	}

	@Override
	public void addBrewingRecipe(IBrewingRecipeDC recipe) {
		if (recipe != null) {
			boolean b1 = recipe.getInput() == null && recipe.getInputFluid() == null;
			boolean b2 = DCUtil.isEmpty(recipe.getOutput()) && recipe.getOutputFluid() == null;
			boolean b3 = hasEmptyInput(recipe.getInput());
			if (!b1 && !b2 && !b3) {
				brewing.add(recipe);
			}
		}
	}

	@Override
	public void addStillRecipe(IStillRecipeDC recipe) {
		if (recipe != null) {
			boolean b1 = recipe.getInput() == null && recipe.getInputFluid() == null;
			boolean b2 = DCUtil.isEmpty(recipe.getOutput()) && recipe.getOutputFluid() == null;
			boolean b3 = hasEmptyInput(recipe.getInput());
			if (!b1 && !b2 && !b3) {
				still.add(recipe);
			}
		}
	}

	@Override
	public void addAgingRecipe(IAgingRecipeDC recipe) {
		if (recipe != null) {
			if (recipe.getInputFluid() != null && recipe.getOutputFluid() != null) {
				aging.add(recipe);
			}
		}
	}

	@Override
	public IBrewingRecipeDC getBrewingRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inFluid) {
		IBrewingRecipeDC ret = null;
		int priority = -1;
		for (IBrewingRecipeDC recipe : brewing) {
			if (recipe.matches(inputs, inFluid) && recipe.matchClimate(clm)) {
				if (recipe.getPriority() > priority) {
					ret = recipe;
					priority = recipe.getPriority();
				}
			}
		}
		if (ret != null)
			return ret;

		return null;
	}

	@Override
	public IStillRecipeDC getStillRecipe(DCHeatTier hot, DCHeatTier cold, List<ItemStack> inputs, FluidStack inFluid) {
		for (IStillRecipeDC recipe : still) {
			if (recipe.matches(inputs, inFluid) && recipe.matchClimate(hot, cold)) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public IAgingRecipeDC getAgingRecipe(IClimate clm, FluidStack inFluid) {
		for (IAgingRecipeDC recipe : aging) {
			if (recipe.matches(inFluid) && recipe.matchClimate(clm)) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public boolean removeBrewingRecipe(List<ItemStack> inputs, FluidStack inFluid) {
		IClimate clm = ClimateAPI.register.getClimateFromParam(DCHeatTier.WARM, DCHumidity.WET, DCAirflow.NORMAL);
		IBrewingRecipeDC recipe = getBrewingRecipe(clm, inputs, inFluid);
		if (recipe != null && brewing.remove(recipe)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeStillRecipe(DCHeatTier hot, DCHeatTier cold, List<ItemStack> inputs, FluidStack inFluid) {
		IStillRecipeDC recipe = getStillRecipe(hot, cold, inputs, inFluid);
		if (recipe != null && still.remove(recipe)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAgingRecipe(FluidStack inFluid) {
		IClimate clm = ClimateAPI.register.getClimateFromParam(DCHeatTier.WARM, DCHumidity.WET, DCAirflow.NORMAL);
		IAgingRecipeDC recipe = getAgingRecipe(clm, inFluid);
		if (recipe != null && aging.remove(recipe)) {
			return true;
		}
		return false;
	}

	private boolean hasEmptyInput(Object[] inputs) {
		if (inputs != null && inputs.length > 0) {
			for (Object in : inputs) {
				if (in instanceof String) {
					boolean ret = true;
					if (OreDictionary.doesOreNameExist((String) in)) {
						List l = OreDictionary.getOres((String) in);
						if (!l.isEmpty() && l.size() > 0) {
							ret = false;
						}
					}

					if (ret) {
						return true;
					}
				} else if (in == null) {
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

}
