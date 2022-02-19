package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerFluidProcessor implements IMessageHandler<MessageFluidProcessor, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageFluidProcessor message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			String id1 = message.id1;
			int amo1 = message.amo1;
			String id2 = message.id2;
			int amo2 = message.amo2;
			boolean cap = message.cap;
			int color = message.color;
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity tile = player.world.getTileEntity(pos);
			if (tile instanceof TileFluidProcessorBase) {
				TileFluidProcessorBase machine = (TileFluidProcessorBase) tile;
				machine.setCap(cap);
				machine.setColor(color);
			}
			if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
				IFluidHandler handler = tile
						.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
				if (handler != null && handler instanceof DCTank) {
					DCTank tank1 = (DCTank) handler;
					tank1.setFluidByIdName(id1);
					tank1.setAmount(amo1);
				}
			}
			if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
				IFluidHandler handler2 = tile
						.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);
				if (handler2 != null && handler2 instanceof DCTank) {
					DCTank tank2 = (DCTank) handler2;
					tank2.setFluidByIdName(id2);
					tank2.setAmount(amo2);
				}
			}
		}
		return null;
	}
}
