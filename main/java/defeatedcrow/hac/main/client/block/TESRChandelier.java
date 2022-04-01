package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.main.block.build.TileChandelierGypsum;
import defeatedcrow.hac.main.client.model.ModelChandelier;
import defeatedcrow.hac.main.client.model.ModelChandelierCrystal;
import defeatedcrow.hac.main.client.model.ModelChandelierMarble;
import defeatedcrow.hac.main.client.model.ModelChandelierSalt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TESRChandelier extends TileEntitySpecialRenderer<TileChandelierGypsum> {

	private static final String TEX_A = "dcs_climate:textures/tiles/chandelier_alabaster";
	private static final String TEX_B = "dcs_climate:textures/tiles/chandelier_salt";
	private static final String TEX_C = "dcs_climate:textures/tiles/chandelier_crystal";
	private static final String TEX_D = "dcs_climate:textures/tiles/chandelier_marble";
	private final ModelChandelier MODEL_A = new ModelChandelier();
	private final ModelChandelierSalt MODEL_B = new ModelChandelierSalt();
	private final ModelChandelierCrystal MODEL_C = new ModelChandelierCrystal();
	private final ModelChandelierMarble MODEL_D = new ModelChandelierMarble();

	private String[] colors = {
			"_normal",
			"_blue",
			"_white",
			"_yellow"
	};

	@Override
	public void render(TileChandelierGypsum te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null && te.hasWorld()) {

			int meta = te.getBlockMetadata();
			int type = meta & 3;
			int color = meta >> 2;
			color = color & 3;

			if (type == 0) {
				String tex = TEX_A + colors[color] + ".png";
				renderChandelierA(te, x, y, z, MODEL_A, tex);
			}

			if (type == 1) {
				String tex = TEX_B + colors[color] + ".png";
				renderChandelierB(te, x, y, z, MODEL_B, tex);
			}

			if (type == 2) {
				String tex = TEX_C + colors[color] + ".png";
				renderChandelierC(te, x, y, z, MODEL_C, tex);
			}

			if (type == 3) {
				String tex = TEX_D + colors[color] + ".png";
				renderChandelierD(te, x, y, z, MODEL_D, tex);
			}

		}
	}

	private void renderChandelierA(TileChandelierGypsum te, double x, double y, double z, ModelChandelier model,
			String tex) {
		this.bindTexture(new ResourceLocation(tex));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.125F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);

		model.render(0.0F, 0.0F, 0.0F);

		for (int m = 0; m < 4; m++) {
			GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
			model.renderPetal2();
			GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
			model.renderPetal1();
		}

		GlStateManager.enableLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.125F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);

		for (int m = 0; m < 4; m++) {
			GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
			model.renderLamps();
		}

		GlStateManager.enableLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	private void renderChandelierB(TileChandelierGypsum te, double x, double y, double z, ModelChandelierSalt model,
			String tex) {
		this.bindTexture(new ResourceLocation(tex));

		GlStateManager.pushMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.375F, (float) z + 0.5F);
		GlStateManager.scale(1.25F, -1.25F, -1.25F);
		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);

		model.render(0.0F, 0.0F, 0.0F);

		for (int m = 0; m <= 12; m++) {
			GlStateManager.rotate(30, 0.0F, 1.0F, 0.0F);
			model.renderBar();
		}

		GlStateManager.enableLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.375F, (float) z + 0.5F);
		GlStateManager.scale(1.25F, -1.25F, -1.25F);
		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);

		model.renderLamp1();

		for (int m = 0; m <= 12; m++) {
			GlStateManager.rotate(30, 0.0F, 1.0F, 0.0F);
			model.renderLamp2();
		}

		GlStateManager.enableLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	private void renderChandelierC(TileChandelierGypsum te, double x, double y, double z, ModelChandelierCrystal model,
			String tex) {
		this.bindTexture(new ResourceLocation(tex));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);

		model.render(0.0F, 0.0F, 0.0F);
		model.renderLight();

		for (int m = 0; m <= 8; m++) {
			GlStateManager.rotate(45, 0.0F, 1.0F, 0.0F);
			model.renderPart();
			model.renderLightPart();
		}

		GlStateManager.enableLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	private void renderChandelierD(TileChandelierGypsum te, double x, double y, double z, ModelChandelierMarble model,
			String tex) {
		this.bindTexture(new ResourceLocation(tex));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.375F, (float) z + 0.5F);
		GlStateManager.scale(1.25F, -1.25F, -1.25F);
		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);

		model.render(0.0F, 0.0F, 0.0F);

		for (int m = 0; m <= 5; m++) {
			GlStateManager.rotate(72, 0.0F, 1.0F, 0.0F);
			model.renderFlower();
		}

		GlStateManager.enableLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

}
