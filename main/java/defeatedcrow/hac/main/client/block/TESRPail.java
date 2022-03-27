package defeatedcrow.hac.main.client.block;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.device.TilePail;
import defeatedcrow.hac.main.client.model.ModelPail_A;
import defeatedcrow.hac.main.client.model.ModelPail_B;
import defeatedcrow.hac.main.client.model.ModelPail_C;
import defeatedcrow.hac.main.client.model.ModelPail_D;
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
public class TESRPail extends DCTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/pail_tin.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/pail_polytank.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/pail_steeltank.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/pail_drink.png";
	private static final ModelPail_A MODEL_A = new ModelPail_A();
	private static final ModelPail_B MODEL_B = new ModelPail_B();
	private static final ModelPail_C MODEL_C = new ModelPail_C();
	private static final ModelPail_D MODEL_D = new ModelPail_D();

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te instanceof TilePail && te.hasWorld()) {

			float f = 0.0F;
			int type = 0;
			int face = 0;

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

			DCTileModelBase model = this.getModel(type);
			String tex = this.getTexPass(type);

			this.bindTexture(new ResourceLocation(tex));
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			model.render(0F);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			if (type == 0 || type == 3) {
				TilePail pot = (TilePail) te;
				Fluid fluid = pot.inputT.getFluidType();
				if (fluid != null && pot.inputT.getFluidAmount() > 0) {

					if (type == 0) {
						renderFluidLabel(fluid, x, y, z, partialTicks);
					} else if (type == 3) {
						renderFluid(fluid, x, y, z, partialTicks, pot.inputT.getFluidAmount());
					}
				}
			}

			if (type == 3) {
				this.bindTexture(new ResourceLocation(tex));
				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);
				GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
				model.render(0F, 0F, 0F);
				GL11.glDisable(GL11.GL_BLEND);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}
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
		}
		return MODEL_A;
	}

	private void renderFluidLabel(Fluid fluid, double x, double y, double z, float partialTicks) {
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
		float uM = textureatlassprite.getMinU();
		float vM = textureatlassprite.getMinV();
		float uX = textureatlassprite.getMaxU();
		float vX = textureatlassprite.getMaxV();

		vertexbuffer.pos(f, f3, -f4).tex(uM, vX).endVertex();
		vertexbuffer.pos(f, f2, -f4).tex(uM, vM).endVertex();
		vertexbuffer.pos(f, f2, f4).tex(uX, vM).endVertex();
		vertexbuffer.pos(f, f3, f4).tex(uX, vX).endVertex();

		vertexbuffer.pos(-f, f3, f4).tex(uM, vX).endVertex();
		vertexbuffer.pos(-f, f2, f4).tex(uM, vM).endVertex();
		vertexbuffer.pos(-f, f2, -f4).tex(uX, vM).endVertex();
		vertexbuffer.pos(-f, f3, -f4).tex(uX, vX).endVertex();

		vertexbuffer.pos(f4, f3, f).tex(uM, vX).endVertex();
		vertexbuffer.pos(f4, f2, f).tex(uM, vM).endVertex();
		vertexbuffer.pos(-f4, f2, f).tex(uX, vM).endVertex();
		vertexbuffer.pos(-f4, f3, f).tex(uX, vX).endVertex();

		vertexbuffer.pos(-f4, f3, -f).tex(uM, vX).endVertex();
		vertexbuffer.pos(-f4, f2, -f).tex(uM, vM).endVertex();
		vertexbuffer.pos(f4, f2, -f).tex(uX, vM).endVertex();
		vertexbuffer.pos(f4, f3, -f).tex(uX, vX).endVertex();

		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();

	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amount) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);
		float f2 = 0.3125F + 0.5F * amount / 18000F;
		float f3 = 0.3125F;
		float f = 0.25F;
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
