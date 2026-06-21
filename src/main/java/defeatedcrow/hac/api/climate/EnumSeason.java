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
	ABSOLUTE(DyeColor.BLUE, 9, 5, false, "absolute"),
	FLOWER(DyeColor.PINK, 10, 6, false, "flower"),
	HARVEST(DyeColor.YELLOW, 11, 7, false, "harvest");

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
		return switch (i) {
		case 0 -> SPRING_EARLY;
		case 1 -> SPRING_LATE;
		case 2 -> SUMMER_EARLY;
		case 3 -> SUMMER_LATE;
		case 4 -> AUTUMN_EARLY;
		case 5 -> AUTUMN_LATE;
		case 6 -> WINTER_EARLY;
		case 7 -> WINTER_LATE;
		case 8 -> SCORCHER;
		case 9 -> ABSOLUTE;
		case 10 -> FLOWER;
		case 11 -> HARVEST;
		default -> SPRING_EARLY;
		};
	}

	// 通常季節のIDのみを返す (0~3)
	public int getSeasonLimitedID() {
		int i = season;
		if (i == 4)
			i = 1;
		if (i == 5)
			i = 3;
		if (i == 6)
			i = 4;
		if (i == 7)
			i = 5;
		return i;
	}

	public boolean isEarly() {
		return early;
	}

}
