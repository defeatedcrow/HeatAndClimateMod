package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceBurn extends TESRMace {

	private final ResourceLocation BURN = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_burn.png");

	@Override
	protected ResourceLocation getTex() {
		return BURN;
	}

}
