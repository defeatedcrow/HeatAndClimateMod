package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCHumidity;

public class TileMaceDry extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (current != null && current.getHumidity() == DCHumidity.DRY) {
			return true;
		}
		return false;
	}

}
