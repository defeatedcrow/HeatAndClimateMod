package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.magic.entity.EntityOwlDoll;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OwlDollRenderer extends Render<EntityOwlDoll> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/owl_doll.png");
	private static final ModelOwlDoll MODEL = new ModelOwlDoll();

	public OwlDollRenderer(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.5F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityOwlDoll entity, double x, double y, double z, float yawIn, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.5D, (float) z);
		GlStateManager.scale(-1F, -1F, 1F);
		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);
		this.bindTexture(TEX);

		float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
		float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;

		MODEL.render(entity, 0, 0, 0, yaw, pitch, 0.0625F);

		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOwlDoll entity) {
		return TEX;
	}

}
