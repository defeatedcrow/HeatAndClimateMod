package defeatedcrow.hac.api.recipe;

import com.google.common.collect.ImmutableList;

public enum FuelTypeDC {
	BIOMASS,
	THERMAL,
	FLUID,
	GAS;

	public static FuelTypeDC getType(String name) {
		if (name != null)
			for (FuelTypeDC t : FuelTypeDC.elements()) {
				if (t.name().equalsIgnoreCase(name)) {
					return t;
				}
			}
		return BIOMASS;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public static ImmutableList<FuelTypeDC> elements() {
		return ImmutableList.of(BIOMASS,
			THERMAL,
			FLUID,
			GAS);
	}
}
