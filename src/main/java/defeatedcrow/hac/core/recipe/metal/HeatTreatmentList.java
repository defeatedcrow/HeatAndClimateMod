package defeatedcrow.hac.core.recipe.metal;

import java.util.List;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class HeatTreatmentList {

	public static void init() {

		HeatTreatment recipe1 = new HeatTreatment(Ingredient.of(CoreInit.DUSTBLOCK_STEEL.get(), CoreInit.METAL_STEEL_FAIL.get()),
				CoreInit.METAL_STEEL_HEATING.get(), CoreInit.METAL_STEEL_COOLING.get(), CoreInit.METALBLOCK_STEEL.get(), CoreInit.METAL_STEEL_FAIL.get())
				.addHeatingParam(SMELTING, DCHumidity.notWet(), ImmutableList.of(DCAirflow.TIGHT))
				.addCoolingParam(COOLING, DCHumidity.wet(), DCAirflow.underRoofs())
				.addAnnealingParam(ImmutableList.of(DCHeatTier.OVEN), DCHumidity.notWet(), DCAirflow.underRoofs());
		addRecipe(CoreInit.METALBLOCK_STEEL.get().asItem(), recipe1);

		HeatTreatment recipe2 = new HeatTreatment(Ingredient.of(CoreInit.DUSTBLOCK_SUS.get(), CoreInit.METAL_SUS_FAIL.get()),
				CoreInit.METAL_SUS_HEATING.get(), CoreInit.METAL_SUS_COOLING.get(), CoreInit.METALBLOCK_SUS.get(), CoreInit.METAL_SUS_FAIL.get())
				.addHeatingParam(UHT, DCHumidity.notWet(), ImmutableList.of(DCAirflow.TIGHT))
				.addCoolingParam(COOLING, DCHumidity.wet(), DCAirflow.underRoofs())
				.addAnnealingParam(ImmutableList.of(DCHeatTier.OVEN), DCHumidity.notWet(), DCAirflow.underRoofs());
		addRecipe(CoreInit.METALBLOCK_SUS.get().asItem(), recipe2);

		HeatTreatment recipe3 = new HeatTreatment(Ingredient.of(CoreInit.DUSTBLOCK_TITANIUM.get(), CoreInit.METAL_TITANIUM_FAIL.get()),
				CoreInit.METAL_TITANIUM_HEATING.get(), CoreInit.METAL_TITANIUM_COOLING.get(), CoreInit.METALBLOCK_TITANIUM.get(), CoreInit.METAL_TITANIUM_FAIL.get())
				.addHeatingParam(UHT, DCHumidity.notWet(), ImmutableList.of(DCAirflow.TIGHT))
				.addCoolingParam(COOLING, DCHumidity.wet(), DCAirflow.underRoofs())
				.addAnnealingParam(ImmutableList.of(DCHeatTier.OVEN), DCHumidity.notWet(), DCAirflow.underRoofs());
		addRecipe(CoreInit.METALBLOCK_TITANIUM.get().asItem(), recipe3);

		HeatTreatment recipe4 = new HeatTreatment(Ingredient.of(CoreInit.DUSTBLOCK_COBALT.get(), CoreInit.METAL_COBALT_FAIL.get()),
				CoreInit.METAL_COBALT_HEATING.get(), CoreInit.METAL_COBALT_COOLING.get(), CoreInit.METALBLOCK_COBALT.get(), CoreInit.METAL_COBALT_FAIL.get())
				.addHeatingParam(UHT, DCHumidity.notWet(), ImmutableList.of(DCAirflow.TIGHT))
				.addCoolingParam(COOLING, DCHumidity.wet(), DCAirflow.underRoofs())
				.addAnnealingParam(ImmutableList.of(DCHeatTier.KILN), DCHumidity.notWet(), DCAirflow.underRoofs());
		addRecipe(CoreInit.METALBLOCK_COBALT.get().asItem(), recipe4);

		HeatTreatment recipe5 = new HeatTreatment(Ingredient.of(CoreInit.DUSTBLOCK_HASTELLOY.get(), CoreInit.METAL_HASTELLOY_FAIL.get()),
				CoreInit.METAL_HASTELLOY_HEATING.get(), CoreInit.METAL_HASTELLOY_COOLING.get(), CoreInit.METALBLOCK_HASTELLOY.get(), CoreInit.METAL_HASTELLOY_FAIL.get())
				.addHeatingParam(UHT, DCHumidity.notWet(), ImmutableList.of(DCAirflow.TIGHT))
				.addCoolingParam(COOLING, DCHumidity.wet(), DCAirflow.underRoofs())
				.addAnnealingParam(ImmutableList.of(DCHeatTier.KILN), DCHumidity.notWet(), DCAirflow.underRoofs());
		addRecipe(CoreInit.METALBLOCK_HASTELLOY.get().asItem(), recipe5);

	}

	private static List<DCHeatTier> SMELTING = ImmutableList.of(DCHeatTier.SMELTING, DCHeatTier.UHT, DCHeatTier.INFERNO);
	private static List<DCHeatTier> UHT = ImmutableList.of(DCHeatTier.UHT, DCHeatTier.INFERNO);
	private static List<DCHeatTier> COOLING = ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL);

	private static void addRecipe(Item output, IHeatTreatment recipe) {
		ResourceLocation resO = DCUtil.getRes(output).orElse(new ResourceLocation(
				ClimateCore.MOD_ID,
				"main/null_item"));
		DCRecipes.INSTANCE.HEAT_TREATMENT.put(resO, recipe);
	}

}
