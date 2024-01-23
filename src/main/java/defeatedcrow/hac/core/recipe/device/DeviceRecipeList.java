package defeatedcrow.hac.core.recipe.device;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.recipe.CookingRecipes;
import defeatedcrow.hac.machine.recipe.PulveriseRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

public class DeviceRecipeList {

	public static void init() {
		CookingRecipes.init();
		PulveriseRecipes.init();
	}

	public static void addSimpleRecipe(int id, RecipeTypeDC group, Item output, DCHeatTier heat, List<Ingredient> input) {
		addSimpleRecipe(id, group, new ItemStack(output), heat, null, null, input);
	}

	public static void addSimpleRecipe(int id, RecipeTypeDC group, Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, List<Ingredient> input) {
		addSimpleRecipe(id, group, new ItemStack(output), heat, hum, air, input);
	}

	public static void addSimpleRecipe(int id, RecipeTypeDC group, ItemStack output, DCHeatTier heat, DCHumidity hum, DCAirflow air, List<Ingredient> input) {
		List<DCHeatTier> heats = Lists.newArrayList();
		List<DCHumidity> hums = Lists.newArrayList();
		List<DCAirflow> airs = Lists.newArrayList();
		if (heat == null) {
			heats.addAll(DCHeatTier.elements());
		} else {
			heats.add(heat);
			if (heat == DCHeatTier.NORMAL) {
				heats.add(DCHeatTier.COOL);
				heats.add(DCHeatTier.WARM);
			} else if (heat.getTier() < 0 && heat != DCHeatTier.ABSOLUTE) {
				heats.add(heat.addTier(-1));
			} else if (heat.getTier() > 0 && heat != DCHeatTier.INFERNO) {
				heats.add(heat.addTier(1));
			}
		}
		if (hum == null) {
			hums.addAll(DCHumidity.elements());
		} else {
			hums.add(hum);
		}
		if (air == null) {
			airs.addAll(DCAirflow.elements());
		} else {
			airs.add(air);
		}
		addRecipe(id, group, output, ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, FluidStack.EMPTY, heats, hums, airs, new ArrayList<String>(), input);
	}

	// mill
	public static void addMillRecipe(int id, RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, List<Ingredient> in) {
		addPulverizeRecipe(id, group, o, sec, secRate, ItemStack.EMPTY, 0, FluidStack.EMPTY, in);
	}

	// squeeze, distill
	public static void addFluidRecipe(int id, RecipeTypeDC group, ItemStack o, FluidStack oF, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, List<String> inF, List<Ingredient> in) {
		addRecipe(id, group, o, ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, oF, t, h, a, inF, in);
	}

	// cooking
	public static void addCookingRecipe(int id, RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, FluidStack oF, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, List<String> inF,
			List<Ingredient> in) {
		addRecipe(id, group, o, sec, secRate, ItemStack.EMPTY, 0, oF, t, h, a, inF, in);
	}

	// cooking2
	public static void addCookingRecipe(int id, RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, FluidStack oF, List<DCHeatTier> t, List<String> inF, List<Ingredient> in) {
		addRecipe(id, group, o, sec, secRate, ItemStack.EMPTY, 0, oF, t, Lists.newArrayList(), Lists.newArrayList(), inF, in);
	}

	// fermentation
	public static void addFermentationRecipe(int id, RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, FluidStack oF, List<String> inF, List<Ingredient> in) {
		addRecipe(id, group, o, sec, secRate, ItemStack.EMPTY, 0, oF,
			ImmutableList.of(DCHeatTier.WARM, DCHeatTier.HOT),
			ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET),
			ImmutableList.of(DCAirflow.NORMAL), inF, in);
	}

	// pulverize
	public static void addPulverizeRecipe(int id, RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack oF, List<Ingredient> in) {
		List<DCHeatTier> heats = Lists.newArrayList();
		List<DCHumidity> hums = Lists.newArrayList();
		List<DCAirflow> airs = Lists.newArrayList();
		addRecipe(id, group, o, sec, secRate, ter, terRate, oF, heats, hums, airs, new ArrayList<String>(), in);
	}

	// all
	public static void addRecipe(int id, RecipeTypeDC type, ItemStack output, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack outF, List<DCHeatTier> heat, List<DCHumidity> hum, List<DCAirflow> air,
			List<String> inF, List<Ingredient> input) {
		ResourceLocation res = DCUtil.getRes(output.getItem()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		if (DCUtil.isEmpty(output)) {
			res = DCUtil.getRes(outF.getFluid()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		}
		String fName = res.getPath().replace('/', '_');
		DeviceRecipe ret = new DeviceRecipe(type, output, sec, secRate, ter, terRate, outF, heat, hum, air, inF, input);
		if (id >= 0) {
			fName += "_" + id;
		}
		DeviceRecipeConfig.addRecipe(fName, ret);
	}

}
