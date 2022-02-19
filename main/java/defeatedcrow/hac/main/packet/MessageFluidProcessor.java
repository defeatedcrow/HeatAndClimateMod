package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageFluidProcessor implements IMessage {

	public int x;
	public int y;
	public int z;
	public int amo1;
	public String id1;
	public int amo2;
	public String id2;
	public boolean cap;
	public int color;

	public MessageFluidProcessor() {}

	public MessageFluidProcessor(BlockPos pos, String fluid1, int a1, String fluid2, int a2, boolean c, int col) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		id1 = fluid1;
		amo1 = a1;
		id2 = fluid2;
		amo2 = a2;
		cap = c;
		color = col;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		amo1 = buf.readInt();
		amo2 = buf.readInt();
		id1 = ByteBufUtils.readUTF8String(buf);
		id2 = ByteBufUtils.readUTF8String(buf);
		cap = buf.readBoolean();
		color = buf.readByte();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(amo1);
		buf.writeInt(amo2);
		ByteBufUtils.writeUTF8String(buf, id1);
		ByteBufUtils.writeUTF8String(buf, id2);
		buf.writeBoolean(cap);
		buf.writeByte(color);
	}
}
