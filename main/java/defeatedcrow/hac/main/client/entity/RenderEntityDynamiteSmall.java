package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.client.model.ModelDynamiteSmall;
import defeatedcrow.hac.main.entity.EntityDynamite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityDynamiteSmall extends Render<EntityDynamite> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/blocks/ores/dustblock_crystal.png");
	private static final ModelDynamiteSmall MODEL = new ModelDynamiteSmall(false);

	public RenderEntityDynamiteSmall(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.5F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityDynamite entity, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y + 0.25D, (float) z);
		GlStateManager.scale(-0.5F, -0.5F, 0.5F);
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
	protected ResourceLocation getEntityTexture(EntityDynamite entity) {
		return TEX;
	}

}
