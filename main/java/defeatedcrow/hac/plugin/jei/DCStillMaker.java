package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IStillRecipeDC;
import mezz.jei.api.IModRegistry;

public final class DCStillMaker {

	private DCStillMaker() {}

	public static void register(IModRegistry registry) {
		List<IStillRecipeDC> l = Lists.newArrayList(MainAPIManager.brewingRegister.getStillList());
		registry.addRecipes(l, "dcs_climate.still");
	}

}
