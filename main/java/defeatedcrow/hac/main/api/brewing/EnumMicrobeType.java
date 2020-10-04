package defeatedcrow.hac.main.api.brewing;

import net.minecraft.client.resources.I18n;

public enum EnumMicrobeType {
	ARCHAEA,
	BACILLI,
	COCCI,
	YEAST,
	FUNGI;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}

	public String localize() {
		return I18n.format("dcs.tip." + this.name().toLowerCase());
	}

}
