package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.machine.client.model.ModelMagneticHover;
import defeatedcrow.hac.machine.entity.EntityMagneticHover;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMagneticHover extends Render<EntityMagneticHover> {

	private static final ResourceLocation BLUE_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magnetic_hover_blue.png");
	private static final ModelMagneticHover MODEL = new ModelMagneticHover(false);

	public RenderMagneticHover(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 1.0F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityMagneticHover entity, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		// GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y + 1.25F, (float) z);
		GlStateManager.scale(-1.2F, -1.2F, 1.2F);
		this.bindTexture(BLUE_TEX);

		float rotX = 0F;
		float rotY = 0F;
		float rotZ = 0F;

		float swing = 0F;
		float wheel = 0F;

		if (entity != null) {
			// rotY = 180 + entity.rotationYaw;
			rotY = 180F + entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;
			// swing = entity.headYaw;
			swing = 45F;

			if (entity.posY - entity.prevPosY > 0.5F) {
				swing = 43F;
			}
			if (entity.posY - entity.prevPosY < -0.5F) {
				swing = 47F;
			}
		}

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(swing, null);

		// RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagneticHover entity) {
		return BLUE_TEX;
	}

}
