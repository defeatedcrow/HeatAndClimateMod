package defeatedcrow.hac.plugin.cofh;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

/**
 * RFを保持できる装置を操作するための中継クラス。
 */
public class RFDeviceHelper {

	private RFDeviceHelper() {}

	public static boolean isRFConnector(TileEntity tile) {
		return tile instanceof cofh.redstoneflux.api.IEnergyConnection
				|| tile instanceof cofh.redstoneflux.api.IEnergyStorage;
	}

	public static boolean isRFStorage(TileEntity tile) {
		return tile instanceof cofh.redstoneflux.api.IEnergyStorage;
	}

	public static int getStorageAmount(TileEntity tile) {
		if (tile instanceof cofh.redstoneflux.api.IEnergyStorage) {
			cofh.redstoneflux.api.IEnergyStorage storage = (cofh.redstoneflux.api.IEnergyStorage) tile;
			return storage.getEnergyStored();
		}
		return 0;
	}

	public static int getStorageMax(TileEntity tile) {
		if (tile instanceof cofh.redstoneflux.api.IEnergyStorage) {
			cofh.redstoneflux.api.IEnergyStorage storage = (cofh.redstoneflux.api.IEnergyStorage) tile;
			return storage.getMaxEnergyStored();
		}
		return 0;
	}

	public static boolean isRFReceiver(TileEntity tile) {
		return tile instanceof cofh.redstoneflux.api.IEnergyReceiver;
	}

	public static int inputEnergy(EnumFacing dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFReceiver(tile)) {
			cofh.redstoneflux.api.IEnergyReceiver handler = (cofh.redstoneflux.api.IEnergyReceiver) tile;

			if (handler.canConnectEnergy(dir)) {
				ret = handler.receiveEnergy(dir, amount, simulate);
			}
		}

		return ret;
	}

	public static boolean isRFProvider(TileEntity tile) {
		return tile instanceof cofh.redstoneflux.api.IEnergyProvider;
	}

	public static int extractEnergy(EnumFacing dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFProvider(tile)) {
			cofh.redstoneflux.api.IEnergyProvider handler = (cofh.redstoneflux.api.IEnergyProvider) tile;

			if (handler.canConnectEnergy(dir)) {
				ret = handler.extractEnergy(dir, amount, simulate);
			}
		}

		return ret;
	}

	public static boolean isRFHandler(TileEntity tile) {
		return tile instanceof cofh.redstoneflux.api.IEnergyHandler;
	}

	public static int getHandlerAmount(TileEntity tile, EnumFacing side) {
		if (tile instanceof cofh.redstoneflux.api.IEnergyHandler) {
			cofh.redstoneflux.api.IEnergyHandler storage = (cofh.redstoneflux.api.IEnergyHandler) tile;
			return storage.getEnergyStored(side);
		}
		return 0;
	}

	public static int getHandlerMax(TileEntity tile, EnumFacing side) {
		if (tile instanceof cofh.redstoneflux.api.IEnergyHandler) {
			cofh.redstoneflux.api.IEnergyHandler storage = (cofh.redstoneflux.api.IEnergyHandler) tile;
			return storage.getMaxEnergyStored(side);
		}
		return 0;
	}

}
