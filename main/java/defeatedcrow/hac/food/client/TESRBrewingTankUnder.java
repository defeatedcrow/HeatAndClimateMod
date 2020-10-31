package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockBrewing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBrewingTankUnder extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/brewing_tank_sus.png";
	private static final ModelBlockBrewing MODEL = new ModelBlockBrewing();

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
