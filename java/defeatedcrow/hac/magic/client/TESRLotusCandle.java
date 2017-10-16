package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.magic.block.TileLotusCandle;
import defeatedcrow.hac.magic.block.TileLotusCandleBlack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRLotusCandle extends TileEntitySpecialRenderer<TileLotusCandle> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/tiles/lotus_candle_black.png");
	private static final ResourceLocation TEX2 = new ResourceLocation("dcs_climate",
			"textures/tiles/lotus_candle_white.png");
	private static final ModelLotusCandle MODEL = new ModelLotusCandle();

	@Override
	public void render(TileLotusCandle te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null && te instanceof TileLotusCandleBlack) {
			this.bindTexture(TEX);
		} else {
			this.bindTexture(TEX2);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.0125F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);
		MODEL.renderMiddle(0.0625F);

		for (int i = 0; i < 6; i++) {
			GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
			MODEL.render(0.0625F);
		}
		GlStateManager.disableRescaleNormal();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}

	protected void render(DCTileModelBase model, float f) {
		model.render(f);
	}
}
