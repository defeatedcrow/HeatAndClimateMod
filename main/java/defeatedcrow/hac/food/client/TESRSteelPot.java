package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.client.model.ModelBlockSteelPot_A;
import defeatedcrow.hac.food.client.model.ModelBlockSteelPot_B;
import defeatedcrow.hac.food.client.model.ModelBlockSteelPot_C;
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
public class TESRSteelPot extends DCLockableTESRBase {

	private static final String TEX_A = "dcs_climate:textures/tiles/steel_pot.png";
	private static final String TEX_B = "dcs_climate:textures/tiles/steel_pot_white.png";
	private static final String TEX_C = "dcs_climate:textures/tiles/steel_pot_orange.png";
	private static final String TEX_D = "dcs_climate:textures/tiles/steel_pot_blue.png";
	private static final ModelBlockSteelPot_A MODEL_A = new ModelBlockSteelPot_A();
	private static final ModelBlockSteelPot_B MODEL_B = new ModelBlockSteelPot_B();
	private static final ModelBlockSteelPot_C MODEL_C = new ModelBlockSteelPot_C();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage, float a) {

		if (te instanceof TileSteelPot && te.hasWorld()) {
			TileSteelPot pot = (TileSteelPot) te;
			int type = 0;
			int face = 0;
			float f = 0.0F;

			if (te.hasWorld()) {
				int meta = te.getBlockMetadata();

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

			if (type != 0) {
				f += 90F;
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);

			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			model.render(0.0625F);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			if (pot.hasCap() && model instanceof ModelBlockSteelPot_A) {
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);
				((ModelBlockSteelPot_A) model).renderCap(0.0625F);
				GlStateManager.popMatrix();
			}

			FluidStack fluid = pot.outputT.getFluid();
			if (fluid == null) {
				fluid = pot.inputT.getFluid();
			}
			if (fluid != null) {
				int amo = fluid.amount;
				renderFluid(fluid.getFluid(), x, y, z, partialTicks, amo, type);
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
			return MODEL_B;
		case 3:
			return MODEL_C;
		}
		return MODEL_A;
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float partialTicks, int amo, int type) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);
		float f2 = 0.125F + 0.6F * amo / 5000F;
		float f = 0.4F;
		if (type > 0) {
			f2 *= 0.4F;
		}
		if (type == 1 || type == 2) {
			f *= 0.65F;
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
