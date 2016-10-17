package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.client.model.ModelFuelStove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRFuelStove extends DCTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/fuel_stove.png";
	private static final ModelFuelStove MODEL = new ModelFuelStove();

	@Override
	public void renderTileEntityAt(DCTileEntity te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		if (te.hasWorldObj() && te instanceof TileCookingStove) {
			int meta = te.getBlockMetadata();
			boolean lit = (meta & 3) == 1;
			if (lit) {
				renderFire(1, x, y, z, partialTicks);
			}
		}
	}

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}

	private void renderFire(int level, double x, double y, double z, float partialTicks) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_0");
		TextureAtlasSprite textureatlassprite1 = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_1");
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.9125F, (float) z + 0.5F);
		float f = 1.0F;
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		float f1 = 0.2F;
		float f3 = 0.3F;
		float f5 = 0.125F;
		// GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F,
		// 1.0F, 0.0F);
		GlStateManager.color(1.5F, 0.85F, 0.85F, 1.0F);

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
		vertexbuffer.pos(-f1, f3, f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(f1, f3, f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f1, 0.0F, f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(f1, 0.0F, f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(f1, f3, f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f1, f3, f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(f1, 0.0F, -f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f1, 0.0F, -f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f1, f3, -f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(f1, f3, -f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f1, 0.0F, -f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(f1, 0.0F, -f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(f1, f3, -f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f1, f3, -f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, f3, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, f3, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, f3, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, f3, f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, f3, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, f3, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, f3, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, f3, f1).tex(f8, f7).endVertex();

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
}
