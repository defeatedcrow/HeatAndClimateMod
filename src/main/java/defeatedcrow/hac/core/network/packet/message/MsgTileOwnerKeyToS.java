package defeatedcrow.hac.core.network.packet.message;

import java.util.UUID;

import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileOwnerKeyToS implements IPacketDC {

	protected UUID id;
	protected int x;
	protected int y;
	protected int z;
	protected boolean lock;

	public MsgTileOwnerKeyToS() {}

	public MsgTileOwnerKeyToS(UUID i, BlockPos pos, boolean l) {
		id = i;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		lock = l;
	}

	public MsgTileOwnerKeyToS(UUID i, int i1, int i2, int i3, boolean l) {
		id = i;
		x = i1;
		y = i2;
		z = i3;
		lock = l;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeUUID(id);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(lock);
	}

	public static MsgTileOwnerKeyToS decode(FriendlyByteBuf buf) {
		UUID i = buf.readUUID();
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		boolean b = buf.readBoolean();
		return new MsgTileOwnerKeyToS(i, x1, y1, z1, b);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (ctx.getSender() != null && ctx.getSender().getUUID().equals(id)) {
			ServerPlayer player = ctx.getSender();
			BlockPos pos = new BlockPos(x, y, z);
			BlockEntity entity = player.getLevel().getBlockEntity(pos);
			if (entity instanceof OwnableBaseTileDC tile) {
				tile.lock(lock);
			}
		}
	}

	public static void sendToServer(Player player, BlockPos pos, boolean b) {
		if (player != null) {
			MsgTileOwnerKeyToS packet = new MsgTileOwnerKeyToS(player.getUUID(), pos, b);
			DCPacket.INSTANCE.getChannel().sendToServer(packet);
		}
	}

}
