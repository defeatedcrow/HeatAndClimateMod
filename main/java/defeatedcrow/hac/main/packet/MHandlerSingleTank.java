package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerSingleTank implements IMessageHandler<MessageSingleTank, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageSingleTank message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			int id1 = message.id1;
			int amo1 = message.amo1;
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity tile = player.world.getTileEntity(pos);
			if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
				IFluidHandler handler = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						EnumFacing.UP);
				if (handler != null && handler instanceof DCTank) {
					DCTank tank = (DCTank) handler;
					Fluid f1 = FluidIDRegisterDC.getFluid(id1);
					if (f1 != null) {
						FluidStack stack1 = new FluidStack(f1, amo1);
						tank.setFluid(stack1);
					} else {
						tank.setFluid(null);
					}
				}
			}
		}
		return null;
	}
}
