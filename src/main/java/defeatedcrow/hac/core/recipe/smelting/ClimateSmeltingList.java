package defeatedcrow.hac.core.recipe.smelting;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.recipe.FoodRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class ClimateSmeltingList {

	public static void init() {
		addRecipe(CoreInit.METALBLOCK_BRASS.get(), getHeat(Rarity.COMMON), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_BRASS.get().asItem()));
		addRecipe(CoreInit.METALBLOCK_BRONZE.get(), getHeat(Rarity.COMMON), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_BRONZE.get().asItem()));
		addRecipe(CoreInit.METALBLOCK_NICKEL_SILVER.get(), getHeat(Rarity.UNCOMMON), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_NICKEL_SILVER.get().asItem()));
		addRecipe(CoreInit.METALBLOCK_ALUMINUM.get(), getHeat(Rarity.UNCOMMON), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_ALUMINUM.get().asItem()));
		addRecipe(CoreInit.METALBLOCK_SILVER.get(), getHeat(Rarity.COMMON), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_SILVER.get().asItem()));
		addRecipe(CoreInit.METALBLOCK_MAGNET.get(), getHeat(Rarity.RARE), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_MAGNET.get().asItem()));
		addRecipe(CoreInit.METALBLOCK_BSCCO.get(), getHeat(Rarity.RARE), null, DCAirflow.TIGHT, Ingredient.of(CoreInit.DUSTBLOCK_BSCCO.get().asItem()));

		for (FoodRecipes.Smelting foods : FoodRecipes.INSTANCE.Smeltings) {
			ResourceLocation res = DCUtil.getRes(foods.output().get()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
			String fName = res.getPath().replace('/', '_');
			ClimateSmelting ret = new ClimateSmelting(new ItemStack(foods.output().get()), ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN), DCHumidity.exceptUnderwater(), Lists
					.newArrayList(), foods
							.time(), Ingredient.of(foods.input().get()));
			ClimateSmeltingConfig.addRecipe(fName, ret);
		}

		for (FoodRecipes.SmeltingBlock foods : FoodRecipes.INSTANCE.SmeltingBlocks) {
			ResourceLocation res = DCUtil.getRes(foods.output().get()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
			String fName = res.getPath().replace('/', '_');
			ClimateSmelting ret = new ClimateSmelting(new ItemStack(foods.output().get()), ImmutableList.of(DCHeatTier.OVEN, DCHeatTier.KILN), DCHumidity.exceptUnderwater(), ImmutableList.of(
					DCAirflow.TIGHT), foods
							.time(), Ingredient.of(foods.input().get()));
			ClimateSmeltingConfig.addRecipe(fName, ret);
		}

		addRecipe(new ItemStack(BuildInit.ADOBE_BLOCK.get()), ImmutableList.of(DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL, DCHeatTier.OVEN), ImmutableList.of(DCHumidity.DRY), null, 300,
				Ingredient.of(
						BuildInit.ADOBE_BLOCK_WET.get()));

		addRecipe(new ItemStack(CoreInit.BLOCK_RUBBER.get()), ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN), ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL), null, 300, Ingredient.of(
				CoreInit.DUSTBLOCK_RUBBER.get()));

		addRecipe(new ItemStack(FoodInit.CONT_LEAF_MOLD.get()), ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM), ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET),
				ImmutableList.of(DCAirflow.TIGHT, DCAirflow.NORMAL), 300, Ingredient.of(TagDC.ItemTag.CONT_LEAVES));

	}

	private static DCHeatTier getHeat(Rarity r) {
		return r == Rarity.RARE ? DCHeatTier.UHT : r == Rarity.UNCOMMON ? DCHeatTier.SMELTING : DCHeatTier.KILN;
	}

	private static void addRecipe(ItemLike output, DCHeatTier heat, Ingredient input) {
		addRecipe(new ItemStack(output), heat, null, null, 100, input);
	}

	private static void addRecipe(ItemLike output, DCHeatTier heat, DCHumidity hum, DCAirflow air, Ingredient input) {
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
