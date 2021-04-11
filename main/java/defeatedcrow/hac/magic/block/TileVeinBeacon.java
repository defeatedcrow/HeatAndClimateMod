package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileVeinBeacon extends DCTileEntity {

	public int living;
	public int lastDay;
	public int maxLiving = 5;

	public void setMaxTime(int i) {
		maxLiving = i;
	}

	@Override
	public void updateTile() {
		if (!getWorld().isRemote) {
			int day = (int) (DCTimeHelper.totalTime(world) / 24000L);
			if (lastDay == 0) {
				lastDay = day;
			}
			if (day > 0 && day != living) {
				living = day;
			} else if ((living - lastDay) > maxLiving) {
				getWorld().setBlockToAir(getPos());
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		living = compound.getInteger("LivingDay");
		lastDay = compound.getInteger("LastDay");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("LivingDay", living);
		compound.setInteger("LastDay", lastDay);
		return compound;
	}

	/* 描画距離のfix */
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65535.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		Block type = getBlockType();
		BlockPos pos = getPos();
		bb = new AxisAlignedBB(pos.getX(), 1, pos.getZ(), pos.getX() + 1, 255, pos.getZ() + 1);
		return bb;
	}

}
