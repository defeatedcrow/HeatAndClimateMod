package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.magic.block.TileInfernalFlame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRInfernalFlame extends TileEntitySpecialRenderer<TileInfernalFlame> {

	public static final ResourceLocation TEX1 = new ResourceLocation("dcs_climate", "entity/magic/infernal_layer_0");
	public static final ResourceLocation TEX2 = new ResourceLocation("dcs_climate", "entity/magic/infernal_layer_1");
	private static final ModelCluster MODEL = new ModelCluster();

	@Override
	public void renderTileEntityAt(TileInfernalFlame te, double x, double y, double z, float partialTicks,
			int destroyStage) {

		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite sprite1 = texturemap.getAtlasSprite(TEX1.toString());
		TextureAtlasSprite sprite2 = texturemap.getAtlasSprite(TEX2.toString());
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.1F, (float) z + 0.5F);
		float f = 1.2F;
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		float f1 = 0.5F;
		float f5 = 0.25F;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float fu = sprite2.getMinU();
		float fv = sprite2.getMinV();
		float fU = sprite2.getMaxU();
		float fV = sprite2.getMaxV();

		float f2u = sprite1.getMinU();
		float f2v = sprite1.getMinV();
		float f2U = sprite1.getMaxU();
		float f2V = sprite1.getMaxV();

		if (i / 2 % 2 == 0) {
			float f10 = fU;
			fU = fu;
			fu = f10;
		}

		vertexbuffer.pos(f1, 0.0F, f5).tex(fU, fV).endVertex();
		vertexbuffer.pos(-f1, 0.0F, f5).tex(fu, fV).endVertex();
		vertexbuffer.pos(-f1, 1.2F, f5).tex(fu, fv).endVertex();
		vertexbuffer.pos(f1, 1.2F, f5).tex(fU, fv).endVertex();

		vertexbuffer.pos(-f1, 0.0F, f5).tex(fU, fV).endVertex();
		vertexbuffer.pos(f1, 0.0F, f5).tex(fu, fV).endVertex();
		vertexbuffer.pos(f1, 1.2F, f5).tex(fu, fv).endVertex();
		vertexbuffer.pos(-f1, 1.2F, f5).tex(fU, fv).endVertex();

		vertexbuffer.pos(f1, 0.0F, -f5).tex(fU, fV).endVertex();
		vertexbuffer.pos(-f1, 0.0F, -f5).tex(fu, fV).endVertex();
		vertexbuffer.pos(-f1, 1.2F, -f5).tex(fu, fv).endVertex();
		vertexbuffer.pos(f1, 1.2F, -f5).tex(fU, fv).endVertex();

		vertexbuffer.pos(-f1, 0.0F, -f5).tex(fU, fV).endVertex();
		vertexbuffer.pos(f1, 0.0F, -f5).tex(fu, fV).endVertex();
		vertexbuffer.pos(f1, 1.2F, -f5).tex(fu, fv).endVertex();
		vertexbuffer.pos(-f1, 1.2F, -f5).tex(fU, fv).endVertex();

		vertexbuffer.pos(f5, 0.0F, -f1).tex(fU, fV).endVertex();
		vertexbuffer.pos(f5, 0.0F, f1).tex(fu, fV).endVertex();
		vertexbuffer.pos(f5, 1.2F, f1).tex(fu, fv).endVertex();
		vertexbuffer.pos(f5, 1.2F, -f1).tex(fU, fv).endVertex();

		vertexbuffer.pos(f5, 0.0F, f1).tex(fU, fV).endVertex();
		vertexbuffer.pos(f5, 0.0F, -f1).tex(fu, fV).endVertex();
		vertexbuffer.pos(f5, 1.2F, -f1).tex(fu, fv).endVertex();
		vertexbuffer.pos(f5, 1.2F, f1).tex(fU, fv).endVertex();

		vertexbuffer.pos(-f5, 0.0F, -f1).tex(fU, fV).endVertex();
		vertexbuffer.pos(-f5, 0.0F, f1).tex(fu, fV).endVertex();
		vertexbuffer.pos(-f5, 1.2F, f1).tex(fu, fv).endVertex();
		vertexbuffer.pos(-f5, 1.2F, -f1).tex(fU, fv).endVertex();

		vertexbuffer.pos(-f5, 0.0F, f1).tex(fU, fV).endVertex();
		vertexbuffer.pos(-f5, 0.0F, -f1).tex(fu, fV).endVertex();
		vertexbuffer.pos(-f5, 1.2F, -f1).tex(fu, fv).endVertex();
		vertexbuffer.pos(-f5, 1.2F, f1).tex(fU, fv).endVertex();

		// 2

		vertexbuffer.pos(f1, 0.0F, f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(-f1, 0.0F, f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(-f1, 1.5F, f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(f1, 1.5F, f1).tex(f2U, f2v).endVertex();

		vertexbuffer.pos(-f1, 0.0F, f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(f1, 0.0F, f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(f1, 1.5F, f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(-f1, 1.5F, f1).tex(f2U, f2v).endVertex();

		vertexbuffer.pos(f1, 0.0F, -f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(-f1, 0.0F, -f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(-f1, 1.5F, -f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(f1, 1.5F, -f1).tex(f2U, f2v).endVertex();

		vertexbuffer.pos(-f1, 0.0F, -f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(f1, 0.0F, -f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(f1, 1.5F, -f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(-f1, 1.5F, -f1).tex(f2U, f2v).endVertex();

		vertexbuffer.pos(f1, 0.0F, -f1).tex(fU, fV).endVertex();
		vertexbuffer.pos(f1, 0.0F, f1).tex(fu, fV).endVertex();
		vertexbuffer.pos(f1, 1.5F, f1).tex(fu, fv).endVertex();
		vertexbuffer.pos(f1, 1.5F, -f1).tex(fU, fv).endVertex();

		vertexbuffer.pos(f1, 0.0F, f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(f1, 0.0F, -f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(f1, 1.5F, -f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(f1, 1.5F, f1).tex(f2U, f2v).endVertex();

		vertexbuffer.pos(-f1, 0.0F, -f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(-f1, 0.0F, f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(-f1, 1.5F, f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(-f1, 1.5F, -f1).tex(f2U, f2v).endVertex();

		vertexbuffer.pos(-f1, 0.0F, f1).tex(f2U, f2V).endVertex();
		vertexbuffer.pos(-f1, 0.0F, -f1).tex(f2u, f2V).endVertex();
		vertexbuffer.pos(-f1, 1.5F, -f1).tex(f2u, f2v).endVertex();
		vertexbuffer.pos(-f1, 1.5F, f1).tex(f2U, f2v).endVertex();

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
}
