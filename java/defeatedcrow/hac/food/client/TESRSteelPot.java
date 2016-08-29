package defeatedcrow.hac.food.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.client.model.ModelSteelPot;

@SideOnly(Side.CLIENT)
public class TESRSteelPot extends DCLockableTESRBase {

	@Override
	public void renderTileEntityAt(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		if (te instanceof TileSteelPot && te.hasWorldObj()) {
			TileSteelPot pot = (TileSteelPot) te;
			Fluid f = pot.outputT.getFluidType();
			if (f == null) {
				f = pot.inputT.getFluidType();
			}
			if (f != null) {
				renderFluid(f, x, y, z, partialTicks);
			}
		}
	}

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/steel_pot.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return new ModelSteelPot();
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);
		float f2 = 0.7F;
		float f = 0.4F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
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
