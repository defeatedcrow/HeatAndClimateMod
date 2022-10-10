package defeatedcrow.hac.plugin.jei.ingredients;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import mezz.jei.api.ingredients.IIngredientType;

public class IngredientTypeDC {

	public static final IIngredientType<DCHeatTier> HEAT_TIER = new IIngredientType<>() {
		@Override
		public Class<? extends DCHeatTier> getIngredientClass() {
			return DCHeatTier.class;
		}
	};

	public static final IIngredientType<DCHumidity> HUMIDITY = new IIngredientType<>() {
		@Override
		public Class<? extends DCHumidity> getIngredientClass() {
			return DCHumidity.class;
		}
	};

	public static final IIngredientType<DCAirflow> AIRFLOW = new IIngredientType<>() {
		@Override
		public Class<? extends DCAirflow> getIngredientClass() {
			return DCAirflow.class;
		}
	};

	private IngredientTypeDC() {}

}
