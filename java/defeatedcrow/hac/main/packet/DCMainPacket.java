package defeatedcrow.hac.main.packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class DCMainPacket {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("dcs_climate_main");

	public static void init() {
		// INSTANCE.registerMessage(MHandlerCharmKey.class, MessageCharmKey.class, 0, Side.SERVER);
	}
}
