package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageSingleTank implements IMessage {

	public int x;
	public int y;
	public int z;
	public int amo1;
	public int id1;

	public MessageSingleTank() {}

	public MessageSingleTank(BlockPos pos, int fluid1, int a1) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		amo1 = a1;
		id1 = fluid1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		id1 = buf.readInt();
		amo1 = buf.readInt();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(id1);
		buf.writeInt(amo1);
	}
}
