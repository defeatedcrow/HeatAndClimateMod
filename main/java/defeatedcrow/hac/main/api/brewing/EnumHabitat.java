package defeatedcrow.hac.main.api.brewing;

public enum EnumHabitat {
	SOIL,
	FLOWER,
	CROPS,
	ANIMAL,
	WATER;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
