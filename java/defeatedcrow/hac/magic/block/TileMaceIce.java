package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCHeatTier;

public class TileMaceIce extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (current != null && current.getHeat().getTier() < DCHeatTier.COOL.getTier()) {
			return true;
		}
		return false;
	}

}
