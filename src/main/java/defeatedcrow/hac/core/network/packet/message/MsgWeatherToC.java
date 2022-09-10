package defeatedcrow.hac.core.network.packet.message;

import java.util.function.Supplier;

import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.PacketDistributor;

public class MsgWeatherToC implements IPacket<MsgWeatherToC> {

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
	public void encode(MsgWeatherToC message, FriendlyByteBuf buf) {
		buf.writeResourceLocation(message.dim);
		buf.writeFloat(message.rain);
		buf.writeInt(message.rainCount);
		buf.writeInt(message.sunCount);
		buf.writeByte(message.season);
	}

	@Override
	public MsgWeatherToC decode(FriendlyByteBuf buf) {
		ResourceLocation d = buf.readResourceLocation();
		float r = buf.readFloat();
		int rC = buf.readInt();
		int sC = buf.readInt();
		int s = buf.readByte();
		return new MsgWeatherToC(d, r, rC, sC, s);
	}

	@Override
	public NetworkDirection senderSide() {
		return NetworkDirection.PLAY_TO_CLIENT;
	}

	@Override
	public void handle(MsgWeatherToC message, Supplier<Context> ctx) {
		ctx.get().enqueueWork(() -> MsgWeatherHandler.handling(message));
		ctx.get().setPacketHandled(true);
	}

	public static void setdToClient(ResourceLocation d, float r, int rC, int sC, int s) {
		// DCLogger.debugInfoLog("dim: " + d.toString() + " / rain: " + r);
		if (d != null) {
			MsgWeatherToC packet = new MsgWeatherToC(d, r, rC, sC, s);
			DCPacket.INSTANCE.getChannel().send(PacketDistributor.ALL.noArg(), packet);
		}
	}

}
