package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// biome reset
public class MessageMagicParticle implements IMessage {

	public double x;
	public double y;
	public double z;

	public MessageMagicParticle() {}

	public MessageMagicParticle(double x1, double y1, double z1) {
		this.x = x1;
		this.y = y1;
		this.z = z1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
	}
}
