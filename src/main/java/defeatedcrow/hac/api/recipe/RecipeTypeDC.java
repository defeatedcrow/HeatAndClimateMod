package defeatedcrow.hac.api.recipe;

import com.google.common.collect.ImmutableList;

public enum RecipeTypeDC {
	PULVERISE,
	SQUEEZE,
	SIEVE,
	DISTILL,
	CATALYST,
	STERILIZE,
	ELECTROLYZE,
	COOKING,
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
			SQUEEZE,
			SIEVE,
			DISTILL,
			CATALYST,
			STERILIZE,
			ELECTROLYZE,
			COOKING,
			TEA);
	}
}
