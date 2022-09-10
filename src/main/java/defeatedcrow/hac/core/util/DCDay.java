package defeatedcrow.hac.core.util;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.config.CoreConfigDC;
import net.minecraft.util.Mth;

public class DCDay {
	/* int上限でカンスト */
	public static int getDay(long day) {
		long d = day + CoreConfigDC.startDate + 1;
		if (d > Integer.MAX_VALUE) {
			d %= Integer.MAX_VALUE;
		}
		return (int) d;
	}

	public static int getDisplayDay(long day) {
		long d = day + 1;
		if (d > Integer.MAX_VALUE) {
			d %= Integer.MAX_VALUE;
		}
		return (int) d;
	}

	public static int getWeek(int day) {
		int week = (day - 1) % 7;
		return week;
	}

	public static int getYear(int day) {
		int y = (day - 1) / CoreConfigDC.yearLength;
		return y + 1;
	}

	public static int getSeason(int day, boolean r) {
		int season = 0;
		int d = day;
		if (!r) {
			double d1 = day * (365D / CoreConfigDC.yearLength);
			d = Mth.floor(d1);
			d = d % 365;
		}
		for (EnumSeason s : EnumSeason.values()) {
			if (s == CoreConfigDC.overYear && seasonPeriod(s)[0] > seasonPeriod(s)[1]) {
				if (d <= seasonPeriod(s)[0] || d >= seasonPeriod(s)[1]) {
					season = s.id;
					break;
				}
			} else {
				if (d >= seasonPeriod(s)[0] && d <= seasonPeriod(s)[1]) {
					season = s.id;
					break;
				}
			}
		}
		return season;
	}

	public static int[] seasonPeriod(EnumSeason season) {
		switch (season) {
		case AUTUMN:
			return CoreConfigDC.autumnDate;
		case SPRING:
			return CoreConfigDC.springDate;
		case SUMMER:
			return CoreConfigDC.summerDate;
		case WINTER:
			return CoreConfigDC.winterDate;
		default:
			return CoreConfigDC.springDate;
		}
	}
}
