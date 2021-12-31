package defeatedcrow.hac.food.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_A;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_B;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_C;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_D;
import defeatedcrow.hac.food.client.model.ModelBlockTeaPot_E;
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
public class TESRTeaPot extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/teapot_silver.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/teapot_white.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/teapot_orange.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/teapot_black.png";
	private static final String TEX_E = "dcs_climate:textures/tiles/teapot_machine.png";
	private static final ModelBlockTeaPot_A MODEL_A = new ModelBlockTeaPot_A();
	private static final ModelBlockTeaPot_B MODEL_B = new ModelBlockTeaPot_B();
	private static final ModelBlockTeaPot_C MODEL_C = new ModelBlockTeaPot_C();
	private static final ModelBlockTeaPot_D MODEL_D = new ModelBlockTeaPot_D();
	private static final ModelBlockTeaPot_E MODEL_E = new ModelBlockTeaPot_E();

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
				f = 180F;
			}
			if (face == 3) {
				f = 0F;
			}
			if (face == 4) {
				f = 90F;
			}
			if (face == 5) {
				f = -90F;
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

		TileTeaPot pot = (TileTeaPot) te;
		Fluid fluid = pot.outputT.getFluidType();
		if (type == 4 && fluid != null && pot.outputT.getFluidAmount() > 0) {
			renderFluid(fluid, x, y, z, partialTicks, pot.outputT.getFluidAmount(), type, face);
		}
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
		case 4:
			return TEX_E;
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
		case 3:
			return MODEL_D;
		case 4:
			return MODEL_E;
		}
		return MODEL_A;
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amount, int type,
			int r) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float xi = 0.2125F;
		if (r == 2 || r == 4) {
			xi = -0.2125F;
		}
		float zi = 0.2125F;
		if (r == 2 || r == 5) {
			zi = -0.2125F;
		}
		GlStateManager.translate((float) x + 0.5F + xi, (float) y, (float) z + 0.5F + zi);
		float f2 = 0.0625F + 0.25F * amount / 5000F;
		float f3 = 0.0625F;
		float f = 0.15F;
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
