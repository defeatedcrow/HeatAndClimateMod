package defeatedcrow.hac.plugin.jei;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.jei.ingredients.AirflowRenderer;
import defeatedcrow.hac.core.plugin.jei.ingredients.ClimateTypes;
import defeatedcrow.hac.core.plugin.jei.ingredients.HeatTierRenderer;
import defeatedcrow.hac.core.plugin.jei.ingredients.HumidityRenderer;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class ClimateFluidCategory implements IRecipeCategory {

	private final IDrawableStatic background;

	public ClimateFluidCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("dcs_climate", "textures/gui/c_fluid_gui_jei.png");
		background = guiHelper.createDrawable(location, 8, 8, 160, 56);
	}

	@Override
	public String getUid() {
		return "dcs_climate.drink";
	}

	@Override
	public String getTitle() {
		return I18n.format(getUid());
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
		if (!(recipeWrapper instanceof ClimateFluidWrapper))
			return;
		ClimateFluidWrapper wrapper = ((ClimateFluidWrapper) recipeWrapper);
		// wrapper.getIngredients(ingredients);

		FluidStack input = wrapper.getFluidInput();

		if (input != null) {
			recipeLayout.getFluidStacks().init(0, false, 24, 20, 13, 13, 1000, false, null);
			recipeLayout.getFluidStacks().set(0, input);
		}

		recipeLayout.getItemStacks().init(0, true, 22, 38);
		recipeLayout.getItemStacks().set(0, wrapper.cup);

		DCHeatTier temp = wrapper.getTemp();
		if (temp != null) {
			recipeLayout.getIngredientsGroup(ClimateTypes.TEMP)
					.init(0, true, new HeatTierRenderer(), 51, 15, 40, 5, 0, 0);
			recipeLayout.getIngredientsGroup(ClimateTypes.TEMP).set(0, temp);
		}

		DCHumidity hum = wrapper.getHum();
		if (hum != null) {
			recipeLayout.getIngredientsGroup(ClimateTypes.HUM)
					.init(0, true, new HumidityRenderer(), 51, 26, 40, 5, 0, 0);
			recipeLayout.getIngredientsGroup(ClimateTypes.HUM).set(0, hum);
		}

		DCAirflow air = wrapper.getAir();
		if (air != null) {
			recipeLayout.getIngredientsGroup(ClimateTypes.AIR)
					.init(0, true, new AirflowRenderer(), 51, 37, 40, 5, 0, 0);
			recipeLayout.getIngredientsGroup(ClimateTypes.AIR).set(0, air);
		}

	}

	@Override
	public String getModName() {
		return ClimateCore.MOD_NAME;
	}

}
