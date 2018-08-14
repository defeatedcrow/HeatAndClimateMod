package defeatedcrow.hac.machine.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileWindmill_EX extends TileWindmill {

	@Override
	public float getGearTier() {
		return 128.0F;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(-8, -8, -8), getPos().add(8, 8, 8));
		return bb;
	}

}
