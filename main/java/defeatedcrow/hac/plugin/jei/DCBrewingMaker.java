package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.IBrewingRecipeDC;
import mezz.jei.api.IModRegistry;

public final class DCBrewingMaker {

	private DCBrewingMaker() {}

	public static void register(IModRegistry registry) {
		List<IBrewingRecipeDC> l = Lists.newArrayList(MainAPIManager.brewingRegister.getBrewingList());
		registry.addRecipes(l, "dcs_climate.brewing");
	}

}
