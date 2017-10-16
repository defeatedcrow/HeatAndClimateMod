package defeatedcrow.hac.machine.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.block.TileHopperFluid;
import defeatedcrow.hac.machine.client.model.ModelHopperF;
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
public class HopperFluidTESR extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/hopper_fluid_silver.png";
	private static final ModelHopperF MODEL = new ModelHopperF();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te != null && te instanceof TileHopperFluid && te.hasWorld()) {

			int type = 0;
			int face = 0;
			float f = 0.0F;

			DCTileModelBase model = this.getModel(type);

			this.bindTexture(new ResourceLocation(TEX));
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
			MODEL.render(0.0F, 0.0F, partialTicks);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			TileHopperFluid pot = (TileHopperFluid) te;
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
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		float f2 = 0.0375F + 0.45F * amount / 5000F;
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

		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(-f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, 0F, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, 0F, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, 0F, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, 0F, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, -f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(-f, f2, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, 0F, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, 0F, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, 0F, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, 0F, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMax, vMin).endVertex();

		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();

	}
}
