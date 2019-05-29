package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.main.api.IFluidFuel;
import defeatedcrow.hac.main.util.DCName;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidStack;

public class DCFuelWrapper implements IRecipeWrapper {

	private final FluidStack fluid;
	private final int time;

	@SuppressWarnings("unchecked")
	public DCFuelWrapper(IFluidFuel recipe) {
		fluid = new FluidStack(recipe.getFluid(), 1000);
		time = recipe.getBurnTime();
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.FLUID, fluid);
	}

	public FluidStack getFluid() {
		return fluid.copy();
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		String s = DCName.BURN_TIME.getLocalizedName() + ": " + time + "s / mB";
		mc.fontRenderer.drawString(s, 40, 10, 0x000000, false);
		if (fluid.getFluid().isGaseous(fluid)) {
			mc.fontRenderer.drawString("GASEOUS", 40, 20, 0xFF5050, false);
		}
	}

}
