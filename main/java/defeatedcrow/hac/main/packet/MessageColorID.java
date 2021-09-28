package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageColorID implements IMessage {

	public int x;
	public int y;
	public int z;
	public int c;

	public MessageColorID() {}

	public MessageColorID(BlockPos pos, int num) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.c = num;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.c = buf.readInt();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(c);
	}
}
