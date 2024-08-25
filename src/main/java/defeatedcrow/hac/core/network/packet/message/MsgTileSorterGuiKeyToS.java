package defeatedcrow.hac.core.network.packet.message;

import java.util.UUID;

import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSortingTile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileSorterGuiKeyToS implements IPacketDC {

	protected UUID id;
	protected int x;
	protected int y;
	protected int z;
	protected int button;

	public MsgTileSorterGuiKeyToS() {}

	public MsgTileSorterGuiKeyToS(UUID i, BlockPos pos, int b) {
		id = i;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		button = b;
	}

	public MsgTileSorterGuiKeyToS(UUID i, int i1, int i2, int i3, int b) {
		id = i;
		x = i1;
		y = i2;
		z = i3;
		button = b;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeUUID(id);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(button);
	}

	public static MsgTileSorterGuiKeyToS decode(FriendlyByteBuf buf) {
		UUID i = buf.readUUID();
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		int b = buf.readInt();
		return new MsgTileSorterGuiKeyToS(i, x1, y1, z1, b);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (ctx.getSender() != null && ctx.getSender().getUUID().equals(id)) {
			ServerPlayer player = ctx.getSender();
			BlockPos pos = new BlockPos(x, y, z);
			BlockEntity entity = player.getLevel().getBlockEntity(pos);
			if (entity instanceof ConveyorSortingTile tile) {
				tile.switchSide(button);
			}
		}
	}

	public static void sendToServer(Player player, BlockPos pos, int b) {
		if (player != null) {
			MsgTileSorterGuiKeyToS packet = new MsgTileSorterGuiKeyToS(player.getUUID(), pos, b);
			DCPacket.INSTANCE.getChannel().sendToServer(packet);
		}
	}

}
