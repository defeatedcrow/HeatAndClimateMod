package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.plugin.EnergyConvertRate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.Optional.Method;

@Optional.InterfaceList({
		@Optional.Interface(iface = "cofh.api.energy.IEnergyReceiver", modid = "cofhcore"),
})
public class TileKineticMotor extends TileTorqueBase implements ITorqueProvider, cofh.api.energy.IEnergyReceiver {

	public int cashedRF = 0;
	public int cashedFU = 0;

	public int getMaxCashRF() {
		return (int) (128 * EnergyConvertRate.rateVsRF);
	}

	public int getMaxCashFU() {
		int cur = cashedRF * (int) (EnergyConvertRate.rateVsRF);
		cur /= EnergyConvertRate.rateVsFU;
		return (int) ((128 - cur) * EnergyConvertRate.rateVsFU);
	}

	@Override
	public void updateTile() {
		super.updateTile();
		if (!worldObj.isRemote) {
			// 方向ごとのFUTileを能動的に見に行く
			for (EnumFacing face : EnumFacing.VALUES) {
				if (face == this.getBaseSide()) {
					continue;
				}
				TileEntity tile = worldObj.getTileEntity(getPos().offset(face));
				if (tile == null) {
					continue;
				}

				if (cashedFU < getMaxCashFU()) {
					if (tile.hasCapability(CapabilityEnergy.ENERGY, face.getOpposite())) {
						IEnergyStorage stFU = tile.getCapability(CapabilityEnergy.ENERGY, face.getOpposite());
						if (stFU != null && stFU.canExtract()) {
							int ext2 = getMaxCashFU();
							int ret = stFU.extractEnergy(ext2, true);
							ret = Math.min(ret, ext2 - cashedFU);
							if (ret > 0) {
								stFU.extractEnergy(ret, false);
								cashedFU += ret;
							}
						}
					}
				}
			}

			// provider
			this.currentTorque += cashedRF * EnergyConvertRate.rateVsRF;
			this.currentTorque += cashedFU * EnergyConvertRate.rateVsFU;
			if (currentTorque > this.maxTorque()) {
				this.currentTorque = maxTorque();
			}

			float send = 0;
			for (EnumFacing side : getOutputSide()) {
				send += this.provideTorque(worldObj, getPos().offset(side), side, false);
			}

			// DCLogger.debugLog("*** Kinetic Motor ***");
			// DCLogger.debugLog("send: " + send);
			// RFが優先で減る
			float lim1 = send * EnergyConvertRate.rateVsRF;
			lim1 = Math.min(cashedRF, lim1);
			float add1 = lim1 / EnergyConvertRate.rateVsRF;
			this.cashedRF -= lim1;
			send -= add1;
			// DCLogger.debugLog("RF use: " + lim1);

			// RFが優先で減る
			float lim2 = send * EnergyConvertRate.rateVsFU;
			lim2 = Math.min(cashedFU, lim2);
			this.cashedFU -= lim2;
			// DCLogger.debugLog("FU use: " + lim2);

		}

	}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 4.0F;
	}

	@Override
	public List<EnumFacing> getOutputSide() {
		List<EnumFacing> ret = Lists.newArrayList();
		ret.add(getBaseSide());
		return ret;
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F)
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		return false;
	}

	@Override
	public float provideTorque(World world, BlockPos outputPos, EnumFacing output, boolean sim) {
		float amo = this.getCurrentTorque();
		if (canProvideTorque(world, outputPos, output)) {
			ITorqueReceiver target = (ITorqueReceiver) world.getTileEntity(outputPos);
			float ret = target.receiveTorque(amo, output, sim);
			return ret;
		}
		return 0;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide();
	}

	// nbt

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.cashedRF = tag.getInteger("dcs.Ecash");
		this.cashedFU = tag.getInteger("dcs.Fcash");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("dcs.Ecash", this.cashedRF);
		tag.setInteger("dcs.Fcash", this.cashedFU);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("dcs.Ecash", this.cashedRF);
		tag.setInteger("dcs.Fcash", this.cashedFU);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.cashedRF = tag.getInteger("dcs.Ecash");
		this.cashedFU = tag.getInteger("dcs.Fcash");
	}

	@Override
	@Method(modid = "cofhcore")
	public int getEnergyStored(EnumFacing face) {
		int ret = cashedRF;
		return ret;
	}

	@Override
	@Method(modid = "cofhcore")
	public int getMaxEnergyStored(EnumFacing face) {
		int ret = getMaxCashRF();
		return ret;
	}

	@Override
	@Method(modid = "cofhcore")
	public boolean canConnectEnergy(EnumFacing face) {
		return face != this.getBaseSide();
	}

	@Override
	@Method(modid = "cofhcore")
	public int receiveEnergy(EnumFacing face, int amo, boolean sim) {
		if (canConnectEnergy(face)) {
			int get = this.getMaxCashRF() - cashedRF;
			if (get <= 0) {
				get = 0;
			}
			get = Math.min(amo, get);
			if (!sim) {
				cashedRF += get;
				this.markDirty();
			}
			return get;
		}
		return 0;
	}

}
