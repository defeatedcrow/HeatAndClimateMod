package defeatedcrow.hac.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.event.GetSeasonEvent;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags.Biomes;

public class DCTimeHelper {

	private DCTimeHelper() {}

	public static long time(Level world) {
		return world.getGameTime() % 24000L;
	}

	public static long totalTime(Level world) {
		return world.getGameTime();
	}

	public static boolean isDayTime(Level world) {
		int t = currentTime(world);
		return t >= 6 && t <= 17;
	}

	public static int currentTime(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(cal.HOUR_OF_DAY);
			return hour;
		}
		long time = time(world);
		time += 6000;
		if (time > 24000)
			time -= 24000;
		return (int) (time / 1000);
	}

	public static int realMinute() {
		Calendar cal = Calendar.getInstance();
		int min = cal.get(cal.MINUTE);
		return min;
	}

	public static int realSecond() {
		Calendar cal = Calendar.getInstance();
		int sec = cal.get(cal.SECOND);
		return sec;
	}

	public static int currentMinute(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			int min = cal.get(cal.MINUTE);
			return min;
		}
		long time = time(world);
		return (int) (time % 1000) * 60 / 1000;
	}

	public static int getCount(Level world) {
		long i = (totalTime(world) % ConfigCommonBuilder.INSTANCE.vUpdateInterval.get());
		return (int) i;
	}

	public static int getCount2(Level world) {
		long f = 1200L / 5;
		long i = (totalTime(world) % f);
		return (int) i;
	}

	// 表示用
	public static String getDate(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			SimpleDateFormat format = new SimpleDateFormat(ConfigCommonBuilder.INSTANCE.dateFormat);
			String s = format.format(date);
			return s;
		} else {
			int displayDay = getDisplayDay(world);
			int day = getDay(world);
			if (day > ConfigCommonBuilder.INSTANCE.vYear.get()) {
				day %= ConfigCommonBuilder.INSTANCE.vYear.get();
			}
			int year = getYear(world);
			return "Year" + year + " / Day" + day;
		}
	}

	public static int getDay(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			return cal.get(cal.DAY_OF_YEAR);
		}
		long day = totalTime(world) / 24000L;
		return DCDay.getDay(day);
	}

	public static int getDisplayDay(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			return cal.get(cal.DAY_OF_YEAR);
		}
		long day = totalTime(world) / 24000L;
		return DCDay.getDisplayDay(day);
	}

	public static int getWeek(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			return cal.get(cal.DAY_OF_WEEK);
		}
		int day = getDay(world);
		return DCDay.getWeek(day);
	}

	public static int getYear(Level world) {
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			return cal.get(cal.YEAR);
		}
		int day = getDay(world);
		return DCDay.getYear(day);
	}

	private static int getSeason(Level world, @Nullable BlockPos pos) {
		// debug
		if (currentSeason != null) {
			return currentSeason.id;
		}

		int season = 0;
		int d = 0;
		if (ConfigCommonBuilder.INSTANCE.enRealTime.get()) {
			Calendar cal = Calendar.getInstance();
			d = cal.get(cal.DAY_OF_YEAR);
			return DCDay.getSeason(d, true);
		} else {
			d = getDay(world);
			return DCDay.getSeason(d, false);
		}
	}

	public static EnumSeason getSeasonEnum(Level world, @Nullable BlockPos pos) {

		int s = getSeason(world, pos);
		EnumSeason current = EnumSeason.getSeasonFromID(s);

		GetSeasonEvent event = new GetSeasonEvent(world, pos, current);
		return event.result();
	}

	public static float getTimeOffset(Level world, Holder<Biome> b) {
		if (world.dimensionType().hasFixedTime()) {
			return 0F;
		}
		float offset = 0F;
		int t = DCTimeHelper.currentTime(world);
		int sD = 6;
		int eD = 17;
		if (t < sD - 2 || t > eD + 2) {
			offset = ConfigCommonBuilder.INSTANCE.vNight.get().floatValue();
		} else if (t < sD || t > eD) {
			offset = ConfigCommonBuilder.INSTANCE.vNight.get().floatValue() * 0.5F;
		}
		if (b.is(Biomes.IS_WATER) || b.is(Biomes.IS_WET)) {
			offset *= 0.5F;
		} else if (b.is(Biomes.IS_DRY) || b.is(Biomes.IS_SANDY) || b.is(Biomes.IS_SPARSE)) {
			if (b.is(Biomes.IS_DRY_OVERWORLD) && b.is(Biomes.IS_HOT))
				offset *= 4F;
			else
				offset *= 2F;
		}
		return offset;
	}

	public static EnumSeason currentSeason = EnumSeason.SPRING_LATE;

}
