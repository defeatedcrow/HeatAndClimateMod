package defeatedcrow.hac.plugin.jei.ingredients;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.ClimateCore;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceLocation;

public class JeiHeatTierHelper implements IIngredientHelper<DCHeatTier> {

	@Override
	public IIngredientType<DCHeatTier> getIngredientType() {
		return IngredientTypeDC.HEAT_TIER;
	}

	@Override
	public String getDisplayName(DCHeatTier ingredient) {
		return ingredient.name();
	}

	@Override
	public String getUniqueId(DCHeatTier ingredient, UidContext context) {
		return "HeatTier:" + ingredient.name();
	}

	@Override
	public ResourceLocation getResourceLocation(DCHeatTier ingredient) {
		return new ResourceLocation(ClimateCore.MOD_ID, "heattier/" + ingredient.toString());
	}

	@Override
	public String getDisplayModId(DCHeatTier ingredient) {
		return ClimateCore.MOD_ID;
	}

	@Override
	public Iterable<Integer> getColors(DCHeatTier ingredient) {
		List<Integer> colors = Lists.newArrayList();
		colors.add(ingredient.getColorInt());
		return colors;
	}

	@Override
	public DCHeatTier copyIngredient(DCHeatTier ingredient) {
		return ingredient;
	}

	@Override
	public String getErrorInfo(@Nullable DCHeatTier ingredient) {
		return ingredient == null ? "Empty" : ingredient.name();
	}

}
