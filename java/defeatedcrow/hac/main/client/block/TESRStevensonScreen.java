package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.client.model.ModelStevensonScreen;

public class TESRStevensonScreen extends DCTESRBase {

	private final ModelStevensonScreen model = new ModelStevensonScreen();

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/stevenson_screen.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return model;
	}

}
