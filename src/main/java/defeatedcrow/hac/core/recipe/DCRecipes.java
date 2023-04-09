package defeatedcrow.hac.core.recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DCRecipes {

	private DCRecipes() {};

	public static DCRecipes INSTANCE = new DCRecipes();

	public static final Map<ResourceLocation, IClimateSmelting> SMELTING = new HashMap<>();

	public static Optional<IClimateSmelting> getSmeltingRecipe(Supplier<IClimate> clm, ItemStack item) {
		for (IClimateSmelting recipe : INSTANCE.SMELTING.values()) {
			if (recipe.matcheInput(item) && recipe.matchClimate(clm.get())) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static Optional<IClimateSmelting> getSmeltingRecipe(ResourceLocation res) {
		IClimateSmelting recipe = INSTANCE.SMELTING.get(res);
		return recipe == null ? Optional.of(recipe) : Optional.empty();
	}

	public static Optional<IClimateSmelting> getSmeltingRecipe(IClimate clm, ItemStack item) {
		return getSmeltingRecipe(Suppliers.ofInstance(clm), item);
	}

	public static Optional<IClimateSmelting> hasAnySmeltingRecipe(ItemLike item) {
		for (IClimateSmelting recipe : INSTANCE.SMELTING.values()) {
			if (recipe.matcheInput(new ItemStack(item))) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

}
