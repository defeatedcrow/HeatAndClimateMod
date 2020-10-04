package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.food.block.TileIncubator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerIncubatorButton implements IMessageHandler<MessageIncubatorButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageIncubatorButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			if (player.world.getTileEntity(pos) != null) {
				TileEntity t = player.world.getTileEntity(pos);
				if (t instanceof TileIncubator) {
					TileIncubator target = (TileIncubator) t;
					short b1 = message.num;
					IClimate climate = ClimateAPI.register.getClimateFromInt(b1);
					target.setClimate(climate);
				}
			}
		}
		return null;
	}
}
