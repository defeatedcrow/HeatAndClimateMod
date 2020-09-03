package defeatedcrow.hac.main.api.brewing;

public enum EnumMicrobeType {
	BACILLI,
	YEAST,
	MOLD;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}

}
