package defeatedcrow.hac.main.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// 雷エフェクト召喚
public class MessageThunderEff implements IMessage {

	public int x;
	public int y;
	public int z;

	public MessageThunderEff() {
	}

	public MessageThunderEff(BlockPos pos, float par1, float par2, float par3) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
}
