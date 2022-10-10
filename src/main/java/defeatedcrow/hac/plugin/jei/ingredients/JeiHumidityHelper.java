package defeatedcrow.hac.plugin.jei.ingredients;

import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceLocation;

public class JeiHumidityHelper implements IIngredientHelper<DCHumidity> {

	@Override
	public IIngredientType<DCHumidity> getIngredientType() {
		return IngredientTypeDC.HUMIDITY;
	}

	@Override
	public String getDisplayName(DCHumidity ingredient) {
		return ingredient.name();
	}

	@Override
	public String getUniqueId(DCHumidity ingredient, UidContext context) {
		return "Humidity:" + ingredient.name();
	}

	@Override
	public ResourceLocation getResourceLocation(DCHumidity ingredient) {
		return new ResourceLocation(ClimateCore.MOD_ID, "humidity/" + ingredient.toString());
	}

	@Override
	public String getDisplayModId(DCHumidity ingredient) {
		return ClimateCore.MOD_ID;
	}

	@Override
	public Iterable<Integer> getColors(DCHumidity ingredient) {
		List<Integer> colors = Lists.newArrayList();
		colors.add(ingredient.getColorInt());
		return colors;
	}

	@Override
	public DCHumidity copyIngredient(DCHumidity ingredient) {
		return ingredient;
	}

	@Override
	public String getErrorInfo(@Nullable DCHumidity ingredient) {
		return ingredient == null ? "Empty" : ingredient.name();
	}

}
