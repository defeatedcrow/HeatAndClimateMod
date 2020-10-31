package defeatedcrow.hac.plugin.jei;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.plugin.jei.ingredients.ClimateTypes;
import defeatedcrow.hac.main.api.brewing.IAgingRecipeDC;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

public class DCAgingWrapper implements IRecipeWrapper {

	private final List<FluidStack> inF;
	private final List<FluidStack> outF;
	private final List<DCHeatTier> temps;
	private final List<DCHumidity> hums;
	private final List<DCAirflow> airs;
	private final int count;

	public DCAgingWrapper(IAgingRecipeDC recipe) {
		inF = new ArrayList<FluidStack>();
		outF = new ArrayList<FluidStack>();
		count = recipe.agingDay();

		if (recipe.getInputFluid() != null) {
			inF.add(recipe.getInputFluid());
		}

		if (recipe.getOutputFluid() != null) {
			outF.add(recipe.getOutputFluid());
		}

		temps = ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		hums = ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
		airs = ImmutableList.of(DCAirflow.TIGHT, DCAirflow.NORMAL);
	}

	public int getDay() {
		return count;
	}

	public List<DCAirflow> getAirs() {
		return airs;
	}

	public List<DCHumidity> getHums() {
		return hums;
	}

	public List<DCHeatTier> getTemps() {
		return temps;
	}

	@Override
	public void getIngredients(IIngredients ing) {
		ing.setInputs(VanillaTypes.FLUID, inF);
		ing.setOutputs(VanillaTypes.FLUID, outF);
		ing.setInputs(ClimateTypes.TEMP, temps);
		ing.setInputs(ClimateTypes.HUM, hums);
		ing.setInputs(ClimateTypes.AIR, airs);
	}

	public List<FluidStack> getFluidInputs() {
		return inF;
	}

	public List<FluidStack> getFluidOutputs() {
		return outF;
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		String s = I18n.format(count + " Day");
		mc.fontRenderer.drawSplitString(s, 67, 42, 110, 0x000000);
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
