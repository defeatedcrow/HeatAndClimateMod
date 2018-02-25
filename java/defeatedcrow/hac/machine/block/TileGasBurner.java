package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.main.api.ISidedTankChecker;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileGasBurner extends TileCookingStove implements ISidedTankChecker {

	@Override
	public void updateTile() {
		super.updateTile();
		if (currentClimate != DCHeatTier.UHT.getID()) {
			currentClimate = DCHeatTier.UHT.getID();
		}
		if (!getWorld().isRemote) {
			if (BlockGasBurner.isLit(getWorld(), getPos()) != this.isActive()) {
				BlockGasBurner.changeLitState(getWorld(), getPos(), isActive());
			}
		}

	}

	@Override
	protected void onServerUpdate() {
		if (this.currentBurnTime > 0 && BlockGasBurner.isPower(getWorld(), getPos())) {
			this.currentBurnTime--;
		}
	}

	/* 隣接tankから燃料液体を吸い取る */
	@Override
	public void checkSideTank() {
		for (EnumFacing face : EnumFacing.HORIZONTALS) {
			int cap = inputT.getCapacity();
			int amo = inputT.getFluidAmount();
			int mov = 50; // 50mBずつ

			if (amo >= cap) {
				break;
			}

			TileEntity tile = worldObj.getTileEntity(getPos().offset(face));
			if (tile != null && !(tile instanceof ISidedTankChecker)
					&& tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face.getOpposite())) {
				IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						face.getOpposite());
				if (tank != null && tank.getTankProperties() != null) {
					FluidStack target = tank.getTankProperties()[0].getContents();
					if (target != null && target.getFluid() != null && getBurnTime(target.getFluid()) > 0) {
						int i = Math.min(mov, cap - amo);
						FluidStack ret = tank.drain(i, false);
						int fill = inputT.fill(ret, false);
						if (fill > 0) {
							ret = tank.drain(fill, true);
							inputT.fill(ret, true);
							tile.markDirty();
							this.markDirty();
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public DCHeatTier getCurrentHeatTier() {
		return DCHeatTier.UHT;
	}

	/* 燃焼判定 */

	public static int getBurnTime(Fluid fluid) {
		if (fluid != null && fluid.isGaseous()) {
			int burn = MainAPIManager.fuelRegister.getBurningTime(fluid);
			return burn;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "dcs.gui.device.gas_burner";
	}

}
