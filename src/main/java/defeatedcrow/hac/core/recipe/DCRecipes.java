package defeatedcrow.hac.core.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.IDeviceFuel;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fluids.FluidStack;

public class DCRecipes {

	private DCRecipes() {};

	public static DCRecipes INSTANCE = new DCRecipes();

	public static final Map<ResourceLocation, IClimateSmelting> SMELTING = new HashMap<>();

	public static final Map<ResourceLocation, IDeviceRecipe> PULVERISE = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> SQUEEZE = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> SIEVE = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> FERMENTATION = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> COOKING = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> TEA = new HashMap<>();

	public static final Map<ResourceLocation, IDeviceFuel> BIOMASS_FUEL = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceFuel> THERMAL_FUEL = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceFuel> FLUID_FUEL = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceFuel> GAS_FUEL = new HashMap<>();

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

	public static Optional<IDeviceRecipe> getCookingRecipe(Supplier<IClimate> clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.COOKING.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm.get())) {
				if (recipe.getPriority() > c) {
					c = recipe.getPriority();
					keep = recipe;
				}
			}
		}
		if (keep != null) {
			return Optional.of(keep);
		}
		return Optional.empty();
	}

	public static Optional<IDeviceRecipe> getFermentationRecipe(Supplier<IClimate> clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.FERMENTATION.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm.get())) {
				if (recipe.getPriority() > c) {
					c = recipe.getPriority();
					keep = recipe;
				}
			}
		}
		if (keep != null) {
			return Optional.of(keep);
		}
		return Optional.empty();
	}

	public static Optional<IDeviceRecipe> getTeaRecipe(Supplier<IClimate> clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.TEA.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm.get())) {
				if (recipe.getPriority() > c) {
					c = recipe.getPriority();
					keep = recipe;
				}
			}
		}
		if (keep != null) {
			return Optional.of(keep);
		}
		return Optional.empty();
	}

	public static Optional<IDeviceRecipe> getPulverizeRecipe(List<ItemStack> inputs) {
		for (IDeviceRecipe recipe : INSTANCE.PULVERISE.values()) {
			if (recipe.matcheInput(inputs).length > 0) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static int getFuelBurnTime(FuelTypeDC type, ItemStack input) {
		if (type == FuelTypeDC.BIOMASS && !DCUtil.isEmpty(input)) {
			for (IDeviceFuel recipe : INSTANCE.BIOMASS_FUEL.values()) {
				if (recipe.matcheInput(input)) {
					return recipe.getBurnTime();
				}
			}
		}
		if (type == FuelTypeDC.THERMAL && !DCUtil.isEmpty(input)) {
			for (IDeviceFuel recipe : INSTANCE.THERMAL_FUEL.values()) {
				if (recipe.matcheInput(input)) {
					return recipe.getBurnTime();
				}
			}
		}
		return 0;
	}

}
