package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerFluidSwitchButton implements IMessageHandler<MessageFluidSwitchButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageFluidSwitchButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			if (player.world.getTileEntity(pos) != null) {
				TileEntity t = player.world.getTileEntity(pos);
				if (t instanceof TileFluidProcessorBase) {
					TileFluidProcessorBase tank = (TileFluidProcessorBase) t;

					FluidStack f1 = null;
					FluidStack f2 = null;

					if (!tank.inputT.isEmpty()) {
						f1 = tank.inputT.getFluid().copy();
					}

					if (!tank.outputT.isEmpty()) {
						f2 = tank.outputT.getFluid().copy();
					}

					tank.inputT.setFluid(f2);
					tank.outputT.setFluid(f1);

				}
			}
		}
		return null;
	}
}
