package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.client.model.ModelBlockPotteryPot;
import defeatedcrow.hac.food.client.model.ModelBlockPotteryPot_A;
import defeatedcrow.hac.food.client.model.ModelBlockPotteryPot_B;
import defeatedcrow.hac.food.client.model.ModelBlockPotteryPot_C;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRPotteryPot extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/pottery_pot.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/pottery_pot_white.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/pottery_pot_black.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/pottery_pot_kama.png";
	private static final String TEX_E = "dcs_climate:textures/tiles/pottery_pot_wood.png";
	private static final ModelBlockPotteryPot MODEL_A = new ModelBlockPotteryPot();
	private static final ModelBlockPotteryPot_A MODEL_B = new ModelBlockPotteryPot_A();
	private static final ModelBlockPotteryPot_B MODEL_C = new ModelBlockPotteryPot_B();
	private static final ModelBlockPotteryPot_C MODEL_D = new ModelBlockPotteryPot_C();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te instanceof TilePotteryPot && te.hasWorld()) {
			TilePotteryPot pot = (TilePotteryPot) te;
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
			model.render(0.0625F);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			if (pot.hasCap() && model instanceof ModelBlockPotteryPot) {
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);
				((ModelBlockPotteryPot) model).renderCap(0.0625F);
				GlStateManager.popMatrix();
			}

			FluidStack fluid = pot.outputT.getFluid();
			if (fluid == null) {
				fluid = pot.inputT.getFluid();
			}
			if (fluid != null) {
				renderFluid(fluid.getFluid(), x, y, z, partialTicks, fluid.amount, type);
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
		case 2:
			return MODEL_B;
		case 3:
			return MODEL_C;
		case 4:
			return MODEL_D;
		}
		return MODEL_A;
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amo, int type) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);
		float f2 = 0.0625F + 0.7F * amo / 5000F;
		float f = 0.4F;
		if (type == 1 || type == 2) {
			f = 0.25F;
		} else if (type == 3 || type == 4) {
			f = 0.32F;
			f2 = 0.125F + 0.3F * amo / 5000F;
		}
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
