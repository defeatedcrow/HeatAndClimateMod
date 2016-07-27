package defeatedcrow.hac.machine.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;

@SideOnly(Side.CLIENT)
public class TB_ShaftTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/shaft_brass_t_b.png";
	}

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return te.getModel();
	}

}
