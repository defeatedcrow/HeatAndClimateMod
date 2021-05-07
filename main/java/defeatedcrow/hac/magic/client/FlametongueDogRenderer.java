package defeatedcrow.hac.magic.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class FlametongueDogRenderer extends RenderWolf {

	private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("dcs_climate",
			"textures/entity/flametongue.png");

	public FlametongueDogRenderer(RenderManager rm) {
		super(rm);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWolf entity) {
		return WOLF_TEXTURES;
	}

}
