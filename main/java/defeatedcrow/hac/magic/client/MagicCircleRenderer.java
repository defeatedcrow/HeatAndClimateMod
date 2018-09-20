package defeatedcrow.hac.magic.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;

@SideOnly(Side.CLIENT)
public class MagicCircleRenderer extends Render<EntityMobBarrier> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_crystal.png");
	private static final ModelMagicCircle MODEL = new ModelMagicCircle(false);

	public MagicCircleRenderer(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.5F;
		this.shadowOpaque = 0.75F;
	}

	@Override
	public void doRender(EntityMobBarrier entity, double x, double y, double z, float yaw, float partialTicks) {

		float height = entity.height * 0.4F;
		DCFoodModelBase model = this.getEntityModel();
		ResourceLocation tex = this.getTexture();

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + height, (float) z);
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);

		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);

		GlStateManager.color(2.0F, 2.0F, 2.0F, 0.75F);
		this.bindTexture(tex);

		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F * 6.0F);

		GlStateManager.scale(-0.99F, 0.99F, 0.99F);
		model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F * 6.0F);

		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMobBarrier entity) {
		return TEX;
	}

	protected ResourceLocation getTexture() {
		return TEX;
	}

	protected DCFoodModelBase getEntityModel() {
		return MODEL;
	}
}