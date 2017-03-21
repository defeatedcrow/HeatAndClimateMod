package defeatedcrow.hac.plugin.cofh;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

/**
 * RFを保持できる装置を操作するための中継クラス。
 */
public class RFDeviceHelper {

	private RFDeviceHelper() {}

	public static boolean isRFConnector(TileEntity tile) {
		return tile instanceof IEnergyConnection || tile instanceof IEnergyStorage;
	}

	public static boolean isRFReceiver(TileEntity tile) {
		return tile instanceof IEnergyReceiver;
	}

	public static int inputEnergy(EnumFacing dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFReceiver(tile)) {
			IEnergyReceiver handler = (IEnergyReceiver) tile;

			if (handler.canConnectEnergy(dir)) {
				ret = handler.receiveEnergy(dir, amount, simulate);
			}
		}

		return ret;
	}

	public static boolean isRFProvider(TileEntity tile) {
		return tile instanceof IEnergyProvider;
	}

	public static int extractEnergy(EnumFacing dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFProvider(tile)) {
			IEnergyProvider handler = (IEnergyProvider) tile;

			if (handler.canConnectEnergy(dir)) {
				ret = handler.extractEnergy(dir, amount, simulate);
			}
		}

		return ret;
	}

}
