package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockStill;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRStill extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/still.png";
	private static final ModelBlockStill MODEL = new ModelBlockStill();

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
