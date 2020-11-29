package defeatedcrow.hac.magic.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.magic.entity.EntityMagicCushion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MagicCushionRenderer extends Render<EntityMagicCushion> {

	public static final String PARTICLE_TEX = "dcs_climate:particles/amnesia";

	public MagicCushionRenderer(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.5F;
		this.shadowOpaque = 0.5F;
	}

	@Override
	public void doRender(EntityMagicCushion entity, double x, double y, double z, float yaw, float partialTicks) {

		float height = entity.height * 0.4F;
		float width = 1.5F;
		if (!entity.getPassengers().isEmpty() && entity.getPassengers().get(0) != null) {
			width = entity.getPassengers().get(0).width * 2F;
		}
		renderEffect(x, y, z, partialTicks, width);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagicCushion entity) {
		return new ResourceLocation(PARTICLE_TEX);
	}

	protected ResourceLocation getTexture() {
		return new ResourceLocation(PARTICLE_TEX);
	}

	protected DCFoodModelBase getEntityModel() {
		return null;
	}

	private void renderEffect(double x, double y, double z, float partialTicks, float width) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite sprite = texturemap.getAtlasSprite(PARTICLE_TEX);
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		int i2 = 15728880;
		int j2 = i2 % 65536;
		int k2 = i2 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j2, k2);
		GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);
		float f2 = width * 0.5F;
		float f = width * 0.5F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float uMin = sprite.getMinU();
		float vMin = sprite.getMinV();
		float uMax = sprite.getMaxU();
		float vMax = sprite.getMaxV();

		vertexbuffer.pos(-f, 0, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, 0, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(-f, 0, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, 0, -f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(f, 0, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, 0, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(f, 0, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, 0, -f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(-f, 0, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, 0, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(f, 0, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, 0, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(-f, 0, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, 0, -f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMax).endVertex();

		vertexbuffer.pos(f, 0, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, 0, -f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMin, vMax).endVertex();

		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();

	}
}