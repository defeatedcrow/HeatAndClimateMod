package defeatedcrow.hac.plugin.jei;

import java.util.List;

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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MicrobeCategory implements IRecipeCategory {

	private final IDrawableStatic background;

	public MicrobeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("dcs_climate", "textures/gui/microbe_gui_jei.png");
		background = guiHelper.createDrawable(location, 21, 18, 134, 122);
	}

	@Override
	public String getUid() {
		return "dcs_climate.microbe";
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
	public void setRecipe(IRecipeLayout layoutIn, IRecipeWrapper wrapperIn, IIngredients ing) {
		if (!(wrapperIn instanceof MicrobeWrapper))
			return;
		if (ing != null) {
			MicrobeWrapper wrapper = ((MicrobeWrapper) wrapperIn);
			List<ItemStack> medium = wrapper.getMedium();
			List<ItemStack> output = wrapper.getOutput();

			layoutIn.getItemStacks().init(0, false, 16, 11);
			layoutIn.getItemStacks().set(0, output);

			if (!medium.isEmpty()) {
				for (int i = 0; i < 5; i++) {
					if (i < medium.size()) {
						layoutIn.getItemStacks().init(i + 1, true, 22 + i * 17, 90);
						layoutIn.getItemStacks().set(i + 1, medium.get(i));
					}
				}
			}

			List<DCHeatTier> temps = wrapper.getTemp();
			List<DCHumidity> hums = wrapper.getHum();
			List<DCAirflow> airs = wrapper.getAir();

			int i = 0;
			for (DCHeatTier temp : temps) {
				layoutIn.getIngredientsGroup(ClimateTypes.TEMP).init(i, true, new HeatTierRenderer(), 33 + temp
						.getID() * 6, 35, 5, 5, 0, 0);
				layoutIn.getIngredientsGroup(ClimateTypes.TEMP).set(i, temp);
				i++;
			}

			List<DCHumidity> hum1 = hums;
			int j = 0;
			for (DCHumidity hum : hum1) {
				layoutIn.getIngredientsGroup(ClimateTypes.HUM).init(j, true, new HumidityRenderer(), 33 + hum
						.getID() * 21, 41, 21, 5, 0, 0);
				layoutIn.getIngredientsGroup(ClimateTypes.HUM).set(j, hum);
				j++;
			}

			List<DCAirflow> air1 = airs;
			int k = 0;
			for (DCAirflow air : air1) {
				layoutIn.getIngredientsGroup(ClimateTypes.AIR).init(k, true, new AirflowRenderer(), 33 + air
						.getID() * 21, 47, 21, 5, 0, 0);
				layoutIn.getIngredientsGroup(ClimateTypes.AIR).set(k, air);
				k++;
			}
		}

	}

	@Override
	public String getModName() {
		return ClimateCore.MOD_NAME;
	}

}
