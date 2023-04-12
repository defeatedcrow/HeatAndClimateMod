package defeatedcrow.hac.core.network.packet.message;

import java.util.ArrayList;
import java.util.UUID;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class MsgCharmKeyToS implements IPacketDC {

	protected UUID id;

	public MsgCharmKeyToS() {}

	public MsgCharmKeyToS(UUID i) {
		id = i;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeUUID(id);
	}

	public static MsgCharmKeyToS decode(FriendlyByteBuf buf) {
		UUID i = buf.readUUID();
		return new MsgCharmKeyToS(i);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (ctx.getSender() != null && ctx.getSender().getUUID().equals(id)) {
			ServerPlayer player = ctx.getSender();
			ArrayList<ItemStack> charms = DCItemUtil.getCharms(player, CharmType.KEY);
			if (!charms.isEmpty() && charms.size() > 0) {
				ItemStack item = charms.get(0);
				if (item.getItem() instanceof IJewelCharm charm) {
					if (charm.isActive(player, item) && charm.onUsing(player, item)) {
						charm.onConsumeResource(player, item);
						player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 0.8F);
					}
				}
			}
		}
	}

	public static void sendToServer(Player player) {
		if (player != null) {
			MsgCharmKeyToS packet = new MsgCharmKeyToS(player.getUUID());
			DCPacket.INSTANCE.getChannel().sendToServer(packet);
		}
	}

}
