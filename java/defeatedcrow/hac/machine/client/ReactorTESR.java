package defeatedcrow.hac.machine.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ReactorTESR extends DCTorqueTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/reactor_outer.png";
	private static final String TEX_INNER = "dcs_climate:textures/tiles/reactor_inner.png";
	private static final ModelReactor MODEL = new ModelReactor();

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		EnumFacing base = te.getBaseSide();
		EnumFacing face = te.getFaceSide();
		float x = 0F;
		float y = 0F;
		float z = 0F;

		switch (base) {
		case DOWN:
			x = 90F;
			break;
		case UP:
			x = -90F;
			break;
		case NORTH:
			y = 180F;
			break;
		case SOUTH:
			break;
		case EAST:
			y = -90F;
			break;
		case WEST:
			y = 90F;
			break;
		default:
			break;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);
	}

	@Override
	public void renderTileEntityAt(TileTorqueBase te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		DCTileModelBase model = this.getModel(te);
		if (model == null)
			return;
		float speed = te.currentSpeed;
		float rot = te.prevRotation + (te.currentRotation - te.prevRotation) * partialTicks;

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		this.render(te, model, rot, speed, partialTicks);
		model.render(rot, speed, partialTicks);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		this.bindTexture(new ResourceLocation(TEX_INNER));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(-1.0F, 1.0F, 1.0F);
		GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);

		this.render(te, model, rot, speed, partialTicks);
		MODEL.renderReverce(0.0625F);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
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
		VertexBuffer vertexbuffer = tessellator.getBuffer();
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
