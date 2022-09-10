package defeatedcrow.hac.core.climate;

import java.util.HashMap;
import java.util.Map;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.network.packet.message.MsgWeatherToC;
import defeatedcrow.hac.core.util.DCTimeHelper;
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

	private static final int drought = CoreConfigDC.droughtFrequency;

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
		int season = DCTimeHelper.getSeason(world);
		int day = DCTimeHelper.getDay(world);
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

		DCTimeHelper.currentSeason = EnumSeason.getSeasonFromID(season);

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

		EnumSeason seasonEnum = DCTimeHelper.getSeasonEnum(world);
		MsgWeatherToC.setdToClient(dim.location(), rain, rainTime, sunTime, seasonEnum.id);
	}

	@OnlyIn(Dist.CLIENT)
	public static void setWeather(ResourceLocation dim, float rain, int countR, int countS, int season) {
		rainPowerMap.put(dim, rain);
		rainCountMap.put(dim, countR);
		sunCountMap.put(dim, countS);
		DCTimeHelper.currentSeason = EnumSeason.getSeasonFromID(season);
		// DCLogger.debugLog("dim " + dim + "/ received data: " + rain + "/ " + countR + ", " + countS);
	}

	public static float getTempOffsetFloat(ResourceLocation dim, boolean isHell) {
		if (!CoreConfigDC.enableWeatherEffect) {
			return 0;
		}
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
			return (float) CoreConfigDC.weatherEffects[1];
		}
		if (count > 0 && rain > 0.25F) {
			return (float) (isHell ? CoreConfigDC.weatherEffects[1] : CoreConfigDC.weatherEffects[0]);
		}
		if (rain > 0.85F) {
			return (float) (isHell ? CoreConfigDC.weatherEffects[1] : CoreConfigDC.weatherEffects[0]);
		}

		return 0;
	}

	public static int getTempOffset(ResourceLocation dim, boolean isHell) {
		if (!CoreConfigDC.enableWeatherEffect) {
			return 0;
		}
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
		if (!CoreConfigDC.enableWeatherEffect) {
			return 0;
		}
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
		if (!CoreConfigDC.enableWeatherEffect) {
			return 0;
		}
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
