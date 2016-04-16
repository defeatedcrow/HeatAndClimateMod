package defeatedcrow.hac.main.client;

import defeatedcrow.hac.core.base.DCSidedBlock;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.container.BlockLogCont;

public class ClientMainProxy extends CommonMainProxy {

	@Override
	public void loadConst() {
		JsonBakery.instance.addTex(BlockLogCont.getTexList());
	}

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

	@Override
	public void addSidedBlock(DCSidedBlock block) {
		JsonBakery.instance.regDummySidedModel(block);
	}

	@Override
	public void addTBBlock(DCSidedBlock block) {
		JsonBakery.instance.regDummyTBModel(block);
	}

}
