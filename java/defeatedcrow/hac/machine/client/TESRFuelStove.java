package defeatedcrow.hac.machine.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCModelBase;
import defeatedcrow.hac.core.client.base.DCTESRBase;

@SideOnly(Side.CLIENT)
public class TESRFuelStove extends DCTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/stove_fuel.png";
	}

	@Override
	protected DCModelBase getModel(int i) {
		return new ModelFuelStove();
	}
}
