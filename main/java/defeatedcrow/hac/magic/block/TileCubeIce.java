package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.base.DCTileEntity;
import net.minecraft.nbt.NBTTagCompound;

public class TileCubeIce extends DCTileEntity {

	public int living;
	public int maxLiving = 1200;

	public void setMaxTime(int i) {
		maxLiving = i;
	}

	@Override
	public void onTickUpdate() {
		living++;
		if (living > maxLiving) {
			if (!getWorld().isRemote)
				getWorld().setBlockToAir(getPos());
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.living = compound.getInteger("LivingTime");
		this.maxLiving = compound.getInteger("MaxTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("LivingTime", (byte) living);
		compound.setInteger("MaxTime", (byte) maxLiving);
		return compound;
	}

}
