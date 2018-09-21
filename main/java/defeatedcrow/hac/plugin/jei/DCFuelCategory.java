package defeatedcrow.hac.plugin.jei;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidStack;

public class DCFuelCategory implements IRecipeCategory {

	private final IDrawableStatic background;

	public DCFuelCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("dcs_climate", "textures/gui/fuellist_gui_jei.png");
		background = guiHelper.createDrawable(location, 40, 20, 100, 30);
	}

	@Override
	public String getUid() {
		return "dcs_climate.fuel";
	}

	@Override
	public String getTitle() {
		return I18n.translateToLocal(getUid());
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft mc) {}

	@Override
	public IDrawable getIcon() {
		return null;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (ingredients != null) {

			List<FluidStack> f = ingredients.getInputs(FluidStack.class).get(0);
			recipeLayout.getFluidStacks().init(0, true, 14, 12, 16, 16, 1000, false, null);
			recipeLayout.getFluidStacks().set(0, f.get(0));
		}

	}

	@Override
	public String getModName() {
		return ClimateCore.MOD_NAME;
	}

}
