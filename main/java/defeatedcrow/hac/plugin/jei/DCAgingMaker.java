package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IAgingRecipeDC;
import mezz.jei.api.IModRegistry;

public final class DCAgingMaker {

	private DCAgingMaker() {}

	public static void register(IModRegistry registry) {
		List<IAgingRecipeDC> l = Lists.newArrayList(MainAPIManager.brewingRegister.getAgingList());
		registry.addRecipes(l, "dcs_climate.aging");
	}

}
