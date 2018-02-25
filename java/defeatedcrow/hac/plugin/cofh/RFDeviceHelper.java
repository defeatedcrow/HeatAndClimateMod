package defeatedcrow.hac.plugin.cofh;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

/**
 * RFを保持できる装置を操作するための中継クラス。
 */
public class RFDeviceHelper {

	private RFDeviceHelper() {}

	public static boolean isRFConnector(TileEntity tile) {
		return tile instanceof cofh.api.energy.IEnergyConnection || tile instanceof cofh.api.energy.IEnergyStorage;
	}

	public static boolean isRFStorage(TileEntity tile) {
		return tile instanceof cofh.api.energy.IEnergyStorage;
	}

	public static int getStorageAmount(TileEntity tile) {
		if (tile instanceof cofh.api.energy.IEnergyStorage) {
			cofh.api.energy.IEnergyStorage storage = (cofh.api.energy.IEnergyStorage) tile;
			return storage.getEnergyStored();
		}
		return 0;
	}

	public static int getStorageMax(TileEntity tile) {
		if (tile instanceof cofh.api.energy.IEnergyStorage) {
			cofh.api.energy.IEnergyStorage storage = (cofh.api.energy.IEnergyStorage) tile;
			return storage.getMaxEnergyStored();
		}
		return 0;
	}

	public static boolean isRFReceiver(TileEntity tile) {
		return tile instanceof cofh.api.energy.IEnergyReceiver;
	}

	public static int inputEnergy(EnumFacing dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFReceiver(tile)) {
			cofh.api.energy.IEnergyReceiver handler = (cofh.api.energy.IEnergyReceiver) tile;

			if (handler.canConnectEnergy(dir)) {
				ret = handler.receiveEnergy(dir, amount, simulate);
			}
		}

		return ret;
	}

	public static boolean isRFProvider(TileEntity tile) {
		return tile instanceof cofh.api.energy.IEnergyProvider;
	}

	public static int extractEnergy(EnumFacing dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFProvider(tile)) {
			cofh.api.energy.IEnergyProvider handler = (cofh.api.energy.IEnergyProvider) tile;

			if (handler.canConnectEnergy(dir)) {
				ret = handler.extractEnergy(dir, amount, simulate);
			}
		}

		return ret;
	}

	public static boolean isRFHandler(TileEntity tile) {
		return tile instanceof cofh.api.energy.IEnergyHandler;
	}

	public static int getHandlerAmount(TileEntity tile, EnumFacing side) {
		if (tile instanceof cofh.api.energy.IEnergyHandler) {
			cofh.api.energy.IEnergyHandler storage = (cofh.api.energy.IEnergyHandler) tile;
			return storage.getEnergyStored(side);
		}
		return 0;
	}

	public static int getHandlerMax(TileEntity tile, EnumFacing side) {
		if (tile instanceof cofh.api.energy.IEnergyHandler) {
			cofh.api.energy.IEnergyHandler storage = (cofh.api.energy.IEnergyHandler) tile;
			return storage.getMaxEnergyStored(side);
		}
		return 0;
	}

}
