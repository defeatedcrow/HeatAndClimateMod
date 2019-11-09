package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.machine.block.TileOscillator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerOscillator implements IMessageHandler<MessageOscillator, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageOscillator message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			int p = message.c;
			BlockPos pos = new BlockPos(x, y, z);
			if (player.world.getTileEntity(pos) != null) {
				TileEntity t = player.world.getTileEntity(pos);
				if (t instanceof TileOscillator) {
					TileOscillator tile = (TileOscillator) t;
					tile.power = p;
				}
			}
		}
		return null;
	}
}
