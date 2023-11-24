package defeatedcrow.hac.core.network.packet.message;

import javax.annotation.Nonnull;

import defeatedcrow.hac.api.material.IDisplayTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileDisplayItemToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	@Nonnull
	protected ItemStack data;

	public MsgTileDisplayItemToC() {}

	public MsgTileDisplayItemToC(BlockPos pos, @Nonnull ItemStack stack) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		data = stack;
	}

	public MsgTileDisplayItemToC(int i1, int i2, int i3, @Nonnull ItemStack stack) {
		x = i1;
		y = i2;
		z = i3;
		data = stack;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeItem(data);
	}

	public static MsgTileDisplayItemToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		ItemStack item = buf.readItem();
		return new MsgTileDisplayItemToC(x1, y1, z1, item);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist.isClient() && ClimateCore.proxy.getClientLevel().isPresent()) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = ClimateCore.proxy.getClientLevel().get();
			BlockEntity entity = level.getBlockEntity(pos);
			if (entity instanceof IDisplayTile tile) {
				tile.setDisplay(data);
			}
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, @Nonnull ItemStack item) {
		if (level != null && level instanceof ServerLevel) {
			MsgTileDisplayItemToC packet = new MsgTileDisplayItemToC(pos, item);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
