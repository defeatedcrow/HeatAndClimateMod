package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileSimpleIntegerToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	protected int data;

	public MsgTileSimpleIntegerToC() {}

	public MsgTileSimpleIntegerToC(BlockPos pos, int i) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		data = i;
	}

	public MsgTileSimpleIntegerToC(int i1, int i2, int i3, int i) {
		x = i1;
		y = i2;
		z = i3;
		data = i;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(data);
	}

	public static MsgTileSimpleIntegerToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		int i = buf.readInt();
		return new MsgTileSimpleIntegerToC(x1, y1, z1, i);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = Minecraft.getInstance().level;
			BlockEntity entity = level.getBlockEntity(pos);
			if (entity instanceof IIntReceiver tile) {
				tile.receiveInteger(data);
			}
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, int i) {
		if (level != null) {
			MsgTileSimpleIntegerToC packet = new MsgTileSimpleIntegerToC(pos, i);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
