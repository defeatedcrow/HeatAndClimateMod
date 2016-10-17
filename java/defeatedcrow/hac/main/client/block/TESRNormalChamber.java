package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.device.TileChamberBase;
import defeatedcrow.hac.main.client.model.ModelMetalChamber;
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
public class TESRNormalChamber extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/metal_chamber.png";
	private static final ModelMetalChamber MODEL = new ModelMetalChamber();

	@Override
	public void renderTileEntityAt(DCLockableTE te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		if (te.hasWorldObj()) {
			int meta = te.getBlockMetadata();
			boolean lit = ((TileChamberBase) te).isActive();
			int level = ((TileChamberBase) te).getCurrentHeatID();
			if (lit) {
				renderFire(level, x, y, z, partialTicks);
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
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.1F, (float) z + 0.5F);
		float f = 0.35F;
		if (level > DCHeatTier.KILN.getID()) {
			f = 0.8F;
		} else if (level > DCHeatTier.OVEN.getID()) {
			f = 0.5F;
		}
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		float f1 = 0.5F;
		float f3 = 1.0F / f;
		float f5 = 0.25F;
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
