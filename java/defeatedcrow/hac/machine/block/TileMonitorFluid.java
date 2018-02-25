package defeatedcrow.hac.machine.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileMonitorFluid extends TileMonitorBase {

	@Override
	public String unit() {
		return "mB";
	}

	@Override
	protected boolean updateAmount() {
		TileEntity target = worldObj.getTileEntity(getPairPos());
		if (target != null
				&& target.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getSide().getFacing())) {
			IFluidHandler tank = target.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
					getSide().getFacing());
			if (tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
				amountMax = tank.getTankProperties()[0].getCapacity();
				FluidStack cont = tank.drain((int) amountMax, false);
				amount = cont == null ? 0 : cont.amount;
			}
			return true;
		}
		return false;
	}

}
