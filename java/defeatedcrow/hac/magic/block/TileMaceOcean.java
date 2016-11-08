package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCHumidity;

public class TileMaceOcean extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (current != null && current.getHumidity() == DCHumidity.UNDERWATER) {
			return true;
		}
		return false;
	}

}
