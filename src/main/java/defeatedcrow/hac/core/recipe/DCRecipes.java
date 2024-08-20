package defeatedcrow.hac.core.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.IDeviceFuel;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;

public class DCRecipes {

	private DCRecipes() {};

	public static DCRecipes INSTANCE = new DCRecipes();

	public static final Map<ResourceLocation, IClimateSmelting> SMELTING = new HashMap<>();
	public static final Map<ResourceLocation, IHeatTreatment> HEAT_TREATMENT = new HashMap<>();

	public static final Map<ResourceLocation, IDeviceRecipe> PULVERISE = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> SQUEEZE = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> SIEVE = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> FERMENTATION = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> COOKING = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> FRYING = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceRecipe> TEA = new HashMap<>();

	public static final Map<ResourceLocation, IDeviceFuel> BIOMASS_FUEL = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceFuel> THERMAL_FUEL = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceFuel> FLUID_FUEL = new HashMap<>();
	public static final Map<ResourceLocation, IDeviceFuel> GAS_FUEL = new HashMap<>();

	public static void clear() {
		SMELTING.clear();
		HEAT_TREATMENT.clear();
		PULVERISE.clear();
		SQUEEZE.clear();
		FERMENTATION.clear();
		COOKING.clear();
		FRYING.clear();
		BIOMASS_FUEL.clear();
		THERMAL_FUEL.clear();
		FLUID_FUEL.clear();
		GAS_FUEL.clear();
	}

	@SubscribeEvent
	public static void serverStop(ServerStoppedEvent event) {
		INSTANCE.clear();
	}

	public static Optional<IClimateSmelting> getSmeltingRecipe(Supplier<IClimate> clm, ItemStack item) {
		return getSmeltingRecipe(clm.get(), item);
	}

	public static Optional<IClimateSmelting> getSmeltingRecipe(ResourceLocation res) {
		IClimateSmelting recipe = INSTANCE.SMELTING.get(res);
		return recipe == null ? Optional.of(recipe) : Optional.empty();
	}

	public static Optional<IClimateSmelting> getSmeltingRecipe(IClimate clm, ItemStack item) {
		for (IClimateSmelting recipe : INSTANCE.SMELTING.values()) {
			if (recipe.matcheInput(item) && recipe.matchClimate(clm)) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static Optional<IClimateSmelting> hasAnySmeltingRecipe(ItemLike item) {
		for (IClimateSmelting recipe : INSTANCE.SMELTING.values()) {
			if (recipe.matcheInput(new ItemStack(item))) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static Optional<IHeatTreatment> getHeatTreatmentRecipe(Supplier<IClimate> clm, ItemStack item) {
		return getHeatTreatmentRecipe(clm.get(), item);
	}

	public static Optional<IHeatTreatment> getHeatTreatmentRecipe(ResourceLocation res) {
		IHeatTreatment recipe = INSTANCE.HEAT_TREATMENT.get(res);
		return recipe == null ? Optional.of(recipe) : Optional.empty();
	}

	public static Optional<IHeatTreatment> getHeatTreatmentRecipe(IClimate clm, ItemStack item) {
		for (IHeatTreatment recipe : INSTANCE.HEAT_TREATMENT.values()) {
			ItemStack ret = recipe.getCurrentOutput(item, clm);
			if (!ret.isEmpty() && !ret.is(item.getItem())) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static Optional<IHeatTreatment> hasAnyHeatTreatmentRecipe(ItemLike item) {
		for (IHeatTreatment recipe : INSTANCE.HEAT_TREATMENT.values()) {
			ItemStack check = new ItemStack(item);
			if (recipe.getHeatingInput().test(check)
					|| recipe.getHeatingOutput().asItem() == item.asItem()
					|| recipe.getCoolingOutput().asItem() == item.asItem()) {
				return Optional.of(recipe);
			}
		}
		return Optional.empty();
	}

	public static Optional<IDeviceRecipe> getCookingRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.COOKING.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm)) {
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

	public static Optional<IDeviceRecipe> getFermentationRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.FERMENTATION.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm)) {
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

	public static Optional<IDeviceRecipe> getTeaRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.TEA.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm)) {
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

	public static Optional<IDeviceRecipe> getFryingRecipe(IClimate clm, List<ItemStack> inputs, FluidStack inF) {
		int c = 0;
		IDeviceRecipe keep = null;
		for (IDeviceRecipe recipe : INSTANCE.FRYING.values()) {
			if (recipe.matcheInput(inputs).length > 0 && recipe.matcheInputFluid(inF, FluidStack.EMPTY) && recipe.matchClimate(clm)) {
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

	public static int getFluidFuelBurnTime(FuelTypeDC type, FluidStack input) {
		if (type == FuelTypeDC.FLUID && input != null && !input.isEmpty()) {
			for (IDeviceFuel recipe : INSTANCE.FLUID_FUEL.values()) {
				if (recipe.matcheInputFluid(input)) {
					return recipe.getBurnTime();
				}
			}
		}
		if (type == FuelTypeDC.GAS && input != null && !input.isEmpty()) {
			for (IDeviceFuel recipe : INSTANCE.GAS_FUEL.values()) {
				if (recipe.matcheInputFluid(input)) {
					return recipe.getBurnTime();
				}
			}
		}
		return 0;
	}

}
