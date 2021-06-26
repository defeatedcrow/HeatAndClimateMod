package defeatedcrow.hac.machine.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.block.tankyard.TileTankYard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TankYardTESR extends DCTESRBase {

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te instanceof TileTankYard && te.hasWorld()) {
			TileTankYard tank = (TileTankYard) te;

			if (tank.multi) {
				float w = tank.width - 2F;
				float h = tank.height - 2F;

				Fluid fluid = tank.getTank().getFluidType();
				if (fluid != null && w >= 1F && h >= 1F) {
					int amo = tank.getTank().getFluidAmount();
					int lim = tank.getTank().getCapacity();
					renderFluid(fluid, x, y, z, partialTicks, amo, lim, w, h);
				}
			}

		}
	}

	@Override
	protected String getTexPass(int i) {
		return null;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return null;
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amount, int limit,
			float width, float height) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1F, (float) z + 0.5F);
		float f2 = height * amount / limit;
		Math.min(f2, 1.0F);
		float f = width * 0.5F;
		float f3 = 0F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float uMin = textureatlassprite.getMinU();
		float vMin = textureatlassprite.getMinV();
		float uMax = textureatlassprite.getMaxU();
		float vMax = textureatlassprite.getMaxV();

		vertexbuffer.pos(f, f2, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMax).endVertex();

		vertexbuffer.pos(-f, f3, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(-f, f3, -f).tex(uMax, vMax).endVertex();

		vertexbuffer.pos(f, f3, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f3, f).tex(uMax, vMax).endVertex();

		vertexbuffer.pos(f, f3, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(-f, f3, f).tex(uMax, vMax).endVertex();

		vertexbuffer.pos(-f, f3, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMax, vMin).endVertex();
		vertexbuffer.pos(f, f3, -f).tex(uMax, vMax).endVertex();

		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();

	}
}
