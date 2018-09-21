package defeatedcrow.hac.machine.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.block.TileGasBurner;
import defeatedcrow.hac.machine.client.model.ModelGasBurner;
import net.minecraft.block.state.IBlockState;
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
public class GasBurnerTESR extends DCTESRBase {

	public static final ResourceLocation TEX1 = new ResourceLocation("dcs_climate", "tiles/gasflame_layer_0");
	private static final String TEX = "dcs_climate:textures/tiles/burner_sus.png";
	private static final ModelGasBurner MODEL = new ModelGasBurner();

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		boolean lit = false;
		boolean power = false;
		if (te.hasWorld() && te instanceof TileGasBurner) {
			IBlockState state = te.getWorld().getBlockState(te.getPos());
			int meta = DCState.getInt(state, DCState.TYPE4);
			lit = (meta & 3) == 1;
			power = (meta & 2) == 0;
		}

		DCTileModelBase model = this.getModel(0);
		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
		this.render(model, 0.0F);
		MODEL.renderLever(0.0F, power);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		if (lit) {
			renderFire(1, x, y, z, partialTicks);
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

	private void renderFire(int level, double x, double y, double z, float partialTicks) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(TEX1.toString());
		TextureAtlasSprite textureatlassprite1 = texturemap.getAtlasSprite(TEX1.toString());
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.85F, (float) z + 0.5F);
		float f = 1.0F;
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		float f1 = 0.3F;
		float f3 = 0.35F;
		float f4 = 0.45F;
		float f5 = 0.3F;
		// GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F,
		// 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float f6 = textureatlassprite1.getMinU();
		float f7 = textureatlassprite1.getMinV();
		float f8 = textureatlassprite1.getMaxU();
		float f9 = textureatlassprite1.getMaxV();

		if (i / 2 % 2 == 0) {
			float f10 = f8;
			f8 = f6;
			f6 = f10;
		}

		vertexbuffer.pos(f1, 0.0F, f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f1, 0.0F, f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f1, f3, f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(f1, f3, f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f1, 0.0F, f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(f1, 0.0F, f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(f1, f3, f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f1, f3, f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(f1, 0.0F, -f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f1, 0.0F, -f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f1, f3, -f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(f1, f3, -f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f1, 0.0F, -f5).tex(f8, f9).endVertex();
		vertexbuffer.pos(f1, 0.0F, -f5).tex(f6, f9).endVertex();
		vertexbuffer.pos(f1, f3, -f5).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f1, f3, -f5).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, f3, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, f3, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, f3, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, f3, f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, f3, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, f3, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, f3, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, f3, f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, f4, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, f4, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, f4, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, f4, f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(f5, 0.0F, -f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(-f5, 0.0F, f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(-f5, f4, f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(f5, f4, -f1).tex(f8, f7).endVertex();

		vertexbuffer.pos(-f5, 0.0F, f1).tex(f8, f9).endVertex();
		vertexbuffer.pos(f5, 0.0F, -f1).tex(f6, f9).endVertex();
		vertexbuffer.pos(f5, f4, -f1).tex(f6, f7).endVertex();
		vertexbuffer.pos(-f5, f4, f1).tex(f8, f7).endVertex();

		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
}
