package defeatedcrow.hac.core.network.packet;

import java.util.Optional;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.network.packet.message.MsgWeatherToC;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * This source code is based on MrClayfish's code.
 * https://github.com/MrCrayfish/MrCrayfishFurnitureMod/tree/1.19.X
 *
 * @Date 2022.9.5
 * @Author defeatedcrow
 */
public class DCPacket {

	public static final String PROTOCOL_VERSION = "1";
	private static SimpleChannel channel;
	private static int id = 0;

	private DCPacket() {
		channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ClimateCore.MOD_ID,
				"core")).networkProtocolVersion(() -> PROTOCOL_VERSION).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).simpleChannel();
	}

	public void init() {
		register(MsgWeatherToC.class, new MsgWeatherToC());
	}

	private static <T> void register(Class<T> clazz, IPacket<T> message) {
		channel.registerMessage(id++, clazz, message::encode, message::decode, message::handle, Optional.ofNullable(message.senderSide()));
	}

	public static final DCPacket INSTANCE = new DCPacket();

	public SimpleChannel getChannel() {
		return channel;
	}

}
