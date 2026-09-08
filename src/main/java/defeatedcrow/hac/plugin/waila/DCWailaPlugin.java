package defeatedcrow.hac.plugin.waila;

import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin("dcs_climate")
public class DCWailaPlugin implements IWailaPlugin {

	@Override
	public void register(IWailaCommonRegistration registration) {
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		HUDHandlerClimateData.register(registration);
		HUDHandlerCropData.register(registration);
		HUDWaterHeadData.register(registration);
	}

}
