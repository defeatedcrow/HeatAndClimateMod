package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFacingTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileFaceIOToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	protected int side;
	protected int io;

	public MsgTileFaceIOToC() {}

	public MsgTileFaceIOToC(BlockPos pos, int s, int d) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		side = s;
		io = d;
	}

	public MsgTileFaceIOToC(int i1, int i2, int i3, int s, int d) {
		x = i1;
		y = i2;
		z = i3;
		side = s;
		io = d;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(side);
		buf.writeInt(io);
	}

	public static MsgTileFaceIOToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		int s = buf.readInt();
		int d = buf.readInt();
		return new MsgTileFaceIOToC(x1, y1, z1, s, d);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist.isClient() && ClimateCore.proxy.getClientLevel().isPresent()) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = ClimateCore.proxy.getClientLevel().get();
			BlockEntity entity = level.getBlockEntity(pos);
			if (entity instanceof IFacingTile tile) {
				if (side >= 0 && side < 6) {
					Direction dir = Direction.from3DDataValue(side);
					tile.setFace(dir, FaceIO.getIO(io));
				}
			}
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, int s, int d) {
		if (level != null && level instanceof ServerLevel) {
			MsgTileFaceIOToC packet = new MsgTileFaceIOToC(pos, s, d);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
