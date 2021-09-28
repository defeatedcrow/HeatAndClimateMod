package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockSkillet;
import defeatedcrow.hac.main.api.IColorChangeTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRSkillet extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/iron_skillet.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/iron_skillet_white.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/iron_skillet_orange.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/iron_skillet_blue.png";
	private static final ModelBlockSkillet MODEL = new ModelBlockSkillet();

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

		if (te instanceof IColorChangeTile)
			type = ((IColorChangeTile) te).getColor();

		DCTileModelBase model = this.getModel(type);

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		this.render(model, 0.0F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected String getTexPass(int i) {
		switch (i) {
		case 0:
			return TEX_A;
		case 1:
			return TEX_B;
		case 2:
			return TEX_C;
		case 3:
			return TEX_D;
		}
		return TEX_A;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amo, float f0) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.75F);
		GlStateManager.rotate(f0, 0.0F, 1.0F, 0.0F);

		float f2 = 0.0625F + 0.5F * amo / 5000F;
		float f = 0.4F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		// GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F,
		// 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float uMin = textureatlassprite.getMinU();
		float vMin = textureatlassprite.getMinV();
		float uMax = textureatlassprite.getMaxU();
		float vMax = textureatlassprite.getMaxV();

		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMin).endVertex();

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
}
