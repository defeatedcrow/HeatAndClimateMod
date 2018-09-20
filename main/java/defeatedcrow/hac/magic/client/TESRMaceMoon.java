package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceMoon extends TESRMace {

	private final ResourceLocation MOON = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_moon.png");

	@Override
	protected ResourceLocation getTex() {
		return MOON;
	}

}
