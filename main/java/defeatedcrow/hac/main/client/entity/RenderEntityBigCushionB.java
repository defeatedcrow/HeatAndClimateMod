package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.client.model.ModelBigCution;
import defeatedcrow.hac.main.entity.EntityBigCushionBrown;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityBigCushionB extends Render<EntityBigCushionBrown> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/cushion_brown.png");
	private static final ModelBigCution MODEL = new ModelBigCution(false);

	public RenderEntityBigCushionB(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 1.0F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityBigCushionBrown entity, double x, double y, double z, float yaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.scale(-1F, -1F, 1F);
		this.bindTexture(TEX);

		float rotX = -entity.rotationPitch;
		float rotY = 180 + entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, null);

		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBigCushionBrown entity) {
		return TEX;
	}

}
