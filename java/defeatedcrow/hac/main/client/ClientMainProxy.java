package defeatedcrow.hac.main.client;

import defeatedcrow.hac.main.CommonMainProxy;

public class ClientMainProxy extends CommonMainProxy {

	@Override
	public void loadMaterial() {
		super.loadMaterial();
		JsonRegister.load();
	}

	@Override
	public void loadTE() {
		super.loadTE();
		// ClientRegistry.bindTileEntitySpecialRenderer(StoveBase.class, new TESRFuelStove());
	}

}
