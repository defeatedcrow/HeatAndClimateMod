package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.client.model.ModelErgonomicChair;
import defeatedcrow.hac.main.entity.EntityErgonomicChair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityErgonomicChair extends Render<EntityErgonomicChair> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate", "textures/entity/ergonomic_chair_black.png");
	private static final ResourceLocation TEX2 = new ResourceLocation("dcs_climate", "textures/entity/ergonomic_chair_white.png");
	private static final ModelErgonomicChair MODEL = new ModelErgonomicChair(false);

	public RenderEntityErgonomicChair(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.5F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityErgonomicChair entity, double x, double y, double z, float yaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.6F, (float) z);
		GlStateManager.scale(-1.2F, -1.2F, 1.2F);
		this.bindTexture(getEntityTexture(entity));

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, null);

		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityErgonomicChair entity) {
		if (entity.getColor() == 1) {
			return TEX2;
		}
		return TEX;
	}

}
