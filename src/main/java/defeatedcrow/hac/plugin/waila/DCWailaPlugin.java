package defeatedcrow.hac.plugin.waila;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;

public class DCWailaPlugin implements IWailaPlugin {

	@Override
	public void register(IRegistrar registrar) {
		HUDHandlerClimateData.register(registrar);
		HUDHandlerCropData.register(registrar);
	}

}
