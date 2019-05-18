package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.main.block.build.TileChandelierChal;
import defeatedcrow.hac.main.client.model.ModelChandelierChal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TESRChandelierChal extends TileEntitySpecialRenderer<TileChandelierChal> {

	private static final String TEX = "dcs_climate:textures/tiles/chandelier_crystal.png";
	private final ModelChandelierChal model = new ModelChandelierChal();

	@Override
	public void render(TileChandelierChal te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			this.bindTexture(new ResourceLocation(TEX));

			GlStateManager.pushMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.375F, (float) z + 0.5F);
			GlStateManager.scale(1.25F, -1.25F, -1.25F);
			GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);

			model.render(0.0F, 0.0F, 0.0F);

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

			for (int m = 0; m <= 6; m++) {
				GlStateManager.rotate(60, 0.0F, 1.0F, 0.0F);
				model.renderLamp();
			}

			GlStateManager.enableLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

		}
	}

}
