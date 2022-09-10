package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.core.climate.WeatherChecker;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class MsgWeatherHandler {

	protected static void handling(MsgWeatherToC msg) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			ResourceLocation d = msg.dim;
			float r = msg.rain;
			int rC = msg.rainCount;
			int sC = msg.sunCount;
			int s = msg.season;
			WeatherChecker.INSTANCE.setWeather(d, r, sC, s, s);
		}
	}

}
