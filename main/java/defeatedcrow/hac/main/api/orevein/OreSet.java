package defeatedcrow.hac.main.api.orevein;

import defeatedcrow.hac.api.climate.BlockSet;

public abstract class OreSet {

	public abstract BlockSet getOre();

	public abstract BlockSet getSecondOre();

	public abstract int getWeight();

	public abstract int getSecondChance();

	public abstract boolean hasSecondOre();

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof OreSet) {
			OreSet p = (OreSet) obj;
			return p.getWeight() == getWeight() && p.getOre().equals(getOre()) && p.getSecondOre().equals(
					getSecondOre()) && p.getSecondChance() == getSecondChance();
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		int i1 = getOre().hashCode();
		int i2 = getSecondOre().hashCode();
		return getWeight() + i1 + i2 + getSecondChance() * 13;
	}

}
