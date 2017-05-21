package defeatedcrow.hac.plugin.waila;

import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin
public class DCWailaPlugin implements IWailaPlugin {

	@Override
	public void register(IWailaRegistrar registrar) {
		HUDHandlerClimateData.register(registrar);
		HUDHandlerChamber.register(registrar);
		HUDHandlerTorque.register(registrar);
	}
}
