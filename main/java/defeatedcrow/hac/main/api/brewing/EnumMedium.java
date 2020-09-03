package defeatedcrow.hac.main.api.brewing;

public enum EnumMedium {
	SIMPLE,
	STANDARD,
	GIBLETS,
	POTATO,
	BACTERIA;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
