package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;

public class TileMaceBurn extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (current != null && current.getHumidity() != DCHumidity.UNDERWATER
				&& current.getHeat().getTier() > DCHeatTier.KILN.getTier())
			return true;
		return false;
	}

}
