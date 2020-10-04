package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import mezz.jei.api.IModRegistry;

public final class MicrobeMaker {

	private MicrobeMaker() {}

	public static void register(IModRegistry registry) {
		List<IMicrobe> l = Lists.newArrayList(MainAPIManager.microbeRegister.getList());
		registry.addRecipes(l, "dcs_climate.microbe");
	}

}
