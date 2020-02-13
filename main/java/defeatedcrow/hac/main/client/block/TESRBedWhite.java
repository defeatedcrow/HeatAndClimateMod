package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.client.model.ModelBedStandard;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBedWhite extends TESRBedIron {

	private static final DCTileModelBase MODEL = new ModelBedStandard();

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/bed_white.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
