package defeatedcrow.hac.main.api.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * 醸造・蒸留・熟成のセットのレシピ。<br>
 * それぞれ別のマシンで利用される。
 */
public interface IStillRecipeDC {

	@Nullable
	FluidStack getInputFluid();

	Object[] getInput();

	@Nullable
	ItemStack getOutput();

	@Nullable
	FluidStack getOutputFluid();

	/**
	 * macth条件判定用、鉱石辞書変換後のInputリスト
	 */
	List<Object> getProcessedInput();

	/**
	 * Input条件判定
	 */
	boolean matches(List<ItemStack> items, FluidStack fluid);

	/**
	 * Output条件判定
	 */
	boolean matchOutput(List<ItemStack> target, FluidStack fluid);

	/**
	 * Climate条件判定
	 */
	boolean matchClimate(DCHeatTier hot, DCHeatTier cool);

}
