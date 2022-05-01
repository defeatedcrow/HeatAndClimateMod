package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// 雷エフェクト召喚
public class MessageDisplayCaseButton implements IMessage {

	public int x;
	public int y;
	public int z;
	public byte num;
	public short emerald;

	public MessageDisplayCaseButton() {}

	public MessageDisplayCaseButton(BlockPos pos, byte b1, short s1) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		num = b1;
		emerald = s1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		num = buf.readByte();
		emerald = buf.readShort();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeByte(num);
		buf.writeShort(emerald);
	}
}
