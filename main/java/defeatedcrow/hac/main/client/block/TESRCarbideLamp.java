package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.main.block.build.TileLampCarbide;
import defeatedcrow.hac.main.client.model.ModelLampCarbide;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TESRCarbideLamp extends TileEntitySpecialRenderer<TileLampCarbide> {

	private static final String TEX = "dcs_climate:textures/tiles/carbidelamp_brass.png";
	private final ModelLampCarbide model = new ModelLampCarbide();

	@Override
	public void render(TileLampCarbide te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null && te.hasWorld()) {
			float f = 0.0F;
			int meta = te.getBlockMetadata();
			int m = meta & 7;
			if (m > 5) {
				m = 5;
			}
			EnumSide side = EnumSide.fromIndex(m);
			boolean hasB = false;
			switch (side) {
			case NORTH:
				f = 0F;
				hasB = true;
				break;
			case SOUTH:
				f = 180F;
				hasB = true;
				break;
			case EAST:
				f = 90F;
				hasB = true;
				break;
			case WEST:
				f = -90F;
				hasB = true;
				break;
			default:
				break;
			}

			this.bindTexture(new ResourceLocation(TEX));

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();

			if (hasB)
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.75F, (float) z + 0.5F);
			else
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			model.render(f);

			if (hasB)
				model.renderBar();

			GlStateManager.disableLighting();
			int i = 15728880;
			int j = i % 65536;
			int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);

			model.renderLamp();

			GlStateManager.enableBlend();
			GlStateManager
					.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.35F);
			model.renderGlass();
			GlStateManager.disableBlend();

			GlStateManager.enableLighting();

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

		}
	}

}
