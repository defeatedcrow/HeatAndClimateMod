package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceIce extends TESRMace {

	private final ResourceLocation ICE = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_ice.png");

	@Override
	protected ResourceLocation getTex() {
		return ICE;
	}

}
