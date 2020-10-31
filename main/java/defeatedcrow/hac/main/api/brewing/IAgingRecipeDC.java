package defeatedcrow.hac.main.api.brewing;

import javax.annotation.Nonnull;

import defeatedcrow.hac.api.climate.IClimate;
import net.minecraftforge.fluids.FluidStack;

/**
 * 醸造・蒸留・熟成のセットのレシピ。<br>
 * それぞれ別のマシンで利用される。
 */
public interface IAgingRecipeDC {

	@Nonnull
	FluidStack getInputFluid();

	@Nonnull
	FluidStack getOutputFluid();

	int agingDay();

	/**
	 * Input条件判定
	 */
	boolean matches(FluidStack fluid);

	/**
	 * Output条件判定
	 */
	boolean matchOutput(FluidStack fluid);

	/**
	 * Climate条件判定
	 */
	boolean matchClimate(IClimate climate);

}
