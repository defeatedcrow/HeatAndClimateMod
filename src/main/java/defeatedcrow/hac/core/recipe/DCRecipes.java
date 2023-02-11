package defeatedcrow.hac.core.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmelting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DCRecipes {

	private DCRecipes() {};

	public static DCRecipes INSTANCE = new DCRecipes();

	public static final List<ClimateSmelting> SMELTING = new ArrayList<>();

	public static Optional<IClimateSmelting> getSmeltingRecipe(Supplier<IClimate> clm, ItemStack item) {
		for (IClimateSmelting recipe : INSTANCE.SMELTING) {
			if (recipe.matcheInput(item) && recipe.matchClimate(clm.get()) && recipe.isActive()) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static Optional<IClimateSmelting> getSmeltingRecipe(IClimate clm, ItemStack item) {
		return getSmeltingRecipe(Suppliers.ofInstance(clm), item);
	}

	public static Optional<IClimateSmelting> hasAnySmeltingRecipe(ItemLike item) {
		for (IClimateSmelting recipe : INSTANCE.SMELTING) {
			if (recipe.matcheInput(new ItemStack(item)) && recipe.isActive()) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

}
