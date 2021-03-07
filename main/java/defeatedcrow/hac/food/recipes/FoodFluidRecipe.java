package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/** We recommend that do not use this class and refer to it from the RecipeAPI. */
@Deprecated
public class FoodFluidRecipe {

	public static void regBoilrecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHumidity hum,
			DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		FluidCraftRecipe recipe = new FluidCraftRecipe(out, sec, outF, DCHeatTier.OVEN, hum, air, chance, cooling, inF,
				input);
		recipe.requiredHeat().add(DCHeatTier.BOIL);
		RecipeAPI.registerFluidRecipes.addRecipe(recipe);
	}

}
