package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceOcean extends TESRMace {

	private final ResourceLocation OCEAN = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_ocean.png");

	@Override
	protected ResourceLocation getTex() {
		return OCEAN;
	}

}
