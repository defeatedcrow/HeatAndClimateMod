package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.client.model.ModelChandelier;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class TESRChandelier extends DCTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/fuel_stove.png";
	private final ModelChandelier model = new ModelChandelier();

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		if (te != null) {

			this.bindTexture(new ResourceLocation(getTexPass(0)));

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.disableLighting();
			int i = 15728880;
			int j = i % 65536;
			int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
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
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
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
	}

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/weather_vane.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return model;
	}

}
