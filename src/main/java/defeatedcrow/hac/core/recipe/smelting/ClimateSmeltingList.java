package defeatedcrow.hac.core.recipe.smelting;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.recipe.FoodRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;

public class ClimateSmeltingList {

	public static void init() {
		for (MaterialRecipes.Alloy alloy : MaterialRecipes.ALLOY_VARIANT) {
			addRecipe(alloy.metalBlock().get().asItem(), getHeat(alloy.rarity()), null, DCAirflow.TIGHT, Ingredient.of(alloy.dustBlock().get().asItem()));
		}

		for (FoodRecipes.Smelting foods : FoodRecipes.INSTANCE.Smeltings) {
			ResourceLocation res = DCUtil.getRes(foods.output().get()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
			String fName = res.getPath().replace('/', '_');
			ClimateSmelting ret = new ClimateSmelting(new ItemStack(foods.output().get()), ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN), DCHumidity.exceptUnderwater(), Lists.newArrayList(), foods
				.time(), Ingredient.of(foods.input().get()));
			ClimateSmeltingConfig.addRecipe(fName, ret);
		}

	}

	private static DCHeatTier getHeat(Rarity r) {
		return r == Rarity.RARE ? DCHeatTier.UHT : r == Rarity.UNCOMMON ? DCHeatTier.SMELTING : DCHeatTier.KILN;
	}

	private static void addRecipe(Item output, DCHeatTier heat, Ingredient input) {
		addRecipe(new ItemStack(output), heat, null, null, 100, input);
	}

	private static void addRecipe(Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, Ingredient input) {
		addRecipe(new ItemStack(output), heat, hum, air, 100, input);
	}

	private static void addRecipe(ItemStack output, DCHeatTier heat, DCHumidity hum, DCAirflow air, int i, Ingredient input) {
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
		addRecipe(output, heats, hums, airs, i, input);
	}

	private static void addRecipe(ItemStack output, List<DCHeatTier> heat, List<DCHumidity> hum, List<DCAirflow> air, int f, Ingredient input) {
		ResourceLocation resO = DCUtil.getRes(output.getItem()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		String fName = resO.getPath().replace('/', '_');
		ClimateSmelting ret = new ClimateSmelting(output, heat, hum, air, f, input);
		ClimateSmeltingConfig.addRecipe(fName, ret);
	}

}
