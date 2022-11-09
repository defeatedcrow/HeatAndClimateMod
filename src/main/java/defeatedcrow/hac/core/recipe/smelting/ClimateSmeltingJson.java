package defeatedcrow.hac.core.recipe.smelting;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.json.IngPair;
import net.minecraft.world.item.ItemStack;

public class ClimateSmeltingJson {

	private final IngPair input;
	private final IngPair output;

	private final int frequency;
	private final String heat;
	private final String hum;
	private final String air;

	public ClimateSmeltingJson(IngPair o, DCHeatTier t, DCHumidity h, DCAirflow a, int freq,
			IngPair i) {
		input = i;
		output = o;
		heat = t == null ? "ANY" : t.name();
		hum = h == null ? "ANY" : h.name();
		air = a == null ? "ANY" : a.name();
		frequency = freq;
	}

	public ClimateSmelting toRecipe() {
		Object in = input.getItemObject();
		ItemStack out = output.getItem();
		return new ClimateSmelting(out, DCHeatTier.getFromNameOrNull(heat), DCHumidity.getFromNameOrNull(hum), DCAirflow.getFromNameOrNull(air), frequency, in);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClimateSmeltingJson))
			return false;
		ClimateSmeltingJson target = (ClimateSmeltingJson) obj;
		return target.output.equals(output) && target.input.equals(input);
	}

}
