package defeatedcrow.hac.main.api.brewing;

import net.minecraft.client.resources.I18n;

public enum EnumMedium {
	SIMPLE(0),
	STANDARD(1),
	POTATO(2),
	GIBLETS(3),
	BACTERIA(4);

	public final int id;

	private EnumMedium(int i) {
		id = i;
	}

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}

	public String localize() {
		return I18n.format("dcs.tip." + this.name().toLowerCase());
	}
}
