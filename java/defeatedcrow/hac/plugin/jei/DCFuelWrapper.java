package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.IFluidFuel;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidStack;

public class DCFuelWrapper implements IRecipeWrapper {

	private final FluidStack fluid;
	private final int time;
	private final List<FluidStack> ret = Lists.newArrayList();

	@SuppressWarnings("unchecked")
	public DCFuelWrapper(IFluidFuel recipe) {
		fluid = new FluidStack(recipe.getFluid(), 1000);
		time = recipe.getBurnTime();
		ret.add(fluid);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(FluidStack.class, fluid);
	}

	public FluidStack getFluid() {
		return fluid.copy();
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		String s = "Burntime: " + time + "s / mB";
		mc.fontRendererObj.drawString(s, 40, 10, 0x000000, false);
		if (fluid.getFluid().isGaseous(fluid)) {
			mc.fontRendererObj.drawString("GASEOUS", 40, 20, 0xFF5050, false);
		}
	}

	@Override
	public List getInputs() {
		return null;
	}

	@Override
	public List getOutputs() {
		return null;
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		return ret;
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return null;
	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
