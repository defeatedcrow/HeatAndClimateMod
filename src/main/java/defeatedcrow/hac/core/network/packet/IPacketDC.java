package defeatedcrow.hac.core.network.packet;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public interface IPacketDC {

	void handle(NetworkEvent.Context ctx);

	void encode(FriendlyByteBuf buf);

	static <PACKET extends IPacketDC> void handle(PACKET message, Supplier<NetworkEvent.Context> ctx) {
		if (message != null) {
			ctx.get().enqueueWork(() -> message.handle(ctx.get()));
			ctx.get().setPacketHandled(true);
		}
	}

}
