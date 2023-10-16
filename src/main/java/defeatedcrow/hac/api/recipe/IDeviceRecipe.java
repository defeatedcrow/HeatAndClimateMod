package defeatedcrow.hac.api.recipe;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * Climateを条件に進む、多:多交換のレシピ。<br>
 * すべてのレシピでインターフェイスは共用。<br>
 * 材料は辞書対応。<br>
 */
public interface IDeviceRecipe extends ISerealizedRecipe {

	RecipeTypeDC getType();

	ItemStack getOutput();

	ItemStack getSecondaryOutput();

	int getSecondaryRate();

	ItemStack getTertiaryOutput();

	int getTertiaryRate();

	FluidStack getOutputFluid();

	/**
	 * macth条件判定用、鉱石辞書変換後のInputリスト
	 */
	List<Ingredient> getInputs();

	List<TagKey<Fluid>> getInputFluids();

	/**
	 * Input条件判定
	 */
	int[] matcheInput(List<ItemStack> items);

	boolean matcheInputFluid(FluidStack input1, FluidStack input2);

	/**
	 * Climate条件判定1
	 */
	boolean matchClimate(int code);

	/**
	 * Climate条件判定2
	 */
	boolean matchClimate(IClimate climate);

	/**
	 * おもにレシピ条件表示機能用
	 * HeatTierのみ、Tier+1まで対応範囲になる
	 */
	List<DCHeatTier> requiredHeat();

	List<DCHumidity> requiredHum();

	List<DCAirflow> requiredAir();

	int getPriority();

}
