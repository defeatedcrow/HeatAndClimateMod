package defeatedcrow.hac.core.network.packet;

import java.util.Optional;
import java.util.function.Function;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.network.packet.message.MsgCharmKeyToS;
import defeatedcrow.hac.core.network.packet.message.MsgTileClimateToC;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.network.packet.message.MsgWeatherToC;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class DCPacket {

	public static final String PROTOCOL_VERSION = "4.0.0";

	private static SimpleChannel createChannel() {
		return NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ClimateCore.MOD_ID, "core"))
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.simpleChannel();
	}

	private static int id = 0;

	private DCPacket() {}

	public void init() {
		registerStoC(MsgWeatherToC.class, MsgWeatherToC::decode);
		registerCtoS(MsgCharmKeyToS.class, MsgCharmKeyToS::decode);
		registerCtoS(MsgTileOwnerKeyToS.class, MsgTileOwnerKeyToS::decode);
		registerStoC(MsgTileSimpleIntegerToC.class, MsgTileSimpleIntegerToC::decode);
		registerStoC(MsgTileClimateToC.class, MsgTileClimateToC::decode);
	}

	private static <MSG extends IPacketDC> void registerStoC(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> decoder) {
		channel.registerMessage(id++, clazz, IPacketDC::encode, decoder, IPacketDC::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
	}

	private static <MSG extends IPacketDC> void registerCtoS(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> decoder) {
		channel.registerMessage(id++, clazz, IPacketDC::encode, decoder, IPacketDC::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
	}

	public static final DCPacket INSTANCE = new DCPacket();
	private static final SimpleChannel channel = createChannel();

	public SimpleChannel getChannel() {
		return channel;
	}

}
