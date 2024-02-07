package defeatedcrow.hac.core.climate;

import defeatedcrow.hac.core.config.ConfigServerBuilder;

public class DCDay {
	/* int上限でカンスト */
	public static int getDay(long day) {
		long d = day + ConfigServerBuilder.INSTANCE.vStartDate.get() + 1;
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
		int year = ConfigServerBuilder.INSTANCE.vYear.get();
		if (ConfigServerBuilder.INSTANCE.enRealTime.get()) {
			year = 365;
		}
		int y = (day - 1) / year;
		return y + 1;
	}

	public static int getSeason(int day, boolean r) {
		int spr = ConfigServerBuilder.INSTANCE.vStartSpr.get();
		int smr = ConfigServerBuilder.INSTANCE.vStartSmr.get();
		int aut = ConfigServerBuilder.INSTANCE.vStartAut.get();
		int wtr = ConfigServerBuilder.INSTANCE.vStartWtr.get();
		int y = ConfigServerBuilder.INSTANCE.vYear.get();
		int oy = ConfigServerBuilder.INSTANCE.vOverYear.get();
		int season = -1;

		int year = ConfigServerBuilder.INSTANCE.vYear.get();
		if (ConfigServerBuilder.INSTANCE.enRealTime.get()) {
			year = 365;
		}
		day %= year;

		if (day >= spr) {
			int i1 = Math.abs(day - spr);
			int i2 = Math.abs(smr - day);
			if (oy == 0) {
				i2 = Math.abs(y - day + spr);
			}
			season = i1 < i2 ? 0 : 1;
		}
		if (day >= smr) {
			int i1 = Math.abs(day - smr);
			int i2 = Math.abs(aut - day);
			if (oy == 1) {
				i2 = Math.abs(y - day + smr);
			}
			season = i1 < i2 ? 2 : 3;
		}
		if (day >= aut) {
			int i1 = Math.abs(day - aut);
			int i2 = Math.abs(wtr - day);
			if (oy == 2) {
				i2 = Math.abs(y - day + aut);
			}
			season = i1 < i2 ? 4 : 5;
		}
		if (day >= wtr) {
			int i1 = Math.abs(day - wtr);
			int i2 = Math.abs(spr - day);
			if (oy == 3) {
				i2 = Math.abs(y - day + wtr);
			}
			season = i1 < i2 ? 6 : 7;
		}
		if (season < 0) {
			if (oy == 0) {
				int i1 = Math.abs(y + day - spr);
				int i2 = Math.abs(smr - day);
				season = i1 < i2 ? 0 : 1;
			} else if (oy == 1) {
				int i1 = Math.abs(y + day - smr);
				int i2 = Math.abs(aut - day);
				season = i1 < i2 ? 2 : 3;
			} else if (oy == 2) {
				int i1 = Math.abs(y + day - aut);
				int i2 = Math.abs(wtr - day);
				season = i1 < i2 ? 4 : 5;
			} else if (oy == 3) {
				int i1 = Math.abs(y + day - wtr);
				int i2 = Math.abs(spr - day);
				season = i1 < i2 ? 6 : 7;
			} else {
				season = 0;
			}
		}
		return season;
	}
}
