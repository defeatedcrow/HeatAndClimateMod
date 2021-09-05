package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.magic.block.TileTimeCage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRTimeCage extends TileEntitySpecialRenderer<TileTimeCage> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/tiles/timecage_base.png");
	private static final ResourceLocation TEX_BLACK = new ResourceLocation("dcs_climate",
			"textures/tiles/timecage_black.png");
	private static final ResourceLocation TEX_GREEN = new ResourceLocation("dcs_climate",
			"textures/tiles/timecage_green.png");
	private static final ResourceLocation TEX_RED = new ResourceLocation("dcs_climate",
			"textures/tiles/timecage_red.png");
	private static final ResourceLocation TEX_WHITE = new ResourceLocation("dcs_climate",
			"textures/tiles/timecage_white.png");
	private static final ModelButterflySpecimen MODEL = new ModelButterflySpecimen();

	@Override
	public void render(TileTimeCage te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		this.bindTexture(TEX);
		float r = 0.0F;
		int type = 0;
		int face = 0;

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

			type = meta & 3;
			face = 5 - (meta >> 2);
			if (face == 2) {
				r = 180F;
			}
			if (face == 3) {
				r = 0F;
			}
			if (face == 4) {
				r = 90F;
			}
			if (face == 5) {
				r = -90F;
			}
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(r, 0.0F, 1.0F, 0.0F);
		MODEL.render(0.0625F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		if (type == 1) {
			this.bindTexture(TEX_GREEN);
		} else if (type == 2) {
			this.bindTexture(TEX_RED);
		} else if (type == 3) {
			this.bindTexture(TEX_WHITE);
		} else {
			this.bindTexture(TEX_BLACK);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(0.875F, -0.875F, -0.875F);

		GlStateManager.rotate(r, 0.0F, 1.0F, 0.0F);
		MODEL.renderButterfly(0.0625F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected void render(DCTileModelBase model, float f) {
		model.render(f);
	}
}
