package defeatedcrow.hac.machine.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;

@SideOnly(Side.CLIENT)
public class S_CrankTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/crank_brass.png";
	}

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return te.getModel();
	}

}
