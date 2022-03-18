package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.machine.block.TileHopperFilterSUS;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerUniHopperButton implements IMessageHandler<MessageUniHopperButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageUniHopperButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			if (player.world.getTileEntity(pos) != null) {
				TileEntity t = player.world.getTileEntity(pos);
				if (t instanceof TileHopperFilterSUS) {
					TileHopperFilterSUS target = (TileHopperFilterSUS) t;
					boolean flag = message.b;
					target.switchUnifierMode();
				}
			}
		}
		return null;
	}
}
