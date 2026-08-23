package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.client.event.BlockHitEffectsEvent;
import defeatedcrow.hac.core.client.event.SoundMufflerEvent;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgWeatherToC implements IPacketDC {

	protected ResourceLocation dim;
	protected float rain;
	protected int rainCount;
	protected int sunCount;
	protected int season;
	protected int day;
	protected int dayI;
	protected int time;
	protected boolean muf;
	protected boolean digging;
	protected String date;

	public MsgWeatherToC() {}

	public MsgWeatherToC(ResourceLocation d, float r, int rC, int sC, int s, int dt, int di, int t, boolean m, boolean dig, String dp) {
		dim = d;
		rain = r;
		rainCount = rC;
		sunCount = sC;
		season = s;
		day = dt;
		dayI = di;
		time = t;
		muf = m;
		digging = dig;
		date = dp;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeResourceLocation(dim);
		buf.writeFloat(rain);
		buf.writeInt(rainCount);
		buf.writeInt(sunCount);
		buf.writeByte(season);
		buf.writeInt(day);
		buf.writeInt(dayI);
		buf.writeInt(time);
		buf.writeBoolean(muf);
		buf.writeBoolean(digging);
		buf.writeUtf(date);
	}

	public static MsgWeatherToC decode(FriendlyByteBuf buf) {
		ResourceLocation d = buf.readResourceLocation();
		float r = buf.readFloat();
		int rC = buf.readInt();
		int sC = buf.readInt();
		int s = buf.readByte();
		int dt = buf.readInt();
		int di = buf.readInt();
		int t = buf.readInt();
		boolean m = buf.readBoolean();
		boolean dig = buf.readBoolean();
		String dp = buf.readUtf();
		return new MsgWeatherToC(d, r, rC, sC, s, dt, di, t, m, dig, dp);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist == Dist.CLIENT && dim != null) {
			WeatherChecker.INSTANCE.setWeather(dim, rain, rainCount, sunCount);
			DCTimeHelper.setClientData(EnumSeason.getSeasonFromID(season), day, dayI, time, date);
			SoundMufflerEvent.setMuffler(muf);
			BlockHitEffectsEvent.setBooster(digging);
		}
	}

	public static void sendToClient(ServerLevel level, float r, int rC, int sC, int s, int dt, int di, int t, boolean muf, boolean dig, String disp) {
		if (level != null) {
			MsgWeatherToC packet = new MsgWeatherToC(level.dimension().location(), r, rC, sC, s, dt, di, t, muf, dig, disp);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
