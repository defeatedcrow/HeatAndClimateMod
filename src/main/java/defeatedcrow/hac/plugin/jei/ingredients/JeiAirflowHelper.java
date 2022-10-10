package defeatedcrow.hac.plugin.jei.ingredients;

import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceLocation;

public class JeiAirflowHelper implements IIngredientHelper<DCAirflow> {

	@Override
	public IIngredientType<DCAirflow> getIngredientType() {
		return IngredientTypeDC.AIRFLOW;
	}

	@Override
	public String getDisplayName(DCAirflow ingredient) {
		return ingredient.name();
	}

	@Override
	public String getUniqueId(DCAirflow ingredient, UidContext context) {
		return "Airflow:" + ingredient.name();
	}

	@Override
	public ResourceLocation getResourceLocation(DCAirflow ingredient) {
		return new ResourceLocation(ClimateCore.MOD_ID, "airflow/" + ingredient.toString());
	}

	@Override
	public String getDisplayModId(DCAirflow ingredient) {
		return ClimateCore.MOD_ID;
	}

	@Override
	public Iterable<Integer> getColors(DCAirflow ingredient) {
		List<Integer> colors = Lists.newArrayList();
		colors.add(ingredient.getColorInt());
		return colors;
	}

	@Override
	public DCAirflow copyIngredient(DCAirflow ingredient) {
		return ingredient;
	}

	@Override
	public String getErrorInfo(@Nullable DCAirflow ingredient) {
		return ingredient == null ? "Empty" : ingredient.name();
	}

}
