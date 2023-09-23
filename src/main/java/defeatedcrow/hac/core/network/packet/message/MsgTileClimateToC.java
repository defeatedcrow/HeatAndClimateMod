package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.api.climate.IClimateReceiver;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileClimateToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	protected int climate;
	protected int temp;
	protected int hum;
	protected int air;

	public MsgTileClimateToC() {}

	public MsgTileClimateToC(BlockPos pos, int i) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		climate = i;
	}

	public MsgTileClimateToC(int i1, int i2, int i3, int clm) {
		x = i1;
		y = i2;
		z = i3;
		climate = clm;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(climate);
	}

	public static MsgTileClimateToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		int clm = buf.readInt();
		return new MsgTileClimateToC(x1, y1, z1, clm);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist.isClient() && ClimateCore.proxy.getClientLevel().isPresent()) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = ClimateCore.proxy.getClientLevel().get();
			BlockEntity entity = level.getBlockEntity(pos);
			if (entity instanceof IClimateReceiver tile) {
				tile.currentClimate(climate);
			}
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, int i) {
		if (level != null) {
			MsgTileClimateToC packet = new MsgTileClimateToC(pos, i);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
