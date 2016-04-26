package defeatedcrow.hac.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;

public interface IClimateSmeltingRegister {

	/**
	 * Recipeのリストを得る。HeatTierごとに別リストになっている。
	 */
	List<? extends IClimateSmelting> getRecipeList(DCHeatTier tier);

	/**
	 * Recipe登録<br>
	 * 
	 * @param output
	 *            : 完成品
	 * @param secondary
	 *            : 副生物 null可
	 * @param heat
	 *            : 要求HeatTier
	 * @param hum
	 *            : 要求Humidity
	 * @param air
	 *            : 要求Airflow
	 * @param secondaryChance
	 *            : 副生成物の生成率 0.0F-1.0F
	 * @param input
	 *            : 材料
	 */
	void addRecipe(ItemStack output, ItemStack secondary, DCHeatTier heat, DCHumidity hum, DCAirflow air, float secondaryChance,
			boolean needCooling, Object input);

	void addRecipe(ItemStack output, DCHeatTier heat, DCHumidity hum, DCAirflow air, boolean needCooling, Object input);

	void addRecipe(ItemStack output, ItemStack secondary, IClimate climate, float secondaryChance, Object input);

	void addRecipe(ItemStack output, DCHeatTier heat, Object input);

	/**
	 * coreのClimateSmelting.class以外受け付けないのでご注意を(要本体)
	 */
	void addRecipe(IClimateSmelting recipe, DCHeatTier heat);

	/**
	 * input, Climateでレシピを判定
	 */
	IClimateSmelting getRecipe(IClimate clm, ItemStack item);

	IClimateSmelting getRecipe(int code, ItemStack item);

}
