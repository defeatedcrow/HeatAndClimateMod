package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.build.TileMCClock_L;
import defeatedcrow.hac.main.client.model.ModelMCClock;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRMCClock extends TileEntitySpecialRenderer<TileMCClock_L> {

	private static final DCTileModelBase MODEL = new ModelMCClock();

	@Override
	public void render(TileMCClock_L te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		float size = te.getSize();
		float fx = 0F;
		float fz = 0F;

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

			face = 5 - (meta & 3);
			if (face == 2) {
				f = 90F;
				fz = 0.5F;
			}
			if (face == 3) {
				f = -90F;
				fz = -0.5F;
			}
			if (face == 4) {
				f = 0F;
				fx = 0.5F;
			}
			if (face == 5) {
				f = 180F;
				fx = -0.5F;
			}
		}

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F + fx, (float) y + 0.5F, (float) z + 0.5F + fz);
		GlStateManager.scale(size, -size, -size);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		MODEL.render(partialTicks, 0, 0);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/large_clock_mc.png";
	}
}
