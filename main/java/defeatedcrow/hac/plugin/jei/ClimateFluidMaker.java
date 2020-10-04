package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import mezz.jei.api.IModRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public final class ClimateFluidMaker {
	private ClimateFluidMaker() {}

	public static void register(IModRegistry registry) {
		List<DCFluidInfo> list = Lists.newArrayList();
		for (Fluid f : FluidRegistry.getRegisteredFluids().values()) {
			if (f != null) {
				DCFluidInfo info = new DCFluidInfo(f);
				if (!info.cup.isEmpty() || !info.bucket.isEmpty()) {
					list.add(info);
				}
			}
		}
		registry.addRecipes(list, "dcs_climate.drink");
	}
}
