package defeatedcrow.hac.food.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileBrewingTankUnder extends TileBrewingTankWood {

	@Override
	public int getProcessTime() {
		return 20;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(0, -1, 0), getPos().add(1, 1, 1));
		return bb;
	}

}
