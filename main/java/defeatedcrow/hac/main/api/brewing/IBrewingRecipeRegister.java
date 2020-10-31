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
	List<IBrewingRecipeDC> getBrewingList();

	List<IStillRecipeDC> getStillList();

	List<IAgingRecipeDC> getAgingList();

	/**
	 * 登録はこのメソッドのみ
	 */
	void addBrewingRecipe(IBrewingRecipeDC recipe);

	void addStillRecipe(IStillRecipeDC recipe);

	void addAgingRecipe(IAgingRecipeDC recipe);

	/**
	 * 段階別にレシピを得る
	 */

	IBrewingRecipeDC getBrewingRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inFluid);

	IStillRecipeDC getStillRecipe(DCHeatTier hot, DCHeatTier cold, List<ItemStack> inputs, FluidStack inFluid);

	IAgingRecipeDC getAgingRecipe(IClimate clm, FluidStack inFluid);

	boolean removeBrewingRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inFluid);

	boolean removeStillRecipe(DCHeatTier hot, DCHeatTier cold, List<ItemStack> inputs, FluidStack inFluid);

	boolean removeAgingRecipe(IClimate clm, FluidStack inFluid);

}
