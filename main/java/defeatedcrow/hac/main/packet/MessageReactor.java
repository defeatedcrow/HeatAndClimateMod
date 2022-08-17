package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.machine.block.TileReactor;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageReactor implements IMessage {

	public int x;
	public int y;
	public int z;
	public int amo1;
	public String id1;
	public int amo2;
	public String id2;
	public int amo3;
	public String id3;
	public int amo4;
	public String id4;
	public byte side1;
	public byte side2;
	public byte side3;
	public byte side4;

	public MessageReactor() {}

	public MessageReactor(BlockPos pos, TileReactor tile) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		id1 = tile.inputT1.getFluidIdName();
		amo1 = tile.inputT1.getFluidAmount();
		id2 = tile.inputT2.getFluidIdName();
		amo2 = tile.inputT2.getFluidAmount();
		id3 = tile.outputT1.getFluidIdName();
		amo3 = tile.outputT1.getFluidAmount();
		id4 = tile.outputT2.getFluidIdName();
		amo4 = tile.outputT2.getFluidAmount();
		side1 = (byte) tile.tankSide1.index;
		side2 = (byte) tile.tankSide2.index;
		side3 = (byte) tile.tankSide3.index;
		side4 = (byte) tile.tankSide4.index;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		amo1 = buf.readInt();
		amo2 = buf.readInt();
		amo3 = buf.readInt();
		amo4 = buf.readInt();
		id1 = ByteBufUtils.readUTF8String(buf);
		id2 = ByteBufUtils.readUTF8String(buf);
		id3 = ByteBufUtils.readUTF8String(buf);
		id4 = ByteBufUtils.readUTF8String(buf);
		side1 = buf.readByte();
		side2 = buf.readByte();
		side3 = buf.readByte();
		side4 = buf.readByte();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(amo1);
		buf.writeInt(amo2);
		buf.writeInt(amo3);
		buf.writeInt(amo4);
		ByteBufUtils.writeUTF8String(buf, id1);
		ByteBufUtils.writeUTF8String(buf, id2);
		ByteBufUtils.writeUTF8String(buf, id3);
		ByteBufUtils.writeUTF8String(buf, id4);
		buf.writeByte(side1);
		buf.writeByte(side2);
		buf.writeByte(side3);
		buf.writeByte(side4);
	}
}
