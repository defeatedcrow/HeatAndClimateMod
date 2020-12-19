package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.fluid.DCTank;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerEntityTank implements IMessageHandler<MessageEntityTank, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageEntityTank message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int e = message.eid;
			String id1 = message.id1;
			int amo1 = message.amo1;
			Entity entity = player.world.getEntityByID(e);
			if (entity != null && entity
					.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
				IFluidHandler handler = entity
						.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
				if (handler != null && handler instanceof DCTank) {
					DCTank tank = (DCTank) handler;
					tank.setFluidByIdName(id1);
					tank.setAmount(amo1);
				}
			}
		}
		return null;
	}
}
