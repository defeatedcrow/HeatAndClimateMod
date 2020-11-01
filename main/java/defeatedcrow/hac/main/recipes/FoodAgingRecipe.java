package defeatedcrow.hac.main.recipes;

import java.util.ArrayList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.main.api.brewing.IAgingRecipeDC;
import net.minecraftforge.fluids.FluidStack;

public class FoodAgingRecipe implements IAgingRecipeDC {

	private final FluidStack inputF;
	private final FluidStack outputF;
	private final int count;
	private static final ArrayList<Object> EMPTY = new ArrayList<Object>();

	public FoodAgingRecipe(FluidStack oF, FluidStack iF, int agingDay) {
		inputF = iF;
		outputF = oF;
		if (agingDay < 1) {
			agingDay = 1;
		}
		count = agingDay;
	}

	@Override
	public FluidStack getInputFluid() {
		return this.inputF;
	}

	@Override
	public FluidStack getOutputFluid() {
		return this.outputF;
	}

	@Override
	public int agingDay() {
		return count;
	}

	@Override
	public boolean matches(FluidStack fluid) {
		boolean b1 = false;
		if (fluid != null && inputF != null) {
			if (inputF.getFluid() == fluid.getFluid() || FluidDictionaryDC.matchFluid(fluid.getFluid(), inputF
					.getFluid())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean matchOutput(FluidStack fluid) {
		return true;
	}

	@Override
	public boolean matchClimate(IClimate climate) {
		if (climate != null) {
			if (climate.getHeat().getID() > DCHeatTier.HOT.getID() || climate.getHeat().getID() < DCHeatTier.COLD
					.getID()) {
				return false;
			}
			if (climate.getHumidity().getID() > DCHumidity.WET.getID()) {
				return false;
			}
			if (climate.getAirflow().getID() > DCAirflow.NORMAL.getID()) {
				return false;
			}
		}
		return true;
	}

}
