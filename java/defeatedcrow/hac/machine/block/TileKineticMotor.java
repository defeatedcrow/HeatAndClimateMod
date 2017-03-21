package defeatedcrow.hac.machine.block;

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
		@Optional.Interface(iface = "cofh.api.energy.IEnergyReceiver", modid = "CoFHAPI|energy"),
})
public class TileKineticMotor extends TileTorqueBase implements ITorqueProvider, cofh.api.energy.IEnergyReceiver {

	public int cashedRF = 0;
	public int cashedFU = 0;

	public int getMaxCashRF() {
		return (int) (128 * EnergyConvertRate.rateVsRF);
	}

	@Override
	public void updateTile() {
		super.updateTile();
		if (!worldObj.isRemote) {
			// Airflowチェック
			// 方向ごとのFUTileを能動的に見に行く
			for (EnumFacing face : EnumFacing.VALUES) {
				if (face == this.getBaseSide()) {
					continue;
				}
				TileEntity tile = worldObj.getTileEntity(getPos().offset(face));
				if (tile == null) {
					continue;
				}
				if (cashedRF < getMaxCashRF()) {
					if (tile.hasCapability(CapabilityEnergy.ENERGY, face.getOpposite())) {
						IEnergyStorage stFU = tile.getCapability(CapabilityEnergy.ENERGY, face.getOpposite());
						if (stFU != null && stFU.canExtract()) {
							int ext2 = (int) (128.0F * EnergyConvertRate.rateVsFU);
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

			float add = 0;
			float add1 = cashedRF / EnergyConvertRate.rateVsRF;
			float add2 = cashedFU / EnergyConvertRate.rateVsFU;

			add = add1 + add2;
			if (add > this.maxTorque()) {
				add = this.maxTorque();
			}
			this.currentTorque += add;
			this.cashedRF = 0;
			this.cashedFU = 0;

			// provider
			this.provideTorque(worldObj, getPos().offset(getOutputSide()), getOutputSide(), false);
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
	public EnumFacing getOutputSide() {
		return this.getBaseSide();
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
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("dcs.Ecash", this.cashedRF);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("dcs.Ecash", this.cashedRF);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.cashedRF = tag.getInteger("dcs.Ecash");
	}

	@Override
	@Method(modid = "CoFHAPI|energy")
	public int getEnergyStored(EnumFacing face) {
		int ret = cashedRF;
		return ret;
	}

	@Override
	@Method(modid = "CoFHAPI|energy")
	public int getMaxEnergyStored(EnumFacing face) {
		int ret = getMaxCashRF();
		return ret;
	}

	@Override
	@Method(modid = "CoFHAPI|energy")
	public boolean canConnectEnergy(EnumFacing face) {
		return face != this.getBaseSide();
	}

	@Override
	@Method(modid = "CoFHAPI|energy")
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
