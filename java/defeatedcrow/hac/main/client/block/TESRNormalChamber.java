package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

public class TESRNormalChamber extends DCLockableTESRBase {

	@Override
	protected String getTexPass(int i) {
		if (i == 1) {
			return "dcs_climate:textures/tiles/normal_chamber_lit.png";
		}
		return "dcs_climate:textures/tiles/normal_chamber.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return new ModelNormalChamber();
	}
}
