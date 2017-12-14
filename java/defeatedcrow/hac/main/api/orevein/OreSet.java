package defeatedcrow.hac.main.api.orevein;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.BlockSet;

public class OreSet {

	private final int weight;
	private final BlockSet ore;
	private final BlockSet ore2;
	private final boolean hasSec;
	private final int probability;

	public OreSet(int weightIn, @Nonnull BlockSet oreIn, @Nullable BlockSet secondOreIn, int chanceIn) {
		weight = weightIn;
		ore = oreIn;
		ore2 = secondOreIn;
		hasSec = (secondOreIn != null);
		probability = chanceIn;
	}

	public OreSet(int weightIn, @Nonnull BlockSet oreIn) {
		weight = weightIn;
		ore = oreIn;
		ore2 = null;
		hasSec = false;
		probability = 0;
	}

	public BlockSet getOre() {
		return ore;
	}

	public BlockSet getSecondOre() {
		return ore2;
	}

	public int getWeight() {
		return weight;
	}

	public int getSecondChance() {
		return probability;
	}

	public boolean hasSecondOre() {
		return hasSec;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof OreSet) {
			OreSet p = (OreSet) obj;
			return p.weight == weight && p.ore.equals(ore) && p.ore2.equals(ore2) && p.probability == probability;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		int i1 = ore.hashCode();
		int i2 = ore2.hashCode();
		return weight + i1 + i2 + probability * 13;
	}

}
