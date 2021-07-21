package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.main.block.device.TileSwedishTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class TESRSwedishTorch extends TileEntitySpecialRenderer<TileSwedishTorch> {

	@Override
	public void render(TileSwedishTorch te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			int type = 0;
			boolean lit = false;

			if (te.hasWorld()) {
				int meta = te.getBlockMetadata();
				type = meta & 7;
				if (meta > 7 && meta < 15) {
					lit = true;
				}
			}

			if (lit) {
				renderFire(x, y, z, partialTicks);
			}

		}
	}

	private void renderFire(double x, double y, double z, float partialTicks) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_0");
		TextureAtlasSprite textureatlassprite1 = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_1");
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.65F, (float) z + 0.5F);
		float f = 0.95F;
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		float f1 = 0.5F;
		float f3 = 1.0F / f;
		float f5 = 0.05F;
		// GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F,
		// 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float f6 = textureatlassprite2.getMinU();
		float f7 = textureatlassprite2.getMinV();
		float f8 = textureatlassprite2.getMaxU();
		float f9 = textureatlassprite2.getMaxV();

		if (i / 2 % 2 == 0) {
			float f10 = f8;
			f8 = f6;
			f6 = f10;
		}

		vertexbuffer.pos(f1, 0.0F, f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f1, 0.0F, f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f1, 1.0F, f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(f1, 1.0F, f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f1, 0.0F, f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(f1, 0.0F, f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(f1, 1.0F, f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f1, 1.0F, f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(f1, 0.0F, -f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f1, 0.0F, -f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f1, 1.0F, -f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(f1, 1.0F, -f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f1, 0.0F, -f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(f1, 0.0F, -f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(f1, 1.0F, -f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f1, 1.0F, -f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, 1.0F, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, 1.0F, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, 1.0F, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, 1.0F, f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, 1.0F, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, 1.0F, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, 1.0F, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, 1.0F, f1).tex(f8, f7).endVertex();

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}

}
