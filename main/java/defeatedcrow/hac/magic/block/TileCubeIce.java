package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.base.DCTileEntity;
import net.minecraft.nbt.NBTTagCompound;

public class TileCubeIce extends DCTileEntity {

	public int living;

	@Override
	public void onTickUpdate() {
		living++;
		if (living > 1200) {
			if (!getWorld().isRemote)
				getWorld().setBlockToAir(getPos());
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.living = compound.getByte("LivingTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setByte("LivingTime", (byte) living);
		return compound;
	}

}
