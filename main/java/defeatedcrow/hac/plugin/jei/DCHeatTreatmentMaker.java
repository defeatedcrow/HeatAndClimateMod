package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.IHeatTreatment;
import defeatedcrow.hac.main.api.MainAPIManager;
import mezz.jei.api.IModRegistry;

public final class DCHeatTreatmentMaker {

	private DCHeatTreatmentMaker() {}

	public static void register(IModRegistry registry) {
		List<IHeatTreatment> l = Lists.newArrayList(MainAPIManager.heatTreatmentRegister.getRecipeList());
		registry.addRecipes(l, "dcs_climate.treatment");
	}

}
