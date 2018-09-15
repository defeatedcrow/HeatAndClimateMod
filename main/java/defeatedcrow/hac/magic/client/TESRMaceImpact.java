package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceImpact extends TESRMace {

	private final ResourceLocation MACE = new ResourceLocation(ClimateCore.PACKAGE_ID, "textures/entity/magic/magicmace_impact.png");

	@Override
	protected ResourceLocation getTex() {
		return MACE;
	}

}
