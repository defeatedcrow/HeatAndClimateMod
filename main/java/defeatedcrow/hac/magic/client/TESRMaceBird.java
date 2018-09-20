package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceBird extends TESRMace {

	private final ResourceLocation BIRD = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_bird.png");

	@Override
	protected ResourceLocation getTex() {
		return BIRD;
	}

}
