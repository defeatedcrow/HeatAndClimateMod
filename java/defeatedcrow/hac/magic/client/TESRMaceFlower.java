package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.ResourceLocation;

public class TESRMaceFlower extends TESRMace {

	private final ResourceLocation FLOWER = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_flower.png");

	@Override
	protected ResourceLocation getTex() {
		return FLOWER;
	}

}
