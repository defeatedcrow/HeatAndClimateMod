package defeatedcrow.hac.core.network.packet;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

/**
 * This source code is based on MrClayfish's code.
 * https://github.com/MrCrayfish/MrCrayfishFurnitureMod/tree/1.19.X
 *
 * @Date 2022.9.5
 * @Author defeatedcrow
 */
public interface IPacket<T> {

	void encode(T message, FriendlyByteBuf buf);

	T decode(FriendlyByteBuf buf);

	NetworkDirection senderSide();

	void handle(T message, Supplier<NetworkEvent.Context> ctx);

	static <T extends IPacket<T>> void callServerConsumer(T t, Supplier<NetworkEvent.Context> supplier,
			BiConsumer<ServerPlayer, T> consumer) {
		ServerPlayer player = supplier.get().getSender();
		if (player != null) {
			consumer.accept(player, t);
		}
	}

}
