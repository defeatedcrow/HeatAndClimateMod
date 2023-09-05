package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimateReceiver;
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

public class MsgTileClimateToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	protected int climate;
	protected int temp;
	protected int hum;
	protected int air;

	public MsgTileClimateToC() {}

	public MsgTileClimateToC(BlockPos pos, int i, int i2, int i3, int i4) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		climate = i;
		temp = i2;
		hum = i3;
		air = i4;
	}

	public MsgTileClimateToC(int i1, int i2, int i3, int clm, int t, int h, int a) {
		x = i1;
		y = i2;
		z = i3;
		climate = clm;
		temp = t;
		hum = h;
		air = a;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(climate);
		buf.writeInt(temp);
		buf.writeInt(hum);
		buf.writeInt(air);
	}

	public static MsgTileClimateToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		int clm = buf.readInt();
		int t = buf.readInt();
		int h = buf.readInt();
		int a = buf.readInt();
		return new MsgTileClimateToC(x1, y1, z1, clm, t, h, a);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = Minecraft.getInstance().level;
			BlockEntity entity = level.getBlockEntity(pos);
			if (entity instanceof IClimateReceiver tile) {
				tile.currentClimate(climate);
				tile.receiveHeatTier(DCHeatTier.getTypeByID(temp));
				tile.receiveHumidity(DCHumidity.getTypeByID(hum));
				tile.receiveAirflow(DCAirflow.getTypeByID(air));
			}
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, int i, DCHeatTier t, DCHumidity h, DCAirflow a) {
		if (level != null) {
			MsgTileClimateToC packet = new MsgTileClimateToC(pos, i, t.getID(), h.getID(), a.getID());
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
