package defeatedcrow.hac.main.api.brewing;

import java.util.List;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IBrewingRecipeRegister {

	/**
	 * Recipeのリストを得る。
	 */
	List<? extends IBrewingRecipe> getRecipeList();

	/**
	 * 登録はこのメソッドのみ
	 */
	void addRecipe(IBrewingRecipe recipe);

	/**
	 * 段階別にレシピを得る
	 */

	IBrewingRecipe getFermentationRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inFluid);

	IBrewingRecipe getDistillationRecipe(DCHeatTier hot, DCHeatTier cold, List<ItemStack> inputs, FluidStack inFluid);

	IBrewingRecipe getAgingRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inFluid);

	IBrewingRecipe getRecipe(FluidStack finalOutputFluid);

	boolean removeRecipe(FluidStack finalOutputFluid);

}
