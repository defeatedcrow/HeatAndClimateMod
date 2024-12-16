package defeatedcrow.hac.core.network.packet.message;

import java.util.UUID;

import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.item.jems.RodBlack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class MsgLeftClickToS implements IPacketDC {

	protected UUID id;
	protected boolean isMainHand;
	protected int actionId;

	public MsgLeftClickToS() {}

	public MsgLeftClickToS(UUID i, InteractionHand hand, int num) {
		id = i;
		isMainHand = hand == InteractionHand.MAIN_HAND;
		actionId = num;
	}

	public MsgLeftClickToS(UUID i, boolean b, int num) {
		id = i;
		isMainHand = b;
		actionId = num;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeUUID(id);
		buf.writeBoolean(isMainHand);
		buf.writeInt(actionId);
	}

	public static MsgLeftClickToS decode(FriendlyByteBuf buf) {
		UUID i = buf.readUUID();
		boolean b = buf.readBoolean();
		int a = buf.readInt();
		return new MsgLeftClickToS(i, b, a);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (ctx.getSender() != null && ctx.getSender().getUUID().equals(id)) {
			ServerPlayer player = ctx.getSender();
			InteractionHand hand = isMainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
			ItemStack held = player.getItemInHand(hand);
			if (actionId == 0) {
				if (!DCUtil.isEmpty(held) && held.getItem() instanceof RodBlack rod) {
					rod.onEmptyHit(player.getLevel(), player, hand, held);
				}
			}
		}
	}

	public static void sendToServer(Player player, InteractionHand hand, int num) {
		if (player != null) {
			MsgLeftClickToS packet = new MsgLeftClickToS(player.getUUID(), hand, num);
			DCPacket.INSTANCE.getChannel().sendToServer(packet);
		}
	}

}
