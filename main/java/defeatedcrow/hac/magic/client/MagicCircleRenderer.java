package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MagicCircleRenderer extends Render<EntityMobBarrier> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_black.png");
	private static final ResourceLocation TEX_L1 = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_black_layer1.png");
	private static final ResourceLocation TEX_L2 = new ResourceLocation("dcs_climate",
			"textures/entity/magic/circle_black_layer2.png");
	private static final ModelMagicCircle MODEL = new ModelMagicCircle(false);

	public MagicCircleRenderer(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0F;
		this.shadowOpaque = 0F;
	}

	@Override
	public void doRender(EntityMobBarrier entity, double x, double y, double z, float yaw, float partialTicks) {

		float height = entity.height * 0.4F;
		float range = entity.getRange();
		int count1 = entity.getTotalAge();
		int count2 = (int) (entity.getEntityWorld().getWorldInfo().getWorldTime() % 40);

		float alpha1 = (20F - count1) - partialTicks;
		float alpha2 = count2 > 20 ? 40F - count2 - partialTicks : count2 + partialTicks;

		renderTexture(getEntityTexture(entity), x, y, z, partialTicks, range, 1.0F);

		if (count1 <= 20) {
			alpha1 /= 20F;
			if (alpha1 > 1F) {
				alpha1 = 1F;
			}
			renderTexture(getLayer2(), x, y + 0.02F, z, partialTicks, range, alpha1);
		}

		alpha2 /= 40F;
		alpha2 += 0.25F;
		if (alpha2 > 1F) {
			alpha2 = 1F;
		}
		renderTexture(getLayer1(), x, y + 0.01F, z, partialTicks, range, alpha2);

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMobBarrier entity) {
		return TEX;
	}

	protected ResourceLocation getLayer1() {
		return TEX_L1;
	}

	protected ResourceLocation getLayer2() {
		return TEX_L2;
	}

	protected DCFoodModelBase getEntityModel() {
		return MODEL;
	}

	private void renderTexture(ResourceLocation tex, double x, double y, double z, float partialTicks, float range,
			float alpha) {
		// TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		// TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(tex);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		GlStateManager.enableBlend();
		GlStateManager
				.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		int i2 = 15728880;
		int j2 = i2 % 65536;
		int k2 = i2 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j2, k2);
		GlStateManager.translate((float) x, (float) y, (float) z);
		float f2 = 0.125F;
		float f = range;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		this.bindTexture(tex);
		float uMin = 0F;
		float vMin = 0F;
		float uMax = 1F;
		float vMax = 1F;

		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMax, vMin).endVertex();

		tessellator.draw();
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
}
