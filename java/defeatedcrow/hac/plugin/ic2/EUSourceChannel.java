package defeatedcrow.hac.plugin.ic2;

import ic2.api.energy.prefab.BasicSource;
import ic2.api.energy.tile.IEnergyAcceptor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

//中継用クラス
public class EUSourceChannel extends BasicSource implements IEUSourceChannel {

	public EUSourceChannel(TileEntity parent1, int capacity1, int tier1) {
		super(parent1, capacity1, tier1);
	}

	@Override
	public void readFromNBT2(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT2(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
	}

	@Override
	public void invalidate2() {
		super.invalidate();
	}

	@Override
	public void onChunkUnload2() {
		super.onChunkUnload();
	}

	@Override
	public void updateEntity2() {
		super.update();
	}

	@Override
	public double addEnergy2(double amount) {
		return super.addEnergy(amount);
	}

	@Override
	public double getEnergyStored() {
		return super.getEnergyStored();

	}

	@Override
	public void onLoaded2() {
		super.onLoaded();
	}

	@Override
	public boolean emitsEnergyTo2(IEnergyAcceptor receiver, EnumFacing direction) {
		return super.emitsEnergyTo(receiver, direction);
	}

	@Override
	public double getOfferedEnergy2() {
		return super.getOfferedEnergy();
	}

	@Override
	public void drawEnergy2(double amount) {
		super.drawEnergy(amount);
	}

	@Override
	public int getSourceTier2() {
		return super.getSourceTier();
	}

}
