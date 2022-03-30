package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.main.api.IColorChangeTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;

public class TileChandelierGypsum extends TileEntity implements IColorChangeTile {

	// color

	@Override
	public int getColor() {
		int i = DCState.getInt(world.getBlockState(getPos()), DCState.TYPE16);
		int color = i >> 2;
		return color;
	}

	@Override
	public void setColor(int num) {
		num = num & 3;
		num = num << 2;
		IBlockState current = world.getBlockState(pos);
		int type = DCState.getInt(current, DCState.TYPE16);
		type = type & 3;
		IBlockState next = current.withProperty(DCState.TYPE16, type + num);
		world.setBlockState(pos, next, 3);
	}

	@Override
	public boolean rotateColor() {
		int c = getColor();
		c++;
		setColor(c);
		return true;
	}

}
