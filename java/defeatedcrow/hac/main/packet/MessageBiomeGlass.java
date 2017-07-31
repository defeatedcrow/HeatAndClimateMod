package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageBiomeGlass implements IMessage {

	public int x;
	public int y;
	public int z;
	public short num;

	public MessageBiomeGlass() {}

	public MessageBiomeGlass(BlockPos pos, short b1) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		num = b1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		num = buf.readShort();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeShort(num);
	}
}
