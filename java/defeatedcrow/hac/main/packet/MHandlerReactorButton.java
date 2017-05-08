package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.machine.block.TileReactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerReactorButton implements IMessageHandler<MessageReactorButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageReactorButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			if (player.worldObj.getTileEntity(pos) != null) {
				TileEntity t = player.worldObj.getTileEntity(pos);
				if (t instanceof TileReactor) {
					TileReactor target = (TileReactor) t;
					byte b1 = message.num;
					byte b2 = message.side;

					EnumSide s = EnumSide.fromIndex(b2);
					if (b1 == 0) {
						target.tankSide1 = s;
					}
					if (b1 == 1) {
						target.tankSide2 = s;
					}
					if (b1 == 2) {
						target.tankSide3 = s;
					}
					if (b1 == 3) {
						target.tankSide4 = s;
					}
				}
			}
		}
		return null;
	}
}
