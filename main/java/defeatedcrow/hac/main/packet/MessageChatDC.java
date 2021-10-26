package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageChatDC implements IMessage {

	public String mes;
	public String mes2;

	public MessageChatDC() {}

	public MessageChatDC(String message) {
		mes = message;
		mes2 = " ";
	}

	public MessageChatDC(String message, String message2) {
		mes = message;
		mes2 = message2;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		mes = ByteBufUtils.readUTF8String(buf);
		mes2 = ByteBufUtils.readUTF8String(buf);
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, mes);
		ByteBufUtils.writeUTF8String(buf, mes2);
	}
}
