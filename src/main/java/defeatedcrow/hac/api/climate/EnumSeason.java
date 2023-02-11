package defeatedcrow.hac.api.climate;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.DyeColor;

public enum EnumSeason {

	SPRING_EARLY(DyeColor.PINK, 0, 0, true, "spring"),
	SPRING_LATE(DyeColor.PINK, 1, 0, false, "spring"),
	SUMMER_EARLY(DyeColor.LIME, 2, 1, true, "summer"),
	SUMMER_LATE(DyeColor.LIME, 3, 1, false, "summer"),
	AUTUMN_EARLY(DyeColor.ORANGE, 4, 2, true, "autumn"),
	AUTUMN_LATE(DyeColor.ORANGE, 5, 2, false, "autumn"),
	WINTER_EARLY(DyeColor.LIGHT_BLUE, 6, 3, true, "winter"),
	WINTER_LATE(DyeColor.LIGHT_BLUE, 7, 3, false, "winter"),
	SCORCHER(DyeColor.RED, 8, 4, false, "scorcher"),
	ARCTIC(DyeColor.BLUE, 9, 5, false, "arctic");

	public final DyeColor color;
	public final int id;
	public final int season;
	public final String seasonName;
	public final boolean early;

	private EnumSeason(DyeColor c, int i, int s, boolean e, String n) {
		color = c;
		id = i;
		season = s;
		early = e;
		seasonName = n;
	}

	public MutableComponent getName() {
		return Component.translatable("dcs.enum." + seasonName);
	}

	public MutableComponent getFullname() {
		return Component.translatable("dcs.enum." + toString());
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public static EnumSeason getSeasonFromID(int i) {
		switch (i) {
		case 0:
			return SPRING_EARLY;
		case 1:
			return SPRING_LATE;
		case 2:
			return SUMMER_EARLY;
		case 3:
			return SUMMER_LATE;
		case 4:
			return AUTUMN_EARLY;
		case 5:
			return AUTUMN_LATE;
		case 6:
			return WINTER_EARLY;
		case 7:
			return WINTER_LATE;
		case 8:
			return SCORCHER;
		case 9:
			return ARCTIC;
		default:
			return SPRING_EARLY;
		}
	}

	// 通常季節のIDのみを返す (0~3)
	public int getSeasonLimitedID() {
		int i = season;
		if (i == 4)
			i = 1;
		if (i == 5)
			i = 3;
		return i;
	}

	public boolean isEarly() {
		return early;
	}

}
