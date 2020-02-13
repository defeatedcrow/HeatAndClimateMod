package defeatedcrow.hac.main.client.block;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.client.model.ModelIBC;
import defeatedcrow.hac.main.block.device.TilePail;
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
public class TESRPail extends DCTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/ibc_cage.png";
	private static final ModelIBC MODEL = new ModelIBC();

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te instanceof TilePail && te.hasWorld()) {

			float f = 0.0F;

			TilePail pot = (TilePail) te;
			Fluid fluid = pot.inputT.getFluidType();
			if (fluid != null && pot.inputT.getFluidAmount() > 0) {
				renderFluid(fluid, x, y, z, partialTicks, pot.inputT.getFluidAmount());
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

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amount) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);
		float f2 = 0.7F;
		float f3 = 0.3F;
		float f = 0.38F;
		float f4 = 0.20F;
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

		vertexbuffer.pos(-f, f2, -f4).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f3, -f4).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f3, f4).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, f2, f4).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(f, f2, f4).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, f3, f4).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, f3, -f4).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, -f4).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(-f4, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f4, f3, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f4, f3, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f4, f2, f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(f4, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f4, f3, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f4, f3, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f4, f2, -f).tex(uMax, vMin).endVertex();

		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();

	}
}
