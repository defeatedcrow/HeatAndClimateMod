package defeatedcrow.hac.main.api.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * 醸造・蒸留・熟成のセットのレシピ。<br>
 * それぞれ別のマシンで利用される。
 */
public interface IBrewingRecipe {

	/*
	 * Input登録内容
	 */

	/*
	 * 1: Fermentation
	 */
	@Nullable
	FluidStack getInputFluid();

	Object[] getInput();

	@Nullable
	ItemStack getOutput();

	@Nullable
	FluidStack getRawFluid();

	/*
	 * 2: Distillation
	 */

	Object getRawInput();

	@Nullable
	ItemStack getDistilledOutput();

	@Nullable
	FluidStack getDistilledFluid();

	/*
	 * 3: Barrel Aging
	 */

	@Nullable
	FluidStack getFinalFluid();

	/**
	 * macth条件判定用、鉱石辞書変換後のInputリスト
	 */
	List<Object> getProcessedInput1();

	List<Object> getProcessedInput2();

	/**
	 * Input条件判定
	 */
	boolean matches1(List<ItemStack> items, FluidStack fluid);

	boolean matches2(ItemStack item, FluidStack fluid);

	boolean matches3(FluidStack fluid);

	/**
	 * Output条件判定
	 */
	boolean matchOutput1(List<ItemStack> target, FluidStack fluid);

	boolean matchOutput2(ItemStack target, FluidStack fluid);

	/**
	 * Climate条件判定
	 */
	boolean matchClimate1(IClimate climate);

	boolean matchClimate2(DCHeatTier hot, DCHeatTier cool);

	boolean matchClimate3(IClimate climate);

	/**
	 * 追加条件
	 * trueで条件クリア
	 */
	boolean additionalRequire(World world, BlockPos pos);

	/**
	 * レシピ表示機能用
	 */
	String additionalString();

}
