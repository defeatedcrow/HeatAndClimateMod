package defeatedcrow.hac.core;

import defeatedcrow.hac.core.climate.ClientClimateData;

public class ClientProxyDC extends CommonProxyDC {

	@SuppressWarnings("resource")
	@Override
	public void updatePlayerClimate() {
		ClientClimateData.INSTANCE.updatePlayerClimate();
	}
}
