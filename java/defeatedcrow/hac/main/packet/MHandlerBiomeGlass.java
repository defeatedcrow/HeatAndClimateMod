package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.block.BlockBiomeGlass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerBiomeGlass implements IMessageHandler<MessageBiomeGlass, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageBiomeGlass message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			int num = message.num;
			BlockPos pos = new BlockPos(x, y, z);
			BlockBiomeGlass.rebuildBiomeFromID(player.worldObj, pos, num);
		}
		return null;
	}
}
