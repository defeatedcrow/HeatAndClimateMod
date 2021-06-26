package defeatedcrow.hac.machine.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.block.TileIBC;
import defeatedcrow.hac.machine.client.model.ModelIBC;
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
public class IBCTESR extends DCTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/ibc_cage.png";
	private static final String TEX_BODY = "dcs_climate:textures/tiles/ibc_body.png";
	private static final String TEX_BOTTOM = "dcs_climate:textures/blocks/build/sofa_black_side.png";
	private static final ModelIBC MODEL = new ModelIBC();

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te instanceof TileIBC && te.hasWorld()) {

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

			DCTileModelBase model = this.getModel(type);

			this.bindTexture(new ResourceLocation(TEX_BOTTOM));
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			MODEL.renderBottom(null, 0);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			this.bindTexture(new ResourceLocation(TEX));
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			MODEL.renderCage(null, 0);

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			TileIBC pot = (TileIBC) te;
			Fluid fluid = pot.inputT.getFluidType();
			if (fluid != null && pot.inputT.getFluidAmount() > 0) {
				renderFluid(fluid, x, y, z, partialTicks, pot.inputT.getFluidAmount());
			}

			this.bindTexture(new ResourceLocation(TEX_BODY));
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			MODEL.renderBody(null, 0);
			GL11.glDisable(GL11.GL_BLEND);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

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
		float f2 = 0.0625F + 0.8F * amount / 128000F;
		float f3 = 0.0625F;
		float f = 0.45F;
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
