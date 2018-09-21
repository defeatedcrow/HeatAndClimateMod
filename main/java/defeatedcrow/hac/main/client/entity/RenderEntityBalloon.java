package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.client.model.ModelCrowBalloon;
import defeatedcrow.hac.main.entity.EntityCrowBalloon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityBalloon extends Render<EntityCrowBalloon> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate", "textures/entity/baloon_crow.png");
	private static final ModelCrowBalloon MODEL = new ModelCrowBalloon(false);

	public RenderEntityBalloon(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.5F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityCrowBalloon entity, double x, double y, double z, float yaw, float partialTicks) {

		float f = entity.swingPrev + (entity.swing - entity.swingPrev) * partialTicks;
		double f2 = Math.sin(f * Math.PI / 180F) * 0.25D;

		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y + 2.0F + f2, (float) z);
		GlStateManager.scale(-1F, -1F, 1F);
		this.bindTexture(TEX);

		float rotX = -entity.rotationPitch;
		float rotY = 180 - entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, null);

		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCrowBalloon entity) {
		return TEX;
	}

}
