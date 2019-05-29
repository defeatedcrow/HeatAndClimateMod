package defeatedcrow.hac.plugin.jei;

import java.util.List;

import defeatedcrow.hac.main.api.IDCInfoData;
import defeatedcrow.hac.main.api.MainAPIManager;
import mezz.jei.api.IModRegistry;

public final class DCInfoDataMaker {

	private DCInfoDataMaker() {}

	public static void register(IModRegistry registry) {
		List<IDCInfoData> l = MainAPIManager.infoRegister.getList();
		registry.addRecipes(l, "dcs_climate.info");
	}

}
