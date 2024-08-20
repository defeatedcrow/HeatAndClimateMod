package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgEffectToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	protected int type;

	public MsgEffectToC() {}

	public MsgEffectToC(BlockPos pos, int i) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		type = i;
	}

	public MsgEffectToC(int i1, int i2, int i3, int t) {
		x = i1;
		y = i2;
		z = i3;
		type = t;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(type);
	}

	public static MsgEffectToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		int t = buf.readInt();
		return new MsgEffectToC(x1, y1, z1, t);
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, int i) {
		if (level != null) {
			MsgEffectToC packet = new MsgEffectToC(pos, i);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist.isClient() && ClimateCore.proxy.getClientLevel().isPresent()) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = ClimateCore.proxy.getClientLevel().get();
			Player player = ClimateCore.proxy.getClientPlayer().orElse(null);
			switch (type) {
			case 0: // POP SE
				level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.5F);
				break;
			case 1: // Shears SE
				level.playSound(player, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 1.5F);
				break;
			case 2: // Fire SE
				level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.5F);
				break;
			}
		}
	}

}
