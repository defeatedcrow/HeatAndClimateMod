package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceDry extends TESRMace {

	private final ResourceLocation MACE = new ResourceLocation(ClimateCore.PACKAGE_ID, "textures/entity/magic/magicmace_dry.png");

	@Override
	protected ResourceLocation getTex() {
		return MACE;
	}

}
