package defeatedcrow.hac.core.util;

import defeatedcrow.hac.core.config.ConfigCommonBuilder;

public class DCDay {
	/* int上限でカンスト */
	public static int getDay(long day) {
		long d = day + ConfigCommonBuilder.INSTANCE.vStartDate.get() + 1;
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
		int year = ConfigCommonBuilder.INSTANCE.vYear.get();
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			year = 365;
		}
		int y = (day - 1) / year;
		return y + 1;
	}

	public static int getSeason(int day, boolean r) {
		int spr = ConfigCommonBuilder.INSTANCE.vStartSpr.get();
		int smr = ConfigCommonBuilder.INSTANCE.vStartSmr.get();
		int aut = ConfigCommonBuilder.INSTANCE.vStartAut.get();
		int wtr = ConfigCommonBuilder.INSTANCE.vStartWtr.get();
		int y = ConfigCommonBuilder.INSTANCE.vYear.get();
		int oy = ConfigCommonBuilder.INSTANCE.overYear.id;
		int season = 0;
		if (day >= spr) {
			int i1 = Math.abs(day - spr);
			int i2 = Math.abs(day - smr);
			if (oy == 0) {
				i2 = Math.abs(y - day + smr);
			}
			season = i1 < i2 ? 0 : 1;
		}
		if (day >= smr) {
			int i1 = Math.abs(day - smr);
			int i2 = Math.abs(day - aut);
			if (oy == 1) {
				i2 = Math.abs(y - day + aut);
			}
			season = i1 < i2 ? 2 : 3;
		}
		if (day >= aut) {
			int i1 = Math.abs(day - aut);
			int i2 = Math.abs(day - wtr);
			if (oy == 2) {
				i2 = Math.abs(y - day + wtr);
			}
			season = i1 < i2 ? 4 : 5;
		}
		if (day >= wtr) {
			int i1 = Math.abs(day - wtr);
			int i2 = Math.abs(day - spr);
			if (oy == 3) {
				i2 = Math.abs(y - day + spr);
			}
			season = i1 < i2 ? 6 : 7;
		}
		return season;
	}
}
