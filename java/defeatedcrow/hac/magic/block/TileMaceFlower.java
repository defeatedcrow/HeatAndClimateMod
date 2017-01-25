package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;

public class TileMaceFlower extends TileMaceBase {

	@Override
	protected boolean checkEnvironment() {
		if (current != null && current.getHumidity() == DCHumidity.WET
				&& current.getHeat().getTier() > DCHeatTier.COLD.getTier()
				&& current.getHeat().getTier() < DCHeatTier.HOT.getTier())
			return true;
		return false;
	}

}
