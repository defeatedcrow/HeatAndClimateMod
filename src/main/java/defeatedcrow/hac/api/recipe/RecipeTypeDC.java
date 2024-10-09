package defeatedcrow.hac.api.recipe;

import com.google.common.collect.ImmutableList;

public enum RecipeTypeDC {
	PULVERISE,
	FOOD_MILL,
	SQUEEZE,
	SIEVE,
	CHEM_DISTILL,
	CHEM_CATALYST,
	CHEM_ELECTROLYZE,
	FERMENTATION,
	COOKING,
	FRYING,
	TEA;

	public static RecipeTypeDC getType(String name) {
		if (name != null)
			for (RecipeTypeDC t : RecipeTypeDC.elements()) {
				if (t.name().equalsIgnoreCase(name)) {
					return t;
				}
			}
		return COOKING;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public static ImmutableList<RecipeTypeDC> elements() {
		return ImmutableList.of(PULVERISE,
				FOOD_MILL,
				SQUEEZE,
				SIEVE,
				CHEM_DISTILL,
				CHEM_CATALYST,
				CHEM_ELECTROLYZE,
				FERMENTATION,
				COOKING,
				FRYING,
				TEA);
	}
}
