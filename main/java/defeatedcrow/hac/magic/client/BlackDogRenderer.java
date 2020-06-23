package defeatedcrow.hac.magic.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class BlackDogRenderer extends RenderWolf {

	private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("dcs_climate",
			"textures/entity/blackdog.png");

	public BlackDogRenderer(RenderManager rm) {
		super(rm);
	}

	protected ResourceLocation getEntityTexture(EntityWolf entity) {
		return WOLF_TEXTURES;
	}

}
