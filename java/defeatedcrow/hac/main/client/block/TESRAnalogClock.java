package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.build.TileRealtimeClock;
import defeatedcrow.hac.main.client.model.ModelAnalogClock;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRAnalogClock extends TileEntitySpecialRenderer<TileRealtimeClock> {

	private static final DCTileModelBase MODEL = new ModelAnalogClock();

	@Override
	public void renderTileEntityAt(TileRealtimeClock te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		int type = 0;
		int face = 0;
		float f = 0.0F;

		if (te.hasWorldObj()) {
			int meta = te.getBlockMetadata();

			face = 5 - (meta & 3);
			if (face == 2) {
				f = 90F;
			}
			if (face == 3) {
				f = -90F;
			}
			if (face == 4) {
				f = 0F;
			}
			if (face == 5) {
				f = 180F;
			}
		}

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		MODEL.render(partialTicks, 0, 0);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/realtime_clock.png";
	}
}
