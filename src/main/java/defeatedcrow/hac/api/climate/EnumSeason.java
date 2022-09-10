package defeatedcrow.hac.api.climate;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.DyeColor;

public enum EnumSeason {

	SPRING(DyeColor.PINK, 0, "dcs.tip.spring"),
	SUMMER(DyeColor.LIME, 1, "dcs.tip.summer"),
	AUTUMN(DyeColor.ORANGE, 2, "dcs.tip.autumn"),
	WINTER(DyeColor.LIGHT_BLUE, 3, "dcs.tip.winter");

	public final DyeColor color;
	public final int id;
	public final String name;

	private EnumSeason(DyeColor c, int i, String n) {
		color = c;
		id = i;
		name = n;
	}

	public String getName() {
		return I18n.get(name);
	}

	public static EnumSeason getSeasonFromID(int i) {
		if (i == 1)
			return SUMMER;
		else if (i == 2)
			return AUTUMN;
		else if (i == 3)
			return WINTER;
		else
			return SPRING;
	}

}
