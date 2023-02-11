package defeatedcrow.hac.core.recipe.smelting;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.IngPair;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.recipe.FoodRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ClimateSmeltingList {

	public static void init() {
		for (MaterialRecipes.Alloy alloy : MaterialRecipes.ALLOY_VARIANT) {
			addRecipe(alloy.metalBlock().get().asItem(), getHeat(alloy.rarity()), null, DCAirflow.TIGHT, alloy.dustBlock().get().asItem());
		}

		for (FoodRecipes.Smelting foods : FoodRecipes.INSTANCE.Smeltings) {
			ResourceLocation res = DCUtil.getRes(foods.output().get()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
			ResourceLocation resI = DCUtil.getRes(foods.input().get()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
			String fName = res.getPath() + "_0";
			fName = fName.replace('/', '_');
			ClimateSmeltingJson ret = new ClimateSmeltingJson(true, new IngPair("item", res.toString()), ImmutableList.of(DCHeatTier.BOIL, DCHeatTier.OVEN, DCHeatTier.KILN), DCHumidity.exceptUnderwater(), null, foods
				.time(), new IngPair("item", resI.toString()));
			ClimateSmeltingConfig.addRecipe(fName, ret);
		}

	}

	private static DCHeatTier getHeat(Rarity r) {
		return r == Rarity.RARE ? DCHeatTier.UHT : r == Rarity.UNCOMMON ? DCHeatTier.SMELTING : DCHeatTier.KILN;
	}

	private static void addRecipe(Item output, DCHeatTier heat, Item input) {
		addRecipe(0, output, heat, null, null, 100, input);
	}

	private static void addOreRecipe(Item output, DCHeatTier heat, String input) {
		addOreRecipe(0, output, heat, null, null, 100, input);
	}

	private static void addRecipe(Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, Item input) {
		addRecipe(0, output, heat, hum, air, 100, input);
	}

	private static void addOreRecipe(Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, String input) {
		addOreRecipe(0, output, heat, hum, air, 100, input);
	}

	private static void addOreRecipe(Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, int i, String input) {
		addOreRecipe(0, output, heat, hum, air, i, input);
	}

	private static void addRecipe(int num, Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, int f, Item input) {
		ResourceLocation resO = DCUtil.getRes(output).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		ResourceLocation resI = DCUtil.getRes(input).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		String fName = resO.getPath() + "_" + num;
		fName = fName.replace('/', '_');
		ClimateSmeltingJson ret = new ClimateSmeltingJson(true, new IngPair("item", resO.toString()), heat, hum, air, f, new IngPair("item", resI.toString()));
		ClimateSmeltingConfig.addRecipe(fName, ret);
	}

	private static void addOreRecipe(int num, Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, int f, String input) {
		ResourceLocation res = DCUtil.getRes(output).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		String fName = res.getPath() + "_" + num;
		fName = fName.replace('/', '_');
		ClimateSmeltingJson ret = new ClimateSmeltingJson(true, new IngPair("item", res.toString()), heat, hum, air, f, new IngPair("dic_name", input));
		ClimateSmeltingConfig.addRecipe(fName, ret);
	}

}
