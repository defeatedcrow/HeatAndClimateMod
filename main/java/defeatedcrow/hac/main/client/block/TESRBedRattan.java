package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.client.model.ModelBedRattan;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBedRattan extends TESRBedIron {

	private static final DCTileModelBase MODEL = new ModelBedRattan();

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/bed_rattan.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
