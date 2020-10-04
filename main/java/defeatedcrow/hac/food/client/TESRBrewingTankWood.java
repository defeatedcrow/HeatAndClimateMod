package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockBrewingWood;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBrewingTankWood extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/barrel_brewing.png";
	private static final ModelBlockBrewingWood MODEL = new ModelBlockBrewingWood();

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
