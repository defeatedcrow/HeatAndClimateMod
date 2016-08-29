package defeatedcrow.hac.machine.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileWindmill_L extends TileWindmill {

	@SideOnly(Side.CLIENT)
	private defeatedcrow.hac.machine.client.ModelWindmill_L model;

	@Override
	@SideOnly(Side.CLIENT)
	protected void createModel() {
		if (model == null)
			model = new defeatedcrow.hac.machine.client.ModelWindmill_L();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public defeatedcrow.hac.core.client.base.DCTileModelBase getModel() {
		return model;
	}

	@Override
	public float getGearTier() {
		return 8.0F;
	}

}
