package defeatedcrow.hac.main.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerMagicWarp implements IMessageHandler<MessageMagicWarp, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageMagicWarp message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		if (player != null) {
			double x1 = message.x;
			double y1 = message.y;
			double z1 = message.z;
			float yaw = message.face;

			player.rotationYaw = yaw;
			player.setPositionAndUpdate(x1, y1, z1);
			player.fallDistance = 0.0F;

		}
		return null;
	}
}
