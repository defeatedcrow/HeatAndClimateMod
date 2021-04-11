package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.magic.block.TileVeinBeacon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRVeinBeacon extends TileEntitySpecialRenderer<TileVeinBeacon> {

	public static final String RED = "dcs_climate:entity/magic/beacon_red";
	public static final String GREEN = "dcs_climate:entity/magic/beacon_green";
	public static final String BLUE = "dcs_climate:entity/magic/beacon_blue";
	public static final String WHITE = "dcs_climate:entity/magic/beacon_white";
	public static final String BLACK = "dcs_climate:entity/magic/beacon_black";
	public static final String GUANO = "dcs_climate:entity/magic/beacon_guano";
	public static final String[] COLOR = { RED, GREEN, BLUE, WHITE, BLACK, GUANO };

	@Override
	public void render(TileVeinBeacon te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		if (te != null) {
			int meta = te.getBlockMetadata();
			int c = getColor(meta);
			if (meta >= 0 && meta < 6) {
				String s = COLOR[c];
				GlStateManager.disableLighting();
				GlStateManager.disableFog();
				TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
				TextureAtlasSprite sprite = texturemap.getAtlasSprite(s);
				GlStateManager.pushMatrix();
				// GL11.glEnable(GL11.GL_BLEND);
				// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				// GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
				int i2 = 15728880;
				int j2 = i2 % 65536;
				int k2 = i2 / 65536;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j2, k2);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				float h = -108F;
				float h2 = 128F;
				float f = 0.25F;
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder vertexbuffer = tessellator.getBuffer();

				int i = 0;
				vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

				this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				float uMin = sprite.getMinU();
				float vMin = sprite.getMinV();
				float uMax = sprite.getMaxU();
				float vMax = sprite.getMaxV();

				vertexbuffer.pos(-f, h, -f).tex(uMin, vMin).endVertex();
				vertexbuffer.pos(-f, h, f).tex(uMax, vMin).endVertex();
				vertexbuffer.pos(-f, h2, f).tex(uMax, vMax).endVertex();
				vertexbuffer.pos(-f, h2, -f).tex(uMin, vMax).endVertex();

				vertexbuffer.pos(f, h, f).tex(uMin, vMin).endVertex();
				vertexbuffer.pos(f, h, -f).tex(uMax, vMin).endVertex();
				vertexbuffer.pos(f, h2, -f).tex(uMax, vMax).endVertex();
				vertexbuffer.pos(f, h2, f).tex(uMin, vMax).endVertex();

				vertexbuffer.pos(-f, h, f).tex(uMin, vMin).endVertex();
				vertexbuffer.pos(f, h, f).tex(uMax, vMin).endVertex();
				vertexbuffer.pos(f, h2, f).tex(uMax, vMax).endVertex();
				vertexbuffer.pos(-f, h2, f).tex(uMin, vMax).endVertex();

				vertexbuffer.pos(f, h, -f).tex(uMin, vMin).endVertex();
				vertexbuffer.pos(-f, h, -f).tex(uMax, vMin).endVertex();
				vertexbuffer.pos(-f, h2, -f).tex(uMax, vMax).endVertex();
				vertexbuffer.pos(f, h2, -f).tex(uMin, vMax).endVertex();

				tessellator.draw();
				// GL11.glDisable(GL11.GL_BLEND);
				GlStateManager.popMatrix();
				GlStateManager.enableLighting();
				GlStateManager.enableFog();
			}
		}
	}

	protected void render(DCTileModelBase model, float f) {
		model.render(f);
	}

	public static int getColor(int meta) {
		if (meta < 5) {
			return meta;
		} else {
			switch (meta) {
			case 5:
				return 0;
			case 6:
				return 5;
			case 9:
				return 4;
			default:
				return -1;
			}
		}
	}

	@Override
	public boolean isGlobalRenderer(TileVeinBeacon te) {
		return true;
	}
}
