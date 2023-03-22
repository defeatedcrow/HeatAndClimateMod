package defeatedcrow.hac.core.recipe.smelting;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.json.IngPair;
import net.minecraft.world.item.ItemStack;

public class ClimateSmeltingJson {

	private final IngPair input;
	private final IngPair output;

	private final int frequency;
	private final List<String> heats = new ArrayList<>();
	private final List<String> hums = new ArrayList<>();
	private final List<String> airs = new ArrayList<>();

	private final boolean active;

	public ClimateSmeltingJson(boolean act, IngPair o, DCHeatTier t, DCHumidity h, DCAirflow a, int freq,
			IngPair i) {
		active = act;
		input = i;
		output = o;
		if (t == null) {
			heats.add("ANY");
		} else {
			heats.add(t.name());
			if (t.getID() < DCHeatTier.INFERNO.getID()) {
				if (t.getID() == DCHeatTier.NORMAL.getID() || t.getID() == DCHeatTier.WARM.getID()) {
					heats.add(t.addTier(1).name());
					heats.add(t.addTier(-1).name());
				} else if (t.getID() > DCHeatTier.ABSOLUTE.getID() && t.getID() < DCHeatTier.NORMAL.getID()) {
					heats.add(t.addTier(-1).name());
				} else if (t.getID() > DCHeatTier.WARM.getID() && t.getID() < DCHeatTier.INFERNO.getID()) {
					heats.add(t.addTier(1).name());
				}
			}
		}
		if (h == null) {
			hums.add("ANY");
		} else {
			hums.add(h.name());
		}
		if (a == null) {
			airs.add("ANY");
		} else {
			airs.add(a.name());
		}
		frequency = freq;
	}

	public ClimateSmeltingJson(boolean act, IngPair o, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, int freq,
			IngPair i) {
		active = act;
		input = i;
		output = o;
		if (t == null || t.isEmpty()) {
			heats.add("ANY");
		} else {
			t.stream().forEach(temp -> heats.add(temp.name()));
		}
		if (h == null || h.isEmpty()) {
			hums.add("ANY");
		} else {
			h.stream().forEach(hum -> hums.add(hum.name()));
		}
		if (a == null || a.isEmpty()) {
			airs.add("ANY");
		} else {
			a.stream().forEach(air -> airs.add(air.name()));
		}
		frequency = freq;
	}

	public ClimateSmelting toRecipe() {
		Object in = input.getItemObject();
		ItemStack out = output.getItem();
		return new ClimateSmelting(active, out, DCHeatTier.getListFromName(heats), DCHumidity.getListFromName(hums), DCAirflow.getListFromName(airs), frequency, in);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClimateSmeltingJson))
			return false;
		ClimateSmeltingJson target = (ClimateSmeltingJson) obj;
		return target.output.equals(output) && target.input.equals(input);
	}

}
