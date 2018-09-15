package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceGlory extends TESRMace {

	private final ResourceLocation GLORY = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_glory.png");

	@Override
	protected ResourceLocation getTex() {
		return GLORY;
	}

}
