package defeatedcrow.hac.plugin.ic2;

import ic2.api.energy.tile.IEnergyAcceptor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional.Method;

public interface IEUSourceChannel {

	public void updateEntity2();

	public void onLoaded2();

	public void invalidate2();

	public void onChunkUnload2();

	public void readFromNBT2(NBTTagCompound tag);

	public void writeToNBT2(NBTTagCompound tag);

	public double addEnergy2(double amount);

	public double getEnergyStored();

	public double getOfferedEnergy2();

	public void drawEnergy2(double amount);

	public int getSourceTier2();

	@Method(modid = "IC2")
	boolean emitsEnergyTo2(IEnergyAcceptor receiver, EnumFacing direction);

}
