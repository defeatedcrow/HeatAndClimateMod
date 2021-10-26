package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerChatDC implements IMessageHandler<MessageChatDC, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageChatDC message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			String str = message.mes;
			String str2 = message.mes2;
			if (str != null) {
				String mes1 = I18n.format(str);
				player.sendMessage(new TextComponentString(mes1 + " " + str2));
			}
		}
		return null;
	}
}
