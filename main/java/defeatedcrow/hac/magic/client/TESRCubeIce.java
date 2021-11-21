package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.magic.block.TileCubeIce;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRCubeIce extends TileEntitySpecialRenderer<TileCubeIce> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate", "textures/tiles/cube_ice.png");
	private static final ModelMagicCube MODEL = new ModelMagicCube();

	@Override
	public void render(TileCubeIce te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		this.bindTexture(TEX);

		float rot = te.living + partialTicks;
		rot = (rot / 120F) * 360F;

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager
				.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.85F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
		MODEL.render(rot);

		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected void render(DCTileModelBase model, float f) {
		model.render(f);
	}
}
