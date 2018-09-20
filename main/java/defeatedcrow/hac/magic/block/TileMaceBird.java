package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCAirflow;

public class TileMaceBird extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (current != null && current.getAirflow() == DCAirflow.WIND) {
			return true;
		}
		return false;
	}

}
