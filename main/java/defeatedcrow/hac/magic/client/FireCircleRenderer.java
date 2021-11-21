package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class FireCircleRenderer extends MagicCircleRenderer {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_red.png");
	private static final ResourceLocation TEX_L1 = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_red_layer1.png");
	private static final ResourceLocation TEX_L2 = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_red_layer2.png");

	public FireCircleRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMobBarrier entity) {
		return TEX;
	}

	protected ResourceLocation getLayer1() {
		return TEX_L1;
	}

	protected ResourceLocation getLayer2() {
		return TEX_L2;
	}

}
