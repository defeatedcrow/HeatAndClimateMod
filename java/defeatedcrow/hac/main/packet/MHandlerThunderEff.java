package defeatedcrow.hac.main.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.hac.core.ClimateCore;

public class MHandlerThunderEff implements IMessageHandler<MessageThunderEff, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageThunderEff message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
		}
		return null;
	}
}
