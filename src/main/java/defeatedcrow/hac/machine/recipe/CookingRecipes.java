package defeatedcrow.hac.machine.recipe;

import java.util.List;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.recipe.device.DeviceRecipeList;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

public class CookingRecipes {
	public static CookingRecipes INSTANCE = new CookingRecipes() {};

	public static void init() {
		List<DCHeatTier> boil = ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN);
		List<DCHeatTier> heat = ImmutableList.of(DCHeatTier.OVEN, DCHeatTier.KILN, DCHeatTier.SMELTING);
		List<DCHumidity> e_hum = ImmutableList.of();
		List<DCAirflow> e_air = ImmutableList.of();
		List<String> tags = ImmutableList.of();

		DeviceRecipeList.addCookingRecipe(RecipeTypeDC.COOKING, new ItemStack(FoodInit.FOOD_SYRUP.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.SAP_SWEET)));

		DeviceRecipeList.addCookingRecipe(RecipeTypeDC.COOKING, new ItemStack(FoodInit.TREEWAX.get()), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil, tags,
			ImmutableList.of(Ingredient.of(TagDC.ItemTag.CROP_LACQUER)));

		DeviceRecipeList.addCookingRecipe(RecipeTypeDC.COOKING, new ItemStack(CoreInit.DUST_SALT.get(), 1), ItemStack.EMPTY, 0, FluidStack.EMPTY, boil,
			ImmutableList.of(TagDC.FluidTag.BRINE.location().toString()),
			ImmutableList.of());
	}

}
