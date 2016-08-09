package defeatedcrow.hac.machine.block;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.energy.ICrankReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;

public class TileRedBox extends TileTorqueBase implements ICrankReceiver {

	@SideOnly(Side.CLIENT)
	private defeatedcrow.hac.machine.client.ModelGearBox model;

	@Override
	@SideOnly(Side.CLIENT)
	protected void createModel() {
		if (model == null)
			model = new defeatedcrow.hac.machine.client.ModelGearBox();
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return !isInputSide(side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public defeatedcrow.hac.core.client.base.DCTileModelBase getModel() {
		return model;
	}

	@Override
	public boolean isPressed() {
		return BlockRedBox.isLit(worldObj, pos);
	}

	@Override
	public boolean isMaxPressed() {
		return false;
	}

	@Override
	public void setPressed(boolean flag) {
		BlockRedBox.changeLitState(worldObj, pos, flag);
	}

	@Override
	public void setMaxPressed(boolean flag) {
	}

}
