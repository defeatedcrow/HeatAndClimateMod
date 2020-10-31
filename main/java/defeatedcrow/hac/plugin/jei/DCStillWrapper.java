package defeatedcrow.hac.plugin.jei;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.plugin.jei.ingredients.ClimateTypes;
import defeatedcrow.hac.main.api.brewing.IStillRecipeDC;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class DCStillWrapper implements IRecipeWrapper {

	private final List<List<ItemStack>> input;
	private final List<ItemStack> output;
	private final IStillRecipeDC rec;
	private final List<FluidStack> inF;
	private final List<FluidStack> outF;
	private final List<DCHeatTier> temps;

	public DCStillWrapper(IStillRecipeDC recipe) {
		rec = recipe;
		input = new ArrayList<List<ItemStack>>();
		if (!recipe.getProcessedInput().isEmpty()) {
			for (Object obj : recipe.getProcessedInput()) {
				if (obj instanceof ItemStack) {
					List<ItemStack> ret = new ArrayList<ItemStack>();
					ret.add((ItemStack) obj);
					input.add(ret);
				} else if (obj instanceof List) {
					input.add((List<ItemStack>) obj);
				}
			}
		}
		output = new ArrayList<ItemStack>();
		output.add(recipe.getOutput());

		inF = new ArrayList<FluidStack>();
		outF = new ArrayList<FluidStack>();
		if (recipe.getInputFluid() != null) {
			inF.add(recipe.getInputFluid());
		}
		if (recipe.getOutputFluid() != null) {
			outF.add(recipe.getOutputFluid());
		}

		temps = ImmutableList.of(recipe.requiredHeatTemp(), recipe.requiredColdTemp());
	}

	public List<List<ItemStack>> getRecipeInputs() {
		return input;
	}

	public List<DCHeatTier> getTemps() {
		return temps;
	}

	@Override
	public void getIngredients(IIngredients ing) {
		ing.setInputLists(VanillaTypes.ITEM, input);
		ing.setInputs(VanillaTypes.FLUID, inF);
		ing.setOutputs(VanillaTypes.ITEM, output);
		ing.setOutputs(VanillaTypes.FLUID, outF);
		ing.setInputs(ClimateTypes.TEMP, temps);
	}

	public List<List<ItemStack>> getInputs() {
		return input;
	}

	public List<ItemStack> getOutputs() {
		return output;
	}

	public List<FluidStack> getFluidInputs() {
		return inF;
	}

	public List<FluidStack> getFluidOutputs() {
		return outF;
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		ResourceLocation res = new ResourceLocation("dcs_climate", "textures/gui/still_gui.png");
		mc.getTextureManager().bindTexture(res);
		if (!temps.isEmpty()) {
			int baseX = 65;
			int baseY = 45;
			mc.currentScreen.drawTexturedModalRect(baseX, baseY, 196, 29, 15, 17);
			mc.currentScreen.drawTexturedModalRect(baseX + 15, baseY, 196, 46, 15, 17);
		}
	}

	@Override
	public List<String> getTooltipStrings(int x, int y) {
		int baseX = 65;
		int baseY = 45;
		List<String> s = new ArrayList<String>();
		if (x > baseX && x < baseX + 30 && y > baseY && y < baseY + 17) {
			if (x < baseX + 15) {
				s.add("Heat Source" + temps.get(0).localize());
			} else {
				s.add("Cold Source" + temps.get(1).localize());
			}
		}

		return s.isEmpty() ? null : s;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
