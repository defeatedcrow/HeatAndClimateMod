package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.main.block.build.TileChandelierSalt;
import defeatedcrow.hac.main.client.model.ModelChandelierSalt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TESRChandelierSalt extends TileEntitySpecialRenderer<TileChandelierSalt> {

	private static final String TEX = "dcs_climate:textures/tiles/chandelier_salt.png";
	private final ModelChandelierSalt model = new ModelChandelierSalt();

	@Override
	public void render(TileChandelierSalt te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			this.bindTexture(new ResourceLocation(TEX));

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
	}

}
