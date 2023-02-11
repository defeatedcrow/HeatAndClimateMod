package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public class MsgWeatherToC implements IPacketDC {

	protected ResourceLocation dim;
	protected float rain;
	protected int rainCount;
	protected int sunCount;
	protected int season;

	public MsgWeatherToC() {}

	public MsgWeatherToC(ResourceLocation d, float r, int rC, int sC, int s) {
		dim = d;
		rain = r;
		rainCount = rC;
		sunCount = sC;
		season = s;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeResourceLocation(dim);
		buf.writeFloat(rain);
		buf.writeInt(rainCount);
		buf.writeInt(sunCount);
		buf.writeByte(season);
	}

	public static MsgWeatherToC decode(FriendlyByteBuf buf) {
		ResourceLocation d = buf.readResourceLocation();
		float r = buf.readFloat();
		int rC = buf.readInt();
		int sC = buf.readInt();
		int s = buf.readByte();
		return new MsgWeatherToC(d, r, rC, sC, s);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			ResourceLocation d = dim;
			float r = rain;
			int rC = rainCount;
			int sC = sunCount;
			int s = season;
			WeatherChecker.INSTANCE.setWeather(d, r, sC, s, s);
		}
	}

	public static void setdToClient(ResourceLocation d, float r, int rC, int sC, int s) {
		// DCLogger.debugInfoLog("dim: " + d.toString() + " / rain: " + r);
		if (d != null) {
			MsgWeatherToC packet = new MsgWeatherToC(d, r, rC, sC, s);
			DCPacket.INSTANCE.getChannel().send(PacketDistributor.ALL.noArg(), packet);
		}
	}

}
