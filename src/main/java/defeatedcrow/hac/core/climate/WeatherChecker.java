package defeatedcrow.hac.core.climate;

import java.util.HashMap;
import java.util.Map;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.network.packet.message.MsgWeatherToC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WeatherChecker {
	public static final WeatherChecker INSTANCE = new WeatherChecker();

	private WeatherChecker() {}

	public static final Map<ResourceLocation, Float> rainPowerMap = new HashMap<ResourceLocation, Float>();

	public static final Map<ResourceLocation, Integer> rainCountMap = new HashMap<ResourceLocation, Integer>();

	public static final Map<ResourceLocation, Integer> sunCountMap = new HashMap<ResourceLocation, Integer>();

	private static final int drought = 30;

	private static int lastDay = 0;

	/* サーバーサイドでのみ季節・天候チェックを行う */
	public static void setWeather(Level world) {
		if (world == null || world.isClientSide) {
			return;
		}
		ResourceKey<Level> dim = world.dimension();
		ResourceLocation dimName = dim.location();
		float rain = world.getRainLevel(1.0F);
		int sunTime = sunCountMap.getOrDefault(dimName, 0);
		int rainTime = rainCountMap.getOrDefault(dimName, 0);
		EnumSeason season = DCTimeHelper.getSeasonEnum(world);
		int day = DCTimeHelper.getDay(world);
		int dayD = DCTimeHelper.getDisplayDay(world);
		int time = DCTimeHelper.currentTime(world);
		String date = DCTimeHelper.getDate(world);
		boolean flag = false;
		if (day != lastDay) {
			lastDay = day;
			flag = true;
		}

		// DCLogger.debugLog("=current weather info=");
		// DCLogger.debugLog("dim:" + dim.registry() + " " + dimName);
		// DCLogger.debugLog("rain:" + rain + " time:" + rainTime);
		// DCLogger.debugLog("thunder:" + world.thunderLevel);
		// DCLogger.debugLog("sun time:" + sunTime);

		rainPowerMap.put(dimName, rain);
		boolean r = rain > 0.25F;

		if (r) {
			if (flag) {
				rainTime++;
				rainCountMap.put(dimName, rainTime);
			}
			if (sunCountMap.getOrDefault(dimName, 0) != 0)
				sunCountMap.put(dimName, 0);
			// DCLogger.debugLog("dim " + dim.registry() + " raining");
		} else if (drought > 0) {
			if (flag) {
				sunTime++;
				if (sunTime > drought) {
					// DCLogger.debugLog("dim " + dim.registry() + " drought");
				}
				if (sunTime > drought * 2) {
					sunTime = DCUtil.rand.nextInt(sunTime);
				}
				sunCountMap.put(dimName, sunTime);
			}
			if (rainCountMap.getOrDefault(dimName, 0) != 0)
				rainCountMap.put(dimName, 0);
		}

		MsgWeatherToC.sendToClient(dim.location(), rain, rainTime, sunTime, season.id, day, dayD, time, date);
	}

	@OnlyIn(Dist.CLIENT)
	public static void setWeather(ResourceLocation dim, float rain, int countR, int countS) {
		rainPowerMap.put(dim, rain);
		rainCountMap.put(dim, countR);
		sunCountMap.put(dim, countS);
		// DCLogger.debugLog("dim " + dim + "/ received data: " + rain + "/ " + countR + ", " + countS);
	}

	public static float getTempOffsetFloat(ResourceLocation dim, boolean isHell) {
		int count = 0;
		int sun = 0;
		float rain = 0F;
		if (rainPowerMap.containsKey(dim)) {
			rain = rainPowerMap.get(dim);
		}
		if (rainCountMap.containsKey(dim)) {
			count = rainCountMap.get(dim);
		}
		if (sunCountMap.containsKey(dim)) {
			sun = sunCountMap.get(dim);
		}
		if (drought > 0 && sun > drought && !isHell) {
			// 日照り気味
			return -1F * ConfigCommonBuilder.INSTANCE.vWeatherRain.get().floatValue();
		}
		if (count > 0 && rain > 0.25F) {
			return isHell ? -1F * ConfigCommonBuilder.INSTANCE.vWeatherRain.get().floatValue() : ConfigCommonBuilder.INSTANCE.vWeatherRain.get().floatValue();
		}
		if (rain > 0.85F) {
			return isHell ? -1F * ConfigCommonBuilder.INSTANCE.vWeatherRain.get().floatValue() : ConfigCommonBuilder.INSTANCE.vWeatherRain.get().floatValue();
		}

		return 0;
	}

	public static int getTempOffset(ResourceLocation dim, boolean isHell) {
		int count = 0;
		int sun = 0;
		float rain = 0F;
		if (rainPowerMap.containsKey(dim)) {
			rain = rainPowerMap.get(dim);
		}
		if (rainCountMap.containsKey(dim)) {
			count = rainCountMap.get(dim);
		}
		if (sunCountMap.containsKey(dim)) {
			sun = sunCountMap.get(dim);
		}
		if (drought > 0 && sun > drought && !isHell) {
			// 日照り気味
			return 1;
		}
		if (count > 0 && rain > 0.25F) {
			return isHell ? 1 : -1;
		}
		if (rain > 0.85F) {
			return isHell ? 1 : -1;
		}

		return 0;
	}

	public static int getHumOffset(ResourceLocation dim, boolean isHell) {
		int count = 0;
		float rain = 0F;
		if (rainPowerMap.containsKey(dim)) {
			rain = rainPowerMap.get(dim);
		}
		if (rainCountMap.containsKey(dim)) {
			count = rainCountMap.get(dim);
		}

		if (rain > 0.25F) {
			return isHell ? 0 : 1;
		}

		return 0;
	}

	public static int getWindOffset(ResourceLocation dim, boolean isHell) {
		int count = 0;
		float rain = 0F;
		if (rainPowerMap.containsKey(dim)) {
			rain = rainPowerMap.get(dim);
		}
		if (rainCountMap.containsKey(dim)) {
			count = rainCountMap.get(dim);
		}

		if (rain > 0.25F) {
			return 1;
		}

		return 0;
	}
}
