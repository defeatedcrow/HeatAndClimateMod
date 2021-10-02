package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.block.device.TileChamberBase;
import defeatedcrow.hac.main.client.model.ModelMetalChamber;
import defeatedcrow.hac.main.client.model.ModelNormalChamber_B;
import defeatedcrow.hac.main.client.model.ModelNormalChamber_C;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRNormalChamber extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/metal_chamber.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/metal_chamber_bricks.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/metal_chamber_stove.png";
	private static final ModelMetalChamber MODEL_A = new ModelMetalChamber();
	private static final ModelNormalChamber_B MODEL_B = new ModelNormalChamber_B();
	private static final ModelNormalChamber_C MODEL_C = new ModelNormalChamber_C();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		int type = 0;
		int face = 0;
		float f = 0.0F;

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

			type = meta & 3;
			face = 5 - (meta >> 2);
			if (face == 2) {
				f = 0F;
			}
			if (face == 3) {
				f = 180F;
			}
			if (face == 4) {
				f = -90F;
			}
			if (face == 5) {
				f = 90F;
			}
		}

		int color = 0;
		if (te instanceof IColorChangeTile)
			color = ((IColorChangeTile) te).getColor();

		DCTileModelBase model = this.getModel(color);

		this.bindTexture(new ResourceLocation(getTexPass(color)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		this.render(model, 0.0F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();
			boolean lit = ((TileChamberBase) te).isActive();
			int level = ((TileChamberBase) te).getCurrentHeatID();
			if (lit) {
				renderFire(level, x, y, z, partialTicks, color);
			}
		}
	}

	@Override
	protected String getTexPass(int i) {
		switch (i) {
		case 0:
			return TEX_A;
		case 1:
			return TEX_C;
		case 2:
			return TEX_B;
		}
		return TEX_A;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		switch (i) {
		case 0:
			return MODEL_A;
		case 1:
			return MODEL_B;
		case 2:
			return MODEL_C;
		}
		return MODEL_A;
	}

	private void renderFire(int level, double x, double y, double z, float partialTicks, int type) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_0");
		TextureAtlasSprite textureatlassprite1 = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_1");
		GlStateManager.pushMatrix();
		float fy = type == 2 ? 0.15F : 0F;
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.1F + fy, (float) z + 0.5F);
		float f = 0.25F;
		if (level > DCHeatTier.KILN.getID()) {
			f = 0.75F;
		} else if (level > DCHeatTier.OVEN.getID()) {
			f = 0.5F;
		}
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
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
