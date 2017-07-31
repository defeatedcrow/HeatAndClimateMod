package defeatedcrow.hac.main.packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class DCMainPacket {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("dcs_main_climate");

	public static void init() {
		INSTANCE.registerMessage(MHandlerReactorButton.class, MessageReactorButton.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MHandlerBiomeGlass.class, MessageBiomeGlass.class, 1, Side.CLIENT);
	}
}
