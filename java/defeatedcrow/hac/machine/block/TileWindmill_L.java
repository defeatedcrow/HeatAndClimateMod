package defeatedcrow.hac.machine.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.client.ModelWindmill_L;

public class TileWindmill_L extends TileWindmill {

	@SideOnly(Side.CLIENT)
	private final ModelWindmill_L model = new ModelWindmill_L();

	@Override
	@SideOnly(Side.CLIENT)
	public DCTileModelBase getModel() {
		return model;
	}

	@Override
	public float getGearTier() {
		return 8.0F;
	}

}
