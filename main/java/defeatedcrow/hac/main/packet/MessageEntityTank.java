package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageEntityTank implements IMessage {

	public int eid;
	public int amo1;
	public String id1;

	public MessageEntityTank() {}

	public MessageEntityTank(int entityId, String fluid1, int a1) {
		eid = entityId;
		amo1 = a1;
		id1 = fluid1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		eid = buf.readInt();
		amo1 = buf.readInt();
		id1 = ByteBufUtils.readUTF8String(buf);
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(eid);
		buf.writeInt(amo1);
		ByteBufUtils.writeUTF8String(buf, id1);
	}
}
