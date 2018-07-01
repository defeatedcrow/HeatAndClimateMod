package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageMonitor implements IMessage {

	public int x;
	public int y;
	public int z;
	public float amo;
	public float max;
	public float last;

	public MessageMonitor() {}

	public MessageMonitor(BlockPos pos, float f1, float f2, float f3) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		amo = f1;
		max = f2;
		last = f3;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		amo = buf.readFloat();
		max = buf.readFloat();
		last = buf.readFloat();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(amo);
		buf.writeFloat(max);
		buf.writeFloat(last);
	}
}
