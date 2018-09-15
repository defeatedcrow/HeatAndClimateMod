package defeatedcrow.hac.machine.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileWindmill_L extends TileWindmill {

	@Override
	public float getGearTier() {
		return 32.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(-2, -2, -2), getPos().add(2, 2, 2));
		return bb;
	}

}
