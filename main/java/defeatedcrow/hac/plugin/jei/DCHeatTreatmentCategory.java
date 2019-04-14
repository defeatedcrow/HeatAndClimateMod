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
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DCHeatTreatmentCategory implements IRecipeCategory {

	private final IDrawableStatic background;

	public DCHeatTreatmentCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("dcs_climate", "textures/gui/metal_treatment_gui_jei.png");
		background = guiHelper.createDrawable(location, 18, 25, 140, 124);
	}

	@Override
	public String getUid() {
		return "dcs_climate.treatment";
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
		if (!(recipeWrapper instanceof DCHeatTreatmentWrapper))
			return;
		if (ingredients != null) {
			DCHeatTreatmentWrapper wrapper = ((DCHeatTreatmentWrapper) recipeWrapper);
			List<List<ItemStack>> in = ingredients.getInputs(VanillaTypes.ITEM);
			List<ItemStack> outputs = wrapper.getOutputs();

			if (!in.isEmpty()) {
				for (int i = 0; i < 3; i++) {
					if (i < in.size()) {
						recipeLayout.getItemStacks().init(i, true, 13, 4 + i * 34);
						recipeLayout.getItemStacks().set(i, in.get(i));
					}
				}
			}

			recipeLayout.getItemStacks().init(3, false, 13, 103);
			recipeLayout.getItemStacks().set(3, outputs.get(0));

			recipeLayout.getItemStacks().init(4, false, 47, 103);
			recipeLayout.getItemStacks().set(4, outputs.get(1));

			List<List<DCHeatTier>> temps = wrapper.getTemps();
			List<List<DCHumidity>> hums = wrapper.getHums();
			List<List<DCAirflow>> airs = wrapper.getAirs();

			List<DCHeatTier> temp1 = temps.get(0);
			int i = 0;
			for (DCHeatTier temp : temp1) {
				recipeLayout.getIngredientsGroup(ClimateTypes.TEMP).init(i, true, new HeatTierRenderer(), 43 + temp
						.getID() * 6, 5, 5, 5, 0, 0);
				recipeLayout.getIngredientsGroup(ClimateTypes.TEMP).set(i, temp);
				i++;
			}

			List<DCHumidity> hum1 = hums.get(0);
			int j = 0;
			for (DCHumidity hum : hum1) {
				recipeLayout.getIngredientsGroup(ClimateTypes.HUM).init(j, true, new HumidityRenderer(), 43 + hum
						.getID() * 21, 11, 21, 5, 0, 0);
				recipeLayout.getIngredientsGroup(ClimateTypes.HUM).set(j, hum);
				j++;
			}

			List<DCAirflow> air1 = airs.get(0);
			int k = 0;
			for (DCAirflow air : air1) {
				recipeLayout.getIngredientsGroup(ClimateTypes.AIR).init(k, true, new AirflowRenderer(), 43 + air
						.getID() * 21, 17, 21, 5, 0, 0);
				recipeLayout.getIngredientsGroup(ClimateTypes.AIR).set(k, air);
				k++;
			}

			if (!in.get(1).isEmpty()) {
				List<DCHeatTier> temp2 = temps.get(1);
				int i2 = 0;
				for (DCHeatTier temp : temp2) {
					recipeLayout.getIngredientsGroup(ClimateTypes.TEMP)
							.init(3 + i2, true, new HeatTierRenderer(), 43 + temp.getID() * 6, 39, 6, 5, 0, 0);
					recipeLayout.getIngredientsGroup(ClimateTypes.TEMP).set(3 + i2, temp);
					i2++;
				}

				List<DCHumidity> hum2 = hums.get(1);
				int j2 = 0;
				for (DCHumidity hum : hum2) {
					recipeLayout.getIngredientsGroup(ClimateTypes.HUM)
							.init(3 + j2, true, new HumidityRenderer(), 43 + hum.getID() * 21, 45, 21, 5, 0, 0);
					recipeLayout.getIngredientsGroup(ClimateTypes.HUM).set(3 + j2, hum);
					j2++;
				}

				List<DCAirflow> air2 = airs.get(1);
				int k2 = 0;
				for (DCAirflow air : air2) {
					recipeLayout.getIngredientsGroup(ClimateTypes.AIR)
							.init(3 + k2, true, new AirflowRenderer(), 43 + air.getID() * 21, 52, 21, 5, 0, 0);
					recipeLayout.getIngredientsGroup(ClimateTypes.AIR).set(3 + k2, air);
					k2++;
				}
			}

			if (!in.get(2).isEmpty()) {
				List<DCHeatTier> temp3 = temps.get(2);
				int i3 = 0;
				for (DCHeatTier temp : temp3) {
					recipeLayout.getIngredientsGroup(ClimateTypes.TEMP)
							.init(6 + i3, true, new HeatTierRenderer(), 43 + temp.getID() * 6, 73, 6, 5, 0, 0);
					recipeLayout.getIngredientsGroup(ClimateTypes.TEMP).set(6 + i3, temp);
					i3++;
				}

				List<DCHumidity> hum3 = hums.get(2);
				int j3 = 0;
				for (DCHumidity hum : hum3) {
					recipeLayout.getIngredientsGroup(ClimateTypes.HUM)
							.init(6 + j3, true, new HumidityRenderer(), 43 + hum.getID() * 21, 78, 21, 5, 0, 0);
					recipeLayout.getIngredientsGroup(ClimateTypes.HUM).set(6 + j3, hum);
					j3++;
				}

				List<DCAirflow> air3 = airs.get(2);
				int k3 = 0;
				for (DCAirflow air : air3) {
					recipeLayout.getIngredientsGroup(ClimateTypes.AIR)
							.init(6 + k3, true, new AirflowRenderer(), 43 + air.getID() * 21, 85, 21, 5, 0, 0);
					recipeLayout.getIngredientsGroup(ClimateTypes.AIR).set(6 + k3, air);
					k3++;
				}
			}
		}

	}

	@Override
	public String getModName() {
		return ClimateCore.MOD_NAME;
	}

}
