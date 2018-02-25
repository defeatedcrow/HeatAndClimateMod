package defeatedcrow.hac.machine.block;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.plugin.EnergyConvertRate;
import defeatedcrow.hac.plugin.cofh.RFDeviceHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.ModAPIManager;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.Optional.Method;

@Optional.InterfaceList({
		@Optional.Interface(iface = "cofh.api.energy.IEnergyProvider", modid = "cofhcore"),
})
public class TileDynamo extends TileTorqueBase implements ITorqueReceiver, cofh.api.energy.IEnergyProvider {

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side != getBaseSide() || side != getBaseSide().getOpposite();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	@Override
	public float maxSpeed() {
		return 360.0F;
	}

	private List<EnumFacing> faces = new ArrayList<EnumFacing>();

	@Override
	public void updateTile() {
		super.updateTile();

		// RF/FU送信
		if (!worldObj.isRemote) {
			faces.clear();
			for (EnumFacing face : EnumFacing.VALUES) {
				if (isOutputSide(face)) {
					TileEntity target = worldObj.getTileEntity(pos.offset(face));
					if (target != null) {
						if (target.hasCapability(CapabilityEnergy.ENERGY, face.getOpposite())) {
							faces.add(face);
						} else if (ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy")) {
							if (RFDeviceHelper.isRFReceiver(target)) {
								faces.add(face);
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected void onServerUpdate() {
		float sendRF = prevTorque * EnergyConvertRate.rateVsRF * 0.05F;
		float sendFU = prevTorque * EnergyConvertRate.rateVsFU * 0.05F;
		if (!faces.isEmpty() && prevTorque > 0F) {
			for (EnumFacing face : faces) {
				TileEntity target = worldObj.getTileEntity(pos.offset(face));
				if (target != null) {
					if (target.hasCapability(CapabilityEnergy.ENERGY, face.getOpposite())) {
						IEnergyStorage sto = target.getCapability(CapabilityEnergy.ENERGY, face.getOpposite());
						float ret = sendFU / faces.size();
						int amo = MathHelper.floor_float(ret);
						sto.receiveEnergy(amo, false);
					} else if (ModAPIManager.INSTANCE.hasAPI("cofhcore")) {
						if (RFDeviceHelper.isRFReceiver(target)) {
							float ret = sendRF / faces.size();
							int amo = MathHelper.floor_float(ret);
							RFDeviceHelper.inputEnergy(face.getOpposite(), target, amo, false);
						}
					}

				}
			}
		}
	}

	@Override
	@Method(modid = "cofhcore")
	public int getEnergyStored(EnumFacing face) {
		if (!faces.contains(face)) {
			faces.add(face);
		}
		float sendRF = prevTorque * EnergyConvertRate.rateVsRF * 0.05F;
		sendRF /= faces.size();
		int amo = MathHelper.floor_float(sendRF);
		return amo;
	}

	@Override
	@Method(modid = "cofhcore")
	public int getMaxEnergyStored(EnumFacing face) {
		float sendRF = maxTorque() * EnergyConvertRate.rateVsRF;
		int amo = MathHelper.floor_float(sendRF);
		return amo;
	}

	@Override
	@Method(modid = "cofhcore")
	public boolean canConnectEnergy(EnumFacing face) {
		return isOutputSide(face);
	}

	@Override
	@Method(modid = "cofhcore")
	public int extractEnergy(EnumFacing face, int amo, boolean sim) {
		TileEntity target = worldObj.getTileEntity(pos.offset(face));
		if (target == null || RFDeviceHelper.isRFReceiver(target))
			return 0;

		if (canConnectEnergy(face) && getEnergyStored(face) > 0) {
			int ret = getEnergyStored(face);
			ret = Math.min(amo, ret);
			return ret;
		}
		return 0;
	}

}
