package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.client.model.ModelDesktopAccessories_H;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityDesktopAccessories_I extends Render<DCEntityBase> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/desktop_i.png");
	private static final DCTileModelBase MODEL = new ModelDesktopAccessories_H();

	public RenderEntityDesktopAccessories_I(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.3F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(DCEntityBase entity, double x, double y, double z, float yaw,
			float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.4375F, (float) z);
		GlStateManager.scale(-0.9F, -0.9F, 0.9F);
		this.bindTexture(TEX);

		float rotX = 0F;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F);

		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(DCEntityBase entity) {
		return TEX;
	}

}
