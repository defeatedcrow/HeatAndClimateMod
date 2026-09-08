package defeatedcrow.hac.plugin.waila;

import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;

public class DCWailaPlugin implements IWailaPlugin {

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		HUDHandlerClimateData.register(registration);
		HUDHandlerCropData.register(registration);
		HUDWaterHeadData.register(registration);
	}

}
