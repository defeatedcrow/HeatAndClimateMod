package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageMagicParticle implements IMessage {

	public double x;
	public double y;
	public double z;
	public byte num;
	public float dx = 0F;
	public float dy = 0F;
	public float dz = 0F;

	public MessageMagicParticle() {}

	public MessageMagicParticle(double x1, double y1, double z1) {
		this.x = x1;
		this.y = y1;
		this.z = z1;
		num = 50;
	}

	public MessageMagicParticle(double x1, double y1, double z1, byte b1, float fx, float fy, float fz) {
		this.x = x1;
		this.y = y1;
		this.z = z1;
		num = b1;
		dx = fx;
		dy = fy;
		dz = fz;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
		num = buf.readByte();
		dx = buf.readFloat();
		dy = buf.readFloat();
		dz = buf.readFloat();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeByte(num);
		buf.writeFloat(dx);
		buf.writeFloat(dy);
		buf.writeFloat(dz);
	}
}
