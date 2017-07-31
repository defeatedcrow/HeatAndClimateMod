package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.machine.client.model.ModelScooter;
import defeatedcrow.hac.machine.entity.EntityScooter;
import defeatedcrow.hac.main.client.model.ModelDynamite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderScooter extends Render<EntityScooter> {

	private static final ResourceLocation ORANGE_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/scooter_orange.png");
	private static final ResourceLocation BLUE_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/scooter_blue.png");
	private static final ResourceLocation WHITE_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/scooter_white.png");
	private static final ResourceLocation BLACK_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/scooter_black.png");
	private static final ModelScooter MODEL = new ModelScooter(false);
	private static final ModelDynamite MODEL2 = new ModelDynamite(false);

	public RenderScooter(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 1.0F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityScooter entity, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		// GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y + 1.25F, (float) z);
		GlStateManager.scale(-0.9F, -0.9F, 0.9F);
		this.bindTexture(ORANGE_TEX);
		int i = entity.getColorID();
		if (i == 1) {
			this.bindTexture(BLUE_TEX);
		}
		if (i == 2) {
			this.bindTexture(WHITE_TEX);
		}
		if (i == 3) {
			this.bindTexture(BLACK_TEX);
		}

		float rotX = 0F;
		float rotY = 0F;
		float rotZ = 0F;

		float swing = 0F;
		float wheel = 0F;

		if (entity != null) {
			// rotY = 180 + entity.rotationYaw;
			rotY = 180F + entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;
			// swing = entity.headYaw;
			swing = entity.prevHeadYaw + (entity.headYaw - entity.prevHeadYaw) * partialTicks;

			if (swing > 60F) {
				swing = 60F;
			}
			if (swing < -60F) {
				swing = -60F;
			}
		}

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(swing, null);
		MODEL.renderTire(swing, wheel, 0.0625F);

		// RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityScooter entity) {
		return ORANGE_TEX;
	}

}
