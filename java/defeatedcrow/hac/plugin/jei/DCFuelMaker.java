package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.IFluidFuel;
import defeatedcrow.hac.main.api.MainAPIManager;
import mezz.jei.api.IModRegistry;

public final class DCFuelMaker {

	private DCFuelMaker() {}

	public static void register(IModRegistry registry) {
		List<IFluidFuel> l = Lists.newArrayList(MainAPIManager.fuelRegister.getFuelList());
		registry.addRecipes(l, "dcs_climate.fuel");
	}

}
